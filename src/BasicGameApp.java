//Basic Game Application
//Version 2
// Basic Object, Image, Movement
// Astronaut moves to the right.
// Threaded

//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


// Class Definition Section

public class BasicGameApp implements Runnable {

   //Variable Definition Section
   //Declare the variables used in the program 

   //Sets the width and height of the program window
	final int WIDTH = 1000;
	final int HEIGHT = 700;

   //Declare the variables needed for the graphics
	public JFrame frame;
	public Canvas canvas;
   public JPanel panel;

    //all images of all characters and background
	public BufferStrategy bufferStrategy;
    public Image backgroundPic;
    public Image hearts;
    public Image heart2;
	public Image kittyPic;
    public Image ratPic;
    public Image foodPic;
    public Image GameOver1;
    public Image goldFish;

   //Declares the objects used in the program
	private Cat kitty;
    private Mice rat;
    private Cheese food;
    private GameOver GameOver2;
    private Fish Fish1;

    // variable for counter method
    public int x;


   // Main method definition
   // This is the code that runs first and automatically
	public static void main(String[] args) {
		BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
		new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method  
	}


   // main Constructor Method

    public BasicGameApp() {
      
      setUpGraphics();
       
      //variable and objects
      //create (construct) the objects needed for the game and load up 
        backgroundPic = Toolkit.getDefaultToolkit().getImage("background.png");//load the picture

        hearts = Toolkit.getDefaultToolkit().getImage("hearts.png");
        heart2 = Toolkit.getDefaultToolkit().getImage("hearts.png");

        kittyPic = Toolkit.getDefaultToolkit().getImage("cat.png"); //load the picture
		kitty = new Cat(450,600);

        ratPic = Toolkit.getDefaultToolkit().getImage("rat.png"); //load the picture
        rat = new Mice(400,100);

        foodPic = Toolkit.getDefaultToolkit().getImage("Cheese.png"); //load the picture
        food = new Cheese(400,100);

        goldFish = Toolkit.getDefaultToolkit().getImage("Fish.png");
        Fish1 = new Fish(10,10); //the size of the images



	}// BasicGameApp()

    //method
    public void endGame(){
        //prints the Game Over image after the rat and the fish die
        if (rat.width<10 && rat.height<10 && Fish1.isAlive==false){
            GameOver1 = Toolkit.getDefaultToolkit().getImage("GameOver.png"); //load the picture
            GameOver2 = new GameOver(600,400);
            rat.isAlive = false;
        }

    }

    //a method that loops all methods while game is runing
	public void run() {

		while (true) {

         moveThings();  //move all the game objects
         render();  // paint the graphics
         pause(20); // sleep for 10 ms
		}
	}


	public void moveThings() {
      //calls all the moving objects and methods in the code so all the methods work and the characters move
		kitty.move();
        rat.move();
        food.move();
        Fish1.move();
        crashing();
        endGame();
        counter();

	}

    //method
    public void counter(){
        //this is a counter to count the points/the number times the cheese intersects with the rat

        if ( rat.hitbox.intersects(food.hitbox)){x=x+5;}
        if ( rat.hitbox.intersects(food.hitbox)){ System.out.println("current points: " + x);}

    }

    //method
    public void crashing(){
        //also defines the interactions and what happens like a character disappears or gets smaller.
        // the rat shrinks everytime it intersects with kitty
        if(kitty.hitbox.intersects(rat.hitbox) && rat.isAlive == true && rat.isCrashing == false){
            System.out.println("HIT!!");
            rat.height = rat.height-5;
            rat.hitbox.height= rat.hitbox.height -5;
            rat.width = rat.width-5;
            rat.hitbox.width= rat.hitbox.width -5;
            rat.isCrashing = true;
        }
        // is is so the rat doesn't shrink too many times in one intersection.
        if (!rat.hitbox.intersects(kitty.hitbox)){
           // System.out.println("no intersection");
            rat.isCrashing = false;
        }

        //when the cheese intersects with the rat, the cheese teleports to another space
        if(food.hitbox.intersects(rat.hitbox) && food.isAlive == true && food.isCrashing == false){
            System.out.println("EATEN");
            food.xpos = 1000-food.xpos;
            food.ypos = 700-food.ypos;
            food.isCrashing = true;
            // rat.isAlive = false;
        }

        //so the cheese doesn't react multiple times in one intersection
        if (!food.hitbox.intersects(rat.hitbox)){
            // System.out.println("no intersection");
            food.isCrashing = false;
        }

        //when kitty and fish intersect, fish disappears and cat gets bigger.
        if( kitty.hitbox.intersects(Fish1.hitbox) && Fish1.isAlive == true && kitty.isAlive == true){
            System.out.println("Fish Died!");
            Fish1.isAlive = false;
            kitty.height = kitty.height+10;
            kitty.hitbox.height= kitty.hitbox.height +10;
            kitty.width = kitty.width+10;
            kitty.hitbox.width= kitty.hitbox.width +10;
        }


    }





    //Pauses or sleeps the computer for the amount specified in milliseconds
   public void pause(int time ){
   		//sleep
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {

			}
   }

   //Graphics setup method
   private void setUpGraphics() {
      frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.
   
      panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
      panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
      panel.setLayout(null);   //set the layout
   
      // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
      // and trap input events (Mouse and Keyboard events)
      canvas = new Canvas();  
      canvas.setBounds(0, 0, WIDTH, HEIGHT);
      canvas.setIgnoreRepaint(true);
   
      panel.add(canvas);  // adds the canvas to the panel.
   
      // frame operations
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
      frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
      frame.setResizable(false);   //makes it so the frame cannot be resized
      frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!
      
      // sets up things so the screen displays images nicely.
      canvas.createBufferStrategy(2);
      bufferStrategy = canvas.getBufferStrategy();
      canvas.requestFocus();
      System.out.println("DONE graphic setup");
   
   }


	//paints things on the screen using bufferStrategy
	private void render() {
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, WIDTH, HEIGHT);

      //draw the image of the characters and all images including background


        g.drawImage(backgroundPic, 0, 0, 1000, 700, null);
        if (Fish1.isAlive == true) {g.drawImage(hearts, 900, 10, 50, 50, null);}
        if (rat.isAlive == true) {g.drawImage(hearts, 845, 10, 50, 50, null);}

		g.drawImage(kittyPic, kitty.xpos, kitty.ypos, kitty.width, kitty.height, null);
        g.drawImage(ratPic, rat.xpos, rat.ypos, rat.width, rat.height, null);
        g.drawImage(foodPic,food.xpos, food.ypos, food.width, food.height, null);
        if(Fish1.isAlive == true){
            g.drawImage(goldFish,Fish1.xpos, Fish1.ypos, Fish1.width, Fish1.height, null);
    }
        //draws the hitbox of all characters

        g.drawRect(food.hitbox.x, food.hitbox.y, food.hitbox.width, food.hitbox.height);
        /*gives a visual of how the hitbox looks */
        g.drawRect(kitty.hitbox.x, kitty.hitbox.y, kitty.hitbox.width, kitty.hitbox.height);
        //so the rat disappears once it is too small and the fish disappears if it interacts with kitty
        if (rat.isAlive==true){g.drawRect(rat.hitbox.x, rat.hitbox.y, rat.hitbox.width, rat.hitbox.height);}
        if(Fish1.isAlive == true){
            g.drawRect(Fish1.hitbox.x, Fish1.hitbox.y, Fish1.hitbox.width, Fish1.hitbox.height);
        }
        g.drawImage(GameOver1,200, 150, 600, 400, null);


        g.setColor(Color.black);
        g.drawString("Points: " + x, 50,50);
		g.dispose();
        //draws points on screen

		bufferStrategy.show();
	}
}