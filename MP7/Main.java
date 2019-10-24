import java.util.Scanner;

/**
 * Machine Problem 7: Binary Tree Traversals.
 * 
 * @author ASILO, Marvaux
 * @author CHUA, Orjan
 * @author GARCIA, Raphael Taylor
 * @author JIMENEZ, Carl Justin P.
 * @section BSCS 2-2
 */
public class Main {

  public static void main(String[] args) {
    Parser parser = new Parser();
    BinaryTree tree;
    Scanner input = new Scanner(System.in);

    try {
      System.out.print("Enter input file name: ");
      String file = input.nextLine();

      System.out.println("\nParse result:\n");
      tree = parser.parseTextFile(file);
      System.out.println("\nRoot of the tree:  " + tree.getRoot() + "\n");
      System.out.print("Preorder traversal:  ");
      tree.traverse(BinaryTree.PREORDER);
      System.out.print("Inorder traversal:   ");
      tree.traverse(BinaryTree.INORDER);
      System.out.print("Postorder traversal: ");
      tree.traverse(BinaryTree.POSTORDER);
    } catch (Exception e) {
      System.out.println("ERROR: " + e.getMessage());
    } finally {
      input.close();
    }
  }

}
