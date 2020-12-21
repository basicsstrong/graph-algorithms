
def allPairShortestPath(graph):
    edges = []

    for u in range(len(graph)):
        for v in range(len(graph)):
            if graph[u][v] != 0:
                edges.append([u, v, graph[u][v]])

    distance = bellman_ford(edges, len(graph))

    # new positive edge weights
    new_graph = [[0 for x in range(len(graph))] for y in range(len(graph))]

    for u in range(len(graph)):
        for v in range(len(graph)):
            if graph[u][v] != 0:
                new_graph[u][v] = distance[u] + graph[u][v] - distance[v]

    print('New graph : ' + str(new_graph))

    # run dijkstra for every vertex as source
    for src in range(len(graph)):
        print('shortest distance from vertex : ' , src)
        dijkstra(graph, new_graph, src)


def dijkstra(old_graph, new_graph, src):
    v = len(new_graph)
    visited = [False for i in range(v)]
    distance = [float('Inf')] * v

    distance[src] = 0

    for i in range(v - 1):
        min_vertex = find_min_vertex(distance, visited)
        visited[min_vertex] = True

        for j in range(v):
            if old_graph[min_vertex][j] != 0 and not visited[j]:
                new_dist = distance[min_vertex] + new_graph[min_vertex][j]
                if new_dist < distance[j]:
                    distance[j] = new_dist

    for i in range(v):
        print(i, ' ', distance[i])


def find_min_vertex(distance, visited):
    min_vertex = -1

    for i in range(len(distance)):
        if (min_vertex == -1 or distance[min_vertex] > distance[i]) and not visited[i]:
            min_vertex = i

    return min_vertex


def bellman_ford(edges, vertices):
    # arbitrary source
    dist = [float('Inf')] * (vertices + 1)

    dist[vertices] = 0

    # added edges to all the other vertices from source
    for i in range(vertices):
        edges.append([vertices, i, 0])

        # vertices -1
    for i in range(vertices):
        for (u, v, w) in edges:
            if dist[u] != float('Inf') and (dist[u] + w < dist[v]):
                dist[v] = dist[u] + w

    # discard the arbitrary source
    return dist[0: vertices]


graph = [[0, 4, 0, 0, 1],
         [0, 0, 0, 0, 0],
         [0, 7, 0, -2, 0],
         [0, 1, 0, 0, 0],
         [0, 0, 0, -5, 0]]

# Johnson's algo
allPairShortestPath(graph)
