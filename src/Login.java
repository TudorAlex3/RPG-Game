import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Login extends JFrame implements ActionListener {
    JPanel panel;
    JLabel user_label, password_label, message;
    JTextField user;
    JPasswordField password;
    JButton submit;
    ArrayList<Account> accounts;
    String selected_name;
    public boolean success;

    Login(String name, ArrayList<Account> users) {
        accounts =  users;
        selected_name = name;
        success = false;

        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,150);
        setVisible(true);
        setLocationRelativeTo(null);

        Verify();
    }

    public void Verify() {
        user_label = new JLabel();
        user_label.setText("Email");
        user = new JTextField();

        password_label = new JLabel();
        password_label.setText("Password");
        password = new JPasswordField();

        submit = new JButton("LOGIN");
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.white);
        submit.addActionListener(this);

        message = new JLabel();

        panel = new JPanel(new GridLayout(3, 2));
        panel.add(user_label);
        panel.add(user);
        panel.add(password_label);
        panel.add(password);
        panel.add(message);
        panel.add(submit);

        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String email = user.getText();
        String get_password = password.getText();

        boolean exist = false;
        int i;
        for (i=0; i<accounts.size(); i++) {
            if (accounts.get(i).account.credential.getEmail().equals(email)) {
                exist = true;
                break;
            } else {
                message.setText("Invalid user... ");
            }
        }

        if (exist) {
            String right_password = accounts.get(i).account.credential.getPassword();
            String right_email = accounts.get(i).account.credential.getEmail();

            if (password == null)
                message.setText("Invalid user... ");
            if (email.equals(right_email) && get_password.equals(right_password)) {
                message.setText("Hello, " + selected_name + "!");
                success = true;
                setVisible(false);
            }
            else
                message.setText("Invalid user... ");
        }
    }
}