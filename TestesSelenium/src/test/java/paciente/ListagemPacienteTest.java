package paciente;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
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
        
    }

    
}
