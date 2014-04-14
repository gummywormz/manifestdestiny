import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Cactus that explodes into bullets when touched or after a bit
 * 
 * @author Paul Alves
 * @version 4/14/2014
 */
public class Cactus extends Actor
{
    private int cooldown = Greenfoot.getRandomNumber(60) + 5;
    private int noExplode = 0;
    /**
     * Act - do whatever the Cactus wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        ManifestWorld w = (ManifestWorld) getWorld();
        if(w.getIsPaused() == 1){return;}
        if(cooldown > 0 && noExplode == 0){
            setLocation(getX(), getY() + 4);
            cooldown--;
        }else{
            int x = getX();
            int y = getY();
            GreenfootSound f = new GreenfootSound("fire.mp3");
            f.play();
            w.explodeIt(x,y);
            noExplode = 1;
            w.removeObject(this);
            return;
        }
        if(getY() > 800 && noExplode == 0){getWorld().removeObject(this);return;}
        if(isTouching(Bullet1.class) && noExplode == 0){
            GreenfootSound p = new GreenfootSound("explode.wav");
            p.play();
            int x = getX();
            int y = getY();
            w.addScore(100);
            GreenfootSound f2 = new GreenfootSound("fire.mp3");
            f2.play();
            w.explodeIt(x,y);
            noExplode = 1;
            w.removeObject(this);
            return;}
    }    
}
