import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ShopGraphic extends JFrame {
    public int potion;
    public boolean exit_pressed;
    public boolean sell;

    ShopGraphic (Character player, Shop shop) {
        exit_pressed = false;
        sell = false;

        setTitle("Shop");
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(500, 1000));
        setLayout(new FlowLayout());

        ShopShow(player, shop);
    }

    public void ShopShow(Character player, Shop shop) {
        JPanel backpack = new JPanel(new GridLayout(4,4));

        JLabel picture_backpack = new JLabel();
        String img = "backpack.PNG";
        picture_backpack.setIcon(addPicture(img));

        JTextField backpack_gold = new JTextField(2);
        backpack_gold.setEnabled(true);
        backpack_gold.setEditable(false);
        JLabel label_backpack_gold = new JLabel("Gold ");
        label_backpack_gold.setSize(new Dimension(20,20));
        backpack_gold.setText(String.valueOf(player.backpack.gold));

        JTextField backpack_weight = new JTextField(2);
        backpack_weight.setEnabled(true);
        backpack_weight.setEditable(false);
        JLabel label_backpack_weight = new JLabel("Available weight ");
        label_backpack_weight.setSize(new Dimension(20,20));
        backpack_weight.setText(String.valueOf(player.backpack.leftWeight()));


        int mana = 0;
        int heal = 0;
        for (int i=0; i<player.backpack.potions.size(); i++) {
            if (player.backpack.potions.get(i) instanceof ManaPotion)
                mana++;
            else
                heal++;
        }

        JTextField mana_potions = new JTextField(2);
        mana_potions.setEnabled(true);
        mana_potions.setEditable(false);
        JLabel label_mana = new JLabel("Mana potions ");
        label_mana.setSize(new Dimension(20,20));
        mana_potions.setText(String.valueOf(mana));

        JTextField heal_potions = new JTextField(2);
        heal_potions.setEnabled(true);
        heal_potions.setEditable(false);
        JLabel label_heal = new JLabel("Health potions ");
        label_heal.setSize(new Dimension(20,20));
        heal_potions.setText(String.valueOf(heal));

        backpack.add(label_backpack_gold);
        backpack.add(backpack_gold);
        backpack.add(label_backpack_weight);
        backpack.add(backpack_weight);
        backpack.add(label_mana);
        backpack.add(mana_potions);
        backpack.add(label_heal);
        backpack.add(heal_potions);

        JSplitPane bp = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT, picture_backpack, backpack);
        add(bp);

        ArrayList<JButton> list = new ArrayList<>();
        for (int i=0; i<shop.potions.size(); i++) {
            JLabel picture = new JLabel();
            String pic;
            if (shop.potions.get(i) instanceof HealthPotion)
                pic = "health.PNG";
            else
                pic = "mana.PNG";
            picture.setIcon(addPicture(pic));

            JTextField potion = new JTextField(7);
            potion.setEnabled(true);
            potion.setEditable(false);
            JLabel label_potion = new JLabel("Potion ");
            label_potion.setSize(new Dimension(15,20));
            if (shop.potions.get(i) instanceof HealthPotion)
                potion.setText("Health Potion ");
            else
                potion.setText("Mana Potion ");

            JTextField regeneration = new JTextField(2);
            regeneration.setEnabled(true);
            regeneration.setEditable(false);
            JLabel label_regeneration = new JLabel("Regeneration value ");
            label_regeneration.setSize(new Dimension(20,20));
            regeneration.setText(String.valueOf(shop.potions.get(i).regenerationValue()));

            JTextField gold = new JTextField(2);
            gold.setEnabled(true);
            gold.setEditable(false);
            JLabel label_gold = new JLabel("Price ");
            label_gold.setSize(new Dimension(20,20));
            gold.setText(String.valueOf(shop.potions.get(i).price()));

            JTextField weight = new JTextField(2);
            weight.setEnabled(true);
            weight.setEditable(false);
            JLabel label_weight = new JLabel("Weight ");
            label_weight.setSize(new Dimension(20,20));
            weight.setText(String.valueOf(shop.potions.get(i).weight()));

            JPanel panel = new JPanel(new GridLayout(4,4));
            panel.add(label_potion);
            panel.add(potion);
            panel.add(label_regeneration);
            panel.add(regeneration);
            panel.add(label_gold);
            panel.add(gold);
            panel.add(label_weight);
            panel.add(weight);

            JButton buy = new JButton("BUY");
            buy.setBackground(Color.BLACK);
            buy.setForeground(Color.white);
            list.add(buy);

            JSplitPane sp = new JSplitPane(
                    JSplitPane.VERTICAL_SPLIT, picture, panel);
            JSplitPane sp1 = new JSplitPane(
                    JSplitPane.VERTICAL_SPLIT, sp, buy);
            add(sp1);
        }

        JButton exit = new JButton("EXIT");
        exit.setBackground(Color.BLACK);
        exit.setForeground(Color.white);
        exit.addActionListener(e -> {
            setVisible(false);
            exit_pressed = true;
        });

        add(exit);

        int choose = 0;
        while (choose < shop.potions.size()) {
            int final_choose = choose;
            list.get(choose).addActionListener(e -> {
                setVisible(false);
                potion = final_choose;
                sell = true;
            });
            choose++;
        }

        pack();
        setVisible(true);
    }

    public static ImageIcon addPicture(String potion) {
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File(
                    "D:\\Facultate\\Programare orientata pe obiecte\\Proiect\\src\\Pictures\\" + potion));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert myPicture != null;
        return new ImageIcon(myPicture);
    }
}
