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
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.crypto.BadPaddingException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.tools.Tool;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.security.Key;

//*******************************************************************************
// Class Definition Section



// control paddles
    // right paddle
    // left paddle
//todo: make ball bounce off of paddle
    /// change dx - hitbox intersect
// if ball goes off of y play game over screen


public class BasicGameApp implements Runnable, KeyListener {

    //Variable Definition Section
    //Declare the variables used in the program
    //You can set their initial values too

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 700;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;

    public BufferStrategy bufferStrategy;
    public Image backgroundpic;
    public Image paddlepic;
    public Image ballpic;
    public int hits;
    public Ball[] balls;
    public int count;

    //Declare the objects used in the program
    //These are things that are made up of more than one variable type
    private Paddles leftPaddle;
    private Paddles rightPaddle;
    private Ball mainBall;


    // Main method definition
    // This is the code that runs first and automatically
    public static void main(String[] args) {

        BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
        new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method


    }


    // Constructor Method
    // This has the same name as the class
    // This section is the setup portion of the program
    // Initialize your variables and construct your program objects here.
    public BasicGameApp() {


            setUpGraphics();


            balls = new Ball[5];
            for (int x = 0; x < balls.length; x++) {
                balls[x] = new Ball(4, 2);
        }


        // load graphics
        backgroundpic = Toolkit.getDefaultToolkit().getImage("backgroundpic.jpg");
        paddlepic = Toolkit.getDefaultToolkit().getImage("paddle.png");
        ballpic = Toolkit.getDefaultToolkit().getImage("ball.png");

        // always on
        leftPaddle = new Paddles(12,12);
        rightPaddle = new Paddles(965,12);
        mainBall = new Ball(5,5);
        // changes
        hits = 0;
        count = 0;
        mainBall.isAlive = true;





    }


    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time) {
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
        // be able to react to keys
        canvas.addKeyListener(this);

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


        //draw the image of the
           g.drawImage(backgroundpic, 0, 0, WIDTH, HEIGHT, null);
        g.drawImage(paddlepic, leftPaddle.xpos, leftPaddle.ypos, leftPaddle.width, leftPaddle.height, null);
        g.drawImage(paddlepic, rightPaddle.xpos, rightPaddle.ypos, rightPaddle.width, rightPaddle.height, null);
        g.drawRect(leftPaddle.hitbox.x, leftPaddle.hitbox.y, leftPaddle.hitbox.width, leftPaddle.hitbox.height);
        g.drawRect(rightPaddle.hitbox.x, rightPaddle.hitbox.y, rightPaddle.hitbox.width, rightPaddle.hitbox.height);


        // render mainball
        if (mainBall.isAlive) {
            g.drawImage(ballpic, mainBall.xpos, mainBall.ypos, mainBall.width, mainBall.height, null);
            g.drawRect(mainBall.hitbox.x, mainBall.hitbox.y, mainBall.hitbox.width, mainBall.hitbox.height);
        }

        else{
            g.drawString("GAME OVER",500,350);
        }

        // render array balls


        for (int x = 0; x< balls.length; x++){
            if (balls[x].isAlive = true){
           g.drawImage(ballpic, mainBall.xpos, mainBall.ypos, mainBall.width, mainBall.height, null);
           g.drawRect(mainBall.hitbox.x, mainBall.hitbox.y, mainBall.hitbox.width, mainBall.hitbox.height);
       }}
        g.dispose();

        bufferStrategy.show();
    }

    @Override
    public void run() {
        while (true) {

            bounce();
            render();
            moveThings();  //move all the game objects
            pause(20); // sleep for 10 ms



        }

    }

    public void moveThings(){
        mainBall.move();
        rightPaddle.move();
        leftPaddle.move();
        for(int x = 0; x< balls.length; x++){
         balls[x].move();}

    }


    // bounicng off walls and paddles
    public void bounce(){
        if (mainBall.hitbox.intersects(leftPaddle.hitbox)){
            mainBall.dx = -mainBall.dx;
            hits = hits +1;
            System.out.println(hits);

        }
        if (mainBall.hitbox.intersects(rightPaddle.hitbox)){
            mainBall.dx = -mainBall.dx;
            hits = hits +1;
            System.out.println(hits);
        }

        if (hits >= 5){
            count = count +1;
            System.out.println("count:" + count);
            hits = 0;

            for (int x =0; x<=count; x++){
                balls[x].isAlive = true;
            }
}



        }


        /// keytyped

        @Override
        //not this one
        public void keyTyped(KeyEvent e) {

        }

        /// keypressed

        @Override
        public void keyPressed(KeyEvent e) {
            // up down, left right
            if (e.getKeyCode() == 38){
                rightPaddle.isNorth = true;
            }

            if (e.getKeyCode() == 40){
                rightPaddle.isSouth = true;
            }
            if (e.getKeyCode() == 87){
                leftPaddle.isNorth = true;
            }

            if (e.getKeyCode() == 83){
                leftPaddle.isSouth = true;
            }



        }


        /// KeyReleased

        @Override
        //not this one
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == 38){
                rightPaddle.isNorth = false;
            }

            if (e.getKeyCode() == 40){
                rightPaddle.isSouth = false;
            }
            if (e.getKeyCode() == 87){
                leftPaddle.isNorth = false;
            }

            if (e.getKeyCode() == 83){
                leftPaddle.isSouth = false;
            }


        }
    }
