package ryan.tree;

/**
 * 树的结点
 */
public class BTreeNode<T> {

    private T data;
    private BTreeNode<T> lChild;
    private BTreeNode<T> rChild;

    // 左右结点默认为空
    public BTreeNode(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BTreeNode<T> getLChild() {
        return lChild;
    }

    public void setLChild(BTreeNode<T> lChild) {
        this.lChild = lChild;
    }

    public BTreeNode<T> getRChild() {
        return rChild;
    }

    public void setRChild(BTreeNode<T> rChild) {
        this.rChild = rChild;
    }

    /**
     * 前序遍历，二叉树的子节点也是二叉树
     */
    public void preOrderTraverse() {
        // todo 这里先单纯地打印，往后可以安排更加复杂地结点操作
        System.out.println(this.data);
        if (this.lChild != null) {
            this.lChild.preOrderTraverse();
        }
        if (this.rChild != null) {
            this.rChild.preOrderTraverse();
        }
    }

    /**
     * 中序遍历
     */
    public void inOrderTraverse() {
        if (this.lChild != null) {
            this.lChild.inOrderTraverse();
        }
        System.out.println(this.data);
        if (this.rChild != null) {
            this.rChild.inOrderTraverse();
        }
    }

    /**
     * 后序遍历
     */
    public void postOrderTraverse() {
        if (this.lChild != null) {
            this.lChild.postOrderTraverse();
        }
        if (this.rChild != null) {
            this.rChild.postOrderTraverse();
        }
        System.out.println(this.data);
    }

    @Override
    public String toString() {
        return "BTreeNode{" +
                "data=" + data +
                '}';
    }
}