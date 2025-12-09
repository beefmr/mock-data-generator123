package com.mockgen.parser;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public interface DataParser {
    Map<String, String> parseData(File dataFile) throws IOException;
}