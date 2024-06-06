package Faker;
import com.github.javafaker.Faker;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FakerUtil {
    private static final Faker faker = new Faker();

    public static String getRandomCRM() {
        return faker.number().digits(7) + "/" + getRandomEstado();
    }
    public static String getWrongRandomCRM() {
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

    public static String getTelefone() {
        return faker.number().digits(11);
    }

    private static final List<String> estadosBrasileiros = Arrays.asList(
            "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS",
            "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC",
            "SP", "SE", "TO"
    );

    private static String getRandomEstado() {
        return estadosBrasileiros.get(new Random().nextInt(estadosBrasileiros.size()));
    }
}
