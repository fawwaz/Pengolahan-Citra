package myutilities;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class MyEqualizer {

	public TreeMap<Short, Integer> oldred = new TreeMap<>(new myshortcomparator()),oldgreen = new TreeMap<>(new myshortcomparator()),oldblue = new TreeMap<>(new myshortcomparator());
	public TreeMap<Short, Short> redlut = new TreeMap<>(new myshortcomparator()),greenlut = new TreeMap<>(new myshortcomparator()),bluelut = new TreeMap<>(new myshortcomparator());	
	
	
	public BufferedImage equalize(){
		BufferedImage retimage = new BufferedImage(MyUtil.width, MyUtil.height, BufferedImage.TYPE_INT_RGB);
		// 1. Baca histogram
		ReadHistogram();
		// 2. Hitung LUT
		CalculateLut();
		// 3. Tulis image
		retimage = WriteImage();
		return retimage;
	}
	

	/**
	 * Private functions
	 * */
	private void ReadHistogram(){
		int w = MyUtil.width;
		int h = MyUtil.height;
		
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				int pixel_color = MyUtil.activeimg.getRGB(j, i);
				Color c = new Color(pixel_color);
				IncreaseOldHistogram(oldred, (short) c.getRed());
				IncreaseOldHistogram(oldgreen, (short) c.getGreen());
				IncreaseOldHistogram(oldblue, (short) c.getBlue());
			}
		}
	}
	
	private void CalculateLut(){
		StretchHistogram(oldred, redlut);
		StretchHistogram(oldgreen, greenlut);
		StretchHistogram(oldblue, bluelut);		
	}
	
	
	
	private BufferedImage WriteImage(){
		BufferedImage retimage = new BufferedImage(MyUtil.width, MyUtil.height, BufferedImage.TYPE_INT_ARGB);
		
		int w = MyUtil.width;
		int h = MyUtil.height;
		
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				int rgb_pixel = MyUtil.activeimg.getRGB(j, i);
				Color old_color = new Color(rgb_pixel);
				
				short new_red = redlut.get((short) old_color.getRed());
				short new_green = greenlut.get((short) old_color.getGreen());
				short new_blue = bluelut.get((short) old_color.getBlue());
				
				int new_color = getintfromRGB(new_red, new_green, new_blue);
				retimage.setRGB(j, i, new_color);
			}
		}
		
		return retimage;
	}
	
	/**
	 * Auxilary functions
	 * */
	
	private void IncreaseOldHistogram(TreeMap<Short, Integer> oldhistogram, Short key){
		int a = (oldhistogram.containsKey(key)) ? oldhistogram.get(key) : 0;
		oldhistogram.put(key, a+1);
	}
	
	
	public void StretchHistogram(TreeMap<Short, Integer> oldhistogram, TreeMap<Short, Short> lut){
		TreeMap<Short, Integer> tabelkumulatif = new TreeMap<>(new myshortcomparator());
		Integer tempcumulative = 0;
		
		for (Map.Entry<Short, Integer> entry : oldhistogram.entrySet()) {
			Short kodewarna = entry.getKey();
			tempcumulative = tempcumulative + entry.getValue();
			tabelkumulatif.put(kodewarna,tempcumulative);
		}
		
		for (Map.Entry<Short, Integer> entry : tabelkumulatif.entrySet()){
			float delta_atas = (float) (entry.getValue() - tabelkumulatif.get(tabelkumulatif.firstKey()));
			float delta_bawah = (float) (tabelkumulatif.get(tabelkumulatif.lastKey()) - tabelkumulatif.get(tabelkumulatif.firstKey()));
			//Short warnabaru = (short) (Math.round(delta_atas /delta_bawah * tabelkumulatif.lastKey()) + tabelkumulatif.firstKey());
			Short warnabaru = (short) Math.round(delta_atas /delta_bawah * 255) ;
			// System.out.println("atas" +delta_atas +" bawah "+delta_bawah+ " hasil : " + warnabaru);
			//System.out.println("atas :"+entry.getValue()+" bawah :" + tabelkumulatif.get(tabelkumulatif.lastKey()) + "hasil bagi :" + entry.getValue() * tabelkumulatif.get(tabelkumulatif.lastKey()));
			lut.put(entry.getKey(), warnabaru);
		}
	}
	
	private int getintfromRGB(int red,int green, int blue){
		
		red 	= (red << 16) & 0x00FF0000;
		green 	= (green << 8) & 0x0000FF00;
		blue 	= blue & 0x000000FF;
		
		return 0xFF000000 |red|green|blue;
	}
	
	class myshortcomparator implements Comparator<Short>{
		@Override
		public int compare(Short arg0, Short arg1) {
			return arg0.compareTo(arg1);
		}
	}
}
