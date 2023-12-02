package ui.peripheral;

import java.awt.BorderLayout;

import javax.swing.JTextArea;

import components.io.Device;
import ui.components.GroupPanel;

public class Console extends GroupPanel implements Device {

    public JTextArea textArea = new JTextArea();

    public Console() {
        super();
        setLayout(new BorderLayout());

        add(textArea, BorderLayout.CENTER);
    }

    @Override
    public void write(char data) {
        textArea.append(String.valueOf(data));
    }

    @Override
    public char read() {
        return 0;
    }

    @Override
    public boolean check() {
        return true;
    }

    @Override
    public short getId() {
        return 1;
    }

    @Override
    public void reset() {
        textArea.setText("");
    }

}
