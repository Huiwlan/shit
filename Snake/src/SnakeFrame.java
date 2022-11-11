import javax.swing.*;

public class SnakeFrame extends JFrame {

    JPanel jPanel = new SnakePanel();
    SnakeFrame()
    {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(jPanel);

        this.pack();

        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}