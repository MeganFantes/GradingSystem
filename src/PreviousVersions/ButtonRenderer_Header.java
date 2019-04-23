package PreviousVersions;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ButtonRenderer_Header extends JButton implements TableCellRenderer {
	int pushedColumn;

	public ButtonRenderer_Header() {
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