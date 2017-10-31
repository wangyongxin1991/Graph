/**
 * 
 */
package cn.tedu;

import java.io.Serializable;

/**
 * @ClassName Edge
 * @Describe 边表的建立,vertex 用来表示顶点，该顶点是边的终点。weight 表示边的权值。
 * 若不考虑带权的情况，就不需要weight属性，那么可以直接定义一个顶点列表 来存放 终点 就可以表示边了。
 * 而Vertex本身就表示顶点，如果在Vertex内部定义一个List存放终点，那么该List再加上Vertex所表示的顶点本身，
 * 就可以表示与起点邻接的各个点了（称之为这个 起点的邻接表）, 但是为了表示带权的边，
 * 因此，新增加weight属性，并用类Edge来封装，这样不管是带权的边还是不带权的边都可以用同一个Edge类来表示。不带权的边将weight赋值为0即可。
 * 
 *  *Task: 这里用了一个单独的类来表示边,主要是考虑到带权值的边
     *可以看出,Edge类封装了一个顶点和一个double类型变量 
     *若不需要考虑权值,可以不需要单独创建一个Edge类来表示边,只需要一个保存顶点的列表即可
 * @author  wyx
 * @param <T>
 * @date 2017年10月31日 下午10:24:31
 */
public class Edge<T> implements Serializable
{
    /**
     * 
     */
    private VertexInterface<T> vertex;
    private double weight;

    protected Edge(VertexInterface<T> endVertex, double edgeWeight)
    {
        vertex = endVertex;
        weight = edgeWeight;
    }

    public VertexInterface<T> getEndVertex()
    {
        return vertex;
    }

    public void setVertex(VertexInterface<T> vertex)
    {
        this.vertex = vertex;
    }

    public double getWeight()
    {
        return weight;
    }

    public void setWeight(double weight)
    {
        this.weight = weight;
    }

}
