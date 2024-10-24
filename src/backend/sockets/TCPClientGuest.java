package backend.sockets;

import backend.utils.Memory;
import backend.utils.Responses;
import com.google.gson.Gson;
import frontend.views.BaseBoard;
import javax.swing.*;
import java.io.*;
import java.net.Socket;


public class TCPClientGuest extends Thread {

    public TCPClientGuest(){}

    @Override
    public void run() {
        try (Socket socket = new Socket(Memory.hostIP, Memory.hostPort)) {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            PrintWriter writer = new PrintWriter(outputStream, true);
            Gson gson = new Gson();

            if (!Memory.configuredGuest) {
                writer.println("getHostData");
                Responses parsedData = gson.fromJson(reader.readLine(), Responses.class);
                System.out.println(parsedData.turnOf);

                Memory.guestSymbol = new ImageIcon(parsedData.guestSymbol);
                Memory.hostSymbol = new ImageIcon(parsedData.hostSymbol);
                Memory.turnOf = parsedData.turnOf;
                Memory.configuredGuest = true;
                Memory.guestFrame.setVisible(false);

                new BaseBoard();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
