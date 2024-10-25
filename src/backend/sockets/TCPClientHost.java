package backend.sockets;

import backend.utils.Memory;
import backend.utils.Responses;
import com.google.gson.Gson;
import frontend.views.BaseBoard;

import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class TCPClientHost extends Thread {

    public TCPClientHost() {}

    public void sendMessage(String symbolPosition, String turnOf) {
        try (Socket socket = new Socket(Memory.hostIP, Memory.hostPort)) {
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream, true);
            writer.println(
                    "{\"symbolPosition\": \""
                            + symbolPosition
                            + "\", \"turnOf\": \""
                            + turnOf
                            + "\"}"
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try (Socket socket = new Socket(Memory.hostIP, Memory.hostPort)) {
            InputStream inputStream = socket.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            Gson gson = new Gson();

            while (true) {
                Responses parsedData = gson.fromJson(reader.readLine(), Responses.class);
                Memory.symbolPosition = parsedData.symbolPosition;
                Memory.turnOf = parsedData.turnOf;
                Memory.baseBoard.onEventDispatcher();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
