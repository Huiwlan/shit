import javax.swing.*;
import java.awt.*;

public class TicMain {

    public static int gridSize = 180;
    public static int colorPl1 = Color.CYAN.getRGB();
    public static int colorPl2 = Color.GREEN.getRGB();


    public static int[][] grid = new int[3][3];
    public static boolean turn = false;
    public static boolean gameOver = false;
    public static int xBox = 0;
    public static int yBox = 0;
    static JFrame jFrame = new TicFrame();


    public static void main(String[] args) {
        jFrame.addMouseListener(new TicMouseListener());
    }


    public static void onClick(int x, int y) {
        xBox = x / gridSize;
        yBox = y / gridSize;

        if (!gameOver && grid[xBox][yBox] == 0)
        {

            if (turn) {
                grid[xBox][yBox] = colorPl2;
                turn = false;
            } else {
                grid[xBox][yBox] = colorPl1;
                turn = true;
            }
        }


        jFrame.repaint();


        gameOver = winCheck();


        System.out.println(xBox + "," + yBox);
    }


    public static boolean winCheck() {
        boolean check = false;

        int firstPos = 0;
        int[] checks = new int[8];

        //lines
        for (int n = 0; n <= 2; n++) {
            firstPos = grid[n][0];

            for (int i = 1; i <= 2; i++) {
                if (grid[n][i] != firstPos || grid[n][i]==0) {
                    checks[n] = -1;
                }

            }
            firstPos = grid[0][n];
            for (int i = 1; i <= 2; i++) {
                if (grid[i][n] != firstPos || grid[i][n] == 0) {
                    checks[n + 3] = -1;
                }
            }
        }



        //diagonal
        firstPos = grid[0][0];

        for (int i = 1; i <= 2; i++) {
            if (grid[i][i] != firstPos || grid[i][i]==0) {
                checks[6] = -1;
                break;
            }

        }
        firstPos = grid[2][0];

        for (int i = 1; i >= 0; i--) {
            if (grid[i][2-i] != firstPos || grid[i][2-i] == 0) {
                checks[7] = -1;
                break;
            }

        }
        //check
        for (int i = 0; i <= 7; i++) {
            if (checks[i] == 0) {
                check = true;
                System.out.println("true");
            }
            System.out.print(checks[i]);
        }
        System.out.println("");

        if(check)
        {
            for (int x = 0; x<=2;x++)
            {
                for (int y = 0; y<=2;y++)
                {
                    if(!turn)
                    {
                        grid[x][y] = colorPl2;
                    }
                    else {
                        grid[x][y] = colorPl1;
                    }
                }
            }
        }


        return check;
    }
}