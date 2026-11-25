package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DataProviders {

    private static final JsonNode ROOT;

    static {
        try (InputStream in = DataProviders.class.getClassLoader().getResourceAsStream("testdata.json")) {
            if (in == null) {
                throw new IllegalStateException("testdata.json not found on classpath");
            }
            ROOT = new ObjectMapper().readTree(in);
        } catch (IOException e) {
            throw new IllegalStateException("Could not read testdata.json", e);
        }
    }

    public static String getString(String... path) {
        JsonNode n = ROOT;
        for (String p : path) {
            n = n.get(p);
        }
        return n.asText();
    }

    private static Object[][] read(String key, String... fields) {
        List<Object[]> rows = new ArrayList<>();
        for (JsonNode item : ROOT.get(key)) {
            Object[] row = new Object[fields.length];
            for (int i = 0; i < fields.length; i++) {
                row[i] = item.get(fields[i]).asText();
            }
            rows.add(row);
        }
        return rows.toArray(new Object[0][]);
    }

    @DataProvider(name = "invalidLogin")
    public static Object[][] invalidLogin() {
        return read("invalidUsers", "username", "password");
    }

    @DataProvider(name = "searchNames")
    public static Object[][] searchNames() {
        return read("searchNames", "name");
    }

    @DataProvider(name = "newEmployees")
    public static Object[][] newEmployees() {
        return read("newEmployees", "firstName", "lastName");
    }
}