package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PaginaInicial {
    private WebDriver driver;
    private WebDriverWait wait;
    private WebElement buttonMedic;
    private WebElement buttonPatient;
    private WebElement buttonHome;

    public PaginaInicial(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        this.buttonMedic = driver.findElement(By.className("pagina-medicos"));
        this.buttonPatient = driver.findElement(By.className("pagina-pacientes"));
        this.buttonHome = driver.findElement(By.className("sair"));
    }

    public void clickButtonMedic() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonMedic)).click();
    }

    public void clickPatientPage() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonPatient)).click();
    }
}
