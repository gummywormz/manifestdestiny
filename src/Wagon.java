import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Your main wagon.
 * 
 * @author Paul Alves 
 * @version 4/14/2014
 */
public class Wagon extends Actor
{
    private int shiftTime = 0;
    private int bulletTime = 0;
    private int currentWeapon = 0;
    private int speed = 3;
    private int health = 50;
    private int iFrames = 50;
    /**
     * Act - do whatever the Wagon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        try{ManifestWorld w = (ManifestWorld) getWorld();
            if(w.getIsPaused() == 1){return;}
            if (Greenfoot.isKeyDown("x") && shiftTime == 0)
            {
                ((ManifestWorld) getWorld()).shiftPosition();
                shiftTime = 30;
            }

            if (Greenfoot.isKeyDown("z") && bulletTime == 0)
            {
                GreenfootSound f = new GreenfootSound("fire.mp3");
                f.play();
                if(currentWeapon==0){
                    ((ManifestWorld) getWorld()).addObject(new Bullet1(), getX(), getY()-3);
                    bulletTime = 30;}

                if(currentWeapon==1){
                    ((ManifestWorld) getWorld()).addObject(new Bullet1(1,1), getX()+40, getY()-3);
                    ((ManifestWorld) getWorld()).addObject(new Bullet1(1,1), getX()-40, getY()-3);
                    bulletTime = 35;}

                if(currentWeapon==2){
                    ((ManifestWorld) getWorld()).addObject(new Bullet1(), getX()+60, getY()-3);
                    ((ManifestWorld) getWorld()).addObject(new Bullet1(), getX()-60, getY()-3);
                    ((ManifestWorld) getWorld()).addObject(new Bullet1(), getX(), getY()-3);
                    bulletTime = 50;}

                if(currentWeapon==3){
                    for(int i = 0; i < 5; i++){
                        int random = Greenfoot.getRandomNumber(120);
                        Bullet1 bl1 = new Bullet1();
                        bl1.turn(Greenfoot.getRandomNumber(360));
                        ((ManifestWorld) getWorld()).addObject(bl1, getX()+(random-40), getY()-3);
                    }
                    bulletTime = 85;}

                if(currentWeapon==4){
                    ((ManifestWorld) getWorld()).addObject(new Bullet1(1,0,1), getX(), getY()-3);
                    bulletTime = 40;}
            }

            if ( Greenfoot.isKeyDown("left") && getX() > 200)
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

            if(shiftTime > 0){
                shiftTime--;
            }

            if(bulletTime > 0){
                bulletTime--;
            }

            List bl2 = getObjectsInRange(105,Bullet2.class);
            if(!bl2.isEmpty() && iFrames == 0){
                health -=10;
                if(health <= 0){GreenfootSound p = new GreenfootSound("explode.wav");
                    p.play();health = 0;getWorld().removeObject(this);}
                iFrames = 50;
                setImage("wagonhit.png");
            }
            if(iFrames > 0){iFrames--;}else{setImage("wagon.png");}
            if(isTouching(WildOxen.class) && iFrames == 0){
                health -= 10;
                if(health <= 0){health = 0; getWorld().removeObject(this);}
                iFrames = 50;
                setImage("wagonhit.png");
            }
            if( (isTouching(WagonEnemy.class) || isTouching(Train.class) )&& iFrames == 0){
                health -= 20;
                if(health <= 0){health = 0; getWorld().removeObject(this);}
                iFrames = 50;
                setImage("wagonhit.png");
            }
        }    catch(java.lang.IllegalStateException e){return;}
    }

    /**
     * Sets the speed of the wagon
     * @param speed The speed to set
     */
    public void setSpeed(int speed)
    {
        this.speed = speed;
    }

    /**
     * Sets the wagon's weapon. 
     * 0 is normal,
     * 1 is wave
     * 2 is spread
     * 3 is burst
     * 4 is plasma / split
     * @param wep The weapon to set
     */
    public void setWeapon(int wep){
        currentWeapon = wep;
        ManifestWorld w = (ManifestWorld) getWorld();
        if(wep == 0){
            w.setFireChances(150);
        }
        if(wep == 1){
            w.setFireChances(500);
        }
        if(wep == 2){
            w.setFireChances(700);
        }
        if(wep == 3){
            w.setFireChances(1000);
        }
        if(wep == 4){
            w.setFireChances(99999);
        }
    }

    /**
     * Gets the wagon's health
     * @return The health of the wagon
     */
    public int getHealth(){
        return health;
    }

    /**
     * Sets the wagon's health
     * @param pHealth The health to set
     */
    public void setHealth(int pHealth){
        health = pHealth;
    }

    /**
     * Sets the wagon's invincibility frames
     * @param frames The frames to set in Act Cycles
     */
    public void setIframes(int frames){
        iFrames = frames;
    }
}
