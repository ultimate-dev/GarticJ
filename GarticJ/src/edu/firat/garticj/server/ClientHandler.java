package edu.firat.garticj.server;

import edu.firat.garticj.model.DrawData;
import edu.firat.garticj.model.Message;
import edu.firat.garticj.model.PlayerData;
import edu.firat.garticj.model.PlayerDataList;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{

    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    public static ArrayList<PlayerData> playerDataArrayList = new ArrayList<>();
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public ClientHandler(Socket socket){
        try {
            this.socket = socket;
            this.oos = new ObjectOutputStream(socket.getOutputStream());
            this.ois = new ObjectInputStream(socket.getInputStream());
            clientHandlers.add(this);

            System.out.println("SERVER: someone has entered the chat!");
        } catch (IOException e) {
            closeEverything(socket,oos,ois);
        }
    }

    @Override
    public void run() {

        while (socket.isConnected()){
            try {
                Message message = (Message) ois.readObject();

                if (message.getDataType().equals("DrawData"))
                {
                    broadcastDrawData((DrawData) message);
                }
                else if (message.getDataType().equals("PlayerData")){
                    broadcastPlayerData((PlayerData) message);
                }
//                else if (message.getDataType().equals("PlayerDataList")){
//                    broadcastPlayerData((PlayerData) message);
//                }

            } catch (IOException | ClassNotFoundException e){
                closeEverything(socket,oos,ois);
                break;
            }
        }
    }

    public void broadcastDrawData(DrawData drawData){

        for (ClientHandler clientHandler : clientHandlers){
            try {
                if (this != clientHandler)
                {
                    clientHandler.oos.writeObject(drawData);
                    clientHandler.oos.flush();
                }
            } catch (IOException e){
                closeEverything(socket,oos,ois);
            }
        }
    }


    public void broadcastPlayerData(PlayerData playerData){

        playerDataArrayList.add(playerData);
        PlayerDataList playerDataList = new PlayerDataList(playerDataArrayList);

        for (ClientHandler clientHandler : clientHandlers){

            try {
                if (this != clientHandler) {
                   // playerDataArrayList.add(playerData);
                    //PlayerData.players.add(playerData);
                    clientHandler.oos.writeObject(playerDataList);
                    clientHandler.oos.flush();
                }
            } catch (IOException e){
                closeEverything(socket,oos,ois);
            }
        }
    }

    public void removeClientHandler(){
        clientHandlers.remove(this);
        System.out.println(("SERVER: someone has left the chat!"));
    }

    public void closeEverything(Socket socket, ObjectOutputStream oos, ObjectInputStream ois){
        removeClientHandler();
        try {
            if (ois!=null){
                ois.close();
            }
            if (oos !=null){
                oos.close();
            }
            if (socket !=null){
                socket.close();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
