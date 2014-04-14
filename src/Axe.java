import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A bullet that has short range.
 * 
 * @author Paul Alves
 * @version 4/14/2014
 */
public class Axe extends Bullet2
{
    private int dir;
    private int xDrift = 0;
    private int countdown = 30;
    /**
     * Constructs a basic axe
     */
    public Axe(){
        dir = 1;
    }

    /**
     * Constructs an axe with the specified direction
     * @param pDir The direction of the axe
     */
    public Axe(int pDir){
        dir = pDir;
    }

    /**
     * Act - do whatever the Axe wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        ManifestWorld w = (ManifestWorld) getWorld();
        if(w.getIsPaused() == 1){return;}
        setLocation(getX()+xDrift,getY()-6 * dir);
        if(getY() < 10 || getY() > 845 || isTouching(Oxen.class) || isTouching(Wagon.class)){
            getWorld().removeObject(this);return;
        }

        if(countdown >= 0){countdown--;}else{getWorld().removeObject(this);
            countdown = 2147856; 
            return;}
    }     
}
