/*package admin;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class CourseReport extends JFrame implements ActionListener
{
	private JPanel contentPane;
	private PreparedStatement ps,ps1,pscount,psdata;
	private ResultSet rs,rs1,rscount,rsdata;
	private Connection con;
	private JComboBox cmbcname;
	private Object[][]data;
	private String[]colnames={"id","pass"};
	private JTable jt;
	private JScrollPane jsp;

	*//**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CourseReport frame = new CourseReport();
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
		String st="select coursename from course";
		try
		{
			ps=con.prepareStatement(st);
			rs=ps.executeQuery();
			while(rs.next()) 
			{
				String cnm=rs.getString("coursename");
				cmbcname.addItem(cnm);		
			}
		}
		catch(SQLException se)
		{
			System.out.println(se);
		}
	}
	
	public CourseReport() 
	{
		con=CrudOperation.createConnection();
		createGUI();
	}
	
	public void createGUI()
	{
		con=CrudOperation.createConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 491, 381);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCourseName = new JLabel("Course Name");
		lblCourseName.setBounds(90, 47, 77, 14);
		contentPane.add(lblCourseName);
		
		cmbcname = new JComboBox();
		cmbcname.setModel(new DefaultComboBoxModel(new String[] {"Select Course Name :"}));
		cmbcname.setBounds(231, 44, 155, 20);
		popCmb();
		contentPane.add(cmbcname);
		
		jt=new JTable(new DefaultTableModel(data,colnames));
		jsp=new JScrollPane();
		jsp.setBounds(69, 266, 317, -167);
		contentPane.add(jsp);
				
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(this);
		btnBack.setBounds(69, 297, 89, 23);
		contentPane.add(btnBack);
		
		JButton btnShow = new JButton("SHOW REPORT");
		btnShow.addActionListener(this);
		btnShow.setBounds(248, 297, 138, 23);
		contentPane.add(btnShow);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String caption=e.getActionCommand();
		if(caption.equalsIgnoreCase("back"))
		{
			AdminFrame af=new AdminFrame();
			af.setVisible(true);
			this.dispose();
		}
		
		if(caption.equalsIgnoreCase("show"))
		{
			try
			{
				String coursenm=(String)cmbcname.getSelectedItem();
				if(coursenm.equalsIgnoreCase("Select Course Name :"))
				{
					JOptionPane.showMessageDialog(this, "Select Course Name !");
				}
				else
				{
					String str2="select cid from course where coursename=?";
					ps1=con.prepareStatement(str2);
					ps1.setString(1, coursenm);
					rs1=ps1.executeQuery();
					String cid=null;
					while(rs1.next())
					{
						cid=rs1.getString("cid");
					}
					String strcount="select count(studentid) from student where cid=?";	
					pscount=con.prepareStatement(strcount);
					pscount.setString(1, cid);
					rscount=pscount.executeQuery();
					rscount.next();
					int cnt=rscount.getInt(1);
					data=new Object[cnt][2];			
					String strdata="select * from student where cid=?";
					psdata=con.prepareStatement(strdata);
					psdata.setString(1, cid);
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
}
*/