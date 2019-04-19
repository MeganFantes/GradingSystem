import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class LoadingPage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.


	/**
	 * Create the frame.
	 */
	public LoadingPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 561, 418);

		
		contentPane = new JPanel();
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
		

		
		JList list_1 = new JList();
		list_1.setListData(new String[]{"CS591 Spring 2018","CS591 Autumn 2018","CS591 Autumn 2017","CS591 Autumn 2016"});
		list_1.setBounds(81, 170, 372, 80);
		contentPane.add(list_1);
		
		JScrollPane scrollPane_1 = new JScrollPane(list_1);
		scrollPane_1.setBounds(81, 170, 372, 80);
		contentPane.add(scrollPane_1);
		
		JButton btnNewButton = new JButton("Create a new class");
		btnNewButton.setBounds(184, 299, 186, 27);
		contentPane.add(btnNewButton);
		
		JList list = new JList();
		list.setBounds(81, 36, 372, 89);
		list.setListData(new String[]{"CS591 Spring 2019","CS591 Autumn 2019"});

		contentPane.add(list);
		
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(81, 36, 372, 89);
		contentPane.add(scrollPane);
		
	}
}
