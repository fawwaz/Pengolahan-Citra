package ui;

import javax.swing.JPanel;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JComponent;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Insets;

import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;

public class ConvolutionFormPanel extends JPanel{
	private JSplitPane splitPane;
	private JPanel panel;
	private JPanel panel_1;
	public ConvolutionFormPanel() {
		JSplitPane splitpane = new JSplitPane(
				JSplitPane.HORIZONTAL_SPLIT,
				true,
				BuildRightPanel(),
				BuildLeftPanel()
				);
	}
	

	private JComponent BuildRightPanel(){
		FormLayout layout = new FormLayout("30dlu:grow","pref, 3dlu, pref, 3dlu, pref");
		PanelBuilder builder = new PanelBuilder(layout);
		builder.setDefaultDialogBorder();

		CellConstraints cc = new CellConstraints();

		builder.add(buildMinimumSizePanel(), cc.xy(1, 1));
		builder.add(buildDefaultSizePanel(), cc.xy(1, 3));
		builder.add(buildPreferredSizePanel(), cc.xy(1, 5));

		return builder.getPanel();
	}

	private Component buildPreferredSizePanel() {
		FormLayout layout = new FormLayout("right:max(25dlu;pref), 3dlu, pref",	"pref");
		DefaultFormBuilder builder = new DefaultFormBuilder(layout);
		builder.append("Pref", new JTextField(15));
		return builder.getPanel();
	}

	private Component buildDefaultSizePanel() {
		FormLayout layout = new FormLayout("right:max(25dlu;pref), 3dlu, default", "pref");
		DefaultFormBuilder builder = new DefaultFormBuilder(layout);
		builder.append("Default", new JTextField(15));
		return builder.getPanel();
	}

	private Component buildMinimumSizePanel() {
		FormLayout layout = new FormLayout("right:max(25dlu;pref), 3dlu, min","pref");
		DefaultFormBuilder builder = new DefaultFormBuilder(layout);
		builder.append("Min", new JTextField(15));
		return builder.getPanel();
	}
	
	
	private JComponent BuildLeftPanel() {
		JTextArea textArea = new JTextArea(5, 20);
        textArea.setEditable(false);
        textArea.setMargin(new Insets(6, 10, 4, 6));
        textArea.setText("The text field used in the example on the left\n" +
        "has a narrow minimum width and a wider preferred width.\n\n" +
        "If you move the split divider to the left and right\n" +
        "you can see how 'Default' shrinks the field if space is scarce.\n\n" +
        "If there's not enough space for the preferred width\n" + 
        "the bottom field will be 'cut' on the right-hand side.");
        JScrollPane scrollpane = new JScrollPane(textArea);
        scrollpane.setBorder(new EmptyBorder(0, 0, 0, 0));
        return scrollpane;
	}

}
