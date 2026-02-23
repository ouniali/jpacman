package nl.tudelft.jpacman.ui;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import nl.tudelft.jpacman.level.Player;

public abstract class PlayerInfoPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private final Map<Player, JLabel> infoLabels;

    private final InfoFormatter infoFormatter;

    public PlayerInfoPanel(List<Player> players, InfoFormatter formatter, String title) {
        super();
        assert players != null;
        assert formatter != null;

        this.infoFormatter = formatter;
        setLayout(new GridLayout(2, players.size()));

        for (int i = 1; i <= players.size(); i++) {
            add(new JLabel(title + " " + i, JLabel.CENTER));
        }
        infoLabels = new LinkedHashMap<>();
        for (Player player : players) {
            JLabel label = new JLabel(formatter.format(player), JLabel.CENTER);
            infoLabels.put(player, label);
            add(label);
        }
    }

    protected void refresh() {
        for (Map.Entry<Player, JLabel> entry : infoLabels.entrySet()) {
            Player player = entry.getKey();
            entry.getValue().setText(infoFormatter.format(player));
        }
    }

    @FunctionalInterface
    public interface InfoFormatter {
        String format(Player player);
    }
}
