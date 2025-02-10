package nl.tudelft.jpacman.ui;

import nl.tudelft.jpacman.level.Player;

import javax.swing.*;

public class DeathWindow {

    private Player player;

    public DeathWindow(Player player) {
        if(player.getLives() == 0) {
            JOptionPane.showMessageDialog(null, "Tu as perdu tous tes vies!", "Death", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Tu as perdu une vie! Il vous reste " + player.getLives() + " vies", "Death",JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
