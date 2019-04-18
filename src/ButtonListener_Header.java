import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class ButtonListener_Header extends MouseAdapter {
	JTableHeader header;

	ButtonRenderer_Header renderer;

	ButtonListener_Header(JTableHeader header, ButtonRenderer_Header renderer) {
		this.header = header;
		this.renderer = renderer;
	}

	public void mousePressed(MouseEvent e) {
		int col = header.columnAtPoint(e.getPoint());
		renderer.setPressedColumn(col);
		header.repaint();

//			System.out.println("Ouch! " + col);
		JOptionPane.showMessageDialog(renderer, "You selected: " + header.getColumnModel().getColumn(col).getHeaderValue());
		renderer.setPressedColumn(-1); // clear
		header.repaint();
	}

	public void mouseReleased(MouseEvent e) {
		int col = header.columnAtPoint(e.getPoint());
		renderer.setPressedColumn(-1); // clear
		header.repaint();
	}
}