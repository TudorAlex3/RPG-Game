import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Table extends JFrame {
    ArrayList<JButton> buttons;
    JButton north;
    JButton south;
    JButton west;
    JButton east;
    JPanel menu;
    JPanel panel;
    public String move;

    Table(Character player, Grid table) {
        move = "NoNe";

        setTitle("WorldOfMarcel");
        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        CreateTable(player, table);
    }
    public void CreateTable(Character player, Grid table) {
        buttons = new ArrayList<>();
        panel = new JPanel(new GridLayout(table.width, table.width));
        for(int i=0; i<table.width; i++){
            for (int j=0; j<table.width; j++) {
                ImageIcon pic = new ImageIcon();
                if (table.player.Ox==i && table.player.Oy==j) {
                    pic = addPicture("ninja.PNG");
                }
                if (table.get(i).get(j).visited == 0) {
                    pic = addPicture("question-circle.PNG");
                }
                else if (table.get(i).get(j).type == CellEntity.EMPTY)
                    pic = addPicture("square.PNG");
                else if (table.get(i).get(j).type == CellEntity.FINISH)
                    pic = addPicture("grin.PNG");
                else if (table.get(i).get(j).type == CellEntity.SHOP)
                    pic = addPicture("shop.PNG");
                else if (table.get(i).get(j).type == CellEntity.ENEMY)
                    pic = addPicture("android.PNG");

                JButton button_i = new JButton(pic);
                if (i==table.player.Ox && j==table.player.Oy) {
                    pic = addPicture("ninja.PNG");
                    button_i.setRolloverIcon(pic);
                    button_i.setBackground(Color.cyan);
                }
                buttons.add(button_i);
                panel.add(button_i);
            }
        }

        menu = new JPanel();
        north = new JButton("NORTH");
        north.setBackground(Color.BLACK);
        north.setForeground(Color.white);
        north.addActionListener(e -> {
            setVisible(false);
            move = "N";
        });

        east = new JButton("EAST");
        east.setBackground(Color.BLACK);
        east.setForeground(Color.white);
        east.addActionListener(e -> {
            setVisible(false);
            move = "E";
        });

        south = new JButton("SOUTH");
        south.setBackground(Color.BLACK);
        south.setForeground(Color.white);
        south.addActionListener(e -> {
            setVisible(false);
            move = "S";
        });

        west = new JButton("WEST");
        west.setBackground(Color.BLACK);
        west.setForeground(Color.white);
        west.addActionListener(e -> {
            setVisible(false);
            move = "W";
        });

        JTextField level = new JTextField(2);
        level.setEnabled(true);
        level.setEditable(false);
        JLabel label_level = new JLabel("Level ");
        label_level.setSize(new Dimension(20,20));
        level.setText(String.valueOf(player.level));

        JTextField xp = new JTextField(2);
        xp.setEnabled(true);
        xp.setEditable(false);
        JLabel label_xp = new JLabel("Experience ");
        label_xp.setSize(new Dimension(20,20));
        xp.setText(String.valueOf(player.current_experience));

        JTextField health = new JTextField(2);
        health.setEnabled(true);
        health.setEditable(false);
        JLabel label_health = new JLabel("Health ");
        label_health.setSize(new Dimension(20,20));
        health.setText(String.valueOf(player.current_life));

        JTextField mana = new JTextField(2);
        mana.setEnabled(true);
        mana.setEditable(false);
        JLabel label_mana = new JLabel("Mana ");
        label_mana.setSize(new Dimension(20,20));
        mana.setText(String.valueOf(player.current_mana));

        JTextField gold = new JTextField(2);
        gold.setEnabled(true);
        gold.setEditable(false);
        JLabel label_gold = new JLabel("Gold ");
        label_gold.setSize(new Dimension(20,20));
        gold.setText(String.valueOf(player.backpack.gold));

        JPanel panel_player = new JPanel(new GridLayout(5,5));
        panel_player.add(label_level);
        panel_player.add(level);
        panel_player.add(label_xp);
        panel_player.add(xp);
        panel_player.add(label_health);
        panel_player.add(health);
        panel_player.add(label_mana);
        panel_player.add(mana);
        panel_player.add(label_gold);
        panel_player.add(gold);

        JSplitPane sp = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT, north, south);
        JSplitPane sp1 = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT, sp, east);
        JSplitPane sp2 = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT, sp1, west);
        menu.add(sp2);

        JSplitPane sp3 = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT, menu, panel_player);

        JSplitPane sp4 = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT, sp3, panel);
        add(sp4);

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