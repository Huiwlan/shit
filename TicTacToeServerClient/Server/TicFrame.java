import javax.swing.*;

public class TicFrame extends JFrame {

    JPanel jPanel = new TicPanel();

    TicFrame()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(jPanel);
        this.pack();

        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }
}
