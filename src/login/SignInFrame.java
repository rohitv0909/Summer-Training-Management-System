package login;

import admin.*;
import acdhead.*;
import counsellor.*;
import java.sql.*;
import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.*;

public class SignInFrame extends JFrame implements ActionListener
{

	private JPanel contentPane;
	private JTextField txtuserid;
	private JPasswordField txtuserpass;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignInFrame frame = new SignInFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SignInFrame() 
	{
		con=CrudOperation.createConnection();
		setTitle("Login Frame");
		createGUI();
	}
	
	public void createGUI()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 422, 316);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbluserid = new JLabel("Enter UserID");
		lbluserid.setBounds(58, 42, 86, 20);
		contentPane.add(lbluserid);
		
		txtuserid = new JTextField();
		txtuserid.setBounds(204, 42, 127, 20);
		contentPane.add(txtuserid);
		txtuserid.setColumns(10);
		
		JLabel lbluserpass = new JLabel("Enter UserPass");
		lbluserpass.setBounds(58, 109, 98, 14);
		contentPane.add(lbluserpass);
		
		txtuserpass = new JPasswordField();
		txtuserpass.setBounds(204, 106, 127, 20);
		contentPane.add(txtuserpass);
		
		JButton btnsubmit = new JButton("Submit");
		btnsubmit.addActionListener(this); 
		btnsubmit.setBounds(148, 166, 89, 23);
		contentPane.add(btnsubmit);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String ui=txtuserid.getText();
		char[] pwd=txtuserpass.getPassword();
		String upass=new String(pwd);
		if(ui.isEmpty()||upass.isEmpty())
		{
			JOptionPane.showMessageDialog(this, "Enter ID/Password !!");
		}
		else
		{
		try
		{
			String strsql="select * from account where userid=? and userpass=?";
			ps=con.prepareStatement(strsql);
			ps.setString(1, ui);
			ps.setString(2, upass);
			rs=ps.executeQuery();
			
			if(rs.next())
			{
				String type=rs.getString("usertype");
				if(type.equals("Admin"))
				{
					AdminFrame ad=new AdminFrame();
					ad.setVisible(true);
					this.dispose();
				}
				if(type.equalsIgnoreCase("Academic Head"))
				{
					AcdheadFrame ah=new AcdheadFrame();
					ah.setVisible(true);
					this.dispose();
				}
				if(type.equalsIgnoreCase("Counsellor"))
				{
					CounFrame cf=new CounFrame();
					cf.setVisible(true);
					this.dispose();
				}
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Invalid ID/Password !!");
			}
		}
		catch(SQLException se)
		{
			System.out.println(se);
		}
		}
	}
}
