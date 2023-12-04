package ui.peripheral;

import java.util.ArrayList;

import javax.swing.JTextField;

import components.io.Device;

public class Keyboard extends JTextField implements Device {

    public ArrayList<Character> buffer = new ArrayList<Character>();

    public Keyboard() {
        super();

        this.addActionListener(e -> {
            // Add all characters to the buffer
            for (char c : getText().toCharArray()) {
                buffer.add(c);
            }
        });
    }

    @Override
    public void write(char data) {
        return;
    }

    @Override
    public char read() {
        char c = buffer.get(0);
        buffer.remove(0);
        return c;
    }

    @Override
    public boolean check() {
        return !buffer.isEmpty();
    }

    @Override
    public short getId() {
        return 0;
    }

    @Override
    public void reset() {
        setText("");
    }

}
