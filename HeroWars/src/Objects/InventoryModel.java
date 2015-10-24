package Objects;

import oracle.sql.CHAR;

import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * Created by Роман on 11.10.2015.
 */
public class InventoryModel extends AbstractTableModel {

    private int columnCount = 5;
    private ArrayList<Item[]> inventorySlots;
    private DBHeroInfo dbHeroInfo;

    public InventoryModel(DBHeroInfo dbHeroInfo){
        this.dbHeroInfo = dbHeroInfo;
        inventorySlots = new ArrayList<Item[]>();
        for (int i = 0; i < inventorySlots.size() ; i++) {
            inventorySlots.add(new Item[getColumnCount()]);
        }
    }

    @Override
    public int getRowCount() {
        return inventorySlots.size();
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Item[] str = inventorySlots.get(columnIndex);
        return str[rowIndex];
    }

    public void addDate (Item[] row){
        inventorySlots.add(row.clone());
    }

    public void addItem(int rowIndex, int columnIndex, Item item){
        Item[] items = inventorySlots.get(columnIndex);
        items[rowIndex] = item;
        String itemID  = "" + String.format("%04d",item.getLevel()) + item.getSlotNumber() + item.itemRarityNumber()+
                String.format("%04d", item.getAddStrenght())+String.format("%04d", item.getAddAgility())
                +String.format("%04d", item.getAddIntellect())+String.format("%04d", item.getAddStamina());
        //System.out.println("Insert into Inventory values("+dbHeroInfo.getInventoryID()+","+itemID+","+rowIndex+columnIndex+")");
        if (item.getLevel()>0) {
            dbHeroInfo.getDbConnection().updateQuery("Insert into Inventory values(" + dbHeroInfo.getInventoryID() + ",'" + itemID + "','" + rowIndex + columnIndex + "')");
        }
        else {
       //     System.out.println("Delete from Inventory where Inventory_id = "+dbHeroInfo.getInventoryID()+" and position = "+rowIndex+columnIndex);
            dbHeroInfo.getDbConnection().updateQuery("Delete from Inventory where Inventory_id = "+dbHeroInfo.getInventoryID()+" and position = "+rowIndex+columnIndex);}
    }

    public void addItemFromBD(int rowIndex, int columnIndex, Item item){
        Item[] items = inventorySlots.get(columnIndex);
        items[rowIndex] = item;
    }

    public void init(){
        ResultSet resultSet = dbHeroInfo.getDbConnection().selectQuery("Select Item, position From Inventory where Inventory_id = "+dbHeroInfo.getInventoryID());
        try {
            while (resultSet.next()){
             this.addItemFromBD(Integer.parseInt(resultSet.getString("position").substring(0,1)),
                     Integer.parseInt(resultSet.getString("position").substring(1,2)),
                     new Item(resultSet.getString("Item")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
