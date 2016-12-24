package logservelt;

import java.io.*;
import java.util.Properties;

/**
 * Created by noodles on 16/12/7 下午8:18.
 */

public class ConfigReader implements Serializable {

    public static final ConfigReader instance = new ConfigReader();

    private Properties properties = new Properties();

    public void loadConfig(String filePath) {
        try {
            this.loadConfig(new FileInputStream(filePath));
        } catch (FileNotFoundException e) {
        }
    }

    public void loadConfig(InputStream inputStream) {
        try {
            this.properties.load(inputStream);
        } catch (IOException e) {
        }
    }

    public long getLongValue(String key, long defaultValue) {
        final String value = getValue(key);
        if (value == null || value.equals("")) {
            return defaultValue;
        }
        return Long.valueOf(value);
    }

    public int getIntValue(String key, int defaultValue) {
        final String value = getValue(key);
        if (value == null || value.equals("")) {
            return defaultValue;
        }
        return Integer.valueOf(value);
    }

    public boolean getBoolValue(String key, boolean defaultValue) {
        final String value = getValue(key);
        if (value == null || value.equals("")) {
            return defaultValue;
        }
        return value.equalsIgnoreCase("true");
    }

    public String getFileSeparator() {
        final Properties properties = System.getProperties();
        return properties.getProperty("file.separator");
    }

    public String getStringValue(String key) {
        return getValue(key);
    }

    public String getStringValue(String key, String defaultValue) {
        final String value = getValue(key);
        if (value == null || value.equals("")) {
            return defaultValue;
        }
        return value;
    }

    private String getValue(String key) {
        return this.properties.getProperty(key);
    }



}
