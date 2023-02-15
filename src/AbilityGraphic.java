import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AbilityGraphic extends JFrame {
    public boolean pressed;
    public int ability;

    AbilityGraphic(Character player) {
        pressed = false;

        setTitle("Abilities");
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(500, 200));
        setLayout(new FlowLayout());

        Ability(player);
    }

    public void Ability(Character player) {
        ArrayList<JButton> list = new ArrayList<>();
        for (int i=0; i<player.abilities.size(); i++) {
            JLabel picture = new JLabel();
            String pic;
            if (player.abilities.get(i) instanceof Fire)
                pic = "fire.PNG";
            else if (player.abilities.get(i) instanceof Earth)
                pic = "earth.PNG";
            else
                pic = "ice.PNG";
            picture.setIcon(addPicture(pic));
            
            JTextField name = new JTextField(7);
            name.setEnabled(true);
            name.setEditable(false);
            JLabel label_name = new JLabel("Name ");
            label_name.setSize(new Dimension(15,20));
            name.setText(player.abilities.get(i).name);

            JTextField mana = new JTextField(2);
            mana.setEnabled(true);
            mana.setEditable(false);
            JLabel label_mana = new JLabel("Mana cost ");
            label_mana.setSize(new Dimension(20,20));
            mana.setText(String.valueOf(player.abilities.get(i).mana_cost));

            JTextField damage = new JTextField(2);
            damage.setEnabled(true);
            damage.setEditable(false);
            JLabel label_damage = new JLabel("Damage ");
            label_damage.setSize(new Dimension(20,20));
            damage.setText(String.valueOf(player.abilities.get(i).damage));

            JPanel panel = new JPanel(new GridLayout(4,4));
            panel.add(label_name);
            panel.add(name);
            panel.add(label_mana);
            panel.add(mana);
            panel.add(label_damage);
            panel.add(damage);

            JButton select = new JButton("SELECT");
            select.setBackground(Color.BLACK);
            select.setForeground(Color.white);
            list.add(select);

            JSplitPane sp = new JSplitPane(
                    JSplitPane.VERTICAL_SPLIT, picture, panel);
            JSplitPane sp1 = new JSplitPane(
                    JSplitPane.VERTICAL_SPLIT, sp, select);
            add(sp1);
        }

        int choose = 0;
        while (choose < player.abilities.size()) {
            int final_choose = choose;
            list.get(choose).addActionListener(e -> {
                setVisible(false);
                ability = final_choose;
                pressed = true;
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
