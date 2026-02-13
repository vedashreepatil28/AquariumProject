import java.awt.*;

public class GameOver {
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the object in the x direction
    public int dy;                    //the speed of the object in the y direction
    public int width;                 // variable for the width of the object
    public int height;                 // variable for the height of the object
    public boolean isAlive;            //a boolean to denote if the object is alive or dead.
   

    

    // Constructor Definition
    // A constructor builds the object when called and sets variable values.

    //This is a  constructor that takes 2 parameters.  This allows us to specify the object's position when we build it.
    // if you put in an int and an int the program will use this constructor.
    public GameOver(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx =0;
        dy =0;
        width = 600;
        height = 400;
        isAlive = true;

    } 


}
