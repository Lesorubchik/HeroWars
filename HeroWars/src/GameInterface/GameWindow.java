package GameInterface;

import ListnersPackege.EquipButtonActionListner;
import ListnersPackege.WindowCloseListner;
import Objects.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Created by Роман on 10.10.2015.
 */
public class GameWindow extends JFrame {

    private PropertyChangeSupport support = new PropertyChangeSupport(this);
    public void addListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public GameWindow(DBHeroInfo dbHeroInfo) {
        super("Hero Wars character wondow");
        JLabel helloLabel = new JLabel("Hello " + dbHeroInfo.getHero().getName());

        JButton actionsButton = new JButton("Actions");
        JButton equipButton = new JButton("Use Item");
        JButton deleteButton = new JButton("Delete from inventory");

        StatsPanel statsPanel = new StatsPanel(dbHeroInfo.getHero());
        dbHeroInfo.getHero().init();
        JLabel inventoryLabel = new JLabel("Inventory");
        JPanel inventoryPanel = new JPanel();
        InventoryModel inventoryModel = new InventoryModel(dbHeroInfo);
        Inventory inventory = new Inventory(inventoryModel);
        inventoryModel.init();
        inventoryPanel.add(inventory);
        dbHeroInfo.getHero().setInventory(inventory);
        dbHeroInfo.getHero().setInventoryModel(inventoryModel);

        JLabel characterItemsLabel = new JLabel("Character Items");
        CharacterItemsPanelModel characterItemsPanelModel = new CharacterItemsPanelModel(dbHeroInfo);
        JPanel characterPanel = new JPanel();
        CharacterItemsPanel characterItemsPanel = new CharacterItemsPanel(characterItemsPanelModel);
        characterPanel.add(characterItemsPanel);
        characterItemsPanelModel.init();
        dbHeroInfo.getHero().setCharacterItemsPanel(characterItemsPanelModel);

        super.setLayout(new GridBagLayout());
        super.setMinimumSize(new Dimension(400, 470));
        super.setResizable(false);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dbHeroInfo.getHero().heroStartTimer();
        this.addWindowListener(new WindowCloseListner(dbHeroInfo));

        super.add(helloLabel, new GridBagConstraints(0, 0, 4, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.CENTER, new Insets(1, 2, 3, 4), 0, 0));
        super.add(statsPanel, new GridBagConstraints(0, 1, 2, 7, 0.0, 0.9, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1, 2, 3, 4), 0, 0));
        super.add(actionsButton, new GridBagConstraints(2, 1, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.CENTER, new Insets(1, 2, 3, 4), 0, 0));
        super.add(characterItemsLabel, new GridBagConstraints(0, 8, 4, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.CENTER, new Insets(1, 2, 3, 4), 0, 0));
        super.add(characterPanel, new GridBagConstraints(0, 9, 5, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1, 2, 3, 4), 0, 0));
        super.add(inventoryLabel, new GridBagConstraints(0, 10, 4, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.NORTH, new Insets(1, 2, 3, 4), 0, 0));
        super.add(inventoryPanel, new GridBagConstraints(0, 11, 4, 4, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1, 2, 3, 4), 0, 0));
        super.add(equipButton, new GridBagConstraints(0, 15, 1, 1, 0, 1, GridBagConstraints.NORTH, GridBagConstraints.CENTER, new Insets(1, 2, 3, 4), 0, 0));
        super.add(deleteButton, new GridBagConstraints(1, 15, 1, 1, 0, 1, GridBagConstraints.NORTH, GridBagConstraints.CENTER, new Insets(1, 2, 3, 4), 0, 0));


        equipButton.addActionListener(new EquipButtonActionListner(characterItemsPanel, inventory, dbHeroInfo.getHero()));

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String lustChange = ((InventoryCellRenderer) inventory.getColumnModel().getColumn(0).getCellEditor()).getLustChange().toString();

                if (lustChange.equals("class Objects.InventoryModel")){characterItemsPanel.clearSelection();}
                else {inventory.clearSelection();}

                if (characterItemsPanel.getSelectedColumn() < 0) {
                    inventoryModel.addItem(inventory.getSelectedRow(), inventory.getSelectedColumn(), new Item("", "", 0, 0, 0, 0, 0));
                    inventory.repaint();
                }
            }
        });

        actionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {support.firePropertyChange("actionWindow", 1, 2);}
        });


        super.setVisible(true);
       // super.pack();
    }
}
