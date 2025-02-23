import javax.swing.*;
import java.awt.*;
import java.sql.*;

class Home extends JFrame
{
    Home(String username)
    {
        Font f = new Font("Futura", Font.BOLD, 35);
        Font f2 = new Font("Calibri", Font.PLAIN, 22);

        JLabel title = new JLabel("Welcome " + username, JLabel.CENTER);
        title.setForeground(Color.white);
        title.setFont(f);


        JButton start=new JButton("Start Quiz");
        start.setForeground(Color.white);
        start.setFont(f2);
        start.setBackground(new Color(100, 180, 250));
        JButton end=new JButton("Edit Profile");
        end.setForeground(Color.white);
        end.setFont(f2);
        end.setBackground(new Color(100, 180, 250));
        JButton logout=new JButton("Log Out");
        logout.setForeground(Color.white);
        logout.setFont(f2);
        logout.setBackground(new Color(100, 180, 250));

        Container c=getContentPane();
        c.setLayout(null);

        c.setBackground( new Color(25, 40, 112));

        title.setBounds(250, 30, 300, 50);
        start.setBounds(280, 130, 240, 50);
        end.setBounds(280, 210, 240, 50);
        logout.setBounds(280, 290, 240, 50);
        c.add(title);
        c.add(start);
        c.add(end);
        c.add(logout);

        setVisible(true);
        setSize(800, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Home");

        start.addActionListener(
                a->
                {
                    new Quiz(username);
                    dispose();
                }
        );

        end.addActionListener(
                a->
                {
                    new EditProfile(username);
                    dispose();
                }
        );

        logout.addActionListener(
                a->
                {
                    new Landing();
                    dispose();
                }
        );





    }

    public static void main(String[] args) {
        new Home("ash");
    }

}
