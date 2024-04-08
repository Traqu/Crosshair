package Constants;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class Finals {
    private static String version;
    static {
        Properties properties = new Properties();
        try {
            InputStream inputStream = Finals.class.getClassLoader().getResourceAsStream(".properties");
            if (inputStream != null) {
                properties.load(inputStream);
                version = properties.getProperty("version");
            } else {
                System.err.println("Failes to load `.properties` file!");
            }
        } catch (IOException e) {
            System.err.println("Erroe loading `.properties` file: " + e.getMessage());
        }
    }

    public static final String APPLICATION_TITLE = "Crosshair by Traqu - version " + version;
    public static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    public static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static final String WINDOWS_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
    public static final Font FONT_BAGHDAD = new Font("Baghdad", 1, 13);
    public static final File CONFIGURATION_FILE = new File(System.getProperty("user.home") + File.separator + "Documents\\TraquCrosshair\\.properties");
    public static final int DEFAULT_SIZE = 3;
    public static final Color DEFAULT_COLOR = Color.RED;

}
