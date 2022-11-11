import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class ClientTemplate {
    public static void main(String[] args) throws IOException {
        String ip = "83.215.169.212";
        int port = 6666;
        Socket clientSocket = new Socket(ip, port);
        DataInputStream in = new DataInputStream(clientSocket.getInputStream());
        DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
        byte[] buffer = {1,2,3,4,5,6,7,8,9};


        out.write(buffer,0,9);


        while (true)
        {

        }

    }
}
