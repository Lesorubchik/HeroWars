package ListnersPackege;

import Objects.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Роман on 14.10.2015.
 */
public class EquipButtonActionListner implements ActionListener {

    private Inventory inventory;
    private CharacterItemsPanel characterItemsPanel;
    private Hero hero;

    public EquipButtonActionListner(CharacterItemsPanel characterItemsPanel, Inventory inventory, Hero hero) {
        this.characterItemsPanel = characterItemsPanel;
        this.inventory = inventory;
        this.hero = hero;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Item selectedItem;
        InventoryModel inventoryModel = ((InventoryModel) inventory.getModel());
        CharacterItemsPanelModel characterItemsPanelModel = ((CharacterItemsPanelModel) characterItemsPanel.getModel());
        String lustChange = ((InventoryCellRenderer) inventory.getColumnModel().getColumn(0).getCellEditor()).getLustChange().toString();

        if (lustChange.equals("class Objects.InventoryModel")){characterItemsPanel.clearSelection();}
        else {inventory.clearSelection();}

        if (inventory.getSelectedColumn() > -1) {
            selectedItem = (Item) inventoryModel.getValueAt(inventory.getSelectedRow(), inventory.getSelectedColumn());
            if (selectedItem.getLevel() > 0) {

                Item characterItem = ((Item) characterItemsPanelModel.getValueAt(0, selectedItem.getSlotNumber()));
                if (characterItem.getLevel() == 0) {
                    characterItem = new Item("", "", 0, 0, 0, 0, 0);
                }

                if (selectedItem.getSlotNumber() > -1) {
                    characterItemsPanel.addItem(0, selectedItem.getSlotNumber(), selectedItem, hero);
                    inventoryModel.addItem(inventory.getSelectedRow(),inventory.getSelectedColumn(), new Item("", "", 0, 0, 0, 0, 0));
                    inventoryModel.addItem(inventory.getSelectedRow(),inventory.getSelectedColumn(), characterItem);
                    inventory.repaint();
                    characterItemsPanel.repaint();
                    inventory.clearSelection();

                }
            }
        }
        if (characterItemsPanel.getSelectedColumn() > -1) {
            selectedItem = (Item) characterItemsPanelModel.getValueAt(characterItemsPanel.getSelectedRow(), characterItemsPanel.getSelectedColumn());

            if (selectedItem.getSlotNumber() > -1) {
                Dimension slot = inventory.getFreeSlot();
                inventoryModel.addItem(slot.height,slot.width,selectedItem);
                characterItemsPanel.addItem(0,selectedItem.getSlotNumber(),new Item(selectedItem.getType() + " slot","",0,0,0, 0,0),hero);
                inventory.repaint();
                characterItemsPanel.repaint();
                characterItemsPanel.clearSelection();
            }
        }
    }

}
