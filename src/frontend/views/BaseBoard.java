package frontend.views;

import backend.utils.Memory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class BaseBoard extends JFrame {

    public JLabel boardLabel;

    public BaseBoard() {
        this.setSize(new Dimension(500, 500));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setLayout(null);

        this.boardLabel = new JLabel();
        this.boardLabel.setIcon(new ImageIcon("src/frontend/assets/board.png"));
        this.boardLabel.setBounds(95, 20, 300, 298);

        this.getContentPane().setBackground(Color.gray);
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
    }

    public JLabel indexA11() {
        JLabel a11 = new JLabel();
        a11.setBackground(null);
        a11.setBounds(100, 27, 85, 85);

        a11.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (Memory.isServer) {
                    a11.setIcon(Memory.hostSymbol);
                } else {
                    a11.setIcon(Memory.guestSymbol);
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
        JLabel a12 = new JLabel();
        a12.setBackground(null);
        a12.setBounds(200, 27, 85, 85);

        a12.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (Memory.isServer) {
                    a12.setIcon(Memory.hostSymbol);
                } else {
                    a12.setIcon(Memory.guestSymbol);
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
        JLabel a13 = new JLabel();
        a13.setBackground(null);
        a13.setBounds(300, 27, 85, 85);

        a13.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (Memory.isServer) {
                    a13.setIcon(Memory.hostSymbol);
                } else {
                    a13.setIcon(Memory.guestSymbol);
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
        JLabel a21 = new JLabel();
        a21.setBackground(null);
        a21.setBounds(100, 129, 85, 85);

        a21.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (Memory.isServer) {
                    a21.setIcon(Memory.hostSymbol);
                } else {
                    a21.setIcon(Memory.guestSymbol);
                }            }

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
        JLabel a22 = new JLabel();
        a22.setBackground(null);
        a22.setBounds(200, 129, 85, 85);

        a22.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (Memory.isServer) {
                    a22.setIcon(Memory.hostSymbol);
                } else {
                    a22.setIcon(Memory.guestSymbol);
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
        JLabel a23 = new JLabel();
        a23.setBackground(null);
        a23.setBounds(300, 129, 85, 85);

        a23.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (Memory.isServer) {
                    a23.setIcon(Memory.hostSymbol);
                } else {
                    a23.setIcon(Memory.guestSymbol);
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
        JLabel a31 = new JLabel();
        a31.setBackground(null);
        a31.setBounds(100, 232, 85, 82);

        a31.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (Memory.isServer) {
                    a31.setIcon(Memory.hostSymbol);
                } else {
                    a31.setIcon(Memory.guestSymbol);
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
        JLabel a32 = new JLabel();
        a32.setBackground(null);
        a32.setBounds(200, 232, 85, 82);

        a32.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (Memory.isServer) {
                    a32.setIcon(Memory.hostSymbol);
                } else {
                    a32.setIcon(Memory.guestSymbol);
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
        JLabel a33 = new JLabel();
        a33.setBackground(null);
        a33.setBounds(300, 232, 85, 82);

        a33.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (Memory.isServer) {
                    a33.setIcon(Memory.hostSymbol);
                } else {
                    a33.setIcon(Memory.guestSymbol);
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
