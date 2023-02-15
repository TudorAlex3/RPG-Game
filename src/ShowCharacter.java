import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ShowCharacter extends JFrame {
    JTextField name;
    JLabel label_name;
    JTextField profession;
    JLabel label_proffesion;
    JTextField level;
    JLabel label_level;
    JTextField xp;
    JLabel label_xp;
    JTextField enemies;
    JLabel label_enemies;
    JTextField gold;
    JLabel label_gold;
    JPanel panel;
    public JButton show;
    public JButton exit;
    public boolean exit_pressed;

    ShowCharacter(Character player) {
        exit_pressed = false;

        setTitle("Info");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(500, 800));
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);

        DisplayCharacter(player);
    }

    public void DisplayCharacter(Character player) {
        JLabel picture = new JLabel();
        picture.setIcon(addPicture(player.picture));
        picture.setSize(new DimensionUIResource(100, 500));

        name = new JTextField(20);
        name.setEnabled(true);
        name.setEditable(false);
        label_name = new JLabel("Name");
        label_name.setSize(new Dimension(20, 20));

        profession = new JTextField(10);
        profession.setEnabled(true);
        profession.setEditable(false);
        label_proffesion = new JLabel("Role");
        label_proffesion.setSize(new Dimension(20, 20));

        level = new JTextField(3);
        level.setEnabled(true);
        level.setEditable(false);
        label_level = new JLabel("Level");
        label_level.setSize(new Dimension(20, 20));

        xp = new JTextField(3);
        xp.setEnabled(true);
        xp.setEditable(false);
        label_xp = new JLabel("Experience");
        label_xp.setSize(new Dimension(20, 20));

        gold = new JTextField(20);
        gold.setEnabled(true);
        gold.setEditable(false);
        label_gold = new JLabel("Gold");
        label_gold.setSize(new Dimension(20, 20));

        enemies = new JTextField(3);
        enemies.setEnabled(true);
        enemies.setEditable(false);
        label_enemies = new JLabel("Killed enemies");
        label_enemies.setSize(new Dimension(20, 20));

        panel = new JPanel(new GridLayout(6, 6));
        panel.add(label_name);
        panel.add(name);
        panel.add(label_proffesion);
        panel.add(profession);
        panel.add(label_level);
        panel.add(level);
        panel.add(label_xp);
        panel.add(xp);
        panel.add(label_gold);
        panel.add(gold);
        panel.add(label_enemies);
        panel.add(enemies);


        JPanel show_button = new JPanel();
        show = new JButton("SHOW");
        show.setBackground(Color.BLACK);
        show.setForeground(Color.white);
        show.addActionListener(e -> {
            name.setText(player.name);
            level.setText(String.valueOf(player.level));
            xp.setText(String.valueOf(player.current_experience));
            enemies.setText(String.valueOf(player.killd_enemies));
            gold.setText(String.valueOf(player.backpack.gold));
            if (player instanceof Warrior)
                profession.setText("Warrior");
            else if (player instanceof Rogue)
                profession.setText("Rogue");
            else
                profession.setText("Mage");
        });
        show_button.add(show);

        JPanel exit_button = new JPanel();
        exit = new JButton("EXIT");
        exit.setBackground(Color.BLACK);
        exit.setForeground(Color.white);
        exit.addActionListener(e -> {
            setVisible(false);
            exit_pressed = true;
        });
        exit_button.add(exit);

        JSplitPane sp = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT, show_button, exit_button);
        JSplitPane sp1 = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT, panel, sp);
        JSplitPane sp2 = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT, picture, sp1);
        add(sp2);

        pack();
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
