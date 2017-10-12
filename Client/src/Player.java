import java.awt.event.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Player extends JComponent{

    // Player Attributes
    Color color = Color.RED;
    boolean alive = true;
    boolean jetWall = true;

    // Initial Conditions
    int initialVelocity = 0;
    int velocity = 5; //Subject to change

    public Player() {
        velocity = 0;
        alive
    }

    public static void Accelerate(){
        velocity =
    }

    public static void Velocity() {

    }
    public void keyPressed(KeyEvent e) {
        if (alive) {
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            // Do Something
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            // Do Something
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            // Do Something
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            // Do Something
        } else if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            // Do Something
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    }
}
