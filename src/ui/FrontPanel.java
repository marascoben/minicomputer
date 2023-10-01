package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FrontPanel extends JFrame {

    // General Purpose Register Panels (16-bit)
    private IndicatorPanel r0Panel, r1Panel, r2Panel, r3Panel;

    // Index Register Panels (16-bit)
    private IndicatorPanel x1Panel, x2Panel, x3Panel;

    // Memory Buffer Register Panel (16-bit)
    private IndicatorPanel mbrPanel;

    // Memory Address Register Panel (12-bit)
    private IndicatorPanel marPanel;

    // Program counter panel (12-bit)
    private IndicatorPanel pcPanel;

    // Instruction register panel (16-bit)
    private IndicatorPanel irPanel;

    // Memory fault register panel (4-bit)
    private IndicatorPanel mfrPanel;

    // Condition code panel (4-bit)
    private IndicatorPanel ccPanel;

    public FrontPanel() {
        super("Minicomputer");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8,2, 10, 10));

        // General Purpose Register Panels (16-bit)
        r0Panel = new IndicatorPanel("R0");
        r1Panel = new IndicatorPanel("R1");
        r2Panel = new IndicatorPanel("R2");
        r3Panel = new IndicatorPanel("R3");

        // Index Register Panels (16-bit)
        x1Panel = new IndicatorPanel("X1");
        x2Panel = new IndicatorPanel("X2");
        x3Panel = new IndicatorPanel("X3");

        // Memory Buffer Register Panel (16-bit)
        mbrPanel = new IndicatorPanel("Memory Buffer");

        // Memory Address Register Panel (12-bit)
        marPanel = new IndicatorPanel("Memory Address", 12);

        // Program counter panel (12-bit)
        pcPanel = new IndicatorPanel("Program Counter", 12);

        // Instruction register panel (16-bit)
        irPanel = new IndicatorPanel("Instruction");

        // Memory fault register panel (4-bit)
        mfrPanel = new IndicatorPanel("Memory Fault", 4);

        // Condition code panel (4-bit)
        ccPanel = new IndicatorPanel("Condition Code", 4);

        add(panel, BorderLayout.CENTER);

        panel.add(r0Panel);
        panel.add(r1Panel);
        panel.add(r2Panel);
        panel.add(r3Panel);
        panel.add(x1Panel);
        panel.add(x2Panel);
        panel.add(x3Panel);
        panel.add(mbrPanel);
        panel.add(marPanel);
        panel.add(pcPanel);
        panel.add(irPanel);
        panel.add(mfrPanel);
        panel.add(ccPanel);

        setSize(850, 500);
        setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
