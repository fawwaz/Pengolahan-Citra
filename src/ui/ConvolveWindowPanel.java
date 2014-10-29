package ui;

import javax.swing.JInternalFrame;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import java.awt.FlowLayout;

public class ConvolveWindowPanel extends JInternalFrame{
	public ConvolveWindowPanel() {
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		ConvolutionFormPanel convolutionFormPanel = new ConvolutionFormPanel();
		getContentPane().add(convolutionFormPanel);
	}

	

}
