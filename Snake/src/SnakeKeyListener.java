import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SnakeKeyListener implements KeyListener {


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {


            SnakeMain.onKeyPress(e.getKeyChar());





    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
