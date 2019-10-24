import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author ASILO, Marvaux
 * @author CHUA, Orjan
 * @author GARCIA, Raphael Taylor
 * @author JIMENZ, Carl Jusin
 * @section BSCS 2-2
 */
public class Main {

  public static void main(String[] args) {
    Anagram app = new Anagram();
    Scanner input = new Scanner(System.in);

    // Convert the dictionary text file to a hash table
    try {
      System.out.print("Enter dictionary file name: ");
      app.getDictionary(input.nextLine());
    } catch (FileNotFoundException e) {
      System.err.println("ERROR: Dictionary not found!");
      System.exit(-1);
    } catch (IOException e) {
      System.err.println("ERROR: Error reading dictionary!");
      System.exit(-1);
    }

    // Parse the input file and check for anagrams for each word in the file
    try {
      System.out.print("Enter input file name: ");
      app.findAnagrams(input.nextLine());
    } catch (FileNotFoundException e) {
      System.err.println("ERROR: File not found!");
      System.exit(-1);
    } catch (IOException e) {
      System.err.println("ERROR: Error reading/writing file!");
      System.exit(-1);
    }

    input.close();
  }

}
