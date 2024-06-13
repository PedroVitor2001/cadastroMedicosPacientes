package home;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import Pages.PaginaInicial;
import io.github.bonigarcia.wdm.WebDriverManager;

public class HomeTests {
    private WebDriver driver;
    private final String BASE_URL =
        "https://cadastro-medicos-pacientes-a4n9.vercel.app/index.html";
    private PaginaInicial page;

    private final String HOME_PAGE =
        "https://cadastro-medicos-pacientes-a4n9.vercel.app/index.html";

    private final String MEDIC_PAGE =
        "https://cadastro-medicos-pacientes-a4n9.vercel.app/pages/medicos.html";

    private final String PATIENT_PAGE =
        "https://cadastro-medicos-pacientes-a4n9.vercel.app/pages/pacientes.html";

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.get(BASE_URL);
        page = new PaginaInicial(driver);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("Should go to the home page")
    public void ShouldGoToTheHomePage() {
        page.goToTheHomePage();

        assertThat(driver.getCurrentUrl()).isEqualTo(HOME_PAGE);
    }

    @Test
    @DisplayName("Should go to the medic page")
    public void ShouldGoToTheMedicPage() {
        page.goToTheMedicPage();

        assertThat(driver.getCurrentUrl()).isEqualTo(MEDIC_PAGE);
    }
}
