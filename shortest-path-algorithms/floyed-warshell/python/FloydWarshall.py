V = 4
INF = 99999


def floyd_warshall(graph):
    dist = graph

    # each vertex as intermediate
    for k in range(V):
        # each vertex as source
        for i in range(V):
            # each vertex as destination for picked source
            for j in range(V):
                # update shortest distance
                dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j])

    # print
    for i in range(V):
        for j in range(V):
            if dist[i][j] == INF:
                print("INF", end=" ")
            else:
                print(dist[i][j], end=" ")
            if j == V-1:
                print()


graph = [[0, 3, INF, 2],
         [4, 0, 6, 7],
         [INF, 5, 0, INF],
         [INF, INF, 1, 0]
         ]

floyd_warshall(graph)