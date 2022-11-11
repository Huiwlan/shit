import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain
{
    public static int gridSize = 150;
    public static JFrame jFrame = new TicFrame();

    public static boolean turn = true;
    public static byte[][] grid = new byte[3][3];

    public static ServerSocket serverSocket;
    public static Socket clientSocket;
    public static DataOutputStream out;
    public static DataInputStream in;
    public static Color colorPl1 = new Color(126,23,255);
    public static Color colorPl2 = new Color(32,166,12);
    public static int port;






    public static int xBox = 0;
    public static int yBox = 0;
    public static boolean click = false;
    public static int updates = 2000;

    public static void main(String[] args) throws IOException {
        port = Integer.parseInt(JOptionPane.showInputDialog("port:"));
        try {
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }




        jFrame.addMouseListener(new TicMouseListener());
        int updateUnit = 1000000;
        long executionStamp = System.nanoTime() / updateUnit;


        //GameLoop
        while (true) {
            long now = System.nanoTime() / updateUnit;
            long diff = now - executionStamp;
            long interval = updateUnit / updates;
            if (diff > interval) {
                System.out.println("tick");
                executionStamp = System.nanoTime() / updateUnit;
                if (turn && click) {

                    click = false;
                    grid[xBox][yBox] = 1;
                    winCheck();
                    jFrame.repaint();
                    turn = false;

                    for (int i = 0; i <= 2; i++) {
                        for (int n = 0; n <= 2; n++) {
                            System.out.print(grid[i][n]);
                        }
                        System.out.println("");
                    }

                    out.write(grid[0], 0, 3);
                    out.write(grid[1], 0, 3);
                    out.write(grid[2], 0, 3);

                } else if(!turn) {
                    in.read(grid[0], 0, 3);
                    in.read(grid[1], 0, 3);
                    in.read(grid[2], 0, 3);
                    winCheck();
                    jFrame.repaint();
                    turn = true;
                    for (int i = 0; i<=2;i++)
                    {
                        for (int n = 0; n<=2;n++)
                        {
                            System.out.print(grid[i][n]);
                        }
                        System.out.println("");
                    }
                }

            }
        }
    }

    public static void onClick(int x, int y) throws IOException {
         xBox = x/gridSize;
         yBox = y/gridSize;
         if(grid[xBox][yBox] == 0 && turn)
         {
             click = true;
         }


    }


    public static boolean winCheck() throws IOException {
        boolean check = false;

        int firstPos = 0;
        int[] checks = new int[8];

        //lines
        for (int n = 0; n <= 2; n++) {
            firstPos = grid[n][0];

            for (int i = 1; i <= 2; i++) {
                if (grid[n][i] != firstPos || grid[n][i] == 0) {
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
            if (grid[i][i] != firstPos || grid[i][i] == 0) {
                checks[6] = -1;
                break;
            }

        }
        firstPos = grid[2][0];

        for (int i = 1; i >= 0; i--) {
            if (grid[i][2 - i] != firstPos || grid[i][2 - i] == 0) {
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

        if (check) {
            for (int x = 0; x <= 2; x++) {
                for (int y = 0; y <= 2; y++) {
                    if (turn) {
                        grid[x][y] = 1;
                    } else {
                        grid[x][y] = 2;
                    }
                }
            }
            out.write(grid[0], 0, 3);
            out.write(grid[1], 0, 3);
            out.write(grid[2], 0, 3);
            serverSocket.close();
        }


        return check;
    }
}