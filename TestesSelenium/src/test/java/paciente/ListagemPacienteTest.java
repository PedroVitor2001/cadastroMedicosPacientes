package paciente;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Pages.ListaPacientes;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ListagemPacienteTest {
    private WebDriver driver;
    private WebDriverWait webDriverWait;
    private String url = "https://cadastro-medicos-pacientes-a4n9.vercel.app/pages/dadosPaciente.html";
    private ListaPacientes listaPacientePage;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        
        driver = new ChromeDriver(options);
        driver.get(url);
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        listaPacientePage = new ListaPacientes(driver); 
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Nested
    @DisplayName("Header buttons")
    class Header {
        @Test
        @DisplayName("Should click on 'Página de Médicos' button and navigate to the médicos page")
        void ShouldClickOnPageDeMedicosButtonAndNavigateToTheMedicosPage() {
            String expectedPage = "https://cadastro-medicos-pacientes-a4n9.vercel.app/pages/medicos.html";
            listaPacientePage.clickMedicoPageBtn();

            assertEquals(expectedPage, driver.getCurrentUrl());
        }

        @Test
        @DisplayName("Should click on 'Página de Pacientes' button and navigate to the pacientes page")
        void ShouldClickOnPaginaDePacientesButtonAndNavigateToThePacientesPage() {
            String expectedPage = "https://cadastro-medicos-pacientes-a4n9.vercel.app/pages/pacientes.html";
            listaPacientePage.clickPacientePageBtn();

            assertEquals(expectedPage, driver.getCurrentUrl());
        }

        @Test
        @DisplayName("Should click on 'Sair' button and navigate to the index page")
        void ShouldClickOnSairButtonAndNavigateToTheIndexPage() {
            String expectedPage = "https://cadastro-medicos-pacientes-a4n9.vercel.app/index.html";
            listaPacientePage.clickSairBtn();

            assertEquals(expectedPage, driver.getCurrentUrl());
        }
    }

    @Nested
    @DisplayName("When empty CPF")
    class EmptyCPF {
        @Test
        @DisplayName("Should display alert 'digite o cpf' after clicking listbtn")
        void shouldDisplayAlertOnEmptyCpfAfterClickingListButton() {
            listaPacientePage.clickListBtn();

            String alertText = webDriverWait.until(ExpectedConditions.alertIsPresent()).getText();
            assertEquals("Digite o CPF", alertText);
        }

        @Test
        @DisplayName("Should display alert 'digite o cpf' after clicking alterarbtn")
        void shouldDisplayAlertOnEmptyCpfAfterClickingAlterarButton() {
            listaPacientePage.clickEditBtn();

            String alertText = webDriverWait.until(ExpectedConditions.alertIsPresent()).getText();
            assertEquals("Digite o CPF", alertText);
        }

        @Test
        @DisplayName("Should display alert 'digite o cpf' after clicking excluirbtn")
        void shouldDisplayAlertOnEmptyCpfAfterClickingExcluirButton() {
            listaPacientePage.clickDeleteBtn();

            String alertText = webDriverWait.until(ExpectedConditions.alertIsPresent()).getText();
            assertEquals("Digite o CPF", alertText);
        }
    }
    
    @Nested
    @DisplayName("When wrong CPF")
    class WrongCPF {
        @Test
        @DisplayName("Should display alert 'Cadastro não encontrado' after clicking list button with wrong CPF")
        public void shouldDisplayAlertOnWrongCpfAfterClickingListButtonWithWrongCPF() {
            listaPacientePage.setCPF("123.456.789-09");
            listaPacientePage.clickListBtn();

            String alertText = webDriverWait.until(ExpectedConditions.alertIsPresent()).getText();
            assertEquals("Cadastro não encontrado.", alertText);
        }

        @Test
        @DisplayName("Should display alert 'Cadastro não encontrado' after clicking edit button with wrong CPF")
        public void shouldDisplayAlertOnWrongCpfAfterClickingEditButtonWithWrongCPF() {
            listaPacientePage.setCPF("123.456.789-09");
            listaPacientePage.clickEditBtn();

            String alertText = webDriverWait.until(ExpectedConditions.alertIsPresent()).getText();
            assertEquals("Cadastro não encontrado.", alertText);
        }

        @Test
        @DisplayName("Should display alert 'Cadastro não encontrado' after clicking delete button with wrong CPF")
        public void shouldDisplayAlertOnWrongCpfAfterClickingDeleteButtonWithWrongCPF() {
            listaPacientePage.setCPF("123.456.789-09");
            listaPacientePage.clickDeleteBtn();

            String alertText = webDriverWait.until(ExpectedConditions.alertIsPresent()).getText();
            assertEquals("Cadastro não encontrado.", alertText);
        }
}

    @Nested
    @DisplayName("List pacientes")
    class ListPacientes {
        @Test
        public void lista(){
            listaPacientePage.clickListAllBtn();
        }
    }
}
