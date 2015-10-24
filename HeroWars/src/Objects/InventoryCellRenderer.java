package Objects;


import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * Created by Роман on 11.10.2015.
 */

public class InventoryCellRenderer extends DefaultTableCellRenderer {

    public static String getLustChange() {
        return lustChange;
    }

    private static String lustChange;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = (JLabel)super.
                getTableCellRendererComponent(table,value, isSelected, hasFocus,row, column);
        //label.setVerticalTextPosition(SwingConstants.CENTER);
        label.setHorizontalAlignment(CENTER);
       // label.setForeground(Color.);

        if (hasFocus){
            label.setBackground(Color.lightGray);
            lustChange = table.getModel().getClass().toString();
         //   System.out.println(lustChange);
        }
        else {
            label.setBackground(Color.WHITE);
        }

        if ((value instanceof Item) && (((Item) value).getRarity().equals("Uncommon")))  {
            label.setForeground(Color.GREEN);
        }
        else  if ((value instanceof Item) && (((Item) value).getRarity().equals("Rare")))  {
            label.setForeground(Color.BLUE);
        }
        else  if ((value instanceof Item) && (((Item) value).getRarity().equals("Epic")))  {
            label.setForeground(Color.MAGENTA);
        }
        else  if ((value instanceof Item) && (((Item) value).getRarity().equals("Rare")))  {
            label.setForeground(Color.ORANGE);
        }
        else {label.setForeground(Color.BLACK);}

        return label;
    }
}
