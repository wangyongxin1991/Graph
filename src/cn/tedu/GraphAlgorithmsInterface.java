/**
 * 
 */
package cn.tedu;

import java.util.Queue;
import java.util.Stack;

/**
 * @ClassName GraphAlgorithmsInterface
 * @Task TODO
 * @author  wyx
 * @date 2017��11��3�� ����10:13:43
 */
public interface GraphAlgorithmsInterface<T>
{
    /*
     * Task ִ��ͼ��������ȱ���
     * @param origin ��ʶ������������
     * @return �����Ķ����ʶ����,���ı��λ�ڶ���ǰ��
     */
    public Queue<T> getDepthFirstTraversal(T origin);

    public Queue<T> getBreadthFirstTraversal(T origin);

    /*
     * Task ִ�������޻�ͼ�Ķ���ĺ���������
     * @return ��ջ����ʼ�������������еĶ����ʶջ
     */
    public Stack<T> getTopologicalSort();

    /**Task��Ѱ������ָ������֮������·��
     * @param begin ��ʶ·�����
     * @param end ��ʶ·���յ�
     * @param path ��ʼΪ�յ�ջ
     *          ��ջ���������·���Ķ���
     *          ����ʶλ��ջ��,�յ��ʶλ��ջ��
     * @return �������·���ĳ���
     */
    public int getShortestPath(T begin, T end, Stack<T> path);

    /**Task: Ѱ������ָ������������͵�·��
     * @param begin ��ʶ·�������
     * @param end   ��ʶ·�����յ�
     * @param path  ��ʼΪ�յ�ջ
     *              ��ջ�����ط�����͵�·���Ķ���
     *              ����ʶλ��ջ��,�յ�ı�ʶλ��ջ��
     * @return ���ط������·���ķ���
     */
    public double getCheapestPath(T begin, T end, Stack<T> path);
}
