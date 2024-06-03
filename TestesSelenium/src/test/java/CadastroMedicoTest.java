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

    @BeforeEach
    void setUp()
    {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }
    @AfterEach
    void tearDown()
    {
        driver.quit();
    }
    @Test
    @DisplayName("Should open and close the website")
    void shouldOpenAndCloseTheWebsite() throws InterruptedException {
        driver.get("http://localhost/cadastroMedicosPacientes/");
        Thread.sleep(1000);
        driver.quit();
    }

}
