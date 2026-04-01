import java.awt.*;

public class Ball {


    public int xpos;
    public int ypos;
    public int dx;
    public int dy;
    public int width;
    public int height;
    public boolean isAlive; //a boolean to denote if the hero is alive or dead.
    public Rectangle hitbox;


    public Ball (int dx2, int dy2){

        xpos = 500;
        ypos = 350;
        dx = dx2;
        dy = dy2;
        width =20;
        height = 20;
        isAlive = true;
        hitbox = new Rectangle(xpos, ypos, width, height);
    }

    public void move(){

        //bouncing
            if (ypos>=675) {
               dy = -dy;

            }
            if (ypos <= 6){
                dy = -dy;

            }

            // if offscreen stop
            if (xpos > 1000){
               isAlive = false;
            }
            if (xpos < 0){
               isAlive= false;
            }
            xpos = xpos + dx;
            ypos = ypos + dy;

            hitbox = new Rectangle(xpos,ypos, width, height);
        }



    }

