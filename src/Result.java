import javax.swing.*;
import java.awt.*;
import java.sql.*;

class Result extends JFrame
{
    Result(int score,String username)
    {
        Font f = new Font("Arial", Font.BOLD, 30);
        Font f2 = new Font("Times New Roman", Font.BOLD, 25);
        Font f3 =new Font("Arial",Font.PLAIN,25);
        JLabel title = new JLabel("Congratulation " + username, JLabel.CENTER);
        title.setForeground(Color.white);
        title.setFont(f);


       JLabel st=new JLabel("You Completed Quiz " );
       st.setForeground(Color.white);
       st.setFont(f2);
       JLabel et=new JLabel("Your Score is   "+score);
       et.setFont(f2);
       et.setForeground(Color.white);



        JButton restart=new JButton("Retry");
        restart.setBackground(new Color(50, 205, 50));
        restart.setFont(f3);
        restart.setForeground(Color.white);

        JButton quit=new JButton("Exit");
        quit.setBackground(new Color(159, 1, 1));
        quit.setFont(f3);
        quit.setForeground(Color.white);

        Container c=getContentPane();
        c.setLayout(null);

        c.setBackground( new Color(25, 40, 112));

        title.setBounds(250, 30, 300, 50);
        st.setBounds(280, 130, 240, 50);
        et.setBounds(280, 180, 240, 50);
        restart.setBounds(280, 260, 240, 40);
        quit.setBounds(280, 320, 240, 40);
        c.add(title);
        c.add(st);
        c.add(et);
        c.add(restart);
        c.add(quit);

        setVisible(true);
        setSize(800, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Home");



        quit.addActionListener(
                a->
                {
                    new Home(username);
                    dispose();
                }
        );
        restart.addActionListener(
                a->
                {
                    new Quiz(username);
                    dispose();
                }
        );





    }

    public static void main(String[] args) {
        new Result(2,"ash");
    }

}
