import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * This class holds the VIEW of "class home" for a class, which can also be called the
 * "summary view" of the class. You can see each category for the class -- categories can be
 * homework, exams, projects, etc. -- and each student's average grade in each category.
 *
 * The CONTENT of the view is created in the ClassHomeTablePanel class.
 */

public class ClassHome {
	private JFrame frame;

	public ClassHome() {
		// create JFrame
		frame = new JFrame("Class Home");

		// add the navigation buttons to the frame
		frame.getContentPane().add(new NavigationButtonBanner(frame), BorderLayout.NORTH);

		// add table
		JPanel tablePanel = new ClassHomeTablePanel(frame);
		frame.getContentPane().add(tablePanel);

		// make the JFrame visible
		JScrollPane scroll = new JScrollPane(tablePanel);
		scroll.setColumnHeaderView(((ClassHomeTablePanel) tablePanel).getHeaderPanel()); // freeze the header row so you can see it as you scroll
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
}
