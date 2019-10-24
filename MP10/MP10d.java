import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
public class MP10d {

	public static void main(String[] args) throws Exception{
		BufferedReader mfr = null;
		BufferedReader tfr = null;
		File file = new File("tempz.txt");
		BufferedWriter mfw = null;
		BufferedWriter tfw = null;
		String row = null;
		String data = null;
		ArrayList<String> Pnum = new ArrayList<String>();
		ArrayList<String> Desc = new ArrayList<String>();
		ArrayList<String> Price = new ArrayList<String>();
		ArrayList<String> C = new ArrayList<String>();
		ArrayList<String> D = new ArrayList<String>();
		ArrayList<String> A = new ArrayList<String>();
		ArrayList<String> ErrList = new ArrayList<String>();
		ArrayList<String> Final = new ArrayList<String>();
		boolean foo = true;
		SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		
		int i = 0;
		int[] add = {0,0};
		int[] del = {0,0};
		int[] ch = {0,0};
		int inv = 0, apnd = 0;
		
		try{
			 mfr = new BufferedReader(new FileReader("master.dat"));
			 tfr = new BufferedReader(new FileReader("transact.dat"));
			 
			 while((row = mfr.readLine()) != null) {
				 data = row;
				 data = change(data);
				 
				 if(data != null && i >= 2) {
					 String[] temp = data.split(",");
					 Pnum.add(temp[0]);
					 Desc.add(temp[1]);
					 Price.add(temp[2]);
				 }
				 i++;
			 }
			 
			 while((row = tfr.readLine()) != null ){
				 String[] temp = row.split(",");
				 if(temp[0].charAt(0) == 'A') {
					 A.add(temp[1]);
					 A.add(temp[2]);
					 A.add(temp[3] + "," + temp[4]);
				 }
				 else if(temp[0].charAt(0) == 'C') {
					 C.add(temp[2]);
					 C.add(temp[1]);
					 if(temp[2].charAt(0) == 'P') {
						 C.add(temp[3] + "," + temp[4]);
					 }
					 else C.add(temp[3]);
				 }
				 else if(temp[0].charAt(0) == 'D') {
					 D.add(temp[1]);
				 }
				 else inv++;
			 }
			 //Adding records
			 for(int x = 0; x < A.size(); x+=3) {
				 foo = true;
				 for(int y = 0; y < Pnum.size(); y++) {
					 if(A.get(x).equals(Pnum.get(y))) {
						 ErrList.add("A\t\t" + A.get(x) + "\t"  + "\tINVALID ADD - ALREADY ON MASTER");
						 foo = false;
						 add[1]++;
						 break;
					 }
				 }
				 if(foo = true) {
					 Pnum.add(A.get(x));
					 Desc.add(A.get(x+1));
					 Price.add(A.get(x+2));
					 ErrList.add("A\t\t" + A.get(x) + "\t\t\t\t" + A.get(x+1) + ";" + A.get(x+2));
					 add[0]++;
				 }
			 }
			 
			 
			 //Changing records
			 for(int x = 0; x < C.size(); x+=3) {
				 foo = true;
				 if(C.get(x).charAt(0) == 'D') {
					 for(int y = 0; y < Pnum.size(); y++) {
						 if(C.get(x+1).equals(Pnum.get(y))) {
							 foo = false;
							 Desc.set(y, C.get(x+1));
							 ErrList.add("A\t\t" + C.get(x+1) + "\t\t\t\tDesc - " + C.get(x+2));
							 ch[0]++;
							 break;
						 }
						 else if(y == (Pnum.size() - 1)) {
						ErrList.add("C\t\t" + C.get(x+1) + "\tINVALID CHANGE - RECORD DOES NOT EXIST");
						ch[1]++;
					 }
					 }
				 }
				 else if(C.get(x).charAt(0) == 'P') {
					 for(int y = 0; y < Pnum.size(); y++) {
						 if(C.get(x+1).equals(Pnum.get(y))) {
							 foo = false;
							 Price.set(y, C.get(x+2));
							 ErrList.add("C\t\t" + C.get(x+1) + "\t\t\t\tPrice - " + C.get(x+2));
							 ch[0]++;
							 break;	
						 }
						 else if(y == (Pnum.size() - 1)) {
							 ErrList.add("C\t\t" + C.get(x+1) + "\tINVALID CHANGE - RECORD DOES NOT EXIST");
							 ch[1]++;
							}
					 }
				 	}
			 }
			 
			 //Deleting Records
			 for(int x = 0; x < D.size(); x++) {
				 for(int y = 0; y < Pnum.size(); y++) {
					 if(D.get(x).equals(Pnum.get(y))) {
						 Pnum.remove(y);
						 Desc.remove(y);
						 Price.remove(y);
						 ErrList.add("D\t\t\t\t" + D.get(x));
						 del[0]++;
						 break;
					 }
					 else if(y == (Pnum.size() - 1)) {
						 ErrList.add("D\t\t"  + D.get(x) + "\tINVALID DELETE - RECORD NOT ON MASTER");
						 del[1]++;
					 }
				 }
			 }
			 
			 for(int x = 0; x < Pnum.size(); x++) {
				 if(Desc.get(x).length() >= 25) {
						Final.add(Pnum.get(x) + "\t" + Desc.get(x) + "\t" + Price.get(x));
				 }
				 else if(Desc.get(x).length() > 20 && Desc.get(x).length() < 25) {
						Final.add(Pnum.get(x) + "\t" + Desc.get(x) + "\t\t" + Price.get(x));
					}
					
				 else if(Desc.get(x).length() <= 20 && Desc.get(x).length() >= 15) {
						Final.add(Pnum.get(x) + "\t" + Desc.get(x) + "\t\t" + Price.get(x));
					}
					
				 else if(Desc.get(x).length() < 15 && Desc.get(x).length() > 9)
						Final.add(Pnum.get(x) + "\t" + Desc.get(x) + "\t\t\t" + Price.get(x));
				 else Final.add(Pnum.get(x) + "\t" + Desc.get(x) + "\t\t\t" + Price.get(x));
			 }
			 
			 mfw = new BufferedWriter(new FileWriter("master.dat"));
			 tfw = new BufferedWriter(new FileWriter(file));
			 mfw.write("Part Number\t\tDescription\t\tPrice");
			 mfw.newLine();
			 for(int x = 0; x < Final.size(); x++) {
				 mfw.append(Final.get(x));
				 mfw.newLine();
			 }
			 
			 tfw.append("\t\t\t\t\tXYZ Company");
			 tfw.newLine();
			 tfw.newLine();
			 tfw.append(d.format(date));
			 tfw.newLine();
			 tfw.newLine();
			 tfw.append("\t\t\t\t\tAudit/Error List");
			 tfw.newLine();
			 tfw.newLine();
			 tfw.append("Update\t\tPart\t\tError");
			 tfw.newLine();
			 tfw.append("Code\t\tNumber\t\tMessage\t\t\tRemarks");
			 tfw.newLine();
			 
			 for(int x = 0; x < ErrList.size(); x++) {
				 tfw.append(ErrList.get(x));
				 apnd++;
				 tfw.newLine();
			 }
			 tfw.newLine();
			 tfw.newLine();
			 tfw.append("Total no. of successful Adds: " + add[0]);
			 tfw.newLine();
			 tfw.append("Total no. of successful Change: " + ch[0]);
			 tfw.newLine();
			 tfw.append("Total no. of successful Delete: " + del[0]);
			 tfw.newLine();
			 tfw.newLine();
			 tfw.append("Total no. of Invalid Adds: " + add[1]);
			 tfw.newLine();
			 tfw.append("Total no. of Invalid Change: " + ch[1]);
			 tfw.newLine();
			 tfw.append("Total no. of Invalid Delete: " + del[1]);
			 tfw.newLine();
			 tfw.append("Total no. of Invalid Codes: " + inv);
			 tfw.newLine();
			 apnd+=8;
			 tfw.append("Total lines printed (not including headers but tally): " + apnd);	
			 
			 
			 DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
				PrintRequestAttributeSet ASet = new HashPrintRequestAttributeSet();
				PrintService[] pservices = PrintServiceLookup.lookupPrintServices(flavor, ASet);
				int printnbr = 0;
				DocPrintJob pj = pservices[printnbr].createPrintJob();
				
				try {
					FileInputStream fis = new FileInputStream("tempz.txt");
					Doc doc = new SimpleDoc(fis, flavor, null);
					
					pj.print(doc,  ASet);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
		}
		catch(Exception e) {
			System.out.println("master.dat or transact.dat not found");
		}
		finally {
			mfw.close();
			mfr.close();
			tfr.close();
			tfw.close();
		}

	}
	
	
	
	
	
	public static String change(String data) {
		int y = 0;
		 for(int x = 0; x < data.length(); x++) {
			 if(data.charAt(x) == '\t') {
				 char[] change = data.toCharArray();
				 change[x] = ',';
				 data = String.valueOf(change);
			 }
		 }
		 for(int x = 1; x < (y = data.length()); x++) {
			 if(data.charAt(x) == ',' && data.charAt(x-1) == ',') {
				 StringBuilder temp = new StringBuilder(data);
				 temp.deleteCharAt(x);
				 data = String.valueOf(temp);
				 x--;
			 }
		 }
		 return data;
	}

}
