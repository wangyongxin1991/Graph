/**
 * 
 */
package cn.tedu;

import java.io.Serializable;

/**
 * @ClassName Edge
 * @Describe �߱�Ľ���,vertex ������ʾ���㣬�ö����Ǳߵ��յ㡣weight ��ʾ�ߵ�Ȩֵ��
 * �������Ǵ�Ȩ��������Ͳ���Ҫweight���ԣ���ô����ֱ�Ӷ���һ�������б� ����� �յ� �Ϳ��Ա�ʾ���ˡ�
 * ��Vertex����ͱ�ʾ���㣬�����Vertex�ڲ�����һ��List����յ㣬��ô��List�ټ���Vertex����ʾ�Ķ��㱾��
 * �Ϳ��Ա�ʾ������ڽӵĸ������ˣ���֮Ϊ��� �����ڽӱ�, ����Ϊ�˱�ʾ��Ȩ�ıߣ�
 * ��ˣ�������weight���ԣ�������Edge����װ�����������Ǵ�Ȩ�ı߻��ǲ���Ȩ�ı߶�������ͬһ��Edge������ʾ������Ȩ�ı߽�weight��ֵΪ0���ɡ�
 * 
 *  *Task: ��������һ��������������ʾ��,��Ҫ�ǿ��ǵ���Ȩֵ�ı�
     *���Կ���,Edge���װ��һ�������һ��double���ͱ��� 
     *������Ҫ����Ȩֵ,���Բ���Ҫ��������һ��Edge������ʾ��,ֻ��Ҫһ�����涥����б���
 * @author  wyx
 * @param <T>
 * @date 2017��10��31�� ����10:24:31
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
