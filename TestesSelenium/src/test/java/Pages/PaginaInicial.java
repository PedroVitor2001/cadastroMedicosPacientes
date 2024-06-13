package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaginaInicial {
    private WebDriverWait wait;
    private WebDriver driver;

    private By buttonMedic;
    private By buttonPatient;
    private By buttonHome;

    public PaginaInicial(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.driver = driver;

        this.buttonMedic = By.className("pagina-medicos");
        this.buttonPatient = By.className("pagina-pacientes");
        this.buttonHome = By.className("sair");
    }

    public void goToTheMedicPage() {
        driver.findElement(buttonMedic).click();
    }

    public void goToThePatientPage() {
        driver.findElement(buttonPatient).click();
    }

    public void goToTheHomePage() {
        driver.findElement(buttonHome).click();
    }

    public void goToTheMedicPageWaited() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonMedic)).click();
    }

    public void goToPatientPageWaited() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonPatient)).click();
    }
}
