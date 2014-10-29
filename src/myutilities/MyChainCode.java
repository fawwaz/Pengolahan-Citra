package myutilities;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import myutilities.helper.*;

public class MyChainCode {
	
	public BufferedImage image;
//	public BufferedImage retimage = new BufferedImage(MyUtil.width, MyUtil.height, BufferedImage.TYPE_INT_RGB);
	public ArrayList<Gambar> gambars = new ArrayList<Gambar>();	
//	public String[][] jalur;
	public int w, h;
	
	
	private int[] dx_chain ={1 ,1 ,0 ,-1,-1,-1, 0, 1};
	private int[] dy_chain ={0 ,-1,-1,-1, 0, 1, 1, 1};
	private boolean[][] visited;
	
	public void CountBoundingBox(){
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if(isblack(j, i)){
					if(!visited[i][j]){
						Gambar g = new Gambar();
						BFS(j, i, g);
						gambars.add(g);
						//jalur[g.y1][g.x1] = "$";
						//jalur[g.y2][g.x2] = "$";
					}
				}
			}
		}
	}
	
	public void SetInputImage(BufferedImage image){
		gambars.clear();
		w = image.getWidth();
		h = image.getHeight();
		this.image = image;
		
		visited = new boolean[MyUtil.height][MyUtil.width];
		for (int i = 0; i < visited.length; i++) {
			Arrays.fill(visited[i], false);
		}
	}
	
	public ArrayList<Gambar> GetGambars(){
		return gambars;
	}
	
	public String GenerateChainCode(){
		String retval="";
		return retval;
	}
	
	
	/**
	 * Private Functions
	 * */
	
	private boolean iswhite(int x, int y){
		Color a = new Color(image.getRGB(x, y));
		return a.getRed() == 255;
	}

	private boolean isblack(int x, int y){
		Color a = new Color(image.getRGB(x, y));
		return a.getRed() == 0;
	} 
	
	public void BFS(int _x, int _y, Gambar g) {
		Queue<Point> q = new LinkedList<Point>();
		visited[_y][_x] = true;
		q.add(new Point(_x, _y));
		
		g.SetStartPoint(_x,_y);
		while (!q.isEmpty()) {
			Point p = q.remove();
			visited[p.y][p.x] = true;
			
			// System.out.println("X: "+p.x+" Y : "+p.y);
			//grayimage.setRGB(p.x, p.y, RGBtointpixel(256, 256, 256));
			//System.out.println("Dalem X :"+p.x+" Y :"+p.y);
			
			// add to queue
			putifvalid(p.x + 1, p.y - 1, q , g);
			putifvalid(p.x + 1, p.y    , q , g);
			putifvalid(p.x + 1, p.y + 1, q , g);
			putifvalid(p.x    , p.y - 1, q , g);
			putifvalid(p.x    , p.y    , q , g);
			putifvalid(p.x    , p.y + 1, q , g);
			putifvalid(p.x - 1, p.y - 1, q , g);
			putifvalid(p.x - 1, p.y    , q , g);
			putifvalid(p.x - 1, p.y + 1, q , g);
		}

	}

	private void putifvalid(int x, int y, Queue<Point> q, Gambar g) {
		if (isvalid(x, y)) {
			g.SetMinBorder(x, y);
			g.SetMaxBorder(x, y);
			//grayimage.setRGB(x, y, RGBtointpixel(0, 0, 0));
			visited[y][x] = true;
			q.add(new Point(x, y));
		}
	}

	private boolean isvalid(int x, int y) {
		if(x > 0 && x < w && y > 0 && y < h){
			Color c = new Color(image.getRGB(x, y));
			if(c.getRed() == 0 && !visited[y][x]){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}

	}

	private int RGBtointpixel(int r, int g, int b) {
		return ((r & 0x0ff) << 16) | ((g & 0x0ff) << 8) | (b & 0x0ff);
	}
	
}
