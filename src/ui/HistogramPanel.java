package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.TreeMap;

import javax.swing.JPanel;

public class HistogramPanel extends JPanel{
	private TreeMap<Short, Integer> histogram;
	private Color histogramcolor;
	protected static final int MIN_BAR_WIDTH = 1;
	
	public HistogramPanel(TreeMap<Short, Integer> histogram_data,Color c){
		histogramcolor = c;
		histogram = histogram_data;
		int width = (histogram.size() * MIN_BAR_WIDTH) +11;
		Dimension minsize = new Dimension(width, 128);
		Dimension prefsize = new Dimension(width, 256);
		setMinimumSize(minsize);
		setPreferredSize(prefsize);
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(histogram!=null){
			int xOffset = 5;
            int yOffset = 5;
            int width = getWidth() - 1 - (xOffset * 2);
            int height = getHeight() - 1 - (yOffset * 2);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(histogramcolor);
            g2d.drawRect(xOffset, yOffset, width, height);
            int barWidth = Math.max(MIN_BAR_WIDTH, (int) Math.floor((float) width / (float) histogram.size()));
            // System.out.println("width = " + width + "; size = " + histogram.size() + "; barWidth = " + barWidth);
            int maxValue = 0;
            for (Short key : histogram.keySet()) {
                int value = histogram.get(key);
                maxValue = Math.max(maxValue, value);
            }
            int xPos = xOffset;
            for (Short key : histogram.keySet()) {
                int value = histogram.get(key);
                int barHeight = Math.round(((float) value
                        / (float) maxValue) * height);
                g2d.setColor(histogramcolor);
                int yPos = height + yOffset - barHeight;
                //	Rectangle bar = new Rectangle(xPos, yPos, barWidth, barHeight);
                Rectangle2D bar = new Rectangle2D.Float(  xPos, yPos, barWidth, barHeight);
                g2d.fill(bar);
                g2d.setColor(histogramcolor);
                g2d.draw(bar);
                xPos += barWidth;
            }
            g2d.dispose();
		}
	}
	
	
	class Dot extends JPanel{
		public Dot(){
			
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			// TODO Auto-generated method stub
			super.paintComponent(g);
		}
		
		class DotAdapter extends MouseAdapter{
			public int x,y;
			
			@Override
			public void mousePressed(MouseEvent e) {
				x = e.getX(); y = e.getY();
				if(e.getModifiers() == MouseEvent.BUTTON3_MASK){
					System.out.println("Right Click clicked");
				}
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseDragged(e);
			}
		}
	}
}
