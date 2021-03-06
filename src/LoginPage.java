import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import Model.ParentNode;
 
public class LoginPage extends JFrame{

	private static int count=0;
	private static JButton bt1;
	private static JLabel jl_1;
	private static JFrame jf_1;
    private static JTextField jtext1;
    private static JPasswordField jtext2;
    private static JLabel jl_admin;
    private static JLabel jl_password;
    private final JPanel contentPanel = new JPanel();
    private ParentNode root;
    public LoginPage (ParentNode root){//initialize
    	this.root=root;

    	Font font =new Font("Times New Roman", Font.PLAIN, 20);//font
	    jf_1=new JFrame("Login Page");
		jf_1.setSize(430, 350);
		jl_1=new JLabel();
		
		jl_admin=new JLabel("Username");
		jl_admin.setBounds(20, 50, 150, 50);
		jl_admin.setFont(font);
		
		jl_password=new JLabel("Password");
		jl_password.setBounds(20, 120, 150, 50);
		jl_password.setFont(font);
		
		bt1=new JButton("Login");         //loginButton
		bt1.setBounds(110, 220, 200, 50);
		//bt1.setUI(new BEButtonUI(). setNormalColor(BEButtonUI.NormalColor.lightBlue));
		bt1.setFont(font);
		bt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginActionPerformed(evt);
            }
        });	
 
 
		//textfield
		jtext1=new JTextField("");
		jtext1.setBounds(150, 50, 250, 50);
		jtext1.setFont(font);
		
		jtext2=new JPasswordField("");//password
		jtext2.setBounds(150, 120, 250, 50);
		jtext2.setFont(font);
		
		jl_1.add(jtext1);
		jl_1.add(jtext2);
		
		jl_1.add(jl_admin);
		jl_1.add(jl_password);
		jl_1.add(bt1);
		
		jf_1.add(jl_1);
		jf_1.setVisible(true);
		jf_1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		jf_1.setLocation(700,300);

	    // make the JFrame appear in the middle of the screen
	    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	    jf_1.setLocation(dim.width/2-jf_1.getSize().width/2, dim.height/2-jf_1.getSize().height/2);
	}
	public static void main(String[] args) {
		//initial
		ParentNode root=new ParentNode();
//	    try
//	    {
//	        org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
//	        BeautyEyeLNFHelper.frameBorderStyle= BeautyEyeLNFHelper.frameBorderStyle.generalNoTranslucencyShadow;
//	        UIManager.put("RootPane.setupButtonVisible", false);
//	        UIManager.put("RootPane.addCreate", false);
//	        BeautyEyeLNFHelper.translucencyAtFrameInactive = false;
//	    }
//	    catch(Exception e)
//	    {
//	        //TODO exception
//	    }
		LoginPage hl =new LoginPage(root);

     }
	public void LoginActionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String admin=jtext1.getText();
		char[] password=jtext2.getPassword();
		String str=String.valueOf(password); //char->string
		System.out.println(admin);
		System.out.println(str);
		/*to jump*/
		LoadingPage frame = new LoadingPage(this,root);
		frame.setTitle("Load/Create a class");
		
		frame.setVisible(true);
		jf_1.dispose();


	};
}