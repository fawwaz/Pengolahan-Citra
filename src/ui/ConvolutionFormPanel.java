package ui;

import javax.swing.ButtonGroup;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JRadioButton;
import javax.swing.JButton;

import myutilities.MyUtil;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.OutputStreamWriter;

public class ConvolutionFormPanel extends JPanel {
	private MyUtil myutilities;
	private JDesktopPane parent;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;
	private JTextField textField_18;
	private JTextField textField_19;
	private JTextField textField_20;
	private JTextField textField_21;
	private JTextField textField_22;
	private JTextField textField_23;
	private JTextField textField_24;
	private JRadioButton rdbtnSharpen;
	private JRadioButton rdbtnBlur;
	private JRadioButton rdbtnRobinson;
	private JRadioButton rdbtnPrewitt;
	private JRadioButton rdbtnFreiChan;
	private JRadioButton rdbtnSobell;
	private JTextField textField_25;
	private JLabel lblFactor;
	private JRadioButton rdbtnCustom;
	public ConvolutionFormPanel() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		textField = new JTextField();
		textField.setText("0");
		add(textField, "6, 4, fill, default");
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setText("0");
		add(textField_1, "8, 4, fill, default");
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setText("0");
		add(textField_2, "10, 4, fill, default");
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setText("0");
		add(textField_3, "12, 4, fill, default");
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setText("0");
		add(textField_4, "14, 4, fill, default");
		textField_4.setColumns(10);
		
		rdbtnSharpen = new JRadioButton("Sharpen");
		rdbtnSharpen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				textField_6.setText("0");
				textField_7.setText("-1");
				textField_8.setText("0");
				
				textField_11.setText("-1");
				textField_12.setText("5");
				textField_13.setText("-1");
				
				textField_16.setText("0");
				textField_17.setText("-1");
				textField_18.setText("0");
			}
		});
		add(rdbtnSharpen, "18, 4");
		
		rdbtnPrewitt = new JRadioButton("Prewitt");
		add(rdbtnPrewitt, "20, 4");
		
		textField_5 = new JTextField();
		textField_5.setText("0");
		add(textField_5, "6, 6, fill, default");
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setText("0");
		add(textField_6, "8, 6, fill, default");
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setText("0");
		add(textField_7, "10, 6, fill, default");
		textField_7.setColumns(10);
		
		textField_8 = new JTextField();
		textField_8.setText("0");
		add(textField_8, "12, 6, fill, default");
		textField_8.setColumns(10);
		
		textField_9 = new JTextField();
		textField_9.setText("0");
		add(textField_9, "14, 6, fill, default");
		textField_9.setColumns(10);
		
		rdbtnBlur = new JRadioButton("Blur");
		rdbtnBlur.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				textField_6.setText("1");
				textField_7.setText("2");
				textField_8.setText("1");
				
				textField_11.setText("2");
				textField_12.setText("4");
				textField_13.setText("2");
				
				textField_16.setText("1");
				textField_17.setText("2");
				textField_18.setText("1");
				
				textField_25.setText("16");
			}
		});
		add(rdbtnBlur, "18, 6");
		
		rdbtnFreiChan = new JRadioButton("Frei Chan");
		add(rdbtnFreiChan, "20, 6");
		
		textField_10 = new JTextField();
		textField_10.setText("0");
		add(textField_10, "6, 8, fill, default");
		textField_10.setColumns(10);
		
		textField_11 = new JTextField();
		textField_11.setText("0");
		add(textField_11, "8, 8, fill, default");
		textField_11.setColumns(10);
		
		textField_12 = new JTextField();
		textField_12.setText("0");
		add(textField_12, "10, 8, fill, default");
		textField_12.setColumns(10);
		
		textField_13 = new JTextField();
		textField_13.setText("0");
		add(textField_13, "12, 8, fill, default");
		textField_13.setColumns(10);
		
		textField_14 = new JTextField();
		textField_14.setText("0");
		add(textField_14, "14, 8, fill, default");
		textField_14.setColumns(10);
		
		rdbtnSobell = new JRadioButton("Sobell");
		rdbtnSobell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_6.setText("-1");
				textField_7.setText("0");
				textField_8.setText("1");
				
				textField_11.setText("-2");
				textField_12.setText("0");
				textField_13.setText("2");
				
				textField_16.setText("-1");
				textField_17.setText("0");
				textField_18.setText("1");
				
			}
		});
		add(rdbtnSobell, "18, 8");
		
		rdbtnRobinson = new JRadioButton("Robinson");
		add(rdbtnRobinson, "20, 8");
		
		textField_15 = new JTextField();
		textField_15.setText("0");
		add(textField_15, "6, 10, fill, default");
		textField_15.setColumns(10);
		
		textField_16 = new JTextField();
		textField_16.setText("0");
		add(textField_16, "8, 10, fill, default");
		textField_16.setColumns(10);
		
		textField_17 = new JTextField();
		textField_17.setText("0");
		add(textField_17, "10, 10, fill, default");
		textField_17.setColumns(10);
		
		textField_18 = new JTextField();
		textField_18.setText("0");
		add(textField_18, "12, 10, fill, default");
		textField_18.setColumns(10);
		
		textField_19 = new JTextField();
		textField_19.setText("0");
		add(textField_19, "14, 10, fill, default");
		textField_19.setColumns(10);
		
		lblFactor = new JLabel("Factor :");
		add(lblFactor, "18, 10");
		
		rdbtnCustom = new JRadioButton("Custom");
		rdbtnBlur.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setText("0");
				textField_1.setText("0");
				textField_2.setText("0");
				textField_3.setText("0");
				textField_4.setText("0");
				textField_5.setText("0");
				textField_6.setText("0");
				textField_7.setText("0");
				textField_8.setText("0");
				textField_9.setText("0");
				textField_10.setText("0");
				textField_11.setText("0");
				textField_12.setText("0");
				textField_13.setText("0");
				textField_14.setText("0");
				textField_15.setText("0");
				textField_16.setText("0");
				textField_17.setText("0");
				textField_18.setText("0");
				textField_19.setText("0");
				textField_20.setText("0");
				textField_21.setText("0");
				textField_22.setText("0");
				textField_23.setText("0");
				textField_24.setText("0");
				
				textField_25.setText("1");
				
				
			}
		});
		add(rdbtnCustom, "20, 10");
		
		textField_20 = new JTextField();
		textField_20.setText("0");
		add(textField_20, "6, 12, fill, default");
		textField_20.setColumns(10);
		
		textField_21 = new JTextField();
		textField_21.setText("0");
		add(textField_21, "8, 12, fill, default");
		textField_21.setColumns(10);
		
		textField_22 = new JTextField();
		textField_22.setText("0");
		add(textField_22, "10, 12, fill, default");
		textField_22.setColumns(10);
		
		textField_23 = new JTextField();
		textField_23.setText("0");
		add(textField_23, "12, 12, fill, default");
		textField_23.setColumns(10);
		
		textField_24 = new JTextField();
		textField_24.setText("0");
		add(textField_24, "14, 12, fill, default");
		textField_24.setColumns(10);
		
		JButton btnConvolve = new JButton("Convolve");
		btnConvolve.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				convolve();
				
			}
		});
		
		textField_25 = new JTextField();
		textField_25.setText("1");
		add(textField_25, "18, 12, fill, default");
		textField_25.setColumns(10);
		add(btnConvolve, "20, 12");
		
		
		ButtonGroup bgroup = new ButtonGroup();
		bgroup.add(rdbtnBlur);
		bgroup.add(rdbtnFreiChan);
		bgroup.add(rdbtnPrewitt);
		bgroup.add(rdbtnRobinson);
		bgroup.add(rdbtnSobell);
		bgroup.add(rdbtnSharpen);
		bgroup.add(rdbtnCustom);
	}
	
	public void setParentDesktopPane(JDesktopPane parent){
		this.parent = parent;
	}
	
	public void setUtilities(MyUtil util){
		this.myutilities = util;
	}
	
	public short GetActiveMethod(){
		if (rdbtnBlur.isSelected()){
			return 1;
		}else if (rdbtnSharpen.isSelected()){
			return 2;
		}else if(rdbtnSobell.isSelected()){
			return 3;
		}else if(rdbtnPrewitt.isSelected()){
			return 4;
		}else if(rdbtnFreiChan.isSelected()){
			return 5;
		}else if(rdbtnRobinson.isSelected()){
			return 6;
		}else if(rdbtnCustom.isSelected()){
			return 7;
		}else{
			return 7;
		}
	}
	
	/* *
	 * Private functions
	 * */
	private void convolve(){
		
		short method_number = GetActiveMethod();
		int subfactor=1;
		float convmatrix[][] = new float[5][5];
		OutputImagePanel output_panel = null;
		
		switch (method_number) {
		case 1:
		default :
			convmatrix = GetConvolutionMatrix();
			subfactor = Integer.parseInt(textField_25.getText());
			output_panel = new OutputImagePanel(myutilities.GetConvoluted(convmatrix,subfactor,method_number));
			break;
		case 2:
			convmatrix = GetConvolutionMatrix();
			subfactor = 1;
			output_panel = new OutputImagePanel(myutilities.GetConvoluted(convmatrix,subfactor,method_number));
			break;
		case 3:
			// Sobel matrix is diffrent, it apply 2 image
			convmatrix = null;
			subfactor = 1;
			output_panel = new OutputImagePanel(myutilities.GetConvoluted(convmatrix,subfactor,method_number));
			break;
		case 4:
			// Prewitt is also diffrent
			convmatrix = null;
			subfactor = 1;
			output_panel = new OutputImagePanel(myutilities.GetConvoluted(convmatrix,subfactor,method_number));
			break;
		case 5:
			// and also frei chan
			convmatrix = null;
			subfactor = 1;
			output_panel = new OutputImagePanel(myutilities.GetConvoluted(convmatrix,subfactor,method_number));
			break;
		case 6:
			// Robinson too
			convmatrix = null;
			subfactor = 1;
			output_panel = new OutputImagePanel(myutilities.GetConvoluted(convmatrix,subfactor,method_number));
			break;
		case 7 :
			convmatrix = GetConvolutionMatrix();
			subfactor = Integer.valueOf(textField_25.getText());
			output_panel = new OutputImagePanel(myutilities.GetConvoluted(convmatrix,subfactor,method_number));
		}
		
		
		
		JInternalFrame temp = new JInternalFrame("Convolution Window",true,true,true,true);
		temp.setBounds(200,200,400,220);
		
		temp.getContentPane().add(output_panel,BorderLayout.CENTER);
		
		temp.setVisible(true);		
		parent.add(temp);
		
	}
	
	private float[][] GetConvolutionMatrix(){
		float[][] convolutionmatrix = new float[5][5];
		convolutionmatrix[0][0] = (float) Double.parseDouble(textField.getText());
		convolutionmatrix[0][1] = (float) Double.parseDouble(textField_1.getText());
		convolutionmatrix[0][2] = (float) Double.parseDouble(textField_2.getText());
		convolutionmatrix[0][3] = (float) Double.parseDouble(textField_3.getText());
		convolutionmatrix[0][4] = (float) Double.parseDouble(textField_4.getText());
		
		convolutionmatrix[1][0] = (float) Double.parseDouble(textField_5.getText());
		convolutionmatrix[1][1] = (float) Double.parseDouble(textField_6.getText());
		convolutionmatrix[1][2] = (float) Double.parseDouble(textField_7.getText());
		convolutionmatrix[1][3] = (float) Double.parseDouble(textField_8.getText());
		convolutionmatrix[1][4] = (float) Double.parseDouble(textField_9.getText());
		
		convolutionmatrix[2][0] = (float) Double.parseDouble(textField_10.getText());
		convolutionmatrix[2][1] = (float) Double.parseDouble(textField_11.getText());
		convolutionmatrix[2][2] = (float) Double.parseDouble(textField_12.getText());
		convolutionmatrix[2][3] = (float) Double.parseDouble(textField_13.getText());
		convolutionmatrix[2][4] = (float) Double.parseDouble(textField_14.getText());
		
		convolutionmatrix[3][0] = (float) Double.parseDouble(textField_15.getText());
		convolutionmatrix[3][1] = (float) Double.parseDouble(textField_16.getText());
		convolutionmatrix[3][2] = (float) Double.parseDouble(textField_17.getText());
		convolutionmatrix[3][3] = (float) Double.parseDouble(textField_18.getText());
		convolutionmatrix[3][4] = (float) Double.parseDouble(textField_19.getText());
		
		convolutionmatrix[4][0] = (float) Double.parseDouble(textField_20.getText());
		convolutionmatrix[4][1] = (float) Double.parseDouble(textField_21.getText());
		convolutionmatrix[4][2] = (float) Double.parseDouble(textField_22.getText());
		convolutionmatrix[4][3] = (float) Double.parseDouble(textField_23.getText());
		convolutionmatrix[4][4] = (float) Double.parseDouble(textField_24.getText());
		
		return convolutionmatrix;
	}
	

}
