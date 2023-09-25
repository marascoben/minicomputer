import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Simple CISC");

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

        frame.setSize(screen.width / 2, screen.height / 2);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}