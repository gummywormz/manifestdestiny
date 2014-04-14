import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The main world of the game
 * 
 * @author Paul Alves
 * @version 4/14/2014
 */
public class ManifestWorld extends World
{
    private Wagon w;
    private Oxen o1;
    private Oxen o2;
    private Oxen o3;
    private int currentPos; 
    private int lives;
    private int score;
    private HealthBar hbo1;
    private HealthBar hbo2;
    private HealthBar hbW;
    private ScoreRight livesC;
    private ScoreRight scoreC;

    private static final String bgImageName = "realbg.png";
    private static final double scrollSpeed = 2.5;
    private static final int picWidth = (new GreenfootImage(bgImageName)).getHeight();
    private GreenfootImage bgImage, bgBase;
    private int scrollPosition = 0;
    private int isPaused;
    private int enemyCountdown;
    private int enemyPos;
    private int level;

    private GreenfootSound[] music = {
            new GreenfootSound("BOSS.mp3"),
            new GreenfootSound("SHAMUS13.mp3"),
            new GreenfootSound("CRIMSON.mp3"),
            new GreenfootSound("d_teremi.mp3"),
            new GreenfootSound("J_CAVE.mid"),
            new GreenfootSound("D_23.mid")
        };

    private GreenfootImage[] levels = {
            new GreenfootImage("logo.png"),
            new GreenfootImage("lv1.png"),
            new GreenfootImage("lv2.png"),
            new GreenfootImage("lv3.png"),
            new GreenfootImage("lv4.png"),
            new GreenfootImage("lv5.png")
        };

    /**
     * Constructor for objects of class ManifestWorld.
     * 
     */
    public ManifestWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(900, 850, 1); 
        w = new Wagon();
        addObject(w,400,800);
        o1 = new Oxen();
        o2 = new Oxen();
        addObject(o1,330,800);
        addObject(o2,470,800);
        lives = 3;
        score = 0;
        level = 1;

        hbo1 = new HealthBar();
        hbo2 = new HealthBar();
        hbW = new HealthBar();
        HUD h = new HUD();
        HUD h2 = new HUD();
        ScoreText t = new ScoreText();
        Oxen1Health oh1 = new Oxen1Health();
        Oxen2Health oh2 = new Oxen2Health();
        WagonHealth wh = new WagonHealth();
        Logo l = new Logo();
        LiveCount lc = new LiveCount();
        livesC = new ScoreRight("");
        scoreC = new ScoreRight("");
        livesC.scoreUp(lives);
        PlayButton pl = new PlayButton();
        PauseButton pa = new PauseButton();
        ImageLoader il = new ImageLoader();

        addObject(h,70,420);
        addObject(h2,840,420);
        addObject(hbW,100,540);
        addObject(hbo2,100,420);
        addObject(hbo1,100,300);
        addObject(t,810,150);
        addObject(oh1,90,250);
        addObject(oh2,90,360);
        addObject(wh,90,480);
        addObject(l,82,48);
        addObject(lc,815,400);
        addObject(scoreC,856,213);
        addObject(livesC,897,463);
        addObject(pl,450,425);
        addObject(pa,831,820);
        addObject(il,0,0);

        setBackground(bgImageName);
        bgImage = new GreenfootImage(getBackground());
        bgBase = new GreenfootImage(getWidth(), picWidth);
        bgBase.drawImage(bgImage, 0, 0);
        isPaused = 1;
    }

    /**
     * Shifts position of the Horses
     */
    public void shiftPosition()
    {
        if(currentPos == 0){
            if(o1 != null){o1.setLocation(w.getX()-30,w.getY()-100);}
            if(o2 != null){o2.setLocation(w.getX()+30,w.getY()-100);}
            currentPos=1;
            w.setSpeed(4);
            o1.setSpeed(4);
            o2.setSpeed(4);
            return;
        }else
        if(currentPos == 1){
            if(o1 != null){o1.setLocation(w.getX()-60,w.getY()-35);}
            if(o2 != null){o2.setLocation(w.getX()+60,w.getY()-35);}
            currentPos=0;
            w.setSpeed(3);
            o1.setSpeed(3);
            o2.setSpeed(3);
        }
    }

    /**
     * Standard act method
     */
    public void act(){
        if(isPaused == 1){return;}
        if(getObjects(Oxen.class).isEmpty() || getObjects(Wagon.class).isEmpty()){
            lives--;
            livesC.scoreUp(-1);
            if(lives == 0){
                Greenfoot.stop(); 
                addObject(new Notifier(new GreenfootImage("gameover.png"),9999),450,425);
                throw new java.lang.IllegalStateException(); //this is here because otherwise you can just click play and resume :/
            }
            else{
                if(getObjects(Wagon.class).isEmpty()){
                    addObject(w,400,800);
                }
                if(getObjects(Oxen.class).size() !=2){
                    removeObject(o1);
                    removeObject(o2);
                    if(currentPos == 0){
                        addObject(o1,w.getX()-60,w.getY()-35);
                        addObject(o2,w.getX()+60,w.getY()-35);}
                    else{
                        addObject(o1,w.getX()-30,w.getY()-100);
                        addObject(o2,w.getX()+30,w.getY()-100);
                    }
                }
            }
            w.setHealth(50);
            o1.addHealth(100);
            o2.addHealth(100);
            o1.setIframes(200);
            o2.setIframes(200);
            w.setIframes(200);
        }

        hbW.update(w.getHealth());
        hbo1.update(o1.getHealth());
        hbo2.update(o2.getHealth());
        scoreC.setScore(score);
        music[level].playLoop();
        if(score % 10000 == 0 && score != 0){score += 100;lives++;livesC.scoreUp(1);}

        scrollPosition += scrollSpeed;
        while(scrollSpeed > 0 && scrollPosition < -picWidth) scrollPosition += picWidth;
        while(scrollSpeed < 0 && scrollPosition > 0) scrollPosition -= picWidth;
        paint(scrollPosition);
        if(scrollSpeed > 0 && scrollPosition >= picWidth) {
            scrollPosition = 0;
        }
        if(enemyCountdown <= 0){
            spawnIn(EnemyDB.getLevel(level)[enemyPos]);
            enemyPos++;
            if(enemyPos >= EnemyDB.getLevel(level).length){
                music[level].stop();
                level++;
                addObject(new Notifier(levels[level],65),450,425);
                enemyPos=0;
                if(level == 6){
                    Greenfoot.stop(); 
                    addObject(new Notifier(new GreenfootImage("youwin.png"),9999),450,425);
                    throw new java.lang.IllegalStateException(); //same reasons as above...
                }
            }
            enemyCountdown = EnemyDB.getLevel(level)[enemyPos].getSpawnDelay();
        }else{enemyCountdown--;}
    }

    /**
     * Sends many bullets forward from the designated point
     * @param x The x position
     * @param y The y Position
     */
    public void explodeIt(int x, int y){
        for(int angle = 0; angle < 405; angle += 45){
            Bullet2 bl = new Bullet2(-1);
            addObject(bl,x,y);
            bl.setRotation(angle);
        }
    }

    /**
     * Aims 3 bullets towards the wagon
     * @param x The x position of the bullet
     * @param y The y Position of the bullet
     */
    public void aimBullet(int x, int y)
    {
        Bullet2 bl = new Bullet2(-1);
        Bullet2 bl2 = new Bullet2(-1);
        Bullet2 bl3 = new Bullet2(-1);
        addObject(bl,x,y);
        bl.turnTowards(w.getX(),w.getY());
        addObject(bl2,x,y);
        bl2.turnTowards(w.getX(),w.getY());
        addObject(bl3,x,y);
        bl3.turnTowards(w.getX(),w.getY());
    }

    /**
     * Aims 1 bullet towards the wagon
     * @param x The x position of the bullet
     * @param y The y Position of the bullet
     * @param dir The direction of the Bullet
     */
    public void aimBullet(int x, int y, int dir)
    {
        Bullet2 bl = new Bullet2(dir,1);
        addObject(bl,x,y);
        try{bl.turnTowards(w.getX(),w.getY());}catch(java.lang.IllegalStateException e){}
    }

    /**
     * Aims 1 boomerang towards the wagon
     * @param x The x position of the bullet
     * @param y The y Position of the bullet
     * @param dir The direction of the Bullet
     */
    public void aimBoomerang(int x, int y, int dir)
    {
        Boomerang bl = new Boomerang(dir);
        addObject(bl,x,y);
        bl.turnTowards(w.getX(),w.getY());
    }

    /**
     * Aims 1 axe towards the wagon
     * @param x The x position of the bullet
     * @param y The y Position of the bullet
     * @param dir The direction of the Bullet
     */
    public void aimAxe(int x, int y, int dir)
    {
        Axe bl = new Axe(dir);
        addObject(bl,x,y);
        bl.turnTowards(w.getX(),w.getY());
        addObject(bl,x,y);
        bl.turnTowards(w.getX(),w.getY());
        addObject(bl,x,y);
        bl.turnTowards(w.getX(),w.getY());
    }

    /**
     * Spawns in a weapon at the designated location
     * @param x The X position
     * @param y The y Position
     */
    public void spawnWeapon(int x, int y){
        int num = Greenfoot.getRandomNumber(8);
        if(num == 0){addObject(new RegularWeapon(),x,y);}
        if(num == 1){addObject(new SineWeapon(),x,y);}
        if(num == 2){addObject(new SpreadWeapon(),x,y);}
        if(num == 3){addObject(new BurstWeapon(),x,y);}
        if(num == 4){addObject(new PlasmaWeapon(),x,y);}
        if(num == 5){addObject(new RapidFire(),x,y);}
        if(num == 6){addObject(new OxenHealth(),x,y);}
        if(num == 7){addObject(new Up1(),x,y);}
    }

    /**
     * Sets the weapon of the wagon
     * @param wep The weapon value to set
     */
    public void setWeapon(int wep){
        GreenfootSound p = new GreenfootSound("powerup.wav");
        p.play();
        w.setWeapon(wep);
    }

    /**
     * Sets the firing chances of the horses
     * @param chance The chance to set
     */
    public void setFireChances(int chance){
        if(o1 != null){o1.setFireChance(chance);}
        if(o2 != null){o1.setFireChance(chance);}
    }

    /**
     * Sends 1 bullet forwards and backwards from the specified location
     * @param x The x position
     * @param y The y position
     */
    public void explodeBullet(int x, int y){
        addObject(new Bullet1(1),x,y);
        addObject(new Bullet1(-1),x,y); 
    }

    /**
     * Sets a powerup to the wagon
     * @param pUp the powerup to set
     */
    public void setPowerUp(int pUp){
        GreenfootSound p = new GreenfootSound("powerup.wav");
        p.play();
        if(pUp == 1){
            if(o1 != null){o1.setFireChance(o1.getFireChance()/2);}
            if(o2 != null){o2.setFireChance(o2.getFireChance()/2);}
        }
        if(pUp == 2){
            if(o1 != null && o2 != null){
                o1.addHealth(25);
                o2.addHealth(25);}
            else{
                if(o1 == null){o2.addHealth(50);}else{o1.addHealth(50);}
            }
        }
        if(pUp == 3){lives++;livesC.scoreUp(1);}
    }

    private void paint(int position)
    {
        GreenfootImage bg = getBackground();
        bg.drawImage(bgBase, 0, position);
        bg.drawImage(bgImage, 0, position - picWidth);
    }

    /**
     * Adds score
     * @param pScore the score to ass
     */
    public void addScore(int pScore){
        score += pScore;
    }

    /**
     * Returns whether the game is paused or not
     * @return 1 if it is paused, 0 if not
     */
    public int getIsPaused(){
        return isPaused;
    }

    /**
     * Pauses or unpauses the game
     * @param set 1 to pause, 0 to unpause
     */
    public void setIsPaused(int set)
    {
        isPaused = set;
    }

    /**
     * Spawns an enemy based on an EnemyInfo object
     * @param e The EnemyInfo object to base the spawn on
     */
    private void spawnIn(EnemyInfo e){
        addObject(e.getActor(),e.getX(),e.getY());
    }
}
