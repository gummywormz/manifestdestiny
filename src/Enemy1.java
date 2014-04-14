import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Snake enemy that moves back and forth in a wave pattern. It fires standard shots.
 * 
 * @author Paul Alves
 * @version 4/14/2014
 */
public class Enemy1 extends Actor
{
    private int cooldown;
    private int dir;
    private int imagePos;
    private int imageCountDown = 10;
    private String[] images = {"snake1.png","snake2.png","snake3.png","snake4.png"};
    /**
     * Act - do whatever the Enemy1 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        ManifestWorld w = (ManifestWorld) getWorld();
        if(w.getIsPaused() == 1){return;}
        if(cooldown == 0){
            setLocation(getX() + Greenfoot.getRandomNumber(20) -10, getY() + 10);
            cooldown = 5;
        }   

        if(Greenfoot.getRandomNumber(900) < 25){GreenfootSound f = new GreenfootSound("fire.mp3");
            f.play();getWorld().addObject(new Bullet2(-1), getX(), getY()+4);}
        if(imageCountDown > 0){imageCountDown--;}else{
            imagePos++;
            imageCountDown=10;
        }
        if(cooldown > 0){cooldown--;}
        if(imagePos >= 3){imagePos=0;}
        setImage(images[imagePos]);
        if(getY() > 800){
            getWorld().removeObject(this);
            return;
        }
        if(isTouching(Bullet1.class)){
            w.addScore(100);
            getWorld().removeObject(this);
            return;
        }
    }    
}
