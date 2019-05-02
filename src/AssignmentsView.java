import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * This class holds the VIEW of assignment grades in the grading system.
 * The assignment grades are the students' grades for any individual assignment,
 * such as a homework, an exam, or a project.
 *
 * The CONTENT of the view is created in the AssignmentsTablePanel class.
 */

public class AssignmentsView {
	private JFrame frame;
	private final Dimension frameDimension = new Dimension(800, 400); // size of the frame in the screen

	public AssignmentsView(Object category) {
		// create JFrame
		frame = new JFrame("View " + category.toString());

		// add the navigation buttons to the frame
		frame.getContentPane().add(new NavigationButtonBanner(frame), BorderLayout.NORTH);

		// add table
		JPanel tablePanel = new AssignmentsTablePanel(category, frame);
		frame.getContentPane().add(tablePanel);

		// make the JFrame visible
		JScrollPane scroll = new JScrollPane(tablePanel);
		scroll.setColumnHeaderView(((AssignmentsTablePanel) tablePanel).getHeaderPanel()); // freeze the header row so you can see it as you scroll
		frame.getContentPane().add(scroll); // this line adds the table to the frame (you do not need a separate panel)
		frame.setSize(frameDimension);
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
