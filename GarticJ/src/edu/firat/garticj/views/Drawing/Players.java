package edu.firat.garticj.views.Drawing;

import edu.firat.garticj.model.PlayerData;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class Players extends JPanel {

    Player[] players;
    static Players single_instance;
    public ArrayList<PlayerData> playerDataArrayList;
    Area area;

    private Players() {
        setBackground(Color.decode("#7859d1"));
        setPreferredSize(new Dimension(220, getSize().height));
        setMinimumSize(new Dimension(220, getSize().height));
        setBorder(new EmptyBorder(20, 0, 0, 0));
        playerDataArrayList = new ArrayList<>();
        area = new Area();
    }

    public static Players getInstance() {
        if (single_instance == null) {
            single_instance = new Players();
        }
        return single_instance;
    }

    public void clearPlayers() {
        if (players != null)
            for (Player player : players) {
                area.container.remove(player);
            }
    }

    public void updatePlayers() {
        clearPlayers();

        players = new Player[playerDataArrayList.size()];

        int index = 0;
        for (PlayerData data : playerDataArrayList) {
            System.out.println("Players " + data.nickname);
            players[index] = new Player(index + 1, data.nickname, data.point);
            area.container.add(players[index]);
            index++;
        }
        area.container.revalidate();
        area.container.repaint();
        area.revalidate();
        area.repaint();
        revalidate();
        repaint();
        add(area);
    }

    class Player extends JPanel {

        JLabel orderComponent;
        JLabel pointComponent;
        JLabel nicknameComponent;

        public Player(int order, String nickname, int point) {
            setBackground(Color.decode("#7859d1"));
            setPreferredSize(new Dimension(220, 70));
            setLayout(null);

            orderComponent = new JLabel(order + "");
            orderComponent.setBounds(16, 20, 16, 16);
            orderComponent.setBackground(Color.decode("#7859d1"));
            orderComponent.setFont(new Font("Serif", Font.BOLD, 16));
            orderComponent.setOpaque(true);
            orderComponent.setForeground(Color.decode("#a18bdf"));
            add(orderComponent);


            pointComponent = new JLabel(point + "p");
            pointComponent.setBounds(40, 5, 50, 50);
            pointComponent.setBackground(Color.decode("#937ada"));
            pointComponent.setOpaque(true);
            pointComponent.setHorizontalAlignment(SwingConstants.CENTER);
            pointComponent.setVerticalAlignment(SwingConstants.CENTER);
            pointComponent.setFont(new Font("Serif", Font.BOLD, 15));
            pointComponent.setForeground(Color.decode("#e4def6"));
            add(pointComponent);

            nicknameComponent = new JLabel(nickname);
            nicknameComponent.setBounds(100, 20, 100, 20);
            nicknameComponent.setBackground(Color.decode("#7859d1"));
            nicknameComponent.setOpaque(true);
            nicknameComponent.setFont(new Font("Serif", Font.PLAIN, 14));
            nicknameComponent.setForeground(Color.decode("#ffffff"));
            add(nicknameComponent);

        }


    }


    class Area extends JScrollPane {

        public JPanel container;

        public Area() {
            setBackground(Color.decode("#7859d1"));
            setBorder(null);
            container = new JPanel();
            container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
            getViewport().add(container);


        }

    }


}
