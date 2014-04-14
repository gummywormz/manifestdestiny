import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Box that spawns powerups
 * 
 * @author Paul Alves 
 * @version 4/14/2014
 */
public class Crate extends Actor
{
    /**
     * Act - do whatever the Crate wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        ManifestWorld w = (ManifestWorld) getWorld();
        if(w.getIsPaused() == 1){return;}
        setLocation(getX(),getY() + 3);
        if(isTouching(Bullet1.class)){
            GreenfootSound f = new GreenfootSound("explode.wav");
            f.play();
            w.spawnWeapon(getX(),getY());
            getWorld().removeObject(this);
            return;
        }
    }    
}
