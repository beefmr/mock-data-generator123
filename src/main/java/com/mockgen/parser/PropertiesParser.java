package com.mockgen.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesParser implements DataParser {
    @Override
    public Map<String, String> parseData(File dataFile) throws IOException {
        Properties props = new Properties();
        Map<String, String> data = new HashMap<>();

        try (FileInputStream input = new FileInputStream(dataFile)) {
            props.load(input);

            for (String key : props.stringPropertyNames()) {
                data.put(key, props.getProperty(key));
            }
        }

        return data;
    }
}