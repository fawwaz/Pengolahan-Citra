package myutilities;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class MyConvolver {

	// Ukuranya belum 9 biji
	private int[] dx = {1 ,0 ,-1, 1, 0,-1, 1, 0,-1};
	private int[] dy = {-1,-1,-1, 0, 0, 0, 1, 1, 1};
	private float[][] convolver;
	public static final int SIZE = 5;
	public static final int OFFSET = (SIZE / 2);
	private float factor;
	BufferedImage image;
	
	public MyConvolver(){
		convolver = new float[SIZE][SIZE];
		factor=(float) 1.0;
	}
	
	
	public void setConvolver(float[][] input_convolution){
		for (int i = 0; i < input_convolution.length; i++) {
			for (int j = 0; j < input_convolution[i].length; j++) {
				convolver[i][j] = input_convolution[i][j];
			}
		}
	}
	
	public void setImage(BufferedImage image){
		this.image = image;
	}
	
	public void setfactor(Float f){
		factor = f;
	}
	
	public BufferedImage convolve(){
		int halfmatrix = OFFSET;
		BufferedImage retval = new BufferedImage((MyUtil.width - (2 * OFFSET)), (MyUtil.height- (2 * OFFSET)), BufferedImage.TYPE_INT_RGB); // 2* offset karena di kiri dan kanan
		int startx = halfmatrix,starty =halfmatrix,endx = (MyUtil.width-halfmatrix), endy = (MyUtil.height-halfmatrix);
		for (int y = starty; y < endy; y++) {
			for (int x = startx; x < endx; x++) {
				
				
				short[][] 	reds = new short[5][5],
							greens = new short[5][5],
							blues = new short[5][5];
				
				for (int i = 0; i < SIZE; ++i) {
					int tempy = y + (i-2);
					for (int j = 0; j < SIZE; ++j) {
						int tempx = x + (j-2);
						Color c = new Color(image.getRGB(tempx, tempy));
						reds[i][j] = (short) c.getRed();
						greens[i][j] = (short) c.getGreen();
						blues[i][j] = (short) c.getBlue();
					}
				}

				
				int red = FindNewColor(reds);
				int green = FindNewColor(greens);
				int blue = FindNewColor(blues);
				
				retval.setRGB(x-halfmatrix, y-halfmatrix, getintfromRGB(red, green, blue));
			}
			
		}
		
		return retval;
	}
	
	
	public BufferedImage getCombinedImages(ArrayList<BufferedImage> images){
		BufferedImage retval = new BufferedImage((MyUtil.width - (2 * OFFSET)), (MyUtil.height- (2 * OFFSET)), BufferedImage.TYPE_INT_RGB); // 2* offset karena di kiri dan kanan
		for (BufferedImage image : images) {
			for (int i = 0; i < image.getHeight(); i++) {
				for (int j = 0; j < image.getWidth(); j++) {
					Color new_color = new Color(image.getRGB(j,i));
					Color old_color = new Color(retval.getRGB(j,i));
					
					
					int newred = CheckColorRange(old_color.getRed() + new_color.getRed());
					int newgreen = CheckColorRange(old_color.getGreen() + new_color.getGreen());
					int newblue = CheckColorRange(old_color.getBlue() + new_color.getBlue());
					
					retval.setRGB(j,i,getintfromRGB(newred,newgreen,newblue));
				}
			}
		}
		return retval;
	}
	/* *
	 * Private Functions
	 * */
	private int FindNewColor(short[][] colors){
		float retval=0;
		for (int i = 0; i < colors.length; i++) {
			for (int j = 0; j < colors[i].length; j++) {
				retval = retval + (colors[i][j] * convolver[i][j]);
			}
		}
		retval = (int) (retval * factor);
		
		if(retval>255){
			retval = 255;
		}else if(retval < 0){
			retval = 0;
		}
		return (int) retval;
	}
	
	private int getintfromRGB(int red,int green, int blue){
		
		red 	= (red << 16) & 0x00FF0000;
		green 	= (green << 8) & 0x0000FF00;
		blue 	= blue & 0x000000FF;
		
		return 0xFF000000 |red|green|blue;
	}
	
	private int CheckColorRange(int color){
		if(color>255){
			return 255;
		}else if(color<0){
			return 0;
		}else{
			return color;
		}
	}
}
