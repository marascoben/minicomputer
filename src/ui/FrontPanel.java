package ui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import components.Computer;
import components.ROM;
import config.Config;
import ui.components.FrontPanelMenu;
import ui.panels.ControlPanel;
import ui.panels.IndicatorPanel;
import ui.panels.InputPanel;
import ui.windows.ConsoleWindow;
import util.ScreenUtil;

public class FrontPanel extends JFrame {

    private static final Logger LOGGER = Logger.getLogger(Computer.class.getName());

    // Panel containing all of the indicator lights for registers
    public IndicatorPanel indicatorPanel = new IndicatorPanel();

    // Panel containing control buttons for the minicomputer
    public ControlPanel controlPanel = new ControlPanel();

    // Panel containing all text inputs for settable registers
    public InputPanel inputPanel = new InputPanel();

    // Menu bar for the front panel
    public FrontPanelMenu menu = new FrontPanelMenu();

    public ConsoleWindow consoleWindow = new ConsoleWindow();

    private final JFileChooser fileChooser = new JFileChooser();

    private Computer computer;

    public FrontPanel(Computer computer) {
        super("Minicomputer");

        this.computer = computer;

        // Setup the file chooser for loading ROMs
        fileChooser.setDialogTitle("Load ROM");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("ROM (.txt)", Config.ROM_FILE_EXTENSION));

        registerListeners();
        registerActionListeners();
        registerKeyListeners();

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
        stackPanel.add(inputPanel, gbc);

        add(menu, BorderLayout.NORTH);
        add(indicatorPanel, BorderLayout.CENTER);
        add(stackPanel, BorderLayout.SOUTH);

        setSize(ScreenUtil.getScaledSize(Config.UI_SCALE_WIDTH, Config.UI_SCALE_HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    /**
     * Registers all of the listeners for the front panel. Will be called after
     * startup and any reset operation.
     */
    public void registerListeners() {
        computer.processor.addListener(() -> {
            updateIndicators();
            updateTextInputs();
        });
    }

    /**
     * Registers all action listeners for the front panel. Will be called once at
     * startup.
     */
    public void registerActionListeners() {

        // Configure load ROM from menu bar button
        menu.fileLoadROM.addActionListener(e -> {
            int returnVal = fileChooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                ROM rom = new ROM(fileChooser.getSelectedFile());
                computer.loadROM(rom);
                JOptionPane.showMessageDialog(this, "ROM loaded successfully!", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        menu.actionReset.addActionListener(e -> {
            computer.reset();
            registerListeners();
            JOptionPane.showMessageDialog(this, "Minicomputer reset successfully!", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        // Launch the console window
        menu.viewConsole.addActionListener(e -> {
            consoleWindow.setVisible(true);
        });

        controlPanel.stepForwardButton.addActionListener(e -> {
            computer.step();
        });

        controlPanel.runButton.addActionListener(e -> {
            computer.run();
        });
    }

    /**
     * Registers all key listeners for the front panel. Will be called once at
     * startup.
     */
    public void registerKeyListeners() {
        inputPanel.miscRegisterPanel.pc.textField.addActionListener(e -> {
            computer.processor.PC = inputPanel.miscRegisterPanel.pc.get();
            updateIndicators();
        });

        inputPanel.miscRegisterPanel.mar.textField.addActionListener(e -> {
            computer.processor.MAR = inputPanel.miscRegisterPanel.mar.get();
            updateIndicators();
        });

        inputPanel.miscRegisterPanel.mbr.textField.addActionListener(e -> {
            computer.processor.MBR = inputPanel.miscRegisterPanel.mbr.get();
            updateIndicators();
        });

        inputPanel.generalRegisterPanel.r0.textField.addActionListener(e -> {
            computer.processor.R0 = inputPanel.generalRegisterPanel.r0.get();
            updateIndicators();
        });

        inputPanel.generalRegisterPanel.r1.textField.addActionListener(e -> {
            computer.processor.R1 = inputPanel.generalRegisterPanel.r1.get();
            updateIndicators();
        });

        inputPanel.generalRegisterPanel.r2.textField.addActionListener(e -> {
            computer.processor.R2 = inputPanel.generalRegisterPanel.r2.get();
            updateIndicators();
        });

        inputPanel.generalRegisterPanel.r3.textField.addActionListener(e -> {
            computer.processor.R3 = inputPanel.generalRegisterPanel.r3.get();
            updateIndicators();
        });

        inputPanel.indexRegisterPanel.x1.textField.addActionListener(e -> {
            computer.processor.X1 = inputPanel.indexRegisterPanel.x1.get();
            updateIndicators();
        });

        inputPanel.indexRegisterPanel.x2.textField.addActionListener(e -> {
            computer.processor.X2 = inputPanel.indexRegisterPanel.x2.get();
            updateIndicators();
        });

        inputPanel.indexRegisterPanel.x3.textField.addActionListener(e -> {
            computer.processor.X3 = inputPanel.indexRegisterPanel.x3.get();
            updateIndicators();
        });
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
        inputPanel.miscRegisterPanel.pc.set(computer.processor.PC);
        inputPanel.miscRegisterPanel.mar.set(computer.processor.MAR);
        inputPanel.miscRegisterPanel.mbr.set(computer.processor.MBR);

        // Update general purpose register text inputs
        inputPanel.generalRegisterPanel.r0.set(computer.processor.R0);
        inputPanel.generalRegisterPanel.r1.set(computer.processor.R1);
        inputPanel.generalRegisterPanel.r2.set(computer.processor.R2);
        inputPanel.generalRegisterPanel.r3.set(computer.processor.R3);

        // Update index register text inputs
        inputPanel.indexRegisterPanel.x1.set(computer.processor.X1);
        inputPanel.indexRegisterPanel.x2.set(computer.processor.X2);
        inputPanel.indexRegisterPanel.x3.set(computer.processor.X3);
    }

}
