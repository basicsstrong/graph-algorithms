
const INF = 9999;
const V = 4;

function floydWarshall(graph) {

    let dist = graph

    //setting each vertex as intermediate
    for (let k = 0; k < V; k++) {
        //each vertex as source
        for (let i = 0; i < V; i++) {
            //each vertex as destination for picked source
            for (let j = 0; j < V; j++)
                //updating shortest distance
                dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
        }
    }
    for (let i = 0; i < V; ++i)
        console.log(dist[i])
}

var graph = [[0, 3, INF, 2],
            [4, 0, 6, 7],
            [INF, 5, 0, INF],
            [INF, INF, 1, 0]
            ];

floydWarshall(graph);