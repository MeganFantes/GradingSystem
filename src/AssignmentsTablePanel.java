import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * This class created the CONTENT of the assignments view window. This is the window where
 * Christine can see individual assignment grades for the students in the class, such
 * as homework grades or exam grades.
 *
 * The content defined here is placed in a visible JFrame in the AssignmentsView class.
 */

public class AssignmentsTablePanel extends JPanel {
	// The assignments view is a table of rows and columns
	// define the table components here:
	private Object[] headerLabels;
	private Object[][] rows;
	private JPanel headerPanel;
	private JFrame callingFrame;

	// define the size of all buttons in the table here, so all table components are consistent
	private final Dimension buttonSize = new Dimension(100, 25);

	// define size of table components here
	private final int panelWidth;
	private final Dimension headerPanelSize;
	private final Dimension rowPanelSize;

	// object to hold the category of assignments that are being displayed
	// eg. Homework, Exams, or Projects
	// this is the category that was clicked on in the ClassHome view to open the assignments view
	private Object category;

	public AssignmentsTablePanel(Object category, JFrame callingFrame) {
		this.headerLabels = GradingSystem.controller.getAssignmentViewHeader(category);
		this.rows = GradingSystem.controller.getAssignmentViewRows(category);
		this.panelWidth = headerLabels.length*buttonSize.width;
		this.headerPanelSize = new Dimension(panelWidth, buttonSize.height + 5);
		this.rowPanelSize = new Dimension(panelWidth, buttonSize.height);
		this.callingFrame = callingFrame;
		this.category = category;
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
			// This is different because it opens up a popup of options to display student information
			if (i == 0) {
				button = new BtnStudentHeader(headerLabels[i], callingFrame, category);
			}
			// add functionality of the rest of the header buttons (the assignment title buttons)
			// The rest of the header buttons are the titles of individual assignments,
			// clicking them opens a popup to edit an assignment or enter grades
			else {
				button = new BtnAssignmentHeader(headerLabels[i], category, callingFrame);
			}
			// set the size of the buttons, for visual consistency
			button.setPreferredSize(buttonSize);
			headerPanel.add(button);
		}
		// add the header to the assignments view table
		add(headerPanel);
	}

	private void addRows() {
		for (int row = 0; row < rows.length; row++) {
			JPanel rowPanel = new JPanel(new GridLayout(0, rows[row].length));
			rowPanel.setPreferredSize(rowPanelSize);

			// set functionality of the GRADING OPTIONS row
			// This row is different because it displays the way the grades for an assignment are entered
			// clicking on this row opens the Grading Options popup
			if (row == 0) {
				for (int col = 0; col < rows[row].length; col++) {
					// Set the label of the grading options row (it is not a clickable button)
					if (col == 0) {
						JLabel cellLabel = new JLabel(rows[row][col].toString());
						cellLabel.setPreferredSize(buttonSize);
						rowPanel.add(cellLabel);
					}
					// make the clickable buttons of the grading options row, so you can click on the button
					// and open the popup to change the grading option
					else {
						JButton cellButton = new BtnAssignmentGradingOption(rows[row][col]);
						cellButton.setPreferredSize(buttonSize);
						rowPanel.add(cellButton);
					}
				}
			}

			// set functionality of the TOTAL POINTS row
			// This row is different because it displays the total points for an assignment
			// clicking on this row opens the Total Points popup, which allows you to edit the
			// total points for an assignment
			else if (row == 1) {
				for (int col = 0; col < rows[row].length; col++) {
					// Set the label of the total points row (it is not a clickable button)
					if (col == 0) {
						JLabel cellLabel = new JLabel(rows[row][col].toString());
						cellLabel.setPreferredSize(buttonSize);
						rowPanel.add(cellLabel);
					}
					// make the clickable buttons of the total points row, so you can click on the button
					// and open the popup to change the number of points for an assignment
					else {
						JButton cellButton = new BtnTotalPoints(rows[row][col]);
						cellButton.setPreferredSize(buttonSize);
						rowPanel.add(cellButton);
					}
				}
			}

			// set functionality of the AVERAGE row
			// This row is different because it displays the average grade for an assignment
			// clicking on this row opens the Summary Statistics popup to display the summary statistics for
			// the grades for an assignment
			else if (row == 2) {
				for (int col = 0; col < rows[row].length; col++) {
					// Set the label of the average row (it is not a clickable button)
					if (col == 0) {
						JLabel cellLabel = new JLabel(rows[row][col].toString());
						cellLabel.setPreferredSize(buttonSize);
						rowPanel.add(cellLabel);
					}
					// make the clickable buttons of the average row, so you can click on the button
					// and open the popup to see the summary statistics for the assignment
					else {
						JButton cellButton = new BtnAssignmentAverage(rows[row][col]);
						cellButton.setPreferredSize(buttonSize);
						rowPanel.add(cellButton);
					}
				}
			}

			// set functionality of the rest of the rows (the assignment grades rows)
			// These rows are different from the above rows because they display a student's
			// grade for an assignment, and when you click them they open a popup to edit the
			// the student's grade and add a note for that student for that grade
			else {
				for (int col = 0; col < rows[row].length; col++) {
					// Set the clickable student information button
					JButton cellButton;
					if (col == 0) {
						cellButton = new BtnStudent(rows[row][col]);
					}
					// make the clickable assignment grade button, so you can click on a grade
					// and open the Assignment Grade popup to edit the grade or add a note
					else {
						cellButton = new BtnAssignmentGrade(rows[row][col]);
					}
					cellButton.setPreferredSize(buttonSize);
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

/**
 * Below are the buttons that are displayed in the Assignments Table.
 *
 * They are kept in the same file to logically group them together with the assignments table,
 * to show that they are buttons that are unique to that view.
 */

/*
Clicking this button opens a popup where you can choose how student information is displayed.
You can choose to display student names, student emails, or student IDs
 */
class BtnStudentHeader extends JButton {
	BtnStudentHeader(Object student, JFrame frame, Object category){
		super(student.toString());
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StudentPool s = (StudentPool)(((Dummy) student).getRealObject());
				if (s == null) {
					loadStudentInfo();
					frame.dispose();
					if (GradingSystem.controller.getCurrentState() != GradingSystem.controller.getRoot()){
						// now we're at assignment view
						AssignmentsView assignmentsView = new AssignmentsView(GradingSystem.controller.getCurrentState());
					} else {
						// we're at class home view
						ClassHome classHome = new ClassHome();
					}
				}
				else {
					Popup_StudentInfo p = new Popup_StudentInfo (s.getDisplayOption(), frame, category);
				}
			}
		});
	}

	BtnStudentHeader(Object student, JFrame frame) {
		super(student.toString());
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StudentPool s = (StudentPool)(((Dummy) student).getRealObject());
				if (s == null) {
					loadStudentInfo();
					frame.dispose();
					if (GradingSystem.controller.getCurrentState() != GradingSystem.controller.getRoot()){
						// now we're at assignment view
						AssignmentsView assignmentsView = new AssignmentsView(GradingSystem.controller.getCurrentState());
					} else {
						// we're at class home view
						ClassHome classHome = new ClassHome();
					}
				}
				else {
					Popup_StudentInfo p = new Popup_StudentInfo (s.getDisplayOption(), frame);
				}
			}
		});
	}

	private void loadStudentInfo(){
		JFileChooser jfc=new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY );
		jfc.showDialog(new JLabel(), "Choose a CSV file");
		File file=jfc.getSelectedFile();
		if(file.isFile()){
			System.out.println("File name: "+file.getAbsolutePath());
		}
		System.out.println(jfc.getSelectedFile().getName());
		String path=file.getAbsolutePath();
		StudentPool studentPool = new StudentPool();
		studentPool.importFromCsv(path);
		GradingSystem.controller.getRoot().connectStudentPool(studentPool);
	}
}

/*
Clicking this button opens a popup to edit the name and weight for an assignment, as well
as the grades for the assignment
 */
class BtnAssignmentHeader extends JButton {
	BtnAssignmentHeader(Object assignment, Object category, JFrame callingFrame) {
		super(assignment.toString());
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Popup_AssignmentHeader popup_assignmentHeader = new Popup_AssignmentHeader(assignment, category, callingFrame);
			}
		});
	}
}

/*
Clicking this button opens a popup to edit the way the grades of an assignment are entered, either
percentage, raw score, or point deduction
 */
class BtnAssignmentGradingOption extends JButton {
	BtnAssignmentGradingOption(Object label) {
		super(label.toString());
		JButton callingBtn = this;
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Popup_GradingOption p = new Popup_GradingOption(label, callingBtn);
			}
		});
	}
}

/*
Clicking this button opens a popup to edit the total number of points for a specific assignment
 */
class BtnTotalPoints extends JButton {
	BtnTotalPoints(Object label) {
		super(((LeafNode) label).getTotalScore().toString());
		JButton callingBtn = this;
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Popup_Total p = new Popup_Total(label, callingBtn);
			}
		});
	}
}

/*
Clicking this button opens a popup to view the summary statistics for an assignment
 */
class BtnAssignmentAverage extends JButton {
	BtnAssignmentAverage(Object label) {
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

/*
Clicking this button opens a popup to view a specific student's information
(Name, email, student ID, class year)
 */
class BtnStudent extends JButton {
	BtnStudent(Object label) {
		super(label.toString());
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Popup_Student s = new Popup_Student(label);
			}
		});
	}
}

/*
Clicking this button opens a popup to edit a student's grade for an assignment and add a note for their grade
 */
class BtnAssignmentGrade extends JButton {
	BtnAssignmentGrade(Object grade) {
		super(grade.toString());
		boolean hasNote = ((NoteInterface) grade).hasNote();
		if (((NoteInterface) grade).hasNote()) setBackground(Color.CYAN);
		JButton callingButton = this;
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Popup_AssignmentGrade popup_assignmentGrade = new Popup_AssignmentGrade(grade, callingButton);
			}
		});

	}
}