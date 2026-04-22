import java.awt.*;

public class Paddles {


    public int xpos;
    public int ypos;
    public int dx;
    public int dy;
    public int width;
    public int height;
    public boolean isAlive; //a boolean to denote if the hero is alive or dead.
    public Rectangle hitbox;
    public boolean isNorth;
    public boolean isSouth;
    public int startypos;


    public Paddles (int pxpos, int pypos, int sypos){

        xpos = pxpos;
        ypos = pypos;
        startypos = sypos;
        width =24;
        height = 133;
        isAlive = true;
        hitbox = new Rectangle(xpos, ypos, width, height);
        isNorth = false;
        isSouth = false;

    }

    public void move(){

        // moving
        if (isNorth == true){
            dy = -6;
        }
        if (isSouth == true){
            dy = 6;
        }
        if (isSouth == false && isNorth == false){
            dy = 0;
        }

        //wrapping
        if (ypos > 700){
            ypos = 0;
        }
        if (ypos <0){
            ypos = 700;
        }

        xpos = xpos + dx;
        ypos = ypos + dy;
        hitbox = new Rectangle(xpos,ypos, width, height);



    }

}
