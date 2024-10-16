package backend.utils;

import frontend.views.GuestWindow;
import javax.swing.ImageIcon;

public class Memory {

    public static boolean configuredGuest = false;
    public static GuestWindow guestFrame = null;

    public static boolean isServer = false;

    public static boolean hostKey = false;
    public static boolean guestKey = false;

    public static String hostIP = null;
    public static int hostPort = 444;

    public static ImageIcon hostSymbol = null;
    public static ImageIcon guestSymbol = null;

    public static String toJSON() {
        return "{\"hostSymbol\": \"" + hostSymbol + "\", \"guestSymbol\": \"" + guestSymbol + "\"}";
    }
}
