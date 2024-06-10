import greenfoot.*;

public class Text extends Actor {
    private GreenfootImage textImage;

    public Text() {
        textImage = new GreenfootImage(1, 1);
        setImage(textImage);
    }

    public void setText(String text, Color color) {
        textImage.clear();
        textImage = new GreenfootImage(text, 24, color, new Color(0, 0, 0, 0));
        setImage(textImage);
    }

    public void setText(String text) {
        setText(text, Color.BLACK); // Default to black if no color is specified
    }
}
