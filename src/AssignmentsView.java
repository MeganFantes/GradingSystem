// Example from http://www.crionics.com/products/opensource/faq/swing_ex/SwingExamples.html
// Megan author

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;


/**
 * @version 1.0 11/09/98
 */
public class AssignmentsView { //extends JFrame {
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

	public AssignmentsView() {
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
		colStudent.setHeaderRenderer(new EditableHeaderRenderer(btnStudent));

		TableColumn colHomework = table.getColumnModel().getColumn(1);
		JButton btnHomework = new JButton("Homework");
		btnHomework.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(btnHomework, "Homework!");
			}
		});
		colHomework.setHeaderRenderer(new EditableHeaderRenderer(btnHomework));

		TableColumn colExam = table.getColumnModel().getColumn(2);
		JButton btnExam = new JButton("Exam");
		btnExam.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(btnExam, "Exam!");
			}
		});
		colExam.setHeaderRenderer(new EditableHeaderRenderer(btnExam));

		TableColumn colProject = table.getColumnModel().getColumn(3);
		JButton btnProject = new JButton("Project");
		btnProject.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(btnProject, "Project!");
			}
		});
		colProject.setHeaderRenderer(new EditableHeaderRenderer(btnProject));

		TableColumn colParticipation = table.getColumnModel().getColumn(4);
		JButton btnParticipation = new JButton("Participation");
		btnParticipation.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(btnParticipation, "Participation!");
			}
		});
		colParticipation.setHeaderRenderer(new EditableHeaderRenderer(btnParticipation));
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
//		header.getColumnModel().getColumn(1).setCellEditor(new ButtonEditor(new JCheckBox()));
//		table.setTableHeader(null);
//		TableColumn tc = table.getColumnModel().getColumn(1);
//		tc.setCellEditor(new ButtonEditor(new JCheckBox()));
//		tc.setCellRenderer(new ButtonRenderer());
//		tc.setHeaderRenderer(new ButtonRenderer());
//		table.getColumn("Student").setCellRenderer(new ButtonRenderer());
//		table.getColumn("Student").setCellEditor(new ButtonEditor(new JCheckBox()));
//		table.getColumn("Exam").setCellRenderer(new ButtonRenderer());
//		table.getColumn("Exam").setCellEditor(new ButtonEditor(new JCheckBox()));
//		JScrollPane scroll = new JScrollPane(table);
//		getContentPane().add(scroll);
//		setSize(400, 100);
//		setVisible(true);

		table.getColumn("Student").setCellRenderer(new ButtonRenderer());
		table.getColumn("Student").setCellEditor(new ButtonEditor(new JCheckBox()));
		table.getColumn("Participation").setCellRenderer(new ButtonRenderer());
		table.getColumn("Participation").setCellEditor(new ButtonEditor(new JCheckBox()));
		table.getColumn("Homework").setCellRenderer(new ButtonRenderer());
		table.getColumn("Homework").setCellEditor(new ButtonEditor(new JCheckBox()));
		table.getColumn("Exam").setCellRenderer(new ButtonRenderer());
		table.getColumn("Exam").setCellEditor(new ButtonEditor(new JCheckBox()));
		table.getColumn("Project").setCellRenderer(new ButtonRenderer());
		table.getColumn("Project").setCellEditor(new ButtonEditor(new JCheckBox()));
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
		AssignmentsView frame = new AssignmentsView();
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
}
/**
 * @version 1.0 11/09/98
 */

class ButtonRenderer extends JButton implements TableCellRenderer {

	public ButtonRenderer() {
		setOpaque(true);
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
	                                               boolean isSelected, boolean hasFocus, int row, int column) {
		if (isSelected) {
			setForeground(table.getSelectionForeground());
			setBackground(table.getSelectionBackground());
		} else {
			setForeground(table.getForeground());
			setBackground(UIManager.getColor("Button.background"));
		}
		setText((value == null) ? "" : value.toString());
		return this;
	}
}

/**
 * @version 1.0 11/09/98
 */


class ButtonEditor extends DefaultCellEditor {
	protected JButton button;

	private String label;

	private boolean isPushed;

	public ButtonEditor(JCheckBox checkBox) {
		super(checkBox);
		button = new JButton();
		button.setOpaque(true);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fireEditingStopped();
			}
		});
	}

	public Component getTableCellEditorComponent(JTable table, Object value,
	                                             boolean isSelected, int row, int column) {
		if (isSelected) {
			button.setForeground(table.getSelectionForeground());
			button.setBackground(table.getSelectionBackground());
		} else {
			button.setForeground(table.getForeground());
			button.setBackground(table.getBackground());
		}
		label = (value == null) ? "" : value.toString();
		button.setText(label);
		isPushed = true;
		return button;
	}

	public Object getCellEditorValue() {
		if (isPushed) {
			//
			//
			JOptionPane.showMessageDialog(button, label + ": Ouch!");
			// System.out.println(label + ": Ouch!");
		}
		isPushed = false;
		return new String(label);
	}

	public boolean stopCellEditing() {
		isPushed = false;
		return super.stopCellEditing();
	}

	protected void fireEditingStopped() {
		super.fireEditingStopped();
	}
}

class EditableHeaderRenderer implements TableCellRenderer {

	private JTable table = null;
	private MouseEventReposter reporter = null;
	private JComponent editor;

	EditableHeaderRenderer(JComponent editor) {
		this.editor = editor;
		this.editor.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
		if (table != null && this.table != table) {
			this.table = table;
			final JTableHeader header = table.getTableHeader();
			if (header != null) {
				this.editor.setForeground(header.getForeground());
				this.editor.setBackground(header.getBackground());
				this.editor.setFont(header.getFont());
				reporter = new MouseEventReposter(header, col, this.editor);
				header.addMouseListener(reporter);
			}
		}

		if (reporter != null) reporter.setColumn(col);

		return this.editor;
	}

	static public class MouseEventReposter extends MouseAdapter {

		private Component dispatchComponent;
		private JTableHeader header;
		private int column  = -1;
		private Component editor;

		public MouseEventReposter(JTableHeader header, int column, Component editor) {
			this.header = header;
			this.column = column;
			this.editor = editor;
		}

		public void setColumn(int column) {
			this.column = column;
		}

		private void setDispatchComponent(MouseEvent e) {
			int col = header.getTable().columnAtPoint(e.getPoint());
			if (col != column || col == -1) return;

			Point p = e.getPoint();
			Point p2 = SwingUtilities.convertPoint(header, p, editor);
			dispatchComponent = SwingUtilities.getDeepestComponentAt(editor, p2.x, p2.y);
		}

		private boolean repostEvent(MouseEvent e) {
			if (dispatchComponent == null) {
				return false;
			}
			MouseEvent e2 = SwingUtilities.convertMouseEvent(header, e, dispatchComponent);
			dispatchComponent.dispatchEvent(e2);
			return true;
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (header.getResizingColumn() == null) {
				Point p = e.getPoint();

				int col = header.getTable().columnAtPoint(p);
				if (col != column || col == -1) return;

				int index = header.getColumnModel().getColumnIndexAtX(p.x);
				if (index == -1) return;

				editor.setBounds(header.getHeaderRect(index));
				header.add(editor);
				editor.validate();
				setDispatchComponent(e);
				repostEvent(e);
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			repostEvent(e);
			dispatchComponent = null;
			header.remove(editor);
		}
	}
}