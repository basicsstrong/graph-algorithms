from queue import Queue
import sys


def get_max_flow(graph, source, sink):
    residual_graph = [e[:] for e in graph]
    augmenting_paths = []

    max_flow = 0

    while True:
        got_the_path, parent_mapping = get_augmenting_path(residual_graph, source, sink)

        if not got_the_path:
            break
        aug_path = []
        v = sink

        flow = sys.maxsize

        while not v == source:
            aug_path.append(v)
            u = parent_mapping[v]

            if flow > residual_graph[u][v]:
                flow = residual_graph[u][v]

            v = u

        aug_path.append(source)
        # reverse

        augmenting_paths.append(aug_path[::-1])
        max_flow += flow

        v = sink
        while not v == source:
            u = parent_mapping[v]
            residual_graph[u][v] -= flow
            residual_graph[v][u] += flow

            v = u

    print(augmenting_paths)
    return max_flow


def get_augmenting_path(residual_graph, source, sink):
    # BFS
    visited = set()
    queue = Queue()
    parent_mapping = {}

    queue.put(source)
    visited.add(source)

    got_the_path = False

    while not queue.empty():
        # poll
        u = queue.get()

        for v in range(len(residual_graph)):
            # 2 contraints
            if v not in visited and residual_graph[u][v] > 0:
                parent_mapping[v] = u
                visited.add(v)
                queue.put(v)

                # stop
                if v is sink:
                    got_the_path = True
                    break

    return got_the_path, parent_mapping


# ford fulkerson (BFS) : Edmonds Karp Algorithm
graph = [[0, 3, 2, 0, 0, 0],
         [0, 0, 0, 2, 0, 0],
         [0, 3, 0, 0, 3, 0],
         [0, 0, 1, 0, 0, 3],
         [0, 0, 0, 3, 0, 2],
         [0, 0, 0, 0, 0, 0]]

print(get_max_flow(graph, 0, 5))

