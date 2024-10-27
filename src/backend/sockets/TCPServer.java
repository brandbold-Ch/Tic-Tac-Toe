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

    public TCPServer() {}

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(Memory.hostPort)){
            while (true) {
                new ClientHandler(serverSocket.accept()).start();
            }
            /*
            if (!Memory.isAsync) {
                while (true) {
                    new ClientHandler(serverSocket.accept()).dataHandler();
                }

            } else {
                while (true) {
                    new ClientHandler(serverSocket.accept()).start();
                }
            }

             */
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Running");
        }
    }
}

class ClientHandler extends Thread {

    Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public void dataHandler() {
        try {
            Gson gson = new Gson();
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            String clientMessage = in.readLine();

            if (clientMessage.equals("getHostData")) {
                System.out.println(clientMessage);
                Memory.outputStreamGuest = clientSocket;
                out.println(Memory.toJSON());

            } else {
                Responses parsedData = gson.fromJson(clientMessage, Responses.class);
                Memory.outputStreamHost = clientSocket;
                Memory.symbolPosition = parsedData.symbolPosition;
                Memory.turnOf = parsedData.turnOf;

                if (Memory.turnOf.equals("guest")) {
                    System.out.println("Turno de invitado");
                    out = new PrintWriter(Memory.outputStreamGuest.getOutputStream(), true);
                    out.println(clientMessage);
                }
                else if (Memory.turnOf.equals("host")) {
                    System.out.println("Turno de anfitrión");
                    out = new PrintWriter(Memory.outputStreamHost.getOutputStream(), true);
                    out.println(clientMessage);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Server listening");
        }
    }

    @Override
    public void run() {
        dataHandler();
    }
}
