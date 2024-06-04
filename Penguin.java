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
    
    public Card showNextCard(){
        return deck.get(0); // show next card idk if this is helpful
    }

    public Card playCard() {
        if (!deck.isEmpty()) {
            return deck.remove(0); // Play the top card
        }
        return null;
    }

    public void winCards(Card card1, Card card2) {
        deck.add(card1);
        deck.add(card2);
    }
    
    public boolean checkStatus(){
        if(deck.isEmpty()){
            setImage("skull.png");
            return false;
        }
        return true;
    }
}
