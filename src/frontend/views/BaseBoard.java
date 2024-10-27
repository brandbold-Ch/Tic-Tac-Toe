package frontend.views;

import backend.sockets.TCPClientGuest;
import backend.sockets.TCPClientHost;
import backend.utils.Memory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class BaseBoard extends JFrame {

    public JLabel boardLabel;
    private byte[][] items = new byte[3][3];
    private JPanel glassPane;
    private boolean a11Pressed = false;
    private boolean a12Pressed = false;
    private boolean a13Pressed = false;
    private boolean a21Pressed = false;
    private boolean a22Pressed = false;
    private boolean a23Pressed = false;
    private boolean a31Pressed = false;
    private boolean a32Pressed = false;
    private boolean a33Pressed = false;
    private final JLabel a11;
    private final JLabel a12;
    private final JLabel a13;
    private final JLabel a21;
    private final JLabel a22;
    private final JLabel a23;
    private final JLabel a31;
    private final JLabel a32;
    private final JLabel a33;

    public BaseBoard() {
        this.setSize(new Dimension(500, 500));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setLayout(null);

        this.glassPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(new Color(0, 0, 0, 0));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        this.glassPane.setOpaque(false);
        glassPane.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
        glassPane.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {}

            @Override
            public void mouseMoved(MouseEvent e) {}
        });

        this.a11 = new JLabel();
        this.a12 = new JLabel();
        this.a13 = new JLabel();
        this.a21 = new JLabel();
        this.a22 = new JLabel();
        this.a23 = new JLabel();
        this.a31 = new JLabel();
        this.a32 = new JLabel();
        this.a33 = new JLabel();

        this.boardLabel = new JLabel();
        this.boardLabel.setIcon(new ImageIcon("src/frontend/assets/board.png"));
        this.boardLabel.setBounds(95, 20, 300, 298);

        this.getContentPane().setBackground(Color.gray);
        this.setGlassPane(this.glassPane);
        this.add(this.boardLabel);
        this.add(this.indexA11());
        this.add(this.indexA12());
        this.add(this.indexA13());
        this.add(this.indexA21());
        this.add(this.indexA22());
        this.add(this.indexA23());
        this.add(this.indexA31());
        this.add(this.indexA32());
        this.add(this.indexA33());

        this.onEventDispatcher();
        Memory.baseBoard = this;
    }

    public void onEventDispatcher() {
        System.out.println(Memory.toJSON());
        if (Memory.isServer) {
            System.out.println("onEventDispatcher: para Host, " + Memory.turnOf);

            if (Memory.turnOf.equals("host")) {
                System.out.println("Destapando pantalla a host");
                this.glassPane.setVisible(false);
            }

            if (Memory.turnOf.equals("guest")) {
                System.out.println("Tapando pantalla a host");
                this.glassPane.setVisible(true);
            }

        } else {
            System.out.println("onEventDispatcher: para Guest, " + Memory.turnOf);

            if (Memory.turnOf.equals("host")) {
                System.out.println("Tapando pantalla a guest");
                this.glassPane.setVisible(true);
            }

            if (Memory.turnOf.equals("guest")) {
                System.out.println("Destapando pantalla a guest");
                this.glassPane.setVisible(false);
            }
        }
    }

    private void clearArray () {
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                items[i][i] = 0;
            }
        }
    }

    public boolean upperTriangular() {
        int counter = 0;
        for (int i=0; i<3; i++) {
            counter += items[i][i];
        }
        return (counter == 3);
    }

    public boolean lowerTriangular() {
        int counter = 0;
        for (int i=3; i>=0; i--) {
            counter += items[i][i];
        }
        return (counter == 3);
    }

    public JLabel indexA11() {
        a11.setBackground(null);
        a11.setBounds(100, 27, 85, 85);

        a11.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (!a11Pressed) {
                    glassPane.setVisible(true);

                    if (Memory.isServer) {
                        a11.setIcon(Memory.hostSymbol);
                        new TCPClientHost().sendMessage("a11", "guest");

                    } else {
                        a11.setIcon(Memory.guestSymbol);
                        new TCPClientGuest().sendMessage("a11", "host");
                    }
                    a11Pressed = true;
                    items[0][0] = 1;

                    if (upperTriangular()) {
                        System.out.println("You Win");
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        return a11;
    }

    public JLabel indexA12() {
        a12.setBackground(null);
        a12.setBounds(200, 27, 85, 85);

        a12.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                if (!a12Pressed) {
                    glassPane.setVisible(true);

                    if (Memory.isServer) {
                        a12.setIcon(Memory.hostSymbol);
                        new TCPClientHost().sendMessage("a12", "guest");

                    } else {
                        a12.setIcon(Memory.guestSymbol);
                        new TCPClientGuest().sendMessage("a12", "host");
                    }
                    a12Pressed = true;
                    items[0][1] = 1;

                    if (upperTriangular()) {
                        System.out.println("You Win");
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        return a12;
    }

    public JLabel indexA13() {
        a13.setBackground(null);
        a13.setBounds(300, 27, 85, 85);

        a13.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (!a13Pressed) {
                    glassPane.setVisible(true);

                    if (Memory.isServer) {
                        a13.setIcon(Memory.hostSymbol);
                        new TCPClientHost().sendMessage("a13", "guest");

                    } else {
                        a13.setIcon(Memory.guestSymbol);
                        new TCPClientGuest().sendMessage("a13", "host");
                    }
                    a13Pressed = true;
                    items[0][2] = 1;

                    if (upperTriangular()) {
                        System.out.println("You Win");
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        return a13;
    }

    public JLabel indexA21() {
        a21.setBackground(null);
        a21.setBounds(100, 129, 85, 85);

        a21.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (!a21Pressed) {
                    glassPane.setVisible(true);

                    if (Memory.isServer) {
                        a21.setIcon(Memory.hostSymbol);
                        new TCPClientHost().sendMessage("a21", "guest");

                    } else {
                        a21.setIcon(Memory.guestSymbol);
                        new TCPClientGuest().sendMessage("a21", "host");
                    }
                    a21Pressed = true;
                    items[1][0] = 1;

                    if (upperTriangular()) {
                        System.out.println("You Win");
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        return a21;
    }

    public JLabel indexA22() {
        a22.setBackground(null);
        a22.setBounds(200, 129, 85, 85);

        a22.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (!a22Pressed) {
                    glassPane.setVisible(true);

                    if (Memory.isServer) {
                        a22.setIcon(Memory.hostSymbol);
                        new TCPClientHost().sendMessage("a22", "guest");

                    } else {
                        a22.setIcon(Memory.guestSymbol);
                        new TCPClientGuest().sendMessage("a22", "host");
                    }
                    a22Pressed = true;
                    items[1][1] = 1;

                    if (upperTriangular()) {
                        System.out.println("You Win");
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        return a22;
    }

    public JLabel indexA23() {
        a23.setBackground(null);
        a23.setBounds(300, 129, 85, 85);

        a23.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (!a23Pressed) {
                    glassPane.setVisible(true);

                    if (Memory.isServer) {
                        a23.setIcon(Memory.hostSymbol);
                        new TCPClientHost().sendMessage("a23", "guest");

                    } else {
                        a23.setIcon(Memory.guestSymbol);
                        new TCPClientGuest().sendMessage("a23", "host");
                    }
                    a23Pressed = true;
                    items[1][2] = 1;

                    if (upperTriangular()) {
                        System.out.println("You Win");
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        return a23;
    }

    public JLabel indexA31() {
        a31.setBackground(null);
        a31.setBounds(100, 232, 85, 82);

        a31.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (!a31Pressed) {
                    glassPane.setVisible(true);

                    if (Memory.isServer) {
                        a31.setIcon(Memory.hostSymbol);
                        new TCPClientHost().sendMessage("a31", "guest");

                    } else {
                        a31.setIcon(Memory.guestSymbol);
                        new TCPClientGuest().sendMessage("a31", "host");
                    }
                    a31Pressed = true;
                    items[2][0] = 1;

                    if (upperTriangular()) {
                        System.out.println("You Win");
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        return a31;
    }

    public JLabel indexA32() {
        a32.setBackground(null);
        a32.setBounds(200, 232, 85, 82);

        a32.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (!a32Pressed) {
                    glassPane.setVisible(true);

                    if (Memory.isServer) {
                        a32.setIcon(Memory.hostSymbol);
                        new TCPClientHost().sendMessage("a32", "guest");

                    } else {
                        a32.setIcon(Memory.guestSymbol);
                        new TCPClientGuest().sendMessage("a32", "host");
                    }
                    a32Pressed = true;
                    items[2][1] = 1;

                    if (upperTriangular()) {
                        System.out.println("You Win");
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        return a32;
    }

    public JLabel indexA33() {
        a33.setBackground(null);
        a33.setBounds(300, 232, 85, 82);

        a33.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (!a33Pressed) {
                    glassPane.setVisible(true);

                    if (Memory.isServer) {
                        a33.setIcon(Memory.hostSymbol);
                        new TCPClientHost().sendMessage("a33", "guest");

                    } else {
                        a33.setIcon(Memory.guestSymbol);
                        new TCPClientGuest().sendMessage("a33", "host");
                    }
                    a33Pressed = true;
                    items[2][2] = 1;

                    if (upperTriangular()) {
                        System.out.println("You Win");
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        return a33;
    }
}
