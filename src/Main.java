import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import components.Computer;
import components.ROM;
import config.Config;
import ui.FrontPanel;
import util.LogFormat;

public class Main {

    public static Logger LOGGER = Logger.getLogger("");

    public static void main(String[] args) {

        System.setProperty("apple.awt.application.name", "Minicomputer");

        String LOG_FILE = "minicomputer-" + System.currentTimeMillis() + "." + Config.LOG_FILE_EXTENSION;

        // Configure logging for the application
        try {
            LogManager.getLogManager().reset();
            LOGGER.setUseParentHandlers(false);

            FileHandler fh = new FileHandler(LOG_FILE, true);
            fh.setFormatter(new LogFormat());
            LOGGER.addHandler(fh);

            ConsoleHandler ch = new ConsoleHandler();
            ch.setFormatter(new LogFormat());
            LOGGER.addHandler(ch);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Failed to configure logging for the application",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        Computer computer;

        // Check for the presence of an IPL file to use as ROM, if it is present then
        // load to memory
        File iplFile = new File(Config.ROM_IPL_FILENAME);
        if (iplFile.exists()) {
            LOGGER.info("Initalizing minicomputer with IPL file " + iplFile.getName());
            computer = new Computer(new ROM(iplFile));
        } else {
            LOGGER.info("Initalizing minicomputer with no IPL file");
            computer = new Computer();
        }

        FrontPanel window = new FrontPanel(computer);
        window.menu.fileOpenLog.addActionListener(e -> {
            try {
                Desktop.getDesktop().open(new File(LOG_FILE));
            } catch (IOException e1) {
                LOGGER.severe("Failed to open log file: " + e1.getMessage());
                JOptionPane.showMessageDialog(null,
                        "Failed to open log file",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        window.setVisible(true);
    }
}