package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ListaPacientes {
    private WebElement medicoPageBtn;
    private WebElement pacientePageBtn;
    private WebElement sairBtn;
    private WebElement listAllBtn;
    private WebElement listBtn;
    private WebElement editBtn;
    private WebElement deleteBtn;
    private WebElement cpfField;
    private WebElement saveEditBtn;
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
        this.saveEditBtn = driver.findElement(By.xpath("//button[@onClick='AlterarPaciente()']"));

        this.patientRows = driver.findElements(By.xpath("//*[@id=\"pacientesTable\"]/tbody/tr"));
    }    

    public void clickPacientePageBtn() {
        pacientePageBtn.click();
    }
    
    public void clickMedicoPageBtn() {
        medicoPageBtn.click();
    }

    public void clickSairBtn() {
        sairBtn.click();
    }

    public void setCPF(String cpf) {
        cpfField.sendKeys(cpf);
    }

    public void clickListAllBtn() {
        listAllBtn.click();
    }

    public void clickListBtn() {
        listBtn.click();
    }

    public void clickEditBtn() {
        editBtn.click();
    }

    public void clickDeleteBtn() {
        deleteBtn.click();
    }

    public void clickSaveEditBtn() {
        saveEditBtn.click();
    }

    public List<WebElement> getPatientRows() {
        return patientRows;
    }
}
