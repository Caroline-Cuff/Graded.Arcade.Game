import java.awt.*;

public class HiddenWall {

    public int xpos;
    public int ypos;
    public int dx;
    public int dy;
    public int width;
    public int height;
    public boolean isAlive; //a boolean to denote if the hero is alive or dead.
    public Rectangle hitbox;
    public boolean isOffscreen;



    public HiddenWall(){
        xpos = 500;
        ypos = 0;


    }

}
