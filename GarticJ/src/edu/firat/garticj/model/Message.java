package edu.firat.garticj.model;

import java.io.Serializable;

public abstract class Message implements Serializable {

    private final String dataType;

    public String getDataType() {
        return dataType;
    }

    public Message(String dataType) {
        this.dataType = dataType;
    }
}
