import java.awt.*;

public class Fireball {

    public int xpos;
    public int ypos;
    public int dx;
    public int dy;
    public int width;
    public int height;
    public boolean isAlive; //a boolean to denote if the hero is alive or dead.
    public Rectangle hitbox;
    public boolean isOffscreen;



    public Fireball (int dx2, int dy2){

        xpos = -10;
        ypos = -10;
        dx = dx2;
        dy = dy2;
        width =25;
        height = 25;
        isAlive = false;
        hitbox = new Rectangle(xpos, ypos, width, height);
        isOffscreen = false;
    }



    public void move(){

        //bouncing
        if (ypos>=750) {
            ypos = (int)(Math.random()*750);
            xpos = (int)(Math.random()*1100);

        }
        if (ypos <= -50){
            ypos = (int)(Math.random()*750);
            xpos = (int)(Math.random()*1100);
            dx = -dx;
            dy = -dy;
        }

        // if offscreen stop
        if (xpos > 1000){
            ypos = (int)(Math.random()*750);
            xpos = (int)(Math.random()*1100);
            dx = -dx;
            dy = -dy;

        }
        if (xpos < 0){
            ypos = (int)(Math.random()*750);
            xpos = (int)(Math.random()*1100);
            dx = -dx;
            dy = -dy;

        }
        xpos = xpos + dx;
        ypos = ypos + dy;

        hitbox = new Rectangle(xpos,ypos, width, height);
    }
}
