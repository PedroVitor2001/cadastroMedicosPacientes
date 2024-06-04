package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaginaInicial {
    private WebDriver driver;
    private By buttonMedic = By.className("pagina-medicos");


    public PaginaInicial(WebDriver driver)
    {
        this.driver = driver;
    }

    public void clickButtonMedic()
    {
        driver.findElement(buttonMedic).click();
    }

}
