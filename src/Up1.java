import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Power up that gives an extra life
 * 
 * @author Paul Alves
 * @version 4/14/2014
 */
public class Up1 extends Actor
{
    /**
     * Act - do whatever the Up1 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        ManifestWorld w = (ManifestWorld) getWorld();
        if(w.getIsPaused() == 1){return;}
        setLocation(getX(),getY() + 4);
        if(isTouching(Wagon.class)){
            w.setPowerUp(3);
            getWorld().removeObject(this);
            return;
        }
    }    
}
