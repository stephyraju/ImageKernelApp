package ie.gmit.dip;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class ImageConvolution {

	public static Kernel k;
	private static float bias = 0f;
	private static float multiFactor = 1.0f;

	// Read in an image and convert to a BufferedImage

	public static void readImage(BufferedImage image, Kernel kernel, File directoryPath) throws IOException {

		k = kernel;

		writeImage(image, directoryPath);
	}

	// Method to convolute
	
	// http://tech.abdulfatir.com/2014/05/kernel-image-processing.html?m=1
	// https://www.codingame.com/playgrounds/2524/basic-image-manipulation/filtering

	private static BufferedImage convolutionFilter(BufferedImage image) {

		BufferedImage input = image;

		// Get width and height of Image

		int height = image.getHeight();
		int width = image.getWidth();

		BufferedImage output = new BufferedImage(width, height, input.getType());

		
		int filterLength = k.getKernels().length;

		// System.out.println("filterLength : " + filterLength );

		// Loop over each pixel
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {

				float red = 0f, green = 0f, blue = 0f, alpha = 0f;
				int pixel = 0;

				// Loop over the Kernel
				for (int i = 0; i < filterLength; i++) {
					for (int j = 0; j < filterLength; j++) {

						// Calculating the pixel's X and Y coordinates to be multiplied by the current
						// kernel element
						int imgX = (x - filterLength / 2 + i + width) % width;
						int imgY = (y - filterLength / 2 + j + height) % height;

						int RGB = image.getRGB(imgX, imgY);

						int A = (RGB >> 24) & 0xFF;
						int R = (RGB >> 16) & 0xff; // Red Value
						int G = (RGB >> 8) & 0xff; // Green Value
						int B = (RGB) & 0xff; // Blue Value

						// The RGB is multiplied with current kernel element and added on to the
						// variables red, blue and green
						alpha += (A * (k.getKernels())[i][j]);
						red += (R * (k.getKernels())[i][j]);
						green += (G * (k.getKernels())[i][j]);
						blue += (B * (k.getKernels())[i][j]);
					}

				}
				// Output values for alpha,red, green and blue
				int outA, outR, outG, outB;

				// If the value exceeds 255, it is trimmed to 0 and 255.
				outA = Math.min(Math.max((int) (alpha + bias), 0), 255);
				outR = Math.min(Math.max((int) (red * multiFactor), 0), 255);
				outG = Math.min(Math.max((int) (green * multiFactor), 0), 255);
				outB = Math.min(Math.max((int) (blue * multiFactor), 0), 255);

				// repalace rgb value with new values
				pixel = pixel | (outA << 24);
				pixel = pixel | (outR << 16);
				pixel = pixel | (outG << 8);
				pixel = pixel | (outB);

				// set the ouput colours
				output.setRGB(x, y, new Color(outR, outG, outB).getRGB());
			}

		}

		System.out.println(ConsoleColour.GREEN_BOLD_BRIGHT);
		System.out.println("Converting the image....");
		return output;
	}

	// Method to write out the file in PNG format
	private static void writeImage(BufferedImage image, File directoryPath) throws IOException {
		File file = null;
		String outputImageName = outputFileName();
		BufferedImage outputImage = convolutionFilter(image);
		// write output image

		try {
			file = new File(directoryPath + "/" + outputImageName);
			ImageIO.write(outputImage, "png", file);
			System.out.println(ConsoleColour.CYAN_BOLD);
			System.out.println("File saved as " + directoryPath + "/" + outputImageName);
		} catch (IOException e) {
			System.out.println(ConsoleColour.RED_BOLD_BRIGHT);
			System.out.println("Error in writing file. Please try agin");
		}
	}
	
	// Method to get output file name for the the convoluted image
	
	private static String outputFileName() {
		Scanner s = new Scanner(System.in);
		System.out.println(ConsoleColour. CYAN_BOLD);
		System.out.println("Enter Output File Name: ");
		String outputFilename = s.nextLine();
		return outputFilename;
		
	}

}
