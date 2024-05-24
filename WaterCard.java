import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WaterCard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WaterCard extends Card
{
    /**
     * Act - do whatever the WaterCard wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public WaterCard(){
        GreenfootImage image = getImage(); // Get the current image
        image.scale(image.getWidth() / 24, image.getHeight() / 24); // Scale the image to half its original size
        setImage(image); // Set the resized image
    }
    public void act()
    {
        // Add your action code here.
    }
}
