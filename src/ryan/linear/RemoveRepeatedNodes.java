package ryan.linear;

import java.util.HashMap;

public class RemoveRepeatedNodes {
    public static void main(String[] args) {
        Node<Integer> head = new Node<>(1);
        Node<Integer> one = new Node<>(2);
        head.next = one;
        Node<Integer> two = new Node<>(2);
        one.next = two;
        Node<Integer> three = new Node<>(3);
        two.next = three;
        three.next = new Node<>(2);

        Node<Integer> temp = head;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }

        Node<Integer> cur = head;
        Node<Integer> pre = cur;
        // 这样的解法默认是重复元素是相邻的
//        HashMap<Integer, Integer> map = new HashMap<>();
//        while (pre != null) {
//            if (map.containsKey(pre.data)) {
//                pre = pre.next;
//            } else {
//                map.put(pre.data, 0);
//                if (!cur.data.equals(pre.data)) {
//                    cur.next = pre;
//                    cur = pre;
//                }
//            }
//        }
//        cur.next = pre;

        // 如果重复元素不相邻，则可能要下面的方法来解决
        while (pre != null) {
            if (cur.data.equals(pre.data)) {
                pre = pre.next;
            } else {
                cur.next = pre;
                cur = pre;
            }
        }
        cur.next = pre;

        System.out.println("------------------");
        Node<Integer> temp1 = head;
        while (temp1 != null) {
            System.out.println(temp1.data);
            temp1 = temp1.next;
        }
    }
}
