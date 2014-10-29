package ui;

import myutilities.helper.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.BorderLayout;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JDesktopPane;
import javax.swing.JToolBar;
import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.plaf.DesktopPaneUI;

import myutilities.MyUtil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;
import java.awt.Panel;


public class MainWindow {

	private JFrame frame;
	// Kayaknya gak perlu nyimpen array list deh
	public ArrayList<JInternalFrame> internalframes = new ArrayList<JInternalFrame>();
	JDesktopPane desktopPane;
	MyUtil utilities;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		utilities = new MyUtil();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(25, 25, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel PanelKanan = new JPanel();
		frame.getContentPane().add(PanelKanan, BorderLayout.EAST);
		
		JPanel PanelKiri = new JPanel();
		frame.getContentPane().add(PanelKiri, BorderLayout.WEST);
		
		JPanel Keterangan = new JPanel();
		frame.getContentPane().add(Keterangan, BorderLayout.SOUTH);
		
		desktopPane= new JDesktopPane();
		frame.getContentPane().add(desktopPane, BorderLayout.CENTER);		
		
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				open_frame();
			}
		});
		mnFile.add(mntmOpen);
		
		JMenu mnOperation = new JMenu("Operation");
		menuBar.add(mnOperation);
		
		JMenuItem mntmShowHistogram = new JMenuItem("Show Histogram");
		mntmShowHistogram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				displayhistogram();
			}
		});
		mnOperation.add(mntmShowHistogram);
		
		JMenuItem mntmHistogramNormalisasi = new JMenuItem("Histogram Normalisasi");
		mnOperation.add(mntmHistogramNormalisasi);
		
		JMenuItem mntmHistogramEqualisasi = new JMenuItem("Histogram Equalisasi");
		mntmHistogramEqualisasi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doEqualization();
			}
		});
		mnOperation.add(mntmHistogramEqualisasi);
		
		JMenuItem mntmHistogramNormalisasi_1 = new JMenuItem("Histogram Normalisasi & Equalisasi");
		mnOperation.add(mntmHistogramNormalisasi_1);
		
		JMenuItem mntmSimplifikasi = new JMenuItem("Simplifikasi");
		mnOperation.add(mntmSimplifikasi);
		
		JMenuItem mntmGrayScale = new JMenuItem("Gray Scale");
		mntmGrayScale.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				doGrayScaling();
			}
		});
		mnOperation.add(mntmGrayScale);
		
		JMenuItem mntmGambarBiner = new JMenuItem("Gambar Biner");
		mntmGambarBiner.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				doBinaryProcessing();
			}
		});
		mnOperation.add(mntmGambarBiner);
		
		JMenuItem mntmBoundingBoxDetection = new JMenuItem("Bounding Box Detection");
		mntmBoundingBoxDetection.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				doCountingBoundingBox();
			}
		});
		mnOperation.add(mntmBoundingBoxDetection);
		
		JMenuItem mntmChainCode = new JMenuItem("Chain Code");
		mnOperation.add(mntmChainCode);
		
		JMenuItem mntmNumberRecognition = new JMenuItem("Number Recognition");
		mnOperation.add(mntmNumberRecognition);
		
		JMenuItem mntmAutoTreshold = new JMenuItem("Auto Treshold");
		mntmAutoTreshold.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				doAutoTresholding();
			}
		});
		mnOperation.add(mntmAutoTreshold);
		
		JMenuItem mntmEdgeDetection = new JMenuItem("Edge Detection");
		mntmEdgeDetection.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				doconvoltuion();
			}
		});
		mnOperation.add(mntmEdgeDetection);
		
		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
		
	}
	
	private void open_frame(){
		JFileChooser jfc = new JFileChooser();
		if(jfc.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION){
			File opened = jfc.getSelectedFile();
						
			final ImagePanel imgpanel;
			try {
				utilities.setActiveimg(ImageIO.read(opened));
				imgpanel = new ImagePanel(opened.getCanonicalPath());
				
				imgpanel.setPreferredSize(new Dimension(300,300));
				
				JInternalFrame temp = new JInternalFrame("Image",true,true,true,true);
				
				Random randomizer = new Random();
				int x = randomizer.nextInt(frame.getWidth())/2 + 300;
				int y = randomizer.nextInt(frame.getHeight())/2;			
				temp.setBounds(x,y,200,200);
				temp.setVisible(true);
				
				temp.setContentPane(imgpanel);
				internalframes.add(temp);
				desktopPane.add(temp);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Error getting file path");
				e.printStackTrace();
			}
		}
	}

	private void displayhistogram(){
		try{
			ImagePanel i =  (ImagePanel) desktopPane.getSelectedFrame().getContentPane();
			utilities.setActiveimg(i.image);
			ArrayList<TreeMap<Short, Integer>> histograms = new ArrayList<TreeMap<Short, Integer>>();
			TreeMap<Short, Integer> Rhistogram,Ghistogram,Bhistogram;
			try {
				histograms = utilities.GetHistograms();
				
				Rhistogram = histograms.get(0);
				Ghistogram = histograms.get(1);
				Bhistogram = histograms.get(2);
				
				HistogramPanel rpanel = new HistogramPanel(Rhistogram,Color.RED);
				HistogramPanel gpanel = new HistogramPanel(Ghistogram,Color.GREEN);
				HistogramPanel bpanel = new HistogramPanel(Bhistogram,Color.BLUE);
				
				JInternalFrame rFrame = new JInternalFrame("Red Histogram",false,true,false,true);
				JInternalFrame gFrame = new JInternalFrame("Green Histogram",false,true,false,true);
				JInternalFrame bFrame = new JInternalFrame("Blue Histogram",false,true,false,true);
				
				rFrame.setBounds(0,0,300,200);
				gFrame.setBounds(0,200,300,200);
				bFrame.setBounds(0,400,300, 200);
				
				rFrame.getContentPane().add(rpanel,BorderLayout.CENTER);
				gFrame.getContentPane().add(gpanel,BorderLayout.CENTER);
				bFrame.getContentPane().add(bpanel,BorderLayout.CENTER);
				
				rFrame.setVisible(true);
				gFrame.setVisible(true);
				bFrame.setVisible(true);
				
				desktopPane.add(rFrame);
				desktopPane.add(gFrame);
				desktopPane.add(bFrame);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "No Image selected, please select one from the active image");
				e.printStackTrace();
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Wheter you select nothing, or you select wrong frame that doesn't contain any picture");
		}
		
	}
	
	private void doEqualization(){
		try{
			ImagePanel i =  (ImagePanel) desktopPane.getSelectedFrame().getContentPane();
			utilities.setActiveimg(i.image);
			
			BufferedImage output = utilities.GetEqualizedImage();
			OutputImagePanel output_panel = new OutputImagePanel(output);
			//ImagePanel output_panel = new ImagePanel(output);
			output_panel.setPreferredSize(new Dimension(300,300));
			
			JInternalFrame temp = new JInternalFrame("Output Image",true,true,true,true);
			
			Random randomizer = new Random();
			int x = randomizer.nextInt(frame.getWidth())/2 + 300;
			int y = randomizer.nextInt(frame.getHeight())/2;			
			temp.setBounds(x,y,200,200);
			temp.setVisible(true);
			
			temp.setContentPane(output_panel);
			internalframes.add(temp);
			desktopPane.add(temp);
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Wheter you select nothing, or you select wrong frame that doesn't contain any picture");
		}
	}
	
	private void doGrayScaling(){
		try{
			ImagePanel i =  (ImagePanel) desktopPane.getSelectedFrame().getContentPane();
			utilities.setActiveimg(i.image);
			
			BufferedImage output = utilities.GetGrayScaledImage();
			OutputImagePanel output_panel = new OutputImagePanel(output);
			//ImagePanel output_panel = new ImagePanel(output);
			output_panel.setPreferredSize(new Dimension(300,300));
			
			JInternalFrame temp = new JInternalFrame("Output Image",true,true,true,true);
			
			Random randomizer = new Random();
			int x = randomizer.nextInt(frame.getWidth())/2 + 300;
			int y = randomizer.nextInt(frame.getHeight())/2;			
			temp.setBounds(x,y,200,200);
			temp.setVisible(true);
			
			temp.setContentPane(output_panel);
			internalframes.add(temp);
			desktopPane.add(temp);
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Wheter you select nothing, or you select wrong frame that doesn't contain any picture");
		}
	}
	
	private void doBinaryProcessing(){
		try{
			ImagePanel i =  (ImagePanel) desktopPane.getSelectedFrame().getContentPane();
			utilities.setActiveimg(i.image);
			
			BufferedImage output = utilities.getBinaryImage();
			OutputImagePanel output_panel = new OutputImagePanel(output);
			//ImagePanel output_panel = new ImagePanel(output);
			output_panel.setPreferredSize(new Dimension(300,300));
			
			JInternalFrame temp = new JInternalFrame("Output Image",true,true,true,true);
			
			Random randomizer = new Random();
			int x = randomizer.nextInt(frame.getWidth())/2 + 300;
			int y = randomizer.nextInt(frame.getHeight())/2;			
			temp.setBounds(x,y,200,200);
			temp.setVisible(true);
			
			temp.setContentPane(output_panel);
			internalframes.add(temp);
			desktopPane.add(temp);
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Wheter you select nothing, or you select wrong frame that doesn't contain any picture");
		}
	}
	
	private void doCountingBoundingBox(){
		try{
			ImagePanel i =  (ImagePanel) desktopPane.getSelectedFrame().getContentPane();
			utilities.setActiveimg(i.image);
			
			
			ArrayList<Gambar> gambars = utilities.CreateBoundingBox();
			BoundingBoxOutputPanel bbop = new BoundingBoxOutputPanel(gambars, utilities.activeimg);
			
			bbop.setPreferredSize(new Dimension(300,300));
			
			JInternalFrame temp = new JInternalFrame("Output Image",true,true,true,true);
			
			Random randomizer = new Random();
			int x = randomizer.nextInt(frame.getWidth())/2 + 300;
			int y = randomizer.nextInt(frame.getHeight())/2;			
			temp.setBounds(x,y,200,200);
			temp.setVisible(true);
			
			temp.setContentPane(bbop);
			internalframes.add(temp);
			desktopPane.add(temp);
			JOptionPane.showMessageDialog(null, "Jumlah objek dalam gambar ada : " + gambars.size());
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Wheter you select nothing, or you select wrong frame that doesn't contain any picture");
		}
	}
	
	private void doAutoTresholding(){
		try{
			ImagePanel i =  (ImagePanel) desktopPane.getSelectedFrame().getContentPane();
			utilities.setActiveimg(i.image);
			
			BufferedImage output = utilities.GetOtsuTresholded();
			OutputImagePanel output_panel = new OutputImagePanel(output);
			//ImagePanel output_panel = new ImagePanel(output);
			output_panel.setPreferredSize(new Dimension(300,300));
			
			JInternalFrame temp = new JInternalFrame("Output Image",true,true,true,true);
			
			Random randomizer = new Random();
			int x = randomizer.nextInt(frame.getWidth())/2 + 300;
			int y = randomizer.nextInt(frame.getHeight())/2;			
			temp.setBounds(x,y,200,200);
			temp.setVisible(true);
			
			temp.setContentPane(output_panel);
			internalframes.add(temp);
			desktopPane.add(temp);
		}catch (Exception e){
			JOptionPane.showMessageDialog(null, "Wheter you select nothing, or you select wrong frame that doesn't contain any picture");
		}
	}
	
	private void doconvoltuion(){
		MyConvolutionPanel cfp = new MyConvolutionPanel();
		
		JInternalFrame temp = new JInternalFrame("Convolution Window",true,true,true,true);
		temp.add(new ConvolutionFormPanel());
		temp.setBounds(200,200,400,220);
		
		temp.getContentPane().add(cfp,BorderLayout.CENTER);
		temp.setContentPane(cfp);
		
		temp.setVisible(true);		
		desktopPane.add(temp);
		
		
	}
	
	private void doconvoltuion2(){
		JOptionPane.showMessageDialog(null, "jangan diimplment dulu");
			ImagePanel i =  (ImagePanel) desktopPane.getSelectedFrame().getContentPane();
			utilities.setActiveimg(i.image);
			
			int[] convolutionMatrix = {-1,0,1,-2,0,2,-1,0,1};
			
			BufferedImage output = utilities.GetConvoluted(convolutionMatrix);
			OutputImagePanel output_panel = new OutputImagePanel(output);
			//ImagePanel output_panel = new ImagePanel(output);
			output_panel.setPreferredSize(new Dimension(300,300));
			
			JInternalFrame temp = new JInternalFrame("Output Image",true,true,true,true);
			
			Random randomizer = new Random();
			int x = randomizer.nextInt(frame.getWidth())/2 + 300;
			int y = randomizer.nextInt(frame.getHeight())/2;			
			temp.setBounds(x,y,200,200);
			temp.setVisible(true);
			
			temp.setContentPane(output_panel);
			internalframes.add(temp);
			desktopPane.add(temp);
		
	}
}


