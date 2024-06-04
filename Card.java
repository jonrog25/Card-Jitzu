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
        //to-do: add animations
        if (this.type.equals(other.type)) {
            return this.value > other.value;
        } else if (this.type.equals("Fire") && other.type.equals("Snow")) {
            return true;
        } else if (this.type.equals("Snow") && other.type.equals("Water")) {
            return true;
        } else if (this.type.equals("Water") && other.type.equals("Fire")) {
            return true;
        }
        return false;
    }
}
