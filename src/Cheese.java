import java.awt.*;

public class Cheese {
    //VARIABLE DECLARATION SECTION
    public String name;                //holds the name of the object
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the object in the x direction
    public int dy;                    //the speed of the object in the y direction
    public int width;                   // variable for the width of the object
    public int height;                 // variable for the height of the object
    public boolean isAlive;             //a boolean to denote if the object is alive or dead.
    public Rectangle hitbox;            //a hit box for the cheese
    public boolean isCrashing;          //boolean to regulate whether the object is crashing


    // Constructor Definition
    // A constructor builds the object when called and sets variable values.

    //This is a  constructor that takes 2 parameters.  This allows us to specify the object's position when we build it.        
    // if you put in an int and an int the program will use this constructor.
    public Cheese(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx =7;
        dy =4;
        width = 60;
        height = 60;
        isAlive = true;
        hitbox = new Rectangle(xpos, ypos, width, height);


    }

    //The move method.  Everytime this is run (or "called") the object's x position and y position change by dx and dy
    // this also shows how the object will move, in this example it teleport to the opposite area
    public void move() {
        if (xpos>1000){dx=-dx;} //bounce off right wall
        if (ypos>700){dy=-dy;} //bounce off bottom wall
        if (xpos<0){dx=-dx;} // bounce off left wall
        if (ypos<0){dy=-dy;} //bounce off top wall

        xpos = xpos + dx;   //defines xpos
        ypos = ypos + dy;   //defines ypos
        hitbox = new Rectangle(xpos, ypos, width, height); //creates a hitbox with parameters that define the shape and size.


    }

}
