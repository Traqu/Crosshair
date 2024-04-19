package CrosshairCustomization;

import javax.swing.*;
import java.awt.*;

public class ColorButton extends JButton {
    private static final Color DEFAULT_COLOR = new Color(238, 238, 238);
    private boolean isSelected = false;
    private Point position;

    public ColorButton(Point position) {
        this.position = position;
        setPreferredSize(new Dimension(16, 16));
        setBackground(DEFAULT_COLOR);
        addActionListener(e -> {
            if (!isSelected) {
                setBackground(Color.RED);
                isSelected = true;
            } else {
                setBackground(DEFAULT_COLOR);
                isSelected = false;
            }
        });
    }

    public boolean isSelected() {
        return isSelected;
    }

    public Point getPosition() {
        return position;
    }
}
