import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

public class exercise1 {

	static String [][] arr;

	public static String generateRandomCharacters(int times) {
		Random rand = new Random();
		int i = rand.nextInt(122-65) + 65 ;
		if(i >= 91 && i <= 96)
			return generateRandomCharacters(times);
		if (times >= 1) {
        return Character.toString((char) i) + generateRandomCharacters(times - 1);
    	}
    	return "";
	}

	public static void printArray(String arr[][], int y) {
		int counter = 1;
		for(String[] arr1: arr) {
			for(String arr2: arr1) {
				if(counter == y) {
					counter = 1;
					System.out.println(arr2 + " ");
				}
				else {
					counter++;
					System.out.print(arr2 + " ");
				}
			}
		}
	}

	public static int[] generateTable() {
		int[] dimensions = new int[2];
		boolean loop = true;
		do {
			try {
			Scanner in = new Scanner(System.in); Scanner pause = new Scanner(System.in);
			System.out.println("Enter the table dimension for x and y");
			System.out.print("x: "); int x = in.nextInt();
			System.out.print("y: "); int y = in.nextInt();
			arr = new String [x][y];
			dimensions[0] = x; dimensions[1] = y;
			for(int i = 0; i < x; i++)
				for(int j = 0; j < y; j++)
					arr[i][j] = generateRandomCharacters(3);
			System.out.println("Tables has been populated! Press any key to continue..."); 
			pause.nextLine();
			loop = false;
			} catch (Exception e) {
				System.out.println("Invalid input!");
			}
		}while(loop);
		return dimensions;
	}

	public static void editCell(int i[] ) {
		System.out.println("Table dimensions X: " + i[0] + " Y: " + i[1] + " | Index starting value is 1");
		int x = 0; int y = 0;
		String cellString = "";
		boolean loop = true;
		do {
			boolean innerLoop = true;
			Scanner choice = new Scanner(System.in); Scanner input = new Scanner(System.in);
			try {
				System.out.print("Enter value for row: "); x = choice.nextInt();
				System.out.print("Enter value for column: "); y = choice.nextInt();
				if(x > i[0] || y > i[1] || x < 0 || y < 0) {
						System.out.println("Invalid dimension!"); System.out.println();
				}
				else {
					do {
						try {
							System.out.print("Input anything: ");
							cellString = input.nextLine();
							if(!cellString.equals("")) {
								cellString = cellString.replaceAll("\\s+","");
								arr[x-1][y-1] = cellString;
								System.out.println("Edit success!"); System.out.println();
								loop = false;
								innerLoop = false;
							}
							else
								System.out.println("Input is empty!");
						} catch(Exception e) {
							System.out.println("Invalid input!");
						}
					}while(innerLoop);
				}
			} catch(Exception e) {
				System.out.println("Invalid input!");
			}
		}while(loop);
	}

	public static void searchCell(int dimension[]) {
		String searchString = "";
		Scanner input = new Scanner(System.in);
		boolean loop = true; boolean searchStringCounted = false;
		do {
			try {
				System.out.print("Input anything: ");
				searchString = input.nextLine();
				if(!searchString.equals("")) {
					boolean innerLoop = true;
					searchString = searchString.replaceAll("\\s+","");
					for(int i = 0; i < dimension[0]; i++) {
						for(int j = 0; j < dimension[1]; j++) {
								if(arr[i][j].contains(searchString)) {
									if(arr[i][j].length() < searchString.length())
										continue;
									else {
										String str = arr[i][j];
										String findStr = searchString;
										int occurence = str.split(findStr, -1).length-1;
										// System.out.println(str.split(findStr, -1).length);
										// System.out.println(Arrays.toString(str.split(findStr, -1)));
										System.out.println("Occurence of " + findStr + " on [" + i + ", " + j + "] is " + occurence + " times");
										searchStringCounted = true;
									}
								}
								else
									continue;
						}
						if(!innerLoop)
							break;
					}
					if(!searchStringCounted)
						System.out.println("No occurence of the input String");
					loop = false;
				}
				else
					System.out.println("Input is empty!");
			} catch(Exception e) {
				System.out.println("Invalid input!");
				loop = false;
			}
		}while(loop);
	}

	public static void main(String args[]) {
		int[] dimensions = generateTable();
		boolean loop = true;
		do {
			int i=0;
			Scanner choice = new Scanner(System.in);
			try {
				System.out.print("[1]Search \n[2]Edit \n[3]Print \n[4]Reset \n[5]Exit\n\nInput your choice: ");
				i = choice.nextInt(); System.out.println();
				if(i > 5) {
					System.out.println("Invalid choice!"); System.out.println();
				}
				else {
					if(i == 1) { //Search
						searchCell(dimensions); System.out.println();
					}
					if(i == 2) //Edit
						editCell(dimensions);
					if(i == 3) { //Print
						printArray(arr, dimensions[1]); 
						System.out.println();
					}
					if(i == 4) //Reset
						dimensions = generateTable();
					if(i == 5) { //Exit
						loop = false; 
						System.out.println("Bye!");
					}
				}
			} catch(Exception e) {
				System.out.println("Invalid number!");
			}
		}while(loop);
	}
}