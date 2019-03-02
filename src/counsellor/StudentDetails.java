package counsellor;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class StudentDetails extends JFrame implements ActionListener
{
	private JPanel contentPane;
	private Connection con;
	private PreparedStatement ps,ps1;
	private ResultSet rs;
	private JTextField txtsid;
	private JTextField txtname;
	private JTextField txtphone;
	private JTextField txtemail;
	private JTextArea txtadd;
	private JComboBox cmbcourse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentDetails frame = new StudentDetails();
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
	public void populateCombo1()
	{
		String strsql="select coursename from course";
		try
		{
			ps=con.prepareStatement(strsql);
			rs=ps.executeQuery();
			while(rs.next()) 
			{
				String cname=rs.getString("coursename");
				cmbcourse.addItem(cname);		
			}
	    }
		catch(SQLException se)
		{
			System.out.println(se);
		}
	}
	
	
	
	public void createGUI()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 474, 439);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblStudentId = new JLabel("Student ID");
		lblStudentId.setBounds(81, 29, 73, 14);
		contentPane.add(lblStudentId);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(81, 61, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(81, 95, 73, 14);
		contentPane.add(lblAddress);
		
		JLabel lblPhoneNo = new JLabel("Phone No.");
		lblPhoneNo.setBounds(81, 201, 73, 14);
		contentPane.add(lblPhoneNo);
		
		JLabel lblEmailId = new JLabel("Email ID");
		lblEmailId.setBounds(81, 243, 46, 14);
		contentPane.add(lblEmailId);
		
		JLabel lblCourseId = new JLabel("Course ID");
		lblCourseId.setBounds(81, 278, 61, 14);
		contentPane.add(lblCourseId);
		
		txtsid = new JTextField();
		txtsid.setBounds(194, 26, 132, 20);
		contentPane.add(txtsid);
		txtsid.setColumns(10);
		
		txtname = new JTextField();
		txtname.setBounds(194, 58, 180, 20);
		contentPane.add(txtname);
		txtname.setColumns(10);
		
		txtadd = new JTextArea();
		txtadd.setLineWrap(true);
		txtadd.setBounds(194, 90, 224, 91);
		contentPane.add(txtadd);
		
		txtphone = new JTextField();
		txtphone.setBounds(194, 198, 180, 20);
		contentPane.add(txtphone);
		txtphone.setColumns(10);
		
		txtemail = new JTextField();
		txtemail.setBounds(194, 240, 180, 20);
		contentPane.add(txtemail);
		txtemail.setColumns(10);
		
		JButton btnsubmit = new JButton("SUBMIT");
		btnsubmit.addActionListener(this);
		btnsubmit.setBounds(266, 331, 89, 23);
		contentPane.add(btnsubmit);
		
		cmbcourse = new JComboBox();
		cmbcourse.setModel(new DefaultComboBoxModel(new String[] {"Select Course Name :"}));
		cmbcourse.setBounds(194, 275, 161, 20);
		populateCombo1();
		contentPane.add(cmbcourse);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(this);
		btnBack.setBounds(62, 331, 89, 23);
		contentPane.add(btnBack);
	}
	
	public StudentDetails() 
	{
		con=CrudOperation.createConnection();
		createGUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String caption=e.getActionCommand();
		if(caption.equalsIgnoreCase("SUBMIT"))
		{
			String stid=txtsid.getText();
			String name=txtname.getText();
			String add=txtadd.getText();
			String email=txtemail.getText();
			String cnm=(String)cmbcourse.getSelectedItem();
			try
			{
				if(stid.isEmpty()||name.isEmpty()||add.isEmpty()||txtphone.getText().isEmpty()||email.isEmpty()||cnm.equals("Select Course Name :"))
				{
					JOptionPane.showMessageDialog(this, "Please enter all the details !!");
				}
				else
				{
					String cid=null;
					String strc="select cid from course where coursename=?";
					ps1=con.prepareStatement(strc);
					ps1.setString(1, cnm);
					rs=ps1.executeQuery();
					if(rs.next())
					{
						cid=rs.getString("cid");
					}
					long phno=Long.parseLong(txtphone.getText());
					String strsql="insert into student (studentid,name,address,phno,emailid,cid,pid) values (?,?,?,?,?,?,?)";
					ps=con.prepareStatement(strsql);
					ps.setString(1, stid);
					ps.setString(2, name);
					ps.setString(3, add);
					ps.setLong(4, phno);
					ps.setString(5, email);
					ps.setString(6, cid);
					ps.setString(7, null);
					int row=ps.executeUpdate();
					if(row>0)
					{
						JOptionPane.showMessageDialog(this, "Data Added");
					}
				}
				
			}
			catch(SQLException se)
			{
				System.out.println(se);
			}
		}
		if(caption.equals("BACK"))
		{
			CounFrame cf=new CounFrame();
			cf.setVisible(true);
			this.dispose();
		}
	}
}
