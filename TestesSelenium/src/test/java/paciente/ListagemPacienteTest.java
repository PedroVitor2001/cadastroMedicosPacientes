package paciente;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Faker.PatientFakerUtil;
import Pages.CadastroPacientesPage;
import Pages.ListaPacientes;
import Pages.PaginaInicial;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ListagemPacienteTest {
    private WebDriver driver;
    private WebDriverWait webDriverWait;
    private String url = "https://cadastro-medicos-pacientes-a4n9.vercel.app/";
    private ListaPacientes listaPacientePage;
    private PaginaInicial paginaInicial;
    private CadastroPacientesPage cadastroPacientesPage;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
    
        driver = new ChromeDriver(options);
        driver.get(url);
        paginaInicial = new PaginaInicial(driver);
        paginaInicial.clickPatientPage();

        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        listaPacientePage = new ListaPacientes(driver); 
        cadastroPacientesPage = new CadastroPacientesPage(driver);
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
            cadastroPacientesPage.clickListarPacientesBtn();
            listaPacientePage.clickMedicoPageBtn();

            assertEquals(expectedPage, driver.getCurrentUrl());
        }

        @Test
        @DisplayName("Should click on 'Página de Pacientes' button and navigate to the pacientes page")
        void ShouldClickOnPaginaDePacientesButtonAndNavigateToThePacientesPage() {
            String expectedPage = "https://cadastro-medicos-pacientes-a4n9.vercel.app/pages/pacientes.html";
            cadastroPacientesPage.clickListarPacientesBtn();
            listaPacientePage.clickPacientePageBtn();

            assertEquals(expectedPage, driver.getCurrentUrl());
        }

        @Test
        @DisplayName("Should click on 'Sair' button and navigate to the index page")
        void ShouldClickOnSairButtonAndNavigateToTheIndexPage() {
            String expectedPage = "https://cadastro-medicos-pacientes-a4n9.vercel.app/index.html";
            cadastroPacientesPage.clickListarPacientesBtn();
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
            cadastroPacientesPage.clickListarPacientesBtn();
            listaPacientePage.clickListBtn();

            String alertText = webDriverWait.until(ExpectedConditions.alertIsPresent()).getText();
            assertEquals("Digite o CPF", alertText);
        }

        @Test
        @DisplayName("Should display alert 'digite o cpf' after clicking alterarbtn")
        void shouldDisplayAlertOnEmptyCpfAfterClickingAlterarButton() {
            cadastroPacientesPage.clickListarPacientesBtn();
            listaPacientePage.clickEditBtn();

            String alertText = webDriverWait.until(ExpectedConditions.alertIsPresent()).getText();
            assertEquals("Digite o CPF", alertText);
        }

        @Test
        @DisplayName("Should display alert 'digite o cpf' after clicking excluirbtn")
        void shouldDisplayAlertOnEmptyCpfAfterClickingExcluirButton() {
            cadastroPacientesPage.clickListarPacientesBtn();
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
            cadastroPacientesPage.clickListarPacientesBtn();
            listaPacientePage.setCPF(PatientFakerUtil.getRandomCPF());
            listaPacientePage.clickListBtn();

            String alertText = webDriverWait.until(ExpectedConditions.alertIsPresent()).getText();
            assertEquals("Cadastro não encontrado.", alertText);
        }

        @Test
        @DisplayName("Should display alert 'Cadastro não encontrado' after clicking edit button with wrong CPF")
        public void shouldDisplayAlertOnWrongCpfAfterClickingEditButtonWithWrongCPF() {
            cadastroPacientesPage.clickListarPacientesBtn();
            listaPacientePage.setCPF(PatientFakerUtil.getRandomCPF());
            listaPacientePage.clickEditBtn();

            String alertText = webDriverWait.until(ExpectedConditions.alertIsPresent()).getText();
            assertEquals("Cadastro não encontrado.", alertText);
        }

        @Test
        @DisplayName("Should display alert 'Cadastro não encontrado' after clicking delete button with wrong CPF")
        public void shouldDisplayAlertOnWrongCpfAfterClickingDeleteButtonWithWrongCPF() {
            cadastroPacientesPage.clickListarPacientesBtn();
            listaPacientePage.setCPF(PatientFakerUtil.getRandomCPF());
            listaPacientePage.clickDeleteBtn();

            String alertText = webDriverWait.until(ExpectedConditions.alertIsPresent()).getText();
            assertEquals("Cadastro não encontrado.", alertText);
        }
}

    @Nested
    @DisplayName("List patients")
    class ListPatients {
        @Test
        @DisplayName("Should list one patients")
        public void shouldListAPatient(){
            cadastroPacientesPage.criarPacienteValido("123.456.789-01");

            cadastroPacientesPage.clickListarPacientesBtn();
            listaPacientePage.setCPF("123.456.789-01");
            listaPacientePage.clickListBtn();
            List<WebElement> patientRows = listaPacientePage.getPatientRows();

            assertEquals(1, patientRows.size(), "The number of registered patients should be 1.");
        }

        @Test
        @DisplayName("Should list all patients")
        public void shouldListAllPacients(){
            cadastroPacientesPage.criarPacienteValido();
            cadastroPacientesPage.criarPacienteValido();
            cadastroPacientesPage.criarPacienteValido();

            cadastroPacientesPage.clickListarPacientesBtn();
            listaPacientePage.clickListAllBtn();
            List<WebElement> patientRows = listaPacientePage.getPatientRows();

            assertEquals(3, patientRows.size(), "The number of registered patients should be 3.");
        }
    }

    @Nested
    @DisplayName("Edit patient")
    class EditPatient {
        @Test
        @DisplayName("Should display alert 'Cadastro atualizado com sucesso' after saving changes in edit patient")
        void shouldDisplaySucessAlertAfterSavingChangesInEditPatient() {
            cadastroPacientesPage.criarPacienteValido("123.456.789-01");

            cadastroPacientesPage.clickListarPacientesBtn();
            listaPacientePage.setCPF("123.456.789-01");
            listaPacientePage.clickEditBtn();
            cadastroPacientesPage.clickEditarBtn();

            String alertText = webDriverWait.until(ExpectedConditions.alertIsPresent()).getText();
            assertEquals("Cadastro atualizado com sucesso", alertText);
        }

        @Test
        @DisplayName("should Check if changes were made once patient is sucessfully edited")
        void shouldCheckIfChangesWereMadeOncePatientIsSucessfullyEdited() {
            cadastroPacientesPage.criarPacienteValido("123.456.789-01");

            cadastroPacientesPage.clickListarPacientesBtn();
            listaPacientePage.setCPF("123.456.789-01");
            listaPacientePage.clickEditBtn();
            cadastroPacientesPage.setNome("nome alterado"); // should work with webdriverwait in this function
            cadastroPacientesPage.clickEditarBtn();
            cadastroPacientesPage.clickListarPacientesBtn();
            listaPacientePage.setCPF("123.456.789-01");
            listaPacientePage.clickListBtn();
            List<WebElement> patientRows = listaPacientePage.getPatientRows();

            assertEquals(1, patientRows.size(), "The number of registered patients should be 1.");
        }
    }

    @Nested
    @DisplayName("delete patient")
    class DeletePatient {
        @Test
        @DisplayName("should Check if patient got deleted")
        void shouldCheckIfPatientGotDeleted() {
            cadastroPacientesPage.criarPacienteValido("123.456.789-01");

            cadastroPacientesPage.clickListarPacientesBtn();
            listaPacientePage.setCPF("123.456.789-01");
            listaPacientePage.clickDeleteBtn();
            //objeto excluido com sucesso
            webDriverWait.until(ExpectedConditions.alertIsPresent()).accept();
            //nenhum objeto encontrado
            webDriverWait.until(ExpectedConditions.alertIsPresent()).accept();


            listaPacientePage.setCPF("123.456.789-01");
            listaPacientePage.clickListBtn();
            String alertMessage = webDriverWait.until(ExpectedConditions.alertIsPresent()).getText();
            
            assertEquals("Cadastro não encontrado.", alertMessage);
        }
        
    }
}
