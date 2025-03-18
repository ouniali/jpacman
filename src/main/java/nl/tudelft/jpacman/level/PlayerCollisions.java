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

    /**
     * Create a simple player-based collision map, informing the
     * point calculator about points to be added.
     *
     * @param pointCalculator
     *             Strategy for calculating points.
     */
    public PlayerCollisions(PointCalculator pointCalculator) {
        this.pointCalculator = pointCalculator;
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
        player.decrementLives(); // Réduit le nombre de vies du joueur
        if (player.getLives() > 0) {
            // Le joueur a encore des vies, il respawn (par exemple, positionner le joueur à un endroit de départ)
            player.respawn();
            System.out.println("Le joueur respawn avec " + player.getLives() + " vies restantes.");
        } else {
            // Le joueur n'a plus de vies, il meurt
            pointCalculator.collidedWithAGhost(player, ghost); // Calculer les points ou autres conséquences
            player.setAlive(false); // Le joueur est désormais mort
            player.setKiller(ghost); // Le fantôme est la cause de la mort
            System.out.println("Le joueur est mort ! Il a été tué par " + ghost);
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
