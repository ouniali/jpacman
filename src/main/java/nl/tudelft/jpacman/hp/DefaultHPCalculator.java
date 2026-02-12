package nl.tudelft.jpacman.hp;

import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.Pellet;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.npc.Ghost;

/**
 * A simple, minimalistic point calculator just
 * adding points for each pellet consumed.
 */
public class DefaultHPCalculator implements HPCalculator {

    @Override
    public void collidedWithAGhost(Player player, Ghost ghost) {
        player.removeHP(1);
    }

    @Override
    public void consumedAPellet(Player player, Pellet pellet) {
        // no hp gained or removed for consuming a pellet
    }

    @Override
    public void pacmanMoved(Player player, Direction direction) {
        // no hp gained or removed for moving
    }
}
