package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.npc.Ghost;

/**
 * Handles collisions between a Player and a Ghost.
 */
public class PlayerGhostCollisionHandler implements CollisionInteractionMap.CollisionHandler<Player, Ghost> {

    @Override
    public void handleCollision(Player player, Ghost ghost) {
        player.decreaseLife();
        if (!player.isAlive()) {
            player.setKiller(ghost);
        }
    }
}
