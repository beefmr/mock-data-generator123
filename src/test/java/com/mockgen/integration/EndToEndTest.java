package com.mockgen.integration;

import com.mockgen.Main;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

class EndToEndTest {

    @TempDir
    Path tempDir;

    @Test
    void testEndToEndWithProperties() throws Exception {
        // Arrange
        Path templateFile = tempDir.resolve("template.txt");
        Files.writeString(templateFile, "Hello, ${user.name}! Order: ${order.id}");

        Path dataFile = tempDir.resolve("data.properties");
        Files.writeString(dataFile, "user.name=John\norder.id=123");

        Path configFile = tempDir.resolve("config.properties");
        Files.writeString(configFile,
                "template.file.path=" + templateFile.toString() + "\n" +
                        "data.file.path=" + dataFile.toString() + "\n" +
                        "data.format=PROPERTIES"
        );

        // Сохраняем оригинальный System.out
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        try {
            // Здесь нужно будет модифицировать Main для приема пути к конфигу
            // Act
            // Main.main(new String[]{configFile.toString()});

            // Assert
            // String output = outputStream.toString();
            // assertTrue(output.contains("Hello, John! Order: 123"));
        } finally {
            System.setOut(originalOut);
        }
    }
}