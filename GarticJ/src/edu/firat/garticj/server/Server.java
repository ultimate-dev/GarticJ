package edu.firat.garticj.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Server {

    private ServerSocket serverSocket;

    public Server(int port) {

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        startServer();
    }

    public void startServer(){

        try {
            while (!serverSocket.isClosed()){

                Socket socket = serverSocket.accept();
                System.out.println("A client has connected");
                ClientHandler clientHandler=new ClientHandler(socket);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void gameLoop(){

        Timer timer = new Timer();

        timer.schedule( new TimerTask() {
            public void run() {

//                String objectToDraw = getName();
//                String playerToDraw = choosePlayer();

            }
        }, 0, 60*1000);

    }

    public String getName(){

        File file = new File(
                "src\\res\\names.txt");
        try {
            BufferedReader br;
            br = new BufferedReader(new FileReader(file));
            String st;
            Random random = new Random();
            int number = random.nextInt(30);
            int index = 0;
            while ((st = br.readLine()) != null)
            {
                if(number == index) {
                    return st;
                }
                index++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "sun";
    }

    public void closeServerSocket(){

        try{
            if (serverSocket!=null){
                serverSocket.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}