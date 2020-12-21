def get_mst(graph, vertices):
    selected = [False] * vertices
    selected[0] = True

    # MST => edges = vertices - 1
    edges = 0

    while edges < vertices - 1:

        minimum = float('Inf')
        x = 0
        y = 0

        for u in range(vertices):
            if selected[u]:
                # adjacent vertices with min edge weight
                for v in range(vertices):
                    if not selected[v] and graph[u][v] != 0:
                        # minimum
                        if minimum > graph[u][v]:
                            minimum = graph[u][v]
                            x = u
                            y = v

        # select vertex
        print(x, '-', y, ' : ', graph[x][y])
        selected[y] = True
        edges += 1


graph = [[0, 3, 0, 0, 8],
         [3, 0, 4, 2, 0],
         [0, 4, 0, 10, 0],
         [0, 2, 10, 11, 0],
         [8, 0, 0, 11, 0]]

get_mst(graph, len(graph))
