package frontend.views;

import backend.sockets.TCPClientGuest;
import backend.utils.Memory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuestWindow extends JFrame {

    public GuestWindow() {
        this.setSize(new Dimension(400, 250));
        this.setTitle("Modo invitado");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);

        this.setIPWindow();
    }

    private GuestWindow getObject() {
        return this;
    }

    private void setIPWindow () {
        JPanel containerIP = new JPanel();
        JLabel connectionMessage = new JLabel("Conexión con el servidor");
        JLabel ipLabel = new JLabel("IP address:");
        JLabel synchronizeLabel = new JLabel("Sincronizando datos con el servidor...");
        JLabel portLabel = new JLabel("Port:");
        JTextField ipField = new JTextField();
        JTextField portField = new JTextField();
        JButton jButton = new JButton("OK");

        connectionMessage.setBounds(120, 0, 150, 30);

        portLabel.setBounds(200, 15, 70, 20);
        portField.setBounds(230, 15, 40, 20);
        portField.setBackground(Color.gray);

        jButton.setBounds(165, 180, 55, 30);

        synchronizeLabel.setBounds(90, 140, 220, 30);
        synchronizeLabel.setVisible(false);

        ipLabel.setBounds(0, 15, 100, 20);
        ipField.setBounds(70, 15, 100, 20);
        ipField.setBackground(Color.gray);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Memory.hostIP = ipField.getText();
                Memory.hostPort = Integer.parseInt(portField.getText());
                Memory.guestFrame = getObject();
                synchronizeLabel.setVisible(true);

                new Thread(new TCPClientGuest()).start();
            }
        });

        containerIP.setLayout(null);
        containerIP.setBackground(Color.gray);
        containerIP.setBounds(0, 50, 400, 50);
        containerIP.add(ipLabel);
        containerIP.add(portLabel);
        containerIP.add(portField);
        containerIP.add(ipField);

        this.add(containerIP);
        this.add(connectionMessage);
        this.add(jButton);
        this.add(synchronizeLabel);
    }
}
