package Okenko;

import javax.swing.*;
import java.awt.*;

public class Okno {

    JFrame window;
    Container con;
    JPanel titlePozadi, startButtonPozadi;
    JLabel titleText;
    JButton startButton;

    public Okno() {

        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800, 600);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);
        window.setVisible(true);
        con = window.getContentPane();

        titlePozadi = new JPanel();
        titlePozadi.setBounds(100,100, 600,150);
        titlePozadi.setBackground(Color.black);

        titleText = new JLabel("Okno");
        titleText.setForeground(Color.white);
        titleText.setFont(new Font("TT Espionage", Font.BOLD, 100));

        startButtonPozadi = new JPanel();
        startButtonPozadi.setBounds(300, 300, 200, 100);
        startButtonPozadi.setBackground(Color.black);

        startButton = new JButton("Start");
        startButton.setForeground(Color.white);
        startButton.setBackground(Color.black);
        startButton.setFont(new Font("TT Espionage", Font.PLAIN, 30));


        titlePozadi.add(titleText);
        startButtonPozadi.add(startButton);

        con.add(titlePozadi);
        con.add(startButtonPozadi);

    }

}
