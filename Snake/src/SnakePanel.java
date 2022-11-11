import javax.swing.*;
import java.awt.*;

public class SnakePanel extends JPanel {

    int gridSize = SnakeMain.gridSize;
    int xGridSize = SnakeMain.xGridSize;
    int yGridSize = SnakeMain.yGridSize;


    SnakePanel()
    {
        this.setPreferredSize(new Dimension(gridSize * xGridSize, gridSize * yGridSize));
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;

        for (int y = 0; y< yGridSize; y++)
        {
            for (int x = 0; x< xGridSize; x++)
            {
                if(SnakeMain.grid[x][y] != 0)
                {
                    g2D.setColor(new Color(SnakeMain.grid[x][y]));
                    g2D.fillRect(x*gridSize,y*gridSize,gridSize,gridSize);
                }
            }
        }
    }
}
