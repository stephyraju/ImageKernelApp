package ie.gmit.dip;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileInput {
//	private String filename;
	public static File fileDirectoryPath;

	// File name: gmit-rgb.png
	// Directory path: //Users/stephyraju/eclipse-workspace/ImageKernelsApp/src/images

	// Method to enter the file and directory
	public static String fileInput() throws IOException {
		Scanner s = new Scanner(System.in);
		System.out.println(ConsoleColour. CYAN_BOLD);
		System.out.println("Enter File Name: "); 

		String filename = s.nextLine(); // Get the file name
		System.out.println(ConsoleColour. CYAN_BOLD);
		System.out.println("Enter Directory Path: ");

		String directoryPath = s.nextLine(); // Get directory path

		File dir = new File(directoryPath); //Create a new directory instance

		// Checking if the directory exist
		if (dir.isDirectory()) {
			System.out.println(ConsoleColour.YELLOW_BOLD);
			System.out.println("Directory exists");
		} else {
			System.out.println(ConsoleColour.RED_BOLD_BRIGHT);
			System.out.println("Directory doesn't exist!!");
		}
		fileDirectoryPath = dir;
		
		File file = new File(dir + "/" + filename); //Create a new file path instance 

		// Checking if file exists
		if (file.exists()) {
			System.out.println(ConsoleColour.GREEN_BOLD_BRIGHT);
			System.out.println("Getting File...");
			
			//www.geeksforgeeks.org/file-getabsolutepath-method-in-java-with-examples/
			return file.getAbsolutePath();
		} else {
			System.out.println(ConsoleColour.RED_BOLD_BRIGHT);
			System.out.println("Invalid File name!!");

			return null;

		}

	}

}


