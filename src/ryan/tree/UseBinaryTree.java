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
        tree2.createFromArray(array4Tree, false);
        System.out.println("前序遍历结果------------------");
        System.out.println(tree2.preOrderTraverse());

        System.out.println("中序遍历结果------------------");
        System.out.println(tree2.inOrderTraverse());

        System.out.println("后序遍历结果------------------");
        System.out.println(tree2.postOrderTraverse());

        System.out.println("层序遍历结果------------------");
        System.out.println(tree2.levelOrderTraverse());

        System.out.println("tree depth: " + tree.depth());
        System.out.println("tree2 depth: " + tree2.depth());

        System.out.println("\n--------------------------------------\n");

        // 手动生成一棵BTS
        BTree<Integer> btsTree = new BTree<>();
        btsTree.createFromArray(new Integer[]{15, 10, 20, 8, 13, 16, 30, 1, 9, 11}, false);
        System.out.println("插入结点前中序：" + btsTree.inOrderTraverse());
        btsTree.insertInBTS(new BTreeNode<>(20));
        System.out.println("插入结点(14)后中序：" + btsTree.inOrderTraverse());

        System.out.println("\n从无序序列中直接生成二叉排序树------------------");
        // 直接将无序序列转换为有序的二叉排序树
        BTree<Integer> btsTreeAuto = new BTree<>();
        btsTreeAuto.createFromArray(new Integer[]{12, 1, 56, 23, 5, 7, 45, 20}, true);
        System.out.println("BST中序: " + btsTreeAuto.inOrderTraverse());
    }
}
