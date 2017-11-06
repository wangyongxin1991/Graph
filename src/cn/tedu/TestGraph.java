/**
 * 
 */
package cn.tedu;

/**
 * @ClassName TestGraph
 * @Task TODO
 * @author  wyx
 * @date 2017年11月3日 下午11:10:42
 */
public class TestGraph
{

    /**
      * @Method main()
      * @Task   
      * @return void
      * @throws Exception
      */
    public static void main(String[] args)
    {
        GraphInterface<String> graph = new DirectedGraph<>();
        System.out.println("Graph is empty? " + graph.isEmpty());

        System.out.println("Adding vertexs...");
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        System.out.println("Number of graph's vertex = " + graph.getNumberOfVertices());//5

        graph.addEdge("A", "D");
        graph.addEdge("A", "C");
        graph.addEdge("A", "B");
        graph.addEdge("D", "C");
        graph.addEdge("C", "E");
        System.out.println("Number of graph's edge = " + graph.getNumberOfEdges());//5
        System.out.println(graph.hasEdge("A", "C"));

    }

}
