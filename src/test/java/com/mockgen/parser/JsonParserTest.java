package com.mockgen.parser;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class JsonParserTest {

    @TempDir
    Path tempDir;

    @Test
    void testParseNestedJson() throws Exception {
        // Arrange
        File jsonFile = tempDir.resolve("nested.json").toFile();
        writeJsonFile(jsonFile,
                "{",
                "  \"user\": {",
                "    \"personal\": {",
                "      \"name\": \"Alice\",",
                "      \"age\": \"25\"",
                "    },",
                "    \"contact\": {",
                "      \"email\": \"alice@example.com\"",
                "    }",
                "  },",
                "  \"order\": {",
                "    \"id\": \"A-123\"",
                "  }",
                "}"
        );

        JsonParser parser = new JsonParser();

        // Act
        Map<String, String> result = parser.parseData(jsonFile);

        // Assert
        assertEquals(4, result.size());
        assertEquals("Alice", result.get("user.personal.name"));
        assertEquals("25", result.get("user.personal.age"));
        assertEquals("alice@example.com", result.get("user.contact.email"));
        assertEquals("A-123", result.get("order.id"));
    }

    @Test
    void testParseFlatJson() throws Exception {
        // Arrange
        File jsonFile = tempDir.resolve("flat.json").toFile();
        writeJsonFile(jsonFile,
                "{",
                "  \"name\": \"Bob\",",
                "  \"age\": \"30\",",
                "  \"city\": \"New York\"",
                "}"
        );

        JsonParser parser = new JsonParser();

        // Act
        Map<String, String> result = parser.parseData(jsonFile);

        // Assert
        assertEquals(3, result.size());
        assertEquals("Bob", result.get("name"));
        assertEquals("30", result.get("age"));
        assertEquals("New York", result.get("city"));
    }

    @Test
    void testParseJsonWithArrays() throws Exception {
        // Arrange
        File jsonFile = tempDir.resolve("array.json").toFile();
        writeJsonFile(jsonFile,
                "{",
                "  \"users\": [\"John\", \"Jane\"],",
                "  \"settings\": {",
                "    \"active\": \"true\"",
                "  }",
                "}"
        );

        JsonParser parser = new JsonParser();

        // Act
        Map<String, String> result = parser.parseData(jsonFile);

        // Assert
        assertEquals(1, result.size()); // массивы игнорируются
        assertEquals("true", result.get("settings.active"));
    }

    @Test
    void testParseInvalidJson() throws Exception {
        // Arrange
        File invalidJsonFile = tempDir.resolve("invalid.json").toFile();
        writeJsonFile(invalidJsonFile, "{ invalid json }");

        JsonParser parser = new JsonParser();

        // Act & Assert
        assertThrows(IOException.class, () -> parser.parseData(invalidJsonFile));
    }

    private void writeJsonFile(File file, String... lines) throws Exception {
        try (FileWriter writer = new FileWriter(file)) {
            for (String line : lines) {
                writer.write(line + "\n");
            }
        }
    }
}