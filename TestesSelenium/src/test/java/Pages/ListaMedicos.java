package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ListaMedicos {
    private WebDriver driver;
    private By listaAllButton = By.xpath("//*[@id=\"cadastro-2\"]/div/button[1]");
    private By doctorRows = By.xpath("//*[@id=\"medicosTable\"]/tbody/tr");
    private By listDoctor = By.xpath("//*[@id=\"cadastro-2\"]/div/button[2]");
    private By editDoctor = By.xpath("//*[@id=\"cadastro-2\"]/div/button[3]");
    private By deleteDoctor = By.xpath("//*[@id=\"cadastro-2\"]/div/button[4]");
    private By search = By.id("icrm");
    private WebDriverWait wait;

    public ListaMedicos(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void listAll() {
        wait.until(ExpectedConditions.elementToBeClickable(listaAllButton)).click();
    }

    public List<WebElement> getDoctorRows() {
        return driver.findElements(doctorRows);
    }

    public void clickListDoctor() {
        wait.until(ExpectedConditions.elementToBeClickable(listDoctor)).click();
    }

    public void searchCRM(String crm) {
        driver.findElement(search).sendKeys(crm);
    }


    public void clickEditDoctor() {
        wait.until(ExpectedConditions.elementToBeClickable(editDoctor)).click();
    }

    public void clickDeleteDoctor() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteDoctor)).click();
    }
}
