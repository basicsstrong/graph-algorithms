//ford fulkerson (BFS) : Edmonds Karp Algorithm
graph = [[0, 3, 2, 0, 0, 0],
        [0, 0, 0, 2, 0, 0],
        [0, 3, 0, 0, 3, 0],
        [0, 0, 1, 0, 0, 3],
        [0, 0, 0, 3, 0, 2], 
        [0, 0, 0, 0, 0, 0]];

console.log(getMaxFlow(graph, 0, 5));


function getMaxFlow(graph, source, sink){
    residualGraph = [];

    for(let i = 0; i < graph.length; i++){
        residualGraph.push(new Array(graph[0].length).fill(0));
        for(let j = 0; j < graph[0].length; j++){
            residualGraph[i][j] = graph[i][j];
        }
    }

    let parentMapping = {};
    let augmentingPaths = [];

    let maxFlow = 0;

    while(findAugmentingPath(residualGraph, parentMapping, source, sink)){
        //construct the augmenting path from parent Mapping

        let augPath = [];
        let flow = Number.MAX_SAFE_INTEGER;

        // t --> s 
        let v = sink;
        while( v != source){
            augPath.push(v);
            //its value
            let u = parentMapping[v];
            // bottleneck cap/ min flow in the path
            if(residualGraph[u][v] < flow){
                flow = residualGraph[u][v];
            }

            //jump to u
            v = u;
        }
        augPath.push(source);
        //reverse
        augPath.reverse();
        augmentingPaths.push(augPath);

        maxFlow += flow;

        //update the flow in the selected path
        v = sink;
        while(v != source){
            let u = parentMapping[v];
            //residual capacities
            residualGraph[u][v] -= flow;  // u-> v
            //shrinking capacities
            residualGraph[v][u] += flow;  // v -> u

            v = u;
        }
    }

    console.log(augmentingPaths);
    return maxFlow;
}


function findAugmentingPath(residualGraph, parentMapping, source, sink){

    //BFS
    let queue = [];
    let visitedSet = new Set();

    queue.push(source);
    visitedSet.add(source);

    let gotThePath = false;

    while( queue.length != 0){
        //poll
        let u = queue.shift();

        for(let v = 0; v < residualGraph.length; v++){
            // 2 constraints
            if( !visitedSet.has(v) && residualGraph[u][v] > 0){

                parentMapping[v] = u; //we came to v through u
                visitedSet.add(v);
                queue.push(v);

                // stop .. sink
                if( v == sink){
                    gotThePath = true;
                    break;
                }
            }
        }
    }
    return gotThePath;

}







