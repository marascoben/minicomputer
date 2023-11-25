import java.io.File;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import components.Computer;
import components.ROM;
import ui.FrontPanel;
import util.LogFormat;

public class Main {

    public static String IPL_FILE = "IPL.txt";

    public static String LOG_FILE = "output%g.log";

    public static Logger LOGGER = Logger.getLogger("");

    public static void main(String[] args) {

        System.setProperty("apple.awt.application.name", "Minicomputer");

        // Setup Logging
        try {

            LOGGER.setUseParentHandlers(false);
            FileHandler fh = new FileHandler(LOG_FILE, true);
            fh.setFormatter(new LogFormat());
            LOGGER.addHandler(fh);

        } catch (Exception e) {
            // TODO: handle exception
        }

        Computer computer;

        // Check for the presence of an IPL file to use as ROM, if it is present then
        // load to memory
        File iplFile = new File(IPL_FILE);
        if (iplFile.exists()) {
            computer = new Computer(new ROM(iplFile));
        } else {
            computer = new Computer();
        }

        FrontPanel window = new FrontPanel(computer);
        window.setVisible(true);
    }
}