//Main Class

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextExcel {
	public static Cell[][] data = new Cell[13][16];
	public static int cellRow;
	public static int cellColumn;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String input = "";
		
		for (int idx = 0; idx <= 12; idx++) {
			for (int x = 0; x <= 15; x++) {
				data[idx][x] = new Cell("       ");
			}
		}
		
		spreadSheet();
		
		while (!input.equals("quit")) {			
			System.out.println("Input: ");
			input = scan.nextLine();
			Commands.cellInput(input);
			Commands.cellSpaces(input);
			Commands.commandType(input);
			spreadSheet();
		}
	}
	
	public static void spreadSheet() {
		dashes();
		System.out.print("|       ");
		
		for (int idx = 1; idx <= 16; idx++) {
			System.out.print("|   ");
			System.out.print(((char) (idx + 64)));
			System.out.print("   ");
		}
		System.out.print("|");
		System.out.println();
		dashes();

		
		for (int idx = 1; idx <= 12; idx++) {
			if (idx < 10) {
				System.out.print("||  " + idx + "  |");
				for (int x = 1; x <= 15; x++) {
					System.out.print("|" + Commands.cellSpaces(data[idx-1][x-1].toString()));
				}
				System.out.println();
				dashes();
			}
		}
	}
	
	public static void dashes() {
		for (int idx = 0; idx <= 135; idx++) {
			System.out.print("-");
		}
		System.out.println();
	}

}
