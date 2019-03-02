package admin;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

public class CreateAcc extends JFrame implements ActionListener
{
	private JPanel contentPane;
	private JTextField txtuid;
	private JTextField txtupass;
	private JComboBox cmbutype;
	private Connection con;
	private PreparedStatement ps;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateAcc frame = new CreateAcc();
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
	public CreateAcc()
	{
		setTitle("Create Account");
		con=CrudOperation.createConnection();
		createGUI();
	}
	
	public void createGUI()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("UserID");
		lblNewLabel.setBounds(105, 48, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(105, 86, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("User Type");
		lblNewLabel_2.setBounds(105, 122, 69, 14);
		contentPane.add(lblNewLabel_2);
		
		txtuid = new JTextField();
		txtuid.setBounds(206, 45, 114, 20);
		contentPane.add(txtuid);
		txtuid.setColumns(10);
		
		txtupass = new JTextField();
		txtupass.setBounds(206, 83, 114, 20);
		contentPane.add(txtupass);
		txtupass.setColumns(10);
		
		cmbutype = new JComboBox();
		cmbutype.setModel(new DefaultComboBoxModel(new String[] {"Select user type :", "Trainer", "Academic Head", "Counsellor"}));
		cmbutype.setBounds(206, 119, 114, 20);
		contentPane.add(cmbutype);
		
		JButton btncreate = new JButton("CREATE");
		btncreate.addActionListener(this);
		btncreate.setBounds(251, 176, 89, 23);
		contentPane.add(btncreate);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(this);
		btnBack.setBounds(85, 176, 89, 23);
		contentPane.add(btnBack);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String caption=e.getActionCommand();
		if(caption.equalsIgnoreCase("Back"))
		{
			AdminFrame ad=new AdminFrame();
			ad.setVisible(true);
			this.dispose();
		}
		if(caption.equalsIgnoreCase("Create"))
		{
		String id=txtuid.getText();
		String pass=txtupass.getText();
		String utype=(String)cmbutype.getSelectedItem();
		if(id.isEmpty()||pass.isEmpty()||utype.equals("Select user type :"))
		{
			JOptionPane.showMessageDialog(this, "Please enter all the details !");
		}
		else
		{
			try 
			{
				String strsql="insert into account values(?,?,?)";
				ps=con.prepareStatement(strsql);
				ps.setString(1, id);
				ps.setString(2, pass);
				ps.setString(3, utype);
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
