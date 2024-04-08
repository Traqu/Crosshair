package GUI;

import Configuration.CrosshairConfig;
import GUI.Custom.GBC;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.Objects;
import java.util.Properties;

import static Constants.Finals.*;

public class ApplicationInterface extends JFrame {
    public static Crosshair crosshair = new Crosshair();
    protected JButton colorPickerButton;
    protected JButton decreaseSizeButton;
    protected JButton increaseSizeButton;
    protected JButton hideButton;
    protected JButton showButton;
    protected JButton saveButton;
    protected JButton resetButton;

    public ApplicationInterface() throws HeadlessException {
        super(APPLICATION_TITLE);

        setLookAndFeel();
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("graphics/reticle.png")));
        setIconImage(icon.getImage());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel configurationPanel = new JPanel(new GridBagLayout());
        add(configurationPanel);

        GBC gbc = new GBC(configurationPanel);
        gbc.insets = new Insets(5, 5, 5, 5);

        colorPickerButton = new JButton("Choose color");
        increaseSizeButton = new JButton("Increase size");
        decreaseSizeButton = new JButton("Decrease size");
        hideButton = new JButton("Hide");
        showButton = new JButton("Show");
        saveButton = new JButton("Save");
        resetButton = new JButton("Reset");

        colorPickerButton.setMnemonic('c');
        decreaseSizeButton.setMnemonic('d');
        increaseSizeButton.setMnemonic('i');
        hideButton.setMnemonic('h');
        showButton.setMnemonic('h');
        saveButton.setMnemonic('s');
        resetButton.setMnemonic('r');
        saveButton.setToolTipText("test");
        saveButton.setEnabled(false);
        if (Crosshair.getCrosshairColor().equals(DEFAULT_COLOR) && Crosshair.getCrosshairSizeType() == DEFAULT_SIZE) {
            resetButton.setEnabled(false);
        }

        try {
            gbc.addComponentToPanel(1, 1, colorPickerButton);
            gbc.addComponentToPanel(2, 1, Box.createHorizontalStrut(0)); //Added to create empty spacing; value 0 is set due to insets from both sides added to each of the components, resulting in total space of 10 pixels.
            gbc.addComponentToPanel(3, 1, decreaseSizeButton);
            gbc.addComponentToPanel(4, 1, increaseSizeButton);
            gbc.addComponentToPanel(1, 2, saveButton);
//            gbc.addComponentToPanel(2, 2, Box.createHorizontalStrut(0)); //Added to create empty spacing; value 0 is set due to insets from both sides added to each of the components, resulting in total space of 10 pixels.
            gbc.addComponentToPanel(3, 2, hideButton);
            gbc.addComponentToPanel(3, 2, showButton);
            gbc.addComponentToPanel(4, 2, resetButton);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        colorPickerButtonAddListener();
        increaseSizeButtonAddListener();
        decreaseSizeButtonAddListener();
        saveButtonAddListener();
        hideButtonAddListener();
        showButtonAddListener();
        resetButtonAddListener();

        setUpButtons();
        packAndDisplay();
    }

    private void colorPickerButtonAddListener() {
        colorPickerButton.addActionListener(e -> {
            Color selectedColor = JColorChooser.showDialog(ApplicationInterface.this, "Choose crosshair color", Crosshair.getCrosshairColor());
            if (selectedColor != null) {
                Crosshair.setCrosshairColor(selectedColor);
                updateCrosshair();
                saveButton.setEnabled(true);
                resetButton.setEnabled(true);
            }
        });
    }

    private static void updateCrosshair() {
        crosshair.dispose();
        crosshair = new Crosshair();
    }

    private void increaseSizeButtonAddListener() {
        increaseSizeButton.addActionListener(e -> {

            int newSize = Crosshair.getCrosshairSizeType() + 1;
            if (Crosshair.getCrosshairSizesMap().containsKey(newSize)) {
                Crosshair.setCrosshairSizeType(newSize);
                saveButton.setEnabled(true);
                resetButton.setEnabled(true);
                decreaseSizeButton.setEnabled(true);
                showButton.setVisible(false);
                hideButton.setVisible(true);
            }

            if (!Crosshair.getCrosshairSizesMap().containsKey(newSize + 1)) {
                increaseSizeButton.setEnabled(false);
            }

            Crosshair.setCrosshairDimension(Crosshair.getCrosshairSizesMap().get(Crosshair.getCrosshairSizeType()));
            updateCrosshair();
        });
    }

    private void decreaseSizeButtonAddListener() {
        decreaseSizeButton.addActionListener(e -> {


            int newSize = Crosshair.getCrosshairSizeType() - 1;
            if (Crosshair.getCrosshairSizesMap().containsKey(newSize)) {
                Crosshair.setCrosshairSizeType(newSize);
                saveButton.setEnabled(true);
                resetButton.setEnabled(true);
                increaseSizeButton.setEnabled(true);
                showButton.setVisible(false);
                hideButton.setVisible(true);
            }
            if (!Crosshair.getCrosshairSizesMap().containsKey(newSize - 1)) {
                decreaseSizeButton.setEnabled(false);
            }

            Crosshair.setCrosshairDimension(new Dimension(Crosshair.getCrosshairSizeType(), Crosshair.getCrosshairSizeType()));
            updateCrosshair();
        });
    }

    private void saveButtonAddListener() {
        saveButton.addActionListener(e -> {
            CrosshairConfig.saveConfiguration(Crosshair.getCrosshairColor(), Crosshair.getCrosshairSizeType());
            saveButton.setEnabled(false);
        });
    }

    private void hideButtonAddListener() {
        hideButton.addActionListener(e -> {
            hideButton.setVisible(false);
            showButton.setVisible(true);
            crosshair.dispose();
            Crosshair.getInstance().dispose();
            Crosshair.removeAllCroshairs();
        });
    }

    private void showButtonAddListener() {
        showButton.addActionListener(e -> {
            showButton.setVisible(false);
            hideButton.setVisible(true);
            updateCrosshair();
        });
    }

    private void resetButtonAddListener() {
        resetButton.addActionListener(e -> {
            Crosshair.removeAllCroshairs();
            CrosshairConfig.saveDefaultConfiguration();
            CrosshairConfig.loadConfiguration(crosshair);
            saveButton.setEnabled(true);
            resetButton.setEnabled(false);
            decreaseSizeButton.setEnabled(true);
            increaseSizeButton.setEnabled(true);
        });
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

    public Crosshair getCrosshair() {
        return crosshair;
    }

    public void setCrosshair(Crosshair newCrosshair) {
        crosshair = newCrosshair;
    }

    public void setUpButtons() {
        Class<?> clazz = this.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            if (field.getType().equals(JButton.class)) {
                try {
                    JButton button = (JButton) field.get(this);
                    button.setFocusable(false);
                    button.setFont(FONT_BAGHDAD);
                    button.setPreferredSize(new Dimension(120, 28));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}