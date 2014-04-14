import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Simple play button. Unpauses the game at the start.
 * 
 * @author Paul Alves
 * @version 4/14/2014
 */
public class PlayButton extends Actor
{
    /**
     * Act - do whatever the PlayButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(Greenfoot.mouseClicked(this)){
            ManifestWorld w = (ManifestWorld) getWorld();
            w.setIsPaused(0);
            w.removeObject(this);
        }
    }    
}
