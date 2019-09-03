package com.company.bashar;

class PointCoordinate {

    private double x;
    private double y;
    private char color;
    private boolean isBoundaryNode = false;

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

    boolean getBoundaryNode() {
        return isBoundaryNode;
    }
    void setColor(char c) {
        color = c;
    }

    void setBoundaryNode(boolean isBoundary) {
        isBoundaryNode = isBoundary;
    }

}
