import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DojoWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DojoWorld extends World
{

    /**
     * Constructor for objects of class DojoWorld.
     * 
     */
    public DojoWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        
        GreenfootImage bg = new GreenfootImage("bg.jpeg"); // Replace with your image file name
        bg.scale(getWidth(), getHeight()); // Scale the image to fit the world dimensions
        setBackground(bg); // Set the resized image as the background
        
        prepareInitial();
    }
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepareInitial()
    {
        addObject(new EnemyPenguin(), 425, 275);
        addObject(new YourPenguin(), 175, 275);
    }
    
    public Card pickCard()
    {
        double cProb = Math.random();
        if(cProb < 0.33){
            return new FireCard();
        }else if(cProb < 0.66){
            return new WaterCard();
        }else{
            return new SnowCard();
        }
    }
}
