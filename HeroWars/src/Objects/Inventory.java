package Objects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Роман on 11.10.2015.
 */
public class Inventory extends JTable {

    private InventoryModel inventoryModel;
    private InventoryCellRenderer inventoryCellRenderer = new InventoryCellRenderer();

    public Inventory(InventoryModel inventoryModel) {
        super(inventoryModel);
        this.inventoryModel = inventoryModel;
        Item[] data = new Item[5];
        data[0] = new Item("", "", 0, 0, 0, 0, 0);
        data[1] = new Item("", "", 0, 0, 0, 0, 0);
        data[2] = new Item("", "", 0, 0, 0, 0, 0);
        data[3] = new Item("", "", 0, 0, 0, 0, 0);
        data[4] = new Item("", "", 0, 0, 0, 0, 0);

        inventoryModel.addDate(data);
        inventoryModel.addDate(data);
        inventoryModel.addDate(data);
        inventoryModel.addDate(data);
        inventoryModel.addDate(data);

        this.setGridColor(Color.BLUE);
        this.setSelectionBackground(Color.WHITE);
        this.setSelectionForeground(Color.BLACK);
        this.setRowHeight(30);

        for (int i = 0; i < this.getColumnCount(); i++) {
            this.getColumnModel().getColumn(i).setCellRenderer(inventoryCellRenderer);
        }
    }

    @Override
    public String getToolTipText(MouseEvent event) {
        String result = "";
        int column = columnAtPoint(event.getPoint());
        int row = rowAtPoint(event.getPoint());
        if (this.getModel().getValueAt(row, column).toString().equals("")) {
            return "";
        } else {
            Item currentItem = (Item) this.getModel().getValueAt(row, column);
            String tooltipText = "<HTML> ";
            if (currentItem.getAddStrenght() > 0) {tooltipText = tooltipText + "Strenght: " + currentItem.getAddStrenght() + "<br>";}
            if (currentItem.getAddAgility() > 0) {tooltipText = tooltipText + "Agility: " + currentItem.getAddAgility() + "<br>";}
            if (currentItem.getAddIntellect() > 0) {tooltipText = tooltipText + "Intellect: " + currentItem.getAddIntellect() + "<br>";}
            if (currentItem.getAddStamina() > 0) {tooltipText = tooltipText + "Stamina: " + currentItem.getAddStamina() + "<br>";}
            return tooltipText;
        }
    }


    public Dimension getFreeSlot() {

        for (int j = 0; j < this.getRowCount(); j++) {
            for (int i = 0; i < this.getColumnCount(); i++) {
                if (((Item) inventoryModel.getValueAt(j, i)).getLevel() == 0) {
                    return new Dimension(i, j);
                }

            }
        }
        return new Dimension(-1,-1);
    }
}
