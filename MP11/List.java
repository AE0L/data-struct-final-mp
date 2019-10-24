import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author ASILO, Marvaux
 * @author CHUA, Orjan
 * @author GARCIA, Raphael Taylor
 * @author JIMENEZ, Carl Justin
 * @section BSCS 2-2
 *
 * @param <T> - the data type of the nodes in the list.
 */
public class List<T> implements Iterable<T> {

  private int size;
  private Node<T> head;
  private Node<T> tail;

  private class Node<E> {
    E value;
    Node<E> next;
    Node<E> prev;

    Node(E value) {
      this.value = value;
      this.next = null;
      this.prev = null;
    }
  }

  public List() {
    this.size = 0;
    this.head = null;
  }

  public void add(T value) {
    Node<T> newNode = new Node<>(value);

    if (this.isEmpty()) {
      this.head = newNode;
      this.tail = newNode;
    } else {
      newNode.prev = this.tail;
      this.tail.next = newNode;
      this.tail = newNode;
    }

    this.size++;
  }

  public T get(int index) throws IndexOutOfBoundsException {
    if (this.isEmpty()) {
      throw new IndexOutOfBoundsException("Index: 0, Size: 0");
    } else {
      Node<T> curr;
      int cnt;

      if (index < (this.size / 2)) {
        curr = this.head;
        cnt = 0;

        while (cnt != index) {
          curr = curr.next;
          cnt++;
        }

        return curr.value;
      } else {
        curr = this.tail;
        cnt = this.size - 1;

        while (cnt != index) {
          curr = curr.prev;
          cnt--;
        }

        return curr.value;
      }
    }
  }

  public void set(int index, T value) throws IndexOutOfBoundsException {
    if (this.isEmpty()) {
      throw new IndexOutOfBoundsException("Index: 0, Size: 0");
    } else if (index == 0) {
      Node<T> newNode = new Node<>(value);
      newNode.next = this.head.next;
      this.head = newNode;
    } else if (index == (this.size - 1)) {
      Node<T> newNode = new Node<>(value);
      newNode.prev = this.tail.prev;
      this.tail = newNode;
    } else {
      Node<T> newNode = new Node<>(value);
      Node<T> curr;
      int cnt;

      if (index < (this.size / 2)) {
        curr = this.head.next;
        cnt = 1;

        while (cnt != index) {
          curr = curr.next;
          cnt++;
        }

        newNode.prev = curr.prev;
        newNode.next = curr.next;
        curr.prev.next = newNode;
        curr.next.prev = newNode;
      } else {
        curr = this.tail.prev;
        cnt = this.size - 2;

        while (cnt != index) {
          curr = curr.prev;
          cnt--;
        }

        newNode.prev = curr.prev;
        newNode.next = curr.next;
        curr.prev.next = newNode;
        curr.next.prev = newNode;
      }
    }
  }

  public boolean isEmpty() {

    return this.size == 0;
  }

  public int size() {
    return this.size;
  }

  @Override
  public Iterator<T> iterator() {
    return new ListIterator<T>();
  }

  public class ListIterator<E> implements Iterator<E> {

    int total = size;
    int ptr = 0;

    @SuppressWarnings("unchecked")
    Node<E> curr = (Node<E>) head;

    @Override
    public boolean hasNext() {
      return ptr < size;
    }

    @Override
    public E next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }

      E value = curr.value;
      curr = curr.next;
      ptr++;

      return value;
    }

  }


}
