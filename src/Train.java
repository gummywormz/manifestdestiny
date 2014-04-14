import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Bulky, slow moving enemy that fires aimed bullets
 * 
 * @author Paul Alves
 * @version 4/14/2014
 */
public class Train extends Actor
{
    private int health = 150;
    private int iFrames = 0;
    private int cooldown;
    /**
     * Act - do whatever the Train wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        ManifestWorld w = (ManifestWorld) getWorld();
        if(w.getIsPaused() == 1){return;}
        setLocation(getX(),getY() + 1);
        if(getY() > 800 ){getWorld().removeObject(this); return;}
        if(health <=0){
            GreenfootSound p = new GreenfootSound("explode.wav");
            p.play();
            w.addScore(1000);
            getWorld().removeObject(this); 
            return;
        }
        if(Greenfoot.getRandomNumber(3500) < 150){
            GreenfootSound f = new GreenfootSound("fire.mp3");
            f.play();
            w.aimBullet(getX(),getY());
            w.aimBullet(getX(),getY());
            cooldown = 150;
        }

        if(isTouching(Bullet1.class) && iFrames <= 0) {health -= 10; iFrames=10;setImage("train-hit.png");}

        if(iFrames >= 0){iFrames--;}else{setImage("train1.png");}

        if(cooldown >= 0){cooldown--;}
    }     
}
