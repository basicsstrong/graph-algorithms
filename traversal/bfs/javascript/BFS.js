class Graph{

    constructor(noOfVertices){
        this.V = noOfVertices
        this.edges = []
        this.visited = new Array(this.V).fill(false)
        this.result = []

        for(let i=0; i<this.V; i++)
            this.edges.push([]);
    }

    addEdge(src, dest){
        this.edges[src].push(dest);
    }

    bfsTraversal(s){
        //adding first node to queue
        let queue = [s]
        this.visited[s] = true

        while(queue.length!=0){
            let curr = queue.shift();

            for(let i=0; i<this.edges[curr].length; i++){
                let next = this.edges[curr][i];
                if(!this.visited[next]){
                    //adding adjacent nodes for current node
                    queue.push(next);
                    this.visited[next] = true;
                }
            }
            this.result.push(curr);
        }
        console.log(this.result);
    }

}

var graph = new Graph(11)

graph.addEdge(0, 1)
graph.addEdge(1, 4)
graph.addEdge(0, 2)
graph.addEdge(0, 3)
graph.addEdge(1, 5)
graph.addEdge(5, 7)
graph.addEdge(7, 9)
graph.addEdge(2, 6)
graph.addEdge(5, 8)
graph.addEdge(2, 5)
graph.addEdge(7, 10)
graph.addEdge(4, 7)
graph.addEdge(6, 8)

graph.bfsTraversal(0);
