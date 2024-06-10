package Pages;

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
    private WebElement CPFField;

    public ListaPacientes(WebDriver driver){
        this.pacientePageBtn = driver.findElement(By.className("pagina-pacientes"));
        this.medicoPageBtn = driver.findElement(By.className("pagina-medicos"));
        this.sairBtn = driver.findElement(By.className("sair"));

        this.CPFField = driver.findElement(By.id("icpf"));
        this.listAllBtn = driver.findElement(By.xpath("//button[@onClick='mostrar()']"));
        this.listBtn = driver.findElement(By.xpath("//button[@onClick='ListarUm()']"));
        this.editBtn = driver.findElement(By.xpath("//button[@onClick='Alterar()']"));
        this.deleteBtn = driver.findElement(By.xpath("//button[@onClick='Excluir()']"));
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
        CPFField.sendKeys(cpf);
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
}
