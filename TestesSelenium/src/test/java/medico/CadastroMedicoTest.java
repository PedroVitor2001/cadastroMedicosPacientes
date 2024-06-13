package medico;
import Pages.CadastroMedicos;
import Pages.PaginaInicial;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Faker.MedicFakerUtil;
import java.time.Duration;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class CadastroMedicoTest {
    private WebDriver driver;
    final String url = "https://cadastro-medicos-pacientes-a4n9.vercel.app/";
    private PaginaInicial paginaInicial;
    private CadastroMedicos cadastroMedicos;
    private  WebDriverWait webDriverWait;
    private Alert alert;
    private String alertMessage;

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
    }
    @AfterEach
    void tearDown()
    {
        driver.quit();
    }


    @Nested
    @DisplayName("Tests for page navigation")
    class home
    {
        @Test
        @DisplayName("Should open and close the website")
        void shouldOpenAndCloseTheWebsite(){
            driver.get(url);
        }

        @Test
        @DisplayName("Should click on the doctor's button and go to another page")
        void shouldClickOnTheDoctorsButtonAndGoToAnotherPage()
        {
            driver.get(url);
            paginaInicial.goToTheMedicPageWaited();
        }

        @Test
        @DisplayName("Should click on the patient page button and redirect to the other page")
        void shouldClickOnThePatientePageButtonAndRedirectToTheOtherPage()
        {
            driver.get(url);
            paginaInicial.goToPatientPageWaited();
        }
    }

    @Nested
    @DisplayName("Tests for doctor form validation")
    class formDoctor
    {
        @Test
        @DisplayName("Should navigate to the doctor registration page, click on register and an alert should appear with a message")
        void shouldNavigateToTheDoctorRegistrationPageClickOnRegisterAndAnAlertShouldAppearWithAMessage()
        {
            driver.get(url);
            paginaInicial.goToTheMedicPageWaited();
            cadastroMedicos.clickRegisterDoctor();
            webDriverWait.until(ExpectedConditions.alertIsPresent());
            alert = driver.switchTo().alert();
            alertMessage = alert.getText();
            assertEquals("Todos os campos são obrigatórios.", alertMessage);
            alert.accept();
        }


        @Test
        @DisplayName("Should navigate to the doctor registration page, fill the form, click on register and an alert should appear with a message")
        void shouldNavigateToTheDoctorRegistrationPageFillFormClickOnRegisterAndAnAlertShouldAppearWithAMessage(){
            driver.get(url);
            paginaInicial.goToTheMedicPageWaited();

            cadastroMedicos.fillAllFields(
                    MedicFakerUtil.getWrongRandomCRM(),
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

            assertEquals("O CRM deve estar no formato 0000000/UF.", alertMessage);

            alert.accept();
        }

        @Test
        @DisplayName("Should show an error in the message if it is not a correct phone number")
        void shouldShowAnErrorInTheMessageIfItIsNotACorrectPhoneNumber(){
            driver.get(url);
            paginaInicial.goToTheMedicPageWaited();

            cadastroMedicos.fillAllFields(
                    MedicFakerUtil.getRandomCRM(),
                    MedicFakerUtil.getNome(),
                    MedicFakerUtil.getDataNascimento(),
                    MedicFakerUtil.getSexo(),
                    MedicFakerUtil.getEspecialidade(),
                    MedicFakerUtil.getUniversidade(),
                    MedicFakerUtil.getEmail(),
                    MedicFakerUtil.getWrongTelefone()
            );
            cadastroMedicos.clickRegisterDoctor();
            webDriverWait.until(ExpectedConditions.alertIsPresent());
            alert = driver.switchTo().alert();
            alertMessage = alert.getText();
            assertEquals("Telefone inválido. Deve estar no formato XXXXXXXX", alertMessage);
            alert.accept();
        }

        @Test
        @DisplayName("Should fill in a wrong email and show an error alert")
        void shouldFillInAWrongEmailAndShowAnErrorAlert()
        {
            driver.get(url);

            paginaInicial.goToTheMedicPageWaited();


            cadastroMedicos.fillAllFields(
                    MedicFakerUtil.getRandomCRM(),
                    MedicFakerUtil.getNome(),
                    MedicFakerUtil.getDataNascimento(),
                    MedicFakerUtil.getSexo(),
                    MedicFakerUtil.getEspecialidade(),
                    MedicFakerUtil.getUniversidade(),
                    MedicFakerUtil.getWrongEmail(),
                    MedicFakerUtil.getTelefone()
            );

            cadastroMedicos.clickRegisterDoctor();

            webDriverWait.until(ExpectedConditions.alertIsPresent());

            alert = driver.switchTo().alert();
            alertMessage = alert.getText();

            assertEquals("Email inválido.", alertMessage);

            alert.accept();
        }

        @Test
        @DisplayName("Should register a doctor, show an alert, and attempt to register the same doctor again with the correct message")
        void shouldRegisterDoctorAndAttemptToRegisterAgain() {
            driver.get(url);

            paginaInicial.goToTheMedicPageWaited();

            String crm = MedicFakerUtil.getRandomCRM();
            String nome = MedicFakerUtil.getNome();
            String dataNascimento = MedicFakerUtil.getDataNascimento();
            String sexo = MedicFakerUtil.getSexo();
            String especialidade = MedicFakerUtil.getEspecialidade();
            String universidade = MedicFakerUtil.getUniversidade();
            String email = MedicFakerUtil.getEmail();
            String telefone = MedicFakerUtil.getTelefone();

            cadastroMedicos.fillAllFields(crm, nome, dataNascimento, sexo, especialidade, universidade, email, telefone);
            cadastroMedicos.clickRegisterDoctor();
            webDriverWait.until(ExpectedConditions.alertIsPresent());
            alert = driver.switchTo().alert();
            alertMessage = alert.getText();
            assertEquals("Cadastrado efetuado com sucesso", alertMessage);
            alert.accept();
            cadastroMedicos.fillAllFields(crm, nome, dataNascimento, sexo, especialidade, universidade, email, telefone);
            cadastroMedicos.clickRegisterDoctor();
            webDriverWait.until(ExpectedConditions.alertIsPresent());
            alert = driver.switchTo().alert();
            alertMessage = alert.getText();
            assertEquals("CRM já cadastrado.", alertMessage);
            alert.accept();
        }
    }

    @Nested
    @DisplayName("Go to another page")
    class anotherPage
    {
        @Test
        @DisplayName("Should click and go to the other doctor listing page")
        void shouldClickAndGoToTheOtherDoctorListingPage()
        {
            driver.get(url);
            paginaInicial.goToTheMedicPageWaited();
            cadastroMedicos.clickDoctorsList();
        }
    }

}
