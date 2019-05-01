import Model.ParentNode;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class NavigationButtonBanner extends JPanel {
	// buttons on the panel
	private static JButton btnClassHome;
	private static JButton btnCalcFinalGrade;
	private static JButton btnAddColumn;
	private static JButton btnExitClass;

	public NavigationButtonBanner(JFrame callingFrame) {

		btnClassHome = new JButton();
		btnAddColumn = new JButton();
//		btnAddColumn = new BtnAddColumn(callingFrame);
		btnCalcFinalGrade = new JButton();
		btnExitClass = new JButton();

		btnClassHome.setText("Class Home");
		btnClassHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				GradingSystem.controller.setCurrentState(GradingSystem.controller.getRoot());
				ClassHome classHome = new ClassHome();
				callingFrame.dispose();
			}
		});

		// This is where we check if it is a past course or a current course
		// if it is an active course, add a button to SAVE a class
		// if it is a past course, add a button only to EXIT without saving
		if (GradingSystem.controller.getIsCurrentClass()) {
			String text = "Save and Exit Class";
			btnExitClass.setText(text);
			btnExitClass.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// add functionality to SAVE and exit
					String outputfileName = GradingSystem.currCourseFolder + GradingSystem.controller.getRoot().getCriteria() + ".ser";
					try {
						FileOutputStream fs = new FileOutputStream(outputfileName);
						ObjectOutputStream objs = new ObjectOutputStream(fs);
						objs.writeObject(GradingSystem.controller.getRoot());
						objs.close();
						System.out.println("write successfully");
					} catch (Exception ex){
						ex.printStackTrace();
					}
					// clear root in controller, otherwise, student info remains there
					GradingSystem.controller.setRoot(new ParentNode());
					// now bring the user back to the Loading page
					LoadingPage loadingPage = new LoadingPage(new JFrame(), GradingSystem.controller.getRoot());
					callingFrame.dispose();
				}
			});
		}
		else {
			String text = "Exit Class";
			btnExitClass.setText(text);
			btnExitClass.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// clear root in controller, otherwise, student info remains there
					GradingSystem.controller.setRoot(new ParentNode());
					// just bring the user back to the Loading page
					LoadingPage loadingPage = new LoadingPage(new JFrame(), GradingSystem.controller.getRoot());
					callingFrame.dispose();
				}
			});
		}

		btnAddColumn.setText("Add Column");
		// if we are NOT at the root, i.e. we are NOT in the Class Summary View,
		// add functionality to the Add Column button
		if (GradingSystem.controller.getCurrentState() != GradingSystem.controller.getRoot()) {
			btnAddColumn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Popup_AddColumn p = new Popup_AddColumn(callingFrame);
				}
			});
		}

		btnCalcFinalGrade.setText("Calculate Final Grade");
		btnCalcFinalGrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ArrayList<String> errorMessages = GradingSystem.controller.getRoot().treeValidation(null, false);
				if (errorMessages.size() > 0) {
					String displayString = "";
					for (int i = 0; i < errorMessages.size(); i++) {
						int displayIndex = i + 1;
						displayString = displayString + displayIndex + ") " + errorMessages.get(i) + "\n";
					}
					JOptionPane.showMessageDialog(callingFrame, displayString);
				}
				else {
					GradingSystem.controller.computeFinalScore();
					ClassHome classHome = new ClassHome();
					callingFrame.dispose();
				}
			}
		});

		// set navigation button panel layout
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								          .addGap(5)
								          .addComponent(btnClassHome)
											.addGap(5)
											.addComponent(btnExitClass)
								          .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 385, Short.MAX_VALUE)
								          .addComponent(btnCalcFinalGrade)
								          .addGap(5)
								          .addComponent(btnAddColumn)
								          .addGap(5))
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								          .addGap(5)
								          .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										                    .addComponent(btnClassHome, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
										                    .addComponent(btnExitClass, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										                    .addComponent(btnCalcFinalGrade, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										                    .addComponent(btnAddColumn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								          .addGap(5))
		);
	}
}

//class BtnAddColumn extends JButton {
//	public BtnAddColumn(JFrame callingFrame) {
//		super();
//
//		addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				Popup_AddColumn popup_addColumn = new Popup_AddColumn(callingFrame);
//				AssignmentsView assignmentsView = new AssignmentsView(GradingSystem.controller.getCurrentState());
//			}
//		});
//	}
//}