import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseListener extends MouseAdapter {
    public float x,y;
    public float dx,dy;

    public boolean mousePressed = false;
    public boolean mouseDragged = false;

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        this.mousePressed = true;
        //System.out.println("Clicked at " + mouseEvent.getX() +"  "+ mouseEvent.getY());

    }
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        //System.out.println("Mouse released");
        this.mousePressed = false;
        this.mouseDragged = false;
        this.dx = 0;
        this.dy = 0;
    }
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        //System.out.println("Moving");
        this.x = mouseEvent.getX();
        this.y = mouseEvent.getY();
    }
    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        //System.out.println("Mouse dragged");
        this.mouseDragged = true;
        this.dx = mouseEvent.getX() - this.x;
        this.dy = mouseEvent.getY() - this.y;
    }


}
