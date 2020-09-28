package ryan.linear;

public interface IList<T> {

    int size();

    T get(int i);

    T remove(int i);

    void insert(int i);

    int indexOf(T data);

    boolean empty();

    void clear();

}
