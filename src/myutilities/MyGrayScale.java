package myutilities;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class MyGrayScale {
	
	public BufferedImage GetGrayScaled(){
		BufferedImage retval = new BufferedImage(MyUtil.width, MyUtil.height, BufferedImage.TYPE_INT_RGB);
		
		for (int i = 0; i < MyUtil.height; i++) {
			for (int j = 0; j < MyUtil.width; j++) {
				Color c = new Color(MyUtil.activeimg.getRGB(j, i)); 
				int average = (c.getRed()+c.getGreen()+c.getBlue()) / 3;
				retval.setRGB(j, i, getintfromRGB(average, average, average));
			}
		}
		return retval;
	}
	
	public BufferedImage getBinaryImage(){
		BufferedImage retval = new BufferedImage(MyUtil.width, MyUtil.height, BufferedImage.TYPE_INT_RGB);
		
		for (int i = 0; i < MyUtil.height; i++) {
			for (int j = 0; j < MyUtil.width; j++) {
				Color c = new Color(MyUtil.activeimg.getRGB(j, i)); 
				int average = (c.getRed()+c.getGreen()+c.getBlue()) / 3;
				if(average>=128){
					retval.setRGB(j, i, getintfromRGB(255, 255, 255));
				}else{
					retval.setRGB(j, i, getintfromRGB(0, 0, 0));
				}
				
			}
		}
		return retval;
	}
	
	public BufferedImage getOtsuTresholded(){
		BufferedImage retval = new BufferedImage(MyUtil.width, MyUtil.height, BufferedImage.TYPE_INT_RGB);
		TreeMap<Short, Integer> histogram = new TreeMap<>(new myshortcomparator());
		int[] simplified_histogram = new int[256];
		int calculated_treshold;
		
		MyHistogram histogramizer = new MyHistogram();
		histogram = histogramizer.countGrayScaleHistogram(GetGrayScaled());
		
		for(Map.Entry<Short, Integer> entry : histogram.entrySet()){
			simplified_histogram[entry.getKey()] = entry.getValue();
		}

		calculated_treshold = OtsuTreshold(simplified_histogram);
		
		for (int i = 0; i < MyUtil.height; i++) {
			for (int j = 0; j < MyUtil.width; j++) {
				Color c = new Color(MyUtil.activeimg.getRGB(j, i)); 
				int average = (c.getRed()+c.getGreen()+c.getBlue()) / 3;
				if(average>=calculated_treshold){
					retval.setRGB(j, i, getintfromRGB(255, 255, 255));
				}else{
					retval.setRGB(j, i, getintfromRGB(0, 0, 0));
				}
				
			}
		}
		
		
		return retval;
	}
	
	
	/**
	 * Private Functions
	 * */
	
	private int OtsuTreshold(int[] data){
		// Otsu's threshold algorithm
		// C++ code by Jordan Bevik <Jordan.Bevic@qtiworld.com>
		// ported to ImageJ plugin by G.Landini
		int k, kStar; // k = the current threshold; kStar = optimal threshold
		double N1, N; // N1 = # points with intensity <=k; N = total number of
						// points
		double BCV, BCVmax; // The current Between Class Variance and maximum
							// BCV
		double num, denom; // temporary bookeeping
		double Sk; // The total intensity for all histogram points <=k
		double S, L = 256; // The total intensity of the image

		// Initialize values:
		S = N = 0;
		for (k = 0; k < L; k++) {
			S += (double) k * data[k]; // Total histogram intensity
			N += data[k]; // Total number of data points
		}

		Sk = 0;
		N1 = data[0]; // The entry for zero intensity
		BCV = 0;
		BCVmax = 0;
		kStar = 0;

		// Look at each possible threshold value,
		// calculate the between-class variance, and decide if it's a max
		for (k = 1; k < L - 1; k++) { // No need to check endpoints k = 0 or k =
										// L-1
			Sk += (double) k * data[k];
			N1 += data[k];

			// The float casting here is to avoid compiler warning about loss of
			// precision and
			// will prevent overflow in the case of large saturated images
			denom = (double) (N1) * (N - N1); // Maximum value of denom is
												// (N^2)/4 = approx. 3E10

			if (denom != 0) {
				// Float here is to avoid loss of precision when dividing
				num = ((double) N1 / N) * S - Sk; // Maximum value of num =
													// 255*N = approx 8E7
				BCV = (num * num) / denom;
			} else
				BCV = 0;

			if (BCV >= BCVmax) { // Assign the best threshold found so far
				BCVmax = BCV;
				kStar = k;
			}
		}
		// kStar += 1; // Use QTI convention that intensity -> 1 if intensity >=
		// k
		// (the algorithm was developed for I-> 1 if I <= k.)
		return kStar;
	}
	
	public boolean isDominantWhite(BufferedImage image){
		int whitecounter=0,blackcounter=0;
		for (int i = 0; i < image.getHeight(); i++) {
			for (int j = 0; j < image.getWidth(); j++) {
				Color c = new Color(image.getRGB(j, i));
				if(c.getRed()<128){
					blackcounter++;
				}else if(c.getRed()>=128){
					whitecounter++;
				}
			}
		}
		System.out.println("white Score "+whitecounter + " Black Score "+blackcounter);
		return whitecounter>blackcounter;
	}
	
	public BufferedImage invertGrayScale(BufferedImage image){
		BufferedImage retimg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < image.getHeight(); i++) {
			for (int j = 0; j < image.getWidth(); j++) {
				Color c = new Color(image.getRGB(j, i));
				int new_color = 255 - c.getRed();
				Color new_c = new Color(new_color,new_color,new_color);
				retimg.setRGB(j, i, new_c.getRGB());
			}
		}
		return retimg;
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
