package com.company.bashar;

import org.junit.Assert;
import org.junit.Test;

public class FindIntersectionsTest {

    private PointCoordinate pointOnOneEndOfLine = new PointCoordinate(2, 1, '\0');
    private PointCoordinate pointOnOtherEndOfLine = new PointCoordinate(6, 5, '\0');

    //test method isPointOnLine -> start

    @Test
    public void isPointOnLineIfPointIsOnSideFalse() {

        PointCoordinate pointOnSideOfLine = new PointCoordinate(3, 4, '\0');
        boolean result = FindIntersections.isPointOnLine(pointOnOneEndOfLine, pointOnSideOfLine, pointOnOtherEndOfLine);

        Assert.assertEquals(false, result);
    }

    @Test
    public void isPointOnLineIfPointIsOnExtendedLineFalse() {

        PointCoordinate pointOnExtendedLine = new PointCoordinate(7, 6, '\0');
        boolean result = FindIntersections.isPointOnLine(pointOnOneEndOfLine, pointOnExtendedLine, pointOnOtherEndOfLine);

        Assert.assertEquals(false, result);
    }

    @Test
    public void isPointOnLineIfPointIsOneOfTheEndPointTrue() {

        boolean result = FindIntersections.isPointOnLine(pointOnOneEndOfLine, pointOnOneEndOfLine, pointOnOtherEndOfLine);

        Assert.assertEquals(true, result);
    }

    @Test
    public void isPointOnLineIfPointIsOnLineTrue() {

        PointCoordinate pointIsOnLine = new PointCoordinate(3.5, 2.5, '\0');
        boolean result = FindIntersections.isPointOnLine(pointOnOneEndOfLine, pointIsOnLine, pointOnOtherEndOfLine);

        Assert.assertEquals(true, result);
    }

    //test method isPointOnLine -> End

    //test method areaOfTriangle -> Start

    @Test
    public void areaOfTriangleIfTriangle() {
        PointCoordinate thirdPointOfTriangle = new PointCoordinate(3, 5, '\0');

        double result = FindIntersections.areaOfTriangle(pointOnOneEndOfLine, pointOnOtherEndOfLine, thirdPointOfTriangle);

        Assert.assertEquals(result, 6, .00000001);
    }

    @Test
    public void areaOfTriangleIfNotTriangle() {
        PointCoordinate thirdPointOfTriangle = new PointCoordinate(4, 3, '\0');

        double result = FindIntersections.areaOfTriangle(pointOnOneEndOfLine, pointOnOtherEndOfLine, thirdPointOfTriangle);

        Assert.assertEquals(result, 0, .00000001);
    }

    //test method areaOfTriangle -> End

    //test method isPointInsideTriangle -> Start

    @Test
    public void isPointInsideTriangleIfPointIsOutsideFalse() {
        PointCoordinate thirdPointOfTriangle = new PointCoordinate(3, 5, '\0');
        PointCoordinate pointToVerify = new PointCoordinate(1, 4, '\0');

        boolean result = FindIntersections.isPointInsideTriangle(pointOnOneEndOfLine, pointOnOtherEndOfLine,
                thirdPointOfTriangle, pointToVerify);

        Assert.assertEquals(result, false);
    }

    @Test
    public void isPointInsideTriangleIfPointIsInsideTrue() {
        PointCoordinate thirdPointOfTriangle = new PointCoordinate(3, 5, '\0');
        PointCoordinate pointToVerify = new PointCoordinate(4, 4, '\0');

        boolean result = FindIntersections.isPointInsideTriangle(pointOnOneEndOfLine, pointOnOtherEndOfLine,
                thirdPointOfTriangle, pointToVerify);

        Assert.assertEquals(result, true);
    }

    @Test
    public void isPointInsideTriangleIfPointIsOneOfTriangleNodesTrue() {
        PointCoordinate thirdPointOfTriangle = new PointCoordinate(3, 5, '\0');
        PointCoordinate pointToVerify = thirdPointOfTriangle;

        boolean result = FindIntersections.isPointInsideTriangle(pointOnOneEndOfLine, pointOnOtherEndOfLine,
                thirdPointOfTriangle, pointToVerify);

        Assert.assertEquals(result, true);
    }

    //test method isPointInsideTriangle -> End

    //test metho doTheEdgesIntersect -> Start

    @Test
    public void doTheLinesIntersectIfIntersectTrue() {
        PointCoordinate pointOnOneEndOfSecondLine = new PointCoordinate(4, 2, '\0');
        PointCoordinate pointOnOtherEndOfSecondLine = new PointCoordinate(2, 4, '\0');

        boolean result = FindIntersections.doTheLinesIntersect(pointOnOneEndOfLine, pointOnOtherEndOfLine,
                pointOnOneEndOfSecondLine, pointOnOtherEndOfSecondLine);

        Assert.assertEquals(true, result);

    }

    @Test
    public void doTheLinesIntersectIfParallelFalse() {
        PointCoordinate pointOnOneEndOfSecondLine = new PointCoordinate(2, 3, '\0');
        PointCoordinate pointOnOtherEndOfSecondLine = new PointCoordinate(3, 4, '\0');

        boolean result = FindIntersections.doTheLinesIntersect(pointOnOneEndOfLine, pointOnOtherEndOfLine,
                pointOnOneEndOfSecondLine, pointOnOtherEndOfSecondLine);

        Assert.assertEquals(false, result);
    }

    @Test
    public void doTheLinesIntersectIfIntersectWhenExtendFalse() {
        PointCoordinate pointOnOneEndOfSecondLine = new PointCoordinate(2, 6, '\0');
        PointCoordinate pointOnOtherEndOfSecondLine = new PointCoordinate(5, 6, '\0');

        boolean result = FindIntersections.doTheLinesIntersect(pointOnOneEndOfLine, pointOnOtherEndOfLine,
                pointOnOneEndOfSecondLine, pointOnOtherEndOfSecondLine);

        Assert.assertEquals(false, result);
    }

    @Test
    public void doTheLinesIntersectIfIntersectAtAnyEndOfAnyLineFalse() {

        PointCoordinate pointOnOneEndOfSecondLine = new PointCoordinate(2, 5, '\0');
        PointCoordinate pointOnOtherEndOfSecondLine = new PointCoordinate(6, 5, '\0');

        boolean result = FindIntersections.doTheLinesIntersect(pointOnOneEndOfLine, pointOnOtherEndOfLine,
                pointOnOneEndOfSecondLine, pointOnOtherEndOfSecondLine);

        Assert.assertEquals(false, result);
    }
}