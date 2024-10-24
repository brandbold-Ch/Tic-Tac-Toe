package backend.utils;

import frontend.views.GuestWindow;
import javax.swing.ImageIcon;
import java.io.OutputStream;

public class Memory {

    public static boolean configuredGuest = false;
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
    public static boolean isAsync = false;

    public static OutputStream outputStreamGuest = null;
    public static OutputStream outputStreamHost = null;

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
