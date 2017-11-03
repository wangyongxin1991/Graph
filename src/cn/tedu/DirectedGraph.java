/**
 * 
 */
package cn.tedu;

/**
 * @ClassName DirectedGraph
 * @Task TODO
 * @author  wyx
 * @date 2017��11��3�� ����11:12:07
 */
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class DirectedGraph<T> implements GraphInterface<T>, java.io.Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Map<T, VertexInterface<T>> vertices;//map ������������ͼ�е����ж���.T �Ƕ����ʶ,VertexInterfaceΪ�������
    private int edgeCount;//��¼ͼ�� �ߵ�����

    public DirectedGraph()
    {
        vertices = new LinkedHashMap<>();//������Ĳ���˳�򱣴涥��,�����Ҫ,��Ϊ���Ӱ�쵽����ͼ�ı����㷨����ȷ��
    }

    @Override
    public void addVertex(T vertexLabel)
    {
        //��������ͬʱ,�²���Ķ��㽫����ԭ����,������LinkedHashMap��put����������
        //ÿ���һ������,�ᴴ��һ��LinkedList�б�,���洢�ö����Ӧ���ڽӵ�,����˵����ö���������ı�
        vertices.put(vertexLabel, new Vertex(vertexLabel));//new Vertex ����,�ᴴ��һ��LinkedList,��LinkedList������ʾ�ö�����ڽӱ�
    }

    @Override
    public boolean addEdge(T begin, T end, double edgeWeight)
    {
        boolean result = false;
        VertexInterface<T> beginVertex = vertices.get(begin);//��ñ�ʾ�ߵ���ʼ����
        VertexInterface<T> endVertex = vertices.get(end);//��ñ�ʾ �ߵ��յ�

        if (beginVertex != null && endVertex != null)
            result = beginVertex.connect(endVertex, edgeWeight);//��ʼ�����յ�����,����һ����
        if (result)
            edgeCount++;
        return result;//������ظ���ʱ�᷵�� false
    }

    @Override
    public boolean addEdge(T begin, T end)
    {
        return addEdge(begin, end, 0);
    }

    @Override
    public boolean hasEdge(T begin, T end)
    {
        boolean found = false;
        VertexInterface<T> beginVertex = vertices.get(begin);
        VertexInterface<T> endVertex = vertices.get(end);
        if (beginVertex == null || endVertex == null || beginVertex.hasNeighbor() == false)
            return found;
        Iterator<VertexInterface<T>> neighbors = beginVertex.getNeighborInterator();
        while (!found && neighbors.hasNext())
        {//����ʼ������ڽӱ��в��� ��������endVertex
            VertexInterface<T> neighbor = neighbors.next();
            if (endVertex.equals(neighbor))
                found = true;//���ҵ���,��������endVertex. ˵��beginVertex��endVertex֮�����бߵ�
        } //end while
        return found;
    }

    @Override
    public boolean isEmpty()
    {
        return vertices.isEmpty();
    }

    @Override
    public int getNumberOfVertices()
    {
        return vertices.size();
    }

    @Override
    public int getNumberOfEdges()
    {
        return edgeCount;
    }

    @Override
    public void clear()
    {
        vertices.clear();
        edgeCount = 0;
    }

    @Override
    public Queue<T> getDepthFirstTraversal(T origin)
    {
        resetVertices();//�Ƚ����еĶ����ʼ��
        LinkedList<VertexInterface<T>> vertexStack = new LinkedList<>();//����DFS�ݹ����
        Queue<T> traversalOrder = new LinkedList<>();//����DFS����˳��

        VertexInterface<T> originVertex = vertices.get(origin);//������ʼ����ı�ʶ�����ʼ����
        originVertex.visit();//������ʼ����,��ʼ����ĳ��Ȳ���Ϊ0(ֻ���Ƕ���һ���������ͨͼ),��Ϊ0,����û���ڽӵ���
        vertexStack.push(originVertex);//�����������ջ˳�����DFS�ı���˳��
        traversalOrder.offer(originVertex.getLabel());//ÿ��һ��������ջʱ,�ͽ��������,�Ӷ����б�������������˳��

        while (!vertexStack.isEmpty())
        {
            VertexInterface<T> topVertex = vertexStack.peek();
            //�ҵ��ö����һ��δ�����ʵ��ڽӵ�,�Ӹ��ڽӵ������ȥ�����ڽӵ���ڽӵ�,Ҳ�����������еı�--���Ӷ�O(E)
            VertexInterface<T> nextNeighbor = topVertex.getUnvisitedNeighbor();
            if (nextNeighbor != null)
            {
                nextNeighbor.visit();
                //�����õ���if,������push�ڽӵ��,��һ��whileѭ��pop���Ǹ��ڽӵ�,Ȼ���ֻ�������ڽӵ�,---DFS
                vertexStack.push(nextNeighbor);
                traversalOrder.offer(nextNeighbor.getLabel());
            } else
                vertexStack.pop();//��ĳ����������ڽӵ㶼��������ʱ,ֱ�ӽ��ö���pop,������һ��while pop ʱ�ͻ��ݵ�ǰһ������
        } //end while
        return traversalOrder;
    }

    @Override
    public Queue<T> getBreadthFirstTraversal(T origin)
    {//origin ��ʶ�����ĳ�ʼ����
        resetVertices();//������ı�Ҫ�������ʼ��,���Ӷ�ΪO(V)
        Queue<VertexInterface<T>> vertexQueue = new LinkedList<>();//������������������Ķ���,���Ǹ���������,�г����в���
        Queue<T> traversalOrder = new LinkedList<>();//������������������� �����ʶ--����ͼ�ı���˳��ͱ���������,�޳��Ӳ���
        VertexInterface<T> originVertex = vertices.get(origin);//���ݶ����ʶ��ó�ʼ��������
        originVertex.visit();//���ʸö���
        traversalOrder.offer(originVertex.getLabel());
        vertexQueue.offer(originVertex);

        while (!vertexQueue.isEmpty())
        {
            VertexInterface<T> frontVertex = vertexQueue.poll();//������,poll()�ڶ���Ϊ��ʱ����null
            Iterator<VertexInterface<T>> neighbors = frontVertex.getNeighborInterator();
            while (neighbors.hasNext())//���� ÿ�����㶼�����������ڽӱ�,�����������еı�,���Ӷ�ΪO(E)
            {
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (!nextNeighbor.isVisited())
                {
                    nextNeighbor.visit();//������ȱ���δ���ʵĶ���
                    traversalOrder.offer(nextNeighbor.getLabel());
                    vertexQueue.offer(nextNeighbor);//���ö�����ڽӵ������
                }
            } //end inner while
        } //end outer while
        return traversalOrder;
    }

    @Override
    public Stack<T> getTopologicalSort()
    {
        /**
         *����ڡ��㷨���ۡ��е��������������DFS���Ӷ�ΪO(V+E),���㷨��ʱ�临�ӶȽϴ�
         *��Ϊ�㷨�����н��ܵ�ͼ�����ݽṹ��˴�ʵ�ֵ�ͼ�����ݽṹ��ͬ
         *���㷨���ʱ�临�Ӷ�ΪO(V*(V+E))==V * max{V,E}
        */
        resetVertices();//�Ƚ����еĶ����ʼ��

        Stack<T> vertexStack = new Stack<>();//����ѷ��ʵĶ����ջ,��ջ����һ����������
        int numberOfVertices = vertices.size();//���ͼ�ж���ĸ���

        for (int counter = 1; counter <= numberOfVertices; counter++)
        {
            VertexInterface<T> nextVertex = getNextTopologyOrder();//���һ��δ�����ʵ��ҳ���Ϊ0�Ķ���
            if (nextVertex != null)
            {
                nextVertex.visit();
                vertexStack.push(nextVertex.getLabel());//������ɺ�,��ջ�Ϳ��Ի��ͼ��һ����������
            }
        }
        return vertexStack;
    }

    private VertexInterface<T> getNextTopologyOrder()
    {//�����¸��Ӷ�ΪO(V+E)
        VertexInterface<T> nextVertex = null;
        Iterator<VertexInterface<T>> iterator = vertices.values().iterator();//���ͼ�Ķ���ĵ�����
        boolean found = false;
        while (!found && iterator.hasNext())
        {
            nextVertex = iterator.next();
            //Ѱ�ҳ���Ϊ0��δ�����ʵĶ���
            if (nextVertex.isVisited() == false && nextVertex.getUnvisitedNeighbor() == null)
                found = true;
        }
        return nextVertex;
    }

    @Override
    public int getShortestPath(T begin, T end, Stack<T> path)
    {
        resetVertices();
        boolean done = false;//����������������Ƿ����
        Queue<VertexInterface<T>> vertexQueue = new LinkedList<>();
        VertexInterface<T> beginVertex = vertices.get(begin);
        VertexInterface<T> endVertex = vertices.get(end);

        beginVertex.visit();
        vertexQueue.offer(beginVertex);
        //Assertion: resetVertices() �Ѿ��� beginVertex ִ���� setCost(0)

        while (!done && !vertexQueue.isEmpty())
        {
            VertexInterface<T> frontVertex = vertexQueue.poll();
            Iterator<VertexInterface<T>> neighbors = frontVertex.getNeighborInterator();
            while (!done && neighbors.hasNext())
            {
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (!nextNeighbor.isVisited())
                {
                    nextNeighbor.visit();
                    nextNeighbor.setPredecessor(frontVertex);
                    nextNeighbor.setCost(frontVertex.getCost() + 1);
                    vertexQueue.offer(nextNeighbor);
                } //end if

                if (nextNeighbor.equals(endVertex))
                    done = true;
            } //end inner while
        } //end outer while. and traverse over

        int pathLength = (int) endVertex.getCost();
        path.push(endVertex.getLabel());

        VertexInterface<T> vertex = endVertex;
        while (vertex.hasPredecessor())
        {
            vertex = vertex.getPredecessor();
            path.push(vertex.getLabel());
        }
        return pathLength;
    }

    @Override
    public double getCheapestPath(T begin, T end, Stack<T> path)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    //���ö���ĳ�ʼ״̬,ʱ�临�Ӷ�ΪO(V)
    protected void resetVertices()
    {
        Iterator<VertexInterface<T>> vertexIterator = vertices.values().iterator();
        while (vertexIterator.hasNext())
        {
            VertexInterface<T> nextVertex = vertexIterator.next();
            nextVertex.unVisit();
            nextVertex.setCost(0);
            nextVertex.setPredecessor(null);
        } //end while
    }

}