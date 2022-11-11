import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class TicMouseListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        try {
            ClientMain.onClick(e.getX(),e.getY());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
