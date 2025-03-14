package nl.tudelft.jpacman.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import nl.tudelft.jpacman.game.Game;
import nl.tudelft.jpacman.ui.ScorePanel.ScoreFormatter;
import nl.tudelft.jpacman.ui.RemainingLivesPanel.RemainingLivesFormatter;


/**
 * The default JPacMan UI frame. The PacManUI consists of the following
 * elements:
 *
 * <ul>
 * <li>A score panel at the top, displaying the score of the player(s).
 * <li>A board panel, displaying the current level, i.e. the board and all units
 * on it.
 * <li>A button panel, containing all buttons provided upon creation.
 * </ul>
 *
 * @author Jeroen Roosen 
 *
 */
public class PacManUI extends JFrame {

    /**
     * Default serialisation UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The desired frame rate interval for the graphics in milliseconds, 40
     * being 25 fps.
     */
    private static final int FRAME_INTERVAL = 40;

    /**
     * The panel displaying the player scores.
     */
    private final ScorePanel scorePanel;

    /**
     * The panel displaying the remaining lives of the players.
     */
    private final RemainingLivesPanel remainingLivesPanel;

    /**
     * The panel displaying the game.
     */
    private final BoardPanel boardPanel;

    /**
     * Creates a new UI for a JPacman game.
     *
     * @param game
     *            The game to play.
     * @param buttons
     *            The map of caption-to-action entries that will appear as
     *            buttons on the interface.
     * @param keyMappings
     *            The map of keyCode-to-action entries that will be added as key
     *            listeners to the interface.
     * @param scoreFormatter
     *            The formatter used to display the current score.
     * @param remainingLivesFormatter
     *            The formatter used to display the remaining
     *            lives of the players. If provided, it customizes
     *            how the remaining lives are displayed.
     */
    public PacManUI(final Game game, final Map<String, Action> buttons,
                    final Map<Integer, Action> keyMappings,
                    ScoreFormatter scoreFormatter,
                    RemainingLivesFormatter remainingLivesFormatter) {
        super("JPacman");
        assert game != null;
        assert buttons != null;
        assert keyMappings != null;

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        PacKeyListener keys = new PacKeyListener(keyMappings);
        addKeyListener(keys);

        JPanel buttonPanel = new ButtonPanel(buttons, this);

        scorePanel = new ScorePanel(game.getPlayers());
        if (scoreFormatter != null) {
            scorePanel.setScoreFormatter(scoreFormatter);
        }

        // Same ScorePanel class, but displays remaining live
        remainingLivesPanel = new RemainingLivesPanel(game.getPlayers());
        if (remainingLivesFormatter != null) { // Apply remaining lives formatter if provided
            remainingLivesPanel.setRemainingLivesFormatter(remainingLivesFormatter);
        }


        boardPanel = new BoardPanel(game);

        Container contentPanel = getContentPane();
        contentPanel.setLayout(new BorderLayout());

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BorderLayout());

        northPanel.add(scorePanel, BorderLayout.WEST);
        northPanel.add(remainingLivesPanel, BorderLayout.EAST);
        contentPanel.add(northPanel, BorderLayout.NORTH);
        contentPanel.add(boardPanel, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        pack();
    }

    /**
     * Starts the "engine", the thread that redraws the interface at set
     * intervals.
     */
    public void start() {
        setVisible(true);
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(this::nextFrame, 0, FRAME_INTERVAL, TimeUnit.MILLISECONDS);
    }

    /**
     * Draws the next frame, i.e. refreshes the scores and game.
     */
    private void nextFrame() {
        boardPanel.repaint();
        scorePanel.refresh();
        remainingLivesPanel.refresh(); // Refresh lives panel as well
    }
}
