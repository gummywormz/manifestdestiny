import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Toggles the game being paused or not
 * 
 * @author Paul Alves
 * @version 4/14/2014
 */
public class PauseButton extends Actor
{
    private int pos;
    /**
     * Act - do whatever the PauseButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(Greenfoot.mouseClicked(this)){
            ManifestWorld w = (ManifestWorld) getWorld();
            if(pos == 0){
                w.setIsPaused(1);
                setImage("play.png");
                pos = 1;
            }else{
                w.setIsPaused(0);
                setImage("pause.png");
                pos = 0;	
            }

        }
    }    
}
