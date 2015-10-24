package Objects;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Created by Роман on 18.10.2015.
 */
public class Adventure implements Runnable, PropertyChangeListener {

    private PropertyChangeSupport support = new PropertyChangeSupport(this);
    public void addListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    private Hero hero;
    private Hero enemy;
    private BattleStats currentBattleStats;
    private boolean stepFlag = false;
    private static boolean adventureActive = false;
    private boolean fastBattle = false;

    public Adventure(Hero hero) {
        this.hero = hero;

        this.enemy = new Hero("Enemy");

        int enemyDificult =  (int)(Math.random() * 5);
        int strenght = (int)(Math.random() * 101);
        int agility = (int)(Math.random() * 101);
        int intellect = (int)(Math.random() * 101);
        int stamina = (int)(Math.random() * 101);
        double pointCost = (hero.getAgility() + hero.getIntellect() + hero.getStamina() + hero.getStrenght() + enemyDificult * 4)/(double)(strenght + stamina + agility + intellect);

        strenght = (int) (strenght * pointCost);
        agility = (int) (agility * pointCost);
        intellect = (int) (intellect * pointCost);
        stamina = (int) (stamina * pointCost);

        int lostPoint = (hero.getAgility() + hero.getIntellect() + hero.getStamina() + hero.getStrenght() + enemyDificult * 4) - stamina - strenght - agility - intellect;

        stamina = stamina + lostPoint;

        this.enemy.setStamina(stamina);
        this.enemy.setStrenght(strenght);
        this.enemy.setIntellect(intellect);
        this.enemy.setAgility(agility);
        this.currentBattleStats = new BattleStats(hero.getHealth(),enemy.getHealth(),
                hero.getMana(),enemy.getMana(),
                "1","2",
                "1","2");
    }
    public static boolean isAdventureActive() {return adventureActive;}
    public void setAdventureActive(boolean adventureActive) {
        Adventure.adventureActive = adventureActive;}

    public Hero getHero() {return hero;}

    public Hero getEnemy() {return enemy;}

    public BattleStats getCurrentBattleStats() {return currentBattleStats;}

    public void battleStep(BattleStats newStep){
        newStep.setStepCount(newStep.getStepCount()+1);
        int dmg1 = enemy.getStrenght();
        int dmg2 = hero.getStrenght();
        
        double dodge;
        if (enemy.getAgility() > hero.getAgility()){dodge = enemy.getAgility()/ (double) hero.getAgility();}
        else {dodge = hero.getAgility()/ (double) enemy.getAgility();}
        dodge = dodge * 10 - 10.0;
        
        if (dodge > 50.0) {dodge = 50.0;}
        System.out.println("dodge = " + dodge);
        System.out.println(hero.getAgility());
        System.out.println(enemy.getAgility());
        if ((Math.random() * 101) <= dodge){
            if (enemy.getAgility() > hero.getAgility()){dmg2 = 0;support.firePropertyChange("EnemyDodge",newStep , enemy);}
            else {dmg1 = 0;support.firePropertyChange("HeroDodge",newStep , hero);}
        }
        
        this.currentBattleStats = new BattleStats(newStep.getCurrentHP1Player()- dmg1,newStep.getCurrentHP2Player()-dmg2,
                                hero.getMana(),enemy.getMana(),
                               "1","2",
                               "1","2");
        support.firePropertyChange("currentBattleStats",newStep , this.currentBattleStats);

      //  return this.currentBattleStats;
    }

   // public int damageDeal(Hero hero){return 1 + (int)(Math.random() * ((hero.getStrenght() - 1) + 1));}

    @Override
    public void run() {
        this.setAdventureActive(true);
        currentBattleStats.setStepCount(0);
        while ((currentBattleStats.getCurrentHP1Player()>0) && (currentBattleStats.getCurrentHP2Player()>0)){
           int i = 0;
            for (i = 0 ; i <16 ; i++) {
                if (this.stepFlag == true){break;}
                support.firePropertyChange("stepTimer",-1 , i);
                if (fastBattle == false) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
           if ((i == 16) || (stepFlag)) {
               this.stepFlag = false;
               this.battleStep(this.currentBattleStats);}
        }
        if (currentBattleStats.getCurrentHP1Player()<=0){support.firePropertyChange("enemyWin",1, this.getEnemy());this.setAdventureActive(false);}
        if (currentBattleStats.getCurrentHP2Player()<=0){support.firePropertyChange("playerWin",1, this.getHero());this.setAdventureActive(false);}
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("stepComplete")){
            this.stepFlag = true;
        }
        if (evt.getPropertyName().equals("fastBattle")){
            this.fastBattle = true;
        }


    }
}


