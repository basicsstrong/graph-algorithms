const graph = [[0, 4, 0, 0, 1],
            [0, 0, 0, 0, 0],
            [0, 7, 0, -2, 0],
            [0, 1, 0, 0, 0],
            [0, 0, 0, -5, 0]]

allPairShortestPath(graph)


function allPairShortestPath(graph){
    const edges = []

    for(let u = 0; u < graph.length; u++){
        for(let v = 0; v < graph.length; v++){
            if( graph[u][v] != 0){
                edges.push([u, v, graph[u][v]])
            }
        }
    }

    const distance = bellmanFord(edges, graph.length)

    // new positive weights to the edges
    const newGraph = []

    for(let i = 0; i < graph.length; i++){
        newGraph.push(new Array(graph.length).fill(0))
    }

    for(let u = 0; u < graph.length; u++){
        for(let v = 0; v < graph.length; v++){
            if( graph[u][v] != 0){
                newGraph[u][v] = distance[u] + graph[u][v] - distance[v]
            }
        }
    }

    console.log("New Graph : ", newGraph)

    //Run dijkstra for every vertex as source
    for(let src = 0; src < graph.length; src++){
        console.log("Shortest distances from vertex "+ src)
        dijkstra(graph, newGraph, src)
    }
}

function dijkstra(oldGraph, newGraph, src){

    const v = newGraph.length
    const visited = new Array(v).fill(false)
    const distance = new Array(v).fill(Number.MAX_SAFE_INTEGER)

    distance[src] = 0

    for(let i = 0; i < v -1 ; i++){
        minVertex = findMinVertex(distance, visited)
        visited[minVertex] = true

        for(let j = 0; j < v; j++){
            if(oldGraph[minVertex][j] != 0 && !visited[j]){
                let newDist = distance[minVertex] + newGraph[minVertex][j]

                if(newDist < distance[j]){
                    distance[j] = newDist
                }
            }
        }
    }

    console.log(distance)
}

function findMinVertex(distance, visited){
    let minVertex = -1

    for(let i = 0; i < distance.length; i++){
        if(!visited[i] && (minVertex == -1 || distance[i] < distance[minVertex]))
            minVertex = i;
    }

    return minVertex
}

function bellmanFord(edges, V){
    //arbitrary source
    const dist = new Array(V+1).fill(Number.MAX_SAFE_INTEGER)

    dist[V] = 0

    //add edges from source to all other vertices
    for(let i = 0; i < V; i++){
        edges.push([V, i, 0])
    }

    for(let i = 0; i < V; i++){
    edges.forEach(
        li => {
            let u = li[0]
            let v = li[1]
            let w = li[2]

            if( dist[u] != Number.MAX_SAFE_INTEGER && dist[u] + w < dist[v]){
                dist[v] = dist[u] + w
             }
            }
        )   
    }

    //discard the arbitrary source
    return dist.splice(0, V)
}

