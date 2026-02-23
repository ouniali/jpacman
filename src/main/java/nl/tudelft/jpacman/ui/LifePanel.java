package nl.tudelft.jpacman.ui;

import nl.tudelft.jpacman.level.Player;
import java.util.List;

/**
 * Panel to display the player's remaining lives.
 */
public class LifePanel extends PlayerInfoPanel {

    private static final long serialVersionUID = 1L;

    /**
     * Default life formatter.
     */
    public static final InfoFormatter DEFAULT_LIFE_FORMATTER =
        (Player player) -> String.format("Life: %d", player.getLife());

    /**
     * Creates a life panel.
     *
     * @param players The players to display lives for.
     */
    public LifePanel(List<Player> players, InfoFormatter formatter) {
        super(players, formatter != null ? formatter : DEFAULT_LIFE_FORMATTER, "Player");
    }
}
