import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class NavigationButtonBanner extends JPanel {
	// buttons on the panel
	private static JButton btnBack;
	private static JButton btnHome;
	private static JButton btnCalcFinalGrade;
	private static JButton btnAddColumn;
	public NavigationButtonBanner() {
		// set layout
		setLayout(new GridLayout(0,4));

		// create the buttons
		btnBack = new JButton("Back");
		btnHome = new JButton("Home");
		btnCalcFinalGrade = new JButton("Calculate Final Grade");
		btnAddColumn = new JButton("Add Column");

		// add the buttons to the panel
		add(btnBack);
		add(btnHome);
		add(btnCalcFinalGrade);
		add(btnAddColumn);

		setBorder(new EmptyBorder(10, 0, 10, 0));
		// TODO: add button functionality
	}
}
