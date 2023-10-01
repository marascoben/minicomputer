import components.Computer;
import ui.FrontPanel;

public class Main {
    public static void main(String[] args) {

        Computer computer = new Computer();

        FrontPanel frontPanel = new FrontPanel(computer);
        frontPanel.setVisible(true);
    }
}