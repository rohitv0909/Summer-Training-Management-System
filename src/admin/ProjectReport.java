/*package admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

public class ProjectReport extends JFrame implements ActionListener
{
	private JPanel contentPane;
	private Connection con;
	private PreparedStatement ps,ps1,pscount,psdata;
	private ResultSet rs,rs1,rscount,rsdata;
	private Object[][]data;
	private String[]colnames={"Student ID","Submission Status"};
	private JTable jt;
	private JScrollPane jsp;
	private JComboBox cmbpr;

	*//**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProjectReport frame = new ProjectReport();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	*//**
	 * Create the frame.
	 *//*
	public void popCmb()
	{
		String st="select projectname from project";
		try
		{
			ps=con.prepareStatement(st);
			rs=ps.executeQuery();
			while(rs.next()) 
			{
				String pnm=rs.getString("projectname");
				cmbpr.addItem(pnm);		
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
		setBounds(100, 100, 494, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		jt=new JTable(new DefaultTableModel(data,colnames));
		jsp=new JScrollPane(jt);
		jsp.setBounds(95, 68, 300, 191);
		contentPane.add(jsp);
		
		JLabel lblProjectName = new JLabel("Project Name");
		lblProjectName.setFont(new Font("Arial", Font.PLAIN, 12));
		lblProjectName.setBounds(102, 30, 85, 14);
		contentPane.add(lblProjectName);
		
		cmbpr = new JComboBox();
		cmbpr.setModel(new DefaultComboBoxModel(new String[] {"Select Project Name :"}));
		cmbpr.setBounds(222, 28, 173, 20);
		popCmb();
		contentPane.add(cmbpr);
		
		JButton btnShowReport = new JButton("SHOW REPORT");
		btnShowReport.addActionListener(this);
		btnShowReport.setBounds(168, 285, 135, 23);
		contentPane.add(btnShowReport);
	}
	
	public ProjectReport() 
	{
		con=CrudOperation.createConnection();
		fillData();
		createGUI();
	}
	
	public void fillData()
	{
		try
		{
	        String pid="55";
			String strcount="select count(studentid) from student where pid=?";	
			pscount=con.prepareStatement(strcount);
			pscount.setString(1, pid);
			rscount=pscount.executeQuery();
			rscount.next();
			int cnt=rscount.getInt(1);
			data=new Object[cnt][2];			
			String strdata="select studentid,submitted from student where pid=?";
			psdata=con.prepareStatement(strdata);
			psdata.setString(1, pid);
			rsdata=psdata.executeQuery();
			int row=0;
			while(rsdata.next())
			{
				data[row][0]=rsdata.getString("studentid");
				data[row][1]=rsdata.getString("submitted");
				row++;
			}
		}
		catch(SQLException se)
		{
			System.out.println(se);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		try
		{
			String projectnm=(String)cmbpr.getSelectedItem();
			if(projectnm.equalsIgnoreCase("Select Project Name :"))
			{
				JOptionPane.showMessageDialog(this, "Select Project Name !");
			}
			else
			{
				String str2="select projectid from project where projectname=?";
				ps1=con.prepareStatement(str2);
				ps1.setString(1, projectnm);
				rs1=ps1.executeQuery();
				String pid=null;
				while(rs1.next())
				{
					pid=rs1.getString("projectid");
				}
				String strcount="select count(studentid) from student where pid=?";	
				pscount=con.prepareStatement(strcount);
				pscount.setString(1, pid);
				rscount=pscount.executeQuery();
				rscount.next();
				int cnt=rscount.getInt(1);
				data=new Object[cnt][2];			
				String strdata="select * from student where pid=?";
				psdata=con.prepareStatement(strdata);
				psdata.setString(1, pid);
				rsdata=psdata.executeQuery();
				int row=0;
				while(rsdata.next())
				{
					data[row][0]=rsdata.getString("studentid");
					data[row][1]=rsdata.getString("submitted");
					row++;
				}
			}
		}
		catch(SQLException se)
		{
			System.out.println(se);
		}
	}
}
*/