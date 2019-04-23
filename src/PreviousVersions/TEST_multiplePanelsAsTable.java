package PreviousVersions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TEST_multiplePanelsAsTable {
	private JFrame frame;
	public TEST_multiplePanelsAsTable() {
		frame = new JFrame("Testing Multiple Tables");

		// add the navigation buttons to the frame
//		frame.getContentPane().add(new NavigationButtonBanner(), BorderLayout.NORTH);

		// main panel
		JPanel mainPanel = new JPanel(new GridLayout(50,1));
		// make button panels

		for (int i = 1; i < 50; i++) {
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(0, 2));
			JButton button1 = new JButton("a");
			panel.add(button1);
			JButton button2 = new JButton("b");
			panel.add(button2);
			mainPanel.add(panel);
		}
//		JPanel header = new JPanel();
//		header.setLayout(new GridLayout(0, 2));
//		JButton button1 = new JButton("a");
//		header.add(button1);
//		JButton button2 = new JButton("b");
//		header.add(button2);
//		mainPanel.add(header);
//
//		JPanel row1 = new JPanel();
//		row1.setLayout(new GridLayout(0,2));
//		JButton button3 = new JButton("c");
//		row1.add(button3);
//		JButton button4 = new JButton("d");
//		row1.add(button4);
//		mainPanel.add(row1, BorderLayout.SOUTH);

		frame.getContentPane().add(mainPanel);

		// make the JFrame visible
		JScrollPane scroll = new JScrollPane(mainPanel);
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

	public static  void main(String[] args) {
		TEST_multiplePanelsAsTable mt = new TEST_multiplePanelsAsTable();
	}
}
