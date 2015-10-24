package Objects;

/**
 * Created by Роман on 21.10.2015.
 */
public class DBHeroInfo {
    DBConnection dbConnection;
    private Hero hero;
    private int inventoryID;

    public DBHeroInfo(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public Hero getHero() {return hero;}
    public void setHero(Hero hero) {this.hero = hero;}

    public int getInventoryID() {return inventoryID;}
    public void setInventoryID(int inventoryID) {this.inventoryID = inventoryID;}

    public DBConnection getDbConnection() {return dbConnection;}
    public void setDbConnection(DBConnection dbConnection) {this.dbConnection = dbConnection;}

}
