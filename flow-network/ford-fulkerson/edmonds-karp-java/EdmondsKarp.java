package com.basicsstrong.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

//ford fulkerson : BFS
public class EdmondsKarp {
	public static void main(String[] args) {

		int[][] graph = { { 0, 3, 2, 0, 0, 0 }, 
				{ 0, 0, 0, 2, 0, 0 }, 
				{ 0, 3, 0, 0, 3, 0 }, 
				{ 0, 0, 1, 0, 0, 3 },
				{ 0, 0, 0, 3, 0, 2 },
				{ 0, 0, 0, 0, 0, 0 } };

		System.out.println("The max possible flow is "+getMaxFlow(graph, 0, graph.length - 1));
	}

	private static int getMaxFlow(int[][] graph, int source, int sink) {

		int[][] residualGraph = new int[graph.length][graph[0].length];

		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph[0].length; j++) {
				residualGraph[i][j] = graph[i][j];
			}
		}

		Map<Integer, Integer> parentMapping = new HashMap<Integer, Integer>();
		List<List<Integer>> augmentingPaths = new ArrayList<List<Integer>>();

		int maxFlow = 0;
		while (getAugmentingPath(residualGraph, parentMapping, source, sink)) {

			List<Integer> augPath = new ArrayList<Integer>();
			int flow = Integer.MAX_VALUE;

			// sink t ---> s
			int v = sink;
			while (v != source) {
				augPath.add(v);
				int u = parentMapping.get(v);

				if (flow > residualGraph[u][v]) {
					flow = residualGraph[u][v];
				}

				v = u;
			}

			augPath.add(source);
			// reverse
			Collections.reverse(augPath);
			augmentingPaths.add(augPath);

			maxFlow += flow;

			v = sink;

			while (v != source) {
				int u = parentMapping.get(v);
				// v -> u //sink -> source
				residualGraph[u][v] -= flow; // edges having residual capacity
				residualGraph[v][u] += flow; // edges having shrinking capacities

				v = u;
			}

		}
		printAugmentingPaths(augmentingPaths);
		return maxFlow;

	}

	private static void printAugmentingPaths(List<List<Integer>> augmentingPaths) {

		augmentingPaths.forEach(path -> {
			path.forEach(e -> System.out.print(e + " "));
			System.out.println();
		});

	}

	private static boolean getAugmentingPath(int[][] residualGraph, Map<Integer, Integer> parentMapping, int source,
			int sink) {

		Set<Integer> visited = new HashSet<Integer>();
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(source);
		visited.add(source);

		boolean gotThePath = false;

		while (!queue.isEmpty()) {
			// poll
			int u = queue.poll();

			for (int v = 0; v < residualGraph.length; v++) {
				// 2 constraints
				if (!visited.contains(v) && residualGraph[u][v] > 0) {
					parentMapping.put(v, u);
					visited.add(v);
					queue.add(v);

					if (v == sink) {
						gotThePath = true;
						break;
					}
				}
			}
		}
		return gotThePath;
	}
}
