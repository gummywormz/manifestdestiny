import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A class designed to simplify the enemy spawning routine
 * 
 * @author Paul Alves
 * @version 4/14/2014
 */
public class EnemyInfo
{
    private int xLoc;
    private int yLoc;
    private Actor enemy;
    private int spawnDelay;
    /**
     * Constructs an EnemyInfo object
     * @param a The object to spawn
     * @param x The x location to spawn it at
     * @param y The y location to spawn it at
     * @param cooldown How many act cycles in to spawn it at (aprox. 60 Cycles Per Second)
     */
    public EnemyInfo(Actor a, int x, int y, int cooldown){
        enemy = a;
        xLoc=x;
        yLoc=y;
        spawnDelay=cooldown;
    }

    /**
     * Returns the x location of the enemy
     * @return The x location of the enemy
     */
    public int getX(){
        return xLoc;
    }

    /**
     * Returns the y location of the enemy
     * @return The y location of the enemy
     */
    public int getY(){
        return yLoc;
    }

    /**
     * Returns the Enemy Actor to spawn
     * @return The Enemy Actor to spawn
     */
    public Actor getActor(){
        return enemy;
    }

    /**
     * Returns the spawn delay of the enemy
     * @return The spawn delay of the enemy.
     */
    public int getSpawnDelay(){
        return spawnDelay;
    }
}
