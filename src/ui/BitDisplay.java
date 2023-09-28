package ui;

import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class BitDisplay extends JPanel {

    private JTextField[] bitFields;

    public BitDisplay(int size) {

        bitFields = new JTextField[size];

        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 5));

        for (int i = 0; i < bitFields.length; i++) {
            JTextField field = new JTextField(1);
            field.setEditable(false);
            field.setFocusable(false);
            field.setHorizontalAlignment(JTextField.CENTER);
            field.setText("0");

            bitFields[i] = field;
            add(field);
        }
    }

    public void setValue(char value) {
        for (int i = 0; i < bitFields.length; i++) {
            int mask = 1 << i;
            int bit = (value & mask) >> i;
            bitFields[(bitFields.length - 1) - i].setText(Integer.toString(bit));
        }
    }

}