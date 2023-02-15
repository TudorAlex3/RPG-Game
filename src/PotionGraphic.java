import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PotionGraphic extends JFrame {
    public int potion;
    public boolean select;

    PotionGraphic (Character player) {
        select = false;

        setTitle("Potion");
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(500, 1000));
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);

        Potion(player);
    }

    public void Potion(Character player) {
        
        ArrayList<JButton> list = new ArrayList<>();
        for (int i = 0; i < player.backpack.potions.size(); i++) {
            JLabel picture = new JLabel();
            String pic;
            if (player.backpack.potions.get(i) instanceof HealthPotion)
                pic = "health.PNG";
            else
                pic = "mana.PNG";
            picture.setIcon(addPicture(pic));

            JTextField potion = new JTextField(7);
            potion.setEnabled(true);
            potion.setEditable(false);
            JLabel label_potion = new JLabel("Potion ");
            label_potion.setSize(new Dimension(15, 20));
            if (player.backpack.potions.get(i) instanceof HealthPotion)
                potion.setText("Health Potion ");
            else
                potion.setText("Mana Potion ");

            JTextField regeneration = new JTextField(2);
            regeneration.setEnabled(true);
            regeneration.setEditable(false);
            JLabel label_regeneration = new JLabel("Regeneration value ");
            label_regeneration.setSize(new Dimension(20, 20));
            regeneration.setText(String.valueOf(player.backpack.potions.get(i).regenerationValue()));

            JTextField weight = new JTextField(2);
            weight.setEnabled(true);
            weight.setEditable(false);
            JLabel label_weight = new JLabel("Weight ");
            label_weight.setSize(new Dimension(20, 20));
            weight.setText(String.valueOf(player.backpack.potions.get(i).weight()));
            

            JPanel panel = new JPanel(new GridLayout(3, 3));
            panel.add(label_potion);
            panel.add(potion);
            panel.add(label_regeneration);
            panel.add(regeneration);
            panel.add(label_weight);
            panel.add(weight);

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
        while (choose < player.backpack.potions.size()) {
            int final_choose = choose;
            list.get(choose).addActionListener(e -> {
                setVisible(false);
                potion = final_choose;
                select = true;
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
