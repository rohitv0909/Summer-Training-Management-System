package counsellor;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class StatusUpdate extends JFrame implements ActionListener
{

	private JPanel contentPane;
	private JTextField txtid;
	private JTextField txtstatus;
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
					StatusUpdate frame = new StatusUpdate();
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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(this);
		btnBack.setBounds(157, 215, 89, 23);
		contentPane.add(btnBack);
		
		JButton btnupdate = new JButton("Update Status");
		btnupdate.addActionListener(this);
		btnupdate.setBounds(141, 181, 125, 23);
		contentPane.add(btnupdate);
		
		JLabel lblEnterStudentId = new JLabel("Enter Student ID");
		lblEnterStudentId.setBounds(79, 57, 99, 14);
		contentPane.add(lblEnterStudentId);
		
		txtid = new JTextField();
		txtid.setBounds(219, 54, 134, 20);
		contentPane.add(txtid);
		txtid.setColumns(10);
		
		JButton btnshow = new JButton("Show Current Status");
		btnshow.addActionListener(this);
		btnshow.setBounds(123, 146, 159, 23);
		contentPane.add(btnshow);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(113, 99, 46, 14);
		contentPane.add(lblStatus);
		
		txtstatus = new JTextField();
		txtstatus.setBounds(219, 96, 134, 20);
		contentPane.add(txtstatus);
		txtstatus.setColumns(10);
	}
	public StatusUpdate() 
	{
		con=CrudOperation.createConnection();
		createGUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String caption=e.getActionCommand();
		String sid=txtid.getText();
		String status=txtstatus.getText();
		if(caption.equalsIgnoreCase("back"))
		{
			CounFrame cf=new CounFrame();
			cf.setVisible(true);
			this.dispose();
		}
		
		if(caption.equalsIgnoreCase("Show Current Status"))
		{	
			try
			{
				if(sid.isEmpty())
					JOptionPane.showMessageDialog(this, "Enter ID !");
				else
				{
					String strsql="select submitted from student where studentid=?";
					ps=con.prepareStatement(strsql);
					ps.setString(1, sid);
					rs=ps.executeQuery();
					if(rs.next()) 
					{
						String st=rs.getString("submitted");
						txtstatus.setText(st);
					}
					else
					{
						JOptionPane.showMessageDialog(this, "No such id !");
					}
				}
			}
			catch(SQLException se)
			{
				System.out.println(se);
			}
		}
		
		if(caption.equalsIgnoreCase("Update Status"))
		{
			try
			{
				String stid=txtid.getText();
				String sta=txtstatus.getText();
				if(stid.isEmpty()||sta.isEmpty())
					JOptionPane.showMessageDialog(this, "PLease enter ID/Status !");
				else
				{
					String strupdate="update student set submitted=? where studentid=?";
					ps=con.prepareStatement(strupdate);
					ps.setString(1, sta);
					ps.setString(2, stid);
					int rw=ps.executeUpdate();
					if(rw>0)
					{
						JOptionPane.showMessageDialog(this,"Status Updated !");
					}
				}
			}
			catch(SQLException se)
			{
				System.out.println(se);
			}
		}
	}
}
