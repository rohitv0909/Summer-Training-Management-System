package acdhead;

import java.sql.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Action;
import javax.swing.JButton;

public class Courses extends JFrame implements ActionListener
{
	private JPanel contentPane;
	private Connection con;
	private PreparedStatement ps;
	private JTextField txtcid;
	private JTextField txtcname;
	private JTextField txtfee;
	private JTextField txtduration;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Courses frame = new Courses();
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
		setBounds(100, 100, 474, 329);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CourseID");
		lblNewLabel.setBounds(101, 36, 59, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Course Name");
		lblNewLabel_1.setBounds(101, 77, 81, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Fee");
		lblNewLabel_2.setBounds(101, 118, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Duration");
		lblNewLabel_3.setBounds(101, 159, 59, 14);
		contentPane.add(lblNewLabel_3);
		
		txtcid = new JTextField();
		txtcid.setBounds(213, 33, 124, 20);
		contentPane.add(txtcid);
		txtcid.setColumns(10);
		
		txtcname = new JTextField();
		txtcname.setBounds(213, 74, 124, 20);
		contentPane.add(txtcname);
		txtcname.setColumns(10);
		
		txtfee = new JTextField();
		txtfee.setBounds(213, 115, 124, 20);
		contentPane.add(txtfee);
		txtfee.setColumns(10);
		
		txtduration = new JTextField();
		txtduration.setBounds(213, 156, 124, 20);
		contentPane.add(txtduration);
		txtduration.setColumns(10);
		
		JButton btncreate = new JButton("Create Course");
		btncreate.addActionListener(this);
		btncreate.setBounds(163, 210, 129, 23);
		contentPane.add(btncreate);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(this);
		btnBack.setBounds(163, 244, 129, 23);
		contentPane.add(btnBack);
	}
	public Courses() 
	{
		setTitle("Create Course");
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
		
		if(caption.equalsIgnoreCase("Create Course"))
		{
			String id=txtcid.getText();
			String name=txtcname.getText();
			int fee=Integer.parseInt(txtfee.getText());
			String duration=txtduration.getText();
			if(id.isEmpty()||name.isEmpty()||fee==0||duration.isEmpty())
			{
				JOptionPane.showMessageDialog(this, "Please enter all the details !");
			}
			else
			{
				try 
				{
						String strsql="insert into course values(?,?,?,?)";
						ps=con.prepareStatement(strsql);
						ps.setString(1, id);
						ps.setString(2, name);
						ps.setInt(3, fee);
						ps.setString(4, duration);
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
