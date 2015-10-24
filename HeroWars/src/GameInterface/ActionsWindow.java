package GameInterface;

import ListnersPackege.AdventureButtonActionListner;
import ListnersPackege.WindowCloseListner;
import Objects.Adventure;
import Objects.BattleStats;
import Objects.Hero;
import Objects.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Роман on 16.10.2015.
 */
public class ActionsWindow extends JFrame implements PropertyChangeListener {

    private JProgressBar myHealth = new JProgressBar();
    private JProgressBar myMana = new JProgressBar();
    private JProgressBar enemyHealth = new JProgressBar();
    private JProgressBar enemyMana = new JProgressBar();
    private JButton adventureButton = new JButton("Adventure");
    private JButton arena = new JButton("Arena");
    private JButton instance = new JButton("Instance");
    private JButton characterMenu = new JButton("Character Menu");
    private JButton fastBattle = new JButton("Fast battle");
    private JProgressBar timeBar = new JProgressBar();
    private JTextArea charachterBook = new JTextArea();
    private Date date = new Date();
    private SimpleDateFormat formattedDate = new SimpleDateFormat("HH:mm:ss");
    private Hero hero;

    private PropertyChangeSupport support = new PropertyChangeSupport(this);
    public void addListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public JTextArea getCharachterBook() {return charachterBook;}
    public SimpleDateFormat getFormattedDate() {return formattedDate;}

    public ActionsWindow(Hero hero) {
        super("Hero Wars actions wondow");
        this.hero = hero;
        super.setLayout(new GridBagLayout());
        super.setMinimumSize(new Dimension(400, 470));
      //  super.setResizable(false);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel heroName = new JLabel(hero.getName());
        JLabel heroAction = new JLabel("My Action");


       // myHealth.setBackground(Color.PINK);
        myHealth.setForeground(Color.PINK);
        myHealth.setMinimum(0);
        myHealth.setMaximum(100);
        myHealth.setValue(30);
        myHealth.setString("Health");
        myHealth.setStringPainted(true);

        myMana.setMinimum(0);
        myMana.setMaximum(100);
        myMana.setValue(30);
        myMana.setString("Mana");
        myMana.setStringPainted(true);

        JLabel enemyName = new JLabel("Enemy");
        JLabel enemyAction = new JLabel("Enemy Action");


       // enemyHealth.setBackground(Color.PINK);
        enemyHealth.setForeground(Color.PINK);
        enemyHealth.setMinimum(0);
        enemyHealth.setMaximum(100);
        enemyHealth.setValue(30);
        enemyHealth.setString("Health");
        enemyHealth.setStringPainted(true);


        enemyMana.setMinimum(0);
        enemyMana.setMaximum(100);
        enemyMana.setValue(30);
        enemyMana.setString("Mana");
        enemyMana.setStringPainted(true);

        timeBar.setMinimum(0);
        timeBar.setMaximum(100);
        timeBar.setValue(30);
        timeBar.setString("Time");
        timeBar.setStringPainted(true);

        JButton skill1 = new JButton("Empty");
        JButton skill2 = new JButton("Empty");
        JButton skill3 = new JButton("Empty");
        JButton skill4 = new JButton("Empty");


        charachterBook.setLineWrap(true);
        charachterBook.setWrapStyleWord(true);
        charachterBook.setForeground(Color.GRAY);
        charachterBook.setMinimumSize(new Dimension(100,100));
        JScrollPane charachterBookScrollPane = new JScrollPane(charachterBook);
        charachterBook.setEditable(false);
        charachterBookScrollPane.setVisible(true);

        this.addWindowListener(new WindowCloseListner(hero.getDbHeroInfo()));

      //  actionHeroPanel.setMaximumSize(new Dimension(10,10));

        JPanel actionPanel = new JPanel(new GridBagLayout());
        actionPanel.setVisible(true);

        JPanel buttonActionPanel = new JPanel(new GridBagLayout());
        actionPanel.setVisible(true);

        actionPanel.add(heroName, new GridBagConstraints(0, 0, 1, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(1, 1, 1, 1), 0, 0));
        actionPanel.add(heroAction, new GridBagConstraints(1, 0, 1, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(0, 0, 0, 0), 0, 0));
        actionPanel.add(myHealth, new GridBagConstraints(2, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(0, 0, 0, 0), 0, 0));
        actionPanel.add(myMana, new GridBagConstraints(2, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(0, 0, 0, 0), 0, 0));

        actionPanel.add(enemyName, new GridBagConstraints(0, 2, 1, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(0, 0, 0, 0), 0, 0));
        actionPanel.add(enemyAction, new GridBagConstraints(1, 2, 1, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(0, 0, 0, 0), 0, 0));
        actionPanel.add(enemyHealth, new GridBagConstraints(2, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(0, 0, 0, 0), 0, 0));
        actionPanel.add(enemyMana, new GridBagConstraints(2, 3, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(0, 0, 0, 0), 0, 0));

        actionPanel.add(timeBar, new GridBagConstraints(0, 4, 4, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

        buttonActionPanel.add(skill1, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        buttonActionPanel.add(skill2, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        buttonActionPanel.add(skill3, new GridBagConstraints(2, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        buttonActionPanel.add(skill4, new GridBagConstraints(3, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        buttonActionPanel.add(fastBattle, new GridBagConstraints(0, 1, 4, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(0, 0, 0, 0), 0, 0));


        actionPanel.add(buttonActionPanel, new GridBagConstraints(0, 5, 3, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(0, 0, 0, 0), 0, 0));

        super.add(actionPanel, new GridBagConstraints(0, 0, 3, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1, 1, 1, 1), 0, 0));

        super.add(adventureButton, new GridBagConstraints(0, 1, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.CENTER, new Insets(1, 1, 1, 1), 0, 0));
        super.add(arena, new GridBagConstraints(1, 1, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.CENTER, new Insets(1, 1, 1, 1), 0, 0));
        super.add(instance, new GridBagConstraints(2, 1, 1, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.CENTER, new Insets(1, 1, 1, 1), 0, 0));
        super.add(characterMenu, new GridBagConstraints(0, 2, 3, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.CENTER, new Insets(1, 1, 1, 1), 0, 0));

        super.add(charachterBookScrollPane, new GridBagConstraints(0, 3, 3, 1, 0, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

      //  super.add(actionHeroPanel, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.SOUTH, GridBagConstraints.CENTER, new Insets(1, 2, 3, 4), 0, 0));

    //    super.add(new JLabel("123"), new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1, 2, 3, 4), 0, 0));
        super.setVisible(true);
        super.pack();
        adventureButton.addActionListener(new AdventureButtonActionListner(hero,this));
        skill1.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){support.firePropertyChange("stepComplete",1,2);}});
        characterMenu.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {support.firePropertyChange("characterMenu",1,2);}});
        fastBattle.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {support.firePropertyChange("fastBattle",1,2);}});
    }
    public JProgressBar getEnemyHealth() {return enemyHealth;}
    public JProgressBar getMyHealth() {return myHealth;}
    public JProgressBar getMyMana() {return myMana;}
    public JProgressBar getEnemyMana() {return enemyMana;}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("playerWin")){
            Hero winHero = ((Hero) evt.getNewValue());
            Item newItem = Item.generateRandomItemOnLvl(winHero.getLevel());

            this.getCharachterBook().append(this.getFormattedDate().format(new Date()).toString() + ": " + winHero.getName()+ " Win!" +"\n");
            this.getCharachterBook().append(this.getFormattedDate().format(new Date()).toString() + ":  You gain: "+
                    (((hero.getLevel())/2)+1)+
                    " Exp"+"\n");
            hero.addExp(((hero.getLevel())/2)+1);
            if (hero.getInventory().getFreeSlot().height > -1) {
                this.getCharachterBook().append(this.getFormattedDate().format(new Date()).toString() + ": " + winHero.getName()+ " get new "+ newItem.getRarity()+" item: " + newItem.toString()+"\n");
                hero.getInventoryModel().addItem(hero.getInventory().getFreeSlot().height, hero.getInventory().getFreeSlot().width, newItem);
                this.charachterBook.setCaretPosition(charachterBook.getDocument().getLength());
            }
            else { this.getCharachterBook().append(this.getFormattedDate().format(new Date()).toString() + ": " + winHero.getName()+ " cant get new "+ newItem.getRarity()+" item: " + newItem.toString()+" becouse inventory full"+"\n");
                this.charachterBook.setCaretPosition(charachterBook.getDocument().getLength());
            }
        }

        if (evt.getPropertyName().equals("enemyWin")){
            Hero winHero = ((Hero) evt.getNewValue());
            this.getCharachterBook().append(this.getFormattedDate().format(new Date()).toString() + ": " + winHero.getName()+ " Win!" +"\n");
            this.getCharachterBook().append(this.getFormattedDate().format(new Date()).toString() + ":  You gain: "+
                    ((hero.getLevel())/2)+1+
                    " Exp"+"\n");
            hero.addExp(((hero.getLevel())/2)+1);
            this.charachterBook.setCaretPosition(charachterBook.getDocument().getLength());
        }
        if (evt.getPropertyName().equals("stepTimer")){
            timeBar.setMinimum(0);
            timeBar.setMaximum(15);
            timeBar.setString(Integer.toString((int)evt.getNewValue()));
            timeBar.setStringPainted(true);
            timeBar.setValue((int)evt.getNewValue());
            timeBar.repaint();
        }
        if (evt.getPropertyName().equals("EnemyDodge")){
            Hero hero = (Hero)(evt.getNewValue());
            this.getCharachterBook().append(this.getFormattedDate().format(new Date()).toString() + ": " + hero.getName()+ " Dodge!" +"\n");
            this.charachterBook.setCaretPosition(charachterBook.getDocument().getLength());
        }
        if (evt.getPropertyName().equals("HeroDodge")){
            Hero hero = (Hero)(evt.getNewValue());
            this.getCharachterBook().append(this.getFormattedDate().format(new Date()).toString() + ": " + hero.getName()+ " dodge!" +"\n");
            this.charachterBook.setCaretPosition(charachterBook.getDocument().getLength());
        }
        if (evt.getPropertyName().equals("currentBattleStats")){
            BattleStats battleStats = (BattleStats)evt.getNewValue();


            myHealth.setValue(battleStats.getCurrentHP1Player());
            myHealth.setString(Integer.toString((battleStats.getCurrentHP1Player())));
            myHealth.repaint();
          //  myHealth.setStringPainted(true);
          //  myHealth.setForeground(Color.PINK);

            myMana.setValue((battleStats.getCurrentMP1Player()));
            myMana.setString(Integer.toString(battleStats.getCurrentMP1Player()));
            myMana.repaint();
           // myMana.setStringPainted(true);

            enemyHealth.setValue(battleStats.getCurrentHP2Player());
            enemyHealth.setString(Integer.toString(battleStats.getCurrentHP2Player()));
            enemyHealth.repaint();
        //    enemyHealth.setStringPainted(true);
        //    enemyHealth.setForeground(Color.PINK);


            enemyMana.setValue(battleStats.getCurrentMP2Player());
            enemyMana.setString(Integer.toString(battleStats.getCurrentMP2Player()));
            enemyMana.repaint();
         //   enemyMana.setStringPainted(true);
            charachterBook.append(this.getFormattedDate().format(new Date()) + " Step-" + battleStats.getStepCount()+
                    ": "+((Adventure) evt.getSource()).getHero().getName()+": " + battleStats.getCurrentHP1Player()+
                    "  "+((Adventure) evt.getSource()).getEnemy().getName()+": " + battleStats.getCurrentHP2Player()+
                    "\n");
           // charachterBook.append(this.getFormattedDate().format(new Date()) + " Step-" + battleStats.getStepCount()+": "+((Adventure) evt.getSource()).getEnemy().getName()+": " + battleStats.getCurrentHP2Player()+"\n");
            charachterBook.setCaretPosition(charachterBook.getDocument().getLength());


        }
    }
}
