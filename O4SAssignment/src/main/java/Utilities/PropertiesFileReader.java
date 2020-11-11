package Utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesFileReader {

    public static Properties getProperty(String file) throws Exception {

        FileInputStream inputStream = new FileInputStream(file);
        Properties properties =new Properties();
        properties.load(inputStream);

        return properties;

    }
}

