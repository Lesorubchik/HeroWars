package Objects;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Роман on 14.10.2015.
 */
public class Hero implements PropertyChangeListener{

    private PropertyChangeSupport support = new PropertyChangeSupport(this);
    public void addListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public void removeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    private String name;
    private int strenght=1;
    private int agility=1;
    private int intellect=1;
    private int stamina=1;
    private int level=1;
    private int expirience = 0;
    private int maxExpForLevel = 10;
    private int actionPoints = 10;
    private int health ;
    private int mana;
    private int levelStats = 0;
    private InventoryModel inventoryModel;
    private Inventory inventory;
    private CharacterItemsPanelModel characterItemsPanel;
    private DBHeroInfo dbHeroInfo;


    private HeroWatch heroWatch = new HeroWatch();
    private Thread myThread = new Thread(heroWatch);

    private Item headSlot = new Item("","",0,0,0,0,0);
    private Item chestSlot = new Item("","",0,0,0,0,0);
    private Item handSlot = new Item("","",0,0,0,0,0);
    private Item legsSlot = new Item("","",0,0,0,0,0);
    private Item ringSlot = new Item("","",0,0,0,0,0);

    public Hero(String name) {
        this.name = name;
        this.level = 1;
        this.strenght = 1;
        this.agility = 1;
        this.intellect = 1;
        this.stamina = 1;
        this.actionPoints = 1;
        this.health = 10 * this.getStamina();
        this.mana = 10 * this.getIntellect();
    }

    public void init(){
        ResultSet resultSet = dbHeroInfo.getDbConnection().selectQuery("Select HeroLevel, exp, strenght, agility, intellect, stamina, action_points, nvl(ap_time,0) from Heroes where name = '"+this.getName()+"'");
        try {
            resultSet.next();
            this.setLevel(Integer.parseInt(resultSet.getString("HeroLevel")));
            this.maxExpForLevel = this.maxExpForLevel * ((int) Math.pow(2,this.getLevel()-1));
            this.setExpirience(Integer.parseInt(resultSet.getString("exp")));
            this.setStrenght(Integer.parseInt(resultSet.getString("strenght")));
            this.setAgility(Integer.parseInt(resultSet.getString("agility")));
            this.setIntellect(Integer.parseInt(resultSet.getString("intellect")));
            this.setStamina(Integer.parseInt(resultSet.getString("stamina")));
            this.setLevelStats(this.getLevel()+3-this.getStrenght()-this.getAgility()-this.getIntellect()-this.getStamina());
            this.setActionPoints(Integer.parseInt(resultSet.getString("action_points")));
            if (resultSet.getString(8).equals("0")){
                this.heroWatch = new HeroWatch(new Date());
            }
            else {
                this.heroWatch = new HeroWatch(new Date(resultSet.getString(8)));
            }
            if (this.getActionPoints() + this.heroWatch.getApAdd()>20){
                this.setActionPoints(20);
            }
            else {this.setActionPoints(this.getActionPoints() + this.heroWatch.getApAdd());}

            this.myThread = new Thread(heroWatch);
           // System.out.println(this.getLevelStats());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void heroStartTimer(){
        myThread.start();
        if (!this.name.equals("Enemy")) {heroWatch.addListener(this);}
    }
    public DBHeroInfo getDbHeroInfo() {return dbHeroInfo;}
    public void setDbHeroInfo(DBHeroInfo dbHeroInfo) {this.dbHeroInfo = dbHeroInfo;}

    public Inventory getInventory() {return inventory;}
    public void setInventory(Inventory inventory) {this.inventory = inventory;}

    public void addExp(int exp){
        int saveExp = this.expirience;
        if ((this.getExpirience() + exp) >= this.maxExpForLevel){
            //this.level = this.level + 1;
            this.setLevel(this.getLevel() + 1);
            dbHeroInfo.getDbConnection().updateQuery("Update Heroes set HeroLevel = "+this.getLevel()+" Where name = '"+ this.getName()+"'");
            this.maxExpForLevel = this.maxExpForLevel * 2;
            //this.expirience = 0;
            setExpirience(0);
            dbHeroInfo.getDbConnection().updateQuery("Update Heroes set exp = 0 where name = '"+this.getName()+"'");
            this.addExp((saveExp + exp)-this.maxExpForLevel/2);
        }
        else {
            //this.expirience = this.expirience + exp;
            this.setExpirience(this.getExpirience() + exp);
            dbHeroInfo.getDbConnection().updateQuery("Update Heroes set exp = "+this.expirience+" where name = '"+this.getName()+"'");
        }
    }
    public void changeItemStats(Item item){

        Item oldItem = new Item("","",0,0,0,0,0);

        if (item.getType().equals("Head")){oldItem = headSlot;}
        if (item.getType().equals("Chest")){oldItem = chestSlot;}
        if (item.getType().equals("Hand")){oldItem = handSlot;}
        if (item.getType().equals("Legs")){oldItem = legsSlot;}
        if (item.getType().equals("Ring")){oldItem = ringSlot;}
        if (item.getType().equals("Head slot")){oldItem = headSlot;}
        if (item.getType().equals("Chest slot")){oldItem = chestSlot;}
        if (item.getType().equals("Hand slot")){oldItem = handSlot;}
        if (item.getType().equals("Legs slot")){oldItem = legsSlot;}
        if (item.getType().equals("Ring slot")){oldItem = ringSlot;}

        setStrenght(this.getStrenght() - oldItem.getAddStrenght());
        setAgility(this.getAgility() - oldItem.getAddAgility());
        setIntellect(this.getIntellect() - oldItem.getAddIntellect());
        setStamina(this.getStamina() - oldItem.getAddStamina());

        setStrenght(this.getStrenght()+item.getAddStrenght());
        setAgility(this.getAgility()+item.getAddAgility());
        setIntellect(this.getIntellect()+item.getAddIntellect());
        setStamina(this.getStamina()+item.getAddStamina());
    }

    public CharacterItemsPanelModel getCharacterItemsPanel() {return characterItemsPanel;}
    public void setCharacterItemsPanel(CharacterItemsPanelModel characterItemsPanel) {this.characterItemsPanel = characterItemsPanel;}

    public InventoryModel getInventoryModel() {return inventoryModel;}
    public void setInventoryModel(InventoryModel inventoryModel) {this.inventoryModel = inventoryModel;}

    public int getLevelStats() {return levelStats;}
    public void setLevelStats(int levelStats) {
        support.firePropertyChange("levelStats", this.levelStats, levelStats);
        this.levelStats = levelStats;
    }

    public int getMana() {return mana;}
    public void setMana(int mana) {this.mana = mana;}

    public int getHealth() {return health;}
    public void setHealth(int health) {this.health = health;}

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getStrenght() {
        return strenght;
    }
    public void setStrenght(int strenght) {
        support.firePropertyChange("strenght", this.strenght, strenght);
        this.strenght = strenght;
        }


    public int getExpirience() {
        return expirience;
    }
    public void setExpirience(int expirience) {
        this.expirience = expirience;
        support.firePropertyChange("expirience", this.maxExpForLevel, this.expirience);
    }

    public int getActionPoints() {return actionPoints;}
    public void setActionPoints(int actionPoints) {
       // System.out.println(actionPoints);
       // System.out.println(this.getName());
       // System.out.println("Update Heroes set action_points = "+actionPoints+" where name = '"+this.getName()+"'");
        dbHeroInfo.getDbConnection().updateQuery("Update Heroes set action_points = "+actionPoints+" where name = '"+this.getName()+"'");
        support.firePropertyChange("actionPoints", this.actionPoints, actionPoints );
        this.actionPoints = actionPoints;
    }

    public int getMaxExpForLevel() {
        return maxExpForLevel;
    }
    public void setMaxExpForLevel(int maxExpForLevel) {
        this.maxExpForLevel = maxExpForLevel;
    }

    public int getAgility() {
        return agility;
    }
    public void setAgility(int agility) {
        support.firePropertyChange("agility", this.agility, agility);
        this.agility = agility;
    }

    public int getStamina() {return stamina;}
    public void setStamina(int stamina) {
        support.firePropertyChange("stamina", this.stamina, stamina);
        this.setHealth(stamina * 10);
        this.stamina = stamina;
    }

    public int getIntellect() {
        return intellect;
    }
    public void setIntellect(int intellect) {
        support.firePropertyChange("intellect", this.intellect, intellect);
        this.setMana(intellect * 10);
        this.intellect = intellect;
    }

    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
        support.firePropertyChange("level", this.getLevel() - 1, this.getLevel());
        setLevelStats(getLevelStats()+1);
    }

    public void setAnyItem(Item item){
        if (item.getType().equals("Head")){this.setHeadSlot(item);}
        if (item.getType().equals("Chest")){this.setChestSlot(item);}
        if (item.getType().equals("Hand")){this.setHandSlot(item);}
        if (item.getType().equals("Legs")){this.setLegsSlot(item);}
        if (item.getType().equals("Ring")){this.setRingSlot(item);}
        if (item.getType().equals("Head slot")){this.setHeadSlot(item);}
        if (item.getType().equals("Chest slot")){this.setChestSlot(item);}
        if (item.getType().equals("Hand slot")){this.setHandSlot(item);}
        if (item.getType().equals("Legs slot")){this.setLegsSlot(item);}
        if (item.getType().equals("Ring slot")){this.setRingSlot(item);}
    }

    public Item getHeadSlot() {
        return headSlot;
    }

    public void setHeadSlot(Item headSlot) {
        this.changeItemStats(headSlot);
        this.headSlot = headSlot;
    }

    public Item getChestSlot() {
        return chestSlot;
    }
    public void setChestSlot(Item chestSlot) {
        this.changeItemStats(chestSlot);
        this.chestSlot = chestSlot;
    }

    public Item getHandSlot() {
        return handSlot;
    }
    public void setHandSlot(Item handSlot) {
        this.changeItemStats(handSlot);
        this.handSlot = handSlot;
    }

    public Item getLegsSlot() {return legsSlot;}
    public void setLegsSlot(Item legsSlot) {
        this.changeItemStats(legsSlot);
        this.legsSlot = legsSlot;
    }

    public Item getRingSlot() {
        return ringSlot;
    }
    public void setRingSlot(Item ringSlot) {
        this.changeItemStats(ringSlot);
        this.ringSlot = ringSlot;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("heroWatch")) {
            if (getActionPoints() < 20) {
                this.setActionPoints(this.getActionPoints() + 1);
            }
        }
    }
}