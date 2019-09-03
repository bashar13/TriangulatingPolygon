package com.company.bashar;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {

    private static Scanner reader;

    private static int totalBoundaryNodes;
    private static char[] colors = {'r', 'b', 'y'}; //an array of available colors
    private static ArrayList<PointCoordinate> nodeList = new ArrayList<>(); //an arrayList of all the nodes (points)

    public static void main(String[] args) {

        // takes input -> start
        String inputFile = "src/input.txt";
        try {
            reader = new Scanner(new File(inputFile));
        } catch(IOException e) {
            e.printStackTrace();
        }

        int numberOfTestCases = reader.nextInt();

        for (int t = 1; t <= numberOfTestCases; t++) {
            nodeList.clear();

            totalBoundaryNodes = reader.nextInt();
            inputNodeData(totalBoundaryNodes, true);

            int totalInsideNodes;
            totalInsideNodes = reader.nextInt();
            inputNodeData(totalInsideNodes, false);

            //takes input -> finish

            //an adjacency matrix to contain the graph of size total-nodes
            int[][] adjacencyMatrix = new int[nodeList.size()][nodeList.size()];

            //sets the boundary connections (edges), (1 represents a connection, 0 represents no-connection)
            for(int i = 0; i< totalBoundaryNodes; i++) {
                if(i == totalBoundaryNodes - 1) {
                    adjacencyMatrix[i][0] = adjacencyMatrix[0][i] = 1;
                } else {
                    adjacencyMatrix[i][i+1] = adjacencyMatrix[i+1][i] = 1;
                }
            }

            //log boundary edges
//        for (int i=0; i<nodeList.size(); i++) {
//            for (int j=0; j<nodeList.size(); j++) {
//                System.out.print(adjacencyMatrix[i][j]);
//            }
//            System.out.println();
//        }

            //System.out.println();

            createTriangulatingPolygon(adjacencyMatrix);

            //log all the edges after creating triangulating polygon
//        int edges = 0;
//        for (int i=0; i<nodeList.size(); i++) {
//            for (int j=0; j<nodeList.size(); j++) {
//                System.out.print(adjacencyMatrix[i][j]);
//                if (adjacencyMatrix[i][j] == 1) {
//                    edges++;
//                }
//            }
//            System.out.println();
//        }
//        System.out.println("Edges: " + edges);

            int indexOfFirstInsideNode = totalBoundaryNodes;
            System.out.println("Case: " + t);
            if (doColorTheTriangle(adjacencyMatrix, indexOfFirstInsideNode)) {
                System.out.println("Hurrah! Solution Possible!!");

            } else
                System.out.println("Solution not possible");
        }
    }

    /*
    Function takes input
    Parameter: node = (int) number of nodes to take input, boundary = (boolean) the nodes are boundary node or not
     */
    static private void inputNodeData(int node, boolean boundary) {

        for(int i=0; i< node; i++) {
            int x = reader.nextInt();
            int y = reader.nextInt();
            char c;
            if (boundary) {
                c = reader.next().charAt(0);
            } else {
                c = '\0';
            }

            PointCoordinate pointCoordinate = new PointCoordinate(x, y, c);
            nodeList.add(pointCoordinate);
        }
    }

    /*
    connects the nodes to create Triangulating polygon with non overlapping triangles
    Populates the connection array with all possible edges.
     */
    static private void createTriangulatingPolygon(int[][] adjacencyMatrix) {

        for (int i = 0; i< nodeList.size(); i++) {
            for(int j = 0; j<nodeList.size(); j++) {
                if (i != j) {
                    if (nodeList.get(j).getX() <= nodeList.get(i).getX()) {
                        if (adjacencyMatrix[i][j] != 1) {
                            if (FindIntersections.isLineInsidePolygon(nodeList.get(i), nodeList.get(j), totalBoundaryNodes, nodeList)) {
                                int p = checkAnyExistingPointOnTheLine(i, j);
                                p = (p != -1)? p : j;
                                if (!checkAnyExistingLineIntersect(adjacencyMatrix, i, p)) {
                                    adjacencyMatrix[i][p] = adjacencyMatrix[p][i] = 1;
                                }
                            }

                        }

                    }
                }
            }
        }
    }

    /*
    checks if there is any point of the polygon graph lies on the edge connecting two points
    Parameter: point1 (int) = one point (node) of the edge, point2 (int) = other point (node) of the edge
    return: Returns the point (node) that lies on the edge (if any), otherwise returns -1 indicating no point on the edge
     */
    static private int checkAnyExistingPointOnTheLine(int point1, int point2) {

        for(int i = 0; i< nodeList.size(); i++) {
            if (i != point1 && i != point2) {
                if (FindIntersections.isPointOnLine(nodeList.get(point1), nodeList.get(i), nodeList.get(point2))) {

                    return i;
                }
            }
        }
        return -1;
    }

    /*
    checks if the assuming edge intersects with any othe edge of the polygon
    Parameter: point1 and point2 that we are expecting to connect with the connectionArray
    Return: if intersects returns true else false
     */
    static private boolean checkAnyExistingLineIntersect(int[][] connectionArray, int point1, int point2) {
        for (int i=0; i<nodeList.size(); i++) {
            for (int j=0; j<nodeList.size(); j++) {
                if (connectionArray[i][j] == 1) {
                    if (FindIntersections.doTheLinesIntersect(nodeList.get(point1), nodeList.get(point2),
                            nodeList.get(i), nodeList.get(j))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /*
    A recursive function to color the inside nodes of the triangulatingPolygon with all possible combination of give number
    of colors.
    Parameter: The graph- triangulatingPolygon, The node needs to be colored
    Return: Returns true if a solution exists for the given condition else false
     */
    static private boolean doColorTheTriangle(int[][] triangulatingPolygon, int node) {

        //after assigning a color to all the inside nodes, check whether the solution exists or not
        if ( node == nodeList.size()) {
            return checkTwoAndNoMoreCompleteTriangle(triangulatingPolygon);

        }

        for (char c : colors) {
            nodeList.get(node).setColor(c);

            if (doColorTheTriangle(triangulatingPolygon,node + 1)) {
                return true;
            }
        }
        return false;
    }

    /*
    verifies if the solution exists for the assigned combination of colors to all the inside nodes
    Returns: returns true if the solution exists else false
     */
    static private boolean checkTwoAndNoMoreCompleteTriangle(int[][] triangulatingPolygon) {

        int numberOfCompleteTriange = 0;

        for(int i=0; i<nodeList.size(); i++) { //first node of the Triangle
            for(int j = i; j<nodeList.size(); j++) { //Second node of the Triangle
                if(triangulatingPolygon[i][j] == 1) {
                    for(int k=j; k<nodeList.size(); k++) { //third node of the Triangle
                        if(triangulatingPolygon[i][k] == 1 && triangulatingPolygon[j][k] == 1) {
                            if(nodeList.get(i).getColor() != nodeList.get(j).getColor()
                                    && nodeList.get(i).getColor() != nodeList.get(k).getColor()
                                    && nodeList.get(j).getColor() != nodeList.get(k).getColor()) {

                                //checks for any invalid triangle
                                int p;
                                for(p = totalBoundaryNodes; p< nodeList.size(); p++) {
                                    if (p != i && p != j && p!= k) {
                                        if (FindIntersections.isPointInsideTriangle(nodeList.get(i), nodeList.get(j), nodeList.get(k), nodeList.get(p)))
                                            break;
                                    }

                                }
                                if(p == nodeList.size()) {
                                    numberOfCompleteTriange++;
                                    if (numberOfCompleteTriange > 2) {
                                        return false;
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }

        return (numberOfCompleteTriange == 2);
    }

}
