import javax.swing.*;
import java.awt.*;
import java.sql.*;

class LogIn extends JFrame {
    LogIn() {
        Font f = new Font("Futura", Font.BOLD, 40);
        Font f2 = new Font("Calibri", Font.PLAIN, 22);

        JLabel title = new JLabel("Login", JLabel.CENTER);
        title.setForeground(Color.white);
        JLabel l1 = new JLabel("Enter Username");
        l1.setForeground(Color.white);
        JTextField t1 = new JTextField(10);

        JLabel l2 = new JLabel("Enter Password");
        l2.setForeground(Color.white);
        JPasswordField t2 = new JPasswordField(10);




        JButton b1 = new JButton("Submit");
        b1.setForeground(Color.white);
        b1.setBackground(new Color(100, 180, 250));
        JButton b2 = new JButton("Back");

        title.setFont(f);
        l1.setFont(f2);
        t1.setFont(f2);
        l2.setFont(f2);
        t2.setFont(f2);
        b1.setFont(f2);
        b2.setFont(f2);





        Container c = getContentPane();
        c.setLayout(null);
        c.setBackground( new Color(25, 40, 112));

        title.setBounds(250, 30, 300, 50);
        l1.setBounds(250, 100, 300, 30);
        t1.setBounds(250, 140, 300, 30);
        l2.setBounds(250, 200, 300, 30);
        t2.setBounds(250, 240, 300, 30);
        b1.setBounds(300, 300, 200, 40);
        b2.setBounds(300, 360, 200, 40);

        c.add(title);
        c.add(l1);
        c.add(t1);
        c.add(l2);
        c.add(t2);
        c.add(b1);
        c.add(b2);

        b2.addActionListener(a->
        {
            new Landing();
            dispose();
        });

        b1.addActionListener(
                a->
                {
                    String url="jdbc:mysql://localhost:3306/Quiz";

                    try(Connection conn = DriverManager.getConnection(url,"root","Ashish030406"))
                    {
                        String sql= "select * from cred where username=? and password=?";
                        try (PreparedStatement pst= conn.prepareStatement(sql))
                        {
                            String user=t1.getText();
                            String pass= new String(t2.getPassword());




                            pst.setString(1,user);
                            pst.setString(2,pass);

                            ResultSet rs= pst.executeQuery();
                            if(rs.next()) {

                                JOptionPane.showMessageDialog(null, "Successfully Log in ");
                                new Home(t1.getText());
                                dispose();
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null,"user not exist");
                            }
                        }

                    }
                    catch(SQLException e)
                    {
                        JOptionPane.showMessageDialog(null,e.getMessage());
                    }



                }
        );




        setVisible(true);
        setSize(800, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Login");
    }

    public static void main(String[] args) {
        new LogIn();
    }
}
