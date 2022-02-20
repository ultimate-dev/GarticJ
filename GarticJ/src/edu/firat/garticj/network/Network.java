package edu.firat.garticj.network;

import edu.firat.garticj.model.PlayerData;
import edu.firat.garticj.model.PlayerDataList;
import edu.firat.garticj.views.Drawing.Players;
import edu.firat.garticj.views.canvas.DrawArea;
import edu.firat.garticj.model.DrawData;
import edu.firat.garticj.model.Message;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;

public class Network {

    private static Socket socket;
    private static ObjectOutputStream oos;
    private static ObjectInputStream ois;
    private static DrawArea drawArea;
    private static Players players;

    public Network(String host, String port) {
        try {
            socket = new Socket(host, Integer.parseInt(port));
            drawArea = DrawArea.getInstance();
            players = Players.getInstance();
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            listenForData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendData(Message message) {

        try {
            oos.writeObject(message);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listenForData() {

        new Thread(() -> {

        while (socket.isConnected()) {
            try {
                Message message = (Message) ois.readObject();

                if(message.getDataType().equals("DrawData")){
                    drawArea.drawBroadcastData((DrawData) message);
                }
//                else if (message.getDataType().equals("PlayerData")){
//                    players.playerDataArrayList = ((PlayerData) message);
//                    players.updatePlayers();
//                }
                else if (message.getDataType().equals("PlayerDataList")){
                    players.playerDataArrayList = ((PlayerDataList) message).players;
                    for (PlayerData data :((PlayerDataList) message).players){
                        System.out.println(data);
                    }
                    players.updatePlayers();
                }

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        }).start();
    }



}
