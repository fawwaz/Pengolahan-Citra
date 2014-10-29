package ui;

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
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class MyConvolutionPanel extends JPanel {
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
	public MyConvolutionPanel() {
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
		add(textField, "6, 4, fill, default");
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		add(textField_1, "8, 4, fill, default");
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		add(textField_2, "10, 4, fill, default");
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		add(textField_3, "12, 4, fill, default");
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		add(textField_4, "14, 4, fill, default");
		textField_4.setColumns(10);
		
		JRadioButton rdbtnSobell = new JRadioButton("Sobell");
		add(rdbtnSobell, "18, 4");
		
		textField_5 = new JTextField();
		add(textField_5, "6, 6, fill, default");
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		add(textField_6, "8, 6, fill, default");
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		add(textField_7, "10, 6, fill, default");
		textField_7.setColumns(10);
		
		textField_8 = new JTextField();
		add(textField_8, "12, 6, fill, default");
		textField_8.setColumns(10);
		
		textField_9 = new JTextField();
		add(textField_9, "14, 6, fill, default");
		textField_9.setColumns(10);
		
		JRadioButton rdbtnPrewitt = new JRadioButton("Prewitt");
		add(rdbtnPrewitt, "18, 6");
		
		textField_10 = new JTextField();
		add(textField_10, "6, 8, fill, default");
		textField_10.setColumns(10);
		
		textField_11 = new JTextField();
		add(textField_11, "8, 8, fill, default");
		textField_11.setColumns(10);
		
		textField_12 = new JTextField();
		add(textField_12, "10, 8, fill, default");
		textField_12.setColumns(10);
		
		textField_13 = new JTextField();
		add(textField_13, "12, 8, fill, default");
		textField_13.setColumns(10);
		
		textField_14 = new JTextField();
		add(textField_14, "14, 8, fill, default");
		textField_14.setColumns(10);
		
		JRadioButton rdbtnRobertCross = new JRadioButton("Robert Cross");
		add(rdbtnRobertCross, "18, 8");
		
		textField_15 = new JTextField();
		add(textField_15, "6, 10, fill, default");
		textField_15.setColumns(10);
		
		textField_16 = new JTextField();
		add(textField_16, "8, 10, fill, default");
		textField_16.setColumns(10);
		
		textField_17 = new JTextField();
		add(textField_17, "10, 10, fill, default");
		textField_17.setColumns(10);
		
		textField_18 = new JTextField();
		add(textField_18, "12, 10, fill, default");
		textField_18.setColumns(10);
		
		textField_19 = new JTextField();
		add(textField_19, "14, 10, fill, default");
		textField_19.setColumns(10);
		
		JRadioButton rdbtnFreiChan = new JRadioButton("Frei Chan");
		add(rdbtnFreiChan, "18, 10");
		
		textField_20 = new JTextField();
		add(textField_20, "6, 12, fill, default");
		textField_20.setColumns(10);
		
		textField_21 = new JTextField();
		add(textField_21, "8, 12, fill, default");
		textField_21.setColumns(10);
		
		textField_22 = new JTextField();
		add(textField_22, "10, 12, fill, default");
		textField_22.setColumns(10);
		
		textField_23 = new JTextField();
		add(textField_23, "12, 12, fill, default");
		textField_23.setColumns(10);
		
		textField_24 = new JTextField();
		add(textField_24, "14, 12, fill, default");
		textField_24.setColumns(10);
		
		JButton btnConvolve = new JButton("Convolve");
		add(btnConvolve, "18, 12");
	}

	

}
