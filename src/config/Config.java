package config;

import java.awt.Font;

public final class Config {

    // Enables/disabled debugging artifacts and output for the UI
    public static final boolean   UI_DEBUG = false;

    public static final double    UI_SCALE_WIDTH = 0.65;

    public static final double    UI_SCALE_HEIGHT = 0.65;

    public static final int       UI_MARGIN = 6;

    public static final int       UI_FONT_SIZE = 12;

    public static final Font      UI_FONT_MONOSPACE = new Font(Font.MONOSPACED, Font.BOLD, Config.UI_FONT_SIZE);

}
