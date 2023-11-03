package ui;

import java.awt.BorderLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import ui.panels.ControlPanel;
import ui.panels.IndicatorPanel;

public class FrontPanel extends JFrame {

    public IndicatorPanel indicatorPanel;

    public ControlPanel controlPanel;

    public FrontPanel() {
        super("Minicomputer");

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();

        indicatorPanel = new IndicatorPanel();

        controlPanel = new ControlPanel();

        add(indicatorPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        setSize((int)(width*.65), (int)(height*.60));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
