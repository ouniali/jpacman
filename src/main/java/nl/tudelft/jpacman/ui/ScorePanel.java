package nl.tudelft.jpacman.ui;

import java.awt.GridLayout;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static javax.swing.SwingConstants.CENTER;
import javax.swing.JLabel;
import javax.swing.JPanel;

import nl.tudelft.jpacman.level.Player;

/**
 * A panel consisting of a column for each player, with the numbered players on
 * top and their respective scores underneath.
 *
 * @author Jeroen Roosen 
 *
 */
public class ScorePanel extends JPanel {

    /**
     * Default serialisation ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The map of players and the labels their scores are on.
     */
    private final Map<Player, JLabel> scoreLabels;

    /**
     * The map of players and the labels their lives are on.
     */
    private transient  final Map<Player, JLabel> livesLabels;

    /**
     * The default way in which the score is shown.
     */
    public static final NumberFormatter DEFAULT_SCORE_FORMATTER =
        (Player player) -> String.format("Score: %3d", player.getScore());

    /**
     * The way to format the score information.
     */
    public static final NumberFormatter DEFAULT_LIVES_FORMATTER =
        (Player player) -> String.format("Lives: %d", player.getLives());

    /**
     * The way to format the score and lives information.
     */
    private NumberFormatter scoreFormatter = DEFAULT_SCORE_FORMATTER;
    private NumberFormatter livesFormatter = DEFAULT_LIVES_FORMATTER;
    /**
     * Creates a new score panel with a column for each player.
     *
     * @param players
     *            The players to display the scores of.
     */
    public ScorePanel(List<Player> players) {
        super();
        assert players != null;

        setLayout(new GridLayout(3, players.size()));

        for (int i = 1; i <= players.size(); i++) {
            add(new JLabel("Player " + i, CENTER));
        }

        livesLabels = new LinkedHashMap<>();
        for (Player player : players) {
            JLabel livesLabel = new JLabel("3", CENTER);
            livesLabels.put(player, livesLabel);
            add(livesLabel);
        }

        scoreLabels = new LinkedHashMap<>();
        for (Player player : players) {
            JLabel scoreLabel = new JLabel("0", CENTER);
            scoreLabels.put(player, scoreLabel);
            add(scoreLabel);
        }
    }

    /**
     * Refreshes the scores of the players.
     */
    protected void refresh() {
        for (Map.Entry<Player, JLabel> entry : scoreLabels.entrySet()) {
            Player player = entry.getKey();
            String score = "";
            if (!player.isAlive()) {
                score = "You died. ";
            }
            score += scoreFormatter.format(player);
            entry.getValue().setText(score);
            String lives = livesFormatter.format(player);
            livesLabels.get(player).setText(lives);
        }
    }

    /**
     * Provide means to format the score for a given player.
     */
    public interface NumberFormatter extends Serializable {

        /**
         * Format the score of a given player.
         * @param player The player and its score
         * @return Formatted score.
         */
        String format(Player player);
    }

    /**
     * Let the score panel use a dedicated score formatter.
     * @param scoreFormatter Score formatter to be used.
     */
    public void setNumberFormatter(NumberFormatter scoreFormatter) {
        assert scoreFormatter != null;
        this.scoreFormatter = scoreFormatter;
    }
}
