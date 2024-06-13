package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PaginaInicial {
    private WebDriver driver;
    private By buttonMedic = By.className("pagina-medicos");
    private By buttonPatient = By.className("pagina-pacientes");
    private WebDriverWait wait;

    public PaginaInicial(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickButtonMedic() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonMedic)).click();
    }

    public void clickPatientPage() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonPatient)).click();
    }
}
