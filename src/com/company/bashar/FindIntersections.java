package com.company.bashar;

class FindIntersections {

    //checks if the point r lies on the line 'pq' or not
    static boolean checkPointOnLineCalculationMethod(PointCoordinate p, PointCoordinate q, PointCoordinate r) {

        double pr = Math.sqrt(Math.pow((p.getX() - r.getX()), 2)
                + Math.pow((p.getY() - r.getY()), 2));
        double pq = Math.sqrt(Math.pow((p.getX() - q.getX()), 2)
                + Math.pow((p.getY() - q.getY()), 2));
        double qr = Math.sqrt(Math.pow((q.getX() - r.getX()), 2)
                + Math.pow((q.getY() - r.getY()), 2));

        return pr == pq + qr;
    }

    /*
    Calculates the area of a Triangle
    Parameter: Three nodes of a Triange
    Return: area of the Triangle
     */

    static double areaOfTriangle(PointCoordinate p, PointCoordinate q, PointCoordinate r) {

        return Math.abs((p.getX()*(q.getY()-r.getY()) + q.getX()*(r.getY()-p.getY())+
                r.getX()*(p.getY()-q.getY()))/2.0);
    }

    /*
    Checks if the point a is inside the Triange formed by points p,q and r
     */
    static boolean isPointInsideTriangle (PointCoordinate p, PointCoordinate q, PointCoordinate r, PointCoordinate a) {

        double areaPQR = areaOfTriangle(p, q, r);
        double areaAPQ = areaOfTriangle(a, p, q);
        double areaAQR = areaOfTriangle(a, q, r);
        double areaARP = areaOfTriangle(a, r, p);

        return areaPQR == areaAPQ + areaAQR + areaARP;
    }

    // checks if point q lies on the line 'pr' or not
    static boolean checkPointOnLine(PointCoordinate p, PointCoordinate q, PointCoordinate r)
    {
        if (q.getX() <= Math.max(p.getX(), r.getX()) && q.getX() >= Math.min(p.getX(), r.getX()) &&
                q.getY() <= Math.max(p.getY(), r.getY()) && q.getY() >= Math.min(p.getY(), r.getY()))
            return true;

        return false;
    }

//    static private int orientation(PointCoordinate p, PointCoordinate q, PointCoordinate r)
//    {
//        double val = (q.getY() - p.getY()) * (r.getX() - q.getX()) -
//                (q.getX() - p.getX()) * (r.getY() - q.getY());
//
//        if (val == 0) return 0; // colinear
//
//        return (val > 0)? 1: 2;
//    }

//    static boolean doIntersect(PointCoordinate p1, PointCoordinate q1, PointCoordinate p2, PointCoordinate q2)
//    {
//        // Find the four orientations needed to check intersection
//        int o1 = orientation(p1, q1, p2);
//        int o2 = orientation(p1, q1, q2);
//        int o3 = orientation(p2, q2, p1);
//        int o4 = orientation(p2, q2, q1);
//
//
//        if (o1 != o2 && o3 != o4)
//            return true;
//
//        return false; // Doesn't fall in any of the above cases
//    }

    static boolean checkIftheEdgeIntersectsWithAnotherEdge(PointCoordinate p1, PointCoordinate q1, PointCoordinate p2,
                                                           PointCoordinate q2)
    {
        // Line 'p1q1' represented by p1x + q1y = c1
        double a1 = q1.getY() - p1.getY();
        double b1 = p1.getX() - q1.getX();
        double c1 = a1 * (p1.getX()) + b1 * (p1.getY());

        // Line 'p2q2' represented as p2x + q2y = c2
        double a2 = q2.getY() - p2.getY();
        double b2 = p2.getX() - q2.getX();
        double c2 = a2 * (p2.getX()) + b2 * (p2.getY());

        double determinant = a1*b2 - a2*b1;

        if (determinant == 0)
        {
            // The lines are parallel. This is simplified
            // by returning a pair of FLT_MAX
            return false;
        }
        else
        {
            double x = (b2*c1 - b1*c2)/determinant;
            double y = (a1*c2 - a2*c1)/determinant;

            //check if the intersection point is on of the points from two edges 'p1q1' and 'p2q2'
            if ( (x == p1.getX() && y == p1.getY()) || (x== q1.getX() && y == q1.getY()) ||
                    (x == p2.getX() && y == p2.getY()) ||  (x== q2.getX() && y == q2.getY())) {

                return false;
            } else {
                //checks if the intersection point is on the both edges or on the extension of the edges
                PointCoordinate pointCoordinate = new PointCoordinate(x, y, '\0');
                return checkPointOnLine(p1, pointCoordinate, q1) && checkPointOnLine(p2, pointCoordinate, q2);
            }

        }
    }
}
