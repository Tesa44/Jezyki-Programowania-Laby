
import org.w3c.dom.css.RGBColor;

import javax.swing.*;
import java.awt.event.MouseListener;

public class Window extends JFrame {
    private Skrzyzowanie panel;
    public Window(){
        initComponents();
    }
    public void initComponents(){

        panel = new Skrzyzowanie();
        this.getContentPane().add(panel);
        panel.setLayout(null);
        panel.setFocusable(true); //ABY DZIAŁAŁ KEY LISTENER w panelu!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                panel.initialize();
//            }
//        });

        this.setTitle("Sylwester z Eripe");
        this.setSize(1280,720);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }




    public static void main(String[] args) {
        new Window().setVisible(true);
    }
}
