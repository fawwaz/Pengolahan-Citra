package myutilities;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Comparator;
import java.util.TreeMap;


public class MyHistogram {
	
	public TreeMap<Short, Integer> oldred = new TreeMap<>(new myshortcomparator()),oldgreen = new TreeMap<>(new myshortcomparator()),oldblue = new TreeMap<>(new myshortcomparator());
	
	
	public void countHistogram(){
		BufferedImage local = MyUtil.activeimg;
		int width = MyUtil.width;
		int height = MyUtil.height;
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int pixel = local.getRGB(j, i);
				
				// Pecah jadi 3 warna
				Color c = new Color(pixel);
				IncreaseOldHistogram(oldred, (short) c.getRed());
				IncreaseOldHistogram(oldgreen, (short) c.getGreen());
				IncreaseOldHistogram(oldblue, (short) c.getBlue());
				
			}
		}
	}
	
	public TreeMap<Short, Integer> countGrayScaleHistogram(BufferedImage image){
		TreeMap<Short, Integer> retval = new TreeMap<Short, Integer>(new myshortcomparator());
		for (int i = 0; i < image.getHeight(); i++) {
			for (int j = 0; j < image.getWidth(); j++) {
				Color c = new Color(image.getRGB(j, i));
				IncreaseOldHistogram(retval, (short) c.getRed());
			}
		}
		return retval;
	}
	
	
	
	/**
	 * Private Functions
	 * */
	private void IncreaseOldHistogram(TreeMap<Short, Integer> oldhistogram, Short key){
		int a = (oldhistogram.containsKey(key)) ? oldhistogram.get(key) : 0;
		oldhistogram.put(key, a+1);
	}
	
	class myshortcomparator implements Comparator<Short>{
		@Override
		public int compare(Short o1, Short o2) {
			return o1.compareTo(o2);
		}
	}
}
