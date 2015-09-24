package myutilities;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class MyNormalizer {

	public TreeMap<Short, Integer> oldred = new TreeMap<>(new myshortcomparator()),oldgreen = new TreeMap<>(new myshortcomparator()),oldblue = new TreeMap<>(new myshortcomparator());
	public TreeMap<Short, Short> redlut = new TreeMap<>(new myshortcomparator()),greenlut = new TreeMap<>(new myshortcomparator()),bluelut = new TreeMap<>(new myshortcomparator());	
	
	
	public BufferedImage normalize(){
		BufferedImage retimage = new BufferedImage(MyUtil.width, MyUtil.height, BufferedImage.TYPE_INT_RGB);
		// 1. Baca histogram
		ReadHistogram();
		// 2. Hitung LUT
		CalculateLut();
		// 3. Tulis image
		retimage = WriteImage();
		return retimage;
	}
	
	public BufferedImage specify(ArrayList<Point> rpoints,int maxred,ArrayList<Point> gpoints,int maxgreen,ArrayList<Point> bpoints,int maxblue){
		BufferedImage retimage = new BufferedImage(MyUtil.width, MyUtil.height, BufferedImage.TYPE_INT_RGB);
		TreeMap<Short, Integer> specifyred = new TreeMap<>(new myshortcomparator()), specifygreen = new TreeMap<>(new myshortcomparator()), specifyblue = new TreeMap<>(new myshortcomparator()); 
		TreeMap<Short, Short> specifyredlut = new TreeMap<>(new myshortcomparator()), specifygreenlut = new TreeMap<>(new myshortcomparator()), specifybluelut = new TreeMap<>(new myshortcomparator());
		
		// 1. Transformasi data point jadi data histogram   -- 200 default height
		specifyred = TransformPointToHistogram(rpoints, maxred, 200);
		specifygreen = TransformPointToHistogram(gpoints, maxgreen, 200);
		specifyblue = TransformPointToHistogram(bpoints, maxblue, 200);
		
		
		// 2. Lakukan Normalisasi terhadap data histogram yang dipsecify
		CalculateSpecifiedLut(specifyred, specifygreen, specifyblue, specifyredlut, specifygreenlut, specifybluelut);
		specifyredlut = StretchHistogram(specifyred);
		specifygreenlut = StretchHistogram(specifygreen);
		specifybluelut = StretchHistogram(specifyblue);
		
		
		
		
		// 3. Tulis Image Menggunakan lut di specify
		retimage = WriteImageBasedLut(specifyredlut, specifygreenlut, specifybluelut);
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
		redlut = StretchHistogram(oldred);
		greenlut = StretchHistogram(oldgreen);
		bluelut = StretchHistogram(oldblue);		
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
	
	
	public TreeMap<Short, Short> StretchHistogram(TreeMap<Short, Integer> oldhistogram){
		TreeMap<Short, Short> lut = new TreeMap<>(new myshortcomparator());
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
		
		return lut;
	}
	
	private int getintfromRGB(int red,int green, int blue){
		
		red 	= (red << 16) & 0x00FF0000;
		green 	= (green << 8) & 0x0000FF00;
		blue 	= blue & 0x000000FF;
		
		return 0xFF000000 |red|green|blue;
	}
	
	private TreeMap<Short, Integer> TransformPointToHistogram(ArrayList<Point> points,int maxpoint,float WindowHeight){
		TreeMap<Short, Integer> specified = new TreeMap<>(new myshortcomparator());
		for (Point point : points) {
			specified.put((short) point.getX(), (int) ((point.getY() / WindowHeight) * maxpoint));
			System.out.println("KEY" + point.getX() +" VALUE : "+ (int) ((point.getY() / WindowHeight) * maxpoint));
		}
		return specified;
	}
	
	private void CalculateSpecifiedLut(TreeMap<Short, Integer> red,TreeMap<Short, Integer> green,TreeMap<Short, Integer> blue , TreeMap<Short,Short> redlut,TreeMap<Short, Short> greenlut,TreeMap<Short, Short> bluelut){
		redlut = StretchHistogram(red);
		greenlut = StretchHistogram(green);
		bluelut = StretchHistogram(blue);
	}
	
	private BufferedImage WriteImageBasedLut(TreeMap<Short,Short> _redlut,TreeMap<Short, Short> _greenlut,TreeMap<Short, Short> _bluelut){
		BufferedImage retimage = new BufferedImage(MyUtil.width, MyUtil.height, BufferedImage.TYPE_INT_ARGB);
		
		int w = MyUtil.width;
		int h = MyUtil.height;
		
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				int rgb_pixel = MyUtil.activeimg.getRGB(j, i);
				Color old_color = new Color(rgb_pixel);
				
				short new_red = _redlut.get((short) old_color.getRed());
				short new_green = _greenlut.get((short) old_color.getGreen());
				short new_blue = _bluelut.get((short) old_color.getBlue());
				
				int new_color = getintfromRGB(new_red, new_green, new_blue);
				retimage.setRGB(j, i, new_color);
			}
		}
		
		return retimage;
	}
	
	class myshortcomparator implements Comparator<Short>{
		@Override
		public int compare(Short arg0, Short arg1) {
			return arg0.compareTo(arg1);
		}
	}
}
