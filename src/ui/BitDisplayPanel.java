package ui;

import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class BitDisplayPanel extends JPanel {

    private JTextField[] fields;

    public BitDisplayPanel(char value) {
        fields = new JTextField[16];

        for (int i = 0; i < 16; i++) {
            fields[i] = new JTextField(1);
            fields[i].setEditable(false);
            fields[i].setText("1");
        }

        JPanel panel = new JPanel(new FlowLayout());
        for (int i = 0; i < 4; i++) {
            panel.add(fields[i]);
        }

        
    }

}