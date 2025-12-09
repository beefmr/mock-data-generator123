package com.mockgen.config;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TemplateConfigTest {

    @Test
    void testConfigInitialization() {
        // Act
        TemplateConfig config = new TemplateConfig();

        // Assert
        assertNotNull(config.getTemplateFilePath());
        assertNotNull(config.getDataFilePath());
        assertNotNull(config.getDataFormat());
    }

    @Test
    void testDataFormatValues() {
        // Act & Assert
        assertEquals("PROPERTIES", TemplateConfig.DataFormat.PROPERTIES.name());
        assertEquals("JSON", TemplateConfig.DataFormat.JSON.name());
    }
}