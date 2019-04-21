import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class NewClass extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textFieldaa;
	private JButton AddCriteriaButton;
	private JButton FinishButton;
	private int countnum=0;
	private JTextField crit1;
	private JTextField wgh1;
	private int AddButtonYaxis=156;
	private JLabel lblCriteria;
	private JLabel lblWeigh;
	/**
	 * Launch the application.

	/**
	 * Create the frame.
	 */
	public NewClass(JFrame parent) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 473, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Class Name: ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(14, 13, 171, 34);
		contentPane.add(lblNewLabel);
		
		JLabel lblSemester = new JLabel("Semester: ");
		lblSemester.setHorizontalAlignment(SwingConstants.CENTER);
		lblSemester.setBounds(14, 60, 171, 34);
		contentPane.add(lblSemester);
		
		textField = new JTextField();
		textField.setBounds(187, 18, 168, 24);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(187, 65, 168, 24);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		AddCriteriaButton = new JButton("Add a new criteria");
		AddCriteriaButton.setBounds(14, 192, 189, 27);
		contentPane.add(AddCriteriaButton);
		AddCriteriaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				AddCriteriaButtonActionPerformed(evt,parent);
			}
		});
		
		
		FinishButton = new JButton("Finish");
		FinishButton.setBounds(39, 232, 113, 27);
		contentPane.add(FinishButton);
		
		crit1 = new JTextField();
		crit1.setBounds(14, 143, 153, 24);
		contentPane.add(crit1);
		crit1.setColumns(10);
		
		wgh1 = new JTextField();
		wgh1.setBounds(187, 143, 168, 24);
		contentPane.add(wgh1);
		wgh1.setColumns(10);
		
		lblCriteria = new JLabel("Criteria");
		lblCriteria.setBounds(59, 107, 72, 18);
		contentPane.add(lblCriteria);
		
		lblWeigh = new JLabel("Weigh");
		lblWeigh.setBounds(237, 107, 72, 18);
		contentPane.add(lblWeigh);
		


	}
	public void AddCriteriaButtonActionPerformed(ActionEvent arg0, JFrame parent) {
		// TODO Auto-generated method stub
		/*to jump*/
		//NewClass frame = new NewClass(this);
		//frame.setTitle("Create a class");
		//frame.setVisible(true);
		System.out.println("countnum: "+ countnum);
		countnum+=1;
		JTextField newcrit=new JTextField();
		JTextField newwgh=new JTextField();
		newcrit.setBounds(14, 143+30*countnum, 153, 24);
		newwgh.setBounds(187, 143+30*countnum, 168, 24);
		contentPane.add(newcrit);	
		contentPane.add(newwgh);	

		/*contentPane.setVisible(false);
		contentPane.removeAll();
		NewClass newwindow= new NewClass(parent);
		contentPane.setVisible(true);*/

		contentPane.remove(AddCriteriaButton);
		contentPane.remove(FinishButton);
		AddCriteriaButton.setBounds(14, 192+countnum*30, 189, 27);
		FinishButton.setBounds(38, 232+countnum*30, 113, 27);
		contentPane.add(AddCriteriaButton);
		contentPane.add(FinishButton);
		
		contentPane.repaint();

	}
	
	
}
