package Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ListaPacientes {
    private WebDriver driver;
    private WebDriverWait wait;

    private By pacientePageBtn = By.xpath("//nav[@class='navegacao']/a[@class='pagina-pacientes' and @href='pacientes.html']");
    private By medicoPageBtn  = By.xpath("//nav[@class='navegacao']/a[@class='pagina-medicos' and @href='medicos.html']");
    private By sairBtn = By.xpath("//nav[@class='navegacao']/a[@class='sair' and @href='../index.html']");

    private By cpfField = By.xpath("//input[@id='icpf' and @type='text']");
    private By listAllBtn = By.xpath("//button[@class='botao' and @onclick='mostrar()']");
    private By listBtn = By.xpath("//button[@class='botao' and @onclick='ListarUm()']");
    private By editBtn = By.xpath("//button[@class='botao' and @onclick='Alterar()']");
    private By deleteBtn = By.xpath("//button[@class='botao' and @onclick='Excluir()']");

    private By patientRows = By.xpath("//*[@id=\"pacientesTable\"]/tbody/tr");

    public ListaPacientes(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }    

    public void clickPacientePageBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(pacientePageBtn)).click();
    }
    
    
    public void clickMedicoPageBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(medicoPageBtn)).click();
    }

    public void clickSairBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(sairBtn)).click();
    }

    public void setCPF(String cpf) {
        driver.findElement(cpfField).sendKeys(cpf);
    }

    public void clickListAllBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(listAllBtn)).click();
    }

    public void clickListBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(listBtn)).click();
    }

    public void clickEditBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(editBtn)).click();
    }

    public void clickDeleteBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteBtn)).click();
    }

    public List<WebElement> getPatientRows() {
        return driver.findElements(patientRows);
    }
}

