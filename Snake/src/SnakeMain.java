import javax.swing.*;
import java.awt.*;
import java.awt.geom.Dimension2D;
import java.util.ArrayList;
import java.util.Random;

public class SnakeMain {

    public static int gridSize = 30;
    public static int xGridSize = 20;
    public static int yGridSize = 20;
    public static int updates = 100000;

    public static int colorPl = Color.GREEN.getRGB();
    public static int colorFood = Color.RED.getRGB();

    public static boolean gameOver = false;
    public static int xDirection = 0;
    public static int yDirection = -1;
    public static int lastXDirection = 0;
    public static int lastYDirection = 0;
    public static int[][] grid = new int[xGridSize][yGridSize];
    public static int[][] copyGrid = new int[xGridSize][yGridSize];
    public static ArrayList<int[]> positions = new ArrayList<>();
    public static Random random = new Random();
    public static boolean hasApple = false;


    public static JFrame jFrame = new SnakeFrame();

    public static void main(String[] args) {
        jFrame.addKeyListener(new SnakeKeyListener());


        positions.add(0, new int[]{xGridSize / 2, yGridSize / 2});
        positions.add(1, new int[]{positions.get(0)[0] - 1, positions.get(0)[1]});
        positions.add(2, new int[]{positions.get(1)[0] - 1, positions.get(0)[1]});

        int updateUnit = 1000000;
        long executionStamp = System.nanoTime() / updateUnit;

        int count = 0;
        //GameLoop
        while (!gameOver) {
            long now = System.nanoTime() / updateUnit;
            long diff = now - executionStamp;
            long interval = updateUnit / updates;
            if (diff > interval) {
                count++;
                if(count >= 10)
                {
                    System.out.println("tick");
                    System.out.println(""+lastXDirection + lastYDirection);

                    gameUpdate();
                    count = 0;
                }
                executionStamp = System.nanoTime() / updateUnit;
            }

        }
    }

    public static void gameUpdate() {

        if (updatePos()) {
            for (int x = 0; x < xGridSize; x++) {
                for (int y = 0; y < yGridSize; y++) {
                    grid[x][y] = copyGrid[x][y];
                }
            }

            for (int i = 0; i < positions.size(); i++) {
                grid[positions.get(i)[0]][positions.get(i)[1]] = colorPl;
            }
            jFrame.repaint();

        }
        else{
            gameOver = true;
        }
        if(!hasApple)
        {
            int randomX = 0;
            int randomY = 0;
            do {
                 randomX = random.nextInt(0,xGridSize);
                 randomY = random.nextInt(0,yGridSize);
            }while(grid[randomX][randomY] == 1);
            copyGrid[randomX][randomY] = colorFood;
            hasApple = true;


        }

    }

    public static void onKeyPress(char key) {


        switch (key) {
            case 'w':
                if (lastYDirection != 1) {
                    yDirection = -1;
                    xDirection = 0;
                }
                break;
            case 'a':
                if (lastXDirection != 1) {
                    yDirection = 0;
                    xDirection = -1;
                }
                break;
            case 's':
                if (lastYDirection != -1) {
                    yDirection = 1;
                    xDirection = 0;
                }
                break;
            case 'd':
                if (lastXDirection != -1) {
                    yDirection = 0;
                    xDirection = 1;
                }
                break;
        }


    }

    public static boolean updatePos() {
        boolean check = true;

        if(positions.get(0)[0] + xDirection < xGridSize && positions.get(0)[1] + yDirection < yGridSize && positions.get(0)[0] + xDirection >= 0 && positions.get(0)[1] + yDirection >= 0){
            if (grid[positions.get(0)[0] + xDirection][positions.get(0)[1] + yDirection] == colorPl) {
                check = false;
            } else {

                    if(grid[positions.get(0)[0] + xDirection][positions.get(0)[1] + yDirection] == colorFood)
                {
                    positions.add(positions.size()-1,new int[]{positions.get(positions.size()-1)[0],positions.get(positions.size()-1)[1]});
                    copyGrid = new int[xGridSize][yGridSize];
                    hasApple = false;
                }
                for (int i = (positions.size()-1); i > 0; i--) {
                    positions.get(i)[0] = positions.get(i - 1)[0];
                    positions.get(i)[1] = positions.get(i - 1)[1];
                }
                positions.get(0)[0] += xDirection;
                positions.get(0)[1] += yDirection;
                lastYDirection = yDirection;
                lastXDirection = xDirection;

            }

        }
        else{
            check = false;
        }


        return check;
    }

}
