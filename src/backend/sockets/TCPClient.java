package backend.sockets;

import backend.utils.Memory;

import java.io.*;
import java.net.Socket;

import com.google.gson.Gson;
import frontend.views.BaseBoard;

import javax.swing.*;


public class TCPClient extends Thread {

    private InputStream inputStream;
    private OutputStream outputStream;

    public TCPClient() {}

    @Override
    public void run() {
        try (Socket socket = new Socket(Memory.hostIP, Memory.hostPort);) {
            this.inputStream = socket.getInputStream();
            this.outputStream = socket.getOutputStream();

            if (!Memory.configuredGuest) {
                Gson gson = new Gson();

                PrintWriter writer = new PrintWriter(this.outputStream, true);
                writer.println("getHostData");

                BufferedReader reader = new BufferedReader(new InputStreamReader(this.inputStream));
                Response parsedData = gson.fromJson(reader.readLine(), Response.class);
                Memory.guestSymbol = new ImageIcon(parsedData.guestSymbol);
                Memory.hostSymbol = new ImageIcon(parsedData.hostSymbol);

                Memory.configuredGuest = true;
                Memory.guestFrame.setVisible(false);
                new BaseBoard();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

class Response {
    String hostSymbol;
    String guestSymbol;
}
