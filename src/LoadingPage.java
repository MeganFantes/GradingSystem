import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import Model.ParentNode;
import java.io.File;
import java.nio.file.FileSystems;
import java.util.Arrays;
import java.util.Collections;

public class LoadingPage extends JFrame {

	private JFrame f=new JFrame();
	private JList list_1;
	private JList list;
	private JPanel contentPane;
	private boolean isChanging=false;
	private ParentNode root;

/**
 * Launch the application.
public void initial()
{

}

/**
 * Create the frame.
 */
	public LoadingPage(JFrame parent,ParentNode root) {
		this.root=root;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 561, 418);


		contentPane = new JPanel();
		f.add(contentPane);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("In Progress");
		lblNewLabel.setBounds(5, 5, 499, 18);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);

		JLabel lblClosedClasses = new JLabel("Closed Classes");
		lblClosedClasses.setHorizontalAlignment(SwingConstants.CENTER);
		lblClosedClasses.setBounds(167, 139, 186, 18);
		contentPane.add(lblClosedClasses);



		list_1 = new JList();
		list_1.setListData(allFileInFolder(GradingSystem.pastCourseFolder));
		list_1.setBounds(81, 170, 372, 80);
		list_1.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!isChanging)
				{
					isChanging =true;
					Click_list1(e);
					isChanging= false;
				}
			}
		});
		list_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt3) {
				JList list = (JList)evt3.getSource();
				ClosedClassActionPerformed(evt3);
			}
		});
		contentPane.add(list_1);

		JScrollPane scrollPane_1 = new JScrollPane(list_1);
		scrollPane_1.setBounds(81, 170, 372, 80);
		contentPane.add(scrollPane_1);

		JButton LoadButton = new JButton("Create a new class");
		LoadButton.setBounds(184, 299, 186, 27);
		//LoadButton.setUI(new BEButtonUI(). setNormalColor(BEButtonUI.NormalColor.lightBlue));
		LoadButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				LoadButtonActionPerformed(evt);
			}
		});
		contentPane.add(LoadButton);

		list = new JList();
		list.setBounds(81, 36, 372, 89);
		list.setListData(allFileInFolder(GradingSystem.currCourseFolder));
		list.addListSelectionListener(new ListSelectionListener() {
			  public void valueChanged(ListSelectionEvent e) {
					if (!isChanging)
					{
						isChanging =true;
						Click_list(e);
						isChanging= false;
					}
				 }
			  });
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt2) {
				ProgressClassActionPerformed(evt2);
			}
		});
		contentPane.add(list);

		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(81, 36, 372, 89);
		contentPane.add(scrollPane);
//		setLocationRelativeTo(parent);
		// make the JFrame appear in the middle of the screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
		setVisible(true);
	}
	public void ProgressClassActionPerformed(MouseEvent arg0)
	{
		JList list = (JList)arg0.getSource();
		if (arg0.getClickCount() == 2) {
			// Double-click detected
			String fileToRead = GradingSystem.currCourseFolder + list.getSelectedValue();
			System.out.println("opening Curr course: " + fileToRead);
			this.dispose();
			GradingSystem.controller.openCourse(fileToRead);
			GradingSystem.controller.setCurrentClass(true);
			ClassHome classHome = new ClassHome();
		}
	}
	public void ClosedClassActionPerformed(MouseEvent arg0)
	{
		JList list = (JList)arg0.getSource();
		if (arg0.getClickCount() == 2) {
			// Double-click detected
			String fileToRead = GradingSystem.pastCourseFolder + list_1.getSelectedValue();
			//String absolutePath = FileSystems.getDefault().getPath(fileToRead).normalize().toAbsolutePath().toString();
			System.out.println("opening Past course: " + fileToRead);
			this.dispose();
			GradingSystem.controller.openCourse(fileToRead);
			GradingSystem.controller.setCurrentClass(false);
			ClassHome classHome = new ClassHome();
		}
	}
	
	public void LoadButtonActionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		/*to jump*/
		CreateClass frame = new CreateClass(this,root);
		frame.setTitle("Create a class");
		frame.setVisible(true);
		this.dispose();
	}
	public void Click_list(ListSelectionEvent e) {
		list_1.clearSelection();
	}
	public void Click_list1(ListSelectionEvent e) {
		list.clearSelection();
	}

	public String[] allFileInFolder(String folderPath){
		final File folder = new File(folderPath);
		ArrayList<String> files = new ArrayList<>();
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				// do nothing
			} else {
				if (fileEntry.getName().contains("ser"))
					files.add(fileEntry.getName());
			}
		}
		Collections.sort(files);
		Collections.reverse(files);

		String[] ret = new String[files.size()];
		for (int i=0; i<files.size(); i++)
			ret[i] = files.get(i);

		return ret;
	}

	public static void main(String[] args){
		//LoadingPage.allFileInFolder(GradingSystem.currCourseFolder);
	}
}
