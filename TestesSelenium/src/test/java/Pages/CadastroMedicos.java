package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CadastroMedicos {
    private WebDriver driver;
    private By buttonRegisterDoctor = By.id("cadastrar");
    public CadastroMedicos(WebDriver driver) {
        this.driver = driver;
    }

    public void clickRegisterDoctor() {
        driver.findElement(buttonRegisterDoctor).click();
    }

}
