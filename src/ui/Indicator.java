package ui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JComponent;

public class Indicator extends JComponent {

    public static Color ON = new Color(191, 107, 39, 255);

    public static Color OFF = new Color(237, 189, 150, 255);

    public static final int SIZE = 16;

    private boolean state = false;

    public Indicator() {
        super();
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);

        if (state) {
            g.setColor(OFF);
        } else {
            g.setColor(ON);
        }

        g.fillRect(0, 0, SIZE, SIZE);
        g.drawRect(0, 0, SIZE, SIZE);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(SIZE, SIZE);
    }

    public void setState(boolean state) {
        this.state = state;
        repaint();
    }
}
