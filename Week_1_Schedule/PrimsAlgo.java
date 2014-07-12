/*
    Right answers for edges.txt: -3612829
                  for testPrim.txt:  2624

*/


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PrimsAlgo {
    
    private static class Edge {
        int cost;
        int vertice1;
        int vertice2;
        
        public Edge(int aCost, int aVertice1, int aVertice2) {
            cost = aCost;
            vertice1 = aVertice1;
            vertice2 = aVertice2;
        }
    }

    
    
    public static void main(String[] args) throws FileNotFoundException {   
        Scanner in = new Scanner(new File("edges.txt")); //System.in "testPrim.txt"
        int n = in.nextInt(); // number of verticies
        int m = in.nextInt(); // number of edges
        Edge[] edgeArray = new Edge[m];// array of edges
        // X - is a subset of all verticies V which are added into MST
        boolean[] x = new boolean[n+1]; // whether vertice is in X, starting from 1
        boolean[] eX = new boolean[m]; // whether edge is in X
        int xCount = 0; // how many verticies were already added 
        long sumOfEdges = 0; // sum of adges added into MST
        
        
        // read input
        int vert1;
        int vert2;
        int aCost;
        Edge e;
        for (int i = 0; i < m; i++) {
            vert1 = in.nextInt();
            vert2 = in.nextInt();
            aCost = in.nextInt();
            e = new Edge (aCost, vert1, vert2);
            edgeArray[i] = e;
        }
        
        // initializing
        x[1] = true;
        xCount++;
        
        // main loop
        int minCost;
        int minEdge = 0; // index of the cheapest edge
        while (xCount != n) {
            minCost = Integer.MAX_VALUE;
            // lastEdge = Integer.MIN_VALUE;
            for (int i = 0; i < m; i++) {
                if (!eX[i]) {
                    if (x[edgeArray[i].vertice1] ^ x[edgeArray[i].vertice2]) {
                        if (edgeArray[i].cost < minCost) {
                            minCost = edgeArray[i].cost;
                            //if (lastEdge != Integer.MIN_VALUE) {}
                            minEdge = i;
                        }
                    }
                }
            }
            sumOfEdges += minCost; 
            eX[minEdge] = true; // edding edge to X
            x[edgeArray[minEdge].vertice1] = true; // adding verticies to X
            x[edgeArray[minEdge].vertice2] = true;
            xCount++;
        }

        System.out.println("Minimum cost of MST according to Prim's algorithm is " + sumOfEdges);
        
    }
}