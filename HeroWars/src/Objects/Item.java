package Objects;

/**
 * Created by Роман on 12.10.2015.
 */
public class Item {
    private String type;
    private String rarity;
    private int level;
    private int slotNumber = -1;

    private int addStrenght;
    private int addAgility;
    private int addIntellect;
    private int addStamina;

    public Item(String type, String rarity, int level, int addStrenght, int addAgility, int addIntellect, int addStamina) {
        this.type = type;
        this.rarity = rarity;
        this.level = level;
        this.addStrenght = addStrenght;
        this.addAgility = addAgility;
        this.addIntellect = addIntellect;
        this.addStamina = addStamina;

        if (this.getType().equals("Head")){this.slotNumber = 0;}
        if (this.getType().equals("Chest")){this.slotNumber = 1;}
        if (this.getType().equals("Hand")){this.slotNumber = 2;}
        if (this.getType().equals("Legs")){this.slotNumber = 3;}
        if (this.getType().equals("Ring")){this.slotNumber = 4;}
        if (this.getType().equals("Head slot")){this.slotNumber = 0;}
        if (this.getType().equals("Chest slot")){this.slotNumber = 1;}
        if (this.getType().equals("Hand slot")){this.slotNumber = 2;}
        if (this.getType().equals("Legs slot")){this.slotNumber = 3;}
        if (this.getType().equals("Ring slot")){this.slotNumber = 4;}
    }
    public Item(String itemID){

        if (itemID.substring(4,5).equals("0")) {this.type = "Head";}
        if (itemID.substring(4,5).equals("1")) {this.type = "Chest";}
        if (itemID.substring(4,5).equals("2")) {this.type = "Hand";}
        if (itemID.substring(4,5).equals("3")) {this.type = "Legs";}
        if (itemID.substring(4,5).equals("4")) {this.type = "Ring";}

        if (itemID.substring(5, 6).equals("0")){this.rarity = "Common";}
        if (itemID.substring(5, 6).equals("1")){this.rarity = "Uncommon";}
        if (itemID.substring(5, 6).equals("2")){this.rarity = "Rare";}
        if (itemID.substring(5, 6).equals("3")){this.rarity = "Epic";}
        if (itemID.substring(5, 6).equals("4")){this.rarity = "Legend";}

        this.level = Integer.parseInt(itemID.substring(0, 4));
        this.addStrenght = Integer.parseInt(itemID.substring(6, 10));
        this.addAgility = Integer.parseInt(itemID.substring(10, 14));
        this.addIntellect = Integer.parseInt(itemID.substring(14, 18));
        this.addStamina = Integer.parseInt(itemID.substring(18, 22));
        this.slotNumber = Integer.parseInt(itemID.substring(4, 5));
    }
    public static Item generateRandomItemOnLvl(int level){

        int slot = (int)(Math.random() * 5);

        int rarity = 1 + (int)(Math.random() * 100);

        if (rarity <= 70){rarity = 0;}
        if ((rarity > 70) && (rarity <= 90)){rarity = 1;}
        if ((rarity > 90) && (rarity <= 96)){rarity = 2;}
        if ((rarity > 96) && (rarity <= 99)){rarity = 3;}
        if (rarity == 100){rarity = 4;}

        int strenght = (int)(Math.random() * 101);
        int agility = (int)(Math.random() * 101);
        int intellect = (int)(Math.random() * 101);
        int stamina = (int)(Math.random() * 101);

        double pointCost = (level + (int)Math.pow(2,rarity)-1)/(double)(strenght + stamina + agility + intellect);

        strenght = (int) (strenght * pointCost);
        agility = (int) (agility * pointCost);
        intellect = (int) (intellect * pointCost);
        stamina = (int) (stamina * pointCost);

        int lostPoint = (level + (int)Math.pow(2,rarity)-1) - stamina - strenght - agility - intellect;

        if (stamina >= strenght && stamina >= agility && stamina >= intellect ) {stamina = stamina + lostPoint;}
        if (strenght >= stamina && strenght >= agility && strenght >= intellect ) {strenght = strenght + lostPoint;}
        if (agility >= stamina && agility >= strenght && agility >= intellect ) {agility = agility + lostPoint;}
        if (intellect >= stamina && intellect >= strenght && intellect >= agility ) {intellect = intellect + lostPoint;}


        String itemID  = "" + String.format("%04d",level) + slot + rarity+
                String.format("%04d", strenght)+String.format("%04d", agility)
                +String.format("%04d", intellect)+String.format("%04d", stamina);

        return new Item(itemID);
    }
    @Override
    public String toString(){
        if (this.getLevel() > 0) {
            return this.getType() + " " + this.getLevel();
        }
        else {return this.getType();}
    };

    public int itemRarityNumber(){
        if (this.getRarity().equals("Common")){return 0;}
        if (this.getRarity().equals("Uncommon")){return 1;}
        if (this.getRarity().equals("Rare")){return 2;}
        if (this.getRarity().equals("Epic")){return 3;}
        if (this.getRarity().equals("Legend")){return 4;}
        return -1;
    }


    @Override
    public Item clone(){
       return (new Item(this.getType(),this.getRarity(),this.getLevel(),this.getAddStrenght(),this.getAddAgility(),this.addIntellect,this.getAddStamina()));
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {this.type = type;}

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public int getAddStrenght() {
        return addStrenght;
    }

    public void setAddStrenght(int addStrenght) {
        this.addStrenght = addStrenght;
    }

    public int getAddAgility() {
        return addAgility;
    }

    public void setAddAgility(int addAgility) {
        this.addAgility = addAgility;
    }

    public int getAddStamina() {
        return addStamina;
    }

    public void setAddStamina(int addStamina) {
        this.addStamina = addStamina;
    }

    public int getAddIntellect() {
        return addIntellect;
    }

    public void setAddIntellect(int addIntellect) {
        this.addIntellect = addIntellect;
    }

    public int getSlotNumber() {return slotNumber;}
}
