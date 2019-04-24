import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import Model.ParentNode;

public class CreateClass extends JFrame {

	private JPanel contentPane;
	private ParentNode root;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public static void main(String[] args) {
		JFrame parent=new JFrame();
		ParentNode root = new ParentNode();
		CreateClass frame = new CreateClass(parent,root);
		frame.setTitle("Create a class");
		frame.setVisible(true);
     }
	public CreateClass(JFrame parent,ParentNode root) {
		this.root=root;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JButton ImportClassButton = new JButton("Import from a previous class");
		ImportClassButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ImportClassButtonActionPerformed(evt);
			}
		});
		
		ImportClassButton.setBounds(80, 30, 250, 50);
		contentPane.add(ImportClassButton);
		
		JButton CreateClassButton = new JButton("Create a new class");
		CreateClassButton.setBounds(80, 120, 250,50);
		contentPane.add(CreateClassButton );
		CreateClassButton.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	CreateClassButtonActionPerformed(evt);
            }
        });
		
		JLabel lblNewLabel = new JLabel("New label");
		contentPane.add(lblNewLabel);
		contentPane.setLayout(null);
		setLocationRelativeTo(parent);
	}
	public void CreateClassButtonActionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		/*to jump*/
		NewClass frame = new NewClass(this,root);
		frame.setTitle("Create a class");
		frame.setVisible(true);
		this.dispose();

	}
	public void ImportClassButtonActionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		/*to jump*/
		//NewClass frame = new NewClass(this);
		//frame.setTitle("Create a class");
		//frame.setVisible(true);
		JFileChooser jfc=new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY );
		jfc.showDialog(new JLabel(), "Choose a CSV file");
		File file=jfc.getSelectedFile();
		if(file.isFile()){
			System.out.println("File name: "+file.getAbsolutePath());
		}
		System.out.println(jfc.getSelectedFile().getName());
		String path=file.getAbsolutePath();
		ImportClass frame= new ImportClass(this,root,path);
		frame.setTitle("Imported From Previous Class");
		frame.setVisible(true);
		this.dispose();

	}

}
