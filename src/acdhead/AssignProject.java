package acdhead;

import java.sql.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;

public class AssignProject extends JFrame implements ActionListener
{
	private JPanel contentPane;
    private Connection con;
    private PreparedStatement ps,ps1,ps2;
    private ResultSet rs;
    private JComboBox cmbproj;
    private JComboBox cmbid;
    private JDateChooser dateChooser;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AssignProject frame = new AssignProject();
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
	public void populateCombo()
	{
		String strsql="select projectname from project";
		try 
		{
			ps=con.prepareStatement(strsql);
			rs=ps.executeQuery();
			while(rs.next()) 
			{
				String pname=rs.getString("projectname");
				cmbproj.addItem(pname);		
			}
		}
		catch (SQLException se) 
		{
			System.out.println(se);
		}
	}
	
	public void populateCombo2()
	{
		String strsql="select studentid from student where submitted='Not Submitted'";
		try 
		{
			ps=con.prepareStatement(strsql);
			rs=ps.executeQuery();
			while(rs.next()) 
			{
				String stid=rs.getString("studentid");
				cmbid.addItem(stid);		
			}
		}
		catch (SQLException se) 
		{
			System.out.println(se);
		}
	}
	
	public AssignProject() 
	{
		con=CrudOperation.createConnection();
		createGUI();
	}
	
	public void createGUI()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 507, 372);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		cmbproj = new JComboBox();
		cmbproj.setModel(new DefaultComboBoxModel(new String[] {"Select Project :"}));
		cmbproj.setBounds(300, 139, 159, 20);
		populateCombo();
		contentPane.add(cmbproj);
		
		JLabel lblStudentsWhoHave = new JLabel("Students who have not been assigned project :");
		lblStudentsWhoHave.setFont(new Font("Arial", Font.PLAIN, 12));
		lblStudentsWhoHave.setBounds(10, 56, 280, 20);
		contentPane.add(lblStudentsWhoHave);
		
		cmbid = new JComboBox();
		cmbid.setModel(new DefaultComboBoxModel(new String[] {"Select Student ID :"}));
		cmbid.setBounds(300, 57, 159, 20);
		populateCombo2();
		contentPane.add(cmbid);
		
		JLabel lblProjectName = new JLabel("Project Name :");
		lblProjectName.setFont(new Font("Arial", Font.PLAIN, 12));
		lblProjectName.setBounds(97, 141, 90, 14);
		contentPane.add(lblProjectName);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(this);
		btnBack.setBounds(71, 270, 89, 23);
		contentPane.add(btnBack);
		
		JButton btnap = new JButton("ASSIGN PROJECT");
		btnap.addActionListener(this);
		btnap.setBounds(266, 270, 136, 23);
		contentPane.add(btnap);
		
		JLabel lblSelectDate = new JLabel("Select Date  :");
		lblSelectDate.setFont(new Font("Arial", Font.PLAIN, 12));
		lblSelectDate.setBounds(108, 211, 90, 14);
		contentPane.add(lblSelectDate);
		
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(300, 205, 159, 20);
		contentPane.add(dateChooser);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String caption=e.getActionCommand();
		if(caption.equalsIgnoreCase("back"))
		{
			AcdheadFrame ahf=new AcdheadFrame();
			ahf.setVisible(true);
			this.dispose();
		}
		
		if(caption.equalsIgnoreCase("ASSIGN PROJECT"))
		{
			String stuid=(String)cmbid.getSelectedItem();
			String projname=(String)cmbproj.getSelectedItem();
			java.util.Date d=dateChooser.getDate();
			if(d==null)
			{
				JOptionPane.showMessageDialog(this, "Date Not Selected");
			}
			long l=d.getTime();
			java.sql.Date sd=new java.sql.Date(l);
			if(stuid.equals("Select Student ID :")||projname.equals("Select Project :"))
			{
				JOptionPane.showMessageDialog(this, "Select Student ID/Project Name first !");
			}
			else
			{
				try
				{
					String pjid=null;
					String strc="select projectid from project where projectname=?";
					ps1=con.prepareStatement(strc);
					ps1.setString(1, projname);
					rs=ps1.executeQuery();
					if(rs.next())
					{
						pjid=rs.getString("projectid");
					}
					String str1="update student set pid=? where studentid=?";
					ps2=con.prepareStatement(str1);
					ps2.setString(1, pjid);
					ps2.setString(2, stuid);
					ps2.executeUpdate();
					String str2="insert into assignproject (projectid,studentid,dateassigned) values (?,?,?)";
					ps=con.prepareStatement(str2);
					ps.setString(1, pjid);
					ps.setString(2, stuid);
					ps.setDate(3, sd);
					int y=ps.executeUpdate();
					if(y>0)
					{
						JOptionPane.showMessageDialog(this, "Project Assigned");
					}
				}
				catch(SQLException se)
				{
					System.out.println(se);
				}
			}
		}
	}
}
