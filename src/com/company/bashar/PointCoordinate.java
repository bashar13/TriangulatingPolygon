package com.company.bashar;

public class PointCoordinate {

    private double x;
    private double y;
    private char color;

    PointCoordinate(double cX, double cY, char c) {
        x = cX;
        y = cY;
        color = c;
    }

    double getX() {
        return x;
    }

    double getY() {
        return y;
    }

    char getColor() {
        return color;
    }
    void setColor(char c) {
        color = c;
    }

}
