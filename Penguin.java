import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class Penguin here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import greenfoot.*;
import java.util.ArrayList;
import java.util.Collections;

public class Penguin extends Actor {
    private ArrayList<Card> deck;
    private boolean isUser;

    public Penguin(boolean isUser) {
        this.isUser = isUser;
        this.deck = new ArrayList<>();
        initializeDeck();
        if(isUser){
            setImage("user.png");
        } else {
            setImage("computer.png");
        }
    }

    private void initializeDeck() {
        for (int i = 1; i <= 9; i++) {
            deck.add(new Card("Fire", i));
            deck.add(new Card("Water", i));
            deck.add(new Card("Snow", i));
        }
        Collections.shuffle(deck); // Shuffle the deck
        // Limit the deck to 10 cards for simplicity
        while (deck.size() > 10) {
            deck.remove(deck.size() - 1);
        }
    }

    public Card playCard() {
        
        if(!isUser){
            if (!deck.isEmpty()) {
            return deck.remove(0); // Play the top card
            }
        }
        
        if(isUser){
            String input = Greenfoot.ask("Input the position of the card you wish to play (0-9)");
            int num_input = Integer.parseInt(input);
            return deck.remove(num_input);
        }
        
        return null;
    }
    
    public Card getCard(int i){
        return deck.get(i);
    }

    public void winCards(Card card1, Card card2) {
        deck.add(card1);
        deck.add(card2);
        Greenfoot.delay(50);
        if(isUser){
            getWorld().showText("You win this round :)", 300, 100);
        } else {
            getWorld().showText("You lose this round :(", 300, 100);
        }
        Greenfoot.delay(50);
        getWorld().showText("", 300, 100);
    }
    
    public void checkStatus(){
        if(deck.isEmpty()){
            setImage("skull.png");
        }
    }
    
    public int getDeckSize(){
        return deck.size();
    }
}
