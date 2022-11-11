import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {


    Panel()
    {
        this.setPreferredSize(new Dimension(400, 400));
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;

        g2D.fillRect(200,200,50,50);
    }
}
