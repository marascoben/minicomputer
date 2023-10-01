package ui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JComponent;

public class Indicator extends JComponent {

    public static final int SIZE = 16;

    private boolean state = false;

    public Indicator() {
        super();
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);

        if (state) {
            g.setColor(Color.GREEN);
        } else {
            g.setColor(Color.RED);
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
