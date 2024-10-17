package frontend.views;
import backend.utils.Memory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RoleSelectorWindow extends JFrame {

    public RoleSelectorWindow() {
        this.setSize(new Dimension(400, 250));
        this.setTitle("Selector de usuario");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setFont(new Font("Console", Font.BOLD, 12));
        this.setLayout(null);

        this.setTitleGame();
        this.frameSelector();

        this.setResizable(false);
        this.setVisible(true);
    }

    private void setTitleGame() {
        JLabel image = new JLabel();
        JLabel title = new JLabel("Tic * Tac * Toe");

        image.setIcon(new ImageIcon("src/frontend/assets/logo.png"));

        image.setBounds(170, 10, 60, 60);
        title.setBounds(150, 60, 100, 30);

        this.add(image);
        this.add(title);
    }

    private void frameSelector() {
        JLabel hostLabel = new JLabel("Modo anfitrión");
        JLabel guestLabel = new JLabel("Modo invitado");

        JCheckBox hostType = new JCheckBox();
        JCheckBox guestType = new JCheckBox();
        JPanel containerButton = new JPanel();
        JButton button = new JButton("OK");

        hostType.setBounds(230, 10, 20 , 20);
        hostLabel.setBounds(200 , 20, 100, 30);
        button.setBounds(165, 180, 55, 30);

        guestType.setBounds(130, 10, 20 , 20);
        guestLabel.setBounds(100, 20, 100, 30);

        hostType.setBackground(Color.gray);
        guestType.setBackground(Color.gray);

        containerButton.setBounds(0, 120, 400, 50);
        containerButton.setBackground(Color.gray);
        containerButton.setLayout(null);

        containerButton.add(hostType);
        containerButton.add(guestType);
        containerButton.add(hostLabel);
        containerButton.add(guestLabel);

        guestType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (guestType.isSelected()) {
                    hostType.setSelected(false);
                    Memory.guestKey = true;
                    Memory.hostKey = false;
                }
            }
        });

        hostType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (hostType.isSelected()) {
                    guestType.setSelected(false);
                    Memory.hostKey = true;
                    Memory.guestKey = false;
                }
            }
        });

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!hostType.isSelected() && !guestType.isSelected()) {
                    JOptionPane.showMessageDialog(containerButton, "Debes elegir una opción");
                }
                else {
                    if (hostType.isSelected()) {new HostWindow();}
                    else if (guestType.isSelected()) {new GuestWindow();}
                    setVisible(false);
                }
            }
        });
        this.add(button);
        this.add(containerButton);
    }
}
