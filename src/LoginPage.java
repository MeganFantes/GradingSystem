import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
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

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI.NormalColor;
 
public class LoginPage extends JFrame{

	private static int count=0;
	private static JButton bt1;//��½��ť
	private static JLabel jl_1;//��¼�İ���
	private static JFrame jf_1;//��½�Ŀ��
    private static JTextField jtext1;//�û���
    private static JPasswordField jtext2;//����
    private static JLabel jl_admin;
    private static JLabel jl_password;
    private final JPanel contentPanel = new JPanel();
    public LoginPage (){//��ʼ����½����

    	Font font =new Font("Times New Roman", Font.PLAIN, 20);//��������
	    jf_1=new JFrame("Login Page");
		jf_1.setSize(650, 500);
		jl_1=new JLabel();
		
		jl_admin=new JLabel("Username");
		jl_admin.setBounds(20, 50, 80, 50);
		jl_admin.setFont(font);
		
		jl_password=new JLabel("Password");
		jl_password.setBounds(20, 120, 80, 50);
		jl_password.setFont(font);
		
		bt1=new JButton("Login");         //���ĳ�loginButton
		bt1.setBounds(110, 250, 200, 50);
		bt1.setUI(new BEButtonUI(). setNormalColor(BEButtonUI.NormalColor.lightBlue));
		bt1.setFont(font);
 
 
		//�����ı���
		jtext1=new JTextField("");
		jtext1.setBounds(150, 50, 250, 50);
		jtext1.setFont(font);
		
		jtext2=new JPasswordField("");//���������
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
		jf_1.setLocation(300,400);
	}
	public static void main(String[] args) {
		//��ʼ����½����
	    try
	    {
	        org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
	        BeautyEyeLNFHelper.frameBorderStyle= BeautyEyeLNFHelper.frameBorderStyle.generalNoTranslucencyShadow;
	        UIManager.put("RootPane.setupButtonVisible", false);
	        UIManager.put("RootPane.addCreate", false);
	        BeautyEyeLNFHelper.translucencyAtFrameInactive = false;
	    }
	    catch(Exception e)
	    {
	        //TODO exception
	    } 
		LoginPage hl =new LoginPage();
		/**
		 * �������¼�
		 */
		//��½����¼�
		ActionListener bt1_ls=new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String admin=jtext1.getText();
				char[] password=jtext2.getPassword();
				String str=String.valueOf(password); //��char����ת��Ϊstring����  
				System.out.println(admin);
				System.out.println(str);
				/*to jump*/
				LoadingPage frame = new LoadingPage();
				frame.setTitle("Load/Create a class");

				frame.setVisible(true);
				hl.jf_1.dispose();//���ٵ�ǰ����

				
			}
		};
		bt1.addActionListener(bt1_ls);	
     }
}