package myutilities;


import myutilities.helper.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.TreeMap;

public class MyUtil {
	public static BufferedImage activeimg;
	public static MyHistogram histogramizer;
	public static MyEqualizer equalizer;
	public static MyGrayScale grayscaler;
	public static MyChainCode chaincoder;
	public static MyConvolver convolver;
	public static int width,height;
	
	
	public MyUtil(){
		histogramizer = new MyHistogram();
		equalizer = new MyEqualizer();
		grayscaler = new MyGrayScale();
		chaincoder = new MyChainCode();
		convolver = new MyConvolver();
	}
	
	public ArrayList<TreeMap<Short, Integer>> GetHistograms() throws Exception{
		ArrayList<TreeMap<Short, Integer>> retval = new ArrayList<TreeMap<Short,Integer>>();
		histogramizer.countHistogram();
		retval.add(histogramizer.oldred);
		retval.add(histogramizer.oldgreen);
		retval.add(histogramizer.oldblue);
		return retval;
	}
	
	public BufferedImage GetEqualizedImage(){
		BufferedImage a = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		a = equalizer.equalize();
		return a;
	}
	
	public BufferedImage GetGrayScaledImage(){
		return grayscaler.GetGrayScaled();
	}
		
	public BufferedImage getBinaryImage(){
		return grayscaler.getBinaryImage();
	}
	
	public ArrayList<Gambar> CreateBoundingBox(){
		chaincoder.SetInputImage(grayscaler.getBinaryImage());
		chaincoder.CountBoundingBox();
		return chaincoder.GetGambars();
	}
	
	public BufferedImage GetOtsuTresholded(){
		return grayscaler.getOtsuTresholded();
	}
	
	/**
	 * Setters &  Getters
	 * */
	public BufferedImage getActiveimg() {return activeimg;}
	public void setActiveimg(BufferedImage activeimg) {
		this.activeimg = activeimg;
		this.width = activeimg.getWidth();
		this.height = activeimg.getHeight();
	}
	
	public BufferedImage GetConvoluted(int[] convmatrix){
		//convolver.setImage(grayscaler.GetGrayScaled());
		convolver.setImage(MyUtil.activeimg);
		convolver.setConvolver(convmatrix);
		
		return convolver.convolve();
	}
	
	
}
