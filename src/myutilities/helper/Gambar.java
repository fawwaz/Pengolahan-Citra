package myutilities.helper;

import java.util.Arrays;

public class Gambar {
	public int xmin,xmax,ymin,ymax;
	public int offsetx,offsety,width,height;
	public int startx,starty;
	public String chaincode="";
	public String[] chaincodes = new String[9];
	public int x1,y1,x2,y2;
	
	public Gambar() {
		Arrays.fill(chaincodes,"");
		xmax = ymax = 0;
		xmin = ymin = 2000000000;
		
	}
	
	public void SetMinBorder(int x, int y){
		if(x<xmin){
			xmin = x;
		}
		if(y<ymin){
			ymin = y;
		}
		resetuppoint();
	}
	
	public void SetMaxBorder(int x,int y){
		if(x>xmax){
			xmax = x;
		}
		if(y>ymax){
			ymax = y;
		}
		resetuppoint();
	}
	
	public void SetStartPoint(int x,int y){
		startx = x; starty = y;
	}
	
	private void resetuppoint(){
		offsetx = xmin;
		offsety = ymin;
		width = xmax - xmin;
		height = ymax - ymin;
		
		// Setup x1
		x1 = xmin + width  / 3;
		y1 = ymin + height / 3;
		x2 = xmin + (width  * 2) / 3;
		y2 = ymin + (height * 2) / 3;
	}
	
	public void putchaincode(int x,int y, int code){
		// +---+---+---+
		// | 0 | 1 | 2 |
		// +---+---+---+
		// | 3 | 4 | 5 |
		// +---+---+---+
		// | 6 | 7 | 8 |
		// +---+---+---+
		int index_array=0;
		if(x>=xmin && x<x1){
			if(y>=ymin && y<y1){
				index_array = 0;
			}else if(y>=y1 && y<y2){
				index_array = 3;
			}else if(y>=y2 && y<=ymax){
				index_array = 6;
			}
		}else if(x>=x1 && x<x2){
			if(y>=ymin && y<y1){
				index_array = 1;
			}else if(y>=y1 && y<y2){
				index_array = 4;
			}else if(y>=y2 && y<=ymax){
				index_array = 7;
			}
		}else if(x>=x2 && x<=xmax){
			if(y>=ymin && y<y1){
				index_array = 2;
			}else if(y>=y1 && y<y2){
				index_array = 5;
			}else if(y>=y2 && y<=ymax){
				index_array = 8;
			}
		}
		//System.out.println("P :"+x+" "+y+" C :"+code + "==> Index :"+index_array);
		chaincodes[index_array] = chaincodes[index_array] + code;
	}
	
	public void printInfo(){
		System.out.println("Informasi gambar");
		//System.out.println("ID : "+id_gambar);
		System.out.println("Startx : "+startx + " Starty : "+starty);
		System.out.println("Width : "+width + " Height :"+height);
		System.out.println("xmin : " +xmin + " ymin : " +ymin);
		System.out.println("xmax : " +xmax + " ymax : " +ymax);
		System.out.println("X1 : "+x1+" Y1 : "+y1);
		System.out.println("X2 : "+x2+" Y2 : "+y2);
		System.out.println("Chain code :" + chaincode);
		for (int i = 0; i < chaincodes.length; i++) {
			System.out.println("Chaincode["+i+"] :"+chaincodes[i]);
		}
	}
	
	public String getInfo(){
		String retval = "";
		retval = retval + "Informasi Gambar : \n";
		retval = retval + "Startx  \t\t: "+startx+" Starty : "+starty+"\n";
		retval = retval + "Width \t\t: "+width + " Height : "+height+"\n";
		retval = retval + "Chain code \t: " + chaincode +"\n";
		return retval;
	}
	
	public String getCCode(){
		return chaincode;
	}
}
