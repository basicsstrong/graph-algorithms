package com.basicsstrong.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JohnsonsAlgorithm {
	public static void main(String[] args) {
		
		int[][] graph = {{0, 4, 0, 0, 1},
				         {0, 0, 0, 0, 0},
				         {0, 7, 0, -2, 0},
				         {0, 1, 0, 0, 0},
				         {0, 0, 0, -5, 0}};
		
		//Johnson's Algo
		allPairShortestPath(graph);

	}

	private static void allPairShortestPath(int[][] graph) {
		
		List<List<Integer>> edges = new ArrayList<List<Integer>>();
		
		for (int u = 0; u < graph.length; u++) {
			for (int v = 0; v < graph[u].length; v++) {
				
				if(graph[u][v] != 0)
					edges.add(List.of(u, v, graph[u][v]));
			}
			
		}
		
		int[] distance = bellmanFord(edges, graph.length);
		
		//new positive edge weights
		
		int[][] newGraph = new int[graph.length][graph[0].length];
		
		System.out.println("Modified Graph after performing bellmanFord is : ");
		
		for (int u = 0; u < graph.length; u++) {
			for (int v = 0; v < graph[u].length; v++) {
				
				if(graph[u][v] != 0)
					newGraph[u][v] = distance[u] + graph[u][v] - distance[v];
				
				System.out.print(newGraph[u][v] + " ");
				
			}
			
			System.out.println();
		}
		
		//Run dijkstra for every vertex as source one by one
		
		for (int src = 0; src < graph.length; src++) {
			System.out.println("Shortest distance from vertex : " + src);
			dijkstra(graph, newGraph, src);
		}
		
	}

	private static void dijkstra(int[][] graph, int[][] newGraph, int src) {
		
		int V = newGraph.length;
		
		boolean[] visited = new boolean[V];
		
		int[] distance = new int[V];
		
		for (int i = 0; i < V ; i++) {
			
			distance[i] = Integer.MAX_VALUE;
			
		}
		
		//source to source = 0
		
		distance[src] = 0;
		
		for (int i = 0; i < V-1 ; i++) {
			
			int minVertex  = findMinVertex(distance, visited);
			
			visited[minVertex] = true;
			
			for (int j = 0; j < V ; j++) {
				
				if( graph[minVertex][j] != 0 && !visited[j]) {
					int newDist = distance[minVertex] + newGraph[minVertex][j];
					
					if(newDist < distance[j]) {
						distance[j] = newDist;
					}
				}
				
			}
			
		}
		
		for (int i = 0; i < V ; i++) {
			
			System.out.println(i + " " + distance[i]);
			
		}
		
	}

	private static int findMinVertex(int[] distance, boolean[] visited) {
		
		int minVertex = -1;
		
		for (int i = 0; i < distance.length; i++) {
			if( !visited[i] && (minVertex == -1 || distance[i] < distance[minVertex])) {
				minVertex = i;
			}
		}
		return minVertex;
	}

	private static int[] bellmanFord(List<List<Integer>> edges, int V) {
		
		//add arbitrary source
		
		int[] dist = new int[V + 1];
		
		for (int vertex : dist) {
			
			dist[vertex] = Integer.MAX_VALUE;
			
		}
		
		dist[V] = 0;
		
		//add edges from source to all the other vertices
		
		for (int i = 0; i < V; i++) {
			
			edges.add(List.of(V, i, 0));
			
		}
		
		//relax V-1 times
		
		for (int i = 0; i < V; i++) {
			for (List<Integer> li : edges) {
				
				int u = li.get(0);
				int v = li.get(1);
				int w = li.get(2);
				
				if( dist[u] != Integer.MAX_VALUE && (dist[u] + w < dist[v])) {
					dist[v] = dist[u] + w;
				}
				
			}
		}
		
		//discard arb source
		
		return Arrays.copyOfRange(dist, 0, V);
	}

	
}
