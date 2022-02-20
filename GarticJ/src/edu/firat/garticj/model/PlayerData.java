package edu.firat.garticj.model;

import edu.firat.garticj.server.ClientHandler;

import java.io.Serializable;
import java.util.ArrayList;

public class PlayerData extends Message implements Serializable {

    public String nickname;
    public int point;

    public PlayerData(String nickname, int point) {
        super("PlayerData");
        this.nickname = nickname;
        this.point = point;
    }
}
