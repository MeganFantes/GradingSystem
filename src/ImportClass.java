import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.border.EmptyBorder;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import Model.ParentNode;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JButton;
import Model.ParentNode;

public class ImportClass extends JFrame {

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
	private ParentNode root;
	private static String path;
	private ArrayList<String> critlist;
	private ArrayList<Float> weightlist;
	private int numcols=0;
	/**
	 * Launch the application.

	/**
	 * Create the frame.
	 */
	public static void main(String[] args) {
		JFrame parent=new JFrame();
		ParentNode root = new ParentNode();
		ImportClass frame = new ImportClass(parent,root,path);
		System.out.println(path);
		frame.setTitle("Create a class");
		frame.setVisible(true);
     }
	
	public ImportClass(JFrame parent,ParentNode root,String path) {
		critlist=new ArrayList<String>();
		weightlist=new ArrayList<Float>();
		
		this.path=path;
		this.root=root;
        try {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream objis = new ObjectInputStream(fis);
            root = (ParentNode) objis.readObject();
            System.out.println("read successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
		ArrayList<ParentNode> children=root.getAllChildren();
		for (int i=0; i<children.size();i++)
		{
			critlist.add(children.get(i).getCriteria());
			weightlist.add(children.get(i).getWeight());
		}

		
		
		
		
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
		
		numcols=children.size();
		if (numcols>0)
		{
			crit1.setText(critlist.get(0));
			wgh1.setText(weightlist.get(0).toString());
		}
		for (int i=1;i<numcols;i++)
		{
			loadingtable(parent,critlist.get(i),weightlist.get(i).toString());
		}
		setLocationRelativeTo(parent);


	}
	public void AddCriteriaButtonActionPerformed(ActionEvent arg0, JFrame parent) {
		// TODO Auto-generated method stub
		int finishflag=1;
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
                if (C_R.length<2)
                {
                	finishflag=0;
                	System.out.println("fail!");
                	break;
                }
                else if(C_R[0].equals(""))
                {
                	finishflag=0;
                	System.out.println("fail");
                	break;
                }
                else {
	                criteria_list.add(C_R[0]);
	                weights_list.add(C_R[1]);
                }
  
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
            if(crit1.getText().equals("")||wgh1.getText().equals("")||textField.getText().equals("")||textField_1.getText().equals(""))
            {
            	finishflag=0;
            	System.out.println("fail");
            }
            System.out.println("flag=: "+finishflag);
            if (finishflag!=0)
            {
                ArrayList<String> errors = root.treeValidation(null,  true);
                root.updateCurrNode(Semester_Name, criteria_list, weights_list);
                root.traverse(0);
                if (errors.size()>0)
                {
                	JOptionPane.showMessageDialog(parent,"All weighs should add up to 100","Weight Error",JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else
            {
            	JOptionPane.showMessageDialog(parent,"Please fill in all blanks","Empty blanks",JOptionPane.INFORMATION_MESSAGE);
            }


		}
		else if(jb==DeleteCriteriaButton)
		{
            if(jpc.getComponentCount()>0) { // 得到jpc里的MyJPanel的组件数量
                jpc.remove(jpc.getComponentCount()-1);//删除末尾的一个组件 ,
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
	public void loadingtable(JFrame parent,String string1,String string2)
	{
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
		newcrit.setJTFValue(string1, string2);
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

