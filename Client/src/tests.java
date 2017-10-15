public class tests {
    int velocityY;
    int velocityX = 0;
    int deltaSpeed = 5;

    public static void main(String[] args) {
        test();
    }

    public static void test() {
        // 0 to 5
        setVelocityX(5);
        System.out.println(this.velocityX);
        //System.out.println(this.velocityY);

        // 5 to 10
        setVelocityX(5);
        System.out.println(this.velocityX);
        //System.out.println(this.velocityY);

        // 10 to 5
        setVelocityX(0);
        System.out.println(this.velocityX);
        //System.out.println(this.velocityY);

        // 5 to 0
        setVelocityX(5);
        System.out.println(this.velocityX);
        //System.out.println(this.velocityY);

        // 10 to 0
        setVelocityX(0);
        System.out.println(this.velocityX);
        //System.out.println(this.velocityY);
    }



    public int morethansetVelocityX(int velocityX) {
        if (!(this.velocityX > 0 && velocityX < 0))
            this.velocityX = velocityX;
        else if (!(this.velocityX < 5 && velocityX > 0))
            this.velocityX = velocityX + deltaSpeed;
        else if (!(this.velocityX < 5 && velocityX > 0))
            this.velocityX = velocityX;
        else if (!(this.velocityX < 0 && velocityX > 0))
            this.velocityX = velocityX;
        else if (!(this.velocityX < 5 && velocityX > 0))
            this.velocityX = velocityX;
        return this.velocityX;
    }

    public void equalssetVelocityX(int velocityX) {
        if (this.velocityX == 0 && velocityX == 5)
            this.velocityX = velocityX;
        else if (this.velocityX == 5 && velocityX == 5)
            this.velocityX = velocityX + deltaSpeed;
        else if (this.velocityX == 10 && velocityX == 0)
            this.velocityX = velocityX;
        else if (this.velocityX == 5 && velocityX == 0)
            this.velocityX = velocityX;
        else if (this.velocityX == 10 && velocityX == 5)
            this.velocityX = velocityX;
    }

    public int setVelocityY(int velocityY) {
        if (!(velocityY > 0 && this.velocityY < 0) && !(velocityY < 0 && this.velocityY > 0)) {
            this.velocityY = velocityY;
        }
        else if ((velocityX > 0 && this.velocityX > 0) && (velocityX < 10 && this.velocityX < 10)){
            this.velocityX = velocityX + deltaSpeed;
        }
        else if ((velocityX > 10)){

        }
        return this.velocityX;
    }
}
