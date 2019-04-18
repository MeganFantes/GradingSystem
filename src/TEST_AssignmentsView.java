// Example from http://www.crionics.com/products/opensource/faq/swing_ex/SwingExamples.html
// Megan author

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


/**
 * @version 1.0 11/09/98
 */
public class TEST_AssignmentsView { //extends JFrame {
	static JFrame f;
	static JLabel l;
	private static JButton b;
	private static JButton b1;
	private static JButton b2;
	private static JButton b3;
	private static JButton a;
	private static JButton a1;
	private static JButton a2;
	private static JButton a3;

	JPanel p = new JPanel(new GridLayout(0,4));
	JPanel p2 = new JPanel(new BorderLayout());
//	JPanel p3 = new JPanel(new BorderLayout());

	public TEST_AssignmentsView() {
//		super("JButtonTable Example");
		f = new JFrame("panel");
		l = new JLabel("panel label");
		b = new JButton("Back");
		b1 = new JButton("Home");
		b2 = new JButton("Calculate Final Grade");
		b3 = new JButton("Add Column");
//		a = new JButton("Student");
//		a1 = new JButton("Homework");
//		a2 = new JButton("Exam");
//		a3 = new JButton("Project");
		p.add(b);
		p.add(b1);
		p.add(b2);
		p.add(b3);
//		p3.add(a);
//		p3.add(a1);
//		p3.add(a2);
//		p3.add(a3);
		DefaultTableModel dm = new DefaultTableModel();
		dm.setDataVector(new Object[][] {
				{ "Ghadah", "foo", "a" },
				{ "Megan", "bar", "b" },
				{ "Yueh", "test", "c"},
				{ "Yuansheng", "test", "d"}
		}, new Object[] { "Student","Participation", "Homework", "Exam", "Project" });

//		DefaultTableModel dm = new DefaultTableModel();
//		dm.setDataVector(new Object[][] {
//				{ "button 1", "foo", "a" },
//				{ "button 2", "bar", "b" },
//				{ "button 3", "test", "c"}
//		}, new Object[] { "Student", "Homework", "Exam", "Project", "Participation" });

		JTable table = new JTable(dm);

		TableColumn colStudent = table.getColumnModel().getColumn(0);
		JButton btnStudent = new JButton("Student");
		btnStudent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(btnStudent, "Student!");
			}
		});
		colStudent.setHeaderRenderer(new TEST_ButtonRenderer_Header(btnStudent));

		TableColumn colHomework = table.getColumnModel().getColumn(1);
		JButton btnHomework = new JButton("Homework");
		btnHomework.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(btnHomework, "Homework!");
			}
		});
		colHomework.setHeaderRenderer(new TEST_ButtonRenderer_Header(btnHomework));

		TableColumn colExam = table.getColumnModel().getColumn(2);
		JButton btnExam = new JButton("Exam");
		btnExam.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(btnExam, "Exam!");
			}
		});
		colExam.setHeaderRenderer(new TEST_ButtonRenderer_Header(btnExam));

		TableColumn colProject = table.getColumnModel().getColumn(3);
		JButton btnProject = new JButton("Project");
		btnProject.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(btnProject, "Project!");
			}
		});
		colProject.setHeaderRenderer(new TEST_ButtonRenderer_Header(btnProject));

		TableColumn colParticipation = table.getColumnModel().getColumn(4);
		JButton btnParticipation = new JButton("Participation");
		btnParticipation.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(btnParticipation, "Participation!");
			}
		});
		colParticipation.setHeaderRenderer(new TEST_ButtonRenderer_Header(btnParticipation));
//		JButton button = new JButton("Homework");
//		button.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				JOptionPane.showMessageDialog(button, "You have clicked me");
//			}
//		});
//		JTableHeader header = table.getTableHeader();
//		header.setLayout(new FlowLayout(FlowLayout.TRAILING, 5, 0));
//		header.add(button);
//		table.getColumn("Homework").setHeaderRenderer(new ButtonRenderer());
//		header.getColumnModel().getColumn(1).setCellEditor(new ButtonEditor_Grade(new JCheckBox()));
//		table.setTableHeader(null);
//		TableColumn tc = table.getColumnModel().getColumn(1);
//		tc.setCellEditor(new ButtonEditor_Grade(new JCheckBox()));
//		tc.setCellRenderer(new ButtonRenderer());
//		tc.setHeaderRenderer(new ButtonRenderer());
//		table.getColumn("Student").setCellRenderer(new ButtonRenderer());
//		table.getColumn("Student").setCellEditor(new ButtonEditor_Grade(new JCheckBox()));
//		table.getColumn("Exam").setCellRenderer(new ButtonRenderer());
//		table.getColumn("Exam").setCellEditor(new ButtonEditor_Grade(new JCheckBox()));
//		JScrollPane scroll = new JScrollPane(table);
//		getContentPane().add(scroll);
//		setSize(400, 100);
//		setVisible(true);

		table.getColumn("Student").setCellRenderer(new ButtonRenderer());
		table.getColumn("Student").setCellEditor(new ButtonEditor_Grade(new JCheckBox()));
		table.getColumn("Participation").setCellRenderer(new ButtonRenderer());
		table.getColumn("Participation").setCellEditor(new ButtonEditor_Grade(new JCheckBox()));
		table.getColumn("Homework").setCellRenderer(new ButtonRenderer());
		table.getColumn("Homework").setCellEditor(new ButtonEditor_Grade(new JCheckBox()));
		table.getColumn("Exam").setCellRenderer(new ButtonRenderer());
		table.getColumn("Exam").setCellEditor(new ButtonEditor_Grade(new JCheckBox()));
		table.getColumn("Project").setCellRenderer(new ButtonRenderer());
		table.getColumn("Project").setCellEditor(new ButtonEditor_Grade(new JCheckBox()));
		f.getContentPane().add(p,BorderLayout.NORTH);
		//f.getContentPane().add(p3, BorderLayout.);
		p2.add(table);
		f.getContentPane().add(p2, BorderLayout.SOUTH);
		JScrollPane scroll = new JScrollPane(table);
		f.getContentPane().add(scroll);
		f.setSize(400, 100);
		f.setVisible(true);
	}

	public static void main(String[] args) {
		TEST_AssignmentsView frame = new TEST_AssignmentsView();
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
}