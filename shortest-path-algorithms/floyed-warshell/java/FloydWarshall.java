package com.basicsstrong.dsa.graph;

import java.util.Arrays;

public class FloydWarshall {
	
	final static int INF = 9999, V = 4; 

	static void floydWarshall(int graph[][]) 
	{ 
		int i, j, k; 
		
		int[][] dist = Arrays.copyOf(graph, V);
		
		//setting each vertex as intermediate
		for (k = 0; k < V; k++) { 
			// each vertex as source 
			for (i = 0; i < V; i++) { 
				//as destination for above picked source 
				for (j = 0; j < V; j++) 
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]); 
			} 
		} 

		//Print the shortest distance matrix 
		printSolution(dist); 
	} 

	static void printSolution(int dist[][]) 
	{ 
		System.out.println("Shortest distance between each pair of vertices"); 
		
		for (int i=0; i<V; ++i) 
		{ 
			for (int j=0; j<V; ++j) 
			{ 
				if (dist[i][j]==INF) 
					System.out.print("INF "); 
				else
					System.out.print(dist[i][j]+" "); 
			} 
			System.out.println(); 
		} 
	} 

	public static void main (String[] args) 
	{ 

		int graph[][] = { {0, 3, INF, 2}, 
						{4, 0, 6, 7}, 
						{INF, 5, 0, INF}, 
						{INF, INF, 1, 0} 
						}; 

		// Print the solution 
		floydWarshall(graph); 
	} 
} 

