package Objects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Роман on 12.10.2015.
 */
public class CharacterItemsPanel extends JTable {

    private InventoryCellRenderer inventoryCellRenderer = new InventoryCellRenderer();
    private CharacterItemsPanelModel characterItemsPanelModel;

    public CharacterItemsPanel(CharacterItemsPanelModel characterItemsPanelModel) {
        super(characterItemsPanelModel);

        this.characterItemsPanelModel = characterItemsPanelModel;
        Item data = new Item("", "", 0, 0, 0, 0, 0);

        characterItemsPanelModel.addDate(new Item("Head slot", "", 0, 0, 0, 0, 0));
        characterItemsPanelModel.addDate(new Item("Chest slot", "", 0, 0, 0, 0, 0));
        characterItemsPanelModel.addDate(new Item("Hand slot", "", 0, 0, 0, 0, 0));
        characterItemsPanelModel.addDate(new Item("Legs slot", "", 0, 0, 0, 0, 0));
        characterItemsPanelModel.addDate(new Item("Ring slot", "", 0, 0, 0, 0, 0));

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
        if ( ((Item) this.getModel().getValueAt(row, column)).getLevel() == 0){return null;}
        else
            return "<HTML> Strenght: " + ((Item) this.getModel().getValueAt(row, column)).getAddStrenght() +"<br>"
                    + "Agility: " +  ((Item) this.getModel().getValueAt(row, column)).getAddAgility() +"<br>"
                    + "Intellect: " +  ((Item) this.getModel().getValueAt(row, column)).getAddIntellect() +"<br>"
                    + "Stamina: " +  ((Item) this.getModel().getValueAt(row, column)).getAddStamina() +"<br>";
    }

    public void addItem(int rowIndex, int columnIndex, Item item, Hero hero){
            hero.setAnyItem(item);
            characterItemsPanelModel.addItem(rowIndex,columnIndex,item);
    }
}
