import Pages.CadastroMedicos;
import Pages.ListaMedicos;
import Pages.PaginaInicial;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ListagemMedicosTest {
    private WebDriver driver;
    private PaginaInicial paginaInicial;
    private CadastroMedicos cadastroMedicos;
    private WebDriverWait webDriverWait;
    private ListaMedicos listaMedicos;
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
    }

}
