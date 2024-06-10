package paciente;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

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

import Pages.ListaPacientes;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ListagemPacienteTest {
    private WebDriver driver;
    private WebDriverWait webDriverWait;
    private String url = "https://cadastro-medicos-pacientes-a4n9.vercel.app/pages/dadosPaciente.html";
    private ListaPacientes page;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        
        driver = new ChromeDriver(options);
        driver.get(url);
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        page = new ListaPacientes(driver);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Nested
    @DisplayName("Header buttons")
    class Header{
        @Test
        @DisplayName("Should click on 'Página de Médicos' button and navigate to the médicos page")
        void ShouldClickOnPageDeMedicosButtonAndNavigateToTheMedicosPage() {
            String expectedPage = "https://cadastro-medicos-pacientes-a4n9.vercel.app/pages/medicos.html";
            page.clickMedicoPageBtn();

            assertEquals(expectedPage, driver.getCurrentUrl());
        }

        @Test
        @DisplayName("Should click on 'Página de Pacientes' button and navigate to the pacientes page")
        void ShouldClickOnPaginaDePacientesButtonAndNavigateToThePacientesPage() {
            String expectedPage = "https://cadastro-medicos-pacientes-a4n9.vercel.app/pages/pacientes.html";
            page.clickPacientePageBtn();

            assertEquals(expectedPage, driver.getCurrentUrl());
        }

        @Test
        @DisplayName("Should click on 'Sair' button and navigate to the index page")
        void ShouldClickOnSairButtonAndNavigateToTheIndexPage() {
            String expectedPage = "https://cadastro-medicos-pacientes-a4n9.vercel.app/index.html";
            page.clickSairBtn();

            assertEquals(expectedPage, driver.getCurrentUrl());
        }
    }

    @Nested
    @DisplayName("Empty CPF")
    class EmptyCPF{
        @Test
        @DisplayName("Should display alert 'digite o cpf' after clicking listbtn")
        void shouldDisplayAlertOnEmptyCpfAfterClickingListButton() {
            page.clickListBtn();

            String alertText = webDriverWait.until(ExpectedConditions.alertIsPresent()).getText();
            assertEquals("Digite o CPF", alertText);
        }

        @Test
        @DisplayName("Should display alert 'digite o cpf' after clicking alterarbtn")
        void shouldDisplayAlertOnEmptyCpfAfterClickingAlterarButton() {
            page.clickEditBtn();

            String alertText = webDriverWait.until(ExpectedConditions.alertIsPresent()).getText();
            assertEquals("Digite o CPF", alertText);
        }

        @Test
        @DisplayName("Should display alert 'digite o cpf' after clicking excluirbtn")
        void shouldDisplayAlertOnEmptyCpfAfterClickingExcluirButton() {
            page.clickDeleteBtn();

            String alertText = webDriverWait.until(ExpectedConditions.alertIsPresent()).getText();
            assertEquals("Digite o CPF", alertText);
        }
    }
    
    @Nested
    @DisplayName("CPF não encontrado")
    class CPFNaoEncontrado{
        @Test
        @DisplayName("Should display alert 'Cadastro não encontrado' after clicking list button with wrong CPF")
        public void shouldDisplayAlertOnWrongCpfAfterClickingListButtonWithWrongCPF() {
            page.setCPF("123.456.789-09");
            page.clickListBtn();

            String alertText = webDriverWait.until(ExpectedConditions.alertIsPresent()).getText();
            assertEquals("Cadastro não encontrado.", alertText);
        }

        @Test
        @DisplayName("Should display alert 'Cadastro não encontrado' after clicking edit button with wrong CPF")
        public void shouldDisplayAlertOnWrongCpfAfterClickingEditButtonWithWrongCPF() {
            page.setCPF("123.456.789-09");
            page.clickEditBtn();

            String alertText = webDriverWait.until(ExpectedConditions.alertIsPresent()).getText();
            assertEquals("Cadastro não encontrado.", alertText);
        }

        @Test
        @DisplayName("Should display alert 'Cadastro não encontrado' after clicking delete button with wrong CPF")
        public void shouldDisplayAlertOnWrongCpfAfterClickingDeleteButtonWithWrongCPF() {
            page.setCPF("123.456.789-09");
            page.clickDeleteBtn();

            String alertText = webDriverWait.until(ExpectedConditions.alertIsPresent()).getText();
            assertEquals("Cadastro não encontrado.", alertText);
        }
}
}
