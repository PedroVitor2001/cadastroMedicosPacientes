import Pages.CadastroMedicos;
import Pages.PaginaInicial;
import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Random;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CadastroMedicoTest {
    private WebDriver driver;
    final String url = "https://cadastro-medicos-pacientes-a4n9.vercel.app/";
    private PaginaInicial paginaInicial;
    private CadastroMedicos cadastroMedicos;
    private  WebDriverWait webDriverWait;
    private Alert alert;
    private String alertMessage;
    private Faker faker;
    private String crm;
    private String nome;
    private String dataNascimento;
    private String sexo;
    private String universidade;
    private String especialidade;
    private String email;
    private String telefone;
    private String estado;
    private String numero;
    private Random random;

    private static final List<String> estadosBrasileiros = Arrays.asList(
            "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS",
            "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC",
            "SP", "SE", "TO"
    );

    private String getRandomEstado() {
        return estadosBrasileiros.get(random.nextInt(estadosBrasileiros.size()));
    }

    @BeforeEach
    void setUp()
    {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        paginaInicial = new PaginaInicial(driver);
        cadastroMedicos = new CadastroMedicos(driver);
        faker = new Faker();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        random = new Random();
    }
    @AfterEach
    void tearDown()
    {
        driver.quit();
    }
    @Test
    @DisplayName("Should open and close the website")
    void shouldOpenAndCloseTheWebsite() throws InterruptedException {
        driver.get(url);
        Thread.sleep(1000);
        driver.quit();
    }

    @Test
    @DisplayName("Should click on the doctor's button and go to another page")
    void shouldClickOnTheDoctorsButtonAndGoToAnotherPage() throws InterruptedException
    {
        driver.get(url);
        Thread.sleep(1000);
        paginaInicial.clickButtonMedic();
        Thread.sleep(1000);
    }

    @Test
    @DisplayName("Should click on the patient page button and redirect to the other page")
    void shouldClickOnThePatientePageButtonAndRedirectToTheOtherPage()throws InterruptedException
    {
        driver.get(url);
        Thread.sleep(1000);
        paginaInicial.clickPatientPage();
        Thread.sleep(1000);
    }

    @Test
    @DisplayName("Should navigate to the doctor registration page, click on register and an alert should appear with a message")
    void shouldNavigateToTheDoctorRegistrationPageClickOnRegisterAndAnAlertShouldAppearWithAMessage() throws InterruptedException
    {
        driver.get(url);
        Thread.sleep(1000);
        paginaInicial.clickButtonMedic();
        Thread.sleep(1000);

        cadastroMedicos.clickRegisterDoctor();
        Thread.sleep(1000);

        webDriverWait.until(ExpectedConditions.alertIsPresent());

        alert = driver.switchTo().alert();

        alertMessage = alert.getText();

        assertEquals("Todos os campos são obrigatórios.", alertMessage);
        alert.accept();
    }


    @Test
    @DisplayName("Should navigate to the doctor registration page, fill the form, click on register and an alert should appear with a message")
    void shouldNavigateToTheDoctorRegistrationPageFillFormClickOnRegisterAndAnAlertShouldAppearWithAMessage() throws InterruptedException{
        driver.get(url);

        paginaInicial.clickButtonMedic();
        Thread.sleep(1000);

        crm = faker.number().digits(20);
        nome = faker.name().fullName();
        dataNascimento = "01/01/1980";
        sexo = faker.demographic().sex();
        universidade = faker.university().name();
        especialidade = "Cardiologista";
        email = faker.internet().emailAddress();
        telefone = faker.number().digits(11);

        cadastroMedicos.fillAllFields(crm, nome, dataNascimento, sexo, especialidade, universidade, email, telefone);
        Thread.sleep(1000);
        cadastroMedicos.clickRegisterDoctor();
        Thread.sleep(1000);

        webDriverWait.until(ExpectedConditions.alertIsPresent());

        alert = driver.switchTo().alert();

        alertMessage = alert.getText();

        assertEquals("O CRM deve estar no formato 0000000/UF.", alertMessage);
        Thread.sleep(1000);
        alert.accept();
    }

    @Test
    @DisplayName("Should show an error in the message if it is not a correct phone number")
    void shouldShowAnErrorInTheMessageIfItIsNotACorrectPhoneNumber() throws InterruptedException{
        driver.get(url);

        paginaInicial.clickButtonMedic();
        Thread.sleep(1000);

        numero = faker.number().digits(7);
        estado = getRandomEstado();
        crm = numero + "/" + estado;
        nome = faker.name().fullName();
        dataNascimento = "01/01/1980";
        sexo = faker.demographic().sex();
        universidade = faker.university().name();
        especialidade = "Cardiologista";
        email = faker.internet().emailAddress();
        telefone = faker.number().digits(11);

        cadastroMedicos.fillAllFields(crm, nome, dataNascimento, sexo, especialidade, universidade, email, telefone);
        Thread.sleep(1000);
        cadastroMedicos.clickRegisterDoctor();
        Thread.sleep(1000);

        webDriverWait.until(ExpectedConditions.alertIsPresent());

        alert = driver.switchTo().alert();

        alertMessage = alert.getText();

        assertEquals("Telefone inválido. Deve estar no formato XXXXXXXX", alertMessage);
        Thread.sleep(1000);
        alert.accept();
    }

}
