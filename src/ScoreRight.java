import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.awt.Font;
import java.awt.Color;
/**
 * Controls the image associated with the player's score. This was largely stolen from the balloon Greenfoot demo.
 * 
 * @author Paul Alves
 * @version 1/14/2014
 */
public class ScoreRight extends Actor
{

    private int value = 0;
    private String text;
    private int stringLength;
    /**
     * Act - do whatever the ScoreLeft wants to do (in this case it continually updates the image). This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        updateImage();
    }    

    /**
     * Constructs the right player's scoreboard. A prefix can be defined
     * @param prefix The prefix desired, unused in this game.
     */
    public ScoreRight(String prefix)
    {
        text = prefix;
        stringLength = (text.length() + 2) * 100; //adjust the size of the score image

        setImage(new GreenfootImage(stringLength, 100)); //create the image
        GreenfootImage image = getImage();
        Font font = image.getFont(); //set the font
        image.setFont(font.deriveFont(36.0F));  // use larger font
        image.setColor(Color.WHITE);
        updateImage();
    }

    /**
     * Increases the player's score and updates the image.
     */
    public void scoreUp(int score)
    {
        value = value + score;
        updateImage();
    }
    
    /**
     * Sets the player's score and updates the image.
     */
    public void setScore(int score)
    {
        value = score;
        updateImage();
    }

    /**
     * Make / update the image
     */
    private void updateImage()
    {
        GreenfootImage image = getImage();
        image.clear(); //reset the image
        image.setColor(Color.WHITE);
        image.drawString(text + value, 1, 48); //draw the image
    }
}
