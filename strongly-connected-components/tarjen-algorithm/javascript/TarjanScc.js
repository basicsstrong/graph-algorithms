const UNVISITED = -1;
var n;
var num = 0;
var visited, dfsNum, dfsLow, stack, graph;

function solve() {
    dfsNum = new Array(n).fill(UNVISITED);
    dfsLow = new Array(n);
    visited = new Array(n);
    stack = [];
  
    for (let i = 0; i < n; i++) {
        if (dfsNum[i] == UNVISITED) {
            //perform dfs traversal starting from ith node
            dfs(i);
        }
    }
}

function dfs(curr) {
    // visiting current vertex
    dfsNum[curr] = dfsLow[curr] = num++;
    stack.push(curr);
    visited[curr] = true;

    //iterating through all child vertices of current vertex
    for (let i = 0; i < graph[curr].length; i++) {
        let to = graph[curr][i];
        if (dfsNum[to] == UNVISITED)
            dfs(to);
        //updating the dfsLow value when each child vertex get visited
        if (visited[to]) {
            dfsLow[curr] = Math.min(dfsLow[curr], dfsLow[to]);
        }
    }

    if (dfsNum[curr] == dfsLow[curr]) {
        let scc = '';
        //removing the nodes above current node
        for (let node = stack.pop(); ; node = stack.pop()) {
            visited[node] = false;
            //printing popped node
            scc += node;
            scc += ' '
            if (node == curr)
                break;
        }
        console.log(scc);
    }
}

function createGraph(n) {
    let graph = [];
    for (let i = 0; i < n; i++) {
        graph.push([]);
    }
    return graph;
}

function addEdge(from, to) {
    graph[from].push(to);
}

n = 7;
graph = createGraph(n);

addEdge(0, 1);
addEdge(1, 2);
addEdge(1, 4);
addEdge(1, 6);
addEdge(2, 3);
addEdge(3, 2);
addEdge(4, 5);
addEdge(5, 4);
addEdge(6, 4);
addEdge(6, 0);

console.log("Strongly connected components are : ");
solve();