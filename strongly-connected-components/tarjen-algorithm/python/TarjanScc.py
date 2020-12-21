from collections import defaultdict


class Graph:
    def __init__(self, vertices):
        self.V = vertices
        self.graph = defaultdict(list)
        self.num = 0

    def add_edge(self, frm, to):
        self.graph[frm].append(to)

    def find_scc(self):
        dfs_num = [-1] * self.V
        dfs_low = [-1] * self.V
        visited = [False] * self.V
        stack = []

        for i in range(self.V):
            if dfs_num[i] == -1:
                # perform dfs traversal starting from ith node
                self.dfs(i, dfs_num, dfs_low, visited, stack)

    def dfs(self, curr, dfs_num, dfs_low, visited, stack):
        # visiting current vertex
        dfs_num[curr] = self.num
        dfs_low[curr] = self.num
        self.num += 1
        stack.append(curr)
        visited[curr] = True

        # iterate through adjacent vertices of current vertex
        for to in self.graph[curr]:
            if dfs_num[to] == -1:
                self.dfs(to, dfs_num, dfs_low, visited, stack)
            # updating dfs_low value when each child vertex gets visited
            if visited[to]:
                dfs_low[curr] = min(dfs_low[curr], dfs_low[to])

        scc_node = -1
        if dfs_num[curr] == dfs_low[curr]:
            # remove nodes above the current node
            while scc_node != curr:
                scc_node = stack.pop()
                print(scc_node)
                visited[scc_node] = False
            print("")


graph = Graph(7)
graph.add_edge(0, 1)
graph.add_edge(1, 2)
graph.add_edge(1, 4)
graph.add_edge(1, 6)
graph.add_edge(2, 3)
graph.add_edge(3, 2)
graph.add_edge(4, 5)
graph.add_edge(5, 4)
graph.add_edge(6, 5)
graph.add_edge(6, 0)

print('Strongly connected componenets are- ')
graph.find_scc()