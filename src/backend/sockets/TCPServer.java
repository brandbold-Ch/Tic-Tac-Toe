package backend.sockets;

import backend.utils.Memory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import backend.utils.Responses;
import com.google.gson.Gson;


public class TCPServer extends Thread {

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(Memory.hostPort)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                Gson gson = new Gson();

                BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                if (input.readLine().equals("getHostData")) {
                    Memory.outputStreamGuest = clientSocket.getOutputStream();
                    out.println(Memory.toJSON());
                }
                else {
                    Responses parsedData = gson.fromJson(input.readLine(), Responses.class);
                    Memory.outputStreamHost = clientSocket.getOutputStream();
                    Memory.turnOf = parsedData.turnOf;
                    Memory.symbolPosition = parsedData.symbolPosition;

                    if (Memory.turnOf.equals("guest")) {
                        out.println("");
                    }
                    if (Memory.turnOf.equals("host")) {
                        out.println("");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
