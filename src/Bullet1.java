import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Your wagon's bullet
 * 
 * @author Paul Alves
 * @version 4/14/2014
 */
public class Bullet1 extends Actor
{
    private int dir;
    private int countdown = 2147888;
    private int sine;
    private int explode;
    /**
     * Constructs a basic bullet
     */
    public Bullet1(){
        dir = 1;
    }

    /**
     * Constructs a bullet with the given direction
     * @param pDir The direction to set
     */
    public Bullet1(int pDir){
        dir = pDir;
    }

    /**
     * Constructs a bullet with the given direction and wave properties
     * @param pDir The direction to set
     * @param useSine Sets whether wave is to be used
     */
    public Bullet1(int pDir,int useSine){
        dir = pDir;
        sine = useSine;
    }

    /**
     * Constructs a bullet with the given direction wave properties, and split properties
     * @param pDir The direction to set
     * @param useSine Sets whether wave is to be used
     * @param pExplode Sets whether split is to be used
     */
    public Bullet1(int pDir,int useSine, int pExplode){
        dir = pDir;
        sine = useSine;
        explode = pExplode; 
    }

    /**
     * Act - do whatever the Bullet1 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        ManifestWorld w = (ManifestWorld) getWorld();
        if(w.getIsPaused() == 1){return;}
        if(sine == 0){
            setLocation(getX(),getY()-6 * dir);
        }else{
            int x = (int) (40 * Math.sin(getX()/2));
            setLocation(getX()+x,getY()-8 * dir);   
        }
        if(getY() < 10 || getY() > 845 || countdown <= 0){
            if(explode == 1){((ManifestWorld)getWorld()).explodeBullet(getX(),getY());}
            getWorld().removeObject(this);
            return;
        }
        if(isTouching(Cactus.class) || isTouching(Enemy1.class) || isTouching(WagonEnemy.class) || isTouching(Train.class) || isTouching(WildOxen.class) || isTouching(Cowboy.class)
        || isTouching(Indian.class) || isTouching(Bandit.class)){
            countdown = 10;
        }
        if(countdown >= 0){
            countdown--;
        }
    }    
}
