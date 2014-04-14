import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Enemy's projectile
 * 
 * @author Paul Alves
 * @version 4/14/2014
 */
public class Bullet2 extends Actor
{
    private int dir;
    private int xDrift;
    /**
     * Constructs a standard bullet
     */
    public Bullet2(){
        dir = 1;
        xDrift = Greenfoot.getRandomNumber(5);
    }

    /**
     * Constructs a bullet with the specified direction
     * @param pDir The direction to set
     */
    public Bullet2(int pDir){
        dir = pDir;
        xDrift = Greenfoot.getRandomNumber(5);
    }

    /**
     * Constructs a bullet with the specified direction, without any drift
     * @param pDir The direction to set
     * @param noDrift 1 for no drift, 0 for drift
     */
    public Bullet2(int pDir, int noDrift){
        dir = pDir;
    }

    /**
     * Act - do whatever the Bullet1 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        ManifestWorld w = (ManifestWorld) getWorld();
        if(w.getIsPaused() == 1){return;}
        setLocation(getX()+xDrift,getY()-6 * dir);
        if(getY() < 10 || getY() > 845 || isTouching(Oxen.class) || isTouching(Wagon.class)){
            getWorld().removeObject(this);
        }
    }    
}
