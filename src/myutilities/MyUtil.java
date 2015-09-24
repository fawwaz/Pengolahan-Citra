package myutilities;


import myutilities.helper.*;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.JFrame;

public class MyUtil {
	public static BufferedImage activeimg;
	public static MyHistogram histogramizer;
	public static MyNormalizer normalizer;
	public static MyEqualizer equalizer;
	public static MyGrayScale grayscaler;
	public static MyChainCode chaincoder;
	public static MyConvolver convolver;
	public static MySyntaticRecognizer syntaticrecongnizer;
	public static MyThinner thinner;
	public static int width,height;
	
	
	public MyUtil(){
		histogramizer = new MyHistogram();
		normalizer = new MyNormalizer();
		grayscaler = new MyGrayScale();
		chaincoder = new MyChainCode();
		convolver = new MyConvolver();
		equalizer = new MyEqualizer();
		thinner = new MyThinner();
		syntaticrecongnizer = new MySyntaticRecognizer();
	}
	
	public ArrayList<TreeMap<Short, Integer>> GetHistograms() throws Exception{
		ArrayList<TreeMap<Short, Integer>> retval = new ArrayList<TreeMap<Short,Integer>>();
		histogramizer.countHistogram();
		retval.add(histogramizer.oldred);
		retval.add(histogramizer.oldgreen);
		retval.add(histogramizer.oldblue);
		return retval;
	}
	
	public BufferedImage getNormalizedImage(){
		BufferedImage a = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		a = normalizer.normalize();
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
	
	public BufferedImage getEqualizedImage(){
		return equalizer.equalize();
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
	
	public BufferedImage GetConvoluted(float[][] convmatrix, int factor, short method_id){
		
		/* * 
		 * active method number
		 * 1	: Blur
		 * 2	: Sharpen
		 * 3	: Sobell
		 * 4	: Prewitt
		 * 5 	: Frei Chan
		 * 6	: Robinson
		 * 7 	: Custom 
		 * */
		switch (method_id) {
		case 1:
		default :
			convolver.setImage(grayscaler.GetGrayScaled());
			convolver.setImage(MyUtil.activeimg);
			convolver.setConvolver(convmatrix);
			convolver.setfactor((float) 1/factor);
			return convolver.convolve();
		case 2:
			convolver.setImage(grayscaler.GetGrayScaled());
			convolver.setImage(MyUtil.activeimg);
			convolver.setConvolver(convmatrix);
			convolver.setfactor((float) 1/factor);
			return convolver.convolve();
		case 3:
			// Sobell
			ArrayList<BufferedImage> images_sobel = new ArrayList<BufferedImage>();
			convolver.setImage(grayscaler.GetGrayScaled());
			convolver.setImage(MyUtil.activeimg);
			float[][] convolver_sobel_1 = {	{ 0, 0, 0, 0, 0},
									{ 0,-1,-2,-1, 0},
									{ 0, 0, 0, 0, 0},
									{ 0, 1, 2, 1, 0},
									{ 0, 0, 0, 0, 0}};
			convolver.setConvolver(convolver_sobel_1);
			convolver.setfactor((float) 1);
			
			images_sobel.add(convolver.convolve());
			float[][] convolver_sobel_2 = {	{ 0, 0, 0, 0, 0},
									{ 0,-1, 0, 1, 0},
									{ 0,-2, 0, 2, 0},
									{ 0,-1, 0, 1, 0},
									{ 0, 0, 0, 0, 0}};
			convolver.setConvolver(convolver_sobel_2);
			convolver.setfactor((float) 1);
			images_sobel.add(convolver.convolve());
			return convolver.getCombinedImages(images_sobel);
		case 4:
			ArrayList<BufferedImage> images_prewitt = new ArrayList<BufferedImage>();
			convolver.setImage(grayscaler.GetGrayScaled());
			convolver.setImage(MyUtil.activeimg);
			float[][] convolver_prewitt_1 = {	{ 0, 0, 0, 0, 0},
												{ 0,-1,-1,-1, 0},
												{ 0, 0, 0, 0, 0},
												{ 0, 1, 1, 1, 0},
												{ 0, 0, 0, 0, 0}};
			convolver.setConvolver(convolver_prewitt_1);
			convolver.setfactor((float) 1);
			
			images_prewitt.add(convolver.convolve());
			float[][] convolver_prewitt_2 = {	{ 0, 0, 0, 0, 0},
											{ 0,-1, 0, 1, 0},
											{ 0,-1, 0, 1, 0},
											{ 0,-1, 0, 1, 0},
											{ 0, 0, 0, 0, 0}};
			convolver.setConvolver(convolver_prewitt_2);
			convolver.setfactor((float) 1);
			images_prewitt.add(convolver.convolve());
			return convolver.getCombinedImages(images_prewitt);
		case 5:
			// Frei Chan is also diffrent
			float akar2 = (float) Math.sqrt(2);
			ArrayList<BufferedImage> images_frei = new ArrayList<BufferedImage>();
			convolver.setImage(grayscaler.getOtsuTresholded());
			//convolver.setImage(grayscaler.GetGrayScaled());
			//convolver.setImage(MyUtil.activeimg);
			float[][] convolver_frei_1 = {	{ 0, 0, 0, 0, 0},
											{ 0, -1,-1 * akar2, -1, 0},
											{ 0, 0, 0, 0, 0},
											{ 0,1, akar2,1, 0},
											{ 0, 0, 0, 0, 0}};
			convolver.setConvolver(convolver_frei_1);
			convolver.setfactor((float) (2 * akar2));
			images_frei.add(convolver.convolve());
			
			float[][] convolver_frei_2 = {	{ 0, 0, 0, 0, 0},
											{ 0, -1, 0, 1, 0},
											{ 0, -1*akar2, 0, akar2, 0},
											{ 0, -1, 0, 1, 0},
											{ 0, 0, 0, 0, 0}};
			convolver.setConvolver(convolver_frei_2);
			convolver.setfactor((float) (2 * akar2));
			images_frei.add(convolver.convolve());
			
			float[][] convolver_frei_3 = {	{ 0, 0, 0, 0, 0},
											{ 0, 0,-1,akar2, 0},
											{ 0, 1, 0, -1, 0},
											{ 0, -1*akar2, 1, 0, 0},
											{ 0, 0, 0, 0, 0}};
//			convolver.setConvolver(convolver_frei_3);
//			convolver.setfactor((float) (2 * akar2));
//			images_frei.add(convolver.convolve());
			
			float[][] convolver_frei_4 = {	{ 0, 0, 0, 0, 0},
											{ 0,akar2,-1,0, 0},
											{ 0, -1, 0, 1, 0},
											{ 0, 0, 1, -1*akar2, 0},
											{ 0, 0, 0, 0, 0}};
//			convolver.setConvolver(convolver_frei_4);
//			convolver.setfactor((float) (2 * akar2));
//			images_frei.add(convolver.convolve());
			
			float[][] convolver_frei_5 = {	{ 0, 0, 0, 0, 0},
											{ 0, 0, 1, 0, 0},
											{ 0,-1, 0,-1, 0},
											{ 0, 0, 1, 0, 0},
											{ 0, 0, 0, 0, 0}};
//			convolver.setConvolver(convolver_frei_5);
//			convolver.setfactor((float) 2);
//			images_frei.add(convolver.convolve());
			
			float[][] convolver_frei_6 = {	{ 0, 0, 0, 0, 0},
											{ 0,-1, 0, 1, 0},
											{ 0, 0, 0, 0, 0},
											{ 0, 1, 0,-1, 0},
											{ 0, 0, 0, 0, 0}};
//			convolver.setConvolver(convolver_frei_6);
//			convolver.setfactor((float) 2);
//			images_frei.add(convolver.convolve());
			
			float[][] convolver_frei_7 = {	{ 0, 0, 0, 0, 0},
											{ 0, 1,-2, 1, 0},
											{ 0,-2, 4,-2, 0},
											{ 0, 1,-2, 1, 0},
											{ 0, 0, 0, 0, 0}};
//			convolver.setConvolver(convolver_frei_7);
//			convolver.setfactor((float) 6);
//			images_frei.add(convolver.convolve());
			
			float[][] convolver_frei_8 = {	{ 0, 0, 0, 0, 0},
											{ 0,-2, 1,-2, 0},
											{ 0, 1, 4, 1, 0},
											{ 0,-2, 1,-2, 0},
											{ 0, 0, 0, 0, 0}};
//			convolver.setConvolver(convolver_frei_8);
//			convolver.setfactor((float) 6);
//			images_frei.add(convolver.convolve());
			
			
			float[][] convolver_frei_9 = {	{ 0, 0, 0, 0, 0},
											{ 0, 1, 1, 1, 0},
											{ 0, 1, 1, 1, 0},
											{ 0, 1, 1, 1, 0},
											{ 0, 0, 0, 0, 0}};
//			convolver.setConvolver(convolver_frei_9);
//			convolver.setfactor((float) 3);
//			images_frei.add(convolver.convolve());
			
			
			return convolver.getCombinedImages(images_frei);
		case 6:
			ArrayList<BufferedImage> images_robinson = new ArrayList<BufferedImage>();
			convolver.setImage(grayscaler.getOtsuTresholded());
			float[][] convolver_robinson_1 = {	{ 0, 0, 0, 0, 0},
											{ 0,-1, 0, 1, 0},
											{ 0,-2, 0, 2, 0},
											{ 0,-1, 0, 1, 0},
											{ 0, 0, 0, 0, 0}};
			convolver.setConvolver(convolver_robinson_1);
			convolver.setfactor((float) 1);
			images_robinson.add(convolver.convolve());
			
			float[][] convolver_robinson_2 = {	{ 0, 0, 0, 0, 0},
											{ 0,  0, 1, 2, 0},
											{ 0, -1, 0, 1, 0},
											{ 0, -2,-1, 0, 0},
											{ 0, 0, 0, 0, 0}};
			convolver.setConvolver(convolver_robinson_2);
			convolver.setfactor((float) 1);
			images_robinson.add(convolver.convolve());
			
			float[][] convolver_robinson_3 = {	{ 0, 0, 0, 0, 0},
											{ 0, 1, 2, 1, 0},
											{ 0, 0, 0, 0, 0},
											{ 0,-1,-2,-1, 0},
											{ 0, 0, 0, 0, 0}};
			convolver.setConvolver(convolver_robinson_3);
			convolver.setfactor((float) 1);
			images_robinson.add(convolver.convolve());
			
			float[][] convolver_robinson_4 = {	{ 0, 0, 0, 0, 0},
											{ 0, 2, 1, 0, 0},
											{ 0, 1, 0,-1, 0},
											{ 0, 0,-1,-2, 0},
											{ 0, 0, 0, 0, 0}};
			convolver.setConvolver(convolver_robinson_4);
			convolver.setfactor((float) 1);
			images_robinson.add(convolver.convolve());
			
			float[][] convolver_robinson_5 = {	{ 0, 0, 0, 0, 0},
											{ 0, 1, 0,-1, 0},
											{ 0, 2, 0,-2, 0},
											{ 0, 1, 0,-1, 0},
											{ 0, 0, 0, 0, 0}};
			convolver.setConvolver(convolver_robinson_5);
			convolver.setfactor((float) 1);
			images_robinson.add(convolver.convolve());
			
			float[][] convolver_robinson_6 = {	{ 0, 0, 0, 0, 0},
											{ 0, 0,-1,-2, 0},
											{ 0, 1, 0,-1, 0},
											{ 0, 2, 1, 0, 0},
											{ 0, 0, 0, 0, 0}};
			convolver.setConvolver(convolver_robinson_6);
			convolver.setfactor((float) 1);
			images_robinson.add(convolver.convolve());
			
			float[][] convolver_robinson_7 = {	{ 0, 0, 0, 0, 0},
											{ 0,-1,-2,-1, 0},
											{ 0, 0, 0, 0, 0},
											{ 0, 1, 2, 1, 0},
											{ 0, 0, 0, 0, 0}};
			convolver.setConvolver(convolver_robinson_7);
			convolver.setfactor((float) 1);
			images_robinson.add(convolver.convolve());
			
			float[][] convolver_robinson_8 = {	{ 0, 0, 0, 0, 0},
											{ 0,-2,-1, 0, 0},
											{ 0,-1, 0, 1, 0},
											{ 0, 0, 1, 2, 0},
											{ 0, 0, 0, 0, 0}};
			convolver.setConvolver(convolver_robinson_8);
			convolver.setfactor((float) 1);
			images_robinson.add(convolver.convolve());
			
			
			
			return convolver.getCombinedImages(images_robinson);
		case 7:
			convolver.setImage(grayscaler.getOtsuTresholded());
			//convolver.setImage(MyUtil.activeimg);
			convolver.setConvolver(convmatrix);
			convolver.setfactor((float) 1/factor);
			return convolver.convolve();
		}
	}
	
	
	public BufferedImage getSpecifiedImage(ArrayList<Point> rpoints,int maxred,ArrayList<Point> gpoints,int maxgreen,ArrayList<Point> bpoints,int maxblue){
		return normalizer.specify(rpoints, maxred, gpoints, maxgreen, bpoints, maxblue);
	}
	
	public String getChainCode(){
		chaincoder.SetInputImage(grayscaler.getBinaryImage());
		chaincoder.CountBoundingBox();
		return chaincoder.getChainCodeInfo();
	} 
	
	
	public String RecognizeNumber(){
		String S="";
		chaincoder.SetInputImage(grayscaler.getBinaryImage());
		chaincoder.CountBoundingBox();
		chaincoder.getChainCodeInfo();
		return S;
	}
	
	public BufferedImage getZhanSuen(){
		thinner.SetInputImage(grayscaler.getOtsuTresholded());
		return thinner.getZhangSuen();
	}
	
	public BufferedImage getRecognizedPlat(){
		thinner.SetInputImage(grayscaler.getOtsuTresholded());		
		syntaticrecongnizer.setInputImage(thinner.getZhangSuen());
		//System.out.println("STAT DEBUGGING");
		return syntaticrecongnizer.getOnePixelWidthImage();
	}
	
	public BufferedImage getKoordinatPlat(){
		syntaticrecongnizer.setInputImage(grayscaler.getOtsuTresholded());
		return syntaticrecongnizer.getPlat();
	}
	
	public void getAngka(){
		thinner.SetInputImage(grayscaler.getOtsuTresholded());
		chaincoder.SetInputImage(thinner.getZhangSuen());
		chaincoder.CountBoundingBox();
		System.out.println(chaincoder.getChainCodeInfo());
	}
}
