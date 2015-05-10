import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Commands {
	// type of method command
		public static void commandType (String input) {
			if (input.indexOf("clear") == -1) {
				cellInput(input);
			} else {
				cellClear(input);
			}
			
			String parsed[] = input.split(" ");
			double[] arrayNum = new double[13 * 16];
			
			//handling SUM input
			if (parsed[0].equals("SUM")) {
				doubleParse(TextExcel.data);
				sum(arrayNum);
			//handling AVG input	
			} else if (parsed[0].equals("AVG")) {
				doubleParse(TextExcel.data);
				average(arrayNum);
			//handling sorta input	
			} else if (parsed[0].equals("sorta")) {
				doubleParse(TextExcel.data);
				sorta(arrayNum);
			//handling sortd input	
			} else if (parsed[0].equals("sortd")) {
				doubleParse(TextExcel.data);
				sortd(arrayNum);
			//handling export input	
			} else if (parsed[0].equals("export")) {
				save(input, TextExcel.data);
			} else if (parsed[0].equals("import")) {
				load(input);
			}
		}
		
		// Cell input method
		public static void cellInput(String input) {
			int cellColumn = (input.charAt(0) - 'A');
			int cellRow = (input.charAt(1) - '1');
			
			TextExcel.data[cellRow][cellColumn].string = input.substring(input.indexOf("=") + 1).trim();
		}
		
		// Cell spaces method
		public static String cellSpaces(String input) {
			if (input.length() > 7) {
				String stop = input.substring(0, 7);
				return stop;
			} else {
				int length = input.length();
				for (int idx = 0; idx < (7 - length); idx++) {
					input = input + " ";
				}
				return input;
			}
		}
		
		// Clearing method
		public static void cellClear (String input) {
			int cellRow = (input.charAt(0) - 'A');
			int cellColumn = (input.charAt(1) - '1');
			
			// clear every cell
			if (input.indexOf(' ') == -1) {
				for (int idx = 0; idx <= cellRow; idx++) {
					for (int x = 0; x <= cellColumn; x++) {
						TextExcel.data[idx][x].string = "       ";
					}
				}
			// clear one cell	
			} else {
				int oneCell = input.indexOf("clear") + 5;
				String name = input.substring(oneCell).trim();
				int horizontal = name.charAt(0) - 'A';
				int vertical = name.charAt(1) - '1';
				TextExcel.data[vertical][horizontal].string = "       ";
			}
		}
		
		// Creates a long single array instead of a double array
		public static void doubleParse (Cell data[][]) {
			double arrayNum[] = new double[13 * 16];
			try {
				int count = 0;
				for (int x = 0; x <= 13; x++) {
					for (int y = 0; y <= 16; y++) {
						arrayNum[count] = data[x][y].number;
						count++;
					}
				}
			} catch (NumberFormatException e) {
				System.err.println("Invalid input. Input needs to be an actual number value.");
			}
			
		}
		
		// Finds the sum
		public static void sum (double[] arrayNum) {
			try {
				double sumNum = 0;
				double storeNum = 0;
				for (int idx = 0; idx <= arrayNum.length; idx++) {
					storeNum = arrayNum[idx] + sumNum;
					sumNum = storeNum; 
				}
				System.out.println("SUM = " + sumNum);
			} catch (Exception e) {
				System.err.println("The contents of each cell must be a real numerical value.");
			}
		}
		
		// Finds the average
		public static void average (double arrayNum[]) {
			try {
				double sumNum = 0;
				double storeNum = 0;
				for (int idx = 0; idx <= arrayNum.length; idx++) {
					storeNum = arrayNum[idx] + sumNum;
					sumNum = storeNum;
				}
				double averageNum = sumNum / arrayNum.length;
				System.out.println("AVG = " + averageNum);
			} catch (Exception e) {
				System.err.println("The contents of each cell must be a real numerical value.");
			}
		}
		
		// Sort in descending order
		public static void sortd (double arrayNum[]) {		
			try {
				for(int idx = arrayNum.length-1; idx >= 0; idx--) {
					int largest = idx;
					double position = arrayNum[idx];
					
					for(int nextnum = idx + 1; nextnum < arrayNum.length; nextnum++){
						if(arrayNum[nextnum] < position) {
							largest = nextnum;
						}
					}
					arrayNum[idx] = arrayNum[largest];
					arrayNum[largest] = position;
				}
				int count = 13 * 16 - 1;
				for (int x = 0; x <= 13; x++) {
					for (int y = 0; y <= 16; y++) {
						TextExcel.data[x][y].number = arrayNum[count];
						count--;
					}
				}
			} catch (Exception e) {
				System.err.println("The contents of each cell must be real numerical values in order to be sorted.");
			}
		}	
		
		// Sort in ascending order
		public static void sorta (double arrayNum[]) {
			try {
				for(int idx = 0; idx < arrayNum.length; idx++){
					int largest = idx;
					double position = arrayNum[idx];
					
					for(int nextnum = idx + 1; nextnum < arrayNum.length; nextnum++){
						if(arrayNum[nextnum] < position) {
							largest = nextnum;
						}
					}
					arrayNum[idx] = arrayNum[largest];
					arrayNum[largest] = position;
				}
				int count = 0;
				for (int x = 0; x <= 13; x++) {
					for (int y = 0; y <= 16; y++) {
						TextExcel.data[x][y].number = arrayNum[count];
						count++;
					}
				}
			} catch (Exception e) {
				System.err.println("The contents of each cell must be real numerical values in order to be sorted.");
			}
		}
		
		// Export a file
		public static void save (String input, Cell[][]data){
			File f = new File(input);
			PrintStream p = null;
			try {
				p = new PrintStream(f);
				int length = data.length;
				int width = data[0].length;
				p.println(length +"x"+width);
				for (int idx = 0; idx < length; idx++) {
					for (int y = 0; y < width; y++) {
						p.println((""+(char)(idx+97))+(""+y)+ " = " + data[idx][y]);
					}
				}
			} catch (Exception e) {
				System.err.println("Location for the file was incorrect.");
			}
		}
		
		// Import a file
		public static void load (String input){
			File f = new File(input);
			Scanner s;
			try {
				s = new Scanner(f);
				// Idea from Jellin to check the loading of the file
				// Check method below
				if (checkLoad(s) == true) {
					String parameters = s.nextLine();
					int hyphen = parameters.indexOf("-");
					int end = parameters.length();
					String x = parameters.substring(0,hyphen-1);
					String y = parameters.substring(hyphen+1,end);
					int xNum = Integer.parseInt(x);
					int yNum = Integer.parseInt(y);
					while (s.hasNextLine()) {
						String nextline = s.nextLine();
						String location = null;
						if (nextline.charAt(2) == ' '){
							location = nextline.substring(6);
						} else if(nextline.charAt(3) == ' '){
							location = nextline.substring(7);
						}
						String[][] importText = new String[xNum][yNum];
						for(int idx = 0; idx < xNum; idx++){
							for(int y2 = 0; y2 < yNum; y2++){
								importText[idx][y2] = location;
							}
						}
					}
				} else {
					System.err.println("Error.");
				}
			} catch (FileNotFoundException e) {
				System.err.println("The file asked for could not be found.");
			}
		}
		
		// Idea from Jellin to check the loading of the file asked for
		public static boolean checkLoad (Scanner s) {
			boolean check = true;
			try {
				String parameters = s.nextLine();
				int hyphen = parameters.indexOf("-");
				int end = parameters.length();
				String x = parameters.substring(0,hyphen-1);
				String y = parameters.substring(hyphen+1,end);
				int xNum = Integer.parseInt(x);
				int yNum = Integer.parseInt(y);
				String[][] testSheet = new String[xNum][yNum];
				while (s.hasNextLine()) {
					for (int idx = 0; idx < xNum; idx++){
						for (int y2 = 0; y2 < yNum; y2++){
							testSheet[idx][y2] = null;
						}
					}
				}
				return check;
			} catch (Exception e) {
				check = false;
				return check;
			}
		}
		
		public static void formulas () {
			
		}

}
