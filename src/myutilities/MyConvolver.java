package myutilities;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class MyConvolver {

	// Ukuranya belum 9 biji
	private int[] dx = {1 ,0 ,-1, 1, 0,-1, 1, 0,-1};
	private int[] dy = {-1,-1,-1, 0, 0, 0, 1, 1, 1};
	private int[] convolver = new int[9];
	BufferedImage image;
	
	public void setConvolver(int[] input_convolution){
		for (int i = 0; i < input_convolution.length; i++) {
			convolver[i] = input_convolution[i];
		}
	}
	
	public void setImage(BufferedImage image){
		this.image = image;
	}
	
	public BufferedImage convolve(){
		BufferedImage retval = new BufferedImage(MyUtil.width-2, MyUtil.height-2, BufferedImage.TYPE_INT_RGB);
		
		int startx = 1,starty =1,endx = (MyUtil.width-1), endy = (MyUtil.height-1);
		for (int y = starty; y < endy; y++) {
			for (int x = startx; x < endx; x++) {
				
				Color[] arround = new Color[9];
				short[] reds = new short[9],greens = new short[9], blues = new short[9];
				
				// Get color arround pixel
				for (int k = 0; k < 9; k++) {
					arround[k] = new Color(image.getRGB(x+dx[k], y+dy[k]));
					
					reds[k] = (short) arround[k].getRed();
					greens[k] = (short) arround[k].getGreen();
					blues[k] = (short) arround[k].getBlue();
				}
				
				int red = FindNewColor(reds);
				int green = FindNewColor(greens);
				int blue = FindNewColor(blues);
				
				retval.setRGB(x-1, y-1, getintfromRGB(red, green, blue));
				
				
			}
			
		}
		
		return retval;
	}
	
	/* *
	 * Private Functions
	 * */
	private int FindNewColor(short[] colors){
		int retval=0;
		for (int i = 0; i < colors.length; i++) {
			retval =  (int) (retval + convolver[i] * colors[i]);
		}
		return retval;
	}
	
	private int getintfromRGB(int red,int green, int blue){
		
		red 	= (red << 16) & 0x00FF0000;
		green 	= (green << 8) & 0x0000FF00;
		blue 	= blue & 0x000000FF;
		
		return 0xFF000000 |red|green|blue;
	}
	
}
