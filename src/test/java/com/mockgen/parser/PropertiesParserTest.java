package com.mockgen.parser;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class PropertiesParserTest {

    @TempDir
    Path tempDir;

    @Test
    void testParseValidPropertiesFile() throws Exception {
        // Arrange
        File propertiesFile = tempDir.resolve("test.properties").toFile();
        writePropertiesFile(propertiesFile, "user.name=John", "order.id=123");

        PropertiesParser parser = new PropertiesParser();

        // Act
        Map<String, String> result = parser.parseData(propertiesFile);

        // Assert
        assertEquals(2, result.size());
        assertEquals("John", result.get("user.name"));
        assertEquals("123", result.get("order.id"));
    }

    @Test
    void testParsePropertiesWithSpecialCharacters() throws Exception {
        // Arrange
        File propertiesFile = tempDir.resolve("special.properties").toFile();
        writePropertiesFile(propertiesFile,
                "user.name=John Doe",
                "email=john.doe@example.com",
                "price=100.50"
        );

        PropertiesParser parser = new PropertiesParser();

        // Act
        Map<String, String> result = parser.parseData(propertiesFile);

        // Assert
        assertEquals("John Doe", result.get("user.name"));
        assertEquals("john.doe@example.com", result.get("email"));
        assertEquals("100.50", result.get("price"));
    }

    @Test
    void testParseEmptyPropertiesFile() throws Exception {
        // Arrange
        File emptyFile = tempDir.resolve("empty.properties").toFile();
        emptyFile.createNewFile();

        PropertiesParser parser = new PropertiesParser();

        // Act
        Map<String, String> result = parser.parseData(emptyFile);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void testParseNonExistentFile() {
        // Arrange
        File nonExistentFile = new File("nonexistent.properties");
        PropertiesParser parser = new PropertiesParser();

        // Act & Assert
        assertThrows(IOException.class, () -> parser.parseData(nonExistentFile));
    }

    private void writePropertiesFile(File file, String... lines) throws Exception {
        try (FileWriter writer = new FileWriter(file)) {
            for (String line : lines) {
                writer.write(line + "\n");
            }
        }
    }
}