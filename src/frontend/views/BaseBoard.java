package frontend.views;

import backend.sockets.TCPClientGuest;
import backend.sockets.TCPClientHost;
import backend.utils.Memory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.lang.reflect.Field;


public class BaseBoard extends JFrame {

    public JLabel boardLabel;
    private final byte[][] items = new byte[3][3];
    private final JLabel[] labels = new JLabel[9];
    private final JPanel glassPane;
    private boolean a00Pressed = false;
    private boolean a01Pressed = false;
    private boolean a02Pressed = false;
    private boolean a20Pressed = false;
    private boolean a21Pressed = false;
    private boolean a22Pressed = false;
    private boolean a30Pressed = false;
    private boolean a31Pressed = false;
    private boolean a32Pressed = false;

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

        this.boardLabel = new JLabel();
        this.boardLabel.setIcon(new ImageIcon("src/frontend/assets/board.png"));
        this.boardLabel.setBounds(95, 20, 300, 298);

        this.getContentPane().setBackground(Color.gray);
        this.setGlassPane(this.glassPane);
        this.add(this.boardLabel);

        this.labelContainers();
        this.onEventDispatcher();
        Memory.baseBoard = this;
    }

    private void labelContainers() {
        this.labels[0] = this.indexA00();
        this.labels[1] = this.indexA01();
        this.labels[2] = this.indexA02();
        this.labels[3] = this.indexA20();
        this.labels[4] = this.indexA21();
        this.labels[5] = this.indexA22();
        this.labels[6] = this.indexA30();
        this.labels[7] = this.indexA31();
        this.labels[8] = this.indexA32();

        for (JLabel label : this.labels) {
            this.add(label);
        }
    }

    public void contextWinner() {
        if (this.upperTriangular()) {
            JOptionPane.showMessageDialog(this, "Ganaste con la diagonal mayor");

        } else if (this.lowerTriangular()) {
            JOptionPane.showMessageDialog(this, "Ganaste con la diagonal menor");
        }
    }

    public boolean isWinner() {
        return this.upperTriangular() | this.lowerTriangular();
    }

    public void clientBoxActivator() {
        Class<?> context = this.getClass();
        Field field = null;

        for (JLabel label : this.labels) {
            if (label.getName().equals(Memory.symbolPosition)) {
                try {
                    if (Memory.isServer) {
                        label.setIcon(Memory.guestSymbol);

                    } else {
                        label.setIcon(Memory.hostSymbol);
                    }

                    field = context.getDeclaredField("a"+Memory.symbolPosition.substring(1)+"Pressed");
                    field.setAccessible(true);
                    field.set(this, true);

                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void onEventDispatcher() {
        this.clientBoxActivator();
        System.out.println(Memory.theWinner);

        if (Memory.isServer) {
            if (Memory.turnOf.equals("host")) {
                this.glassPane.setVisible(false);
            }

            if (Memory.turnOf.equals("guest")) {
                this.glassPane.setVisible(true);
            }

            if (Memory.theWinner.equals("guest")) {
                System.out.println(Memory.theWinner);
                SwingUtilities.invokeLater(
                        () -> JOptionPane.showMessageDialog(
                                this, "Haz perdido contra el invitado"
                        )
                );
            }

        } else {
            if (Memory.turnOf.equals("host")) {
                this.glassPane.setVisible(true);
            }

            if (Memory.turnOf.equals("guest")) {
                this.glassPane.setVisible(false);
            }

            if (Memory.theWinner.equals("host")) {
                SwingUtilities.invokeLater(
                        () -> JOptionPane.showMessageDialog(
                                this, "Haz perdido contra el anfitrión"
                        )
                );
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

    private boolean upperTriangular() {
        int counter = 0;

        for (int i=0; i<3; i++) {
            counter += items[i][i];
        }
        return (counter == 3);
    }

    private boolean lowerTriangular() {
        int counter = 0;
        int j = 0;

        for (int i=2; i>=0; i--) {
            counter += items[j][i];
            j++;
        }
        return (counter == 3);
    }

    public JLabel indexA00() {
        JLabel a00 = new JLabel();
        a00.setBackground(null);
        a00.setBounds(100, 27, 85, 85);
        a00.setName("A00");

        a00.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                if (!a00Pressed) {
                    glassPane.setVisible(true);

                    if (Memory.isServer) {
                        a00.setIcon(Memory.hostSymbol);
                        new TCPClientHost()
                                .sendMessage(
                                        "A00",
                                        "guest",
                                        (isWinner()) ? "host" : "none"
                                );

                    } else {
                        a00.setIcon(Memory.guestSymbol);
                        new TCPClientGuest()
                                .sendMessage(
                                        "A00",
                                        "host",
                                        (isWinner()) ? "guest" : "none"
                                );
                    }
                    a00Pressed = true;
                    items[0][0] = 1;
                    contextWinner();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
        return a00;
    }

    public JLabel indexA01() {
        JLabel a01 = new JLabel();
        a01.setBackground(null);
        a01.setBounds(200, 27, 85, 85);
        a01.setName("A01");

        a01.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                if (!a01Pressed) {
                    glassPane.setVisible(true);

                    if (Memory.isServer) {
                        a01.setIcon(Memory.hostSymbol);
                        new TCPClientHost()
                                .sendMessage(
                                        "A01",
                                        "guest",
                                        (isWinner()) ? "host" : "none"
                                );

                    } else {
                        a01.setIcon(Memory.guestSymbol);
                        new TCPClientGuest()
                                .sendMessage(
                                        "A01",
                                        "host",
                                        (isWinner()) ? "guest" : "none"
                                );
                    }
                    a01Pressed = true;
                    items[0][1] = 1;
                    contextWinner();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
        return a01;
    }

    public JLabel indexA02() {
        JLabel a02 = new JLabel();
        a02.setBackground(null);
        a02.setBounds(300, 27, 85, 85);
        a02.setName("A02");

        a02.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                if (!a02Pressed) {
                    glassPane.setVisible(true);

                    if (Memory.isServer) {
                        a02.setIcon(Memory.hostSymbol);
                        new TCPClientHost()
                                .sendMessage(
                                        "A02",
                                        "guest",
                                        (isWinner()) ? "host" : "none"
                                );

                    } else {
                        a02.setIcon(Memory.guestSymbol);
                        new TCPClientGuest()
                                .sendMessage(
                                        "A02",
                                        "host",
                                        (isWinner()) ? "guest" : "none"
                                );
                    }
                    a02Pressed = true;
                    items[0][2] = 1;
                    contextWinner();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
        return a02;
    }

    public JLabel indexA20() {
        JLabel a20 = new JLabel();
        a20.setBackground(null);
        a20.setBounds(100, 129, 85, 85);
        a20.setName("A20");

        a20.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                if (!a20Pressed) {
                    glassPane.setVisible(true);

                    if (Memory.isServer) {
                        a20.setIcon(Memory.hostSymbol);
                        new TCPClientHost()
                                .sendMessage(
                                        "A20",
                                        "guest",
                                        (isWinner()) ? "host" : "none"
                                );

                    } else {
                        a20.setIcon(Memory.guestSymbol);
                        new TCPClientGuest()
                                .sendMessage(
                                        "A20",
                                        "host",
                                        (isWinner()) ? "guest" : "none"
                                );
                    }
                    a20Pressed = true;
                    items[1][0] = 1;
                    contextWinner();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
        return a20;
    }

    public JLabel indexA21() {
        JLabel a21 = new JLabel();
        a21.setBackground(null);
        a21.setBounds(200, 129, 85, 85);
        a21.setName("A21");

        a21.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                if (!a21Pressed) {
                    glassPane.setVisible(true);

                    if (Memory.isServer) {
                        a21.setIcon(Memory.hostSymbol);
                        new TCPClientHost()
                                .sendMessage(
                                        "A21",
                                        "guest",
                                        (isWinner()) ? "host" : "none"
                                );

                    } else {
                        a21.setIcon(Memory.guestSymbol);
                        new TCPClientGuest()
                                .sendMessage(
                                        "A21",
                                        "host",
                                        (isWinner()) ? "guest" : "none"
                                );
                    }
                    a21Pressed = true;
                    items[1][1] = 1;
                    contextWinner();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
        return a21;
    }

    public JLabel indexA22() {
        JLabel a22 = new JLabel();
        a22.setBackground(null);
        a22.setBounds(300, 129, 85, 85);
        a22.setName("A22");

        a22.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                if (!a22Pressed) {
                    glassPane.setVisible(true);

                    if (Memory.isServer) {
                        a22.setIcon(Memory.hostSymbol);
                        new TCPClientHost()
                                .sendMessage(
                                        "A22",
                                        "guest",
                                        (isWinner()) ? "host" : "none"
                                );

                    } else {
                        a22.setIcon(Memory.guestSymbol);
                        new TCPClientGuest()
                                .sendMessage(
                                        "A22",
                                        "host",
                                        (isWinner()) ? "guest" : "none"
                                );
                    }
                    a22Pressed = true;
                    items[1][2] = 1;
                    contextWinner();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
        return a22;
    }

    public JLabel indexA30() {
        JLabel a30 = new JLabel();
        a30.setBackground(null);
        a30.setBounds(100, 232, 85, 82);
        a30.setName("A30");

        a30.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                if (!a30Pressed) {
                    glassPane.setVisible(true);

                    if (Memory.isServer) {
                        a30.setIcon(Memory.hostSymbol);
                        new TCPClientHost()
                                .sendMessage(
                                        "A30",
                                        "guest",
                                        (isWinner()) ? "host" : "none"
                                );

                    } else {
                        a30.setIcon(Memory.guestSymbol);
                        new TCPClientGuest()
                                .sendMessage(
                                        "A30",
                                        "host",
                                        (isWinner()) ? "guest" : "none"
                                );
                    }
                    a30Pressed = true;
                    items[2][0] = 1;
                    contextWinner();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
        return a30;
    }

    public JLabel indexA31() {
        JLabel a31 = new JLabel();
        a31.setBackground(null);
        a31.setBounds(200, 232, 85, 82);
        a31.setName("A31");

        a31.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                if (!a31Pressed) {
                    glassPane.setVisible(true);

                    if (Memory.isServer) {
                        a31.setIcon(Memory.hostSymbol);
                        new TCPClientHost()
                                .sendMessage(
                                        "A31",
                                        "guest",
                                        (isWinner()) ? "host" : "none"
                                );

                    } else {
                        a31.setIcon(Memory.guestSymbol);
                        new TCPClientGuest()
                                .sendMessage(
                                        "A31",
                                        "host",
                                        (isWinner()) ? "guest" : "none"
                                );
                    }
                    a31Pressed = true;
                    items[2][1] = 1;
                    contextWinner();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
        return a31;
    }

    public JLabel indexA32() {
        JLabel a32 = new JLabel();
        a32.setBackground(null);
        a32.setBounds(300, 232, 85, 82);
        a32.setName("A32");

        a32.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                if (!a32Pressed) {
                    glassPane.setVisible(true);

                    if (Memory.isServer) {
                        a32.setIcon(Memory.hostSymbol);
                        new TCPClientHost()
                                .sendMessage(
                                        "A32",
                                        "guest",
                                        (isWinner()) ? "host" : "none"
                                );

                    } else {
                        a32.setIcon(Memory.guestSymbol);
                        new TCPClientGuest()
                                .sendMessage(
                                        "A32",
                                        "host",
                                        (isWinner()) ? "host" : "none"
                                );
                    }
                    a32Pressed = true;
                    items[2][2] = 1;
                    contextWinner();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
        return a32;
    }
}
