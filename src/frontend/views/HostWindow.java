package frontend.views;

import backend.sockets.TCPClientHost;
import backend.sockets.TCPServer;
import backend.utils.Memory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class HostWindow extends JFrame {

    public HostWindow() {
        this.setSize(new Dimension(400, 250));
        this.setTitle("Modo anfitrión");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);

        this.getIPWindow();
    }

    private void getIPWindow() {
        JPanel containerIP = new JPanel();
        JTextField ipField = new JTextField();
        JTextField portField = new JTextField();
        JLabel message = new JLabel("Proporciona la siguiente IP y puerto al invitado");
        JLabel ipLabel = new JLabel("IP address:");
        JLabel portLabel = new JLabel("Port:");
        JLabel symbolLabel = new JLabel("Símbolo:");
        JLabel modeLabel = new JLabel("Asíncrono:");
        JButton jButton = new JButton("OK");
        JCheckBox option = new JCheckBox();
        JComboBox<String> symbolBox = new JComboBox<>(new String[]{"O", "X"});

        ipLabel.setBounds(0, 15, 100, 20);
        symbolLabel.setBounds(0, 40, 50, 30);
        symbolBox.setBounds(55, 40, 50, 30);
        message.setBounds(65, 0, 265, 30);
        modeLabel.setBounds(167, 40, 120, 20);
        option.setBounds(226, 36, 50, 30);
        option.setBackground(Color.gray);

        portLabel.setBounds(200, 15, 70, 20);
        portField.setBounds(230, 15, 40, 20);
        portField.setBackground(Color.gray);
        portField.setText(String.valueOf(Memory.hostPort));

        ipField.setBounds(70, 15, 100, 20);
        ipField.setText(this.getServerIP());
        ipField.setBackground(Color.gray);
        ipField.setEditable(false);

        containerIP.setLayout(null);
        containerIP.setBackground(Color.gray);
        containerIP.setBounds(0, 50, 400, 75);

        jButton.setBounds(165, 180, 55, 30);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String urlBuild = String.format("src/frontend/assets/%s.png", symbolBox.getSelectedItem());
                Memory.hostSymbol = new ImageIcon(urlBuild);
                Memory.isServer = true;
                Memory.isAsync = option.isSelected();
                Memory.hostIP = getServerIP();

                switch (symbolBox.getSelectedIndex()) {
                    case 0 -> Memory.guestSymbol = new ImageIcon("src/frontend/assets/X.png");
                    case 1 -> Memory.guestSymbol = new ImageIcon("src/frontend/assets/O.png");
                }
                setVisible(false);

                new Thread(new TCPServer()).start();
                new Thread(new TCPClientHost()).start();

                new BaseBoard();
            }
        });

        containerIP.add(ipField);
        containerIP.add(ipLabel);
        containerIP.add(portLabel);
        containerIP.add(portField);
        containerIP.add(symbolBox);
        containerIP.add(symbolLabel);
        containerIP.add(modeLabel);
        containerIP.add(option);

        this.add(containerIP);
        this.add(jButton);
        this.add(message);
    }

    public String getServerIP() {
        try {
             return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            JOptionPane.showMessageDialog(this, "Error al obtener la IP del equipo");
        }
        return null;
    }
}
