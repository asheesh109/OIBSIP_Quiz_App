import javax.swing.*;
import java.awt.*;

class Landing extends JFrame {
    Landing() {
        Font f = new Font("Futura", Font.BOLD, 40);
        Font f2 = new Font("Calibri", Font.PLAIN, 22);

        JLabel l1 = new JLabel("Quiz Application", JLabel.CENTER);
        JButton b1 = new JButton("New User");
        JButton b2 = new JButton("Existing User");
        JButton b3 = new JButton("EXit");

        l1.setFont(f);
        b1.setFont(f2);
        b2.setFont(f2);
        b3.setFont(f2);



        l1.setForeground(Color.WHITE);
        b1.setBackground(new Color(100, 180, 250));
        b2.setBackground(new Color(100, 180, 250));
        b1.setForeground(Color.WHITE);
        b2.setForeground(Color.WHITE);
        b3.setForeground(Color.white);
        b3.setBackground(new Color(100, 180, 250));

        Container c = getContentPane();
        c.setLayout(null);
        c.setBackground( new Color(25, 40, 112));

        l1.setBounds(150, 50, 500, 50);
        b1.setBounds(300, 150, 200, 50);
        b2.setBounds(300, 230, 200, 50);
        b3.setBounds(300, 310, 200, 50);

        c.add(l1);
        c.add(b1);
        c.add(b2);
        c.add(b3);

        b2.addActionListener(
                a -> {
                     new LogIn();
                    dispose();
                }
        );

        b1.addActionListener(a -> {
             new SignUp();
            dispose();
        });

        b3.addActionListener(
                a->
                {
                    System.exit(0);
                }
        );

        setVisible(true);
        setSize(800, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Landing Page");
    }

    public static void main(String[] args) {
        Landing a = new Landing();
    }
}
