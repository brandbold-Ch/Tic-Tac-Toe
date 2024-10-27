package backend.sockets;

import backend.utils.Memory;
import backend.utils.Responses;
import backend.utils.TCPClientBase;
import com.google.gson.Gson;
import java.io.*;
import java.net.Socket;

public class TCPClientHost extends TCPClientBase implements Runnable {

    public TCPClientHost() {}

    @Override
    public void run() {
        try (Socket socket = new Socket(Memory.hostIP, Memory.hostPort)) {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            PrintWriter writer = new PrintWriter(outputStream, true);
            Gson gson = new Gson();

            while (true) {
                System.out.println(reader.readLine());
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
