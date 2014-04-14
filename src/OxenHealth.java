import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Power up that gives oxen health
 * 
 * @author Paul Alves
 * @version 4/14/2014
 */
public class OxenHealth extends Actor
{
    /**
     * Act - do whatever the OxenHealth wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        ManifestWorld w = (ManifestWorld) getWorld();
        if(w.getIsPaused() == 1){return;}
        setLocation(getX(),getY() + 4);
        if(isTouching(Wagon.class)){
            w.setPowerUp(2);
            getWorld().removeObject(this);
            return;
        }
    }    
}
