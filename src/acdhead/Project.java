package acdhead;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

public class Project extends JFrame implements ActionListener
{
	private JPanel contentPane;
	private JTextField txtpid;
	private JTextField txtpname;
	private JTextArea txtdetails;
	private Connection con;
	private PreparedStatement ps,ps1;
	private ResultSet rs;
	private JComboBox cmbctg;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Project frame = new Project();
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
	public void popCmb()
	{
		String strsql="select categoryname from projectcategory";
		try
		{
			ps=con.prepareStatement(strsql);
			rs=ps.executeQuery();
			while(rs.next()) 
			{
				String cname=rs.getString("categoryname");
				cmbctg.addItem(cname);		
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
		setBounds(100, 100, 458, 360);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProjectId = new JLabel("Project ID");
		lblProjectId.setBounds(68, 31, 63, 14);
		contentPane.add(lblProjectId);
		
		txtpid = new JTextField();
		txtpid.setBounds(213, 28, 131, 20);
		contentPane.add(txtpid);
		txtpid.setColumns(10);
		
		JLabel lblProjectName = new JLabel("Project Name");
		lblProjectName.setBounds(68, 83, 74, 14);
		contentPane.add(lblProjectName);
		
		txtpname = new JTextField();
		txtpname.setBounds(213, 80, 131, 20);
		contentPane.add(txtpname);
		txtpname.setColumns(10);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setBounds(68, 133, 46, 14);
		contentPane.add(lblCategory);
		
		cmbctg = new JComboBox();
		cmbctg.setModel(new DefaultComboBoxModel(new String[] {"Select a Category :"}));
		cmbctg.setBounds(213, 130, 152, 20);
		popCmb();
		contentPane.add(cmbctg);
		
		JLabel lblDetails = new JLabel("Details");
		lblDetails.setBounds(68, 182, 46, 14);
		contentPane.add(lblDetails);
		
		txtdetails = new JTextArea();
		txtdetails.setBounds(213, 177, 183, 57);
		contentPane.add(txtdetails);
		
		JButton btnCreateProject = new JButton("Create Project");
		btnCreateProject.addActionListener(this);
		btnCreateProject.setBounds(213, 268, 131, 23);
		contentPane.add(btnCreateProject);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(this);
		btnBack.setBounds(68, 268, 89, 23);
		contentPane.add(btnBack);
	}
	
	public Project() 
	{
		con=CrudOperation.createConnection();
		createGUI();
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
		
		if(caption.equalsIgnoreCase("Create Project"))
		{
			String pid=txtpid.getText();
			String pname=txtpname.getText();
			String cname=(String)cmbctg.getSelectedItem();
			String details=txtdetails.getText();
			try
			{
				if(pid.isEmpty()||pname.isEmpty()||details.isEmpty()||cname.equals("Select a Category :"))
				{
					JOptionPane.showMessageDialog(this, "Please enter all the details !!");
				}
				else
				{
					String cid=null;
					String strc="select categoryid from projectcategory where categoryname=?";
					ps=con.prepareStatement(strc);
					ps.setString(1, cname);
					rs=ps.executeQuery();
					if(rs.next())
					{
						cid=rs.getString("categoryid");
					}
					String str="insert into project (projectid,projectname,categoryid,details) values (?,?,?,?)";
					ps1=con.prepareStatement(str);
					ps1.setString(1, pid);
					ps1.setString(2, pname);
					ps1.setString(3, cid);
					ps1.setString(4, details);
					int row=ps1.executeUpdate();
					if(row>0)
					{
						JOptionPane.showMessageDialog(this, "Project Created");
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
