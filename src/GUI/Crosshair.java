package GUI;

import Configuration.CrosshairConfig;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.List;

import static Constants.Finals.*;

public class Crosshair extends JWindow {
    private static List<Crosshair> crosshairList = new ArrayList<>();
    private static Dimension crosshairDimension = new Dimension(4, 4);
    private static int crosshairSizeType;
    private static Map<Integer, Dimension> crosshairSizesMap = new Hashtable<>();

    static {
        crosshairSizesMap.put(1, new Dimension(2,2));
        crosshairSizesMap.put(2, new Dimension(3,3));
        crosshairSizesMap.put(3, new Dimension(4,4));
        crosshairSizesMap.put(4, new Dimension(5,5));
        crosshairSizesMap.put(5, new Dimension(6,6));
    }
    private static Color crosshairColor = Color.RED;
    private static Crosshair instance;
    private static int x = SCREEN_WIDTH / 2 - crosshairSizeType / 2;
    private static int y = SCREEN_HEIGHT / 2 - crosshairSizeType / 2;

    private static boolean hasLoadedSize = false;
    public static boolean isInitialLoad = true;

    public Crosshair() {
//        setOpacity(0); //TODO pozwoli na grafics i customowy ksztalt celownika?
        instance = this;
        crosshairList.add(this);

        if(!hasLoadedSize){
            try (InputStream input = new FileInputStream(CONFIGURATION_FILE)) {
                Properties properties = new Properties();
                properties.load(input);
                crosshairSizeType = Integer.parseInt(properties.getProperty("Size"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            hasLoadedSize = true;
        }

        if (!GlobalFlag.isLoaded()) {
            GlobalFlag.setLoaded();
            CrosshairConfig.loadConfiguration(Crosshair.instance);
        }
        setAlwaysOnTop(true);
        JPanel crosshair = new JPanel();
        crosshair.setBackground(crosshairColor);
        crosshair.setPreferredSize(new Dimension(crosshairSizesMap.get(getCrosshairSizeType())));
        setLocation((int) (SCREEN_WIDTH / 2 - getCrosshairSizesMap().get(crosshairSizeType).getWidth() / 2), (int) (SCREEN_HEIGHT / 2 - getCrosshairSizesMap().get(crosshairSizeType).getWidth() / 2));
        add(crosshair);
        packAndDisplay();
        hideCursor();
    }

    public static void removeAllCroshairs(){
        for (Crosshair crosshair : crosshairList) {
            crosshair.dispose();
        }
    }

    public static Map<Integer, Dimension> getCrosshairSizesMap() {
        return crosshairSizesMap;
    }

    public static void setCrosshairSizesMap(Map<Integer, Dimension> crosshairSizesMap) {
        Crosshair.crosshairSizesMap = crosshairSizesMap;
    }

    public static int crosshairY() {
        return SCREEN_HEIGHT / 2 - crosshairSizeType;
    }

    public static int crosshairX() {
        return SCREEN_WIDTH / 2 - crosshairSizeType;
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

    public static int getCrosshairSizeType() {
        return crosshairSizeType;
    }

    public static void setCrosshairSizeType(int crosshairSizeType) {
        Crosshair.crosshairSizeType = crosshairSizeType;
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
    }

    public static void setX(int x) {
        Crosshair.x = x;
    }

    public static void setY(int y) {
        Crosshair.y = y;
    }

    private static void updateX() {
        x = SCREEN_WIDTH / 2 - crosshairSizeType / 2;
    }

    private static void updateY() {
        y = SCREEN_HEIGHT / 2 - crosshairSizeType / 2;
    }

    public static Crosshair getInstance() {
        return instance;
    }

    public static void setInstance(Crosshair instance) {
        Crosshair.instance = instance;
    }
}
