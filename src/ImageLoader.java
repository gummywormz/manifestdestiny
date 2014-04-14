import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Loads all needed images into memory (because Java is slow to load images...)
 * 
 * @author Paul Alves 
 * @version 4/14/2014
 */
public class ImageLoader extends Actor
{
    /**
     * Act - do whatever the ImageLoader wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        setImage("1up.png");
        setImage("acrate.png");
        setImage("Betacactus.png");
        setImage("bullet1.png");
        setImage("bullet2.png");
        setImage("burst.png");
        setImage("cowboy.png");
        setImage("fOxen1.png");
        setImage("fOxen2.png");
        setImage("fOxen3.png");
        setImage("fOxen4.png");
        setImage("fOxenH.png");
        setImage("fWagon1.png");
        setImage("fWagon2.png");
        setImage("fWagon3.png");
        setImage("fWagon4.png");
        setImage("fWagonH.png");
        setImage("health.png");
        setImage("indian.png");
        setImage("normal.png");
        setImage("oxen.png");
        setImage("oxen2.png");
        setImage("oxen3.png");
        setImage("oxen4.png");
        setImage("oxen-hit.png");
        setImage("plasma.png");
        setImage("rapid.png");
        setImage("snake1.png");
        setImage("snake2.png");
        setImage("snake3.png");
        setImage("snake4.png");
        setImage("spread.png");
        setImage("theif.png");
        setImage("train1.png");
        setImage("train-hit.png");
        setImage("wagon.png");
        setImage("wagonhit.png");
        setImage("wave.png");
        getWorld().removeObject(this);
    }    
}
