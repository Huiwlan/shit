import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientMain {

    public static int gridSize = 150;
    public static boolean turn = false;
    public static byte[][] grid = new byte[3][3];
    public static Color colorPl1 = new Color(126,23,255);
    public static Color colorPl2 = new Color(32,166,12);
    static JFrame jFrame = new TicFrame();

    public static Socket clientSocket;
    public static DataInputStream in;
    public static DataOutputStream out;


    public static int xBox = 0;
    public static int yBox = 0;
    public static boolean click = false;
    public static int updates = 2000;


    public static void main(String[] args) throws IOException {
        String ip = JOptionPane.showInputDialog("ip:");
        int port = Integer.parseInt(JOptionPane.showInputDialog("port:"));

        try {
            clientSocket = new Socket(ip, port);
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
                    turn = false;
                    click = false;
                    grid[xBox][yBox] = 2;

                    jFrame.repaint();

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


}