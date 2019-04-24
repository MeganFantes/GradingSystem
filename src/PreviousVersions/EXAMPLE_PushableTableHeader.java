package PreviousVersions;// Example from http://www.crionics.com/products/opensource/faq/swing_ex/SwingExamples.html
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 * @version 1.0 02/25/99
 */
public class EXAMPLE_PushableTableHeader extends JPanel {

	public EXAMPLE_PushableTableHeader() {
		setLayout(new BorderLayout());
		String[] headerStr = { "Push", "me", "here" };

		DefaultTableModel dm = new DefaultTableModel(headerStr, 4);
		JTable table = new JTable(dm);
		ButtonHeaderRenderer renderer = new ButtonHeaderRenderer();
		TableColumnModel model = table.getColumnModel();
		int n = headerStr.length;
		for (int i = 0; i < n; i++) {
			model.getColumn(i).setHeaderRenderer(renderer);
		}

		JTableHeader header = table.getTableHeader();
		header.addMouseListener(new HeaderListener(header, renderer));
		JScrollPane pane = new JScrollPane(table);
		add(pane, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		JFrame f = new JFrame("PushableHeaderTable Example");
		f.getContentPane()
				.add(new EXAMPLE_PushableTableHeader(), BorderLayout.CENTER);
		f.setSize(400, 100);
		f.setVisible(true);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	static class HeaderListener extends MouseAdapter {
		JTableHeader header;

		ButtonHeaderRenderer renderer;

		HeaderListener(JTableHeader header, ButtonHeaderRenderer renderer) {
			this.header = header;
			this.renderer = renderer;
		}

		public void mousePressed(MouseEvent e) {
			int col = header.columnAtPoint(e.getPoint());
			renderer.setPressedColumn(col);
			header.repaint();

//			System.out.println("Ouch! " + col);
			JOptionPane.showMessageDialog(renderer, "Ouch!" + col);
			renderer.setPressedColumn(-1); // clear
			header.repaint();
		}

		public void mouseReleased(MouseEvent e) {
			int col = header.columnAtPoint(e.getPoint());
			renderer.setPressedColumn(-1); // clear
			header.repaint();
		}
	}

	static class ButtonHeaderRenderer extends JButton implements TableCellRenderer {
		int pushedColumn;

		public ButtonHeaderRenderer() {
			pushedColumn = -1;
			setMargin(new Insets(0, 0, 0, 0));
		}

		public Component getTableCellRendererComponent(JTable table,
		                                               Object value, boolean isSelected, boolean hasFocus, int row,
		                                               int column) {
			setText((value == null) ? "" : value.toString());
			boolean isPressed = (column == pushedColumn);
			getModel().setPressed(isPressed);
			getModel().setArmed(isPressed);
			return this;
		}

		public void setPressedColumn(int col) {
			pushedColumn = col;
		}
	}
}