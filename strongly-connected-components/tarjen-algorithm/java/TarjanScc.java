package com.basicsstrong.dsa.graph;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class TarjanScc {

	private int n;
	private boolean[] visited;
	private int[] dfsNum, dfsLow;
	private Stack<Integer> stack;
	private LinkedList<Integer>[] graph;

	  private static final int UNVISITED = -1;
 	 
	  public TarjanScc(LinkedList<Integer>[] graph) {
	    if (graph == null) throw new IllegalArgumentException("Graph cannot be null.");
	    this.graph = graph;
	    this.n = graph.length;
	  }
	  
	public static LinkedList<Integer>[] createGraph(int n){
		LinkedList<Integer>[] graph = new LinkedList[n];
		for (int i = 0; i < n; i++) 
			graph[i] = new LinkedList<Integer>();
		return graph;
	}
	
	// Adds a directed edge from node 'from' to node 'to'
	public static void addEdge(LinkedList<Integer>[] graph, int from, int to) {
		graph[from].add(to);
	}

	  // method to get the connected components of this graph. 
	  public void solve() {
	    dfsNum = new int[n];
	    dfsLow = new int[n];
	    visited = new boolean[n];
	    stack = new Stack<>();
	    Arrays.fill(dfsNum, UNVISITED);

	  //find the sccs rooted with each vertex i.
	    for (int i = 0; i < n; i++) {
	      if (dfsNum[i] == UNVISITED)
	        dfs(i);
	    }
	  }

	  private void dfs(int curr) {
		int num = 0;
		dfsNum[curr] = dfsLow[curr] = num++;
		//pushing node onto stack as we explore it
	    stack.push(curr);
	    visited[curr] = true;
	    
	    //now we traverse to each outgoing vertex from the current vertex 
	    Iterator<Integer> i = graph[curr].iterator();
	    
	    while(i.hasNext()) {
	    	//take the vertex to be visited in a variable
	    	int to = i.next();
	    	//if it is unvisited, make a recursive call and visit its adjacent vertices.
	    	if (dfsNum[to] == UNVISITED) {
		        dfs(to);
		      }
	    	//and if it is visited, that means we're done visiting each child vertex of the node
	    	//so we update dfs low value for the current vertex.
	    	//pick minimum from current dfs low and dfs low of to vertex,
		      if (visited[to]) {
		        dfsLow[curr] = Math.min(dfsLow[curr], dfsLow[to]);
		      }
	    }
	    
	    // now on recursive callback, if we're at the root node (start of SCC)
	    // then we'll empty the seen stack. that is all the nodes above that node
	   
	    if (dfsNum[curr] == dfsLow[curr]) {
		      for (int node = stack.pop(); ; node = stack.pop()) {
		        visited[node] = false;
		        //here we print the popped nodes for each iteration, 
		        System.out.print(node + " ");
		      //If two indexes have the same low value then they're in the same SCC. so we check if
		        //node is equals to curr then we break the loop.
		        if (node == curr) break;
		      }
		      sccCount++;
		      System.out.println();
		    }
	 
	  }

	  /* Example usage: */

	  public static void main(String[] arg) {
	    int n = 7;
	    LinkedList<Integer>[] graph = createGraph(n);
	 
	    addEdge(graph, 0, 1);
	    addEdge(graph, 1, 2);
	    addEdge(graph, 1, 4);
	    addEdge(graph, 1, 6);
	    addEdge(graph, 2, 3);
	    addEdge(graph, 3, 2);
	    addEdge(graph, 4, 5);
	    addEdge(graph, 5, 4);
	    addEdge(graph, 6, 4);
	    addEdge(graph, 6, 0);

	    TarjanScc sccSolver = new TarjanScc(graph);

	    System.out.println("Strongly Connected Components are: "); 
	    sccSolver.solve();
	    
	    System.out.println("Number of SCC: "+ sccSolver.sccCount()); 
	   
	  }

}
