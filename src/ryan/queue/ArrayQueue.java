package ryan.queue;


import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 队列
 */
public class ArrayQueue<T> {

    private Object[] elements;
    private int tail;   // 指向最后一个数据的后一个位置
    private int head;   // 指向队列的数据

    public ArrayQueue() {
        // 测试时使用临时的容量 5
        elements = new Object[5 + 1];
    }

    public ArrayQueue(int initLength) {
        elements = new Object[initLength + 1];
    }

    public ArrayQueue(Collection<T> elements) {

    }

    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data to be added mustn't be null for ArrayQueue");
        }
        if (full()) {
            // 当前队列已满，进行数组扩容后再添加
            System.out.println("Full queue, needs new capacity");
        } else {
            // 再当前的tail位置加入数据
            elements[tail] = data;
            tail++;
            // tail位置往后挪动一位
            tail = tail % elements.length;
        }
    }

    private boolean full() {
        int tempTail = tail;
        tempTail++;
        tempTail = tempTail % elements.length;
        return tempTail == head;
    }

    /**
     * 队首元素出队
     * @return 出队的队首元素，如果队列为空，则回返回空
     */
    public T remove() {
        @SuppressWarnings("unchecked") T t = (T) elements[head];
        // 当前head结点位置置空
        elements[head] = null;
        head++;
        // 当前head往后挪一位，指向下一个位置
        head = head % elements.length;
        return t;
    }

    /**
     * 获取队首元素
     * @return 队首元素
     */
    public T getHead() {
        @SuppressWarnings("unchecked") T t = (T) elements[head];
        return t;
    }

    /**
     * 获取队尾元素
     * @return 队尾元素
     */
    public T getTail() {
        int lastE = 0;
        @SuppressWarnings("unchecked") T t = (T) elements[tail == 0 ? elements.length - 1 : tail - 1];
        return t;
    }

    /**
     * 获得队列内先包含元素的个数
     * @return 队列内先包含元素的个数
     */
    public int size() {
        int maxLen = elements.length;
        return (tail - head + maxLen) % maxLen;
    }

    /**
     * 判断队列是否为空
     * @return 队列是否为空
     */
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public String toString() {
        return "ArrayQueue{" +
                "elements=" + Arrays.toString(elements) +
                ", tail=" + tail +
                ", head=" + head +
                '}';
    }
}
