import Pages.PaginaInicial;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CadastroMedicoTest {
    private WebDriver driver;
    final String url = "https://cadastro-medicos-pacientes-a4n9.vercel.app/";
    private PaginaInicial paginaInicial;
    @BeforeEach
    void setUp()
    {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        paginaInicial = new PaginaInicial(driver);
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

}
