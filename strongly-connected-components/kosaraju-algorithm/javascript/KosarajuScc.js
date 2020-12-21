var n;
var visited, stack;

function createGraph(n) {
    g = [];
    for (let i = 0; i < n; i++) {
        g.push([]);
    }
    return g;
}

function addEdge(g, from, to) {
    g[from].push(to);
}

function find_scc() {
    stack = []
    visited = new Array(n).fill(false)

    //step 1 - do dfs and fill stack
    for (let i = 0; i < n; i++) {
        if (!visited[i])
            dfs(i);
    }

    //step 2 - reverse the graph
    revGraph = reverse();

    visited.fill(false)

    //step 3 - second dfs
    while (stack.length != 0) {
        let top = stack.pop();
        scc = ''
        // dfs traversal starting with top vertex  
        if (!visited[top]) {
            scc = dfs2(revGraph, top);
            console.log(scc);
        }
    }
}

function dfs(curr) {
    visited[curr] = true;

    //recur for all adjacent vertices to current vertex
    for (let i = 0; i < graph[curr].length; i++) {
        let to = graph[curr][i];
        if (!visited[to])
            dfs(to);
    }

    //push vertex to stack
    stack.push(curr);
}

function dfs2(revGraph, curr) {

    visited[curr] = true;
    scc += curr
    scc += ' '
    //look for adjacent vertices
    for (let i = 0; i < revGraph[curr].length; i++) {
        let next = revGraph[curr][i];
        if (!visited[next])
            dfs2(revGraph, next);
    }
    return scc;
}

function reverse() {
    rev = createGraph(n);
    for (let u = 0; u < n; u++) {
        for (let i = 0; i < graph[u].length; i++) {
            let v = graph[u][i];
            addEdge(rev, v, u);
        }
    }
    return rev;
}

n = 8;
graph = createGraph(n);

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

find_scc();