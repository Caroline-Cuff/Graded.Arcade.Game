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

    public Ball (int pxpos, int pypos){

        xpos = pxpos;
        ypos = pypos;
        dx = 3;
        dy = 3;
        width =20;
        height = 20;
        isAlive = true;
        hitbox = new Rectangle(xpos, ypos, width, height);
    }

    public void move(){
            if (ypos>675) {
               dy = -dy;

            }
            if (ypos < 0){
                dy = -dy;

            }
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

