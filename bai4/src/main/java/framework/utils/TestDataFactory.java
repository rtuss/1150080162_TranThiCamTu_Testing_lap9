package framework.utils;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.github.javafaker.Faker;

public class TestDataFactory {

    private static final Faker faker = new Faker(Locale.forLanguageTag("vi"));

    public static String randomFirstName() {
        String value = faker.name().firstName();
        if (value == null || value.trim().isEmpty()) {
            return "Test";
        }
        return value.trim();
    }

    public static String randomLastName() {
        String value = faker.name().lastName();
        if (value == null || value.trim().isEmpty()) {
            return "User";
        }
        return value.trim();
    }

    public static String randomPostalCode() {
        String value = faker.number().digits(5);

        if (value == null || value.trim().isEmpty()) {
            return "12345";
        }

        value = value.trim();

        if (value.matches("0+")) {
            return "12345";
        }

        return value;
    }

    public static String randomEmail() {
        String value = faker.internet().emailAddress();
        if (value == null || value.trim().isEmpty()) {
            return "testuser@example.com";
        }
        return value.trim();
    }

    public static Map<String, String> randomCheckoutData() {
        Map<String, String> data = new HashMap<>();

        data.put("firstName", randomFirstName());
        data.put("lastName", randomLastName());
        data.put("postalCode", randomPostalCode());

        return data;
    }
}