import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Slow moving indian that fires boomerangs
 * 
 * @author Paul Alves
 * @version 4/14/2014
 */
public class Indian extends Actor
{
    private int health = 10;
    private int iFrames = 0;
    private int cooldown;
    /**
     * Act - do whatever the Indian wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        ManifestWorld w = (ManifestWorld) getWorld();
        if(w.getIsPaused() == 1){return;}
        setLocation(getX(),getY() + 1);

        if(getY() > 800 ){
            getWorld().removeObject(this); 
            return;}

        if(health <=0){
            w.addScore(200);
            GreenfootSound p = new GreenfootSound("maledie.wav");
            p.play();
            getWorld().removeObject(this); 
            return;
        }
        if(Greenfoot.getRandomNumber(3500) < 150 && cooldown <=0){
            GreenfootSound f = new GreenfootSound("fire.mp3");
            f.play();
            w.aimBoomerang(getX(),getY(),-2);
            cooldown = 100;
        }

        if(getY() > 800 ){
            getWorld().removeObject(this); 
            return;}

        if(health <=0){
            w.addScore(200);
            GreenfootSound p = new GreenfootSound("maledie.wav");
            p.play();
            getWorld().removeObject(this); 
            return;
        }

        if(isTouching(Bullet1.class) && iFrames <= 0) {health -= 10; iFrames=10;}

        if(iFrames >= 0){iFrames--;}

        if(cooldown >= 0){cooldown--;}
    }   
}
