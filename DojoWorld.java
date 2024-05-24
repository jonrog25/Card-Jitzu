import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DojoWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DojoWorld extends World
{

    /**
     * Constructor for objects of class DojoWorld.
     * 
     */
    private Penguin userPenguin;
    private Penguin computerPenguin;
    
    public DojoWorld()
    {    
        super(600, 400, 1); // Set the size of the world
        userPenguin = new Penguin(true); // The user's penguin
        computerPenguin = new Penguin(false); // The computer's penguin
        addObject(userPenguin, 150, 300); // Add user penguin to the world
        addObject(computerPenguin, 450, 300);
    }
    
    public void act() {
        if (Greenfoot.mouseClicked(null)) {
            playRound();
        }
    }

    private void playRound() {
        Card userCard = userPenguin.playCard();
        Card computerCard = computerPenguin.playCard();

        if (userCard != null && computerCard != null) {
            addObject(userCard, 200, 300);
            showText(""+userCard.getValue(), 200, 300);
            addObject(computerCard, 400, 300);
            showText(""+computerCard.getValue(), 400, 300);

            if (userCard.beats(computerCard)) {
                userPenguin.winCards(userCard, computerCard);
                Greenfoot.delay(50);
                removeObjects(getObjects(Card.class));
                showText("", 200, 300);
                showText("", 400, 300);
            } else if (computerCard.beats(userCard)) {
                computerPenguin.winCards(userCard, computerCard);
                Greenfoot.delay(50);
                removeObjects(getObjects(Card.class));
                showText("", 200, 300);
                showText("", 400, 300);
            } else {
                // If it's a tie, return cards to the respective decks
                userPenguin.winCards(userCard, null);
                computerPenguin.winCards(computerCard, null);
                Greenfoot.delay(50);
                removeObjects(getObjects(Card.class));
                showText("", 200, 300);
                showText("", 400, 300);
            }
        }
    }
    
    
    
}
