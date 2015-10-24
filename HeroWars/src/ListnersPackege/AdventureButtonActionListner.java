package ListnersPackege;

import GameInterface.ActionsWindow;
import Objects.Adventure;
import Objects.Hero;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * Created by Роман on 18.10.2015.
 */
public class AdventureButtonActionListner implements ActionListener {

    private Hero hero;
    private ActionsWindow actionsWindow;

    public AdventureButtonActionListner(Hero hero, ActionsWindow actionsWindow) {
        this.hero = hero;
        this.actionsWindow = actionsWindow;
    }


    @Override
        public void actionPerformed(ActionEvent e) {

        if ((Adventure.isAdventureActive()== false) && (hero.getActionPoints()-5 > -1)) {
            hero.setActionPoints(hero.getActionPoints()-5);
            Adventure currentAdventure = new Adventure(hero);
            actionsWindow.getMyHealth().setMinimum(0);
            actionsWindow.getMyHealth().setMaximum(currentAdventure.getHero().getHealth());
            actionsWindow.getMyHealth().setValue((currentAdventure.getCurrentBattleStats().getCurrentHP1Player()));
            actionsWindow.getMyHealth().setString(Integer.toString(currentAdventure.getCurrentBattleStats().getCurrentHP1Player()));
            actionsWindow.getMyHealth().setStringPainted(true);
            actionsWindow.getMyHealth().setForeground(Color.PINK);

            actionsWindow.getMyMana().setMinimum(0);
            actionsWindow.getMyMana().setMaximum(currentAdventure.getHero().getMana());
            actionsWindow.getMyMana().setValue(currentAdventure.getCurrentBattleStats().getCurrentMP1Player());
            actionsWindow.getMyMana().setString(Integer.toString(currentAdventure.getCurrentBattleStats().getCurrentMP1Player()));
            actionsWindow.getMyMana().setStringPainted(true);

            actionsWindow.getEnemyHealth().setMinimum(0);
            actionsWindow.getEnemyHealth().setMaximum(currentAdventure.getEnemy().getHealth());
            actionsWindow.getEnemyHealth().setValue(currentAdventure.getCurrentBattleStats().getCurrentHP2Player());
            actionsWindow.getEnemyHealth().setString(Integer.toString(currentAdventure.getCurrentBattleStats().getCurrentHP2Player()));
            actionsWindow.getEnemyHealth().setStringPainted(true);
            actionsWindow.getEnemyHealth().setForeground(Color.PINK);

            actionsWindow.getEnemyMana().setMinimum(0);
            actionsWindow.getEnemyMana().setMaximum(currentAdventure.getEnemy().getMana());
            actionsWindow.getEnemyMana().setValue(currentAdventure.getCurrentBattleStats().getCurrentMP2Player());
            actionsWindow.getEnemyMana().setString(Integer.toString(currentAdventure.getCurrentBattleStats().getCurrentMP2Player()));
            actionsWindow.getEnemyMana().setStringPainted(true);
            currentAdventure.addListener(actionsWindow);
            actionsWindow.getCharachterBook().append(actionsWindow.getFormattedDate().format(new Date()).toString() + ": Battle begin!" + "\n");
            actionsWindow.getCharachterBook().setCaretPosition(actionsWindow.getCharachterBook().getDocument().getLength());
            actionsWindow.addListener(currentAdventure);
            Thread thread = new Thread(currentAdventure);

            thread.start();
        }
    }
}
