import javax.swing.*;
import java.awt.*;
import java.sql.*;

class EditProfile extends JFrame {
    String result = "";

    EditProfile(String username) {
        Font f = new Font("Futura", Font.BOLD, 35);
        Font f2 = new Font("Calibri", Font.PLAIN, 20);

        JLabel title = new JLabel("Profile Settings", JLabel.CENTER);
        title.setForeground(Color.white);
        title.setFont(f);

        JLabel l1 = new JLabel("Select Field to Update:");
        l1.setForeground(Color.white);
        JComboBox<String> box = new JComboBox<>(new String[]{"Username", "Password", "Phone", "Email"});

        JLabel l2 = new JLabel("Enter New Value:");
        l2.setForeground(Color.white);
        JTextField t1 = new JTextField(15);

        JButton b1 = new JButton("Update");
        b1.setForeground(Color.white);
        b1.setBackground(new Color(100, 180, 250));
        JButton b2 = new JButton("Back");



        l1.setFont(f2);
        box.setFont(f2);
        l2.setFont(f2);
        t1.setFont(f2);
        b1.setFont(f2);
        b2.setFont(f2);

        Container c = getContentPane();
        c.setLayout(null);
       c.setBackground( new Color(25, 40, 112));

        title.setBounds(250, 20, 300, 40);
        l1.setBounds(200, 100, 200, 30);
        box.setBounds(400, 100, 200, 30);
        l2.setBounds(200, 160, 200, 30);
        t1.setBounds(400, 160, 200, 30);
        b1.setBounds(250, 220, 120, 40);
        b2.setBounds(400, 220, 120, 40);

        c.add(title);
        c.add(l1);
        c.add(box);
        c.add(l2);
        c.add(t1);
        c.add(b1);
        c.add(b2);

        b1.addActionListener(
                a->
                {
                    String s1 = box.getSelectedItem().toString().toLowerCase();
                    String s2 = t1.getText();


                    if(s2.isEmpty())
                    {
                        JOptionPane.showMessageDialog(null,"cannot be null");
                        return;
                    }



                    String url = "jdbc:mysql://localhost:3306/Quiz";
                    try (Connection con = DriverManager.getConnection(url, "root", "Ashish030406"))
                    {
                        String sql = "update cred set "+s1+"=? where username =?";
                        try(PreparedStatement pst = con.prepareStatement(sql))
                        {
                            pst.setString(1,s2);
                            pst.setString(2,username);
                            pst.executeUpdate();
                            JOptionPane.showMessageDialog(null,"successfully updated");

                            if(s1.equals("username"))
                            {
                                new EditProfile(s2);
                                dispose();
                            }

                        }
                    }
                    catch(Exception e)
                    {
                        JOptionPane.showMessageDialog(null,e.getMessage());
                        return;
                    }





                }
        );

        b2.addActionListener(
                a-> {
                    new Home(username);
                    dispose();

                }
        );




        setVisible(true);
        setSize(800, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Profile Settings");
    }




    public static void main(String[] args) {
        new EditProfile("kaustubh");
    }
}
