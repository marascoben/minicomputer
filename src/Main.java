import java.io.File;

import components.Computer;
import ui.FrontPanel;

public class Main {

    public static String IPL_FILE = "IPL.txt";

    public static void main(String[] args) {

        Computer computer = new Computer();

        // Check for the presence of an IPL file to use as ROM, if it is present then load to memory
        File iplFile = new File(IPL_FILE);
        if (iplFile.exists()) {
            computer.loadROM(iplFile);
        }

        FrontPanel frontPanel = new FrontPanel(computer);
        frontPanel.setVisible(true);
    }
}