package backend.sockets;

import backend.utils.Memory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer extends Thread {

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(Memory.hostPort)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();

                BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                switch (input.readLine()) {
                    case "getHostData" -> {
                        out.println(Memory.toJSON());
                        break;
                    }
                    case "hostTurn" -> {
                        break;
                    }
                    case "guestTurn" -> {
                        break;
                    }
                    default -> {
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
