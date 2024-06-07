package Faker;
import com.github.javafaker.Faker;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MedicFakerUtil {
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

    public static String getWrongEmail() {

        String[] wrongEmailPatterns = {
                getNome().replaceAll(" ", "").toLowerCase() + ".com",
                getNome().replaceAll(" ", "").toLowerCase() + "@.com",
                getNome().replaceAll(" ", "").toLowerCase()+ "@domain.",
                getNome() .replaceAll(" ", "").toLowerCase()+"!@domain.com",
                getNome() .replaceAll(" ", "").toLowerCase()+ "@domain"
        };
        return wrongEmailPatterns[new Random().nextInt(wrongEmailPatterns.length)];
    }

    public static String getTelefone() {

        String numeros = faker.number().digits(7);
        return "1" + numeros;

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
        return estadosBrasileiros.get(new Random().nextInt(estadosBrasileiros.size()));
    }
}
