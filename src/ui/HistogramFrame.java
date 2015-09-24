package ui;

import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JInternalFrame;

public class HistogramFrame extends JInternalFrame {
	ArrayList<Point> points;
	int maxval;
	public ArrayList<Point> getPoints() {
		return points;
	}
	public void setPoints(ArrayList<Point> points) {
		this.points = points;
	}
	public int getMaxval() {
		return maxval;
	}
	public void setMaxval(int maxval) {
		this.maxval = maxval;
	}
	public HistogramFrame(String s,boolean a,boolean b, boolean c, boolean d){
		super(s, a, b, c, d);
	}
}
