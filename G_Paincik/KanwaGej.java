package kursJava.G_Paincik;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class KanwaGej extends JPanel {

    private int WIDTH = 1000;
    private int HEIGHT = 720;
    public ArrayList<Shape> lista = new ArrayList<>();
    public ArrayList<Color> kolory = new ArrayList<>();
     protected Color kolor = Color.BLACK;

    public KanwaGej() {
        this.setBounds(0,0,1000,720);
        this.setBackground(Color.white);
        this.setLayout(null);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.white);
        g2d.fillRect(0,0,WIDTH,HEIGHT);
        if(lista!=null){
            for (int i = 0; i < lista.size(); i ++) {
                g2d.setColor(kolory.get(i));
                g2d.fill(lista.get(i));
            }
        }
        repaint();
    }
}
