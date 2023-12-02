package ui.windows;

import javax.swing.JFrame;

import config.Config;
import ui.peripheral.Console;
import util.ScreenUtil;

public class ConsoleWindow extends JFrame {

    public Console console = new Console();

    public ConsoleWindow() {
        super("Console");

        setSize(ScreenUtil.getScaledSize(Config.UI_CONSOLE_SCALE_WIDTH, Config.UI_CONSOLE_SCALE_HEIGHT));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

}
