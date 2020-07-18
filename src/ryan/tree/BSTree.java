package ryan.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 二叉排序树
 */
public class BSTree<T extends Comparable<T>> extends BTree<T> {

    @Override
    public void createFromList(List<T> list) {
        if (list != null && list.size() != 0) {
            List<BTreeNode<T>> nodes = new ArrayList<>();
            for (T t : list) {
                nodes.add(new BTreeNode<T>(t));
            }
            // 设置根结点
            this.root = nodes.get(0);
            // 生成二叉排序树
            // 根结点已经生成，现在开始插入
            for (int i = 1; i < nodes.size(); i++) {
                insert(nodes.get(i));
            }
        }
    }

    @Override
    public void createFromArray(T[] array) {
        if (array != null && array.length != 0) {
            // 先把所有结点创建出来,并暂时保存在List中
            List<T> nodeData = new ArrayList<>(Arrays.asList(array));
            // 转化为List借用List中构建方法来构建二叉树
            createFromList(nodeData);
        }
    }

    /**
     * 在二叉排序树中插入一个新节点，且是在叶子节点上插入的
     *
     * @param node 新结点
     */
    public void insert(BTreeNode<T> node) {
        if (this.root != null) {
            insertInBTSHelp(this.root, node);
        }
    }

    /**
     * 插入新数据
     *
     * @param data 新数据
     */
    public void insert(T data) {
        insert(new BTreeNode<>(data));
    }

    /**
     * 往二叉排序树中插入一个结点，要保持该树仍然是一棵二叉排序树
     *
     * @param node1 当前的结点
     * @param node2 需要插入的新节点
     */
    private void insertInBTSHelp(BTreeNode<T> node1, BTreeNode<T> node2) {
        if (node1 != null) {
            int res = node1.getData().compareTo(node2.getData());
            // 当前结点比较大，插入结点往左走
            if (res > 0) {
                if (node1.getLChild() != null) {
                    insertInBTSHelp(node1.getLChild(), node2);
                } else {
                    // 左子树为空了，直接插入在左边
                    node1.setLChild(node2);
                }
            } else {
                // 插入结点往右走
                if (node1.getRChild() != null) {
                    insertInBTSHelp(node1.getRChild(), node2);
                } else {
                    node1.setRChild(node2);
                }
            }
        }
    }

    /**
     * 给定结点的数据，删除二叉排序树上的结点
     *
     * @param data 需要删除的结点的数据
     * @return 是否删除成功，成功则返回true，失败返回false
     */
    public boolean remove(T data) {
        return removeHelper(this.root, data);
    }

    /**
     * 从node结点往下开始查找数据为data的结点，并且删除
     *
     * @param node 开始查找的node结点
     * @param data 需要删除的结点的数据
     * @return 是否删除成功，成功则返回true，失败返回false
     */
    private boolean removeHelper(BTreeNode<T> node, T data) {
        // 使用非递归的方法来实现删除
        // 先找到要删除的结点和它的双亲结点
        BTreeNode<T> parent = node;
        BTreeNode<T> current = node;
        boolean nodeInLeft = false;
        while (current != null) {
            int res = current.getData().compareTo(data);
            if (res > 0) {
                // 当前结点的值比要查找的值大，往左子树继续查找
                if (current.getLChild() != null) {
                    nodeInLeft = true;
                    parent = current;
                    current = current.getLChild();
                } else {
                    // 没找到
                    return false;
                }
            } else if (res < 0) {
                // 当前结点的值比要查找的值小，往右子树继续查找
                if (current.getRChild() != null) {
                    nodeInLeft = false;
                    parent = current;
                    current = current.getRChild();
                } else {
                    // 没找到
                    return false;
                }
            } else {
                // 当前结点的值和要查找的值相等，已经找到了相应的结点current，他的双亲结点是parent
                break;
            }
        }
        // 找到了结点，开始执行删除操作
        // 当前结点没有左右子树,已经是叶子节点了
        assert parent != null;
        assert current != null;
        boolean needsClearCurrent = false;
        if (current.getLChild() == null && current.getRChild() == null) {
            changeLinks(parent, nodeInLeft, null);
            needsClearCurrent = true;
        }
        // 当前结点只有左子树或者只有右子树
        else if (current.getLChild() != null && current.getRChild() == null) {
            changeLinks(parent, nodeInLeft, current.getLChild());
            needsClearCurrent = true;
        } else if (current.getRChild() != null && current.getLChild() == null) {
            changeLinks(parent, nodeInLeft, current.getRChild());
            needsClearCurrent = true;
        }
        // 当前结点左右子树都有
        else if (current.getLChild() != null && current.getRChild() != null) {
            // 使用这个结点的后继来替代这个结点，然后把替代它的那个后继结点也删掉
            // 找后继节点，当前结点先往右走，再一直往左走知道没有左，则找到了后继节点
            BTreeNode<T> next = current.getRChild();
            BTreeNode<T> parentNext = current;
            boolean flag = false;
            while (next.getLChild() != null) {
                // 查找的深度大于等于二
                // 即找到next结点实在当前结点至少往下两层了
                flag = true;
                parentNext = next;
                next = next.getLChild();
            }
            current.setData(next.getData());    // 改变current结点的值来达到删除结点的效果
            // 删掉next
            changeLinks(parentNext, flag, null);
            next = null;
        }
        if (needsClearCurrent) {
            // 当前结点清空，使用于前两种情况，最后一种情况是不用删除current结点的
            // 而是改变current结点的值来达到删除结点的效果
            current.setLChild(null);
            current.setRChild(null);
        }
        return true;
    }

    /**
     * 改变父节点的左右孩子情况
     *
     * @param parent 父节点
     * @param flag   设置左子树还是右子树，flag表示设置左子树
     * @param o      新设置的子树
     */
    private void changeLinks(BTreeNode<T> parent, boolean flag, BTreeNode<T> o) {
        if (flag) {
            parent.setLChild(o);
        } else {
            parent.setRChild(o);
        }
    }
}
