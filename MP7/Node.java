/**
 * @author ASILO, Marvaux
 * @author CHUA, Orjan
 * @author GARCIA, Raphael Taylor
 * @author JIMENEZ, Carl Justin
 * @section BSCS 2-2
 */
public class Node {

  private String value;
  private Node left;
  private Node right;

  public Node(String value) {
    this.value = value;
    this.left = null;
    this.right = null;
  }

  public Node(String value, Node left, Node right) {
    this.value = value;
    this.left = left;
    this.right = right;
  }

  public String getValue() {
    return value;
  }

  public Node getLeft() {
    return left;
  }

  public void setLeft(Node left) {
    this.left = left;
  }

  public Node getRight() {
    return right;
  }

  public void setRight(Node right) {
    this.right = right;
  }

  @Override
  public String toString() {
    return getValue();
  }

}
