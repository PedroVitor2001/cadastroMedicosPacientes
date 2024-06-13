package paciente;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
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
    private final String BASE_URL =
        "https://cadastro-medicos-pacientes-a4n9.vercel.app/pages/pacientes.html";
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

    @Nested
    class CadastroTests {
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

        @Test
        @DisplayName("Should not register a patient with a invalid email")
        public void shouldNotRegisterAPatientWithAInvalidEmail() {
            page.criarPaciente();

            page.setEmail(PatientFakerUtil.getWrongEmail());

            page.cadastrar();

            final Alert alert = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.alertIsPresent());

            assertThat(alert.getText()).isEqualTo("Email inválido.");
        }

        @Test
        @DisplayName("Should not register a patient with a invalid telefone")
        public void shouldNotRegisterAPatientWithAInvalidTelefone() {
            page.criarPaciente();

            page.setTelefone(PatientFakerUtil.getWrongTelefone());

            page.cadastrar();

            final Alert alert = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.alertIsPresent());

            assertThat(alert.getText()).isEqualTo("Telefone inválido. Deve estar no formato XXXXXXXX");
        }

        @Test
        @DisplayName("Should register a new valid patient")
        public void ShouldRegisterANewValidPatient() {
            page.criarPaciente();

            page.cadastrar();

            final Alert alert = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.alertIsPresent());

            assertThat(alert.getText()).isEqualTo("Cadastro efetuado com sucesso");
        }

        @Test
        @DisplayName("Should not register two or more patients with the same CPF")
        public void ShouldNotRegisterTwoOrMorePatientsWithTheSameCPF() {
            String CPF = PatientFakerUtil.getRandomCPF();

            page.criarPaciente(CPF);

            page.cadastrar();

            Alert alert = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.alertIsPresent());

            alert.accept();

            page.criarPaciente(CPF);

            page.cadastrar();

            alert = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.alertIsPresent());

            assertThat(alert.getText())
                .isEqualTo("O cadastro deste CPF não foi possível, pois já está cadastrado.");
        }
    }

    @Nested
    class RouteTests {
        private final String HOME_PAGE =
            "https://cadastro-medicos-pacientes-a4n9.vercel.app/index.html";

        private final String MEDIC_PAGE =
            "https://cadastro-medicos-pacientes-a4n9.vercel.app/pages/medicos.html";

        private final String PATIENT_PAGE =
            "https://cadastro-medicos-pacientes-a4n9.vercel.app/pages/pacientes.html";

        private final String PATIENT_LIST_PAGE =
            "https://cadastro-medicos-pacientes-a4n9.vercel.app/pages/dadosPaciente.html";

        @Test
        @DisplayName("Should go to the home page")
        public void ShouldGoToTheHomePage() {
            page.goToHomePage();

            assertThat(driver.getCurrentUrl()).isEqualTo(HOME_PAGE);
        }
        
        @Test
        @DisplayName("Should go to the medic page")
        public void ShouldGoToTheMedicPage() {
            page.goToMedicPage();

            assertThat(driver.getCurrentUrl()).isEqualTo(MEDIC_PAGE);
        }
        
        @Test
        @DisplayName("Should go to the patient page")
        public void ShouldGoToThePatientPage() {
            page.goToPatientPage();

            assertThat(driver.getCurrentUrl()).isEqualTo(PATIENT_PAGE);
        }
        
        @Test
        @DisplayName("Should go to the patient list page")
        public void ShouldGoToThePatientListPage() {
            page.goToPatientsPage();

            assertThat(driver.getCurrentUrl()).isEqualTo(PATIENT_LIST_PAGE);
        }
    }
}