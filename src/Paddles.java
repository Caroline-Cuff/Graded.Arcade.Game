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


    public Paddles (int pxpos, int pypos){

        xpos = pxpos;
        ypos = pypos;
        width =24;
        height = 133;
        isAlive = true;
        hitbox = new Rectangle(xpos, ypos, width, height);
        isNorth = false;
        isSouth = false;

    }

    public void move(){
        if (isNorth == true){
            dy = -4;
        }
        if (isSouth == true){
            dy = 4;
        }
        if (isSouth == false && isNorth == false){
            dy = 0;
        }

        xpos = xpos + dx;
        ypos = ypos + dy;
        hitbox = new Rectangle(xpos,ypos, width, height);



    }

}
