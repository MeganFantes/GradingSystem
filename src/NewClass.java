import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JTextField;
import javax.swing.JButton;

public class NewClass extends JFrame {

	private JPanel contentPane;
	private JPanel jpc;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textFieldaa;
	private JButton AddCriteriaButton;
	private JButton DeleteCriteriaButton;
	private JButton FinishButton;
	private int countnum=0;
	private int index=0;
	private JTextField crit1;
	private JTextField wgh1;
	private int AddButtonYaxis=156;
	private JLabel lblCriteria;
	private JLabel lblWeigh;
	private Vector weigh;
	private String Semester_Name;
	private ArrayList<String> criteria_list;
	private ArrayList<String> weights_list;
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
		AddCriteriaButton.setBounds(14, 193, 189, 27);
		contentPane.add(AddCriteriaButton);
		AddCriteriaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				AddCriteriaButtonActionPerformed(evt,parent);
			}
		});
		
		
		FinishButton = new JButton("Finish");
		FinishButton.setBounds(39, 232, 113, 27);
		contentPane.add(FinishButton);
		FinishButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				AddCriteriaButtonActionPerformed(evt,parent);
			}
		});
		
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
		
		lblWeigh = new JLabel("Weights");
		lblWeigh.setBounds(237, 107, 72, 18);
		contentPane.add(lblWeigh);
		
		jpc = new JPanel();
		jpc.setBounds(0, 192, 400, 1);

		
		contentPane.add(jpc);
		
		DeleteCriteriaButton = new JButton("Delete a criteria");
		DeleteCriteriaButton.setBounds(211, 193, 189, 27);
		contentPane.add(DeleteCriteriaButton);
		DeleteCriteriaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				AddCriteriaButtonActionPerformed(evt,parent);
			}
		});
		
		
		setLocationRelativeTo(parent);


	}
	public void AddCriteriaButtonActionPerformed(ActionEvent arg0, JFrame parent) {
		// TODO Auto-generated method stub
		JButton jb=(JButton) arg0.getSource();
		if (jb==AddCriteriaButton)
		{
		/*to jump*/
		//NewClass frame = new NewClass(this);
		//frame.setTitle("Create a class");
		//frame.setVisible(true);
		System.out.println("countnum: "+ countnum);
		countnum+=1;
		MyJPanel newcrit=new MyJPanel(index);

		//MyJPanel newwgh=new MyJPanel(index+1);
		index+=1;
		//newwgh.setBounds(187, 143+50*countnum, 50, 24);
		jpc.setBounds(0, 192, 400, 1+50*countnum);
		jpc.setLayout(null);
		jpc.add(newcrit);
		/*JTextField newcrit=new JTextField();
		JTextField newwgh=new JTextField();*/
		newcrit.setBounds(14, -50+50*countnum, 450, 50);

	
		//jpc.add(newwgh);	

		/*contentPane.setVisible(false);
		contentPane.removeAll();
		NewClass newwindow= new NewClass(parent);
		contentPane.setVisible(true);*/
		
		contentPane.remove(AddCriteriaButton);
		contentPane.remove(DeleteCriteriaButton);
		contentPane.remove(FinishButton);
		AddCriteriaButton.setBounds(14, 193+countnum*50, 189, 27);
		DeleteCriteriaButton.setBounds(211, 193+countnum*50, 189, 27);
		FinishButton.setBounds(38, 232+countnum*50, 113, 27);
		contentPane.add(AddCriteriaButton);
		contentPane.add(DeleteCriteriaButton);
		contentPane.add(FinishButton);
		SwingUtilities.updateComponentTreeUI(this);
		contentPane.repaint();
		}
		
		else if(jb==FinishButton)
		{
			Semester_Name=textField_1.getText()+"-"+textField.getText();
;			//System.out.println(Semester_Name);
			System.out.println("The value of row 1 is "+crit1.getText()+" "+wgh1.getText());
			criteria_list=new ArrayList<String>();
			weights_list=new ArrayList<String>();
			criteria_list.add(crit1.getText());
			weights_list.add(wgh1.getText());
            for (int i = 0; i < jpc.getComponentCount(); i++) {
                MyJPanel mjp = (MyJPanel) jpc.getComponent(i);
                System.out.println("The value of row "+(i+2)+" is "+mjp.getJTFValue());
                String result=mjp.getJTFValue();
                String[] C_R=result.split(" ");

                criteria_list.add(C_R[0]);
                weights_list.add(C_R[1]);
                
                
                
            }
            /*
            for (String x:criteria_list)
            {
            	if (x!=null)
            		System.out.println(x);
            }
            for (String x:weights_list)
            {
            	if (x!=null)
            		System.out.println(x);
            }*/
		}
		else if(jb==DeleteCriteriaButton)
		{
            if(jpc.getComponentCount()>0) { // �õ�jpc���MyJPanel���������
                jpc.remove(jpc.getComponentCount()-1);//ɾ��ĩβ��һ����� ,
                index-=1;
                countnum-=1;
        		jpc.setBounds(0, 192, 400, 1+50*countnum);
        		jpc.setLayout(null);
        		/*JTextField newcrit=new JTextField();
        		JTextField newwgh=new JTextField();*/
        		contentPane.remove(AddCriteriaButton);
        		contentPane.remove(DeleteCriteriaButton);
        		contentPane.remove(FinishButton);
        		AddCriteriaButton.setBounds(14, 193+countnum*50, 189, 27);
        		DeleteCriteriaButton.setBounds(211, 193+countnum*50, 189, 27);
        		FinishButton.setBounds(38, 232+countnum*50, 113, 27);
        		contentPane.add(AddCriteriaButton);
        		contentPane.add(DeleteCriteriaButton);
        		contentPane.add(FinishButton);
        		SwingUtilities.updateComponentTreeUI(this);
        		contentPane.repaint();
            }
		}

	}
}

class MyJPanel extends JPanel{
	
	
    public JTextField jtf1;
    public JTextField jtf2;
    
    public MyJPanel(int index) {
    	setLayout(null);
        jtf1 = new JTextField();
        jtf2 = new JTextField();
        jtf1.setBounds(0,0,153, 24);
        jtf2.setBounds(173,0,168, 24);

        add(jtf1);
        add(jtf2);
		
    }
    //get value
    public String getJTFValue() {
        return jtf1.getText()+" "+jtf2.getText();
    }
    //setvalue
    public void setJTFValue(String value) {
        jtf1.setText(value);
    }
}
