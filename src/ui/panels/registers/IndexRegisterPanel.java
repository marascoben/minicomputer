package ui.panels.registers;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;

import ui.components.GroupPanel;

public class IndexRegisterPanel extends GroupPanel {
    
    public JTextField x1;

    public JTextField x2;

    public JTextField x3;

    public IndexRegisterPanel(){
        super();
        setLayout(new GridBagLayout());

        JLabel x1Label = new JLabel("X1: ");
        x1 = new JTextField("0000");

        JLabel x2Label = new JLabel("X2: ");
        x2 = new JTextField("0000");

        JLabel x3Label = new JLabel("X3: ");
        x3 = new JTextField("0000");

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(x1Label, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(x1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(x2Label, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(x2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(x3Label, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(x3, gbc);
    }

}
