package ui.panels;

import javax.swing.JPanel;
import config.Config;

public class ControlPanel extends JPanel {
    public ControlPanel() {
        super();
        setOpaque(false);
                setBorder(javax.swing.BorderFactory.createEmptyBorder(Config.UI_MARGIN * 2,
                Config.UI_MARGIN * 2,
                Config.UI_MARGIN * 2,
                Config.UI_MARGIN * 2));
    }
}
