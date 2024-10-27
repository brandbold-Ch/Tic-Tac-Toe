package backend.utils;

import frontend.views.BaseBoard;
import frontend.views.GuestWindow;
import javax.swing.ImageIcon;
import java.net.Socket;

public class Memory {

    public static boolean configuredGuest = false;
    public static boolean configuredHost = false;
    public static GuestWindow guestFrame = null;

    public static boolean isServer = false;
    public static boolean hostKey = false;
    public static boolean guestKey = false;
    public static String symbolPosition = "";

    public static String hostIP = null;
    public static int hostPort = 444;

    public static ImageIcon hostSymbol = null;
    public static ImageIcon guestSymbol = null;

    public static String turnOf = "host";

    public static String theWinner = "none";
    public static boolean isAsync = false;

    public static Socket outputStreamGuest = null;
    public static Socket outputStreamHost = null;
    public static BaseBoard baseBoard = null;

    public static String toJSON() {
        return "{\"hostSymbol\": \""
                + hostSymbol
                + "\", \"guestSymbol\": \""
                + guestSymbol
                + "\", \"turnOf\": \""
                + turnOf
                + "\", \"isAsync\": \""
                + isAsync
                + "\"}";
    }
}
