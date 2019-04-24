import Model.ParentNode;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class NavigationButtonBanner extends JPanel {
	// buttons on the panel
	private static JButton btnClassHome;
	private static JButton btnCalcFinalGrade;
	private static JButton btnAddColumn;
	public NavigationButtonBanner(JFrame callingFrame) {

		btnClassHome = new javax.swing.JButton();
		btnAddColumn = new javax.swing.JButton();
		btnCalcFinalGrade = new javax.swing.JButton();

		btnClassHome.setText("Class Home");
		btnClassHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ClassHome classHome = new ClassHome();
				callingFrame.dispose();
			}
		});

		btnAddColumn.setText("Add Column");
		btnAddColumn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO: add button functionality
			}
		});

		btnCalcFinalGrade.setText("Calculate Final Grade");
		btnCalcFinalGrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				GradingSystem.controller.computeFinalScore();
				ClassHome classHome = new ClassHome();
				callingFrame.dispose();
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
										                    .addComponent(btnCalcFinalGrade, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										                    .addComponent(btnAddColumn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								          .addGap(5))
		);
	}
}
