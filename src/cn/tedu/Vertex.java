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
    private T lable;//��ʶ���,������ʶ��ͬ���͵Ķ���.����:int,string
    private List<T> edgeList;//�ö��㵽�ٽӵ�ı�,ʵ����LinkList�洢
    private boolean visited;//��ʶ�����Ƿ񱻷��ʹ�
    private VertexInterface<T> previousVertex;//�ö����ǰ���ڵ�
    private double cost;//�����Ȩֵ , ��ߵ�Ȩֵ��ͬ

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

    private boolean hasPreviousVertex()//�ж϶����Ƿ���ǰ������
    {
        return this.previousVertex != null;
    }

    public boolean isVisited()
    {
        return visited;
    }

    //���ǰ������
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
}


