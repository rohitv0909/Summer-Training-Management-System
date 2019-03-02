package admin;

import login.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class AdminFrame extends JFrame implements ActionListener
{
	private JPanel contentPane;
	private Connection con;
	private PreparedStatement ps;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminFrame frame = new AdminFrame();
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
	public void createGUI()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 435, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btncreate = new JButton("Create Account");
		btncreate.addActionListener(this);
		btncreate.setBounds(178, 25, 145, 23);
		contentPane.add(btncreate);
		
		JButton btnupdate = new JButton("Update Account");
		btnupdate.addActionListener(this);
		btnupdate.setBounds(178, 102, 145, 23);
		contentPane.add(btnupdate);
		
		JButton btndelete = new JButton("Delete Account");
		btndelete.addActionListener(this);
		btndelete.setBounds(178, 179, 145, 23);
		contentPane.add(btndelete);
		
		JButton btnback = new JButton("BACK");
		btnback.addActionListener(this);
		btnback.setBounds(23, 25, 89, 23);
		contentPane.add(btnback);
	}
	
	public AdminFrame() 
	{
		setTitle("Admin Frame");
		con=CrudOperation.createConnection();
		createGUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String caption=e.getActionCommand();
		if(caption.equalsIgnoreCase("create account"))
		{
			CreateAcc ca=new CreateAcc();
			ca.setVisible(true);
			this.dispose();
		}
		
		if(caption.equalsIgnoreCase("update account"))
		{
			UpdateAcc ua=new UpdateAcc();
			ua.setVisible(true);
			this.dispose();
		}
		
		if(caption.equalsIgnoreCase("delete account"))
		{
			DeleteAcc da=new DeleteAcc();
			da.setVisible(true);
			this.dispose();
		}
		
		if(caption.equalsIgnoreCase("back"))
		{
			SignInFrame lf=new SignInFrame();
			lf.setVisible(true);
			this.dispose();
		}
	}

}
