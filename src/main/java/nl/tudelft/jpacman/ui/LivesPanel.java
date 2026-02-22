package nl.tudelft.jpacman.ui;

import java.awt.GridLayout;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

import nl.tudelft.jpacman.level.Player;

/**
 * A panel consisting of a column for each player, with the numbered players on
 * top and their respective remaining lives underneath.
 *
 *
 */
public class LivesPanel extends JPanel {

    /**
     * Default serialisation ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The map of players and the labels their remaining lives are on.
     */
    private final Map<Player, JLabel> livesLabels;

    /**
     * Creates a new lives panel with a column for each player.
     *
     * @param players
     *            The players to display the lives of.
     */
    public LivesPanel(List<Player> players) {
        super();
        assert players != null;

        setLayout(new GridLayout(1, players.size()));

        livesLabels = new LinkedHashMap<>();
        for (Player player : players) {

            JLabel livesLabel = new JLabel("Lives: " + player.getLives(), JLabel.CENTER);
            livesLabels.put(player, livesLabel);
            add(livesLabel);
        }
    }

    /**
     * Refreshes the lives of the players.
     */
    protected void refresh() {
        for (Map.Entry<Player, JLabel> entry : livesLabels.entrySet()) {
            Player player = entry.getKey();
            String lives= "";
            if (!player.isAlive()) {
                lives = "Lives: 0";
                entry.getValue().setText(lives);
            }
            else{
                lives = "Lives: " + player.getLives();
                entry.getValue().setText(lives);
            }
        }
    }
}
