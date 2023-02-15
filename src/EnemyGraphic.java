import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class EnemyGraphic extends JFrame  {
    public boolean attack_pressed;
    public boolean potion_pressed;
    public boolean ability_pressed;

    EnemyGraphic(Character player, Entity enemy) {
        attack_pressed = false;
        potion_pressed = false;
        ability_pressed = false;

        setTitle("Fight");
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1000, 800));
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);

        Fight(player, enemy);
    }

    public void Fight(Character player, Entity enemy) {
        JLabel picture = new JLabel();
        picture.setIcon(addPicture(player.picture));
        picture.setSize(new DimensionUIResource(100,500));

        JTextField health = new JTextField(20);
        health.setEnabled(true);
        health.setEditable(false);
        health.setText(String.valueOf(player.current_life));
        JLabel label_health = new JLabel("Health");
        label_health.setSize(new Dimension(20,20));

        JTextField mana = new JTextField(20);
        mana.setEnabled(true);
        mana.setEditable(false);
        mana.setText(String.valueOf(player.current_mana));
        JLabel label_mana = new JLabel("Mana");
        label_mana.setSize(new Dimension(20,20));

        JPanel panel_player = new JPanel(new GridLayout(2,2));
        panel_player.add(label_health);
        panel_player.add(health);
        panel_player.add(label_mana);
        panel_player.add(mana);

        JLabel picture_enemy = new JLabel();
        picture_enemy.setIcon(addPicture(enemy.picture));
        picture_enemy.setSize(new DimensionUIResource(100,500));

        JTextField enemy_health = new JTextField(20);
        enemy_health.setEnabled(true);
        enemy_health.setEditable(false);
        enemy_health.setText(String.valueOf(enemy.current_life));
        JLabel label_enemy_health = new JLabel("Health");
        label_enemy_health.setSize(new Dimension(20,20));

        JTextField enemy_mana = new JTextField(20);
        enemy_mana.setEnabled(true);
        enemy_mana.setEditable(false);
        enemy_mana.setText(String.valueOf(enemy.current_mana));
        JLabel label_enemy_mana = new JLabel("Mana");
        label_enemy_mana.setSize(new Dimension(20,20));

        JPanel panel_enemy = new JPanel(new GridLayout(2,2));
        panel_enemy.add(label_enemy_health);
        panel_enemy.add(enemy_health);
        panel_enemy.add(label_enemy_mana);
        panel_enemy.add(enemy_mana);

        JButton attack = new JButton("ATTACK");
        attack.setBackground(Color.BLACK);
        attack.setForeground(Color.white);
        attack.addActionListener(e -> {
            attack_pressed = true;
            setVisible(false);
        });

        JButton ability = new JButton("ABILITY");
        ability.setBackground(Color.BLACK);
        ability.setForeground(Color.white);
        ability.addActionListener(e -> {
            ability_pressed = true;
            setVisible(false);
        });

        JButton potion = new JButton("POTION");
        potion.setBackground(Color.BLACK);
        potion.setForeground(Color.white);
        potion.addActionListener(e -> {
            potion_pressed = true;
            setVisible(false);
        });

        JPanel attack_button = new JPanel();
        attack_button.add(attack);
        JPanel ability_button = new JPanel();
        ability_button.add(ability);
        JPanel potion_button = new JPanel();
        potion_button.add(potion);


        JSplitPane sp = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT, picture, panel_player);
        JSplitPane sp1 = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT, picture_enemy, panel_enemy);
        JSplitPane sp2 = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT, attack_button, ability_button);
        JSplitPane sp3 = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT, sp2, potion_button);
        JSplitPane sp4 = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT, sp, sp3);
        sp4.setBackground(Color.white);
        JSplitPane sp5 = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT, sp4, sp1);

        add(sp5);

        setVisible(true);
    }

    public static ImageIcon addPicture(String name) {
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File(
                    "D:\\Facultate\\Programare orientata pe obiecte\\Proiect\\src\\Pictures\\" + name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert myPicture != null;
        return new ImageIcon(myPicture);
    }
}
