import Faker.MedicFakerUtil;
import Pages.CadastroMedicos;
import Pages.ListaMedicos;
import Pages.PaginaInicial;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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


    @Test
    @DisplayName("Should click on the button to list all doctors")
    void shouldClickOnTheButtonToListAllDoctors() throws InterruptedException
    {
        driver.get(url);
        paginaInicial.clickButtonMedic();
        Thread.sleep(1000);
        cadastroMedicos.clickDoctorsList();
        Thread.sleep(1000);
        listaMedicos.listAll();
        Thread.sleep(1000);

        webDriverWait.until(ExpectedConditions.alertIsPresent());

        alert = driver.switchTo().alert();

        alertMessage = alert.getText();

        assertEquals("Nenhum objeto encontrado", alertMessage);
        alert.accept();
    }

    @Test
    @DisplayName("Should register some doctors, list them and check if you have been listed")
    void shouldRegisterSomeDoctorsListThemAndCheckIfYouHaveBeenListed()throws InterruptedException
    {
        driver.get(url);
        paginaInicial.clickButtonMedic();
        Thread.sleep(1000);

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
            Thread.sleep(1000);

            webDriverWait.until(ExpectedConditions.alertIsPresent());

            alert = driver.switchTo().alert();
            alertMessage = alert.getText();
            assertEquals("Cadastrado efetuado com sucesso", alertMessage);
            alert.accept();
        }

        Thread.sleep(1000);

        cadastroMedicos.clickDoctorsList();

        Thread.sleep(1000);

        listaMedicos.listAll();

        Thread.sleep(1000);

        listaMedicos.getDoctorRows();
        Thread.sleep(1000);

        List<WebElement> doctorRows = listaMedicos.getDoctorRows();

        assertEquals(5, doctorRows.size(), "The number of registered doctors should be 5.");
    }

    @Test
    @DisplayName("Should return an alert to enter the CRM when clicking the list a button")
    void shouldReturnAnAlertToEnterTheCRMWhenClickingTheListAButton() throws InterruptedException
    {
        driver.get(url);
        paginaInicial.clickButtonMedic();
        Thread.sleep(1000);
        cadastroMedicos.clickDoctorsList();
        Thread.sleep(1000);

        listaMedicos.clickListDoctor();
        Thread.sleep(1000);

        webDriverWait.until(ExpectedConditions.alertIsPresent());
        alert = driver.switchTo().alert();
        alertMessage = alert.getText();
        assertEquals("Digite o CRM", alertMessage);
        alert.accept();
    }

    @Test
    @DisplayName("Should list a doctor")
    void shouldListADoctor()throws InterruptedException
    {
        driver.get(url);
        paginaInicial.clickButtonMedic();
        Thread.sleep(1000);
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
            Thread.sleep(1000);

            webDriverWait.until(ExpectedConditions.alertIsPresent());

            alert = driver.switchTo().alert();
            alertMessage = alert.getText();
            assertEquals("Cadastrado efetuado com sucesso", alertMessage);
            alert.accept();
        }

        listaMedicos.searchCRM(crm);

        Thread.sleep(1000);
        listaMedicos.clickListDoctor();
        Thread.sleep(1000);

        List<WebElement> doctorRows = listaMedicos.getDoctorRows();

        assertEquals(1, doctorRows.size(), "The number of registered doctors should be 1.");
    }




}



