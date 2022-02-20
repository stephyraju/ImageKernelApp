package ie.gmit.dip;

// Importing the utility classes
import java.awt.image.BufferedImage; 
import java.io.File; 
import java.io.IOException;
import java.util.List;
import java.util.Scanner; 

import javax.imageio.ImageIO;

public class Menu {
	
	private static Scanner s;
	public Menu() {
		s = new Scanner(System.in); // Create a Scanner object
	}

	public void start() throws IOException {
		
		Kernel kernal = Kernel.SHARPEN;
		showHeader(); // Display header once
		while (true) { // keep running
			
			showMenu();// Display menu options
			int choice = Integer.parseInt(s.next());//String input from user
			
			switch (choice) {
			case 1:// Enter Image File Detailsto apply filter
				System.out.println(ConsoleColour.BLUE_BOLD);
				System.out.println("Enter File Details");
				System.out.println("******************");
				
				try {
					BufferedImage image = ImageIO.read(new File(FileInput.fileInput())); // get the file input from user
//					System.out.println(image); 
					
					boolean found = false;
					
					System.out.println(ConsoleColour.PURPLE_BOLD);
					System.out.println("Select A Filter"); // Kernel Filters to select
					System.out.println("*****************");
					List<Kernel> filterList = showFilterData();
					
					
					System.out.println(ConsoleColour.CYAN_BOLD);
					System.out.println("Enter Selected Filter :");
					
									
					String filter = s.next().toUpperCase();
					System.out.println(ConsoleColour.YELLOW_BOLD);
					System.out.println("You Selected: " + filter); // Display the selected filter
					
					
					for (Kernel item : filterList) { // Getting the selected kernel to apply
					    if (filter.equals(item.toString())) {
					         found = true;
					         kernal = item; 
					         break;
					   }
								
					}
					
					if (!found) { // if filter entered is wrong
						System.out.println(ConsoleColour.RED_BOLD);
						System.out.println("The items you  selected: " + filter + " not found!");
						
					}
					
					ImageConvolution.readImage(image, kernal, FileInput.fileDirectoryPath);  // Processing the image by applying selected filter
					
				} catch (Exception e) {
					// error display in fileinput class
				}
				
				break;
				
			case 2: // Display available filters
				
				System.out.println(ConsoleColour.YELLOW_BOLD);
				System.out.println("***** Available Filters *****");
				showFilterData();
				
				System.out.println(ConsoleColour.RED_BOLD);
				System.out.println("If you like to apply any of these filters to your images select option 1 and proceed");
				break;

			case 3:
				System.out.println(ConsoleColour.RED_BOLD);
				System.out.println("Quit"); //Quitting
				break;
				
			default:
				System.out.println(ConsoleColour.RED_BOLD);
				System.out.print("Please enter a valid option");
				break;
			}

		}
	}

	// Method to displaythe list of Kernal Names in numberd list
	private List<Kernel> showFilterData() {
		List<Kernel> filterList = java.util.Arrays.asList(Kernel.values());

		int count = 1;
		for(Kernel value : filterList) {
			System.out.println(count + ") " + value);
			count++;
		}
		return filterList;
	}

	// Method to display header
	public static void showHeader() {

		System.out.println(ConsoleColour.WHITE_BRIGHT);
		System.out.println("***************************************************");
		System.out.println("* GMIT - Dept. Computer Science & Applied Physics *");
		System.out.println("*                                                 *");
		System.out.println("*           Image Filtering System V0.1           *");
		System.out.println("*     H.Dip in Science (Software Development)     *");
		System.out.println("*                                                 *");
		System.out.println("***************************************************");
		
	}
	// Method to display menu options
		public static void showMenu() {
		System.out.println(ConsoleColour.PURPLE_BOLD);
		System.out.println("1) Enter Image Details"); // Ask user to specify the file to process. 
		System.out.println("2) Display Available Filters"); // Display all the filters
		System.out.println("3) Quit"); //Terminate
		System.out.println("\nSelect Option [1-3]>");
	}

	
}
