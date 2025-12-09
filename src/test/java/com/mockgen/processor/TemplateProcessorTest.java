package com.mockgen.processor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class TemplateProcessorTest {

    private final TemplateProcessor processor = new TemplateProcessor();

    @Test
    void testBasicPlaceholderReplacement() {
        // Arrange
        String template = "Hello, ${name}! Welcome to ${service}.";
        Map<String, String> data = Map.of("name", "John", "service", "Our Platform");

        // Act
        String result = processor.processTemplate(template, data);

        // Assert
        assertEquals("Hello, John! Welcome to Our Platform.", result);
    }

    @Test
    void testMissingPlaceholdersRemainUnchanged() {
        // Arrange
        String template = "Hello, ${name}! Your ${item} is ready.";
        Map<String, String> data = Map.of("name", "Alice"); // item отсутствует

        // Act
        String result = processor.processTemplate(template, data);

        // Assert
        assertEquals("Hello, Alice! Your ${item} is ready.", result);
    }

    @Test
    void testMultipleSamePlaceholders() {
        // Arrange
        String template = "${greeting}, ${name}! ${greeting} again, ${name}!";
        Map<String, String> data = Map.of("greeting", "Hello", "name", "Bob");

        // Act
        String result = processor.processTemplate(template, data);

        // Assert
        assertEquals("Hello, Bob! Hello again, Bob!", result);
    }

    @Test
    void testSpecialCharactersInTemplate() {
        // Arrange
        String template = "Price: $${amount} | Contact: ${email}";
        Map<String, String> data = Map.of("amount", "100", "email", "test@example.com");

        // Act
        String result = processor.processTemplate(template, data);

        // Assert
        assertEquals("Price: $100 | Contact: test@example.com", result);
    }

    @Test
    void testEmptyTemplate() {
        // Arrange
        String template = "";
        Map<String, String> data = Map.of("key", "value");

        // Act
        String result = processor.processTemplate(template, data);

        // Assert
        assertEquals("", result);
    }

    @Test
    void testNullTemplateThrowsException() {
        // Arrange
        Map<String, String> data = new HashMap<>();

        // Act & Assert
        assertThrows(IllegalArgumentException.class,
                () -> processor.processTemplate(null, data));
    }

    @ParameterizedTest
    @MethodSource("provideTemplatesForTesting")
    void testParameterizedTemplates(String template, Map<String, String> data, String expected) {
        // Act
        String result = processor.processTemplate(template, data);

        // Assert
        assertEquals(expected, result);
    }

    private static Stream<Arguments> provideTemplatesForTesting() {
        return Stream.of(
                Arguments.of(
                        "User: ${user}",
                        Map.of("user", "admin"),
                        "User: admin"
                ),
                Arguments.of(
                        "${a}${b}${c}",
                        Map.of("a", "1", "b", "2", "c", "3"),
                        "123"
                ),
                Arguments.of(
                        "No placeholders here",
                        Map.of("key", "value"),
                        "No placeholders here"
                )
        );
    }
}