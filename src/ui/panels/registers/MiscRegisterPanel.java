package ui.panels.registers;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;

import ui.components.GroupPanel;

public class MiscRegisterPanel extends GroupPanel {

    public JTextField pc;

    public JTextField mar;

    public JTextField mbr;

    public MiscRegisterPanel() {
        super();
        setLayout(new GridBagLayout());

        JLabel pcLabel = new JLabel("PC: ");
        pc = new JTextField("0000000000000000");

        JLabel marLabel = new JLabel("MAR: ");
        mar = new JTextField("000000000000");

        JLabel mbrLabel = new JLabel("MBR: ");
        mbr = new JTextField("0000000000000000");

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(pcLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(pc, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(marLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(mar, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(mbrLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(mbr, gbc);
    }

}
