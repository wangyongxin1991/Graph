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
 * @date 2017��10��31�� ����10:33:08
 */
public class Vertex<T> implements Serializable, VertexInterface<T>
{
    public T label;//��ʶ���,������ʶ��ͬ���͵Ķ���.����:int,string
    public List<T> edgeList;//�ö��㵽�ٽӵ�ı�,ʵ����LinkList�洢
    public boolean visited;//��ʶ�����Ƿ񱻷��ʹ�
    public VertexInterface<T> previousVertex;//�ö����ǰ���ڵ�
    public double cost;//�����Ȩֵ , ��ߵ�Ȩֵ��ͬ

    public Vertex(T vertexlable)
    {
        label = vertexlable;
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


    //����ͼ��ĳ��������֮������·��ʱ���ڴ���ʼ������������У���Ҫ��¼�±�����ĳ������ʱ��ǰ�����㣬 
    //previousVertex ���Ծ������ó��ˡ���ˣ���Ҫ���жϺͻ�ȡ�����ǰ������ķ�����
    private boolean hasPreviousVertex()//�ж϶����Ƿ���ǰ������
    {
        return this.previousVertex != null;
    }

    //���ǰ������
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
     * @Task: �����ö����ڽӵ�ĵ�����--Ϊ getNeighborInterator()���� �ṩ������
     * ���ڶ�����ڽӵ��Աߵ���ʽ�洢��java.util.List��,��˽���List�ĵ�������ʵ��
     * ���ڶ�����ڽӵ���Edge���װ������--��Edge.java�Ķ���ĵ�һ������
     * ��ˣ����Ȼ�ñ���Edge����ĵ�����,�ٸ��ݻ�õ�Edge����������ڽӵ����
     * @author  wyx
     * @date 2017��10��31�� ����11:07:36
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
                Edge nextToNeighbor = edgeIterator.next();//LinkedList�д洢����Edge
                edgeIterator = (Iterator<Edge>) nextToNeighbor.getEndVertex();//��Edge������ȡ������
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


    /**
     * @ClassName WeightIterator
     * @Task ����һ�������ö�����ڽӱߵ�Ȩֵ�ĵ�����
     * Ȩֵ��Edge���� ,����Ȼ��һ������Edge����ĵ�����,ȡ��Eege���� , �ٻ��Ȩֵ
     * @author  wyx
     * @date 2017��11��3�� ����10:29:19
     */
    private class WeightIterator implements Iterator
    {
        Iterator<Edge> edgesIterator;
        private WeightIterator()
        {
            edgesIterator = (Iterator<Edge>) edgeList.iterator();
        }
        @Override
        public boolean hasNext()
        {
            return edgesIterator.hasNext();
        }

        @Override
        public Object next()
        {
            Double result;
            if (edgesIterator.hasNext())
            {
                Edge next = edgesIterator.next();
                result = next.getWeight();
            } else
                throw new NoSuchElementException();
            return result;
        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * @ClassName Edge
     * @Describe �߱�Ľ���,vertex ������ʾ���㣬�ö����Ǳߵ��յ㡣weight ��ʾ�ߵ�Ȩֵ��
     * �������Ǵ�Ȩ��������Ͳ���Ҫweight���ԣ���ô����ֱ�Ӷ���һ�������б� ����� �յ� �Ϳ��Ա�ʾ���ˡ�
     * ��Vertex����ͱ�ʾ���㣬�����Vertex�ڲ�����һ��List����յ㣬��ô��List�ټ���Vertex����ʾ�Ķ��㱾��
     * �Ϳ��Ա�ʾ������ڽӵĸ������ˣ���֮Ϊ��� �����ڽӱ�, ����Ϊ�˱�ʾ��Ȩ�ıߣ�
     * ��ˣ�������weight���ԣ�������Edge����װ�����������Ǵ�Ȩ�ı߻��ǲ���Ȩ�ı߶�������ͬһ��Edge������ʾ������Ȩ�ı߽�weight��ֵΪ0���ɡ�
     * 
     * @Task: ��������һ��������������ʾ��,��Ҫ�ǿ��ǵ���Ȩֵ�ı�
         *���Կ���,Edge���װ��һ�������һ��double���ͱ��� 
         *������Ҫ����Ȩֵ,���Բ���Ҫ��������һ��Edge������ʾ��,ֻ��Ҫһ�����涥����б���
     * @author  wyx
     * @param <T>
     * @date 2017��10��31�� ����10:24:31
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
    public boolean connect(VertexInterface<T> endVertex, double edgeWeight)
    {
        // ��"��"(�ߵ�ʵ���Ƕ���)���붥����ڽӱ�
        boolean result = false;
        if (!this.equals(endVertex))
        {//���㻥����ͬ
            Iterator<VertexInterface<T>> neighbors = this.getNeighborInterator();
            boolean duplicateEdge = false;
            while (!duplicateEdge && neighbors.hasNext())
            {//��֤������ظ��ı�
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (endVertex.equals(nextNeighbor))
                {
                    duplicateEdge = true;
                    break;
                }
            } //end while
            if (!duplicateEdge)
            {
                edgeList.add((T) new Edge(endVertex, edgeWeight));//���һ���±�
                result = true;
            } //end if
        } //end if
        return result;
    }

    @Override
    public boolean connect(VertexInterface<T> endVertex)
    {
        return connect(endVertex, 0);
    }

    @Override
    public T getLabel()
    {
        return label;
    }

    @Override
    public boolean isVisited()
    {
        return visited;
    }

    @Override
    public void visit()
    {
        visited = true;
    }

    @Override
    public void unVisit()
    {
        visited = false;
    }

    @Override
    public Iterator<Double> getWeightIterator()
    {
        return new WeightIterator();
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

    //�ж����������Ƿ���ͬ
    @Override
    public boolean equals(Object other)
    {
        boolean result;
        if ((other == null) || (getClass() != other.getClass()))
            result = false;
        else
        {
            Vertex<T> otherVertex = (Vertex<T>) other;
            result = label.equals(otherVertex.label);//�ڵ��Ƿ���ͬ���ջ����ɱ�ʶ �ڵ����͵����equals() ����
        }
        return result;
    }
}


