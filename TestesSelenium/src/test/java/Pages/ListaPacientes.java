package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ListaPacientes {
    private WebDriver driver;
    private WebElement medicoPageBtn;
    private WebElement pacientePageBtn;
    private WebElement sairBtn;
    private WebElement listAllBtn;
    private WebElement listBtn;
    private WebElement editBtn;
    private WebElement deleteBtn;
    private WebElement cpfField;
    private WebDriverWait wait;
    private List<WebElement> patientRows;

    public ListaPacientes(WebDriver driver){
        this.pacientePageBtn = driver.findElement(By.className("pagina-pacientes"));
        this.medicoPageBtn = driver.findElement(By.className("pagina-medicos"));
        this.sairBtn = driver.findElement(By.className("sair"));

        this.cpfField = driver.findElement(By.id("icpf"));
        this.listAllBtn = driver.findElement(By.xpath("//button[@onClick='mostrar()']"));
        this.listBtn = driver.findElement(By.xpath("//button[@onClick='ListarUm()']"));
        this.editBtn = driver.findElement(By.xpath("//button[@onClick='Alterar()']"));
        this.deleteBtn = driver.findElement(By.xpath("//button[@onClick='Excluir()']"));

        this.patientRows = driver.findElements(By.xpath("//*[@id=\"pacientesTable\"]/tbody/tr"));
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
        cpfField.sendKeys(cpf);
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
        return patientRows;
    }
}
