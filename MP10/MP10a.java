import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author ASILO, Marvaux
 * @author CHUA, Orjan
 * @author GARCIA, Raphael
 * @author JIMENEZ, Carl
 * @section BSCS 2-2
 */
public class MP10a {

	public static void main(String[] args) throws IOException{
		BufferedWriter fw = new BufferedWriter(new FileWriter("master.dat"));
		ArrayList<String> num = new ArrayList<>();
		ArrayList<String> Final = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		String temp = "";
		String[] foo = new String[3];
		String ans = "yes";
		while(ans.charAt(0) == 'y' || ans.charAt(0) == 'Y') {
			System.out.print("Part Number: ");
			temp = sc.nextLine();
			num.add(temp);
			System.out.print("Part Description: ");
			temp = sc.nextLine();
			num.add(temp);
			System.out.print("Part Price: ");
			temp = sc.nextLine();
			num.add(temp);
			System.out.print("\nAnother (Y/N)?: ");
			ans = sc.nextLine();
		}
		
		Iterator itr1 = num.iterator();
		while(itr1.hasNext()) {
			foo[0] = "" + itr1.next(); 
			foo[1] = "" + itr1.next();
			foo[2] = "" + itr1.next();
			
			if(foo[1].length() > 20) {
				Final.add(foo[0] + "\t" + foo[1] + "\t" + foo[2]);
			}
			
			else if(foo[1].length() <= 20 && foo[1].length() >= 15) {
				Final.add(foo[0] + "\t" + foo[1] + "\t\t" + foo[2]);
			}
			
			else if(foo[1].length() < 15 && foo[1].length() > 9)
				Final.add(foo[0] + "\t" + foo[1] + "\t\t\t" + foo[2]);
			else Final.add(foo[0] + "\t" + foo[1] + "\t\t\t" + foo[2]);
		}
		
		Collections.sort(Final);
		Iterator itr4 = Final.iterator();
		fw.write("Part Number\t\tDescription\t\tPrice");
		fw.newLine();
		fw.newLine();
		
		while(itr4.hasNext()) {
			temp = "" + itr4.next();
			fw.write(temp);
			fw.newLine();
		}
		fw.close();
		sc.close();
		System.out.println("Transactions saved. Press any key to continue...");
	}

}	
