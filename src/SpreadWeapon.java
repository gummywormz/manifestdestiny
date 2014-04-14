import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Weapon that fires 3 bullets
 * 
 * @author Paul Alves
 * @version 4/14/2014
 */
public class SpreadWeapon extends Actor
{
    /**
     * Act - do whatever the SpreadWeapon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        ManifestWorld w = (ManifestWorld) getWorld();
        if(w.getIsPaused() == 1){return;}
        setLocation(getX(),getY() + 4);
        if(isTouching(Wagon.class)){
            w.setWeapon(2);
            getWorld().removeObject(this);
            return;
        }
    }    
}
