import java.awt.event.*;
import javax.swing.*;

public class Player extends JComponent{
    //Player Attributes
    boolean alive = true;
    int startVelocity = 0;
    //name = ...
    int VELOCITY = 5; //Subject to change

    public static void Accelerate(){

    }

    public static void Velocity() {

    }
    public void KeyboardInput(){
        addKeyListener(new KeyAdapter() {
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
                }
            }

            public void keyReleased(KeyEvent e) {
            }
        });
    }
}
