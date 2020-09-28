package ryan.linear;

public class LinkedList<T> implements IList<T> {
    private Node<T> head;

    @Override
    public int size() {
        int len = 0;
        Node<T> temp = this.head;
        while (temp != null) {
            len++;
            temp = temp.next;
        }
        return len;
    }

    @Override
    public T get(int i) {
        return null;
    }

    @Override
    public T remove(int i) {
        return null;
    }

    @Override
    public void insert(int i) {

    }

    @Override
    public int indexOf(T data) {
        return 0;
    }

    @Override
    public boolean empty() {
        return false;
    }

    @Override
    public void clear() {

    }

    public void reverse() {
        Node<T> cur = null;
        Node<T> pre = this.head;
        while (pre != null) {
            Node<T> temp = pre.next;
            pre.next = cur;
            cur = pre;
            pre = temp;
        }
    }

    public Node<T> getKthFromEnd(int k) {
    	Node<T> pre = this.head;
    	Node<T> cur = this.head;
    	for (int i = 0; i < k; i++) {
    		pre = pre.next;
    	}
    	while(pre != null) {
    		pre = pre.next;
    		cur = cur.next;
    	}
        return cur;
    }

    public String printLinkedList() {
        Node<T> temp = this.head;
        StringBuilder res = new StringBuilder();
        while (temp != null) {
            res.append(temp.data).append(" ");
            temp = temp.next;
        }
        return res.toString();
    }

    @Override
    public String toString() {
        return printLinkedList();
    }
}
