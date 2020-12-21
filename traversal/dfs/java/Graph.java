package com.basicsstrong.dsa.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class Graph {
	
	//Adjacency list representation
	// array[1] -> list[...] - adjacent vertices for vertex 1
 	private ArrayList<Integer>[] graph;
	private int V;
	private boolean visited[];
	
	@SuppressWarnings("unchecked")
	Graph(int vertices){
		V = vertices;
		graph = new ArrayList[V];
		visited = new boolean[V];
		
		for (int i = 0; i < V; i++) {
			graph[i] = new ArrayList<Integer>();
		}
	}
	
	//adding edge
	void addEdge(int src, int dest) {
		graph[src].add(dest);
	}
	
	//DFS - recursive
	void dfsTraversal(int vertex) {
		visited[vertex] = true;
		
		System.out.print(vertex + " ");
		
		Iterator<Integer> i = graph[vertex].listIterator();
		while(i.hasNext()) {
			int adj = i.next();
			
			if(!visited[adj])
				dfsTraversal(adj);
		}
		
	}
	
	//dfs iterative
	void dfsTraversalIter(int s) {
		
		Arrays.fill(visited, false);
		
		Stack<Integer> stack = new Stack<>();
		
		stack.push(s);
		
		while(!stack.empty()) {
			//pop a vertex
			s = stack.pop();
			
			if(!visited[s]) {
				System.out.print(s + " ");
				visited[s] = true;
			}
			
			Iterator<Integer> itr = graph[s].iterator();
			
			while(itr.hasNext()) {
				int v = itr.next();
				if(!visited[v]) {
					stack.push(v);
				}
			}
		}
	}

	public static void main(String args[]) {
		
		Graph g = new Graph(13);
		
		g.addEdge(1, 2);
	    g.addEdge(1, 3);
	    g.addEdge(1, 5);
	    g.addEdge(2, 4);
	    g.addEdge(2, 10);
	    g.addEdge(2, 5);
	    g.addEdge(2, 7);
	    g.addEdge(3, 6);
	    g.addEdge(4, 7);
	    g.addEdge(5, 2);
	    g.addEdge(5, 8);
	    g.addEdge(6, 9);
	    g.addEdge(8, 11);
	    g.addEdge(11, 12);

	    System.out.println("DFS traversal - recursive");
	    g.dfsTraversal(1);
	    
	    System.out.println("DFS traversal - iterative");
	    g.dfsTraversalIter(1);
		
	}

}
