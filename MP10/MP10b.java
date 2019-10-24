import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

/**
 * @author ASILO, Marvaux
 * @author CHUA, Orjan
 * @author GARCIA, Raphael
 * @author JIMENEZ, Carl
 * @section BSCS 2-2
 */
public class MP10b {

	   public static void main(String [] args) throws Exception{

	        Scanner sc = new Scanner(System.in);
	        Scanner sc2 = new Scanner(System.in);
	        String fn;
	        int ans;
	        System.out.print("Enter source file: ");
	        fn = sc.nextLine();
	        System.out.print("Show Items in\n1 - Screen\n2 - Printer\n3 - File");
	        System.out.print("\n\nEnter Choice: ");
	        ans = sc.nextInt();
	        File file = null;
        	String f;
	        try {

		       
	        	FileReader fr = new FileReader(fn);
	        	BufferedReader bfr = new BufferedReader(fr);
	        	String line;
	        	
	        	if(ans == 1) {
	        		for(int x = 0; x < 10; x++) {
	        			System.out.println("\n");
	        		}
	        		SimpleDateFormat t = new SimpleDateFormat("HH:mm:ss");
	        		SimpleDateFormat d = new SimpleDateFormat("MM/dd/yyyy");
	        		Date date = new Date();
	        		System.out.println("\t\t\tXYZ Company");
	        		System.out.println("\n\t\t\t\t\t\tDate: " + d.format(date));
	        		System.out.println("\t\t\t\t\t\tTime: " + t.format(date) + "\n");
	        		int i;
	        		while((line = bfr.readLine()) != null) {
	        			System.out.println(line);
	        		}
	        		bfr.close();
	        	}
	        	else if(ans == 2){
	        		for(int x = 0; x < 10; x++) {
	        			System.out.println("\n");
	        		}
					ArrayList<String> list = new ArrayList<String>();
					while((line = bfr.readLine()) != null) {
						list.add(line);
					}
					Iterator itr = list.iterator();
					file = new File("temp.txt");
					BufferedWriter bfw = new BufferedWriter(new FileWriter(file));
					bfw.write("\t\t\tXYZ Company");
					bfw.newLine();
					bfw.newLine();
					bfw.write("\t\t     Product Listing");
					bfw.newLine();
					bfw.newLine();
					while(itr.hasNext()) {
						bfw.write("" + itr.next());
						bfw.newLine();
					}
					bfw.newLine();
					bfw.newLine();
					SimpleDateFormat d = new SimpleDateFormat("MM/dd/yyyy");
					Date date = new Date();
					bfw.write("\t\t\t\t\t\t" + d.format(date));
					bfw.close();
					bfr.close();
					
					DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
					PrintRequestAttributeSet ASet = new HashPrintRequestAttributeSet();
					PrintService[] pservices = PrintServiceLookup.lookupPrintServices(flavor, ASet);
					int printnbr = 0;
					DocPrintJob pj = pservices[printnbr].createPrintJob();
					
					try {
						FileInputStream fis = new FileInputStream(file);
						Doc doc = new SimpleDoc(fis, flavor, null);
						
						pj.print(doc,  ASet);
					}
					catch(Exception e) {
						e.printStackTrace();
					}
					finally {
						System.out.println("File printed.");
						file.delete();
					}
					
	        	}	
	        	else if(ans == 3) {
	        		for(int x = 0; x < 10; x++) {
	        			System.out.println("\n");
	        		}
	        		try{
	        			System.out.println("Enter target file: ");
	        			f = sc2.nextLine();
	        			BufferedWriter bf = new BufferedWriter(new FileWriter(f));
	        			while((line = bfr.readLine()) != null) {
	        				bf.write(line);
							bf.newLine();
	        			}
	        			bf.close();
	        		}
	        		catch(Exception e) {
	        			System.out.println("File already exists");
	        		}
					
	     
	        	}
	        }
	        catch(Exception e) {
	        	System.out.println("File not found");
	        }
	 
	       
}
	 
	 
	}



