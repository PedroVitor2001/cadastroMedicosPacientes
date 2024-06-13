package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PaginaInicial {
    private WebElement buttonMedic;
    private WebElement buttonPatient;
    private WebElement buttonHome;

    public PaginaInicial(WebDriver driver) {
        this.buttonMedic = driver.findElement(By.className("pagina-medicos"));
        this.buttonPatient = driver.findElement(By.className("pagina-pacientes"));
        this.buttonHome = driver.findElement(By.className("sair"));
    }

    public void clickButtonMedic() {
        buttonMedic.click();
    }

    public void clickPatientPage() {
        buttonPatient.click();
    }

    public void clickHomePage() {
        buttonHome.click();
    }
}
