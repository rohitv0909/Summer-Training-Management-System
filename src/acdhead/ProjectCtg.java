package acdhead;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

public class ProjectCtg extends JFrame implements ActionListener
{
	private JPanel contentPane;
	private JTextField txtid;
	private JComboBox cmbname;
	private Connection con;
	private PreparedStatement ps;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProjectCtg frame = new ProjectCtg();
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
		
		JLabel lblNewLabel = new JLabel("Category ID");
		lblNewLabel.setBounds(83, 48, 75, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Category Name");
		lblNewLabel_1.setBounds(83, 97, 90, 14);
		contentPane.add(lblNewLabel_1);
		
		txtid = new JTextField();
		txtid.setBounds(197, 45, 159, 20);
		contentPane.add(txtid);
		txtid.setColumns(10);
		
		cmbname = new JComboBox();
		cmbname.setModel(new DefaultComboBoxModel(new String[] {"Select a category :", "Summer Training", "Winter Training", "Internship", "Dummy Project"}));
		cmbname.setBounds(197, 94, 159, 20);
		contentPane.add(cmbname);
		
		JButton btncreate = new JButton("Create");
		btncreate.addActionListener(this);
		btncreate.setBounds(169, 162, 89, 23);
		contentPane.add(btncreate);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(this);
		btnBack.setBounds(169, 208, 89, 23);
		contentPane.add(btnBack);
	}
	public ProjectCtg()
	{
		setTitle("Project Category Creation");
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
		
		if(caption.equalsIgnoreCase("Create"))
		{
			String cid=txtid.getText();
			String cname=(String)cmbname.getSelectedItem();
			if(cid.isEmpty()||cname.equals("Select a category :"))
			{
				JOptionPane.showMessageDialog(this,"Please enter/select both the details !");
			}
			else
			{
				try
				{
					String strsql="insert into projectcategory values(?,?)";
					ps=con.prepareStatement(strsql);
					ps.setString(1, cid);
					ps.setString(2, cname);
					int row=ps.executeUpdate();
					if(row>0)
					{
						JOptionPane.showMessageDialog(this, "Data Added");
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
