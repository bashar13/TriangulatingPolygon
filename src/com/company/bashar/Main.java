package com.company.bashar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    private static Scanner reader;

    private static String inputFile = "src/input.txt";



    private static ArrayList<PointCoordinate> nodeList = new ArrayList<>();
    private static ArrayList<PointCoordinate> insidePointList = new ArrayList<>();
    private static ArrayList<String> nodeColor = new ArrayList<String>();

    public static void main(String[] args) {

        try {
            reader = new Scanner(new File(inputFile));
        } catch(IOException e) {
            e.printStackTrace();
        }

        int boundaryNodes;
        int insideNodes;

        boundaryNodes = reader.nextInt();



        inputBoundaryNodesData(boundaryNodes);


        //System.out.println(nodeColor.get(0));
        insideNodes = reader.nextInt();

        inputInsideNodesData(insideNodes);

//        for(int i = 0; i < boundaryNodes + insideNodes; i++) {
//            System.out.println(nodeList.get(i).getX());
//        }
//        for (String s: nodeColor){
//            System.out.println(s);
//        }

        Collections.sort(insidePointList, new Comparator<PointCoordinate>() {
            @Override
            public int compare(PointCoordinate o1, PointCoordinate o2) {
                return Integer.compare(o1.getX(), o2.getX());
            }
        });

        nodeList.addAll(insidePointList);

        for(int i = 0; i < boundaryNodes + insideNodes; i++) {
            System.out.println(nodeList.get(i).getX());
        }
//        for (String s: nodeColor){
//            System.out.println(s);
//        }

        int[] connectionArray = new int[nodeList.size()];
    }

    static void inputBoundaryNodesData(int node) {

        for(int i=0; i< node; i++) {
            int x = reader.nextInt();
            int y = reader.nextInt();

            PointCoordinate pointCoordinate = new PointCoordinate(x, y);
            nodeList.add(pointCoordinate);
            nodeColor.add(reader.next());

        }
    }

    static void inputInsideNodesData(int node) {

        for(int i = 0; i< node; i++) {

            int x = reader.nextInt();
            int y = reader.nextInt();

            PointCoordinate pointCoordinate = new PointCoordinate(x, y);
            insidePointList.add(pointCoordinate);

            //nodeColor.add(input.next());

        }
    }


}
