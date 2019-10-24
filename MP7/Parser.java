import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/** 
 * @author ASILO, Marvaux
 * @author CHUA, Orjan
 * @author GARCIA, Raphael Taylor
 * @author JIMENEZ, Carl Justin
 * @section BSCS 2-2
 */
public class Parser {

  /**
   * Creates a Binary Tree representation of the data provided in the text file. Format of the
   * binary tree/subtree is: (rootNode, leftNode, rightNode).
   * 
   * @param src - the text file to be parsed.
   * @return the Binary Tree representation of the data in the text file.
   * @throws Exception when an error occured during parsing.
   */
  public BinaryTree parseTextFile(String src) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(src)));
    BinaryTree tree;
    String[] nodes;
    String line;

    try {
      System.out.println("\tNode\tL-Subtree\tR-Subtree");
      line = br.readLine().replaceAll("[()]", "");
      nodes = line.split(",");
      System.out.println("\t" + nodes[0] + "\t" + nodes[1] + "\t\t" + nodes[2]);

      Node left = nodes[1].equals("null") ? null : new Node(nodes[1]);
      Node right = nodes[2].equals("null") ? null : new Node(nodes[2]);
      tree = new BinaryTree(new Node(nodes[0], left, right));

      while ((line = br.readLine()) != null) {
        line = line.replaceAll("[()]", "");
        nodes = line.split(",");
        System.out.println("\t" + nodes[0] + "\t" + nodes[1] + "\t\t" + nodes[2]);
        tree.addNode(nodes[0], nodes[1], BinaryTree.LEFT);
        tree.addNode(nodes[0], nodes[2], BinaryTree.RIGHT);
      }

      br.close();
      return tree;
    } catch (IOException e) {
      throw new IOException("error reading file");
    } catch (Exception e) {
      throw new Exception("error in constructing tree");
    }
  }

}
