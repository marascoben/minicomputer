package ui;

import javax.swing.JComponent;

public class IndicatorGroup extends JComponent {

    private static final int SPACING = Indicator.SIZE / 2;

    private boolean[] states = new boolean[4];

    public IndicatorGroup() {
        super();
        for (int i = 0; i < states.length; i++) {
            states[i] = false;
        }
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);

        g.fillRect(0, 0, (Indicator.SIZE * 4) + (SPACING * 3), 2);
        g.drawRect(0, 0, (Indicator.SIZE * 4) + (SPACING * 3), 2);

        for (int i = 0; i < states.length; i++) {

            if (states[i]) {
                g.setColor(Indicator.ON);
            } else {
                g.setColor(Indicator.OFF);
            }

            g.fillRect((i * Indicator.SIZE) + (i * SPACING), SPACING, Indicator.SIZE, Indicator.SIZE);
            g.drawRect((i * Indicator.SIZE) + (i * SPACING), SPACING, Indicator.SIZE, Indicator.SIZE);
        }
    }

    @Override
    public java.awt.Dimension getPreferredSize() {
        return new java.awt.Dimension((Indicator.SIZE * 4) + (SPACING * 3), Indicator.SIZE * 2);
    }

    /**
     * Sets the value of the indicator group. The value is broken up into 4 bits.
     * 
     * @param value The value to display.
     */
    public void setValue(byte value) {
        for (int i = 0; i < states.length; i++) {
            int mask = 1 << i;
            boolean state = (value & mask) >> i == 1;
            states[(states.length - 1) - i] = state;
        }

        repaint();
    }
}
