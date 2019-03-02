package admin;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class DeleteAcc extends JFrame implements ActionListener
{

	private JPanel contentPane;
	private JComboBox cmbid;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private JButton btnBack;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteAcc frame = new DeleteAcc();
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
		String strsql="select userid from account";
		try
		{
			ps=con.prepareStatement(strsql);
			rs=ps.executeQuery();
			while(rs.next()) 
			{
				String id=rs.getString("userid");
				cmbid.addItem(id);		
			}
	    }
		catch(SQLException se)
		{
			System.out.println(se);
		}
	}
	
	public DeleteAcc() 
	{
		setTitle("Delete Account");
		con=CrudOperation.createConnection();
		createGUI();
	}

	public void createGUI()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btndelete = new JButton("Delete");
		btndelete.addActionListener(this);
		btndelete.setBounds(167, 124, 89, 23);
		contentPane.add(btndelete);
		
		cmbid = new JComboBox();
		cmbid.setBackground(Color.LIGHT_GRAY);
		cmbid.setModel(new DefaultComboBoxModel(new String[] {"Select user id"}));
		cmbid.setBounds(143, 50, 139, 20);
		populateCombo();
		contentPane.add(cmbid);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(this);
		btnBack.setBounds(167, 173, 89, 23);
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
		if(caption.equalsIgnoreCase("Delete"))
		{
		String id=(String)cmbid.getSelectedItem();
		if(id.equals("Select user id"))
		{
			JOptionPane.showMessageDialog(this, "Select ID first !");
		}
		else
		{
			int option=JOptionPane.showConfirmDialog(this, "Are you sure ?");
			if(option==0)
			{
				String strdelete="delete from account where userid=?";
				try
				{
					ps=con.prepareStatement(strdelete);
					ps.setString(1, id);
					int rw=ps.executeUpdate();
					if(rw>0)
					{
						JOptionPane.showMessageDialog(this, "Data deleted");
					}
					cmbid.removeAllItems();
					cmbid.addItem("Select user id");
					populateCombo();
				}
				catch(SQLException se)
				{
					System.out.println(se);
				}
			}
		}
	}
	}
}
