package ListnersPackege;

import Objects.DBHeroInfo;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Роман on 22.10.2015.
 */
public class WindowCloseListner extends WindowAdapter {

    private DBHeroInfo dbHeroInfo;
    private SimpleDateFormat formattedDate = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");

    public WindowCloseListner(DBHeroInfo dbHeroInfo) {
        this.dbHeroInfo = dbHeroInfo;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        dbHeroInfo.getDbConnection().updateQuery("Update Heroes set AP_time = '" + formattedDate.format(new Date()) + "' where name = '" + dbHeroInfo.getHero().getName() + "'");
        System.exit(0);
    }
}

