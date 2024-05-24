import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EnemyPenguin here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnemyPenguin extends Penguin
{
    /**
     * Act - do whatever the EnemyPenguin wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public EnemyPenguin() {
        GreenfootImage image = getImage(); // Get the current image
        image.scale(image.getWidth() / 6, image.getHeight() / 6); // Scale the image to half its original size
        setImage(image); // Set the resized image
    }
    public void act()
    {
        // Add your action code here.
    }
}
