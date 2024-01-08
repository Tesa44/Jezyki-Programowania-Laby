import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

public class Window extends JFrame implements Runnable {
    private static Window window = null;
    private boolean isRunnable = true;
    private MouseListener mouseListener;
    private KListener keyListener;

    public Window() {
        this.setSize(1280,720);
        this.setTitle("Geometry");
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.mouseListener = new MouseListener();
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);
        this.keyListener = new KListener();
        this.addKeyListener(keyListener);
    }

    public void init() {

    }

    public static Window getWindow() {
        if (Window.window == null){
            Window.window = new Window();
        }
        return Window.window;
    }

    public void update(double dt) {
        if (mouseListener.mousePressed) {
            System.out.println("Pressed at " + mouseListener.x + mouseListener.y);
        }
        if (keyListener.keyPressed) {
            System.out.println("Pressed " + keyListener.charPressed);
        }
    }


    @Override
    public void run() {
        double lastFrameTime = 0.0;
        while (isRunnable) {
            try {
                double time = Time.getTime();
                double deltaTime = time - lastFrameTime;
                lastFrameTime = time;
                update(deltaTime);
                Thread.sleep(20);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
