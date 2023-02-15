import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.Vector;


public class SelectAccount extends JFrame implements  ListSelectionListener {
    JList accounts;
    JScrollPane scrollPane;
    JTextField name;
    JLabel label_name;
    JPanel panel;
    public JButton new_account;
    public JButton select;
    public String selected_name;
    public boolean selected;
    public boolean create;

    SelectAccount (Vector list, String select_type) {
        selected = false;
        create = false;

        setTitle(select_type);
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(300, 200));
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);

        PickAccount(list);
    }

    public void PickAccount(Vector list) {

        accounts = new JList<>(list);
        scrollPane = new JScrollPane(accounts);

        accounts.setBackground(Color.LIGHT_GRAY);
        accounts.setForeground(Color.BLUE);
        accounts.setPreferredSize(new Dimension(200,200));
        scrollPane.setViewportView(accounts);
        accounts.setLayoutOrientation(JList.VERTICAL);

        name = new JTextField(20);
        name.setBounds(100,30,200,20);
        name.setEnabled(false);

        label_name = new JLabel("Name");
        label_name.setSize(new Dimension(20,20));

        panel = new JPanel(new GridLayout(2,2));
        panel.add(label_name);
        panel.add(name);

        accounts.addListSelectionListener(this);

        JPanel select_button = new JPanel();
        select_button.setBounds(400, 400, 200, 600);
        select = new JButton("SELECT");
        select.setBackground(Color.BLACK);
        select.setForeground(Color.white);
        select.addActionListener(e -> {
            if (!name.getText().equals("")) {
                setVisible(false);
                selected = true;
                selected_name = name.getText();
            }
        });
        select_button.add(select);

        JPanel new_accounts = new JPanel();
        new_accounts.setBounds(400, 400, 200, 600);
        new_account = new JButton("NEW");
        new_account.setBackground(Color.BLACK);
        new_account.setForeground(Color.white);
        new_account.addActionListener(e -> {
            setVisible(false);
            create = true;
        });
        new_accounts.add(new_account);

        JSplitPane sp = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT, select_button, new_accounts);
        JSplitPane sp1 = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT, panel, sp);
        JSplitPane sp2 = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT, sp1, scrollPane);

        add(sp2);

        setVisible(true);
        pack();

    }

    public void valueChanged(ListSelectionEvent e) {
        if(accounts.isSelectionEmpty())
            return;
        String an = accounts.getSelectedValue().toString();
        name.setText(an);
    }
}
