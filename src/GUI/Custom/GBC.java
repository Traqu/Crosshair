package GUI.Custom;

import javax.swing.*;
import java.awt.*;

/**
 * GBC is an extended version of GridBagConstraints class, that's main purpose is to simplify its usage.
 *
 * @author Piotr Smolik /Traqu
 * @see GridBagConstraints
 */
public class GBC extends GridBagConstraints {

    private JPanel panel;

    /**
     * Instantiates a new Gbc.
     */
    public GBC() {
        gridx = 0;
        gridy = 0;
    }

    /**
     * This constructor allows you to pass through a JPanel, which later in combination with
     * overloaded setLocation method, will lead to simplifying code.
     * * Example usage:
     * * {@code
     * * try {
     * * JPanel panel = new JPanel(new GridBagLayout());
     * * this.add(panel);
     * * GBC gbc = new GBC(panel);
     * * gbc.addComponent(1, 1, new JLabel("Example button label"));
     * * gbc.addComponent(1, 2, new JButton("Example button"));
     * * } catch (Exception e) {
     * * throw new RuntimeException(e);
     * * }
     * }*
     *
     * @param panel The panel to which passed components will be added.
     */
    public GBC(JPanel panel) {
        this.panel = panel;
        gridx = 0;
        gridy = 0;
    }

    /**
     * User should provide actual non-abstract component location. First will be first, so instead passing 0, you would pass 1.
     * If you want to keep one coordinate as it is, you can pass 0 as an argument, so previous value will not be updated.
     *
     * @param x Horizontal location for the component.
     * @param y Vertical location for the component.
     */
    public void setLocation(int x, int y) {
        if (x != 0) {
            this.gridx = x - 1;
        }

        if (y != 0) {
            this.gridy = y - 1;
        }
    }

    /**
     * Add component.
     *
     * @param column         Horizontal location for the component.
     * @param row         Vertical location for the component.
     * @param component The component which will be added to the panel.
     * @throws Exception This exception will be thrown if no JPanel is passed to constructor and addComponent method is called.
     * @see #addComponentToPanel(int, int, Component)
     * @see #checkForPanel()
     */
    public void addComponentToPanel(int column, int row, Component component) throws Exception {
        checkForPanel();
        if (column != 0) {
            this.gridx = column - 1;
        }

        if (row != 0) {
            this.gridy = row - 1;
        }
        panel.add(component, this);
    }

    /**
     * Add component.
     *
     * @param component the component
     * @param x         the x
     * @param y         the y
     * @throws Exception the exception
     */
    public void addComponentToPanel(Component component, int x, int y) throws Exception {
        checkForPanel();
        if (x != 0) {
            this.gridx = x - 1;
        }

        if (y != 0) {
            this.gridy = y - 1;
        }
        panel.add(component, this);
    }

    private void checkForPanel() {
        if (panel == null) {
            throw new IllegalStateException("Panel not initialized. Use constructor with JPanel argument.");
        }
    }
}
