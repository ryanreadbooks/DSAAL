package ryan.queue;

public class UseQueue {
    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        queue.add(10);
        queue.add(11);
        queue.add(12);
        queue.add(4);

        System.out.println("remove: " + queue.remove());
        queue.add(77);
        queue.add(80);
        queue.add(90);
        System.out.println("remove: " + queue.remove());
        queue.add(100);

        System.out.println("remove: " + queue.remove());
        System.out.println("remove: " + queue.remove());
        System.out.println("remove: " + queue.remove());
        System.out.println("remove: " + queue.remove());
        System.out.println("remove: " + queue.remove());

        System.out.println(queue.isEmpty());
        queue.add(200);
        queue.add(300);
        queue.add(400);
        queue.add(500);
        queue.add(600);
        queue.add(700);
        System.out.println("get head: " + queue.getHead());
        System.out.println("get tail: " + queue.getTail());
        System.out.println(queue.isEmpty());
        System.out.println(queue.size());
        System.out.println(queue);
    }
}
