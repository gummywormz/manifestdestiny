import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * An enemy bullet that returns after a bit
 * 
 * @author Paul Alves
 * @version 4/14/2014
 */
public class Boomerang extends Bullet2
{
    private int dir;
    private int xDrift = 0;
    private int countdown = 20;
    /**
     * Constructs a basic boomerang
     */
    public Boomerang(){
        dir = 1;
    }

    /**
     * Constructs a Boomerang with a given direction.
     * @param pDir the direction of the boomerang
     */
    public Boomerang(int pDir){
        dir = pDir;
    }

    /**
     * Act - do whatever the Boomerang wants to do. This method is called whenever
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

        if(countdown >= 0){countdown--;}else{dir=-dir;countdown = 2147856;}
    }    
}
