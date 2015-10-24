package Objects;

import javax.xml.crypto.Data;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Роман on 16.10.2015.
 */
public class HeroWatch extends Date implements Runnable {

    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    private int apAdd = 0;
    private int mSecAdd = 0;

    public HeroWatch() {
    }

    public HeroWatch(Date startTime) {
        //startTime = new Date(startTime);
        double src = (new Date().getTime() - startTime.getTime())/10000.0;
        this.apAdd = (int)src; //целая часть
        src = src - this.apAdd; //дробная часть
        this.mSecAdd = (int)(src * 10000);

    }

    public void addListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public void removeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
    SimpleDateFormat formattedDate = new SimpleDateFormat("dd.MM.yy HH:mm:ss");

    public int getApAdd() {return apAdd;}

    @Override
    public String toString(){
        return formattedDate.format(this).toString();
    }

    @Override
    public void run() {
        while(true)  {
            try {
                Thread.sleep(10000-this.mSecAdd);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                this.mSecAdd = 0;
                support.firePropertyChange("heroWatch", this, new HeroWatch());

        }
    }
}
