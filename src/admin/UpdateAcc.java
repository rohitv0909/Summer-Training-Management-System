package admin;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class UpdateAcc extends JFrame implements ActionListener
{
	private JPanel contentPane;
	private JTextField txtid;
	private JTextField txtpass;
	private JRadioButton rdtrainer;
	private JRadioButton rdacdhead;
	private JRadioButton rdcoun;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private JTextField txttype;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateAcc frame = new UpdateAcc();
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
	public UpdateAcc() 
	{
		setTitle("Update Account");
		con=CrudOperation.createConnection();
		createGUI();
	}
	public void createGUI()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 476, 354);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Password");
		lblNewLabel.setBounds(112, 102, 71, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setBounds(112, 53, 71, 14);
		contentPane.add(lblNewLabel_1);
		
		txtid = new JTextField();
		txtid.setBounds(193, 50, 138, 20);
		contentPane.add(txtid);
		txtid.setColumns(10);
		
		txtpass = new JTextField();
		txtpass.setBounds(193, 99, 138, 20);
		contentPane.add(txtpass);
		txtpass.setColumns(10);
		
		JButton btnshow = new JButton("Show");
		btnshow.addActionListener(this);
		btnshow.setBounds(75, 237, 89, 23);
		contentPane.add(btnshow);
		
		JButton btnupdate = new JButton("Update");
		btnupdate.addActionListener(this);
		btnupdate.setBounds(283, 237, 89, 23);
		contentPane.add(btnupdate);
		
		JLabel lblNewLabel_2 = new JLabel("UserType");
		lblNewLabel_2.setBounds(112, 152, 71, 14);
		contentPane.add(lblNewLabel_2);
		
		txttype = new JTextField();
		txttype.setBounds(193, 149, 138, 20);
		contentPane.add(txttype);
		txttype.setColumns(10);
		
		rdtrainer = new JRadioButton("Trainer");
		buttonGroup.add(rdtrainer);
		rdtrainer.setBounds(49, 207, 109, 23);
		contentPane.add(rdtrainer);
		
		rdacdhead = new JRadioButton("Academic Head");
		buttonGroup.add(rdacdhead);
		rdacdhead.setBounds(165, 207, 138, 23);
		contentPane.add(rdacdhead);
		
		rdcoun = new JRadioButton("Counsellor");
		buttonGroup.add(rdcoun);
		rdcoun.setBounds(313, 207, 109, 23);
		contentPane.add(rdcoun);
		
		JLabel lblNewUserType = new JLabel("New user type :");
		lblNewUserType.setBounds(49, 186, 89, 14);
		contentPane.add(lblNewUserType);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(this);
		btnBack.setBounds(10, 11, 89, 23);
		contentPane.add(btnBack);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String id=txtid.getText();
		String caption=e.getActionCommand();
		if(caption.equals("Back"))
		{
			AdminFrame ad=new AdminFrame();
			ad.setVisible(true);
			this.dispose();
		}
		if(caption.equalsIgnoreCase("Show"))
		{
			if(id.isEmpty())
			{
				JOptionPane.showMessageDialog(this, "Enter id first !");
			}
			else
			{
			String  strsql="select userpass,usertype from account where userid=?";
			try
			{
				ps=con.prepareStatement(strsql);
				ps.setString(1, id);
				rs=ps.executeQuery();
				if(rs.next()) 
				{
					String pass=rs.getString("userpass");
					txtpass.setText(pass);
					String type=rs.getString("usertype");
					txttype.setText(type);
				}
				else
				{
					JOptionPane.showMessageDialog(this, "No such id !");
				}
			}
			catch(SQLException se)
			{
				System.out.println(se);
			}
		}
		if(caption.equalsIgnoreCase("Update"))
		{
			String strupdate="update account set userpass=?,usertype=? where userid=?";
			try
			{
				String pass=txtpass.getText();
				String acid=txtid.getText();
				String utype=txttype.getText();
				if(rdtrainer.isSelected())
				{
					utype=rdtrainer.getText();
				}
				if(rdacdhead.isSelected())
				{
					utype=rdacdhead.getText();
				}
				if(rdcoun.isSelected())
				{
					utype=rdcoun.getText();
				}
				if(acid.isEmpty()||pass.isEmpty()||utype.isEmpty())
				{
					JOptionPane.showMessageDialog(this, "Please enter all details !!");
				}
				else
				{
					ps=con.prepareStatement(strupdate);
					ps.setString(1, pass);
					ps.setString(2, utype);
					ps.setString(3, acid);
					int rw=ps.executeUpdate();
					if(rw>0)
					{
						JOptionPane.showMessageDialog(this,"Data Updated !");
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
}
