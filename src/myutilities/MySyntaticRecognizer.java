package myutilities;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MySyntaticRecognizer {
	public static final int b_black = 0;	// binary black
	public static final int r_black = 0;	// rgb black
	public static final int b_white = 1; 	// binary white
	public static final int r_white = 255; 	// rgb white
	private int[][] temparr = new int[3][3];
	BufferedImage image;
	int w,h;
	
	public void setInputImage(BufferedImage image){
		this.image = image;
		this.w = image.getWidth();
		this.h = image.getHeight();
	}
	
	public BufferedImage getPlat(){
		//BufferedImage retimg = deepCopy(MyUtil.activeimg);
		int framewidth = 125, frameheight = 38;
		BufferedImage retimg = new BufferedImage(framewidth+2, frameheight+2, BufferedImage.TYPE_INT_RGB);
		
		//int framewidth = 20, frameheight = 25;
		int counter = 0,MaxCounter = 0, step = 2;
		int x_max=0,y_max=0,prevcolor,currcolor;		
		
		for (int y = 0; y < h-frameheight; y++) {
			for (int x = 0; x < w-framewidth; x++) {
				counter =0;
				// Hitung total perubahan di dalam satu kotak
				for (int i = y; i+1 < y+frameheight; i+=step) {
					for (int j = x; j+1 < x+framewidth; j+=step) {
						if(isWhite(j, i) && isblack(j+1, i)){
							counter++;
						}
					}
				}
				
				// Cek apakah counter di koordinat tersebut paling banyak
				if(counter>MaxCounter){
					MaxCounter = counter;
					x_max = x;
					y_max = y;
				}
				
			}
		}
		
		// Buat kotak warna merah
		for (int y = y_max; y < y_max+frameheight; y++) {
			for (int x = x_max; x < x_max+framewidth; x++) {
				retimg.setRGB(y-y_max, x-x_max, MyUtil.activeimg.getRGB(y,x));
				//if(x<x_max||y<y_max||x>x_max+framewidth||y>y_max+frameheight){
					//retimg.setRGB(x, y, Color.RED.getRGB());
				//}
			}
		}
		//System.out.println("XMax :"+x_max + " YMAX : "+y_max);
		
		return retimg;
	}

	public BufferedImage getOnePixelWidthImage() {
		for (int i = 1; i < h-1; i++) {
			for (int j = 1; j < w-1; j++) {
				copy_to_temp(j, i);
				if(isDeleteable(j,i)){
					writeWhite(j, i);
				}
				
				for (int z = 0; z < temparr.length; z++) {
					for (int k = 0; k < temparr[z].length; k++) {
						temparr[z][k] = 0;
					}
				}
			}
		}
		return image;
	}
	
	/*
	 * DEBUG
	 * */
	
	public void debug(){
		for (int i = 0; i < temparr.length; i++) {
			for (int j = 0; j < temparr[i].length; j++) {
				System.out.print(temparr[j][i] + " ");
			}
			System.out.print("==");
		}
		System.out.println();
	}
	

	/* *
	 * Private Functions
	 * */
	
	private boolean isDeleteable(int x, int y) {
		//System.out.println("X :" + x + " Y :" + y + "Count" + countNumofObject());
		if(countNumofObject()>1){
			return true;
		}else{
			return false;
		}
	}
	
	private void copy_to_temp(int x,int y){
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (isblack(x, y)) {
					temparr[(i+1)][(j+1)] = b_black;
				}else if(isWhite(x, y)){
					temparr[(i+1)][(j+1)] = b_white;
				}
			}
		}
		temparr[1][1] = b_white;
	}
	
	private int countNumofObject(){
		int counter = 1;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if(is_b_black(j, i)){
					bfs(j, i);
					counter ++;
					//System.out.println("Counter :" + counter);
					
				}
			}
		}
		return counter;
	}
	
	private void bfs(int x,int y){
		boolean[][] visited = new boolean[3][3];
		for (int i = 0; i < visited.length; i++) {
			Arrays.fill(visited[i], false);
		}
		Queue<Location> queue = new LinkedList<Location>();
		queue.add(new Location(x, y));
		while(!queue.isEmpty()){
			Location location = queue.remove();
			visited[location.y][location.x] = true;
			temparr[location.y][location.x] = b_white;
			
			
			putIfValid(location.x +1, location.y-1, queue, visited);
			putIfValid(location.x +1, location.y  , queue, visited);
			putIfValid(location.x +1, location.y+1, queue, visited);
			putIfValid(location.x  , location.y -1, queue, visited);
			putIfValid(location.x  , location.y +1, queue, visited);
			putIfValid(location.x -1, location.y -1, queue, visited);
			putIfValid(location.x -1, location.y  , queue, visited);
			putIfValid(location.x -1, location.y +1, queue, visited);
		}
	}
	
	
	private void putIfValid(int x,int y,Queue<Location> q,boolean[][] visited){
		if(isvalid(x, y, visited)){
			q.add(new Location(x, y));
		}
	}
	
	private boolean isvalid(int x, int y,boolean[][] visited){
		return 0<=y && y < visited.length && 0<=x && x<visited.length && !visited[y][x] && is_b_black(x, y); 
	}
	
	private int RGBtointpixel(int r, int g, int b) {
		return ((r & 0x0ff) << 16) | ((g & 0x0ff) << 8) | (b & 0x0ff);
	}
	
	private boolean is_b_white(int x,int y){
		return temparr[y][x] == b_white;
	}
	private boolean is_b_black(int x,int y){
		return temparr[y][x] == b_black;
	}
	
	
	private boolean isWhite(int x,int y){
		Color a = new Color(image.getRGB(x, y));
		return a.getRed() == r_white;
	}
	
	private boolean isblack(int x, int y){
		Color a = new Color(image.getRGB(x, y));
		return a.getRed() == r_black;
	}
	
	private void writeWhite(int x,int y){
		image.setRGB(x, y, RGBtointpixel(255, 255, 255));
	}
	
	private int getGray(int x,int y){
		Color a = new Color(image.getRGB(x, y));
		return a.getRed();
	}
	
	/* * 
	 * Static Functions
	 * */
	static BufferedImage deepCopy(BufferedImage bi) {
		ColorModel cm = bi.getColorModel();
		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		WritableRaster raster = bi.copyData(null);
		return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}
	/* *
	 * Private Class
	 * */
	private static class Location {
		private final int x;
		private final int y;

		public Location(int x, int y) {
			this.y = y;
			this.x = x;
		}
	}
}

