package common;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Created by noodles on 16/11/18 下午2:47.
 */
public class ConfigReader {

    public static final ConfigReader instance = new ConfigReader();

    private Properties properties;
    private ConfigReader(){}

    public void loadConfig(String filePath){
        try {
            this.properties = new Properties();
            this.properties.load(new FileInputStream(filePath));
        }catch (Exception e){
            ApmLoggerUtil.log("load config file fail...." + e.getMessage());
        }
    }

    public int getIntValue(String key,int defaultValue){
        final String value = getValue(key);
        if(value == null || value.equals("")){
            return defaultValue;
        }
        return Integer.valueOf(value);
    }

    public String getStringValue(String key,String defaultValue){
        final String value = getValue(key);
        if(value == null || value.equals("")){
            return defaultValue;
        }
        return value;
    }

    private String getValue(String key){
        if(properties == null){
            throw new RuntimeException("configReader not init yet.");
        }
        return this.properties.getProperty(key);
    }


}
