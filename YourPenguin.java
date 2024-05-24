import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class YourPenguin here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class YourPenguin extends Penguin
{
    /**
     * Act - do whatever the YourPenguin wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     * 
     * 
     */
    
    public YourPenguin() {
        GreenfootImage image = getImage(); // Get the current image
        image.scale(image.getWidth() / 6, image.getHeight() / 6); // Scale the image to half its original size
        setImage(image); // Set the resized image
    }
    public void act()
    {
        // Add your action code here.
    }
}
