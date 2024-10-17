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

                } else {
                    System.out.println("Entrando");
                    Responses parsedData = gson.fromJson(input.readLine(), Responses.class);
                    Memory.outputStreamHost = clientSocket.getOutputStream();
                    Memory.symbolPosition = parsedData.symbolPosition;
                    Memory.turnOf = parsedData.turnOf;

                    System.out.println(parsedData);

                    if (Memory.turnOf.equals("guest")) {
                        out = new PrintWriter(Memory.outputStreamGuest, true);
                        out.println(
                                "{\"symbolPosition\": \""
                                        + Memory.symbolPosition
                                        + "\", \"turnOf\": \""
                                        + Memory.turnOf
                                        + "\"}"
                        );
                        System.out.println("Le toca a Guest");
                    }
                    if (Memory.turnOf.equals("host")) {
                        out = new PrintWriter(Memory.outputStreamHost, true);
                        out.println(
                                "{\"symbolPosition\": \""
                                        + Memory.symbolPosition
                                        + "\", \"turnOf\": \""
                                        + Memory.turnOf
                                        + "\"}"
                        );
                        System.out.println("Le toca a Host");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
