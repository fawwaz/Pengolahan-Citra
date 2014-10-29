package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import myutilities.helper.*;
import javax.swing.JPanel;

public class BoundingBoxOutputPanel extends JPanel{
	ArrayList<Gambar> gambars;
	BufferedImage img;
	
	public BoundingBoxOutputPanel(ArrayList<Gambar> gambars,BufferedImage img){
		this.gambars = gambars;
		this.img = img;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
		for (Gambar gambar : gambars) {
			g.setColor(new Color(200,0,0));
			g.drawRect(gambar.offsetx, gambar.offsety, gambar.width, gambar.height);
		}
	}

	
}
