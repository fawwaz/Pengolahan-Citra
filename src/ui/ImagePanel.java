package ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

public class ImagePanel extends JScrollPane {
	public BufferedImage image;
	public String filepath;
	public static int jumlahwindow=0;
	public int id_window;

	public ImagePanel(String filepath){
		super(new JLabel(new ImageIcon(filepath)));
		this.filepath = filepath;
		try {
			image = ImageIO.read(new File(filepath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Error Getting the file, make sure the file is readable");
			e.printStackTrace();
		}
		id_window=jumlahwindow;
		jumlahwindow++;
	}
	
	public ImagePanel(BufferedImage image){
		this.image = image;
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}

}
