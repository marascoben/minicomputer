package ui.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;

import ui.components.GroupPanel;

public class ControlPanel extends GroupPanel {

    public JButton runButton;

    public JButton stepButton;

    public JButton initButton;

    public JButton loadButton;

    public ControlPanel() {
        super();
        setOpaque(false);

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        runButton = new JButton("Run");
        stepButton = new JButton("SS");
        initButton = new JButton("Init");
        loadButton = new JButton("Load");

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(initButton, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(loadButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        add(stepButton, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        add(runButton, gbc);

    }
}
