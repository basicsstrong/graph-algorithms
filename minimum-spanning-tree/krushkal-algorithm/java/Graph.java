package com.basicsstrong.dsa.graph;

import java.util.Arrays;

class Graph {
	
	class Edge implements Comparable<Edge>{
		int src, dest, weight;

		@Override
		public int compareTo(Edge edge) {
			return this.weight - edge.weight;
		}
	};
	
	class subset{
		int parent, rank;
	}

	int V, E;
	Edge edges[];

	Graph(int v, int e) {
		V = v;
		E = e;
		edges = new Edge[E];
		for (int i = 0; i < E; ++i)
			edges[i] = new Edge();
	}
	
	void kruskalAlgo() {
		Edge mst[] = new Edge[V];
		int e = 0;
		
		for (int i = 0; i < mst.length; i++) {
			mst[i] = new Edge();
		}
		
		//step 1
		Arrays.sort(edges);
		
		//creating subsets
		subset subsets[] = new subset[V];
		
		for (int i = 0; i < subsets.length; i++) {
			subsets[i] = new subset();
		}
		
		for(int v=0; v<V; v++) {
			subsets[v].parent = v;
			subsets[v].rank = 0;
		}
		
		int i = 0;
		//step2 pick the smallest edge
		while(e < V-1) {
			Edge edge = new Edge();
			edge = edges[i++];
			
			//get parent root or src, dest
			int x = find(subsets, edge.src);
			int y = find(subsets, edge.dest);
			
			//perform union if edge is not making cycle
			if(x!=y) {
				//include edge
				mst[e++] = edge;
				union(subsets, x, y);
			}
		}
		
		int minCost = 0;
		//print
		for (int j = 0; j < e; j++) {
			System.out.println(mst[j].src + " - " + mst[j].dest + ": "+mst[j].weight);
			minCost += mst[j].weight;
		}
		System.out.println("Minimum cost: "+ minCost);
		
	}

	private void union(subset[] subsets, int x, int y) {

		//union by rank
		if(subsets[x].rank < subsets[y].rank)
			subsets[x].parent = y;
		else if(subsets[y].rank < subsets[x].rank)
			subsets[y].parent = x;
		else {
			subsets[y].parent = x;
			subsets[x].rank++;
		}
	}

	private int find(subset[] subsets, int ele) {
		if(subsets[ele].parent != ele)
		//path compression
			subsets[ele].parent = find(subsets, subsets[ele].parent);
		return subsets[ele].parent;
	}

	public static void main(String[] args) {
		
		int vertices = 8; 
		int edges = 13; 
		Graph graph = new Graph(vertices, edges);

		graph.edges[0].src = 4;
		graph.edges[0].dest = 2;
		graph.edges[0].weight = 1;

		graph.edges[1].src = 2;
		graph.edges[1].dest = 6;
		graph.edges[1].weight = 2;

		graph.edges[2].src = 2;
		graph.edges[2].dest = 7;
		graph.edges[2].weight = 9;

		graph.edges[3].src = 4;
		graph.edges[3].dest = 3;
		graph.edges[3].weight = 8;

		graph.edges[4].src = 0;
		graph.edges[4].dest = 1;
		graph.edges[4].weight = 8;

		graph.edges[5].src = 3;
		graph.edges[5].dest = 7;
		graph.edges[5].weight = 7;

		graph.edges[6].src = 0;
		graph.edges[6].dest = 5;
		graph.edges[6].weight = 4;

		graph.edges[7].src = 4;
		graph.edges[7].dest = 0;
		graph.edges[7].weight = 2;
		
		graph.edges[8].src = 2;
		graph.edges[8].dest = 3;
		graph.edges[8].weight = 4;
		
		graph.edges[9].src = 0;
		graph.edges[9].dest = 3;
		graph.edges[9].weight = 6;
		
		graph.edges[10].src = 2;
		graph.edges[10].dest = 1;
		graph.edges[10].weight = 10;
		
		graph.edges[11].src = 3;
		graph.edges[11].dest = 5;
		graph.edges[11].weight = 8;
		
		graph.edges[12].src = 6;
		graph.edges[12].dest = 1;
		graph.edges[12].weight = 7;
		
		graph.kruskalAlgo();
		
	}
}