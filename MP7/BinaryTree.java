/**
 * Simple binary tree class.
 * 
 * @author ASILO, Marvaux
 * @author CHUA, Orjan
 * @author GARCIA, Raphael Taylor
 * @author JIMENEZ, Carl Justin
 * @section BSCS 2-2
 */
public class BinaryTree {

  public static final int LEFT = 10;
  public static final int RIGHT = 20;
  public static final int INORDER = 30;
  public static final int PREORDER = 31;
  public static final int POSTORDER = 32;

  private Node root;

  public BinaryTree(Node root) {
    this.root = root;
  }

  public Node getRoot() {
    return this.root;
  }

  /**
   * Creates a new node with the value provided and insert it as a child node of the specified root
   * node.
   * 
   * @param parent - parent of the new node to be added.
   * @param value - value of the new node.
   * @param pos - can be either LEFT or RIGHT.
   * @throws Exception when pos is invalid.
   */
  public void addNode(String parent, String value, int pos) throws Exception {
    Node root = this.getNode(this.root, parent);
    Node node = value.equals("null") ? null : new Node(value);

    if (pos == BinaryTree.LEFT) {
      root.setLeft(node);
    } else if (pos == BinaryTree.RIGHT) {
      root.setRight(node);
    } else {
      throw new Exception("invalid position argument");
    }
  }

  /**
   * Recursively search the tree.
   * 
   * @param root - the current root of the tree/subtree.
   * @param search - the value to be search.
   * @return the Node with the matching value.
   */
  public Node getNode(Node root, String search) {
    Node tmp;

    if (root == null) {
      return null;
    } else if (root.getValue().equals(search)) {
      return root;
    } else if ((tmp = getNode(root.getLeft(), search)) != null && tmp.getValue().equals(search)) {
      return tmp;
    } else if ((tmp = getNode(root.getRight(), search)) != null && tmp.getValue().equals(search)) {
      return tmp;
    } else {
      return null;
    }
  }

  /**
   * Traverse the tree with the specified method. Accepted methods are: PREORDER, INORDER,
   * POSTORDER.
   * 
   * @param method - the method of traversal.
   * @throws Exception when method is invalid.
   */
  public void traverse(int method) throws Exception {
    switch (method) {
      case BinaryTree.PREORDER:
        printPreOrder(this.root);
        break;
      case BinaryTree.INORDER:
        printInOrder(this.root);
        break;
      case BinaryTree.POSTORDER:
        printPostOrder(this.root);
        break;
      default:
        throw new Exception("invalid traversal method");
    }
    System.out.println();
  }

  /**
   * Recursively print the Preorder traversal of the tree.
   * 
   * @param root - current root the tree/subtree.
   */
  private void printPreOrder(Node root) {
    if (root == null)
      return;

    System.out.print(root + " ");
    printPreOrder(root.getLeft());
    printPreOrder(root.getRight());
  }

  /**
   * Recursively print the Inorder traversal of the tree.
   * 
   * @param root - current root of the tree/subtree.
   */
  private void printInOrder(Node root) {
    if (root == null)
      return;

    printInOrder(root.getLeft());
    System.out.print(root + " ");
    printInOrder(root.getRight());
  }

  /**
   * Recursively print the Postorder traversal of the tree.
   * 
   * @param root - current root of the tree/subtree.
   */
  private void printPostOrder(Node root) {
    if (root == null)
      return;

    printPostOrder(root.getLeft());
    printPostOrder(root.getRight());
    System.out.print(root + " ");
  }

}
