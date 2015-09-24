package myutilities;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyThinner {
	
	BufferedImage image;
	int w,h;
	final static int[][] nbrs = {{0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1},
        {-1, 1}, {-1, 0}, {-1, -1}, {0, -1}};
 
    final static int[][][] nbrGroups = {{{0, 2, 4}, {2, 4, 6}}, {{0, 2, 6},
        {0, 4, 6}}};
    static List<Point> toWhite = new ArrayList<>();
	
	
	public BufferedImage getZhangSuen(){ 
		
		boolean firstStep = false;
		boolean hasChanged = false;
		 do {
	            hasChanged = false;
	            firstStep = !firstStep;
	 
	            for (int r = 1; r < h - 1; r++) {
	                for (int c = 1; c < w - 1; c++) {
	 
	                    if (!isblack(c,r))
	                        continue;
	 
	                    int nn = numNeighbors(r, c);
	                    if (nn < 2 || nn > 6)
	                        continue;
	 
	                    if (numTransitions(r, c) != 1)
	                        continue;
	 
	                    if (!atLeastOneIsWhite(r, c, firstStep ? 0 : 1))
	                        continue;
	 
	                    toWhite.add(new Point(c, r));
	                    hasChanged = true;
	                }
	            }
	 
	            for (Point p : toWhite)
	            	writeWhite(p.x, p.y);
	            toWhite.clear();
	 
	        } while (hasChanged || firstStep);
	 
	        return image;
	}
	
	
	public void SetInputImage(BufferedImage image){
		
		w = image.getWidth();
		h = image.getHeight();
		this.image = image;
		
	}
	
	/* *
	 * Private Functions
	 * */
	
	private int RGBtointpixel(int r, int g, int b) {
		return ((r & 0x0ff) << 16) | ((g & 0x0ff) << 8) | (b & 0x0ff);
	}
	
	private boolean iswhite(int x, int y){
		Color a = new Color(image.getRGB(x, y));
		return a.getRed() == 255;
	}

	private boolean isblack(int x, int y){
		Color a = new Color(image.getRGB(x, y));
		return a.getRed() == 0;
	}
	
	private void writeWhite(int x,int y){
		image.setRGB(x, y, RGBtointpixel(255, 255, 255));
	}
	
	private int numNeighbors(int r, int c) {
        int count = 0;
        for (int i = 0; i < nbrs.length - 1; i++)
        	if(isblack(c+nbrs[i][0],r+nbrs[i][1]))
                count++;
        return count;
    }
	
	private int numTransitions(int r, int c) {
        int count = 0;
        for (int i = 0; i < nbrs.length - 1; i++)
        	if(iswhite(c + nbrs[i][0], r + nbrs[i][1])){
        		if(isblack(c + nbrs[i + 1][0], r + nbrs[i + 1][1])){
                    count++;
        		}
            }
        return count;
    }
	
	private boolean atLeastOneIsWhite(int r, int c, int step) {
        int count = 0;
        int[][] group = nbrGroups[step];
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < group[i].length; j++) {
                int[] nbr = nbrs[group[i][j]];
                if (iswhite(c + nbr[0], r + nbr[1])){
                    count++;
                    break;
                }
            }
        return count > 1;
    }
	
	

}
