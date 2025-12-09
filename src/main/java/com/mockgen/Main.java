package com.mockgen;

import com.mockgen.config.TemplateConfig;
import com.mockgen.parser.DataParser;
import com.mockgen.parser.JsonParser;
import com.mockgen.parser.PropertiesParser;
import com.mockgen.processor.TemplateProcessor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            TemplateConfig config = new TemplateConfig();

            // Read template
            String template = Files.readString(Paths.get(config.getTemplateFilePath()));

            // Select data parser
            DataParser parser = getDataParser(config.getDataFormat());
            Map<String, String> data = parser.parseData(new File(config.getDataFilePath()));

            // Process template
            TemplateProcessor processor = new TemplateProcessor();
            String result = processor.processTemplate(template, data);

            // Output result
            System.out.println(result);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static DataParser getDataParser(TemplateConfig.DataFormat format) {
        switch (format) {
            case PROPERTIES:
                return new PropertiesParser();
            case JSON:
                return new JsonParser();
            default:
                throw new IllegalArgumentException("Unsupported data format: " + format);
        }
    }
}