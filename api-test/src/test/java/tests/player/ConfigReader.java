package tests.player;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    public static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream("testdata.properties")) {
            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Не удалось загрузить properties");
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
