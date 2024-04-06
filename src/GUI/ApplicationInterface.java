package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

import static Constants.Finals.FONT_BAGHDAD;
import static Constants.Finals.WINDOWS_LOOK_AND_FEEL;

public class ApplicationInterface extends JFrame {
    private final Crosshair CROSSHAIR = new Crosshair();

    public ApplicationInterface() throws HeadlessException {
        super("GUI.Crosshair by Traqu - alpha prerelease");
        setLookAndFeel();
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("graphics/reticle.png")));
        setIconImage(icon.getImage());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel configurationPanel = new JPanel();
        add(configurationPanel);

        JButton colorPickerButton = new JButton("Choose color");
        JButton increaseSizeButton = new JButton("Increase size");
        JButton decreaseSizeButton = new JButton("Decrease size");
        configurationPanel.add(Box.createHorizontalStrut(2));
        configurationPanel.add(colorPickerButton);
        configurationPanel.add(Box.createHorizontalStrut(10));
        configurationPanel.add(decreaseSizeButton);
        configurationPanel.add(increaseSizeButton);

        colorPickerButton.setFont(FONT_BAGHDAD);
        decreaseSizeButton.setFont(FONT_BAGHDAD);
        increaseSizeButton.setFont(FONT_BAGHDAD);
        colorPickerButton.setFocusable(false);
        decreaseSizeButton.setFocusable(false);
        increaseSizeButton.setFocusable(false);
        colorPickerButton.setMnemonic('c');
        decreaseSizeButton.setMnemonic('d');
        increaseSizeButton.setMnemonic('i');

        colorPickerButton.addActionListener(e -> {
            Color selectedColor = JColorChooser.showDialog(ApplicationInterface.this, "Choose crosshair color", Crosshair.getCrosshairColor());
            if (selectedColor != null) {
                Crosshair.setCrosshairColor(selectedColor);
            }
        });
        increaseSizeButton.addActionListener(e -> {
            int currentCrosshairDimension = Crosshair.getCrosshairSize();
            if (currentCrosshairDimension >= 6) {
            } else {
                Crosshair.setCrosshairSize(currentCrosshairDimension + 1);
            }
            Crosshair.setCrosshairDimension(new Dimension(Crosshair.getCrosshairSize(), Crosshair.getCrosshairSize()));
        });
        decreaseSizeButton.addActionListener(e -> {
            int currentCrosshairDimension = Crosshair.getCrosshairSize();
            if (currentCrosshairDimension <= 2) {
            } else {
                Crosshair.setCrosshairSize(currentCrosshairDimension - 1);
            }
            Crosshair.setCrosshairDimension(new Dimension(Crosshair.getCrosshairSize(), Crosshair.getCrosshairSize()));
        });
        packAndDisplay();
    }

    public void packAndDisplay() {
        pack();
        setVisible(true);
    }
    private static void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(WINDOWS_LOOK_AND_FEEL);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
    }
}
