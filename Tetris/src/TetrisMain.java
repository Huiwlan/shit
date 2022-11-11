import javax.swing.*;
import java.util.Random;

public class TetrisMain {


    static int clearedLines = 0;
    static int score = 0;
    static Random random = new Random();
    static boolean haveActivePiece = false;
    static TetrisPiece activePiece = null;
    static int updates = 1200;
    public static int gridSize = 30;
    public static int xGridSize = 10;
    public static int yGridSize = 20;
    public static int[][] grid = new int[xGridSize][yGridSize];
    public static int[][] displayGrid = new int[xGridSize][yGridSize];
    public static int[][] colorGrid = new int[xGridSize][yGridSize];
    public static JFrame jFrame = new TetrisFrame();
    public static boolean gameOver = false;


    public static void main(String[] args) {

        jFrame.addKeyListener(new TetrisKeyListener());


        //region init grid
        for (int x = 0; x < xGridSize; x++) {
            for (int y = 0; y < yGridSize; y++) {
                grid[x][y] = -1;
                displayGrid[x][y] = -1;
                colorGrid[x][y] = -1;
            }
        }
        //endregion


        int updateUnit = 1000000;
        long executionStamp = System.nanoTime() / updateUnit;


        //GameLoop
        while (!gameOver) {
            long now = System.nanoTime() / updateUnit;
            long diff = now - executionStamp;
            long interval = updateUnit / updates;
            if (diff > interval) {
                System.out.println("tick");
                executionStamp = System.nanoTime() / updateUnit;
                gameUpdate();
            }
            jFrame.repaint();


        }

    }

    public static void gameUpdate() {
        for (int x = 0; x < xGridSize; x++) {
            for (int y = 0; y < yGridSize; y++) {
                displayGrid[x][y] = grid[x][y];

            }
        }

        deleteLine();

        if (!haveActivePiece) {
            activePiece = new TetrisPiece(random.nextInt(0, 7), 5, 0);
            if (!activePiece.updatePos(0,0))
            {
                gameOver = true;
            }
            haveActivePiece = true;
            System.out.println("new piece");

        } else {
            if (!activePiece.updatePos(0, 1)) {
                System.out.println("no shit");
                haveActivePiece = false;
                for (int x = 0; x < xGridSize; x++) {
                    for (int y = 0; y < yGridSize; y++) {

                        grid[x][y] = displayGrid[x][y];

                    }
                }
            }
        }


    }

    public static void deleteLine()
    {
        boolean check = true;
        int[] toClear = new int[yGridSize];
        int counter = 0;

        for (int y = 0; y<yGridSize; y++)
        {
            check = true;
            for (int x = 0; x<xGridSize; x++)
            {
                if(grid[x][y] == -1)
                {
                    check = false;
                }
            }
            if (check)
            {
                toClear[counter] = y;
                counter++;
            }
        }
            if(counter>0)
            {
                System.out.println(counter);
                for (int i = 0; i < counter; i++)
                {
                    clearedLines++;
                    score += 1;
                    updates += 100;
                    for (int x = 0; x<xGridSize; x++)
                    {
                        grid[x][toClear[i]] = -1;
                        colorGrid[x][toClear[i]] = -1;



                    }
                    for (int y = 0; y<toClear[i]; y++) {

                        for (int x = 0; x < xGridSize; x++) {

                            if (colorGrid[x][toClear[i]-y-1] == -1)
                            {
                                colorGrid[x][toClear[i]-y] = -1;
                            }
                            else {
                                colorGrid[x][toClear[i]-y] = colorGrid[x][toClear[i]-y-1];
                            }

                            grid[x][toClear[i]-y] = grid[x][toClear[i]-y-1];
                        }
                    }
                }
            }




    }
    public static void onKeyPress(char key) {
        int direction = 0;
        switch (key) {
            case 'a' -> direction = -1;
            case 'd' -> direction = 1;
            case 's' -> activePiece.updatePos(0, 1);
            case 'q' -> activePiece.rotate(-1);
            case 'e' -> activePiece.rotate(1);

        }


        for (int x = 0; x < xGridSize; x++) {
            for (int y = 0; y < yGridSize; y++) {

                displayGrid[x][y] = grid[x][y];

            }
        }

        activePiece.updatePos(direction, 0);

    }


}

