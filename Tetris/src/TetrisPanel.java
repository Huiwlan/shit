import javax.swing.*;
import java.awt.*;

public class TetrisPanel extends JPanel {


    int gridSize = TetrisMain.gridSize;
    int xGridSize = TetrisMain.xGridSize;
    int yGridSize = TetrisMain.yGridSize;
    int gameInfoSize = 100;

    TetrisPanel() {
        this.setPreferredSize(new Dimension(gridSize*xGridSize + gameInfoSize, gridSize*yGridSize));
    }

    public void paint(Graphics g) {

        Graphics2D g2D = (Graphics2D) g;

        //region lines
        g2D.setColor(new Color(0,0,0));
        for (int i = 0; i <= xGridSize; i++) {
            g2D.drawLine(i * gridSize, 0, i*gridSize, gridSize*yGridSize);
        }
        for (int i = 0; i <= yGridSize; i++) {
            g2D.drawLine(0, i * gridSize, gridSize*xGridSize, i * gridSize);
        }
        //endregion

        g2D.drawString("score: "+TetrisMain.score,gridSize*xGridSize+gridSize/3,gridSize);
        g2D.drawString("cleared lines: "+TetrisMain.clearedLines,gridSize*xGridSize+gridSize/3,2*gridSize);


        for (int x = 0; x < xGridSize; x++)
        {
            for (int y = 0; y < yGridSize; y++)
            {
                if (TetrisMain.displayGrid[x][y] == 1)
                {

                    g2D.setColor(new Color(TetrisMain.colorGrid[x][y]));


                    g2D.fillRect(x*gridSize, y*gridSize, gridSize, gridSize);
                }
            }
        }



    }


}
