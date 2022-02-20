package edu.firat.garticj.model;

import java.io.Serializable;
import java.util.ArrayList;

public class PlayerDataList extends Message implements Serializable {


    public ArrayList<PlayerData> players;

    public PlayerDataList(ArrayList<PlayerData> players) {
        super("PlayerDataList");
        this.players = players;
    }


}
