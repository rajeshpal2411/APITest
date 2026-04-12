package utils;

import java.util.Properties;
import java.io.InputStream;

public class ConfigReader {

    private static Properties properties = new Properties();

    static {
        try (InputStream input = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {

            if (input == null) {
                throw new RuntimeException("Sorry, unable to find config.properties");
            }

            properties.load(input);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load config.properties");
        }
    }

    // ✅ Generic method
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    // ✅ Optional: with default value
    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}