import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    JPanel jPanel = new Panel();
    Frame()
    {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(jPanel);

        this.pack();

        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}