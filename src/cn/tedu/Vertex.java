/**
 * 
 */
package cn.tedu;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @ClassName Vertex
 * @Describe TODO
 * @author  wyx
 * @param <T>
 * @date 2017年10月31日 下午10:33:08
 */
public class Vertex<T> implements Serializable, VertexInterface<T>
{
    private T lable;//标识标点,用来标识不同类型的顶点.比如:int,string
    private List<T> edgeList;//该顶点到临接点的边,实际以LinkList存储
    private boolean visited;//标识顶点是否被访问过
    private VertexInterface<T> previousVertex;//该顶点的前驱节点
    private double cost;//顶点的权值 , 与边的权值不同

    public Vertex(T vertexlable)
    {
        lable = vertexlable;
        edgeList = (List<T>) new LinkedList<Edge>();
        visited = false;
        previousVertex = null;
        cost = 0;
    }

    public Iterator<VertexInterface<T>> getNeighborInterator()
    {
        return new NeighborIterator<T>();
    }

    public T getLable()
    {
        return lable;
    }

    private boolean hasPreviousVertex()//判断顶点是否有前驱顶点
    {
        return this.previousVertex != null;
    }

    public boolean isVisited()
    {
        return visited;
    }

    //获得前驱顶点
    public VertexInterface<T> getPreviousVertex()
    {
        return previousVertex;
    }

    public double getCost()
    {
        return cost;
    }

    public void setCost(double cost)
    {
        this.cost = cost;
    }

    /**
     * @ClassName NeighborIterator
     * @Task: 遍历该顶点邻接点的迭代器--为 getNeighborInterator()方法 提供迭代器
     * 由于顶点的邻接点以边的形式存储在java.util.List中,因此借助List的迭代器来实现
     * 由于顶点的邻接点由Edge类封装起来了--见Edge.java的定义的第一个属性
     * 因此，首先获得遍历Edge对象的迭代器,再根据获得的Edge对象解析出邻接点对象
     * @author  wyx
     * @date 2017年10月31日 下午11:07:36
     */
    private class NeighborIterator<T> implements Iterator<VertexInterface<T>>
    {
        Iterator<Edge> edgeIterator;

        private NeighborIterator()
        {
            edgeIterator = (Iterator<Edge>) edgeList.iterator();
        }

        @Override
        public boolean hasNext()
        {
            return edgeIterator.hasNext();
        }

        @Override
        public VertexInterface<T> next()
        {
            VertexInterface<T> nextNeighbor = null;
            if(edgeIterator.hasNext()){
                Edge nextToNeighbor = edgeIterator.next();//LinkedList中存储的是Edge
                edgeIterator = (Iterator<Edge>) nextToNeighbor.getEndVertex();//从Edge对象中取出顶点
            }
            else
                throw new NoSuchElementException();
            return nextNeighbor;
        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }
}


