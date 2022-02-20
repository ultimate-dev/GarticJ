package edu.firat.garticj.views.Login;

import edu.firat.garticj.model.Message;
import edu.firat.garticj.model.PlayerData;
import edu.firat.garticj.network.Network;
import edu.firat.garticj.server.Server;
import edu.firat.garticj.views.Drawing.Drawing;
import edu.firat.garticj.views.Drawing.Players;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class Login extends JFrame {
    JPanel container, leftPanel, rightPanel;

    Players players;

    public Login() {
        initContainer();
        setContentPane(container);
        setTitle("GarticJ | Login");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 360);
        setResizable(false);
        final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        final int centerX = dim.width / 2 - this.getSize().width / 2;
        final int centerY = dim.height / 2 - this.getSize().height / 2;
        setLocation(centerX, centerY);
        setVisible(true);
    }


    public void initContainer() {
        container = new JPanel();
        leftPanel = new JPanel();
        leftPanel.setBackground(Color.decode("#ffffff"));
        leftPanel.setLayout(null);


        LoginLabel nickLabel = new LoginLabel("Nickname", 30, "#808080");
        LoginInput nickInput = new LoginInput(50, "#f2eefa");
        LoginLabel hostLabel = new LoginLabel("Host", 100, "#808080");
        LoginInput hostInput = new LoginInput(120, "#f2eefa");
        LoginLabel portLabel = new LoginLabel("Port", 170, "#808080");
        LoginInput portInput = new LoginInput(190, "#f2eefa");
        LoginButton connectButton = new LoginButton("Connect", 260, "#7859d1", "#ffffff");

        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nickname = nickInput.getText();
                String host = hostInput.getText();
                String port = portInput.getText();
                PlayerData playerData = new PlayerData(nickname,0);
                new Network(host, port);
                new Drawing();
                Network.sendData(playerData);
//                players = Players.getInstance();
//                players.playerDataArrayList.add(playerData);
//                players.updatePlayers();

                setVisible(false);
                dispose();
            }
        });

        leftPanel.add(nickLabel);
        leftPanel.add(nickInput);
        leftPanel.add(hostLabel);
        leftPanel.add(hostInput);
        leftPanel.add(portLabel);
        leftPanel.add(portInput);
        leftPanel.add(connectButton);


        rightPanel = new JPanel();
        rightPanel.setBackground(Color.decode("#7859d1"));
        rightPanel.setLayout(null);


        LoginLabel newNickLabel = new LoginLabel("Nickname", 100, "#ffffff");
        LoginInput newNickInput = new LoginInput(120, "#937ada");
        LoginLabel newPortLabel = new LoginLabel("Port", 170, "#ffffff");
        LoginInput newPortInput = new LoginInput(190, "#937ada");
        LoginButton createButton = new LoginButton("Create", 260, "#ffffff", "#7859d1");

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nickname = newNickInput.getText();
                String port = newPortInput.getText();
                PlayerData playerData = new PlayerData(nickname,0);

                new Thread(()->{
                    new Server(Integer.parseInt(port));
                }).start();

                new Network("localhost", port);
                new Drawing();

                players = Players.getInstance();

                players.playerDataArrayList.add(playerData);
                players.updatePlayers();

                Network.sendData(playerData);
                setVisible(false);
                dispose();

            }
        });

        rightPanel.add(newNickLabel);
        rightPanel.add(newNickInput);
        rightPanel.add(newPortLabel);
        rightPanel.add(newPortInput);
        rightPanel.add(createButton);


        GridLayout layout = new GridLayout(0, 2);
        container.setLayout(layout);


        container.add(leftPanel);
        container.add(rightPanel);
    }

    public class LoginLabel extends JLabel {
        public LoginLabel(String text, int y, String color) {
            setText(text.toUpperCase(Locale.ROOT));
            setBorder(new EmptyBorder(0, 10, 0, 10));
            setHorizontalAlignment(SwingConstants.LEFT);
            setFont(new Font("Serif", Font.BOLD, 10));
            setForeground(Color.decode(color));
            setBounds(30, y, 240, 20);
        }
    }

    public class LoginInput extends JTextField {
        public LoginInput(int y, String color) {
            setBounds(30, y, 240, 40);
            setBackground(Color.decode(color));
            setBorder(new EmptyBorder(0, 15, 0, 15));
            setFont(new Font("Serif", Font.PLAIN, 14));
        }
    }

    public class LoginButton extends JButton {
        public LoginButton(String text, int y, String color, String textColor) {
            setText(text);
            setBounds(30, y, 240, 40);
            setForeground(Color.decode(textColor));
            setBackground(Color.decode(color));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setOpaque(true);
            setBorder(null);
        }
    }


}

