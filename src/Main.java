import Configuration.CrosshairConfig;
import GUI.ApplicationInterface;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        CrosshairConfig.setupFile();
        SwingUtilities.invokeLater(ApplicationInterface::new);
    }
}