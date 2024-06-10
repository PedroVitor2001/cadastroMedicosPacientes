package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ListaPacientes {
    private WebDriver driver;

    private WebElement medicoPageBtn;
    private WebElement pacientePageBtn;
    private WebElement sairBtn;
    private WebElement listAllBtn;
    private WebElement listBtn;
    private WebElement editBtn;
    private WebElement deleteBtn;
    private WebElement CPFField;

    public ListaPacientes(WebDriver driver){
        this.driver = driver;

        this.pacientePageBtn = driver.findElement(By.className("pagina-medicos"));
        this.medicoPageBtn = driver.findElement(By.className("pagina-pacientes"));
        this.sairBtn = driver.findElement(By.className("sair"));

        this.CPFField = driver.findElement(By.id("icpf"));
        this.listAllBtn = driver.findElement(By.cssSelector("button.botao[type='submit'][text*='Listar todos']"));
        this.listBtn = driver.findElement(By.cssSelector("button.botao[type='submit'][text*='Listar Um']"));
        this.editBtn = driver.findElement(By.cssSelector("button.botao[type='submit'][text*='Alterar']"));
        this.deleteBtn = driver.findElement(By.cssSelector("button.botao[type='submit'][text*='Excluir']"));
    }    
}
