package paciente;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
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

import Faker.PatientFakerUtil;
import Pages.CadastroPacientes;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CadastroPacienteTest {
    private WebDriver driver;
    private String BASE_URL = "https://cadastro-medicos-pacientes-a4n9.vercel.app/pages/pacientes.html";
    private CadastroPacientes page;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.get(BASE_URL);
        page = new CadastroPacientes(driver);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Should not register a new patient with empty fields")
    public void shouldNotRegisterANewPatientWithEmptyFields() {
        page.cadastrar();

        final Alert alert = new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.alertIsPresent());

        assertThat(alert.getText()).isEqualTo("Todos os campos são obrigatórios.");
    }

    @Test
    @DisplayName("Should not register a new patient with blank fields")
    public void shouldNotRegisterANewPatientWithBlankFields() {
        page.setCPF(" ");
        page.setNome(" ");
        page.setSexo(" ");
        page.setPlano(" ");
        page.setData(" ");
        page.setEmail(" ");
        page.setTelefone(" ");

        page.cadastrar();

        final Alert alert = new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.alertIsPresent());

        assertThat(alert.getText()).isEqualTo("Todos os campos são obrigatórios.");
    }

    @Test
    @DisplayName("Should not register a patient with a invalid CPF")
    public void shouldNotRegisterAPatientWithAInvalidCPF() {
        page.criarPaciente();

        page.setCPF(PatientFakerUtil.getWrongRandomCPF());

        page.cadastrar();

        final Alert alert = new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.alertIsPresent());

        assertThat(alert.getText()).isEqualTo("CPF inválido. Deve ser: XXX.XXX.XXX-XX");
    }
}