package Configuration;

import GUI.Crosshair;

import java.awt.*;
import java.io.*;
import java.util.Properties;

import static Constants.Finals.*;

public abstract class CrosshairConfig {
    public static void setupFile() {
        if (!CONFIGURATION_FILE.exists()) {
            CONFIGURATION_FILE.getParentFile().mkdirs();
            try {
                CONFIGURATION_FILE.createNewFile();
                saveDefaultConfiguration();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void saveDefaultConfiguration() {
        saveConfiguration(DEFAULT_COLOR, DEFAULT_SIZE);
    }

    public static void saveConfiguration(Color color, int size) {
        try (OutputStream output = new FileOutputStream(CONFIGURATION_FILE)) {
            Properties properties = new Properties();
            String colorString = color.getRed() + "," + color.getGreen() + "," + color.getBlue();
            properties.setProperty("Color", colorString);
            properties.setProperty("Size", String.valueOf(size));
            properties.store(output, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadConfiguration(Crosshair crosshair) {
        try (InputStream input = new FileInputStream(CONFIGURATION_FILE)) {
            Properties properties = new Properties();
            properties.load(input);
            String[] colorComponents = properties.getProperty("Color").split(",");
            int red = Integer.parseInt(colorComponents[0]);
            int green = Integer.parseInt(colorComponents[1]);
            int blue = Integer.parseInt(colorComponents[2]);
            Color color = new Color(red, green, blue);
            int size = Integer.parseInt(properties.getProperty("Size"));
            Crosshair.setCrosshairColor(color);
            Crosshair.setCrosshairSizeType(size);
            crosshair.dispose();
            if(!Crosshair.isInitialLoad){
                new Crosshair();
            }
            Crosshair.isInitialLoad = false;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}