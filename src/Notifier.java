import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Displays an image for a short period of time
 * 
 * @author Paul Alves
 * @version 4/14/2014
 */
public class Notifier extends Actor
{
    GreenfootImage img;
    int cooldown;
    /**
     * Constructs a Notifier with the specified image and time limit
     * @param i Greenfoot Image to be displayed
     * @param time The number of act cycles the image is to be displayed for.
     */
    public Notifier(GreenfootImage i, int time){
        cooldown = time;
        img = i;
    }
    
    /**
     * Act - do whatever the Notifier wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        setImage(img);
        if(cooldown <= 0){getWorld().removeObject(this);}else{cooldown--;}
    }    
}
