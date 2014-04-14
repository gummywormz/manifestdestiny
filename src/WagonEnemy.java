import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Fast enemy that fires aimed bullets.
 * 
 * @author Paul Alves
 * @version 4/14/2014
 */
public class WagonEnemy extends Actor
{
    private int health = 30;
    private int iFrames = 0;
    private int imageCountDown = 10;
    private String[] images = {"fWagon1.png","fWagon2.png","fWagon3.png","fWagon4.png"};
    private int imagePos;
    /**
     * Act - do whatever the WagonEnemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        ManifestWorld w = (ManifestWorld) getWorld();
        if(w.getIsPaused() == 1){return;}
        setLocation(getX(),getY() + 4);
        if(getY() > 800 ){getWorld().removeObject(this); return;}
        if(health <=0){
            GreenfootSound p = new GreenfootSound("explode.wav");
            p.play();
            w.addScore(500);
            getWorld().removeObject(this); 
            return;
        }
        if(Greenfoot.getRandomNumber(3500) < 150){
            GreenfootSound f = new GreenfootSound("fire.mp3");
            f.play();
            w.aimBullet(getX(),getY());
        }

        if(getY() > 800){getWorld().removeObject(this); return;}

        if(health <=0){
            GreenfootSound p = new GreenfootSound("explode.wav");
            p.play();
            w.addScore(500);
            getWorld().removeObject(this); 
            return;
        }
        if(isTouching(Bullet1.class) && iFrames <= 0) {health -= 10; iFrames=10;}

        if(iFrames >= 0){iFrames--;setImage("fWagonH.png");}

        if(imageCountDown > 0){imageCountDown--;}else{
            imagePos++;
            imageCountDown=10;
        }
        if(imagePos >= 3){imagePos=0;}
        setImage(images[imagePos]);
    }    
}
