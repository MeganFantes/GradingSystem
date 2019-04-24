package PreviousVersions;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

public class Version1_ClassHome {
	private Object[] headerLabels;
	private Object[][] rows;
	private JFrame frame;
	public Version1_ClassHome() {
		// read the input file
		try {
			// read the headerLabels
			BufferedReader br = new BufferedReader(new FileReader("testValues_classGrades.csv"));
			headerLabels = br.readLine().split(",");
			// read the rows into an ArrayList (because its length is flexible)
			ArrayList<Object[]> rowsAsAdded = new ArrayList<>();
			int numRows = 0;
			String row = br.readLine();
			while (row != null){
				rowsAsAdded.add(row.split(","));
				row = br.readLine();
				numRows++;
			}
			// now add the rows to the final 2D array of rows (now that you know how many rows there are and can declare the array length)
			rows = new Object[numRows][];
			for (int i = 0; i < numRows; i++) {
				rows[i] = rowsAsAdded.get(i);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// create the JFrame
		frame = new JFrame("Class Home");

		// add the navigation buttons to the frame
//		frame.getContentPane().add(new NavigationButtonBanner(),BorderLayout.NORTH);

		// create the table to hold assignment grades, each cell will be a clickable button
		DefaultTableModel dm = new DefaultTableModel();
		dm.setDataVector(rows, headerLabels);
		JTable table = new JTable(dm);

		// make the table clickable
		// make the headerLabels clickable
		ButtonRenderer_Header renderer = new ButtonRenderer_Header();
		TableColumnModel model = table.getColumnModel();
		JTableHeader header = table.getTableHeader();
		header.addMouseListener(new ButtonListener_Header(header, renderer));
		for (int i = 0; i < headerLabels.length; i++) {
			Object colTitle = headerLabels[i];
			model.getColumn(i).setHeaderRenderer(renderer);
			// make the student cells clickable
			if (i == 0) {
				// if we are setting the first column, this is the student column
				table.getColumn(colTitle).setCellRenderer(new ButtonRenderer());
				table.getColumn(colTitle).setCellEditor(new ButtonEditor_Student(new JCheckBox()));
			}
		}

		// make the JFrame visible
		JScrollPane scroll = new JScrollPane(table);
		frame.getContentPane().add(scroll); // this line adds the table to the frame (you do not need a separate panel)
		frame.setSize(800, 400);
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		// make the JFrame appear in the middle of the screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
	}

	public static void main(String[] args) {
		Version1_ClassHome av = new Version1_ClassHome();
	}
}
