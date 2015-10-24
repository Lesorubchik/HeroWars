package Objects;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Роман on 12.10.2015.
 */
public class CharacterItemsPanelModel extends AbstractTableModel {

    private int columnCount = 5;
    private DBHeroInfo dbHeroInfo;
    private ArrayList<Item> inventorySlots;
    public CharacterItemsPanelModel(DBHeroInfo dbHeroInfo){
        this.dbHeroInfo = dbHeroInfo;
        inventorySlots = new ArrayList<Item>();
        for (int i = 0; i < inventorySlots.size() ; i++) {
            inventorySlots.add(new Item("","",0,0,0,0,0));
        }
    }

    @Override
    public int getRowCount() {
        return 1;//inventorySlots.size();
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return inventorySlots.get(columnIndex);
    }

    public void addDate (Item row){
        inventorySlots.add(row.clone());
    }
    public void addItem(int rowIndex, int columnIndex, Item item) {

        inventorySlots.set(columnIndex, item);

        String itemID  = "" + String.format("%04d",item.getLevel()) + item.getSlotNumber() + item.itemRarityNumber()+
                String.format("%04d", item.getAddStrenght())+String.format("%04d", item.getAddAgility())
                +String.format("%04d", item.getAddIntellect())+String.format("%04d", item.getAddStamina());
    //    System.out.println("Insert into Inventory values("+dbHeroInfo.getInventoryID()+","+itemID+","+rowIndex+columnIndex+")");
        String dbSlot="";
        if (item.getSlotNumber()==0) {dbSlot="Head_slot";}
        if (item.getSlotNumber()==1) {dbSlot="Chest_slot";}
        if (item.getSlotNumber()==2) {dbSlot="Hand_slot";}
        if (item.getSlotNumber()==3) {dbSlot="Legs_slot";}
        if (item.getSlotNumber()==4) {dbSlot="Ring_slot";}

        if (item.getLevel()>0) {
        //    System.out.println("UPDATE Heroes set "+dbSlot+"=" + itemID +" Where name = "+dbHeroInfo.getHero().getName());
            dbHeroInfo.getDbConnection().updateQuery("UPDATE Heroes set "+dbSlot+"= '" + itemID +"' Where name = '"+dbHeroInfo.getHero().getName()+"'");
        }
        else {
      //      System.out.println("Update Heroes set "+dbSlot+"= null where name = '"+ dbHeroInfo.getHero().getName()+"'");
            dbHeroInfo.getDbConnection().updateQuery("Update Heroes set "+dbSlot+"= null where name = '"+ dbHeroInfo.getHero().getName()+"'");
        }
    }
    public void addItemFromBD(int rowIndex, int columnIndex, Item item) {
        inventorySlots.set(columnIndex, item);
    }

    public void init(){
     //   System.out.println("Select Head_slot, Chest_slot, Hand_slot, Legs_slot, Ring_slot " +
     //           "From Heroes where name = "+dbHeroInfo.getHero().getName());
        ResultSet resultSet = dbHeroInfo.getDbConnection().selectQuery("Select nvl(Head_slot,'1'), nvl(Chest_slot,'1'),  nvl(Hand_slot,'1'),  nvl(Legs_slot,'1'),  nvl(Ring_slot,'1') " +
                                                                       "From Heroes where name = '"+dbHeroInfo.getHero().getName()+"'");
        try {
            resultSet.next();
          //  System.out.println(resultSet.getString(1));
          //  System.out.println(resultSet.getString(2));
         //   System.out.println(resultSet.getString(3));
         //   System.out.println(resultSet.getString(4));
        //    System.out.println(resultSet.getString(5));
            if (resultSet.getString(1).equals("1")== false){this.addItemFromBD(0, 0, new Item(resultSet.getString(1))); dbHeroInfo.getHero().setAnyItem(new Item(resultSet.getString(1)));}
            if (resultSet.getString(2).equals("1")== false){this.addItemFromBD(0, 1, new Item(resultSet.getString(2))); dbHeroInfo.getHero().setAnyItem(new Item(resultSet.getString(2)));}
            if (resultSet.getString(3).equals("1")== false){this.addItemFromBD(0, 2, new Item(resultSet.getString(3))); dbHeroInfo.getHero().setAnyItem(new Item(resultSet.getString(3)));}
            if (resultSet.getString(4).equals("1")== false){this.addItemFromBD(0, 3, new Item(resultSet.getString(4))); dbHeroInfo.getHero().setAnyItem(new Item(resultSet.getString(4)));}
            if (resultSet.getString(5).equals("1")== false){this.addItemFromBD(0, 4, new Item(resultSet.getString(5))); dbHeroInfo.getHero().setAnyItem(new Item(resultSet.getString(5)));}

        } catch (SQLException e) {
           // e.printStackTrace();
        }
    }
}