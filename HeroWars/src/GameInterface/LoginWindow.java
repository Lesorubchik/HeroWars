package GameInterface;

import Objects.DBConnection;
import Objects.DBHeroInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Роман on 09.10.2015.
 */
public class LoginWindow extends JFrame {

    private boolean passwordCorrect = false;
    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void addListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }


    public boolean getPasswordCorrect() {
        return passwordCorrect;
    }

public LoginWindow(DBConnection dbConnection){
    super("Hero Wars login wondow");
    JLabel nameLabel = new JLabel("Name");
    JLabel passwordLabel = new JLabel("Password");

    JTextField nameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();

    JButton loginButton = new JButton("Login");
    JButton registrationButton = new JButton("Registration");

    super.setLayout(new GridBagLayout());
    super.setMinimumSize(new Dimension(280, 110));
    super.setResizable(false);
    super.setLocationRelativeTo(null);
    super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    super.add(nameLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(1, 2, 3, 4), 0, 0));
    super.add(nameField, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(1, 2, 3, 4), 0, 0));
    super.add(passwordLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(1, 2, 3, 4), 0, 0));
    super.add(passwordField, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(1, 2, 3, 4), 0, 0));
    super.add(loginButton, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(1, 2, 3, 4), 0, 0));
    super.add(registrationButton, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.9, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(1, 2, 3, 4), 0, 0));

    super.setVisible(true);
    super.pack();

    registrationButton.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            ResultSet resultSet = dbConnection.selectQuery("Select 1 from Users where name='" + nameField.getText() + "'");
         //   System.out.println("Select 1 from Users where name='" + nameField.getText() );
            try {
                resultSet.next();
                if (resultSet.getString(1).equals("1")) {
                    JOptionPane.showMessageDialog(null, "User already exist");
                }
            } catch (SQLException e1) {
                dbConnection.updateQuery("Insert into Users values ('" + nameField.getText() + "','" + new String(passwordField.getPassword()) + "')");
                JOptionPane.showMessageDialog(null, "User added");
            }
        }
    });

    loginButton.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
           ResultSet resultSet = dbConnection.selectQuery("Select 1 from Users where name='" + nameField.getText() + "' and password='" + new String(passwordField.getPassword()) + "'");
          //  System.out.println("Select 1 from Users where name='" + nameField.getText() + "' and password='" + new String(passwordField.getPassword()) + "'");
            try {
                resultSet.next();
                if (resultSet.getString(1).equals("1")){
                    boolean oldValue = passwordCorrect;
                    passwordCorrect = true;
                    JOptionPane.showMessageDialog(null, "Information correct");
                    support.firePropertyChange("passwordCorrect", oldValue, nameField.getText());}
            } catch (SQLException e1) {
                JOptionPane.showMessageDialog(null, "Password or name wrong");

              }
        }
    });

}

}
