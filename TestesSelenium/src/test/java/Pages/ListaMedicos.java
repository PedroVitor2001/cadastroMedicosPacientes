package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ListaMedicos {
    private WebDriver driver;
    private By listaAllButton = By.xpath("//*[@id=\"cadastro-2\"]/div/button[1]");
    private By listDoctors = By.id("medicosTable");

    public ListaMedicos(WebDriver driver) {
        this.driver = driver;
    }

    public void listAll() {
        driver.findElement(listaAllButton).click();
    }

    public List<WebElement> getDoctorRows() {
        return driver.findElements(listDoctors);
    }
}
