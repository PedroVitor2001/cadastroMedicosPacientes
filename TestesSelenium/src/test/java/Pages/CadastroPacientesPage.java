package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CadastroPacientesPage {
    private WebDriver driver;

    private WebElement medicPagebtn;
    private WebElement patientPagebtn;
    private WebElement homePagebtn;

    private WebElement CPFField;
    private WebElement nomeField;
    private WebElement sexoField;
    private WebElement planoField;
    private WebElement dataNascimentoField;
    private WebElement emailField;
    private WebElement telefoneField;
    private WebElement cadastrarbtn;
    private WebElement editarbtn;

    private WebElement listarPacientesbtn;

    public CadastroPacientesPage(WebDriver driver) {
        this.driver = driver;

        this.medicPagebtn = driver.findElement(By.xpath("/body/header/div/nav/a[1]"));
        this.medicPagebtn = driver.findElement(By.xpath("/body/header/div/nav/a[2]"));
        this.medicPagebtn = driver.findElement(By.xpath("/body/header/div/nav/a[3]"));

        this.CPFField = driver.findElement(By.id("cpf"));
        this.nomeField = driver.findElement(By.id("nome"));
        this.sexoField = driver.findElement(By.id("sexo"));
        this.planoField = driver.findElement(By.id("plano"));
        this.dataNascimentoField = driver.findElement(By.id("data"));
        this.emailField = driver.findElement(By.id("email"));
        this.telefoneField = driver.findElement(By.id("telefone"));
        this.cadastrarbtn = driver.findElement(By.id("cadastrar"));
        this.editarbtn = driver.findElement(By.id("AlterarPaciente"));

        this.listarPacientesbtn = driver.findElement(By.cssSelector(".cadastro-sec .collum-2 .campos a"));
    }
}