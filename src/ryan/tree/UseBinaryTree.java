package ryan.tree;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class UseBinaryTree {

    public static void main(String[] args) {
        // 创建一棵空二叉树
        BTree<Integer> tree = new BTree<>();
        // 先手动创建一棵二叉树，并且验证三种遍历算法是否有效
        BTreeNode<Integer> root = new BTreeNode<>(5);
        BTreeNode<Integer> node1 = new BTreeNode<>(6);
        BTreeNode<Integer> node2 = new BTreeNode<>(3);
        BTreeNode<Integer> node3 = new BTreeNode<>(1);
        BTreeNode<Integer> node4 = new BTreeNode<>(4);
        BTreeNode<Integer> node5 = new BTreeNode<>(2);
        node1.setLChild(node3);
        node1.setRChild(node4);
        node2.setLChild(node5);
        root.setLChild(node1);
        root.setRChild(node2);
        tree.setRoot(root);

        System.out.println("前序遍历结果------------------");
        List<Integer> preList = tree.preOrderTraverse();
        System.out.println(preList);

        System.out.println("\n中序遍历结果------------------");
        List<Integer> inList = tree.inOrderTraverse();
        System.out.println(inList);

        System.out.println("\n后序遍历结果------------------");
        List<Integer> postList = tree.postOrderTraverse();
        System.out.println(postList);

        System.out.println("\n层序遍历结果------------------");
        List<Integer> levelList = tree.levelOrderTraverse();
        System.out.println(levelList);

        System.out.println("\n从数组创建一棵完全二叉树------------------");

        // 从数组创建一棵完全二叉树
        Integer[] array4Tree = new Integer[]{4, 7, 14, 3, 6, 1, 5, 2, 8, 9};
        BTree<Integer> tree2 = new BTree<>();
        tree2.createFromArray(array4Tree);
        System.out.println("前序遍历结果------------------");
        System.out.println(tree2.preOrderTraverse());

        System.out.println("中序遍历结果------------------");
        System.out.println(tree2.inOrderTraverse());

        System.out.println("后序遍历结果------------------");
        System.out.println(tree2.postOrderTraverse());

        System.out.println("层序遍历结果------------------");
        System.out.println(tree2.levelOrderTraverse());

        // System.out.println("\n从列表创建一棵完全二叉树------------------");

        /*List<Integer> list4Tree = new ArrayList<>();
        list4Tree.add(5);
        list4Tree.add(6);
        list4Tree.add(3);
        list4Tree.add(1);
        list4Tree.add(4);
        list4Tree.add(2);
        list4Tree.add(10);
        BTree<Integer> tree3 = new BTree<>();
        tree3.createFromList(list4Tree);
        System.out.println("中序遍历结果------------------");
        System.out.println(tree3.inOrderTraverse());*/

        // 通过反射获取私有的方法并且调用
        /*try {
            Class<?> clazz = Class.forName("ryan.tree.BTree");
            Method declaredMethod = clazz.getDeclaredMethod("inOr", BTreeNode.class);
            // 私有方法先设置可以访问
            declaredMethod.setAccessible(true);
            System.out.println("\n使用私有方法强制中序遍历结果------------------");
            declaredMethod.invoke(tree, root);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
