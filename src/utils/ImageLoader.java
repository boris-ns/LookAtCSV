package utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {

	public static BufferedImage loadBufferedImage(String path) {
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			System.out.print("Failed to load image at this path: " + path);
			e.printStackTrace();
		}
		
		return image;
	}
	
	public static Image loadImage(String path) {
		Image img = null;
		
		try {
			img = ImageIO.read(new File(path));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		
		return img;
	}
}