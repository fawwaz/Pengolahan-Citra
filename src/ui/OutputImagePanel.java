package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class OutputImagePanel extends JPanel {
	BufferedImage image;
	public OutputImagePanel(BufferedImage b){
		image = b;
	}
	@Override
	public void paint(Graphics g) {
		g.drawImage(image, 0 ,0 ,null);
	}
}
