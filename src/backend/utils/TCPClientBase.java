package backend.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPClientBase {

    public void sendMessage(String symbolPosition, String turnOf, String theWinner) {
        try (Socket socket = new Socket(Memory.hostIP, Memory.hostPort)) {
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream, true);
            writer.println(
                    "{\"symbolPosition\": \""
                            + symbolPosition
                            + "\", \"turnOf\": \""
                            + turnOf
                            + "\", \"theWinner\": \""
                            + theWinner
                            + "\"}"
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
