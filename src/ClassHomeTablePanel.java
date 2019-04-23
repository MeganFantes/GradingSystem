import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class ClassHomeTablePanel extends JPanel {
	private Object[] headerLabels;
	private Object[][] rows;
	private JPanel headerPanel;
	private JFrame callingFrame;
	private final Dimension buttonSize = new Dimension(100, 25);
	private final int panelWidth;
	//	private final int panelWidth = 400;
//	private final Dimension panelSize;
	private final Dimension headerPanelSize;
	private final Dimension rowPanelSize;

	public ClassHomeTablePanel(Object[] headerLabels, Object[][] rows, JFrame callingFrame) {
		this.headerLabels = headerLabels;
		this.rows = rows;
		this.panelWidth = headerLabels.length*buttonSize.width;
//		this.panelSize = new Dimension(panelWidth,(rows.length+1)*buttonSize.height);
		this.headerPanelSize = new Dimension(panelWidth, buttonSize.height + 5);
		this.rowPanelSize = new Dimension(panelWidth, buttonSize.height);
		this.callingFrame = callingFrame;
//		setPreferredSize(panelSize);
		setLayout(new GridLayout(rows.length + 1, 1));
		addHeader();
		addRows();
	}

	private void addHeader() {
		headerPanel = new JPanel(new GridLayout(0, headerLabels.length));
		headerPanel.setPreferredSize(headerPanelSize);
		headerPanel.setMinimumSize(headerPanelSize);
		headerPanel.setMaximumSize(headerPanelSize);
		for (int i = 0; i < headerLabels.length; i++) {
			JButton button;
			// add functionality of the STUDENT header button
			if (i == 0) {
				button = new BtnStudentHeader(headerLabels[i], Arrays.asList(headerLabels).indexOf(headerLabels[i]));
			}
			// add functionality of the rest of the header buttons (the assignment title buttons)
			else {
				button = new BtnCategoryHeader(headerLabels[i], Arrays.asList(headerLabels).indexOf(headerLabels[i]), callingFrame);
			}
			button.setPreferredSize(buttonSize);
			button.setMinimumSize(buttonSize);
			button.setMaximumSize(buttonSize);
			headerPanel.add(button);
		}
		add(headerPanel);
	}

	private void addRows() {
		for (int row = 0; row < rows.length; row++) {
			JPanel rowPanel = new JPanel(new GridLayout(0, rows[row].length));
			rowPanel.setPreferredSize(rowPanelSize);
			rowPanel.setMinimumSize(rowPanelSize);
			rowPanel.setMaximumSize(rowPanelSize);
			// set functionality of the WEIGHTS row
			if (row == 0) {
				for (int col = 0; col < rows[row].length; col++) {
					// Set the labels of the weights row (none of them are a clickable button)
					JLabel cellLabel = new JLabel((String) rows[row][col]);
					rowPanel.add(cellLabel);
				}
			}
			// set functionality of the AVERAGE row
			else if (row == 1) {
				for (int col = 0; col < rows[row].length; col++) {
					// Set the label of the average row (it is not a clickable button)
					if (col == 0) {
						JLabel cellLabel = new JLabel((String) rows[row][col]);
						cellLabel.setPreferredSize(buttonSize);
						cellLabel.setMinimumSize(buttonSize);
						cellLabel.setMaximumSize(buttonSize);
						rowPanel.add(cellLabel);
					}
					// make the clickable buttons of the average row, so you can click on the button and see summary statistics
					else {
						JButton cellButton = new BtnCategoryAverage(rows[row][col]);
						cellButton.setPreferredSize(buttonSize);
						cellButton.setMinimumSize(buttonSize);
						cellButton.setMaximumSize(buttonSize);
						rowPanel.add(cellButton);
					}
				}
			}
			// set functionality of the rest of the rows (the student category total grades rows)
			else {
				for (int col = 0; col < rows[row].length; col++) {
					// Set the clickable student information button
					JButton cellButton;
					if (col == 0) {
						cellButton = new BtnStudent(rows[row][col]);
					}
					// make the clickable assignment grade button, so you can click on a grade and edit the grade or add a note
					else {
						cellButton = new BtnCategoryGrade(rows[row][col]);
					}
					cellButton.setPreferredSize(buttonSize);
					cellButton.setMinimumSize(buttonSize);
					cellButton.setMaximumSize(buttonSize);
					rowPanel.add(cellButton);
				}
			}
			add(rowPanel);
		}
	}

	public JPanel getHeaderPanel() {
		return headerPanel;
	}
}

class BtnCategoryHeader extends JButton {
	BtnCategoryHeader(Object label, int categoryIndex, JFrame callingFrame) {
		super((String) label);
		addActionListener(new AL_CategoryHeader(label, categoryIndex, callingFrame));
	}
}

class AL_CategoryHeader implements ActionListener {
//	JButton callingButton;
	Object callingCategory;
	JFrame callingFrame;
	int categoryIndex;
	public AL_CategoryHeader(Object category, int categoryIndex, JFrame callingFrame) {
		super();
//		this.callingButton = btn;
		this.callingCategory = category;
		this.callingFrame = callingFrame;
		this.categoryIndex = categoryIndex;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		JOptionPane.showMessageDialog(callingButton, "You clicked on the " + callingButton.getText() + " button, you will be able to edit the name and weight of the category here");
		AssignmentsView assignmentsView = new AssignmentsView(categoryIndex);
		callingFrame.dispose();
	}
}

class BtnCategoryAverage extends JButton {
	BtnCategoryAverage(Object label) {
		super((String) label);
		addActionListener(new AL_CategoryAverage(this));
	}
}

class AL_CategoryAverage implements ActionListener {
	JButton callingButton;
	public AL_CategoryAverage(JButton btn) {
		super();
		this.callingButton = btn;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(callingButton, "You are now seeing the summary statistics of a category");
	}
}

class BtnCategoryGrade extends JButton {
	BtnCategoryGrade(Object label) {
		super((String) label);
		addActionListener(new AL_CategoryGrade(this));
	}
}

class AL_CategoryGrade implements ActionListener {
	JButton callingButton;
	public AL_CategoryGrade(JButton btn) {
		super();
		this.callingButton = btn;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(callingButton, "You clicked on a category grade, you will be able to edit the grade and add a note here");
	}
}