import javax.swing.*;

import ui.BitDisplay;
import ui.MainWindow;

import java.awt.*;

public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Simple CISC");

        JFrame window = new MainWindow();
        
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        BitDisplay display1 = new BitDisplay(16);
        display1.setValue((char) 4);

        panel.add(display1);

        frame.add(panel);

        frame.setSize(screen.width / 2, screen.height / 2);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
}