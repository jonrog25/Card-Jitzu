import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SnowCard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SnowCard extends Card
{
    /**
     * Act - do whatever the SnowCard wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public SnowCard(){
        GreenfootImage image = getImage(); // Get the current image
        image.scale(image.getWidth() / 24, image.getHeight() / 24); // Scale the image to half its original size
        setImage(image); // Set the resized image
    }
    public void act()
    {
        // Add your action code here.
    }
}
