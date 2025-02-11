package nl.tudelft.jpacman.ui;

import nl.tudelft.jpacman.level.Player;
import java.util.List;

/**
 * Panel to display the player's score.
 */
public class ScorePanel extends PlayerInfoPanel {

    private static final long serialVersionUID = 1L;

    /**
     * Default score formatter.
     */
    public static final InfoFormatter DEFAULT_SCORE_FORMATTER =
        (Player player) -> String.format("Score: %3d", player.getScore());

    /**
     * Creates a score panel.
     *
     * @param players The players to display scores for.
     */
    public ScorePanel(List<Player> players, InfoFormatter formatter) {
        super(players, formatter != null ? formatter : DEFAULT_SCORE_FORMATTER, "Player");
    }
}
