import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class DojoWorld here.
 * 
 * @version (a version number or a date)
 */
public class DojoWorld extends World {

    private Penguin userPenguin;
    private Penguin computerPenguin;
    private String userActivePowerup = null; // To track the current active powerup for the user
    private String enemyActivePowerup = null; // To track the current active powerup for the enemy

    private String nextUserPowerup = null; // To track the next active powerup for the user
    private String nextEnemyPowerup = null; // To track the next active powerup for the enemy

    /**
     * Constructor for objects of class DojoWorld.
     */
    public DojoWorld() {    
        super(600, 400, 1); // Set the size of the world
        userPenguin = new Penguin(true); // The user's penguin
        computerPenguin = new Penguin(false); // The computer's penguin
        addObject(userPenguin, 150, 300); // Add user penguin to the world
        addObject(computerPenguin, 450, 300);
        setupCardPositions(); // Initial setup of card positions
    }

    public void act() {
        if (Greenfoot.isKeyDown("space")) {
            Card.shrinkEnlargedCard(); // Shrink any enlarged card
            clearRoundText(); // Clear text from previous round
            playRound();
            updateCardPositions();
            assignCardNumbers(); // Reassign card numbers after updating positions
            applyNextPowerups(); // Apply next powerups for the current round
        }

        showText("Deck Count: " + userPenguin.getDeckSize(), 150, 350);
        showText("Deck Count: " + computerPenguin.getDeckSize(), 450, 350);
        displayActivePowerup(); // Always display the current powerup status
    }

    private void setupCardPositions() {
        int deckSize = userPenguin.getDeckSize();
        int screenWidth = getWidth();
        int cardWidth = 60; // approximate width of a card

        int availableWidth = screenWidth - 2 * cardWidth; // subtracting space for the first and last card
        int spacing = deckSize > 1 ? availableWidth / (deckSize - 1) : 0; // avoid division by zero

        for (int i = 0; i < deckSize; i++) {
            if (userPenguin.getCard(i) != null) {
                int xPosition = cardWidth + (i * spacing);
                Card card = userPenguin.getCard(i);
                addObject(card, xPosition, 50);
                card.setNumber(i); // Set the number for each card
            }
        }
    }

    private void updateCardPositions() {
        List<Card> cards = getObjects(Card.class);
        for (Card card : cards) {
            card.removeNumberText(); // Remove number text before removing card
        }
        removeObjects(cards); // Clear all card objects
        setupCardPositions();
    }

    private void playRound() {
        Card userCard = userPenguin.playCard();
        Card computerCard = computerPenguin.playCard();

        if (userCard != null && computerCard != null) {
            if (userCard.isFirePowerUp()) {
                nextUserPowerup = "noFire";
                userCard.removePowerup();
            } else if (userCard.isWaterPowerUp()) {
                nextUserPowerup = "noWater";
                userCard.removePowerup();
            } else if (userCard.isSnowPowerUp()) {
                nextUserPowerup = "noSnow";
                userCard.removePowerup();
            }

            if (computerCard.isFirePowerUp()) {
                nextEnemyPowerup = "noFire";
                computerCard.removePowerup();
            } else if (computerCard.isWaterPowerUp()) {
                nextEnemyPowerup = "noWater";
                computerCard.removePowerup();
            } else if (computerCard.isSnowPowerUp()) {
                nextEnemyPowerup = "noSnow";
                computerCard.removePowerup();
            }

            userCard.setLocation(200, 300);
            showText("" + userCard.getValue(), 200, 300);
            addObject(computerCard, 400, 300);
            showText("" + computerCard.getValue(), 400, 300);

            if (userCard.beats(computerCard)) {
                showText("You win this round :)", 300, 125);
                Greenfoot.delay(50);
                showText("", 300, 125);
                userPenguin.winCards(userCard, computerCard);
            } else if (computerCard.beats(userCard)) {
                showText("You lose this round :(", 300, 125);
                Greenfoot.delay(50);
                showText("", 300, 125);
                computerPenguin.winCards(userCard, computerCard);
                userCard.removeNumberText();
            } else {
                showText("Tie", 300, 125);
                Greenfoot.delay(50);
                showText("", 300, 125);
                userPenguin.winCards(userCard, null);
                computerPenguin.winCards(computerCard, null);
            }
            Greenfoot.delay(50);
            removeObjects(getObjects(Card.class));
            clearRoundText(); // Clear round message
            //updateCardPositions(); // Update card positions and their numbers
            //assignCardNumbers(); // Reassign card numbers after updating positions
            checkEndGame();
        }
    }

    private void applyNextPowerups() {
        userActivePowerup = nextUserPowerup;
        enemyActivePowerup = nextEnemyPowerup;
        nextUserPowerup = null;
        nextEnemyPowerup = null;
    }

    private void clearRoundText() {
        showText("", 200, 300);
        showText("", 400, 300);
        Card.clearRoundMessage(this); // Clear the round message
    }

    private void checkEndGame() {
        if (!userPenguin.inPlay()) {
            showText("You're out of cards, you lose!!", 300, 200);
            Greenfoot.stop();
        }
        if (!computerPenguin.inPlay()) {
            showText("The opponent ran out of cards, you win", 300, 200);
            Greenfoot.stop();
        }
    }

    private void assignCardNumbers() {
        int deckSize = userPenguin.getDeckSize();
        for (int i = 0; i < deckSize; i++) {
            if (userPenguin.getCard(i) != null) {
                Card card = userPenguin.getCard(i);
                card.setNumber(i); // Reassign the number for each card
            }
        }
    }

    private void displayActivePowerup() {
        String userPowerupMessage = "User Active Powerup: ";
        String enemyPowerupMessage = "Enemy Active Powerup: ";
        if (userActivePowerup == null) {
            userPowerupMessage += "none";
        } else {
            userPowerupMessage += userActivePowerup;
        }
        if (enemyActivePowerup == null) {
            enemyPowerupMessage += "none";
        } else {
            enemyPowerupMessage += enemyActivePowerup;
        }
        showText(userPowerupMessage, 300, 370);
        showText(enemyPowerupMessage, 300, 390);
    }

    private void clearActivePowerup() {
        userActivePowerup = null;
        enemyActivePowerup = null;
    }

    public String getActivePowerup(boolean isUser) {
        return isUser ? userActivePowerup : enemyActivePowerup;
    }
}
