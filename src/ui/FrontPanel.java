package ui;

import java.awt.BorderLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import components.Computer;
import components.ROM;
import config.Config;
import ui.panels.ControlPanel;
import ui.panels.IndicatorPanel;
import ui.panels.RegisterPanel;
import util.FormatUtils;

public class FrontPanel extends JFrame {

    // Panel containing all of the indicator lights for registers
    public IndicatorPanel indicatorPanel = new IndicatorPanel();

    // Panel containing control buttons for the minicomputer
    public ControlPanel controlPanel = new ControlPanel();

    // Panel containing all text inputs for settable registers
    public RegisterPanel registerPanel = new RegisterPanel();

    private final JFileChooser fileChooser = new JFileChooser();

    private Computer computer;

    public FrontPanel(Computer computer) {
        super("Minicomputer");

        this.computer = computer;

        // Setup the file chooser for loading ROMs
        fileChooser.setDialogTitle("Load ROM");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("ROM (.txt)", Config.ROM_FILE_EXTENSION));

        // Setup the load button action listener
        controlPanel.loadButton.addActionListener(e -> {
            int returnVal = fileChooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                ROM rom = new ROM(fileChooser.getSelectedFile());
                computer.loadROM(rom);
            }
        });

        computer.processor.addListener(() -> {
            updateIndicators();
            updateTextInputs();
        });

        // Setup the intialize button action listener
        controlPanel.initButton.addActionListener(e -> {
            computer.reset();
        });

        // Setup the Single Step button action listener
        controlPanel.stepButton.addActionListener(e -> {
            computer.step();
        });

        // Setup the Run button action listener
        controlPanel.runButton.addActionListener(e -> {
            computer.run();
        });

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();

        JPanel stackPanel = new JPanel();
        stackPanel.setOpaque(false);
        stackPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        stackPanel.add(controlPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        stackPanel.add(registerPanel, gbc);

        add(indicatorPanel, BorderLayout.CENTER);
        add(stackPanel, BorderLayout.SOUTH);

        setSize((int) (width * Config.UI_SCALE_WIDTH), (int) (height * Config.UI_SCALE_HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void updateIndicators() {
        indicatorPanel.r0Group.set(computer.processor.R0);
        indicatorPanel.r1Group.set(computer.processor.R1);
        indicatorPanel.r2Group.set(computer.processor.R2);
        indicatorPanel.r3Group.set(computer.processor.R3);
        indicatorPanel.x1Group.set(computer.processor.X1);
        indicatorPanel.x2Group.set(computer.processor.X2);
        indicatorPanel.x3Group.set(computer.processor.X3);
        indicatorPanel.mbrGroup.set(computer.processor.MBR);
        indicatorPanel.marGroup.set(computer.processor.MAR);
        indicatorPanel.pcGroup.set(computer.processor.PC);
        indicatorPanel.irGroup.set(computer.processor.getIR());
        indicatorPanel.mfrGroup.set(computer.processor.getMFR());
        indicatorPanel.ccGroup.set(computer.processor.getCC());
    }

    public void updateTextInputs() {
        // Update misc regsiter group text inputs
        registerPanel.miscRegisterPanel.pc.setText(FormatUtils.toBinaryString(computer.processor.PC, 16));
        registerPanel.miscRegisterPanel.mar.setText(FormatUtils.toBinaryString(computer.processor.MAR, 12));
        registerPanel.miscRegisterPanel.mbr.setText(FormatUtils.toBinaryString(computer.processor.MBR, 16));

        // Update general purpose register text inputs
        registerPanel.generalRegisterPanel.r0.setText(FormatUtils.toBinaryString(computer.processor.R0, 16));
        registerPanel.generalRegisterPanel.r1.setText(FormatUtils.toBinaryString(computer.processor.R1, 16));
        registerPanel.generalRegisterPanel.r2.setText(FormatUtils.toBinaryString(computer.processor.R2, 16));
        registerPanel.generalRegisterPanel.r3.setText(FormatUtils.toBinaryString(computer.processor.R3, 16));
    }

}
