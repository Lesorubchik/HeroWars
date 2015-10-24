package Objects;

import com.sun.deploy.panel.NodeBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Created by Роман on 12.10.2015.
 */
public class StatsPanel extends JPanel implements PropertyChangeListener {

    JTextField strenghtField = new JTextField();
    JTextField agilityField = new JTextField();
    JTextField intellectField = new JTextField();
    JTextField staminaField = new JTextField();
    JTextField levelField = new JTextField();
    JTextField expField = new JTextField();
    JTextField actionPointsField= new JTextField();
    JTextField healthField= new JTextField();
    JTextField manaField= new JTextField();

    JButton strenghtAddButton = new JButton("+");
    JButton agilityAddButton = new JButton("+");
    JButton intellectAddButton = new JButton("+");
    JButton staminaAddButton = new JButton("+");

    JLabel statsLabel = new JLabel("Stats:");
    JLabel strenghtLabel = new JLabel("Strenght");
    JLabel agilityLabel = new JLabel("Agility");
    JLabel intellectLabel = new JLabel("Intellect");
    JLabel staminaLabel = new JLabel("Stamina");
    JLabel levelLabel = new JLabel("Level");
    JLabel expLabel = new JLabel("Exp");
    JLabel actionPointsLabel = new JLabel("Action Points");
    JLabel healthLabel = new JLabel("Health");
    JLabel manaLabel = new JLabel("Mana");


    public StatsPanel(Hero hero){
        super();
        this.setLayout(new GridBagLayout());
        hero.addListener(this);

        strenghtAddButton.setMinimumSize(new Dimension(20,20));
        strenghtAddButton.setBorder(new NodeBorder(Color.BLACK,1));
       // strenghtAddButton.setFocusPainted(false);
       // strenghtAddButton.setBorderPainted(false);
       // strenghtAddButton.setFont(new Font("Italic",2,14));
        agilityAddButton.setMinimumSize(new Dimension(20,20));
        agilityAddButton.setBorder(new NodeBorder(Color.BLACK,1));
        intellectAddButton.setMinimumSize(new Dimension(20,20));
        intellectAddButton.setBorder(new NodeBorder(Color.BLACK,1));
        staminaAddButton.setMinimumSize(new Dimension(20,20));
        staminaAddButton.setBorder(new NodeBorder(Color.BLACK,1));
        strenghtAddButton.setVisible(false);
        agilityAddButton.setVisible(false);
        intellectAddButton.setVisible(false);
        staminaAddButton.setVisible(false);

        strenghtField = new JTextField(Integer.toString(hero.getStrenght()));
        strenghtField.setEditable(false);
        strenghtField.setMinimumSize(new Dimension(40,20));
        agilityField = new JTextField(Integer.toString(hero.getAgility()));
        agilityField.setEditable(false);
        agilityField.setMinimumSize(new Dimension(40,20));
        intellectField = new JTextField(Integer.toString(hero.getIntellect()));
        intellectField.setEditable(false);
        intellectField.setMinimumSize(new Dimension(40,20));
        staminaField = new JTextField(Integer.toString(hero.getStamina()));
        staminaField.setEditable(false);
        staminaField.setMinimumSize(new Dimension(40,20));
        levelField = new JTextField(Integer.toString(hero.getLevel()));
        levelField.setEditable(false);
        levelField.setMinimumSize(new Dimension(40,20));
        expField = new JTextField(Double.toString(((double) Math.round(((double) hero.getExpirience()) * 10000 / ((double) hero.getMaxExpForLevel())))/100)+"%");
        expField.setEditable(false);
        expField.setMinimumSize(new Dimension(50,20));
        actionPointsField = new JTextField(Integer.toString(hero.getActionPoints()));
        actionPointsField.setEditable(false);
        actionPointsField.setMinimumSize(new Dimension(40,20));
        healthField = new JTextField(Integer.toString(hero.getHealth()));
        healthField.setEditable(false);
        healthField.setMinimumSize(new Dimension(40,20));
        manaField = new JTextField(Integer.toString(hero.getMana()));
        manaField.setEditable(false);
        manaField.setMinimumSize(new Dimension(40,20));

        super.add(statsLabel, new GridBagConstraints(0, 0, 5, 1, 0.1, 0.9, GridBagConstraints.NORTH, GridBagConstraints.CENTER, new Insets(1, 2, 3, 4), 0, 0));
        super.add(strenghtLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(1, 2, 3, 4), 0, 0));
        super.add(strenghtField, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH, GridBagConstraints.NORTH, new Insets(1, 2, 3, 4), 0, 0));
        super.add(strenghtAddButton, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.9, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(1, 2, 3, 4), 0, 0));
        super.add(actionPointsLabel, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(1, 2, 3, 4), 0, 0));
        super.add(actionPointsField, new GridBagConstraints(4, 1, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH, GridBagConstraints.NORTH, new Insets(1, 2, 3, 4), 0, 0));
        super.add(agilityLabel, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(1, 2, 3, 4), 0, 0));
        super.add(agilityField, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH, GridBagConstraints.NORTH, new Insets(1, 2, 3, 4), 0, 0));
        super.add(agilityAddButton, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.9, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(1, 2, 3, 4), 0, 0));
        super.add(healthLabel, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(1, 2, 3, 4), 0, 0));
        super.add(healthField, new GridBagConstraints(4, 2, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH, GridBagConstraints.NORTH, new Insets(1, 2, 3, 4), 0, 0));
        super.add(intellectLabel, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(1, 2, 3, 4), 0, 0));
        super.add(intellectField, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH, GridBagConstraints.NORTH, new Insets(1, 2, 3, 4), 0, 0));
        super.add(intellectAddButton, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.9, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(1, 2, 3, 4), 0, 0));
        super.add(manaLabel, new GridBagConstraints(3, 3, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(1, 2, 3, 4), 0, 0));
        super.add(manaField, new GridBagConstraints(4, 3, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH, GridBagConstraints.NORTH, new Insets(1, 2, 3, 4), 0, 0));
        super.add(staminaLabel, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(1, 2, 3, 4), 0, 0));
        super.add(staminaField, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH, GridBagConstraints.NORTH, new Insets(1, 2, 3, 4), 0, 0));
        super.add(staminaAddButton, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.9, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(1, 2, 3, 4), 0, 0));
        super.add(levelLabel, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH, GridBagConstraints.NORTH, new Insets(1, 2, 3, 4), 0, 0));
        super.add(levelField, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH, GridBagConstraints.NORTH, new Insets(1, 2, 3, 4), 0, 0));
        super.add(expLabel, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH, GridBagConstraints.NORTH, new Insets(1, 2, 3, 4), 0, 0));
        super.add(expField, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH, GridBagConstraints.NORTH, new Insets(1, 2, 3, 4), 0, 0));

        strenghtAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hero.setStrenght(hero.getStrenght()+1);
                hero.setLevelStats(hero.getLevelStats()-1);
                hero.getDbHeroInfo().getDbConnection().updateQuery("Update Heroes set strenght = strenght + 1 Where name = '"+hero.getName()+"'");
                if (hero.getLevelStats() < 1){
                    strenghtAddButton.setVisible(false);
                    agilityAddButton.setVisible(false);
                    intellectAddButton.setVisible(false);
                    staminaAddButton.setVisible(false);
                }
            }
        });

        agilityAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hero.setAgility(hero.getAgility() + 1);
                hero.getDbHeroInfo().getDbConnection().updateQuery("Update Heroes set agility = agility + 1 Where name = '"+hero.getName()+"'");
                hero.setLevelStats(hero.getLevelStats()-1);
                if (hero.getLevelStats() < 1){
                    strenghtAddButton.setVisible(false);
                    agilityAddButton.setVisible(false);
                    intellectAddButton.setVisible(false);
                    staminaAddButton.setVisible(false);
                }
            }
        });

        intellectAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hero.setIntellect(hero.getIntellect()+1);
                hero.setLevelStats(hero.getLevelStats()-1);
                hero.getDbHeroInfo().getDbConnection().updateQuery("Update Heroes set intellect = intellect + 1 Where name = '"+hero.getName()+"'");
                if (hero.getLevelStats() < 1){
                    strenghtAddButton.setVisible(false);
                    agilityAddButton.setVisible(false);
                    intellectAddButton.setVisible(false);
                    staminaAddButton.setVisible(false);
                }
            }
        });

        staminaAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hero.setStamina(hero.getStamina()+1);
                hero.setLevelStats(hero.getLevelStats()-1);
                hero.getDbHeroInfo().getDbConnection().updateQuery("Update Heroes set stamina = stamina + 1 Where name = '"+hero.getName()+"'");
                if (hero.getLevelStats() < 1){
                    strenghtAddButton.setVisible(false);
                    agilityAddButton.setVisible(false);
                    intellectAddButton.setVisible(false);
                    staminaAddButton.setVisible(false);
                }
            }
        });

        this.setVisible(true);
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("strenght")){strenghtField.setText(Integer.toString(((int) evt.getNewValue())));}
        if (evt.getPropertyName().equals("agility")){agilityField.setText(Integer.toString(((int) evt.getNewValue())));}
        if (evt.getPropertyName().equals("intellect")){
            intellectField.setText(Integer.toString(((int) evt.getNewValue())));
            manaField.setText(Integer.toString(((int) evt.getNewValue())*10));
        }
        if (evt.getPropertyName().equals("stamina")){
            staminaField.setText(Integer.toString(((int) evt.getNewValue())));
            healthField.setText(Integer.toString(((int) evt.getNewValue())*10));
        }
        if (evt.getPropertyName().equals("level")){levelField.setText(Integer.toString(((int) evt.getNewValue())));}
        if (evt.getPropertyName().equals("expirience")){expField.setText(Double.toString(((double) Math.round(((int) evt.getNewValue()) * 10000 / ((int) evt.getOldValue())))/100)+"%");}
        if (evt.getPropertyName().equals("actionPoints")){actionPointsField.setText(Integer.toString(((int) evt.getNewValue())));}
        if (evt.getPropertyName().equals("levelStats")){
            if ((int)evt.getNewValue() > 0) {
                strenghtAddButton.setVisible(true);
                agilityAddButton.setVisible(true);
                intellectAddButton.setVisible(true);
                staminaAddButton.setVisible(true);
            }
            else {
                strenghtAddButton.setVisible(false);
                agilityAddButton.setVisible(false);
                intellectAddButton.setVisible(false);
                staminaAddButton.setVisible(false);
            }
        }
    }
}
