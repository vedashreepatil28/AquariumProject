import java.awt.*;

public class Cat {

    //VARIABLE DECLARATION SECTION
        public String name;                //holds the name of the object
        public int xpos;                //the x position
        public int ypos;                //the y position
        public int dx;                    //the speed of the object in the x direction
        public int dy;                    //the speed of the object in the y direction
        public int width;                 // variable for the width of the object
        public int height;                 // variable for the height of the object
        public boolean isAlive;            //a boolean to denote if the object is alive or dead.
        public Rectangle hitbox;            //a hit box for the cat



        // Constructor Definition
        // A constructor builds the object when called and sets variable values.

    //This is a  constructor that takes 2 parameters.  This allows us to specify the object's position when we build it.        
    // if you put in an int and an int the program will use this constructor.
        public Cat(int pXpos, int pYpos) {
            xpos = pXpos;
            ypos = pYpos;
            dx =0;
            dy =5;
            width = 100;
            height = 100;
            isAlive = true;
            hitbox = new Rectangle(xpos, ypos, width, height);

        } 

        //The move method.  Everytime this is run (or "called") the object's x position and y position change by dx and dy
        // this also shows how the object will move, in this example it will wrap around all walls
        public void move() {

            if(xpos>1000){xpos=0-width;} //wrap when hits right wall
            if(xpos<0-width){xpos=1000;} //wrap when hits left wall
            if(ypos>700){ypos=0-height;} //wrap when hits bottom wall
            if(ypos<0-height){ypos=700;} //wrap when it hits top wall

                xpos = xpos + dx;   //defines xpos
                ypos = ypos + dy;   //defines ypos
                hitbox = new Rectangle(xpos, ypos, width, height); //creates a hitbox with parameters that define the shape and size.


        }
    }

