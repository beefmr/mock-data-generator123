package com.mockgen.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TemplateConfig {
    private String templateFilePath;
    private String dataFilePath;
    private DataFormat dataFormat;

    public enum DataFormat {
        PROPERTIES, JSON
    }

    public TemplateConfig() {
        loadConfig();
    }

    private void loadConfig() {
        Properties props = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("Sorry, unable to find config.properties");
            }
            props.load(input);

            this.templateFilePath = props.getProperty("template.file.path");
            this.dataFilePath = props.getProperty("data.file.path");
            this.dataFormat = DataFormat.valueOf(props.getProperty("data.format", "PROPERTIES").toUpperCase());

        } catch (IOException e) {
            throw new RuntimeException("Error loading configuration", e);
        }
    }

    // Геттеры
    public String getTemplateFilePath() {
        return templateFilePath;
    }

    public String getDataFilePath() {
        return dataFilePath;
    }

    public DataFormat getDataFormat() {
        return dataFormat;
    }
}