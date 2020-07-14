package ryan.tree;

import java.util.*;

/**
 * 二叉树的实现类
 */
public class BTree<T> {

    private BTreeNode<T> root;

    public BTree() {
    }

    /**
     * 传入二叉树的根结点，创建一个二叉树
     *
     * @param root 该树的根结点
     */
    public BTree(BTreeNode<T> root) {
        this.root = root;
    }

    public void setRoot(BTreeNode<T> root) {
        if (this.root == null) {
            this.root = root;
        } else {
            System.out.println("此二叉树不为空，不能重新设置根结点");
        }
    }

    /**
     * 判断该二叉树是否为空树，而且是完全二叉树的结构
     *
     * @return 该二叉树是否为空树
     */
    public boolean isEmpty() {
        return this.root == null;
    }

    /**
     * 从list转化为二叉树结构，而且是完全二叉树的结构
     *
     * @param list 需要转换为树结构的列表
     */
    public void createFromList(List<T> list) {
        if (list != null && list.size() != 0) {
            List<BTreeNode<T>> nodes = new ArrayList<>();
            for (T t : list) {
                nodes.add(new BTreeNode<T>(t));
            }
            // 定义两个指针 rear, front; rear 指向子结点, front指向父结点
            int rear = 0, front = 0;
            // 创建根结点
            this.root = nodes.get(0);
            while (rear != nodes.size() - 1) {
                rear++;
                if (rear % 2 == 1) {
                    // 左子节点
                    // System.out.println("设置左子节点 == " + nodes.get(rear));
                    nodes.get(front).setLChild(nodes.get(rear));
                }
                if (rear % 2 == 0) {
                    // 右子节点
                    // System.out.println("设置右子节点 == " + nodes.get(rear));
                    nodes.get(front).setRChild(nodes.get(rear));
                    // 设置完右子节点之后,必定是需要设置兄弟结点的左右子节点的
                    // 所以设置完右子节点之后,front指针加1
                    front++;
                }
            }
        }
    }

    /**
     * 从数组结构转化为完全二叉树结构
     *
     * @param array 需要转换为树结构的数组
     */
    public void createFromArray(T[] array) {
        if (array != null && array.length != 0) {
            // 先把所有结点创建出来,并暂时保存在List中
            List<T> nodeData = new ArrayList<>(Arrays.asList(array));
            // 转化为List借用List中构建方法来构建二叉树
            createFromList(nodeData);
        }
    }

    /**
     * 前序遍历，外部接口
     *
     * @return 遍历后的结点的数据的列表
     */
    public List<T> preOrderTraverse() {
        if (this.root != null) {
            return preOr(this.root);
        }
        return new ArrayList<>();
    }

    /**
     * 递归使用前序遍历
     *
     * @param node 当前遍历到的结点
     * @return 含有遍历过的结点的值的列表
     */
    private List<T> preOr(BTreeNode<T> node) {
        List<T> list = new ArrayList<>();
        if (node != null) {
            list.add(node.getData());
            if (node.getLChild() != null) {
                list.addAll(preOr(node.getLChild()));
            }
            if (node.getRChild() != null) {
                list.addAll(preOr(node.getRChild()));
            }
        }
        return list;
    }

    /**
     * 中序遍历
     *
     * @return 遍历后的结点的数据的列表
     */
    public List<T> inOrderTraverse() {
        if (this.root != null) {
            return inOr(this.root);
        }
        return new ArrayList<>();
    }

    /**
     * 递归使用中序遍历
     *
     * @param node 当前遍历到的结点
     * @return 含有遍历过的结点的值的列表
     */
    private List<T> inOr(BTreeNode<T> node) {
        List<T> list = new ArrayList<>();
        if (node != null) {
            if (node.getLChild() != null) {
                list.addAll(inOr(node.getLChild()));
            }
            list.add(node.getData());
            if (node.getRChild() != null) {
                list.addAll(inOr(node.getRChild()));
            }
        }
        return list;
    }

    /**
     * 后序遍历
     *
     * @return 遍历后的结点的数据的列表
     */
    public List<T> postOrderTraverse() {
        if (this.root != null) {
            return postOr(this.root);
        }
        return new ArrayList<>();
    }

    /**
     * 递归使用后序遍历
     *
     * @param node 当前遍历到的结点
     * @return 含有遍历过的结点的值的列表
     */
    private List<T> postOr(BTreeNode<T> node) {
        List<T> list = new ArrayList<>();
        if (node != null) {
            if (node.getLChild() != null) {
                list.addAll(postOr(node.getLChild()));
            }
            if (node.getRChild() != null) {
                list.addAll(postOr(node.getRChild()));
            }
            list.add(node.getData());
        }
        return list;
    }

    /**
     * 层序遍历，先遍历完一层，在遍历下一层
     * 借助队列来实现层序遍历
     *
     * @return 遍历后的结点的数据的列表
     */
    public List<T> levelOrderTraverse() {
        List<T> list = new ArrayList<>();
        if (this.root != null) {
            // 初始化一个动态大小的队列
            Queue<BTreeNode<T>> queue = new ArrayDeque<>();
            // 先根结点入队
            queue.add(this.root);
            while (!queue.isEmpty()) {
                // 首元素出队访问，进行需要的操作
                BTreeNode<T> firstTreeNode = queue.remove();
                list.add(firstTreeNode.getData());
                // System.out.println(firstTreeNode.getData());
                // 左右子节点入队
                if (firstTreeNode.getLChild() != null) {
                    queue.add(firstTreeNode.getLChild());
                }
                if (firstTreeNode.getRChild() != null) {
                    queue.add(firstTreeNode.getRChild());
                }
            }
        }
        return list;
    }
}
