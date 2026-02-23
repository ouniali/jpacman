package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.points.PointCalculator;
import nl.tudelft.jpacman.board.Unit;

/**
 * A player-based collision map that now delegates all collision logic
 * to the CollisionInteractionMap.
 */
public class PlayerCollisions implements CollisionMap {

    private final CollisionMap collisionInteractionMap;

    /**
     * Creates a collision map that delegates to CollisionInteractionMap.
     *
     * @param pointCalculator The point calculation strategy.
     */
    public PlayerCollisions(PointCalculator pointCalculator) {
        this.collisionInteractionMap = new DefaultPlayerInteractionMap(pointCalculator);
    }




    @Override
    public <C1 extends Unit, C2 extends Unit> void collide(C1 collider, C2 collidee) {
        collisionInteractionMap.collide(collider, collidee);
    }
}
