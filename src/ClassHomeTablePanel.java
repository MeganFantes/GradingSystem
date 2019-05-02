import Model.Statistics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class created the CONTENT of the class home view window. This is the window where
 * Christine can see the average category grades for the students in the class, where
 * categories are homework, exams, projects, participation, etc. This is also the
 * central page for the class, where she can click to view and enter individual grades.
 *
 * The content defined here is placed in a visible JFrame in the ClassHome class.
 */

public class ClassHomeTablePanel extends JPanel {
	private Object[] headerLabels;
	private Object[][] rows;
	private JPanel headerPanel;
	private JFrame callingFrame;
	private final Dimension buttonSize = new Dimension(100, 25);
	private final int panelWidth;
	private final Dimension headerPanelSize;
	private final Dimension rowPanelSize;

	public ClassHomeTablePanel(JFrame callingFrame) {
		this.headerLabels = GradingSystem.controller.getClassSummaryViewHeader();
		this.rows = GradingSystem.controller.getClassSummaryViewRows();
		this.panelWidth = headerLabels.length*buttonSize.width;
		this.headerPanelSize = new Dimension(panelWidth, buttonSize.height + 5);
		this.rowPanelSize = new Dimension(panelWidth, buttonSize.height);
		this.callingFrame = callingFrame;
		setLayout(new GridLayout(rows.length + 1, 1));
		addHeader();
		addRows();
	}

	private void addHeader() {
		headerPanel = new JPanel(new GridLayout(0, headerLabels.length));
		headerPanel.setPreferredSize(headerPanelSize);
		for (int i = 0; i < headerLabels.length; i++) {
			JButton button;
			// add functionality of the STUDENT header button
			if (i == 0) {
				button = new BtnStudentHeader(headerLabels[i], callingFrame);
				button.setPreferredSize(buttonSize);
				headerPanel.add(button);
			}
			// make the FINAL SCORE header a label, not a button
			else if (i == headerLabels.length - 1) {
				JLabel label = new JLabel(headerLabels[i].toString());
				label.setPreferredSize(buttonSize);
				headerPanel.add(label);
			}
			// add functionality of the rest of the header buttons (the assignment title buttons)
			else {
				button = new BtnCategoryHeader(headerLabels[i], callingFrame);
				button.setPreferredSize(buttonSize);
				headerPanel.add(button);
			}
		}
		add(headerPanel);
	}

	private void addRows() {
		for (int row = 0; row < rows.length; row++) {
			JPanel rowPanel = new JPanel(new GridLayout(0, rows[row].length));
			rowPanel.setPreferredSize(rowPanelSize);
			// set functionality of the WEIGHTS row
			if (row == 0) {
				for (int col = 0; col < rows[row].length; col++) {
					// Set the labels of the weights row (none of them are a clickable button)
					JLabel cellLabel = new JLabel(rows[row][col].toString());
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
			// set labels of the rest of the rows (the student category total grades rows)
			// these are NOT clickable buttons, because they are just a calculation of other grades
			else {
				for (int col = 0; col < rows[row].length; col++) {
					// Set the clickable student information button
					if (col == 0) {
						JButton cellButton = new BtnStudent(rows[row][col]);
						cellButton.setPreferredSize(buttonSize);
						rowPanel.add(cellButton);
					}
					// make the clickable assignment grade button, so you can click on a grade and edit the grade or add a note
					else {
						JLabel cellLabel;
						cellLabel = new JLabel(rows[row][col].toString());
						cellLabel.setPreferredSize(buttonSize);
						rowPanel.add(cellLabel);
					}
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
	BtnCategoryHeader(Object category, JFrame callingFrame) {
		super(category.toString());
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GradingSystem.controller.setCurrentState(GradingSystem.controller.getAssignmentChild(category));
				AssignmentsView assignmentsView = new AssignmentsView(category);
				callingFrame.dispose();
			}
		});
	}
}

class BtnCategoryAverage extends JButton {
	BtnCategoryAverage(Object label) {
		super(label.toString());
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Statistics stat = (Statistics) label;
				Popup_Average p = new Popup_Average(stat);
			}
		});
	}
}

////TODO: add a popup for this, will be exactly the same as assignment grade, just make sure it edits the right type of object
//class BtnCategoryGrade extends JButton {
//	BtnCategoryGrade(Object label) {
//		super(label.toString());
//		addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				JOptionPane.showMessageDialog(getParent(), "You clicked on a category grade, you will be able to edit the grade and add a note here");
//			}
//		});
//	}
//}