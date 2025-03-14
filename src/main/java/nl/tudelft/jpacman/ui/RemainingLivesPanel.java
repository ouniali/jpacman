package nl.tudelft.jpacman.ui;

import java.awt.GridLayout;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

import nl.tudelft.jpacman.level.Player;

/**
 * A panel consisting of a column for each player, displaying only the remaining lives.
 *
 * @author Jeroen Roosen
 *
 */
public class RemainingLivesPanel extends JPanel {

    /**
     * Default serialisation ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The default way in which remaining lives are shown.
     */
    public static final RemainingLivesFormatter DEFAULT_LIVES_FORMATTER =
        (Player player) -> String.format("Remaining Lives: %3d", player.getRemainingLives());

    /**
     * The way to format remaining lives information.
     */
    private transient RemainingLivesFormatter remainingLivesFormatter;

    /**
     * Map to associate each player with their remaining lives label.
     */
    private final transient Map<Player, JLabel> livesLabels;

    /**
     * Creates a new remaining lives panel with a column for each player.
     *
     * @param players The players to display the remaining lives of.
     */
    public RemainingLivesPanel(List<Player> players) {
        this.remainingLivesFormatter = DEFAULT_LIVES_FORMATTER;
        assert players != null;

        setLayout(new GridLayout(2, players.size())); // Two rows: player name, remaining lives

        // Add a label for each player (player number)
        for (int i = 1; i <= players.size(); i++) {
            add(new JLabel("Player " + i, 0));
        }

        // Initialize the map for remaining lives labels
        livesLabels = new LinkedHashMap<>();
        for (Player player : players) {
            JLabel livesLabel = new JLabel("0", 0);  // Start with a default "0"
            livesLabels.put(player, livesLabel);
            add(livesLabel);  // Add the label to the panel
        }
    }

    /**
     * Refreshes the remaining lives of the players.
     * This updates the lives displayed for each player.
     */
    protected void refresh() {
        for (Map.Entry<Player, JLabel> entry : livesLabels.entrySet()) {
            Player player = entry.getKey();
            String lives = remainingLivesFormatter.format(player);  // Format remaining lives
            entry.getValue().setText(lives);  // Update the label with the formatted lives
        }
    }

    /**
     * Provides a way to format the remaining lives of a given player.
     */
    public interface RemainingLivesFormatter {

        /**
         * Format the remaining lives of a given player.
         * @param player The player and its remaining lives.
         * @return Formatted remaining lives.
         */
        String format(Player player);
    }

    /**
     * Let the remaining lives panel use a dedicated remaining lives formatter.
     * @param remainingLivesFormatter Remaining lives formatter to be used.
     */
    public void setRemainingLivesFormatter(RemainingLivesFormatter remainingLivesFormatter) {
        assert remainingLivesFormatter != null; // Ensure the formatter is not null
        this.remainingLivesFormatter = remainingLivesFormatter;
    }
}
