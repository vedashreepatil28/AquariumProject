import java.awt.*;

public class Fish {
    public String name;                //holds the name of the hero
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public int width;
    public int height;
    public boolean isAlive;            //a boolean to denote if the hero is alive or dead.
    public Rectangle hitbox; //a hit box for the cat
    public int randomdx;
    public int randomdy;


    // METHOD DEFINITION SECTION

    // Constructor Definition
    // A constructor builds the object when called and sets variable values.


    //This is a SECOND constructor that takes 3 parameters.  This allows us to specify the hero's name and position when we build it.
    // if you put in a String, an int and an int the program will use this constructor instead of the one above.
    public Fish(int pXpos, int pYpos) {
        randomdx = (int)(Math.random()*10);
        randomdy = (int)(Math.random()*10);

        xpos = pXpos;
        ypos = pYpos;
        dx =randomdx;
        dy =randomdy;
        width = 50;
        height = 50;
        isAlive = true;
        hitbox = new Rectangle(xpos, ypos, width, height);
        move();

    } // constructor

    //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
    public void move() {

        if(xpos>1000){xpos=0-width;} //wrap when hits right wall
        if(xpos<0-width){xpos=1000;} //wrap when hits left wall
        if(ypos>700){ypos=0-height;} //wrap when hits bottom wall
        if(ypos<0-height){ypos=700;} //wrap when it hits top wall



        xpos = xpos + dx;
        ypos = ypos + dy;
        hitbox = new Rectangle(xpos, ypos, width, height);
        hitbox.setBounds(xpos, ypos, width, height);


    }
}
