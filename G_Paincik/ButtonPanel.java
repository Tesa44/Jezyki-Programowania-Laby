package kursJava.G_Paincik;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPanel extends JPanel{
    private int WIDTH = 280;
    private int HEIGHT = 720;
    protected JToggleButton bLinia;
    protected JToggleButton bKwadrat;
    protected JToggleButton bKolo;
    public  JButton bReset;
    public  JButton bKolory;
    public boolean linia = false;
    public boolean kwadrat = false;
    public boolean kolo = false;
    public ButtonPanel() {
        this.setBounds(1000,0,280,720);
        this.setBackground(Color.ORANGE);
        this.setLayout(null);
        bLinia = new JToggleButton("Rysuj linie");
        bLinia.setBounds(this.getX()+60,this.getY()+100,140,40);
        this.add(bLinia);
        bLinia.setFocusable(false);

        bLinia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (linia) {
                    linia = false;
                    bLinia.setSelected(false);
                }
                else {
                    bLinia.setSelected(true);
                    linia = true;
                    bKwadrat.setSelected(false);
                    bKolo.setSelected(false);
                    kwadrat = false;
                    kolo = false;
                }
            }
        });
        bKwadrat = new JToggleButton("Rysuj kwadrat");
        bKwadrat.setBounds(this.getX()+60,this.getY()+200,140,40);
        this.add(bKwadrat);
        bKwadrat.setFocusable(false);

        bKwadrat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (kwadrat) {
                    kwadrat = false;
                    bKwadrat.setSelected(false);
                }
                else {
                    bKwadrat.setSelected(true);
                    kwadrat = true;
                    bKolo.setSelected(false);
                    bLinia.setSelected(false);
                    kolo = false;
                    linia = false;
                }
            }
        });
        bKolo = new JToggleButton("Rysuj kolo");
        bKolo.setBounds(this.getX()+60,this.getY()+300,140,40);
        this.add(bKolo);
        bKolo.setFocusable(false);

        bKolo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (kolo) {
                    kolo = false;
                    bKolo.setSelected(false);
                }
                else {
                    bKolo.setSelected(true);
                    kolo = true;
                    bKwadrat.setSelected(false);
                    bLinia.setSelected(false);
                    kwadrat = false;
                    linia = false;
                }
            }
        });
        bReset = new JButton("Reset");
        bReset.setBounds(this.getX()+60,this.getY()+600,140,40);
        this.add(bReset);

        bKolory = new JButton("Kolorek");
        bKolory.setBounds(this.getX()+60,this.getY()+450,140,40);
        this.add(bKolory);
    }
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.pink);
        g2d.fillRect(1000,0,WIDTH,HEIGHT);
        repaint();
    }
}
