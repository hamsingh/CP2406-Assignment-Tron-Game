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

    // Speed Increment
    int deltaSpeed = 5;

    public Object(int x, int y, int velocityX, int velocityY, int width, int height){
        this.x = x;
        this.y = y;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.width = width;
        this.height = height;
    }

    public void setVelocityX(int velocityX) {
        if (!(velocityX > 0 && this.velocityX < 0) && !(velocityX < 0 && this.velocityX > 0))
            this.velocityX = velocityX;
        /*
        else if (!(this.velocityX < 5 && velocityX > 0))
            this.velocityX = velocityX + deltaSpeed;
        else if (!(this.velocityX < 5 && velocityX > 0))
            this.velocityX = velocityX;
        else if (!(this.velocityX < 0 && velocityX > 0))
            this.velocityX = velocityX;
        else if (!(this.velocityX < 5 && velocityX > 0))
            this.velocityX = velocityX;
        */
    }

    public void setVelocityY(int velocityY) {
        if (!(velocityY > 0 && this.velocityY < 0) && !(velocityY < 0 && this.velocityY > 0))
            this.velocityY = velocityY;
        /*
        else if (!(this.velocityY < 5 && velocityY > 0))
            this.velocityY = velocityY + deltaSpeed;
        else if (!(this.velocityY < 5 && velocityY > 0))
            this.velocityY = velocityY;
        else if (!(this.velocityY < 0 && velocityY > 0))
            this.velocityY = velocityY;
        else if (!(this.velocityY < 5 && velocityY > 0))
            this.velocityY = velocityY;
        */
    }

    public void setBoundary(int xBoundary, int yBoundary) {
        this.xBoundary = xBoundary;
        this.yBoundary = yBoundary;
    }

    public Intersection intersects(Object other) {
        if (other != this) {
            if (other.y - other.height/2 <= y + height/2 &&
                    other.y + other.height/2 >= y - height/2 &&
                    other.x - other.width/2 <= x + width/2 &&
                    other.x + other.width/2 >= x - width/2) {
                return Intersection.UP;
            }
        }
        ArrayList<Shape> pa = other.getPath();
        for (int i = 0; i < pa.size() - 1; i++) {
            Shape k = pa.get(i);
            int x1 = k.getStartX();
            int y1 = k.getStartY();
            int x2 = k.getEndX();
            int y2 = k.getEndY();

            if (y1 == y2) {
                if (Math.abs(y1 - y) <= height/2 &&
                        (x >= Math.min(x1, x2) && x <= Math.max(x1, x2))) {
                    return Intersection.UP;
                }
            } else if (x1 == x2) {
                if (Math.abs(x1 - x) <= width/2 &&
                        (y >= Math.min(y1, y2) && y <= Math.max(y1, y2))) {
                    return Intersection.UP;
                }
            }
        }
        return Intersection.NONE;
    }

    // Move the object at the given velocity.
    public void move() {
        x += velocityX;
        y += velocityY;

        //accelerate();
        clip();
    }

    // Accelerate Player
    public abstract void accelerate();

    // Check if player is in bounds
    public abstract void clip();

    // Draws Shapes
    public abstract void draw(Graphics g);

    // Return Player Status
    public abstract boolean getAlive();

    // Returns the Player's Path as a List of Shapes
    public abstract ArrayList<Shape> getPath();
}
