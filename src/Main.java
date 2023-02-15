import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main extends JFrame {
    public JButton start;
    JFrame mainWindow;

    public void CreateWindow() {
        mainWindow = new JFrame("WorldOfMarcel");
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setSize(1000, 1000);
        mainWindow.setLocationRelativeTo(null);

        JLabel picture = new JLabel();
        picture.setIcon(addPicture("WorldOfMarcel.PNG"));
        mainWindow.setContentPane(picture);

        JPanel start_button = new JPanel();
        start_button.setBounds(400, 400, 200, 600);

        start = new JButton("GAME");
        start.setBackground(Color.BLACK);
        start.setForeground(Color.white);
        start.addActionListener(e -> mainWindow.setVisible(false));

        start_button.add(start);
        mainWindow.add(start_button);
        mainWindow.pack();
        mainWindow.setVisible(true);
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
