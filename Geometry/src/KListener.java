import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KListener extends KeyAdapter implements KeyListener {
    public boolean keyPressed = false;
    public char charPressed;

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        this.keyPressed = true;
        charPressed = keyEvent.getKeyChar();
    }
    @Override
    public void keyReleased(KeyEvent keyEvent) {
        this.keyPressed = false;
        charPressed = ' ';
    }
}
