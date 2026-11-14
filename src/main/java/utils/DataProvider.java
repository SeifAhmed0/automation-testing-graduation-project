package utils;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class DataProvider {
    public static JsonNode JsonReader() throws IOException {
        File jsonFile = new File("src/test/resources/DataDriven/testdata.json");
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(jsonFile);
    }
}
