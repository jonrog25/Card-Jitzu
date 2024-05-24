import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FireCard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FireCard extends Card
{
    /**
     * Act - do whatever the FireCard wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public FireCard(){
        GreenfootImage image = getImage(); // Get the current image
        image.scale(image.getWidth() / 24, image.getHeight() / 24); // Scale the image to half its original size
        setImage(image); // Set the resized image
    }
    public void act()
    {
        // Add your action code here.
    }
}
