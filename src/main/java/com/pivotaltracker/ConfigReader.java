package com.pivotaltracker;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private Properties properties;

    public ConfigReader() {
        try {
            InputStream input = new FileInputStream("gradle.properties");
            properties = new Properties();
            properties.load(input);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String getToken() {
        return properties.getProperty("apiToken");
    }

    public String getApiUrl() {
        return properties.getProperty("apiURL");
    }
}
