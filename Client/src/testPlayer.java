import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

public class testPlayer {

    public void move(){

    }

    public static void main(String[] args) {
            LocalPlayer player1 = new LocalPlayer
            (100, 100, 0, 5, Color.CYAN, "Player1", "DOWN", true);
    LocalPlayer player2 = new LocalPlayer
            (200, 100, 0, 5, Color.RED, "Player2", "DOWN", true);
    LocalPlayer player3 = new LocalPlayer
            (300, 100, 0, 5, Color.YELLOW, "Player3", "DOWN", true);
        System.out.println(player1);
        System.out.println(player2);
        System.out.println(player3);
    }
}
