import java.awt.Graphics;
import java.util.ArrayList;

public abstract class Object {
    // Co-ordinates
    int x;
    int y;

    // Game Zone
    int width;
    int height;

    // Velocity in pixels per move
    int velocityX;
    int velocityY;

    // Boundaries
    int xBoundary;
    int yBoundary;

    public Object(){

    }


    // Accelerate Player
    public abstract void accelerate();

    // Draws Shapes
    public abstract void draw(Graphics g);

    // Return Player Status
    public abstract boolean getAlive();
}
