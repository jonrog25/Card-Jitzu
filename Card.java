import greenfoot.*;

public class Card extends Actor {
    private String type;
    private int value;
    private boolean isEnlarged = false; // Track whether the card is enlarged
    private int originalX, originalY; // Store the original position
    private static Card enlargedCard = null; // Track the currently enlarged card
    private Text numberText; // Text actor to display the card's position
    private static Text roundMessage = new Text(); // Static Text actor for round messages

    // Powerup fields
    private boolean isFirePowerUp = false;
    private boolean isWaterPowerUp = false;
    private boolean isSnowPowerUp = false;

    public Card(String type, int value) {
        this.type = type;
        this.value = value;

        switch(this.type) {
            case "Fire":
                GreenfootImage fire = new GreenfootImage("Fire" + value + ".png");
                fire.scale(fire.getWidth() / 30, fire.getHeight() / 30);
                setImage(fire);
                break;
            case "Water":
                GreenfootImage water = new GreenfootImage("Water" + value + ".png");
                water.scale(water.getWidth() / 30, water.getHeight() / 30);
                setImage(water);
                break;
            case "Snow":
                GreenfootImage snow = new GreenfootImage("Snow" + value + ".png");
                snow.scale(snow.getWidth() / 30, snow.getHeight() / 30);
                setImage(snow);
                break;
        }
        numberText = new Text();
    }

    public Card() {
        this("Fire", 1);
    }

    public String getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    public boolean isFirePowerUp() {
        return isFirePowerUp;
    }

    public boolean isWaterPowerUp() {
        return isWaterPowerUp;
    }

    public boolean isSnowPowerUp() {
        return isSnowPowerUp;
    }

    // Assign a random powerup to the card
    public void assignRandomPowerup() {
        double chance = Math.random();
        if (chance < 0.33) {
            isFirePowerUp = true;
        } else if (chance < 0.66) {
            isWaterPowerUp = true;
        } else {
            isSnowPowerUp = true;
        }
    }

    public void removePowerup() {
        isFirePowerUp = false;
        isWaterPowerUp = false;
        isSnowPowerUp = false;
    }

    // Change color of the number text based on the powerup
    public void setNumber(int number) {
        Color textColor = Color.BLACK;
        if (isFirePowerUp) {
            textColor = Color.RED;
        } else if (isWaterPowerUp) {
            textColor = Color.BLUE;
        } else if (isSnowPowerUp) {
            textColor = Color.GRAY;
        }
        numberText.setText(String.valueOf(number), textColor);
        World world = getWorld();
        if (world != null && !world.getObjects(Text.class).contains(numberText)) {
            world.addObject(numberText, getX(), getY() - getImage().getHeight() / 2 - 10);
        } else {
            numberText.setLocation(getX(), getY() - getImage().getHeight() / 2 - 10);
        }
    }

    public void removeNumberText() {
        World world = getWorld();
        if (world != null) {
            world.removeObject(numberText);
        }
    }

    public boolean beats(Card other) {
        Animation a = new Animation();
        World world = getWorld();
        int centerX = world.getWidth() / 2;
        int centerY = world.getHeight() / 2;

        if (this.type.equals(other.type)) {
            showRoundMessage(world, "Elements are matched - highest value wins", 300, 100);
            Greenfoot.delay(50);
            return this.value > other.value;
        } else if (this.type.equals("Fire") && other.type.equals("Snow")) {
            showRoundMessage(world, "Fire melts snow", 300, 100);
            a = new fireMeltingSnow();
            scaleAnimation(a);
            world.addObject(a, centerX, centerY);
            Greenfoot.delay(100);
            world.removeObject(a);
            return true;
        } else if (this.type.equals("Snow") && other.type.equals("Water")) {
            showRoundMessage(world, "Snow freezes water", 300, 100);
            a = new snowFreezingWater();
            scaleAnimation(a);
            world.addObject(a, centerX, centerY);
            Greenfoot.delay(100);
            world.removeObject(a);
            return true;
        } else if (this.type.equals("Water") && other.type.equals("Fire")) {
            showRoundMessage(world, "Water puts out fire", 300, 100);
            a = new waterKillingFire();
            scaleAnimation(a);
            world.addObject(a, centerX, centerY);
            Greenfoot.delay(100);
            world.removeObject(a);
            return true;
        }
        return false;
    }

    private void scaleAnimation(Animation animation) {
        GreenfootImage img = animation.getImage();
        img.scale(img.getWidth() / 3, img.getHeight() / 3); // Scale the image to 1/3 of its size
        animation.setImage(img);
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            if (isEnlarged) {
                shrink();
                enlargedCard = null;
            } else {
                if (enlargedCard != null) {
                    enlargedCard.shrink();
                }
                enlarge();
                enlargedCard = this;
            }
        }
    }

    public void enlarge() {
        isEnlarged = true;
        originalX = getX();
        originalY = getY();
        GreenfootImage img = getImage();
        img.scale(img.getWidth() * 3, img.getHeight() * 3); // Enlarge the image
        setImage(img);
        setLocation(getWorld().getWidth() / 2, getWorld().getHeight() / 2);
        numberText.setLocation(getX(), getY() - getImage().getHeight() / 2 - 10); // Adjust position of the text
    }

    public void shrink() {
        isEnlarged = false;
        GreenfootImage img = getImage();
        img.scale(img.getWidth() / 3, img.getHeight() / 3); // Shrink the image back to original size
        setImage(img);
        setLocation(originalX, originalY);
        numberText.setLocation(getX(), getY() - getImage().getHeight() / 2 - 10); // Adjust position of the text
    }

    public static void shrinkEnlargedCard() {
        if (enlargedCard != null) {
            enlargedCard.shrink();
            enlargedCard = null;
        }
    }

    private void showRoundMessage(World world, String text, int x, int y) {
        if (!world.getObjects(Text.class).contains(roundMessage)) {
            world.addObject(roundMessage, x, y);
        }
        roundMessage.setText(text);
        roundMessage.setLocation(x, y);
    }

    public static void clearRoundMessage(World world) {
        roundMessage.setText("");
        world.removeObject(roundMessage); // Remove the round message object
    }
}
