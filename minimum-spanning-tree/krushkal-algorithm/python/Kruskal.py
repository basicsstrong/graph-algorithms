class Graph:

    def __init__(self, vertices):
        self.V = vertices
        self.graph = []

    def add_edge(self, u, v, w):
        self.graph.append([u, v, w])

    # (uses path compression technique)
    def find(self, parent, ele):
        if ele != parent[ele]:
            parent[ele] = self.find(parent, parent[ele])
        return parent[ele]

    # (uses union by rank)
    def union(self, parent, rank, x, y):
        if rank[x] < rank[y]:
            parent[x] = y
        elif rank[x] > rank[y]:
            parent[y] = x
        else:
            parent[y] = x
            rank[x] += 1

    def kruskal(self):
        mst = []

        # Step 1: Sort all the edges in non-decreasing order of their weight
        self.graph = sorted(self.graph,
                            key=lambda item: item[2])

        parent = []
        rank = []

        # Create V subsets with single elements
        for node in range(self.V):
            parent.append(node)
            rank.append(0)

        i = 0
        e = 0

        # Number of edges to be taken is equal to V-1
        while e < self.V - 1:

            # Step 2: Pick the smallest edge and increment the index for next iteration
            u, v, w = self.graph[i]
            i = i + 1
            x = self.find(parent, u)
            y = self.find(parent, v)

            # include this edge if it does't create cycle
            if x != y:
                e = e + 1
                mst.append([u, v, w])
                self.union(parent, rank, x, y)

        min_cost = 0
        print
        "Edges in the constructed MST"
        for u, v, w in mst:
            min_cost += w
            print("%d -- %d : %d" % (u, v, w))
        print("Minimum Spanning Tree", min_cost)


# Driver code
graph = Graph(8)

graph.add_edge(2, 6, 2)
graph.add_edge(2, 7, 9)
graph.add_edge(4, 2, 1)
graph.add_edge(4, 3, 8)
graph.add_edge(0, 1, 8)
graph.add_edge(3, 7, 7)
graph.add_edge(0, 5, 4)
graph.add_edge(4, 0, 2)
graph.add_edge(2, 3, 4)
graph.add_edge(0, 3, 6)
graph.add_edge(2, 1, 10)
graph.add_edge(3, 5, 8)
graph.add_edge(6, 1, 7)


graph.kruskal()