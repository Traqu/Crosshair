package Constants;

import java.awt.*;
import java.io.File;

public abstract class Finals {
    public static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    public static final int SCREEN_WIDTH     = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static final String WINDOWS_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
    public static final Font FONT_BAGHDAD = new Font("Baghdad", 1, 13);
    public static final File CONFIGURATION_FILE = new File(System.getProperty("user.home") + File.separator + "Documents\\TraquCrosshair\\.properties");
    public static final int DEFAULT_SIZE = 3;
    public static final Color DEFAULT_COLOR = Color.RED;

}
