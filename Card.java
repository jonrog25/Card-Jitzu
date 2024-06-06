import greenfoot.*;
import java.util.Random;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Card here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Card extends Actor {
    private String type;
    private int value;
    

    public Card(String type, int value) {
        this.type = type;
        this.value = value;
        
        //to-do: nest switch statements so that there is a Fire 1-9 image, Water 1-9, etc
        switch(this.type){
            case "Fire" :
                setImage("Fire0.png");
                break;
            case "Water" :
                setImage("Water0.png");
                break;
            case "Snow" :
                setImage("Snow0.png");
                break;
        }
    }
    
    public Card(){
        type = "Fire";
        value = 1;
    }
    

    public String getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    public boolean beats(Card other) {
        Animation a = new Animation();
        //to-do: add animations
        if (this.type.equals(other.type)) {
            getWorld().showText("Elements are matched - highest val wins", 300, 100);
            Greenfoot.delay(50);
            return this.value > other.value;
        } else if (this.type.equals("Fire") && other.type.equals("Snow")) {
            getWorld().showText("Fire melts snow", 300, 100);
            a = new fireMeltingSnow();
            getWorld().addObject(a, 550, 100);
            Greenfoot.delay(50);
            getWorld().removeObject(a);
            return true;
        } else if (this.type.equals("Snow") && other.type.equals("Water")) {
            getWorld().showText("Snow freezes water", 300, 100);
            a = new snowFreezingWater();
            getWorld().addObject(a, 550, 100);
            Greenfoot.delay(50);
            getWorld().removeObject(a);
            return true;
        } else if (this.type.equals("Water") && other.type.equals("Fire")) {
            getWorld().showText("Water puts out fire", 300, 100);
            a = new waterKillingFire();
            getWorld().addObject(a, 550, 100);
            Greenfoot.delay(50);
            getWorld().removeObject(a);
            return true;
        }
        return false;
    }
}
