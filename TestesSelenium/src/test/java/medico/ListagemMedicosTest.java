package medico;
import Faker.MedicFakerUtil;
import Pages.CadastroMedicos;
import Pages.ListaMedicos;
import Pages.PaginaInicial;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ListagemMedicosTest {
    private WebDriver driver;
    private PaginaInicial paginaInicial;
    private CadastroMedicos cadastroMedicos;
    private WebDriverWait webDriverWait;
    private ListaMedicos listaMedicos;
    private Alert alert;
    private String alertMessage;
    final String url = "https://cadastro-medicos-pacientes-a4n9.vercel.app/";

    @BeforeEach
    void setUp()
    {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        paginaInicial = new PaginaInicial(driver);
        cadastroMedicos = new CadastroMedicos(driver);
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        listaMedicos = new ListaMedicos(driver);
    }

    @AfterEach
    void tearDown()
    {
        driver.quit();
    }

    @Nested
    @DisplayName("List all")
    class ListAll
    {
        @Test
        @DisplayName("Should click on the button to list all doctors")
        void shouldClickOnTheButtonToListAllDoctors()
        {
            driver.get(url);
            paginaInicial.goToTheMedicPageWaited();
            cadastroMedicos.clickDoctorsList();
            listaMedicos.listAll();
            webDriverWait.until(ExpectedConditions.alertIsPresent());
            alert = driver.switchTo().alert();
            alertMessage = alert.getText();
            assertEquals("Nenhum objeto encontrado", alertMessage);
            alert.accept();
        }

        @Test
        @DisplayName("Should register some doctors, list them and check if you have been listed")
        void shouldRegisterSomeDoctorsListThemAndCheckIfYouHaveBeenListed()
        {
            driver.get(url);
            paginaInicial.goToTheMedicPageWaited();
            for (int i = 0; i < 5; i++) {
                cadastroMedicos.fillAllFields(
                        MedicFakerUtil.getRandomCRM(),
                        MedicFakerUtil.getNome(),
                        MedicFakerUtil.getDataNascimento(),
                        MedicFakerUtil.getSexo(),
                        MedicFakerUtil.getEspecialidade(),
                        MedicFakerUtil.getUniversidade(),
                        MedicFakerUtil.getEmail(),
                        MedicFakerUtil.getTelefone()
                );

                cadastroMedicos.clickRegisterDoctor();
                webDriverWait.until(ExpectedConditions.alertIsPresent());
                alert = driver.switchTo().alert();
                alertMessage = alert.getText();
                assertEquals("Cadastrado efetuado com sucesso", alertMessage);
                alert.accept();
            }

            cadastroMedicos.clickDoctorsList();
            listaMedicos.listAll();
            List<WebElement> doctorRows = listaMedicos.getDoctorRows();
            assertEquals(5, doctorRows.size(), "The number of registered doctors should be 5.");
        }
    }


    @Nested
    @DisplayName("List a doctor")
    class ListDoctor
    {
        @Test
        @DisplayName("Should return an alert to enter the CRM when clicking the list a button")
        void shouldReturnAnAlertToEnterTheCRMWhenClickingTheListAButton()
        {
            driver.get(url);
            paginaInicial.goToTheMedicPageWaited();
            cadastroMedicos.clickDoctorsList();
            listaMedicos.clickListDoctor();
            webDriverWait.until(ExpectedConditions.alertIsPresent());
            alert = driver.switchTo().alert();
            alertMessage = alert.getText();
            assertEquals("Digite o CRM", alertMessage);
            alert.accept();
        }

        @Test
        @DisplayName("Should list a doctor")
        void shouldListADoctor()
        {
            driver.get(url);
            paginaInicial.goToTheMedicPageWaited();
            String crm = null;
            for (int i = 0; i < 2; i++) {
                crm = MedicFakerUtil.getRandomCRM();
                cadastroMedicos.fillAllFields(
                        crm,
                        MedicFakerUtil.getNome(),
                        MedicFakerUtil.getDataNascimento(),
                        MedicFakerUtil.getSexo(),
                        MedicFakerUtil.getEspecialidade(),
                        MedicFakerUtil.getUniversidade(),
                        MedicFakerUtil.getEmail(),
                        MedicFakerUtil.getTelefone()
                );


                cadastroMedicos.clickRegisterDoctor();
                webDriverWait.until(ExpectedConditions.alertIsPresent());
                alert = driver.switchTo().alert();
                alertMessage = alert.getText();
                assertEquals("Cadastrado efetuado com sucesso", alertMessage);
                alert.accept();
            }

            cadastroMedicos.clickDoctorsList();
            listaMedicos.searchCRM(crm);
            listaMedicos.clickListDoctor();
            List<WebElement> doctorRows = listaMedicos.getDoctorRows();
            assertEquals(1, doctorRows.size(), "The number of registered doctors should be 1.");
        }
    }



    @Nested
    @DisplayName("Edit doctor")
    class Edit
    {
        @Test
        @DisplayName("Should click on change doctor and a message appears to enter the CRM")
        void shouldClickOnChangeDoctorAndAMessageAppearsToEnterTheCRM()
        {
            driver.get(url);
            paginaInicial.goToTheMedicPageWaited();
            cadastroMedicos.clickDoctorsList();
            listaMedicos.clickEditDoctor();

            webDriverWait.until(ExpectedConditions.alertIsPresent());
            alert = driver.switchTo().alert();
            alertMessage = alert.getText();
            assertEquals("Digite o CRM", alertMessage);
            alert.accept();
        }

        @Test
        @DisplayName("Should enter a CRM that does not exist and click on search to try to change the doctor")
        void shouldEnterACRMThatDoesNotExistAndClickOnSearchToTryToChangeTheDoctor()
        {
            driver.get(url);
            paginaInicial.goToTheMedicPageWaited();
            cadastroMedicos.clickDoctorsList();
            listaMedicos.searchCRM(MedicFakerUtil.getRandomCRM());
            listaMedicos.clickEditDoctor();
            webDriverWait.until(ExpectedConditions.alertIsPresent());
            alert = driver.switchTo().alert();
            alertMessage = alert.getText();
            assertEquals("Cadastro não encontrado.", alertMessage);
            alert.accept();
        }

        @Test
        @DisplayName("Should enter a CRM that is registered and change the data")
        void ShouldEnterACRMThatIsRegisteredAndChangeTheData()
        {
            driver.get(url);
            paginaInicial.goToTheMedicPageWaited();

            String crm = null;
            for (int i = 0; i < 2; i++) {
                crm = MedicFakerUtil.getRandomCRM();
                cadastroMedicos.fillAllFields(
                        crm,
                        MedicFakerUtil.getNome(),
                        MedicFakerUtil.getDataNascimento(),
                        MedicFakerUtil.getSexo(),
                        MedicFakerUtil.getEspecialidade(),
                        MedicFakerUtil.getUniversidade(),
                        MedicFakerUtil.getEmail(),
                        MedicFakerUtil.getTelefone()
                );


                cadastroMedicos.clickRegisterDoctor();
                webDriverWait.until(ExpectedConditions.alertIsPresent());
                alert = driver.switchTo().alert();
                alertMessage = alert.getText();
                assertEquals("Cadastrado efetuado com sucesso", alertMessage);
                alert.accept();
            }

            cadastroMedicos.clickDoctorsList();

            listaMedicos.searchCRM(crm);

            listaMedicos.clickEditDoctor();

            cadastroMedicos.clearAllFields();

            cadastroMedicos.fillAllFields(
                    MedicFakerUtil.getRandomCRM(),
                    MedicFakerUtil.getNome(),
                    MedicFakerUtil.getDataNascimento(),
                    MedicFakerUtil.getSexo(),
                    MedicFakerUtil.getEspecialidade(),
                    MedicFakerUtil.getUniversidade(),
                    MedicFakerUtil.getEmail(),
                    MedicFakerUtil.getTelefone()
            );

            cadastroMedicos.clickSaveEditDoctor();

            webDriverWait.until(ExpectedConditions.alertIsPresent());

            alert = driver.switchTo().alert();
            alertMessage = alert.getText();
            assertEquals("Cadastro atualizado com sucesso", alertMessage);
            alert.accept();

        }


        @Test
        @DisplayName("Should enter a CRM that is registered and change the data and check if it has been changed by looking for the CRM again and see if it has been listed")
        void shouldEnterACRMThatIsRegisteredAndChangeTheDataAndCheckIfItHasBeenChangedByLookingForTheCRMAgainAndSeeIfItHasBeenListed()
        {
            driver.get(url);
            paginaInicial.goToTheMedicPageWaited();

            String crm = null;
            String editCrm = null;
            for (int i = 0; i < 2; i++) {
                crm = MedicFakerUtil.getRandomCRM();
                cadastroMedicos.fillAllFields(
                        crm,
                        MedicFakerUtil.getNome(),
                        MedicFakerUtil.getDataNascimento(),
                        MedicFakerUtil.getSexo(),
                        MedicFakerUtil.getEspecialidade(),
                        MedicFakerUtil.getUniversidade(),
                        MedicFakerUtil.getEmail(),
                        MedicFakerUtil.getTelefone()
                );


                cadastroMedicos.clickRegisterDoctor();
                webDriverWait.until(ExpectedConditions.alertIsPresent());
                alert = driver.switchTo().alert();
                alertMessage = alert.getText();
                assertEquals("Cadastrado efetuado com sucesso", alertMessage);
                alert.accept();
            }

            cadastroMedicos.clickDoctorsList();

            listaMedicos.searchCRM(crm);

            listaMedicos.clickEditDoctor();

            cadastroMedicos.clearAllFields();


            editCrm = MedicFakerUtil.getRandomCRM();

            cadastroMedicos.fillAllFields(
                    editCrm,
                    MedicFakerUtil.getNome(),
                    MedicFakerUtil.getDataNascimento(),
                    MedicFakerUtil.getSexo(),
                    MedicFakerUtil.getEspecialidade(),
                    MedicFakerUtil.getUniversidade(),
                    MedicFakerUtil.getEmail(),
                    MedicFakerUtil.getTelefone()
            );

            cadastroMedicos.clickSaveEditDoctor();

            webDriverWait.until(ExpectedConditions.alertIsPresent());

            alert = driver.switchTo().alert();
            alertMessage = alert.getText();
            assertEquals("Cadastro atualizado com sucesso", alertMessage);
            alert.accept();

            cadastroMedicos.clickDoctorsList();

            listaMedicos.searchCRM(editCrm);

            listaMedicos.clickListDoctor();
            List<WebElement> doctorRows = listaMedicos.getDoctorRows();
            assertEquals(1, doctorRows.size(), "The number of registered doctors should be 1.");

        }


        @Test
        @DisplayName("Should check that the fields have been set when you return to the registration page")
        void shouldCheckThatTheFieldsHaveBeenSetWhenYouReturnToTheRegistrationPage()
        {
            driver.get(url);
            paginaInicial.goToTheMedicPageWaited();
            String crm = null;
            for (int i = 0; i < 2; i++) {
                crm = MedicFakerUtil.getRandomCRM();
                cadastroMedicos.fillAllFields(
                        crm,
                        MedicFakerUtil.getNome(),
                        MedicFakerUtil.getDataNascimento(),
                        MedicFakerUtil.getSexo(),
                        MedicFakerUtil.getEspecialidade(),
                        MedicFakerUtil.getUniversidade(),
                        MedicFakerUtil.getEmail(),
                        MedicFakerUtil.getTelefone()
                );


                cadastroMedicos.clickRegisterDoctor();

                webDriverWait.until(ExpectedConditions.alertIsPresent());

                alert = driver.switchTo().alert();
                alertMessage = alert.getText();
                assertEquals("Cadastrado efetuado com sucesso", alertMessage);
                alert.accept();
            }

            cadastroMedicos.clickDoctorsList();

            listaMedicos.searchCRM(crm);

            listaMedicos.clickEditDoctor();

            paginaInicial.goToTheMedicPageWaited();

            assertFalse(cadastroMedicos.isRegisterButtonDisplayed(), "O botão Cadastrar não está presente na página.");

        }
    }

    @Nested
    @DisplayName("Delete doctor")
    class Delete
    {
        @Test
        @DisplayName("Should click on delete a doctor and a message appears to enter the CRM")
        void shouldClickOnDeleteADoctorAndAMessageAppearsToEnterTheCRM()
        {
            driver.get(url);

            paginaInicial.goToTheMedicPageWaited();

            cadastroMedicos.clickDoctorsList();

            listaMedicos.clickDeleteDoctor();

            webDriverWait.until(ExpectedConditions.alertIsPresent());
            alert = driver.switchTo().alert();
            alertMessage = alert.getText();
            assertEquals("Digite o CRM", alertMessage);
            alert.accept();
        }

        @Test
        @DisplayName("Should register two CRM, delete and check if deleted by listing the CRM")
        void shouldRegisterTwoCRMDeleteAndCheckIfDeletedByListingTheCRM()
        {
            driver.get(url);
            paginaInicial.goToTheMedicPageWaited();
            String crm = null;
            for (int i = 0; i < 2; i++) {
                crm = MedicFakerUtil.getRandomCRM();
                cadastroMedicos.fillAllFields(
                        crm,
                        MedicFakerUtil.getNome(),
                        MedicFakerUtil.getDataNascimento(),
                        MedicFakerUtil.getSexo(),
                        MedicFakerUtil.getEspecialidade(),
                        MedicFakerUtil.getUniversidade(),
                        MedicFakerUtil.getEmail(),
                        MedicFakerUtil.getTelefone()
                );


                cadastroMedicos.clickRegisterDoctor();
                webDriverWait.until(ExpectedConditions.alertIsPresent());

                alert = driver.switchTo().alert();
                alertMessage = alert.getText();
                assertEquals("Cadastrado efetuado com sucesso", alertMessage);
                alert.accept();
            }

            cadastroMedicos.clickDoctorsList();

            listaMedicos.searchCRM(crm);

            listaMedicos.clickDeleteDoctor();

            webDriverWait.until(ExpectedConditions.alertIsPresent());
            alert = driver.switchTo().alert();
            alertMessage = alert.getText();
            assertEquals("Objeto removido com sucesso.", alertMessage);
            alert.accept();

            listaMedicos.clickListDoctor();

            webDriverWait.until(ExpectedConditions.alertIsPresent());

            alert = driver.switchTo().alert();
            alertMessage = alert.getText();
            assertEquals("Cadastro não encontrado.", alertMessage);
            alert.accept();

        }

    }


}



