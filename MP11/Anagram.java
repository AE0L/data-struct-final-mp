import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author ASILO, Marvaux
 * @author CHUA, Orjan
 * @author GARCIA, Raphael Taylor
 * @author JIMENEZ, Carl Justin
 * @section BSCS 2-2
 */
public class Anagram {

  /**
   * The hash table for the dictionary.
   */
  private HashMap<String, String> dictionary = new HashMap<String, String>();

  /**
   * Parse the dictionary text file then transfer each word to the hash table. The key to be used
   * for each word is the sorted letters of the word i.e. (key of the word "toxic" is "ciotx").
   * 
   * @param file - file name of the dictionary text file.
   * @throws FileNotFoundException when the dictionary text file does not exist.
   * @throws IOException when an error occured while reading the data in the text file.
   */
  public void getDictionary(String file) throws FileNotFoundException, IOException {
    long start = System.currentTimeMillis();
    FileReader src = new FileReader(file);
    BufferedReader reader = new BufferedReader(src);
    String line = "";

    // Read dictionary file until EOF
    while ((line = reader.readLine()) != null) {
      if (line.equals(""))
        continue;

      // Sort the letters of the word for the key
      char[] arr = line.toLowerCase().toCharArray();
      Arrays.sort(arr);

      // Add the word and the key generated in the hash table
      dictionary.add(new String(arr), line);
    }

    // Close the input stream
    reader.close();

    System.out.println(
        "Dictionary parsed! (" + ((System.currentTimeMillis() - start) / 1000F) + " seconds)\n");
  }

  /**
   * Parse the input file and put each word in an Linked List. For each word, find matching anagrams
   * in the dictionary provided. The format for each output word is:
   * 
   * [input word] [number of anagrams] [list of anagrams found]
   * 
   * @param file - file name of the input file.
   * @throws FileNotFoundException when the input file does not exist.
   * @throws IOException when an error occured while reading the input file or writing in the output
   *         file.
   */
  public void findAnagrams(String file) throws FileNotFoundException, IOException {
    long start = System.currentTimeMillis();
    BufferedReader reader = new BufferedReader(new FileReader(file));
    BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
    String word;

    // Read input file until EOF
    while ((word = reader.readLine()) != null) {
      // If the line is empty skip it
      if (word.equals("")) {
        continue;
      }

      // Sort the letters of the word to compare with the keys in the dictionary
      char[] arr = word.toLowerCase().toCharArray();
      Arrays.sort(arr);

      // Get the node in the dictionary hash table that has a matching key
      Node<String, String> match = dictionary.get(new String(arr));
      List<String> result = new List<>();

      // If a matching key was found iterate through the nodes to find for anagrams
      if (match != null) {
        if (!match.value().equals(word))
          result.add(match.value());

        while (match.hasNext()) {
          match = match.next();

          // If an anagram was found add to the result list
          if (match.key().equals(new String(arr)) && !match.value().equals(word))
            result.add(match.value());
        }

        // Append the word and the number of anagrams found
        writer.write(word + " " + result.size());

        // Append the anagrams found
        for (String anagram : result)
          writer.write(" " + anagram);
      } else {
        // If no matching key was found append the word and the digit zero
        writer.write(word + "0");
      }

      writer.newLine();
    }

    // Close the input and output stream
    reader.close();
    writer.close();

    System.out.println(
        "Output file created! (" + ((System.currentTimeMillis() - start) / 1000F) + " seconds)");
  }

}
