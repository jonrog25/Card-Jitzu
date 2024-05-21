import greenfoot.*;
import java.util.Random;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Card here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Card extends Actor
{
    private int num;
    private String color;
    
    public Card(){
        
        
        num = weightedProbability();
        double cProb = Math.random();
        if(cProb < 0.33){
            color = "red";
        }else if(cProb < 0.66){
            color = "blue";
        }else{
            color = "yellow";
        }
    }
    
    private int weightedProbability(){
        Random r = new Random();
        int[] weights = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        int index = r.nextInt(weights.length);
        return index + 1;
    }
    
    /**
     * Act - do whatever the Card wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
}
