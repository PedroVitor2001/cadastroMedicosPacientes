package Faker;
import com.github.javafaker.Faker;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MedicFakerUtil {
    private static final Faker faker = new Faker();
    private static Random random = new Random();
    public static String getRandomCRM() {
        return faker.number().digits(7) + "/" + getRandomEstado();
    }
    public static String getWrongRandomCRM() {
        return faker.number().digits(20);
    }

    public static String getRandomCPF() {
        return String.format("%s.%s.%s-%s", faker.number().digits(3), faker.number().digits(3), 
                faker.number().digits(3), faker.number().digits(2));
    }
    public static String getWrongRandomCPF() {
        return faker.number().digits(20);
    }

    public static String getNome() {
        return faker.name().fullName();
    }

    public static String getDataNascimento() {
        return "01/01/1980";
    }

    public static String getSexo() {
        return faker.demographic().sex();
    }

    public static String getUniversidade() {
        return faker.university().name();
    }

    public static String getEspecialidade() {
        return "Cardiologista";
    }

    public static String getEmail() {
        return faker.internet().emailAddress();
    }

    public static String getWrongEmail() {

        String[] wrongEmailPatterns = {
                getNome().replaceAll(" ", "").toLowerCase() + ".com",
                getNome().replaceAll(" ", "").toLowerCase() + "@.com",
                getNome().replaceAll(" ", "").toLowerCase()+ "@domain.",
                getNome() .replaceAll(" ", "").toLowerCase()+"!@domain.com",
                getNome() .replaceAll(" ", "").toLowerCase()+ "@domain"
        };
        return wrongEmailPatterns[random.nextInt(wrongEmailPatterns.length)];
    }

    public static String getTelefone() {
        int primeiroNumero = random.nextInt(9) + 1;
        int segundoNumero = random.nextInt(9) + 1;
        String numeros = faker.number().digits(6);
        return primeiroNumero + "" + segundoNumero + numeros;
    }

    public static String getWrongTelefone() {
        return faker.number().digits(15);
    }

    private static final List<String> estadosBrasileiros = Arrays.asList(
            "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS",
            "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC",
            "SP", "SE", "TO"
    );

    private static String getRandomEstado() {
        return estadosBrasileiros.get(random.nextInt(estadosBrasileiros.size()));
    }
}
