package CrosshairCustomization;

import GUI.Custom.GBC;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CustomCrosshairConfigurationTool extends JWindow {

//    private static List<ColorButton> canvas = new ArrayList<>();
    private final ColorButton[][] CANVAS = new ColorButton[16][16];

    public CustomCrosshairConfigurationTool() {
        setSize(16 * 16, 256);
        setLocationRelativeTo(null);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        add(buttonPanel);
        GBC gbc = new GBC(buttonPanel);
        gbc.insets = new Insets(0, 0, 0, 0);


        int x = 1, y = 1;
        for (int i = 0; i < 256; i++) {
            ColorButton colorButton = new ColorButton(new Point(x, y));
            try {
                gbc.addComponentToPanel(colorButton, x, y);
//                canvas.add(colorButton);
                CANVAS[x-1][y-1] = colorButton;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            System.out.println("Added: " + colorButton.getPosition());
            x++;
            if (x > 16) {
                x = 1;
                y++;
            }
        }
//        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CustomCrosshairConfigurationTool::new);
    }
}