package bank_management_system;
//TEN
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PinChange extends JFrame implements ActionListener {

    JPasswordField pin, repin;
    JButton change, back;
    String pinnumber;

    public PinChange(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("CHANGE YOUR PIN");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setBounds(250, 280, 500, 35);
        image.add(text);

        JLabel pintext = new JLabel("New PIN");
        pintext.setForeground(Color.WHITE);
        pintext.setFont(new Font("System", Font.BOLD, 16));
        pintext.setBounds(165, 320, 180, 25);
        image.add(pintext);

        JLabel repintext = new JLabel("Re-Enter New PIN");
        repintext.setForeground(Color.WHITE);
        repintext.setFont(new Font("System", Font.BOLD, 16));
        repintext.setBounds(165, 360, 180, 25);
        image.add(repintext);

        pin = new JPasswordField();
        pin.setFont(new Font("System", Font.BOLD, 25));
        pin.setBounds(320, 320, 180, 25);
        image.add(pin);

        repin = new JPasswordField();
        repin.setFont(new Font("System", Font.BOLD, 25));
        repin.setBounds(320, 360, 180, 25);
        image.add(repin);

        change = new JButton("CHANGE");
        change.setBounds(355, 485, 150, 30);
        change.addActionListener(this);
        image.add(change);

        back = new JButton("BACK");
        back.setBounds(355, 520, 150, 30);
        back.addActionListener(this);
        image.add(back);

        setSize(900, 900);
        setUndecorated(true);
        setLocation(300, 0);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == change) {

            try {
                String npin = pin.getText();
                String rpin = repin.getText();

                if (!npin.equals(rpin)) {
                    JOptionPane.showMessageDialog(null, "Entered PIN does not match");
                    return;
                }
                if (npin.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter new PIN");
                    return;
                }
                if (rpin.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please re-enter PIN");
                    return;
                }

                Conn conn = new Conn();
//                String query1 = "update bank set pin = '" + rpin + "' where pin = '" + pinnumber + "'";
//                String query2 = "update login set pin = '" + rpin + "' where pin = '" + pinnumber + "'";
//                String query3 = "update signupthree set pin = '" + rpin + "' where pin = '" + pinnumber + "'";

                String query1 = "update bank set pin = '" + rpin + "' where pin = '" + pinnumber + "'";
                String query2 = "update login set pin = '" + rpin + "' where pin = '" + pinnumber + "'";
                String query3 = "update signupthree set pin = '" + rpin + "' where pin = '" + pinnumber + "'";


                conn.s.executeUpdate(query1);
                conn.s.executeUpdate(query2);
                conn.s.executeUpdate(query3);

                JOptionPane.showMessageDialog(null, "PIN changed successfully");
                setVisible(false);
                new Transactions(rpin).setVisible(true);

            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new PinChange("").setVisible(true);
    }

}
