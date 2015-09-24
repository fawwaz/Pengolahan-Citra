package myutilities;

import myutilities.helper.*;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import weka.classifiers.misc.SerializedClassifier;
import weka.classifiers.trees.J48;

import myutilities.helper.*;

public class MyChainCode {
	
	public BufferedImage image;
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
	
	public String getChainCodeInfo(){
		String retval = "";
		
		makeChainCode();
		retval = getInfos();
	
		return retval;
	}
	
	public ArrayList<Gambar> getGambars(){
		makeChainCode();
		return gambars;
	}
	
	public String RecognizeNumber(){
		String retval = "";
		double[][] the_gambars = new double[gambars.size()][81]; 
		
		Learner learner = new Learner();
		for (int i = 0; i < gambars.size(); i++) {
			learner.CalculatePercentage(gambars.get(i));
			the_gambars[i] = learner.properties;
		}
		SerializedClassifier classifier = new SerializedClassifier();
		classifier.setModelFile(new File("output.model"));
		
		//classifier.classifyInstance(arg0)
		
		return retval;
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
	
	private void makeChainCode(){
		for (int i = 0; i < gambars.size(); i++) {
			generateChainCode(gambars.get(i));
		}
	}
	
	private void generateChainCode(Gambar g){
		int curx = g.startx;
		int cury = g.starty;
		int blackcode = 0, wx,wy,bx,by;
		int whitecode = blackcode + 1;
		int curcode = 0, prevcode = 0; // default ke kanan
		
		do{
			
			for (int i = 0; i < 8; i++) {
				blackcode = (prevcode + i    ) % 8;
				whitecode = (prevcode + i + 1) % 8;
				
				// Konversi code ke koordinat
				wx = curx + dx_chain[whitecode];
				wy = cury + dy_chain[whitecode];
				bx = curx + dx_chain[blackcode];
				by = cury + dy_chain[blackcode];
				//System.out.println("Cur: " +curx + " " + cury + " W :" + wx +" "+ wy + " B :" + bx +" "+ by +" White ?: " + iswhite(wx, wy) + " Black ? :"+ isblack(bx, by));
				//print_w_b(wx, wy, bx, by);
				
				Color c = new Color(image.getRGB(wx, wy));
				//System.out.println("white"+c.getRed());
				
				if(iswhite(wx, wy)&&isblack(bx, by)){
					System.out.print(blackcode);
					g.chaincode = g.chaincode + blackcode;
					curx = bx;
					cury = by;
										
					//jalur[by][bx] = String.valueOf(blackcode);
					
					g.putchaincode(curx, cury, blackcode);
					
					prevcode = blackcode;
					break;
				}
			}
		}while((curx!=g.startx) || (cury != g.starty));
		System.out.println("===");
	}
	
	private String getInfos(){
		String retval = "";
		for (int i = 0; i < gambars.size(); i++) {
			retval = retval + gambars.get(i).getInfo();
		}
		return retval;
	}
	
	
}
