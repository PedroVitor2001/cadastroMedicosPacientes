package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaginaInicial {
    private WebDriverWait wait;

    private WebElement buttonMedic;
    private WebElement buttonPatient;
    private WebElement buttonHome;

    public PaginaInicial(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        this.buttonMedic = driver.findElement(By.className("pagina-medicos"));
        this.buttonPatient = driver.findElement(By.className("pagina-pacientes"));
        this.buttonHome = driver.findElement(By.className("sair"));
    }

    public void goToTheMedicPage() {
        buttonMedic.click();
    }

    public void goToThePatientPage() {
        buttonPatient.click();
    }

    public void goToTheHomePage() {
        buttonHome.click();
    }

    public void goToTheMedicPageWaited() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonMedic)).click();
    }

    public void clickPatientPageWaited() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonPatient)).click();
    }
}
