package kursJava.G_Paincik;

import kursJava.G_Paincik.ButtonPanel;
import kursJava.G_Paincik.KanwaGej;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;

public class Window extends JFrame implements KeyListener{
    private KanwaGej kanwaGej;
    private ButtonPanel buttonPanel;
    private Color kolor = Color.BLACK;

    public Window() {
        this.setSize(1280,720);
        this.setTitle("Dzialaj");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(3);

        kanwaGej = new KanwaGej();
        buttonPanel = new ButtonPanel();

        this.getContentPane().add(kanwaGej);
        this.getContentPane().add(buttonPanel);
        this.setFocusable(true);//!!!!!!
        this.setFocusTraversalKeysEnabled(true);
        this.addKeyListener(this);

        kanwaGej.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(buttonPanel.linia){
                    kanwaGej.lista.add(new Ellipse2D.Double(e.getX()-3,e.getY()-3,6,6));
                    kanwaGej.kolory.add(kolor);
                    kanwaGej.repaint();
                }
                if(buttonPanel.kwadrat) {
                    kanwaGej.lista.add(new Rectangle(e.getX() - 50, e.getY() - 50, 100, 100));
                    kanwaGej.kolory.add(kolor);
                    kanwaGej.repaint();
                }
                if(buttonPanel.kolo) {
                    kanwaGej.lista.add(new Ellipse2D.Double(e.getX() - 50, e.getY() - 50, 100, 100));
                    kanwaGej.kolory.add(kolor);
                    kanwaGej.repaint();
                }
            }
        });
        kanwaGej.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if(buttonPanel.linia){
                    kanwaGej.lista.add(new Ellipse2D.Double(e.getX()-3,e.getY()-3,6,6));
                    kanwaGej.kolory.add(kolor);
                    kanwaGej.repaint();
                }
            }
            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });
        buttonPanel.bReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                buttonPanel.linia = buttonPanel.kwadrat = buttonPanel.kolo = false;
                buttonPanel.bLinia.setSelected(false);
                buttonPanel.bKwadrat.setSelected(false);
                buttonPanel.bKolo.setSelected(false);

                kanwaGej.lista.clear();
                kanwaGej.repaint();
            }
        });
        buttonPanel.bKolory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                kolor = JColorChooser.showDialog(kanwaGej,"No Siema",Color.BLACK);
            }
        });

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.isControlDown() && e.getKeyCode()==KeyEvent.VK_Z && !kanwaGej.lista.isEmpty()){
            kanwaGej.lista.remove(kanwaGej.lista.size() - 1);
            kanwaGej.kolory.remove(kanwaGej.kolory.size() - 1);
            System.out.println("yomanik");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
