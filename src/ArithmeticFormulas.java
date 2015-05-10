
public class ArithmeticFormulas {
	public String originalFormula = "";
	public String cellAddress = "";
	
	//convert cell address
	//gets the number from cell address 
	//multiply and divide
	//add and subtract
	
	public static String[] parseParts (String s) {
		if (s.contains("(") && s.contains(")")) {
			String address = s.substring(0, s.indexOf(" "));
			String formula = s.substring(s.indexOf("(") + 1, s.lastIndexOf(")"));
			String parts[] = formula.split(" ");
			return parts;
		} else {
			return
		}
	}
		
	public static int[] convertAddress (String[] parts) {
		String s1 = parts[0];
		int[] indexes1 = new int[2];
		indexes1[0] = ((int)s1.charAt(0)) - 65;
		indexes1[1] = ((int)s1 .charAt(1)) - 48;
		
		String s2 = parts[2];
		int[] indexes2 = new int[2];
		indexes2[0] = ((int)s2.charAt(0)) - 65;
		indexes2[1] = ((int)s2.charAt(0)) - 48;
		
		return indexes1;
		return indexes2;
	}

}
