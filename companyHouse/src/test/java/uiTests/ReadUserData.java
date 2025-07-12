package uiTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class ReadUserData {
    public static User loadUserFromFile(String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(filePath), User.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

