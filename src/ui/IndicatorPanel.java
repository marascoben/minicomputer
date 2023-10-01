package ui;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class IndicatorPanel extends JPanel {

    private IndicatorGroup[] groups;

    // Text label for the indicator panel
    private JPanel labelPanel;

    // The panel that contains the indicator groups
    private JPanel indicatorPanel;

    /**
     * Create an indicator panel with 16 lights (the default size of a word). The
     * panel is comprised of multiple IndicatorGroup objects, each of which contains
     * 4 Indicators.
     * 
     * @param label The label text for the indicator panel.
     */
    public IndicatorPanel(String label) {
        this(label, 16);
    }

    public IndicatorPanel(String label, int lights) {
        super();
        setLayout(new GridLayout(2, 1));

        labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        indicatorPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));

        labelPanel.add(new JLabel(label));

        groups = new IndicatorGroup[lights / 4];

        for (int i = 0; i < groups.length; i++) {
            groups[i] = new IndicatorGroup();
            indicatorPanel.add(groups[i]);
        }

        add(labelPanel);
        add(indicatorPanel);
    }

    /**
     * Sets the value displayed on the indicator panel. The word gets broken up into
     * modules of 4 bits, and each module is displayed by an IndicatorGroup.
     * 
     * @param value The value to display.
     */
    public void setValue(char value) {
        for (int i = 0; i < groups.length; i++) {
            int mask = 0b1111 << (i * 4);
            byte groupValue = (byte) ((value & mask) >> (i * 4));
            groups[(groups.length - 1) - i].setValue(groupValue);
        }
    }
}
