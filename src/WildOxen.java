import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Slow, medium health enemy that fires straight only
 * 
 * @author Paul Alves
 * @version 4/14/2014
 */
public class WildOxen extends Actor
{
    private int health = 60;
    private int iFrames = 0;
    private int cooldown;
    private int imageCountDown = 10;
    private String[] images = {"fOxen1.png","fOxen2.png","fOxen3.png","fOxen4.png"};
    private int imagePos;
    /**
     * Act - do whatever the WildOxen wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        ManifestWorld w = (ManifestWorld) getWorld();
        if(w.getIsPaused() == 1){return;}
        setLocation(getX(),getY() + 1);
        if(Greenfoot.getRandomNumber(3500) < 150){
            GreenfootSound f = new GreenfootSound("fire.mp3");
            f.play();
            getWorld().addObject(new Bullet2(-1,0), getX(), getY() + 3);
            cooldown = 50;
        }

        if(getY() > 800){
            getWorld().removeObject(this); 
            return;}

        if( health <=0 ){
            GreenfootSound p = new GreenfootSound("explode.wav");
            p.play();
            w.addScore(400);
            getWorld().removeObject(this); 
            return;
        }

        if(isTouching(Bullet1.class) && iFrames <= 0) {health -= 10; iFrames=10;setImage("fOxenH.png");}

        if(iFrames >= 0){iFrames--;}

        if(cooldown >= 0){cooldown--;}

        if(imageCountDown > 0){imageCountDown--;}else{
            imagePos++;
            imageCountDown=10;
        }
        if(imagePos >= 3){imagePos=0;}
        setImage(images[imagePos]);
    }     
}
