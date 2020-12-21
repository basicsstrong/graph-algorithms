from collections import defaultdict


class Graph:

    def __init__(self, vertices):
        self.V = vertices
        self.graph = defaultdict(list)

    def add_edge(self, frm, to):
        self.graph[frm].append(to)

    def find_scc(self):
        stack = []
        visited = [False] * self.V

        # step 1 - perform dfs and fill stack
        for i in range(self.V):
            if not visited[i]:
                self.dfs(i, visited, stack)

        # step 2 - reverse the graph
        rev = self.reverse()

        visited = [False] * self.V
        # step 3 - perform dfs in order defined by stack
        while stack:
            i = stack.pop()

            if not visited[i]:
                rev.dfs2(i, visited)
                print("")

    def dfs2(self, curr, visited):
        visited[curr] = True
        print(curr, end=" ")

        # look for adjacent vertices
        for i in self.graph[curr]:
            if not visited[i]:
                self.dfs2(i, visited)

    def reverse(self):
        g = Graph(self.V)

        for u in self.graph:
            for v in self.graph[u]:
                g.add_edge(v, u)
        return g

    def dfs(self, curr, visited, stack):
        visited[curr] = True

        # recur for adjacent vertices
        for i in self.graph[curr]:
            if not visited[i]:
                self.dfs(i, visited, stack)

        stack.append(curr)


graph = Graph(8)

graph.add_edge(0, 1)
graph.add_edge(1, 2)
graph.add_edge(2, 0)
graph.add_edge(2, 3)
graph.add_edge(3, 4)
graph.add_edge(4, 7)
graph.add_edge(4, 5)
graph.add_edge(6, 4)
graph.add_edge(5, 6)
graph.add_edge(6, 7)

graph.find_scc()
