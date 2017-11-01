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
    public T lable;//标识标点,用来标识不同类型的顶点.比如:int,string
    public List<T> edgeList;//该顶点到临接点的边,实际以LinkList存储
    public boolean visited;//标识顶点是否被访问过
    public VertexInterface<T> previousVertex;//该顶点的前驱节点
    public double cost;//顶点的权值 , 与边的权值不同

    public Vertex(T vertexlable)
    {
        lable = vertexlable;
        edgeList = (List<T>) new LinkedList<Edge>();
        visited = false;
        previousVertex = null;
        cost = 0;
    }

    @Override
    public Iterator<VertexInterface<T>> getNeighborInterator()
    {
        return new NeighborIterator<T>();
    }

    public T getLable()
    {
        return lable;
    }

    //在求图中某两个顶点之间的最短路径时，在从起始顶点遍历过程中，需要记录下遍历到某个顶点时的前驱顶点， 
    //previousVertex 属性就派上用场了。因此，需要有判断和获取顶点的前驱顶点的方法：
    private boolean hasPreviousVertex()//判断顶点是否有前驱顶点
    {
        return this.previousVertex != null;
    }

    @Override
    public boolean isVisited()
    {
        return visited;
    }

    //获得前驱顶点
    public VertexInterface<T> getPreviousVertex()
    {
        return previousVertex;
    }

    @Override
    public double getCost()
    {
        return cost;
    }

    @Override
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

        /** 生成一个遍历该顶点所有邻接边的权值的迭代器
           * 权值是Edge类的属性,因此先获得一个遍历Edge对象的迭代器,取得Edge对象,再获得权值
           * @param <Double> 权值的类型
           */
        public class WeightIterator<T> implements Iterator<T>
        {
            private Iterator<Edge> degesIterator;

            private WeightIterator()
            {
                degesIterator = (Iterator<Edge>) edgeList.iterator();
            }
            @Override
            public boolean hasNext()
            {
                return degesIterator.hasNext();
            }

            @Override
            public T next()
            {
                Double result;
                if (degesIterator.hasNext())
                {
                    Edge next = degesIterator.next();
                    result = next.getWeight();
                } else
                    throw new NoSuchElementException();
                return (T) result;//从迭代器中取得结果时,需要强制转换成Double
            }

            @Override
            public void remove()
            {
                throw new UnsupportedOperationException();
            }

        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * @ClassName Edge
     * @Describe 边表的建立,vertex 用来表示顶点，该顶点是边的终点。weight 表示边的权值。
     * 若不考虑带权的情况，就不需要weight属性，那么可以直接定义一个顶点列表 来存放 终点 就可以表示边了。
     * 而Vertex本身就表示顶点，如果在Vertex内部定义一个List存放终点，那么该List再加上Vertex所表示的顶点本身，
     * 就可以表示与起点邻接的各个点了（称之为这个 起点的邻接表）, 但是为了表示带权的边，
     * 因此，新增加weight属性，并用类Edge来封装，这样不管是带权的边还是不带权的边都可以用同一个Edge类来表示。不带权的边将weight赋值为0即可。
     * 
     * @Task: 这里用了一个单独的类来表示边,主要是考虑到带权值的边
         *可以看出,Edge类封装了一个顶点和一个double类型变量 
         *若不需要考虑权值,可以不需要单独创建一个Edge类来表示边,只需要一个保存顶点的列表即可
     * @author  wyx
     * @param <T>
     * @date 2017年10月31日 下午10:24:31
     */
    public class Edge<T> implements Serializable
    {

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

    @Override
    public T getLabel()
    {
        return null;
    }

    @Override
    public void visit()
    {
    }

    @Override
    public void unVisit()
    {
    }

    @Override
    public boolean connect(VertexInterface<T> endVertex, double edgeWeight)
    {
        return false;
    }

    @Override
    public boolean connect(VertexInterface<T> endVertex)
    {
        return false;
    }

    @Override
    public Iterator<Double> getWeightIterator()
    {
        return null;
    }

    @Override
    public boolean hasNeighbor()
    {
        return false;
    }

    @Override
    public VertexInterface<T> getUnvisitedNeighbor()
    {
        return null;
    }

    @Override
    public void setPredecessor(VertexInterface<T> predecessor)
    {
    }

    @Override
    public VertexInterface<T> getPredecessor()
    {
        return null;
    }

    @Override
    public boolean hasPredecessor()
    {
        return false;
    }
}


