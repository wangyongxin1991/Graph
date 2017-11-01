/**
 * 
 */
package cn.tedu;

import java.util.Iterator;

/**
 * @ClassName Vertexinterface
 * @Describe TODO
 * @author  wyx
 * @date 2017��10��31�� ����10:28:47
 */
public interface VertexInterface<T>
{

    /** Task:  ȡ�ö���ı�ʶ--�����ʶ�������ָ�������
     * @return ��Ǹö���Ķ���
     */
    public T getLabel();

    public void visit();//��Ǹö����Ѿ�������
    public void unVisit();//��Ƕ�����δ����
    public boolean isVisited();//�ж϶����Ƿ񱻷���

    /** Task: ��������Ȩ�����Ӹö�����ָ������
     * @param endVertex  ͼ����Ϊ�������յ�Ķ���
     * @param edgeWeight  ʵ��ֵ�ı�Ȩֵ,����еĻ�
     * @return ������ɹ�,����true,���򷵻�false
     */
    public boolean connect(VertexInterface<T> endVertex, double edgeWeight);

    /** Task: ��һ����Ȩ�����Ӹö�����ָ������
     * @param endVertex ͼ����Ϊ�������յ�Ķ���
     * @return ������ɹ�,����true
     */
    public boolean connect(VertexInterface<T> endVertex);

    /**Task: ����һ�������������Ӹö��㿪ʼ�����б�
     * @return �Ӹö��㿪ʼ�ı߶���ĵ�����
     */
    public Iterator<VertexInterface<T>> getNeighborInterator();

    /**Task:  ����һ��������,����Ӹö��㵽���ڽӵ�ıߵ�Ȩ��
     * @return �ö���������ڽӵ��Ȩ�ص�����
     */
    public Iterator<Double> getWeightIterator();

    public boolean hasNeighbor();//�鿴�����Ƿ�������һ���ڽӵ�

    /**Task: ȡ�øö���һ��δ���ʵ��ڽӵ�,����еĻ�
     * @return δ���ʵ��ڽӵ㶥��,���������������ڽӵ��򷵻� null
     */
    public VertexInterface<T> getUnvisitedNeighbor();

    /**Task: ��¼���ö���·���ϵ�ǰһ������
     * @param predecessor �ö����ǰһ������
     */
    public void setPredecessor(VertexInterface<T> predecessor);

    /**Task:  ȡ�øö���·���ϵ�ǰһ������
     * @return ǰһ������,��û�з��� null
     */
    public VertexInterface<T> getPredecessor();

    /**Task:  ���ǰһ�������Ƿ񱻼�¼
     * @return ���Ϊ�ö����¼��ǰһ������,�򷵻�true
     */
    public boolean hasPredecessor();

    public void setCost(double newCost);//���õ��ö���·���ķ���

    public double getCost();//��ȡ���ö���·���ķ���
}
