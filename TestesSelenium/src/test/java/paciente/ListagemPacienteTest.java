package paciente;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import Pages.ListaPacientes;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ListagemPacienteTest {
    private WebDriver driver;
    private String url = "https://cadastro-medicos-pacientes-a4n9.vercel.app/pages/dadosPaciente.html";
    private ListaPacientes page;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.get(url);
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
        
    }
    
    @Nested
    @DisplayName("CPF não encontrado")
    class CPFNaoEncontrado{
        
    }


}
