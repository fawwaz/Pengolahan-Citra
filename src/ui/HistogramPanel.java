package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import myutilities.MyConvolver;

public class HistogramPanel extends JPanel{
	private TreeMap<Short, Integer> histogram;
	private Color histogramcolor;
	protected static final int MIN_BAR_WIDTH = 1;
	ArrayList<Point> points = new ArrayList<>();
	private int maxpixel;
	
	public HistogramPanel(TreeMap<Short, Integer> histogram_data,Color c){
		histogramcolor = c;
		histogram = histogram_data;
		int width = (histogram.size() * MIN_BAR_WIDTH) +11;
		Dimension minsize = new Dimension(width, 128);
		Dimension prefsize = new Dimension(width, 256);
		setMinimumSize(minsize);
		setPreferredSize(prefsize);
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)){
					points.add(e.getPoint());
					((HistogramFrame) getRootPane().getParent()).setPoints(points);
				}else if(SwingUtilities.isRightMouseButton(e)){
					RemoveOnRadius(e.getPoint());
					//points.remove(e.getPoint());
				}
				repaint();
			}
		});
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawhistogram(g);
		drawdots(g);
	}
	
	public int getMaxPixel(){return maxpixel;}
	
	/* *
	 * Private Functions
	 * */
	private void drawhistogram(Graphics g){
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
            this.maxpixel = maxValue;
            ((HistogramFrame) getRootPane().getParent()).setMaxval(maxValue);
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
	
	private void drawdots(Graphics g){
		System.out.println("Dots Data :");
		for (Point point : points) {
			g.setColor(Color.BLACK);
			g.fillRect(point.x, point.y, 3, 3);
			System.out.println("X : "+point.x + " Y : "+point.y);
		}
	}
	
	private void RemoveOnRadius(Point p){
		int x = p.x; int _x=0,_y=0;
		int y = p.y;
		for (int i = y-3; i < y+3; i++) {
			for (int j = x-3; j < x+3; j++) {
				if(points.contains(new Point(j,i))){
					_x = j; _y=i;
					break;
				}
			}
		}
		
		// alvailable or end of loop
		int key = points.indexOf(new Point(_x,_y));
		if(key >= 0){
			points.remove(key);
		}
	}
}
