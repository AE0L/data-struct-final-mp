import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author ASILO, Marvaux
 * @author CHUA, Orjan
 * @author GARCIA, Raphael
 * @author JIMENEZ, Carl Justin
 * @section BSCS 2-2
 */
public class Graph {
  @SuppressWarnings("unchecked")
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    /* Adjacency List */
    ArrayList<ArrayList<Integer>> Y = new ArrayList<>();
    /* Removed Nodes List */
    ArrayList<Integer> J = new ArrayList<>();
    /* Directive Input */
    ArrayList<Integer> directives = new ArrayList<>();
    /* Directive Index Counter */
    int x = 0;
    String[] arr;

    System.out.print("Enter directives (SEPARATED BY COMMAS!): ");
    arr = input.nextLine().split(",");
    input.close();

    for (String num : arr)
      directives.add(Integer.parseInt(num));

    for (Integer in : directives) {
      if (0 == in) {
        /* 0 - Directive */
        ArrayList<Integer> _new = new ArrayList<>();
        _new.add(x);
        Y.add(_new);
        x++;
      } else if (1 == in) {
        /* 1 - Directive */
        ArrayList<Integer> _new = new ArrayList<>();

        for (int i = 0; i <= x; i++)
          _new.add(i);

        for (ArrayList<Integer> set : Y)
          set.add(x);

        Y.add(_new);
        x++;
      } else {
        /* >1 - Directive */
        int i = 0;

        for (ArrayList<Integer> set : Y) {
          if ((set.size() - 1) % in == 0)
            if (!J.contains(i))
              J.add(i);
          i++;
        }

        for (ArrayList<Integer> set : (ArrayList<ArrayList<Integer>>) Y.clone())
          for (Integer node : (ArrayList<Integer>) set.clone())
            if (J.contains(node))
              set.remove(node);
      }
    }

    System.out.println("\nDirectives: " + directives.toString());
    System.out.println("Final Node Count: " + (Y.size() - J.size()));
  }

}
