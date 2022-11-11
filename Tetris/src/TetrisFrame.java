import javax.swing.*;

public class TetrisFrame extends JFrame {

    JPanel jPanel = new TetrisPanel();
    TetrisFrame()
    {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(jPanel);

        this.pack();

        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
