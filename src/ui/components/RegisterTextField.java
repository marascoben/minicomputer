package ui.components;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RegisterTextField extends JPanel {

    private JLabel label;

    private JTextField textField;

    public RegisterTextField(String label) {
        super();

        this.label = new JLabel(label);
        this.textField = new JTextField("0000");
    }

    public char get() {
        return 0;
    }

    public void set(char value) {

    }

}
