import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A bar that updates based on the health of an Actor
 * 
 * @author Paul Alves
 * @version 4/14/2014
 */
public class HealthBar extends Actor
{
    /**
     * Act - do whatever the HealthBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    

    /**
     * Updates the health bar
     * @param h The health value to be displayed
     */
    public void update(int h){
        if(h <= 0){setImage("hb-0.png"); return;}
        if(h <= 20 && h > 0){setImage("hb-10.png"); return;}
        if(h <= 30 && h > 10){setImage("hb-20.png"); return;}
        if(h <= 40 && h > 20){setImage("hb-30.png"); return;}
        if(h <= 50 && h > 30){setImage("hb-40.png"); return;}
        if(h <= 60 && h > 40){setImage("hb-50.png"); return;}
        if(h <= 70 && h > 50){setImage("hb-60.png"); return;}
        if(h <= 80 && h > 60){setImage("hb-70.png"); return;}
        if(h <= 90 && h > 70){setImage("hb-80.png"); return;}
        if(h <= 99 && h > 80){setImage("hb-90.png"); return;}
        if(h == 100){setImage("HealthBar.png"); return;}
    }
}
