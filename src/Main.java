import javax.swing.*;

import ui.BitDisplayPanel;

import java.awt.*;

public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Simple CISC");

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        BitDisplayPanel display1 = new BitDisplayPanel((char) 0);

        panel.add(display1);

        frame.add(panel);

        frame.setSize(screen.width / 2, screen.height / 2);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
}