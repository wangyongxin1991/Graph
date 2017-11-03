/**
 * 
 */
package cn.tedu;

/**
 * @ClassName BasicGraphInterface
 * @Task TODO
 * @author  wyx
 * @date 2017��11��3�� ����10:10:07
 */
public interface BasicGraphInterface<T>
{
    /*
     *@Task:�������Ķ������ͼ
     *@param vertexLabel ����¶���Ķ��� 
     */
    public void addVertex(T vertexLabel);
    //  public boolean addVertex(T vertexLable);

    /*
     * @Task ��ͼ��ָ����������һ����Ȩ��,����������֮���ڲ����֮ǰ�����б�
     * @param begin ��ʶ�ߵ����
     * @param end ��ʶ�ߵ��յ�
     * @return ������ɹ�,����true,���򷵻�false
     */
    public boolean addEdge(T begin, T end, double edgeWeight);

    public boolean addEdge(T begin, T end);

    /*
     * @Task �������ָ���Ķ���֮���Ƿ���ڱ�
     * @param begin ��ʶ�ߵ����Ķ���
     * @param end ��ʶ�ߵ��յ�Ķ���
     * @return ���б��򷵻�true
     */
    public boolean hasEdge(T begin, T end);

    public boolean isEmpty();//���ͼ�Ƿ�Ϊ��

    public int getNumberOfVertices();//���ͼ�ж���ĸ���

    public int getNumberOfEdges();//���ͼ�еıߵ�����

    public void clear();//ɾ��ͼ�����еĶ������
}
