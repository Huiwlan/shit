import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TetrisKeyListener implements KeyListener {


    @Override
    public void keyTyped(KeyEvent e) {



    }

    @Override
    public void keyPressed(KeyEvent e) {
        TetrisMain.onKeyPress(e.getKeyChar());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
