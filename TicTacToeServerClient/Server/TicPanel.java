import javax.swing.*;
import java.awt.*;

public class TicPanel extends JPanel {

    int gridSize = ServerMain.gridSize;

    TicPanel()
    {
        this.setPreferredSize(new Dimension(gridSize * 3, gridSize * 3));
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;

        g2D.setColor(new Color(0,0,0));
        for (int i = 1; i<=2; i++)
        {
            g2D.drawLine(gridSize*i,0,gridSize*i,3*gridSize);
            g2D.drawLine(0,gridSize*i,3*gridSize,gridSize*i);
        }

        for (int x = 0; x <=2; x++){
            for (int y = 0; y <=2; y++){
                if(ServerMain.grid[x][y] != 0)
                {
                    if (ServerMain.grid[x][y] == 1)
                    {
                        g2D.setColor(ServerMain.colorPl1);
                    }
                    else if (ServerMain.grid[x][y] == 2)
                    {
                        g2D.setColor(ServerMain.colorPl2);
                    }
                    g2D.fillRect(x*gridSize,y*gridSize, gridSize, gridSize);
                }

            }
        }


    }
}
