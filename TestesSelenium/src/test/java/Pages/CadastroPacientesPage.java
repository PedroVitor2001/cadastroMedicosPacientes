package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Faker.PatientFakerUtil;

public class CadastroPacientesPage {
    private WebDriver driver;
    private WebDriverWait wait;

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
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        this.medicPagebtn = driver.findElement(By.xpath("/html/body/header/div/nav/a[1]"));
        this.medicPagebtn = driver.findElement(By.xpath("/html/body/header/div/nav/a[2]"));
        this.medicPagebtn = driver.findElement(By.xpath("/html/body/header/div/nav/a[3]"));

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

    public void cadastrar() {
        cadastrarbtn.click();
    }

    public void setCPF(String CPF) {
        CPFField.sendKeys(CPF);
    }

    public void setNome(String nome) {
        nomeField.sendKeys(nome);
    }

    public void setSexo(String sexo) {
        sexoField.sendKeys(sexo);
    }

    public void setPlano(String plano) {
        planoField.sendKeys(plano);
    }

    public void setData(String data) {
        dataNascimentoField.sendKeys(data);
    }

    public void setEmail(String email) {
        emailField.sendKeys(email);
    }

    public void setTelefone(String telefone) {
        telefoneField.sendKeys(telefone);
    }

    public void clickListarPacientesbtn() {
        wait.until(ExpectedConditions.elementToBeClickable(listarPacientesbtn)).click();
    }

    public void criarPacienteValido() {
        setCPF(PatientFakerUtil.getRandomCPF());
        setNome(PatientFakerUtil.getNome());
        setSexo(PatientFakerUtil.getSexo());
        setPlano(PatientFakerUtil.getPlano());
        setData(PatientFakerUtil.getDataNascimento());
        setEmail(PatientFakerUtil.getEmail());
        setTelefone(PatientFakerUtil.getTelefone());
        cadastrar();
        wait.until(ExpectedConditions.alertIsPresent()).accept();
    }
}