package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.points.PointCalculator;

/**
 * Handles collisions between a Player and a Pellet.
 */
public class PlayerPelletCollisionHandler implements CollisionInteractionMap.CollisionHandler<Player, Pellet> {

    private final PointCalculator pointCalculator;

    public PlayerPelletCollisionHandler(PointCalculator pointCalculator) {
        this.pointCalculator = pointCalculator;
    }

    @Override
    public void handleCollision(Player player, Pellet pellet) {
        pointCalculator.consumedAPellet(player, pellet);
        pellet.leaveSquare();
    }
}
