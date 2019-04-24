import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ClassHome {
//	private Object[] headerLabels;
//	private Object[][] rows;
	private JFrame frame;
//	private static Controller controller;

	public ClassHome() {
		// TODO: update function after you calculate final score
		// read the input file
//		try {
			// read the headerLabels
//			BufferedReader br = new BufferedReader(new FileReader("testValues_classGrades.csv"));
//			headerLabels = br.readLine().split(",");
//			String inputfileName = "./test_course_with_score.ser";
//			ParentNode root = null;
//			try {
//				FileInputStream fis = new FileInputStream(inputfileName);
//				ObjectInputStream objis = new ObjectInputStream(fis);
//				root = (ParentNode) objis.readObject();
//				System.out.println("read successfully");
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			System.out.println(" =====  read tree =====");
//			root.traverse(0);
//			headerLabels = root.genFieldRowArray();
			// read the rows into an ArrayList (because its length is flexible)
//			ArrayList<Object[]> rowsAsAdded = new ArrayList<>();
//			int numRows = 0;
//			String row = br.readLine();
//			while (row != null){
//				rowsAsAdded.add(row.split(","));
//				row = br.readLine();
//				numRows++;
//			}
//			// now add the rows to the final 2D array of rows (now that you know how many rows there are and can declare the array length)
//			rows = new Object[numRows][];
//			for (int i = 0; i < numRows; i++) {
//				rows[i] = rowsAsAdded.get(i);
//			}
//			StudentPool studentPool = root.getStudentPool();
//			rows = root.genSummaryTableArray(studentPool.getPrimaryKeyAndSortBy("last name"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		controller = c;
//		headerLabels = controller.getClassSummaryViewHeader();
//		rows = controller.getClassSummaryViewRows();

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

	public static void main(String[] args) {
		ClassHome classHome = new ClassHome();
	}
}
