package framework.config;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static ConfigReader instance;
    private final Properties properties;
    private final String env;

    private ConfigReader() {
        this.env = System.getProperty("env", "dev");
        this.properties = new Properties();

        String fileName = "config-" + env + ".properties";

        try (InputStream input = new FileInputStream(fileName)) {
            properties.load(input);
            System.out.println("Đang dùng môi trường: " + env);
            System.out.println("base.url = " + getBaseUrl());
            System.out.println("explicit.wait = " + getExplicitWait());
        } catch (Exception e) {
            throw new RuntimeException("Không đọc được file config: " + fileName, e);
        }
    }

    public static ConfigReader getInstance() {
        if (instance == null) {
            instance = new ConfigReader();
        }
        return instance;
    }

    public static void reset() {
        instance = null;
    }

    public String getEnv() {
        return env;
    }

    public String getBaseUrl() {
        return properties.getProperty("base.url");
    }

    public int getExplicitWait() {
        return Integer.parseInt(properties.getProperty("explicit.wait"));
    }

    public String getBrowser() {
        return properties.getProperty("browser", "chrome");
    }
    public int getMaxRetry() {
    return Integer.parseInt(properties.getProperty("max.retry", "0"));
}
}