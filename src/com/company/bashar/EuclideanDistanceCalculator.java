package com.company.bashar;

import java.awt.*;

class EuclideanDistanceCalculator {

    static double calculate(PointCoordinate point1, PointCoordinate point2) {

        double sum = Math.pow((point1.getX() - point2.getX()), 2)
                + Math.pow((point1.getY() - point2.getY()), 2);

        return Math.sqrt(sum);
    }

}
