package me.grocery.grocerylist.ai;

import java.io.InputStream;
import java.util.Properties;

/**
 * Handles reading of OpenAI API key.
 */
public class ApiKeyReader {
    /**
     * @return the api key
     */
    public static String getApiKey() {
        Properties properties = new Properties();
        try (InputStream input = ApiKeyReader.class.getClassLoader().getResourceAsStream("config" +
                ".properties")) {
            properties.load(input);
            return properties.getProperty("api.key");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
