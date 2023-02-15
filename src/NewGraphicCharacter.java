import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

public class NewGraphicCharacter extends JFrame{
    public Account new_account;
    public boolean create;
    public CharType type;
    public String char_name;
    JTextField name;
    JLabel label_name;

    NewGraphicCharacter () {
        setTitle("Create character");
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1000, 800));
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);

        create = false;
        Create();
    }

    public void Create() {
        name = new JTextField(20);
        name.setEnabled(true);
        label_name = new JLabel("Name");
        label_name.setSize(new Dimension(20, 20));
        JPanel name_panel = new JPanel();
        name_panel.add(label_name);
        name_panel.add(name);

        JsonInputPictures pic = new JsonInputPictures();
        ArrayList war = pic.getPictures("Warrior");
        ArrayList rog = pic.getPictures("Rogue");
        ArrayList mag = pic.getPictures("Mage");

        RandomInteger rand_pic = new RandomInteger();
        int rand_war = rand_pic.RandomNumber(0, war.size());
        int rand_rog = rand_pic.RandomNumber(0, rog.size());
        int rand_mag = rand_pic.RandomNumber(0, mag.size());

        JLabel picture_warrior = new JLabel();
        picture_warrior.setIcon(addPicture((String) war.get(rand_war)));
        picture_warrior.setSize(new DimensionUIResource(100, 500));

        JLabel picture_rogue = new JLabel();
        picture_rogue.setIcon(addPicture((String) rog.get(rand_rog)));
        picture_rogue.setSize(new DimensionUIResource(100, 500));

        JLabel picture_mage = new JLabel();
        picture_mage.setIcon(addPicture((String) mag.get(rand_mag)));
        picture_mage.setSize(new DimensionUIResource(100, 500));

        JLabel label_backpack_warrior = new JLabel("Backpack            -    Big");
        label_backpack_warrior.setSize(new Dimension(20, 20));
        JLabel label_protection_warrior = new JLabel("Protection          -    Fire");
        label_protection_warrior.setSize(new Dimension(20, 20));
        JLabel label_ability_warrior = new JLabel("Main attribute    -    Strenght");
        label_ability_warrior.setSize(new Dimension(20, 20));

        JPanel panel_warrior = new JPanel(new GridLayout(3, 1));
        panel_warrior.add(label_backpack_warrior);
        panel_warrior.add(label_protection_warrior);
        panel_warrior.add(label_ability_warrior);

        JButton button_warrior = new JButton("WARRIOR");
        button_warrior.setBackground(Color.BLACK);
        button_warrior.setForeground(Color.white);
        button_warrior.addActionListener(e -> {
            setVisible(false);
            if (name.getText().equals(""))
                char_name = null;
            else
                char_name = name.getText();
            type = CharType.Warrior;
            create = true;
        });

        JSplitPane sp_pic_war = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT, picture_warrior, panel_warrior);
        JSplitPane warrior = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT, sp_pic_war, button_warrior);

        JLabel label_backpack_rogue = new JLabel("Backpack            -    Medium");
        label_backpack_rogue.setSize(new Dimension(20, 20));
        JLabel label_protection_rogue = new JLabel("Protection          -    Earth");
        label_protection_rogue.setSize(new Dimension(20, 20));
        JLabel label_ability_rogue = new JLabel("Main attribute    -    Dexterity");
        label_ability_rogue.setSize(new Dimension(20, 20));

        JPanel panel_rogue = new JPanel(new GridLayout(3, 1));
        panel_rogue.add(label_backpack_rogue);
        panel_rogue.add(label_protection_rogue);
        panel_rogue.add(label_ability_rogue);

        JButton button_rogue = new JButton("ROGUE");
        button_rogue.setBackground(Color.BLACK);
        button_rogue.setForeground(Color.white);
        button_rogue.addActionListener(e -> {
            setVisible(false);
            if (name.getText().equals(""))
                char_name = null;
            else
                char_name = name.getText();
            type = CharType.Rogue;
            create = true;
        });

        JSplitPane sp_pic_rog = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT, picture_rogue, panel_rogue);
        JSplitPane rogue = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT, sp_pic_rog, button_rogue);

        JLabel label_backpack_mage = new JLabel("Backpack            -    Small");
        label_backpack_mage.setSize(new Dimension(20, 20));
        JLabel label_protection_mage = new JLabel("Protection          -    Ice");
        label_protection_mage.setSize(new Dimension(20, 20));
        JLabel label_ability_mage = new JLabel("Main attribute    -    Charisma");
        label_ability_mage.setSize(new Dimension(20, 20));

        JPanel panel_mage = new JPanel(new GridLayout(3, 1));
        panel_mage.add(label_backpack_mage);
        panel_mage.add(label_protection_mage);
        panel_mage.add(label_ability_mage);

        JButton button_mage = new JButton("Mage");
        button_mage.setBackground(Color.BLACK);
        button_mage.setForeground(Color.white);
        button_mage.addActionListener(e -> {
            setVisible(false);
            if (name.getText().equals(""))
                char_name = null;
            else
                char_name = name.getText();
            type = CharType.Mage;
            create = true;
        });

        JSplitPane sp_pic_mag = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT, picture_mage, panel_mage);
        JSplitPane mage = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT, sp_pic_mag, button_mage);

        JSplitPane sp1 = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT, warrior, rogue);
        JSplitPane sp2 = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT, sp1, mage);
        JSplitPane window = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT, name_panel, sp2);

        add(window);
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
