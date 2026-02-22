package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.board.Unit;
import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.points.PointCalculator;

/**
 * A simple implementation of a collision map for the JPacman player.
 * <p>
 * It uses a number of instanceof checks to implement the multiple dispatch for the 
 * collisionmap. For more realistic collision maps, this approach will not scale,
 * and the recommended approach is to use a {@link CollisionInteractionMap}.
 *
 * @author Arie van Deursen, 2014
 *
 */

public class PlayerCollisions implements CollisionMap {

    private PointCalculator pointCalculator;

    private final Level level; // Référence au niveau


    /**
     * Create a simple player-based collision map, informing the
     * point calculator about points to be added.
     *
     * @param pointCalculator
     *             Strategy for calculating points.
     */
    public PlayerCollisions(PointCalculator pointCalculator, Level level) {
        this.pointCalculator = pointCalculator;
        this.level = level;
    }

    @Override
    public void collide(Unit mover, Unit collidedOn) {
        if (mover instanceof Player) {
            playerColliding((Player) mover, collidedOn);
        }
        else if (mover instanceof Ghost) {
            ghostColliding((Ghost) mover, collidedOn);
        }
        else if (mover instanceof Pellet) {
            pelletColliding((Pellet) mover, collidedOn);
        }
    }

    private void playerColliding(Player player, Unit collidedOn) {
        if (collidedOn instanceof Ghost) {
            playerVersusGhost(player, (Ghost) collidedOn);
        }
        if (collidedOn instanceof Pellet) {
            playerVersusPellet(player, (Pellet) collidedOn);
        }
    }

    private void ghostColliding(Ghost ghost, Unit collidedOn) {
        if (collidedOn instanceof Player) {
            playerVersusGhost((Player) collidedOn, ghost);
        }
    }

    private void pelletColliding(Pellet pellet, Unit collidedOn) {
        if (collidedOn instanceof Player) {
            playerVersusPellet((Player) collidedOn, pellet);
        }
    }


    /**
     * Actual case of player bumping into ghost or vice versa.
     *
     * @param player
     *          The player involved in the collision.
     * @param ghost
     *          The ghost involved in the collision.
     */
    public void playerVersusGhost(Player player, Ghost ghost) {
        pointCalculator.collidedWithAGhost(player, ghost);

        if (player.getLives() > 1) {
            player.loseLife();
            player.occupy(player.getInitialPosition()); // Réapparaître
            level.resetGhosts(); // Réinitialiser les fantômes après la perte d'une vie
            return;
        }
        player.setAlive(false);
        player.setKiller(ghost);
    }



    private void respawnPlayer(Player player) {
        if (player.getInitialPosition() != null) {
            player.occupy(player.getInitialPosition());
        }
    }


    /**
     * Actual case of player consuming a pellet.
     *
     * @param player
     *           The player involved in the collision.
     * @param pellet
     *           The pellet involved in the collision.
     */
    public void playerVersusPellet(Player player, Pellet pellet) {
        pointCalculator.consumedAPellet(player, pellet);
        pellet.leaveSquare();
    }

}
