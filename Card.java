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
        switch(this.type){
            case "Fire" :
                setImage("Fire0.png");
            case "Water" :
                setImage("Water0.png");
            case "Snow" :
                setImage("Snow0.png");
        }
    }

    public String getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    public boolean beats(Card other) {
        if (this.type.equals(other.type)) {
            if(this.value == other.value){
                // idk what should happen but need to check for this
                int random = (int)(Math.random())*100;
                return random < 50; //we could do something like this: random
            }
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
