/**
 * @file roundedButtonUI.java
 * @brief This file contains the custom UI class for creating rounded buttons in the car maintenance application.
 */

package com.ebcak.carmaintenancegui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicButtonUI;

public class roundedButtonUI extends BasicButtonUI {
    
    /**
     * @brief Installs the UI for the button, setting it to be non-opaque and adding empty borders.
     * @param c The component to which the UI is being installed.
     */
    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        AbstractButton button = (AbstractButton) c;
        button.setOpaque(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
    }

    /**
     * @brief Paints the button with a rounded background.
     * @param g The Graphics object used for painting.
     * @param c The component to be painted.
     */
    @Override
    public void paint(Graphics g, JComponent c) {
        AbstractButton b = (AbstractButton) c;
        paintBackground(g, b, b.getModel().isPressed() ? 2 : 0);
        super.paint(g, c);
    }

    /**
     * @brief Paints the rounded background of the button.
     * @param g The Graphics object used for painting.
     * @param c The component to be painted.
     * @param yOffset The y-offset for the pressed state effect.
     */
    private void paintBackground(Graphics g, JComponent c, int yOffset) {
        Dimension size = c.getSize();
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(c.getBackground());
        g2.fillRoundRect(0, yOffset, size.width, size.height - yOffset, 30, 30);
    }
}
