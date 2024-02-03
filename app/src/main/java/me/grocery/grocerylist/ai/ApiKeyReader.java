package me.grocery.grocerylist.ai;

import android.content.Context;
import android.util.Log;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Properties;

import me.grocery.grocerylist.R;

/**
 * Handles reading of OpenAI API key.
 */
public class ApiKeyReader {
    /**
     * @return the api key
     */
    public static String getApiKey(Context context) {
        Properties properties = new Properties();
        try  {
            InputStream input = context.getResources().openRawResource(R.raw.config);
            properties.load(input);
            return properties.getProperty("api.key");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
