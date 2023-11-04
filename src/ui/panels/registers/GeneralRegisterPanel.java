package ui.panels.registers;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;

import ui.components.GroupPanel;

public class GeneralRegisterPanel extends GroupPanel {

    public JTextField r0;

    public JTextField r1;

    public JTextField r2;

    public JTextField r3;

    public GeneralRegisterPanel() {
        super();
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        JLabel r0Label = new JLabel("R0: ");
        r0 = new JTextField("0000000000000000");

        JLabel r1Label = new JLabel("R1: ");
        r1 = new JTextField("0000000000000000");

        JLabel r2Label = new JLabel("R2: ");
        r2 = new JTextField("0000000000000000");

        JLabel r3Label = new JLabel("R3: ");
        r3 = new JTextField("0000000000000000");

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(r0Label, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(r0, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(r1Label, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(r1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(r2Label, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(r2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(r3Label, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(r3, gbc);
    }
    
}
