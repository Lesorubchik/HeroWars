package Objects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Роман on 20.10.2015.
 */
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {
    private String name;
    private String password;
    private String dbName;
    private String host;
    private String url;
    private Connection myConnection;

    public DBConnection(String name, String host, String password, String dbName) {
        this.name = name;
        this.host = host;
        this.password = password;
        this.dbName = dbName;
    }

    public void finalize(){
        try {
            myConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void initProp(){
        url = "jdbc:oracle:thin:@//" + host +":1521/xe";//+dbName;
      //  jdbc:oracle:thin:@//oracle.hostserver2.mydomain.ca:1522/ABCD
      //  url = "jdbc:oracle://" + host +"/" + "dbName";
        System.out.println(url);
    }

    public void init() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            myConnection = DriverManager.getConnection(url,name,password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet selectQuery(String query){
        ResultSet result = null;
        try {
            Statement statement = myConnection.createStatement();
            result = statement.executeQuery(query);
            //result.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void updateQuery(String query){
        try {
            Statement statement = myConnection.createStatement();
             statement.executeUpdate(query);
            //result.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}