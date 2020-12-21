package com.basicsstrong.dsa.graph;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class KosarajuScc {

	private int n;
	private boolean[] visited;
	private Stack<Integer> stack;
	private LinkedList<Integer>[] graph;

	public KosarajuScc(LinkedList<Integer>[] graph) {
		if (graph == null)
			throw new IllegalArgumentException("Graph cannot be null.");
		this.graph = graph;
		this.n = graph.length;
	}

	public static LinkedList<Integer>[] createGraph(int n) {
		LinkedList<Integer>[] graph = new LinkedList[n];
		for (int i = 0; i < n; i++)
			graph[i] = new LinkedList<Integer>();
		return graph;
	}

	// Adds a directed edge from node 'from' to node 'to'
	public static void addEdge(LinkedList<Integer>[] graph, int from, int to) {
		graph[from].add(to);
	}

	void dfs(int curr, boolean visited[]) {
		// Mark the current node as visited
		visited[curr] = true;

		// Recur for all the vertices adjacent to this vertex
		Iterator<Integer> i = graph[curr].iterator();
		while (i.hasNext()) {
			int to = i.next();
			if (!visited[to])
				dfs(to, visited);
		}

		// by now, all vertices reachable from v must be processed,
		// push v to Stack
		stack.push(curr);
	}

	LinkedList<Integer>[] reverse() {
		LinkedList<Integer>[] rev = createGraph(n);
		for (int u = 0; u < n; u++) {
			// Recur for all the vertices adjacent to this vertex
			Iterator<Integer> i = graph[u].listIterator();
			while (i.hasNext()) {
				Integer v = i.next();
				addEdge(rev, v, u);
			}
		}
		return rev;
	}

	void dfs2(LinkedList<Integer>[] graph, int curr, boolean visited[]) {
		// Mark the current node as visited and print it
		visited[curr] = true;
		System.out.print(curr + " ");

		// Recur for all the vertices adjacent to this vertex
		Iterator<Integer> i = graph[curr].iterator();
		while (i.hasNext()) {
			int next = i.next();
			if (!visited[next])
				dfs2(graph, next, visited);
		}
	}

	public void solve() {
		stack = new Stack<>();

		// Mark all the vertices as not visited (For first DFS)
		visited = new boolean[n];
		Arrays.fill(visited, false);

		//step 1. dfs and fill vertex in stack
		for (int i = 0; i < n; i++)
			if (visited[i] == false)
				dfs(i, visited);

		// step 2. Create a reversed graph
		LinkedList<Integer>[] revGraph = reverse();

		// Mark all the vertices as not visited (For second DFS)
		Arrays.fill(visited, false);

		// step 3. start dfs starting from top vertex in stack
		// process all vertices in order defined by Stack
		while (!stack.empty()) {
			// Pop a vertex from stack
			int curr = (int) stack.pop();

			// find connected vertices of popped vertex (second traversal) and print
			if (!visited[curr]) {
				dfs2(revGraph, curr, visited);
				System.out.println();
			}
		}
	}

	public static void main(String[] args) {
		int n = 8;
		LinkedList<Integer>[] graph = createGraph(n);

		addEdge(graph, 0, 1);
		addEdge(graph, 1, 2);
		addEdge(graph, 2, 0);
		addEdge(graph, 2, 3);
		addEdge(graph, 3, 4);
		addEdge(graph, 4, 7);
		addEdge(graph, 4, 5);
		addEdge(graph, 6, 4);
		addEdge(graph, 5, 6);
		addEdge(graph, 6, 7);

		System.out.println("Following are strongly connected components " + "in given graph ");
		KosarajuScc solver = new KosarajuScc(graph);
		solver.solve();

	}

}
