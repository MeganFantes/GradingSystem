package PreviousVersions;// Example from http://www.crionics.com/products/opensource/faq/swing_ex/SwingExamples.html
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
//		table.getColumn("Homework").setHeaderRenderer(new PreviousVersions.ButtonRenderer());
//		header.getColumnModel().getColumn(1).setCellEditor(new PreviousVersions.ButtonEditor_Grade(new JCheckBox()));
//		table.setTableHeader(null);
//		TableColumn tc = table.getColumnModel().getColumn(1);
//		tc.setCellEditor(new PreviousVersions.ButtonEditor_Grade(new JCheckBox()));
//		tc.setCellRenderer(new PreviousVersions.ButtonRenderer());
//		tc.setHeaderRenderer(new PreviousVersions.ButtonRenderer());
//		table.getColumn("Student").setCellRenderer(new PreviousVersions.ButtonRenderer());
//		table.getColumn("Student").setCellEditor(new PreviousVersions.ButtonEditor_Grade(new JCheckBox()));
//		table.getColumn("Exam").setCellRenderer(new PreviousVersions.ButtonRenderer());
//		table.getColumn("Exam").setCellEditor(new PreviousVersions.ButtonEditor_Grade(new JCheckBox()));
//		JScrollPane scroll = new JScrollPane(table);
//		getContentPane().add(scroll);
//		setSize(400, 100);
//		setVisible(true);

		table.getColumn("Student").setCellRenderer(new ButtonRenderer());
//		table.getColumn("Student").setCellRenderer(new TableCellRenderer() {
//			@Override
//			public Component getTableCellRendererComponent(
//					JTable table, Object value, boolean isSelected,
//					boolean hasFocus, int row, int column) {
//				// This component should be a JLabel
//				Component component = table.getDefaultRenderer(String.class).getTableCellRendererComponent(table, value,
//								                      isSelected, hasFocus, row, column);
//
//				// Just check for sanity, this is overkill.
//				if (!(component instanceof JLabel))
//					throw new RuntimeException(
//							"Programmer error, wrong type");
//
//				// The component is a label
//				JLabel label = (JLabel) component;
//
//				// This label must show no icon.
//				label.setIcon(null);
//
//				// Build a little panel to hold the controls
//				JPanel panel = new JPanel();
//				panel.setLayout(new BorderLayout());
//
//				// Color appropriately for selection status
//				if (isSelected) {
//					panel.setForeground(table.getSelectionForeground());
//					panel.setBackground(table.getSelectionBackground());
//				} else {
//					panel.setForeground(table.getForeground());
//					panel.setBackground(table.getBackground());
//				}
//
//				// Create and add a button with the icon;
//				// this button has no listener
//				panel.add(new JButton("..."), BorderLayout.WEST);
//
//				// Add the original JLabel renderer
//				panel.add(label, BorderLayout.CENTER);
//
//				// The panel should be displayed
//				return panel;
//			}
//		});
		table.getColumn("Student").setCellEditor(new ButtonEditor_Grade(new JCheckBox()));
//		table.getColumnModel().getColumn(0)
//				.setCellEditor(new TableCellEditor() {
//					@Override
//					public Object getCellEditorValue() {
//						return table.getDefaultEditor(String.class).getCellEditorValue();
//					}
//
//					@Override
//					public boolean isCellEditable(EventObject anEvent) {
//						return table.getDefaultEditor(String.class).isCellEditable(anEvent);
//					}
//
//					@Override
//					public boolean shouldSelectCell(EventObject anEvent) {
//						return table.getDefaultEditor(String.class).shouldSelectCell(anEvent);
//					}
//
//					@Override
//					public boolean stopCellEditing() {
//						return table.getDefaultEditor(String.class).stopCellEditing();
//					}
//
//					@Override
//					public void cancelCellEditing() {
//						table.getDefaultEditor(String.class).cancelCellEditing();
//					}
//
//					@Override
//					public void addCellEditorListener(CellEditorListener l) {
//						table.getDefaultEditor(String.class).addCellEditorListener(new CellEditorListener() {
//							@Override
//							public void editingStopped(ChangeEvent e) {
//								System.out.println("stopped");
//							}
//
//							@Override
//							public void editingCanceled(ChangeEvent e) {
//								System.out.println("canceled");
//							}
//						});
//					}
//
//					@Override
//					public void removeCellEditorListener(CellEditorListener l) {
//						table.getDefaultEditor(String.class).removeCellEditorListener(l);
//					}
//
//					@Override
//					public Component getTableCellEditorComponent(JTable table,
//					                                             final Object value, boolean isSelected, int row,
//					                                             int column) {
//						Component component = table.getDefaultEditor(String.class)
//								                      .getTableCellEditorComponent(table, value,
//										                      isSelected, row, column);
//
//						// Just check for sanity, this is overkill.
//						if (!(component instanceof JTextField))
//							throw new RuntimeException(
//									"Programmer error, wrong type");
//
//						// The component is a text field and the icons are
//						// available.
//						final JTextField textField = (JTextField) component;
//
//						// Build a little panel to hold the controls
//						JPanel panel = new JPanel();
//						panel.setLayout(new BorderLayout());
//
//						// Color appropriately for selection status
//						if (isSelected) {
//							panel.setForeground(table.getSelectionForeground());
//							panel.setBackground(table.getSelectionBackground());
//						} else {
//							panel.setForeground(table.getForeground());
//							panel.setBackground(table.getBackground());
//						}
//
//						// Create a button with the icon;
//						JButton dotButton = new JButton("...");
//
//						// Define listener that pops up bigger text editor
//						dotButton.addActionListener(new ActionListener() {
//							@Override
//							public void actionPerformed(ActionEvent e) {
//								String editedText = showTextEditDialog(
//										"Edit Text", 350, 100,
//										textField.getText());
//								// Store the value back to the text area
//								// which is still on the screen.
//								if (editedText != null)
//									textField.setText(editedText);
//							}
//						});
//
//						// Add button to panel
//						panel.add(dotButton, BorderLayout.WEST);
//
//						// Add the original editor to panel
//						panel.add(component, BorderLayout.CENTER);
//
//						// The panel should be displayed
//						return panel;
//					}
//				});
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

//	private String showTextEditDialog(final String dialogTitle, final int width, final int height, final String textToEdit) {
//
//		// This panel holds the only edit control
//		JPanel panel = new JPanel();
//		BorderLayout layout = new BorderLayout();
//		panel.setLayout(layout);
//		JTextArea textArea = new JTextArea();
//		textArea.setText(textToEdit);
//		// Use a scroll pane so the text area can be scrolled
//		JScrollPane jsp = new JScrollPane(textArea) {
//			private static final long serialVersionUID = 1L;
//
//			@Override
//			public Dimension getPreferredSize() {
//				return new Dimension(width, height);
//			}
//		};
//		panel.add(jsp, BorderLayout.CENTER);
//
//		// Use JOptionPane for a pane-less (ha!) way to create a
//		// dialog in just a few lines. Also get system L&F.
//		JOptionPane optPane = new JOptionPane();
//		optPane.setMessage(panel);
//		optPane.setMessageType(JOptionPane.PLAIN_MESSAGE);
//		optPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
//		JDialog dialog = optPane.createDialog(
//				p2, dialogTitle);
//		// This resizable setting is critical;
//		// by default the dialog is quite small
//		dialog.setResizable(true);
//
//		// Show it already!
//		dialog.setVisible(true);
//
//		// Get the value and decide if it was "OK"
//		Object selectedValue = optPane.getValue();
//		int n = -1;
//		if (selectedValue != null && selectedValue instanceof Integer)
//			n = ((Integer) selectedValue).intValue();
//		String result = null;
//		if (n == JOptionPane.OK_OPTION)
//			result = textArea.getText();
//
//		// Might be good text, might be null
//		return result;
//	}
}