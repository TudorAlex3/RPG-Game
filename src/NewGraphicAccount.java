import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

public class NewGraphicAccount extends JFrame implements ActionListener {
    public Account new_account;
    public boolean success;
    public boolean create;
    ArrayList fav_games;
    JTextField name;
    JTextField country;
    JTextField email;
    JPasswordField password;

    NewGraphicAccount () {
        setTitle("Create account");
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(300, 600));
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);

        fav_games = new ArrayList();
        success = true;
        create = false;
        Create();
    }

    public void Create() {
        name = new JTextField(20);
        name.setEnabled(true);
        JLabel label_name = new JLabel("Name");
        label_name.setSize(new Dimension(20,20));

        country = new JTextField(20);
        country.setEnabled(true);
        JLabel label_country = new JLabel("Country");
        label_country.setSize(new Dimension(20,20));

        JPanel name_country = new JPanel(new GridLayout(4,4));
        name_country.add(label_name);
        name_country.add(name);
        name_country.add(label_country);
        name_country.add(country);

        email = new JTextField(20);
        email.setEnabled(true);
        JLabel label_email = new JLabel("Email");
        label_email.setSize(new Dimension(20,20));

        password = new JPasswordField(20);
        password.setEnabled(true);
        JLabel label_password = new JLabel("Password");
        label_password.setSize(new Dimension(20,20));


        JPanel credentials = new JPanel(new GridLayout(4,4));
        credentials.add(label_email);
        credentials.add(email);
        credentials.add(label_password);
        credentials.add(password);

        JPanel games_button = new JPanel();
        JButton games = new JButton("ADD GAME");
        games.setBackground(Color.BLACK);
        games.setForeground(Color.white);
        JLabel label_games = new JLabel("Favorite games");
        label_games.setSize(new Dimension(20,20));
        games_button.add(games);

        games.addActionListener(e -> {
            JFrame game_name = new JFrame("Game");
            game_name.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
            game_name.setMinimumSize(new Dimension(300, 200));
            game_name.setLayout(new FlowLayout());
            game_name.setLocationRelativeTo(null);

            JTextField enter_name = new JTextField(20);
            enter_name.setEnabled(true);
            JButton add_game = new JButton("ADD");
            add_game.setBackground(Color.BLACK);
            add_game.setForeground(Color.white);
            add_game.addActionListener(f -> {
                game_name.setVisible(false);
                if (!enter_name.getText().equals(""))
                    if (fav_games.contains(enter_name.getText())) {
                        System.out.println("The game has already been introduced!");
                        System.out.println();
                    } else
                        fav_games.add(enter_name.getText());
            });

            JSplitPane sp = new JSplitPane(
                    JSplitPane.VERTICAL_SPLIT, enter_name, add_game);
            game_name.add(sp);
            game_name.setVisible(true);
        });


        JPanel add_games = new JPanel(new GridLayout(3,3));
        add_games.add(label_games);
        add_games.add(games_button);

        JButton create_account = new JButton("CREATE ACCOUNT");
        create_account.setBackground(Color.BLACK);
        create_account.setForeground(Color.white);
        create_account.addActionListener(this);

        JSplitPane sp = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT, name_country, credentials);
        JSplitPane sp1 = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT, sp, add_games);
        JSplitPane sp2 = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT, sp1, create_account);
        add(sp2);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new_account = new Account();
        Account.Information.InfoBuilder build = new Account.Information.InfoBuilder();

        if (name.getText().equals(""))
            name = null;
        else
            build.name(name.getText());
        build.country(country.getText());
        new_account.setGames_number(0);

        Credentials cred = new Credentials();
        if (email.getText().equals("") || password.getText().equals(""))
            cred = null;
        else {
            cred.setEmail(email.getText());
            cred.setPassword(password.getText());

        }
        build.credentials(cred);

        SortedSet favorite_games = new TreeSet(fav_games);
        build.favorite_games(favorite_games);

        new_account.all_characters = new ArrayList<>();

        Account.Information info = null;
        try {
            info = new Account.Information(build);
        } catch (InformationIncompleteException f) {
            f.printStackTrace();
            success = false;
        }

        if (success) {
            new_account.setAccount(info);
            create = true;
        }
        setVisible(false);
    }
}
