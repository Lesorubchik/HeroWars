import GameInterface.ActionsWindow;
import GameInterface.LoginWindow;
import GameInterface.GameWindow;
import Objects.DBConnection;
import Objects.DBHeroInfo;
import Objects.Hero;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Роман on 09.10.2015.
 */

public class Start implements PropertyChangeListener {
    private Hero myHero;
    private GameWindow gameWindow;
    private ActionsWindow actionsWindow;
    private LoginWindow loginWindow;
    private static DBConnection dbConnection = new DBConnection("Amuro","localhost","dpplgs","HeroWars");
    private static DBHeroInfo dbHeroInfo;

    public static void main(String[] args) {
        dbConnection.initProp();
        dbConnection.init();
        LoginWindow loginWindow =  new LoginWindow(dbConnection);
        loginWindow.addListener(new Start());
      //  GameWindow gameWindow = new GameWindow(new Hero("Amuro"));
      //  ActionsWindow actionsWindow = new ActionsWindow(new Hero("Amuro"));


    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        //Введён правильный пароль
        if (event.getPropertyName().equals("passwordCorrect")) {
        //    System.out.println("Variable \"" + event.getPropertyName() + "\" has new value: " + event.getNewValue());
            loginWindow = (LoginWindow)event.getSource();
            ((LoginWindow)event.getSource()).dispose();
            myHero = new Hero((String)event.getNewValue());
            dbHeroInfo = new DBHeroInfo(dbConnection);
            myHero.setDbHeroInfo(dbHeroInfo);
            ResultSet resultSet = dbConnection.selectQuery("Select INVENTORY_ID from Heroes where name = '" + event.getNewValue() + "'");
            try {
                resultSet.next();
                dbHeroInfo.setInventoryID(Integer.valueOf(resultSet.getString(1)));
            } catch (SQLException e) {
                dbConnection.updateQuery("Insert into Heroes values('"+event.getNewValue()+"',1,1,1,1,null,null,null,null,null,INVENTORY_SEQ.nextval,1,0,20,'')");
                resultSet = dbConnection.selectQuery("Select INVENTORY_ID from Heroes where name = '" + event.getNewValue() + "'");
                try {
                    resultSet.next();
                    dbHeroInfo.setInventoryID(Integer.valueOf(resultSet.getString(1)));
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            dbHeroInfo.setHero(myHero);
            gameWindow = new GameWindow(dbHeroInfo);
            gameWindow.addListener(this);
        }

        if (event.getPropertyName().equals("actionWindow")) {
        //    System.out.println("Variable \"" + event.getPropertyName() + "\" has new value: " + event.getNewValue());
            if (actionsWindow == null){
            ((GameWindow)event.getSource()).setVisible(false);
            actionsWindow = new ActionsWindow(myHero);
            actionsWindow.addListener(this);}
            else {actionsWindow.setVisible(true);gameWindow.setVisible(false);}
        }

        if (event.getPropertyName().equals("characterMenu")) {
            actionsWindow.setVisible(false);
            gameWindow.setVisible(true);
        }

    }
}
