import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NavigationButtonBanner extends JPanel {
	// buttons on the panel
	private static JButton btnClassHome;
	private static JButton btnCalcFinalGrade;
	private static JButton btnAddColumn;
	public NavigationButtonBanner(JFrame callingFrame) {
		// TODO: add button functionality

		btnClassHome = new javax.swing.JButton();
		btnAddColumn = new javax.swing.JButton();
		btnCalcFinalGrade = new javax.swing.JButton();

		btnClassHome.setText("Class Home");
		btnClassHome.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ClassHome classHome = new ClassHome();
				callingFrame.dispose();
			}
		});

		btnAddColumn.setText("Add Column");
		btnAddColumn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		btnCalcFinalGrade.setText("Calculate Final Grade");
		btnCalcFinalGrade.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {

			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								          .addGap(5)
								          .addComponent(btnClassHome)
								          .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 385, Short.MAX_VALUE)
								          .addComponent(btnCalcFinalGrade)
								          .addGap(5)
								          .addComponent(btnAddColumn)
								          .addGap(5))
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								          .addGap(5)
								          .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										                    .addComponent(btnClassHome, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
										                    .addComponent(btnCalcFinalGrade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										                    .addComponent(btnAddColumn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								          .addGap(5))
		);
	}
}
