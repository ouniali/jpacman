package nl.tudelft.jpacman.level;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {
    /**
     * Do we get the correct state of the player?
     */
    @Test
    void testIsAlive() {
        PacManSprites newPacManSprites = new PacManSprites();
        PlayerFactory newPlayerFactory = new PlayerFactory(newPacManSprites);
        Player newPlayer = newPlayerFactory.createPacMan();
        assertThat(newPlayer.isAlive()).isTrue();
    }
}
