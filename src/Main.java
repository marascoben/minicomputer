import java.io.File;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

import components.Computer;
import ui.FrontPanel;

public class Main {

    public static String IPL_FILE = "IPL.txt";

    public static String LOG_FILE = "output.log";

    public static void main(String[] args) {

        // Setup Logging
        try {

            FileHandler fh = new FileHandler(LOG_FILE, true);
            fh.setFormatter(new SimpleFormatter() {
                private static final String format = "[%1$tF %1$tT] [%2$-7s] %3$s %n";

                @Override
                public synchronized String format(LogRecord lr) {
                    return String.format(format,
                            new Date(lr.getMillis()),
                            lr.getLevel().getLocalizedName(),
                            lr.getMessage());
                }
            });
            java.util.logging.Logger.getLogger("").addHandler(fh);

        } catch (Exception e) {
            // TODO: handle exception
        }

        Computer computer = new Computer();

        // Check for the presence of an IPL file to use as ROM, if it is present then
        // load to memory
        File iplFile = new File(IPL_FILE);
        if (iplFile.exists()) {
            computer.loadROM(iplFile);
        }

        FrontPanel frontPanel = new FrontPanel(computer);
        frontPanel.setVisible(true);
    }
}