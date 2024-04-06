package GUI;

import javax.swing.*;
import java.awt.*;

import static Constants.Finals.*;

public class Crosshair extends JWindow {
    private static Dimension crosshairDimension = new Dimension(4, 4);
    private static int crosshairSize = (int) (crosshairDimension.getWidth() / 2);
    private static Color crosshairColor = Color.RED;
    private static Crosshair instance;
    private static int x = SCREEN_WIDTH / 2 - crosshairSize / 2;
    private static int y = SCREEN_HEIGHT / 2 - crosshairSize / 2;


    public Crosshair() {
        instance = this;
        setAlwaysOnTop(true);
        JPanel crosshair = new JPanel();
        crosshair.setBackground(crosshairColor);
        crosshair.setPreferredSize(crosshairDimension);
        setLocation(x, y);
        add(crosshair);
        packAndDisplay();
        hideCursor();
    }

    public static int crosshairY() {
        return SCREEN_HEIGHT / 2 - crosshairSize;
    }

    public static int crosshairX() {
        return SCREEN_WIDTH / 2 - crosshairSize;
    }

    private void packAndDisplay() {
        pack();
        setVisible(true);
    }

    private void hideCursor() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Cursor transparentCursor = toolkit.createCustomCursor(toolkit.createImage(new byte[]{}), new Point(0, 0), "transparentCursor");
        setCursor(transparentCursor);
    }

    public static Dimension getCrosshairDimension() {
        return crosshairDimension;
    }

    public static void setCrosshairDimension(Dimension crosshairDimension) {
        Crosshair.crosshairDimension = crosshairDimension;
        updateCrosshair();

    }

    public static int getCrosshairSize() {
        return crosshairSize;
    }

    public static void setCrosshairSize(int crosshairSize) {
        Crosshair.crosshairSize = crosshairSize;
        updateCrosshair();
    }

    public static Color getCrosshairColor() {
        return crosshairColor;
    }

    public static void setCrosshairColor(Color crosshairColor) {
        Crosshair.crosshairColor = crosshairColor;
        updateCrosshair();
    }

    private static void updateCrosshair() {
        instance.dispose();
        updateX();
        updateY();
        new Crosshair();
    }

    public static void setX(int x) {
        Crosshair.x = x;
    }

    public static void setY(int y) {
        Crosshair.y = y;
    }

    private static void updateX() {
        x = SCREEN_WIDTH / 2 - crosshairSize / 2;
    }

    private static void updateY() {
        y = SCREEN_HEIGHT / 2 - crosshairSize / 2;
    }
}
