/**
 * @author ASILO, Marvaux
 * @author CHUA, Orjan
 * @author GARCIA, Raphael Taylor
 * @author JIMENEZ, Carl Justin
 * @section BSCS 2-2
 *
 * @param <K> - the data of the key.
 * @param <V> - the data of the value.
 */
public class Node<K, V> {

  private K key;
  private V value;
  private Node<K, V> next;

  public Node(K key, V value) {
    this.key = key;
    this.value = value;
  }

  public K key() {
    return this.key;
  }

  public V value() {
    return this.value;
  }

  public void value(V value) {
    this.value = value;
  }

  public Node<K, V> next() {
    return this.next;
  }

  public void next(Node<K, V> next) {
    this.next = next;
  }

  public boolean hasNext() {
    return this.next != null;
  }

}
