
function getMST(graph, V){

    const selected = new Array(V).fill(false);

    selected[0] = true;

    //MST => edges = V-1
    let edges = 0;

    while( edges < V-1){

    let min = Number.MAX_SAFE_INTEGER;
    let x = 0, y = 0;

    for(let u = 0; u < V; u++){
        //the vertex that's already selected
        if(selected[u]){
            //adjacent vertex with min weight of the edge
            for(let v = 0; v < V; v++){
                if(!selected[v] && graph[u][v] != 0){
                    //it should be a min vertex
                    if(min > graph[u][v]){
                        min = graph[u][v];
                        x = u;
                        y = v;
                    }
                }
            }
        }
    }

    //select vertex
    console.log(x, "-", y, " : ", graph[x][y]);
    selected[y] = true;
    edges++;

    }
}

const graph = [[ 0, 3, 0, 0, 8 ],  
				[ 3, 0, 4, 2, 0 ],  
				[ 0, 4, 0, 10, 0 ],  
				[ 0, 2, 10, 11, 0 ],  
                [ 8, 0, 0, 11, 0 ]];
                
getMST(graph, graph.length);
