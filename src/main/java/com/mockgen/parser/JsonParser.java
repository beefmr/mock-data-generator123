package com.mockgen.parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

public class JsonParser implements DataParser {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Map<String, String> parseData(File dataFile) throws IOException {
        JsonNode rootNode = objectMapper.readTree(dataFile);
        Map<String, String> data = new HashMap<>();
        flattenJson("", rootNode, data);
        return data;
    }

    private void flattenJson(String currentPath, JsonNode node, Map<String, String> result) {
        if (node.isObject()) {
            Iterator<String> fieldNames = node.fieldNames();
            String pathPrefix = currentPath.isEmpty() ? "" : currentPath + ".";

            while (fieldNames.hasNext()) {
                String fieldName = fieldNames.next();
                JsonNode fieldValue = node.get(fieldName);
                flattenJson(pathPrefix + fieldName, fieldValue, result);
            }
        } else if (node.isValueNode()) {
            result.put(currentPath, node.asText());
        }
    }
}