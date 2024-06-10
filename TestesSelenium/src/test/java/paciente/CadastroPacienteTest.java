package paciente;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import Pages.CadastroPacientesPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CadastroPacienteTest {
    private WebDriver driver;
    private String BASE_URL = "https://cadastro-medicos-pacientes-a4n9.vercel.app/pages/pacientes.html";
    private CadastroPacientesPage page;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.get(BASE_URL);
        page = new CadastroPacientesPage(driver);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
