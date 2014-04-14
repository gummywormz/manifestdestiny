import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Your horses
 * 
 * @author Paul Alves
 * @version 4/14/2014
 */
public class Oxen extends Actor
{

    private int health = 100;
    private int speed = 3;
    private int iFrames = 0;
    private int fireChance = 150;
    private int imageCountDown = 10;
    private String[] images = {"oxen.png","oxen2.png","oxen3.png","oxen4.png"};
    private int imagePos;
    /**
     * Act - do whatever the Oxen wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        ManifestWorld w = (ManifestWorld) getWorld();
        if(w.getIsPaused() == 1){return;}   
        if ( Greenfoot.isKeyDown("left") && getX() > 200 )
        {
            setLocation(getX()-speed,getY());
        }        

        if ( Greenfoot.isKeyDown("right") && getX() < 700)
        {
            setLocation(getX()+speed,getY());
        }       

        if ( Greenfoot.isKeyDown("up") )
        {
            setLocation(getX(),getY()-speed);
        }

        if ( Greenfoot.isKeyDown("down") )
        {
            setLocation(getX(),getY()+speed);
        }

        if (Greenfoot.isKeyDown("z"))
        {
            if(Greenfoot.getRandomNumber(fireChance) < 2){
                GreenfootSound f = new GreenfootSound("fire.mp3");
                f.play();
                ((ManifestWorld) getWorld()).addObject(new Bullet1(1), getX(), getY()-3);
            }
        }    
        List bl2 = getObjectsInRange(90,Bullet2.class);
        if(!bl2.isEmpty() && iFrames == 0){
            health -=10;
            if(health <= 0){health = 0;getWorld().removeObject(this);return;}
            iFrames = 50;
            setImage("oxen-hit.png");
        }
        if(iFrames > 0){iFrames--;}else{setImage("oxen.png");}
        if(imageCountDown > 0){imageCountDown--;}else{
            imagePos++;
            imageCountDown=10;
        }
        if(imagePos >= 3){imagePos=0;}
        setImage(images[imagePos]);

        if(isTouching(WildOxen.class) && iFrames == 0){
            health -= 10;
            if(health <= 0){health = 0;getWorld().removeObject(this);return;}
            iFrames = 50;
            setImage("oxen-hit.png");
        }
        if( (isTouching(WagonEnemy.class) || isTouching(Train.class) )&& iFrames == 0){
            health -= 20;
            if(health <= 0){health = 0; getWorld().removeObject(this);return;}
            iFrames = 50;
            setImage("oxen-hit.png");
        }
    }

    /**
     * Sets the horse's speed
     * @param speed The speed to set
     */
    public void setSpeed(int speed)
    {
        this.speed = speed;
    }

    /**
     * Sets the horse's firing chances
     * @param chance the firing chance to set
     */
    public void setFireChance(int chance){
        fireChance = chance;
    }

    /**
     * Returns the fire chance
     * @return The fire chance
     */
    public int getFireChance(){
        return fireChance;
    }

    /**
     * Adds health to this horse
     * @param h The amount of health to add
     */
    public void addHealth(int h){
        health+=h;
        if (health > 100){health = 100;}
    }

    /**
     * Sets this horse's invincibility frames
     * @param frames The amount of frames to set in Act Cycles
     */
    public void setIframes(int frames){
        iFrames = frames;
    }

    /**
     * Returns this horse's health
     * @return This horse's health
     */
    public int getHealth(){
        return health;
    }
}
