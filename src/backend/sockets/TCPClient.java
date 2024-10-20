package backend.sockets;

import backend.utils.Memory;
import java.io.*;
import java.net.Socket;
import com.google.gson.Gson;
import frontend.views.BaseBoard;
import backend.utils.Responses;
import javax.swing.*;


public class TCPClient extends Thread {

    private InputStream inputStream;
    private OutputStream outputStream;

    public TCPClient() {}

    public void sendMessage(String data) {
        PrintWriter writer = new PrintWriter(
                (Memory.isServer)?Memory.outputStreamHost:Memory.outputStreamGuest, true
        );
        writer.println(data);
    }

    @Override
    public void run() {
        try (Socket socket = new Socket(Memory.hostIP, Memory.hostPort)) {
            this.inputStream = socket.getInputStream();
            this.outputStream = socket.getOutputStream();

            while (true) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(this.inputStream));

                System.out.println(reader.readLine());

                if (!Memory.configuredGuest) {
                    System.out.println("Guest no configurado");
                    Gson gson = new Gson();
                    PrintWriter writer = new PrintWriter(this.outputStream, true);
                    writer.println("getHostData");

                    Responses parsedData = gson.fromJson(reader.readLine(), Responses.class);
                    System.out.println(parsedData.guestSymbol);

                    Memory.guestSymbol = new ImageIcon(parsedData.guestSymbol);
                    Memory.hostSymbol = new ImageIcon(parsedData.hostSymbol);
                    Memory.turnOf = parsedData.turnOf;
                    Memory.configuredGuest = true;
                    Memory.guestFrame.setVisible(false);

                    new BaseBoard();
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
