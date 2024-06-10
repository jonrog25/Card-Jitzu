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
        if (isUser) {
            setImage("user.png");
        } else {
            setImage("computer.png");
        }
    }

    private void initializeDeck() {
        // Create the deck with 6 cards
        for (int i = 1; i <= 6; i++) {
            double chance = Math.random();
            Card card;
            if (chance < 0.33) {
                card = new Card("Fire", i);
            } else if (chance < 0.66) {
                card = new Card("Water", i);
            } else {
                card = new Card("Snow", i);
            }
            deck.add(card);
        }

        // Shuffle the deck
        Collections.shuffle(deck);

        // Randomly assign powerups to 2 cards
        ArrayList<Integer> indices = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            indices.add(i);
        }
        Collections.shuffle(indices);
        deck.get(indices.get(0)).assignRandomPowerup();
        deck.get(indices.get(1)).assignRandomPowerup();
    }

    public Card playCard() {
        DojoWorld world = (DojoWorld) getWorld();
        String activePowerup = world.getActivePowerup(!isUser); // Get the current active powerup for the opponent

        if (isUser) {
            int numInput = -1;
            while (true) {
                try {
                    String inputPrompt = "Input the position of the card you wish to play (0-" + (deck.size() - 1) + "): ";
                    String input = Greenfoot.ask(inputPrompt);
                    numInput = Integer.parseInt(input);
                    if (numInput >= 0 && numInput < deck.size()) {
                        Card selectedCard = deck.get(numInput);
                        if ((activePowerup != null && activePowerup.equals("noFire") && selectedCard.getType().equals("Fire")) ||
                            (activePowerup != null && activePowerup.equals("noWater") && selectedCard.getType().equals("Water")) ||
                            (activePowerup != null && activePowerup.equals("noSnow") && selectedCard.getType().equals("Snow"))) {
                            Greenfoot.ask("Card type is restricted. Please select a different card.");
                        } else {
                            break;
                        }
                    } else {
                        Greenfoot.ask("Invalid input. Please enter a number between 0 and " + (deck.size() - 1));
                    }
                } catch (NumberFormatException e) {
                    Greenfoot.ask("Invalid input. Please enter a valid number.");
                }
            }
            return deck.remove(numInput);
        } else {
            for (Card card : deck) {
                if ((activePowerup != null && activePowerup.equals("noFire") && card.getType().equals("Fire")) ||
                    (activePowerup != null && activePowerup.equals("noWater") && card.getType().equals("Water")) ||
                    (activePowerup != null && activePowerup.equals("noSnow") && card.getType().equals("Snow"))) {
                    continue; // Skip the restricted card
                } else {
                    return deck.remove(deck.indexOf(card)); // Play the allowed card
                }
            }
        }

        return null; // No card played if all are restricted
    }

    public Card getCard(int i) {
        return deck.get(i);
    }

    public void winCards(Card card1, Card card2) {
        if (card1 != null) deck.add(card1);
        if (card2 != null) deck.add(card2);
        Greenfoot.delay(50);
        //if (isUser) {
            //getWorld().showText("You win this round :)", 300, 100);
        //} else {
            //getWorld().showText("You lose this round :(", 300, 100);
        //}
        Greenfoot.delay(50);
        getWorld().showText("", 300, 100);
    }

    public boolean inPlay() {
        return !deck.isEmpty();
    }

    public int getDeckSize() {
        return deck.size();
    }
}
