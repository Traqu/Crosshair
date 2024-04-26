import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) {

        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("data/presets/crosshair.json");

        try {
            CrosshairType crosshairType = objectMapper.readValue(inputStream, CrosshairType.class);
            crosshairType.initializeActiveFields();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        CrosshairConfig.setupFile();
//        SwingUtilities.invokeLater(ApplicationInterface::new);
    }
}