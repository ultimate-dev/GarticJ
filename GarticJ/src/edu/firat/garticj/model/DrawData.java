package edu.firat.garticj.model;

import java.io.Serializable;

public class DrawData extends Message implements Serializable {

    public int oldX,oldY,currentX,currentY;


    public DrawData(int oldX, int oldY, int currentX, int currentY) {
        super("DrawData");
        this.oldX = oldX;
        this.oldY = oldY;
        this.currentX = currentX;
        this.currentY = currentY;
    }
}
