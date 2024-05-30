/**
 * @file roundedPanelUI.java
 * @brief This file contains the custom UI class for creating panels with rounded corners in the car maintenance application.
 */

package com.ebcak.carmaintenancegui;

import javax.swing.*;
import java.awt.*;

public class roundedPanelUI extends JPanel {
    private int radius;

    /**
     * @brief Constructor for roundedPanelUI class to initialize the radius and set the panel to be non-opaque.
     * @param radius The radius of the rounded corners.
     */
    public roundedPanelUI(int radius) {
        this.radius = radius;
        setOpaque(false);
    }

    /**
     * @brief Paints the panel with rounded corners.
     * @param g The Graphics object used for painting.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension arcs = new Dimension(radius, radius);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Çerçeve Rengi
        graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
    }
}
