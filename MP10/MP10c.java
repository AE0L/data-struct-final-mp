import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * @author ASILO, Marvaux
 * @author CHUA, Orjan
 * @author GARCIA, Raphael
 * @author JIMENEZ, Carl
 * @section BSCS 2-2
 */
public class MP10c {

	public static void main(String[] args) throws Exception{
		BufferedWriter bfr = null;
		char again = 'y', ans;
		Scanner sc = new Scanner(System.in);
		ArrayList<String> arr = new ArrayList<String>();
		String temp = "";
		SimpleDateFormat d = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		
		try {
			bfr = new BufferedWriter(new FileWriter("transact.dat"));
			while(again == 'y' || again == 'Y') {
				temp = "";
				System.out.print("Update Code: ");
				ans = sc.nextLine().charAt(0);
				temp += ans;
				if(ans == 'a' || ans == 'A') {	
					bfr.append("A,");
					System.out.print("Part number: ");
					
					temp = sc.nextLine();
					bfr.append(temp);
					bfr.append(",");
					
					System.out.print("Part Description: ");
					
					temp = sc.nextLine();
					bfr.append(temp);
					bfr.append(",");
					
					System.out.print("Part Price: ");
					
					temp =  sc.nextLine();
					bfr.append(temp);
					bfr.append(",");
					
					bfr.append(d.format(date));
				}
				else if(ans == 'c' || ans == 'C') {
					System.out.print("Part number: ");
					temp = sc.nextLine();

					bfr.append("C,");
					bfr.append(temp);
					bfr.append(",");
					System.out.print("Change Code: ");
					temp = sc.nextLine();
					bfr.append(temp);
					bfr.append(",");
					
					if(temp.charAt(temp.length() - 1) == 'D') {
							System.out.print("New Description: ");
							temp = sc.nextLine();
							bfr.append(temp);
							bfr.append(",");
							
							bfr.append(d.format(date));
					}
					else if(temp.charAt(temp.length() - 1) == 'P') {
						System.out.print("New Price: ");
						temp = sc.nextLine();
						bfr.append(temp);
						bfr.append(",");
						
						bfr.append(d.format(date));
					}
				}
				else if(ans == 'd' || ans == 'D') {
					 bfr.append("D,");
					 System.out.print("Part number: ");
					 temp = sc.nextLine();
					 bfr.append(temp);
					bfr.append(",");
						
					 bfr.append(d.format(date));
				}
				bfr.newLine();
				System.out.print("Again?: ");
				again = sc.nextLine().charAt(0);
		}
		}
		catch(Exception e) {
			System.out.println("File transact.dat already exists! Run MP10d.java to first append past transactions to master.dat");
		}
		finally {
			bfr.close();
			sc.close();
			System.out.println("Transaction saved");
		}
		
		
		}
}

