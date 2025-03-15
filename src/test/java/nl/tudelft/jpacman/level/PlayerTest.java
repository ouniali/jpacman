package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    @Test
    public void When_Calling_isAlive_For_Alive_Player_Then_Return_True() {
        var pacManSpritesInstance = new PacManSprites();
        var playerFactoryInstance = new PlayerFactory(pacManSpritesInstance);

        var player = playerFactoryInstance.createPacMan();

        assertThat(player.isAlive()).isEqualTo(true);
    }
}
