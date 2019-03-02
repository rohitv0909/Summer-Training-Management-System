package counsellor;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import login.*;

import javax.swing.JButton;

public class CounFrame extends JFrame implements ActionListener
{
	private JPanel contentPane;
    private Connection con;
	private PreparedStatement ps;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CounFrame frame = new CounFrame();
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
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(this);
		btnBack.setBounds(35, 24, 89, 23);
		contentPane.add(btnBack);
		
		JButton btnfeed = new JButton("Student Details Feeding");
		btnfeed.addActionListener(this);
		btnfeed.setBounds(216, 24, 192, 23);
		contentPane.add(btnfeed);
		
		JButton btnstatus = new JButton("Submission Status");
		btnstatus.addActionListener(this);
		btnstatus.setBounds(216, 79, 192, 23);
		contentPane.add(btnstatus);
	}
	public CounFrame() 
	{
		con=CrudOperation.createConnection();
		createGUI();
	}

	public void actionPerformed(ActionEvent e) 
	{
		String caption=e.getActionCommand();
		if(caption.equalsIgnoreCase("back"))
		{
			SignInFrame lf=new SignInFrame();
			lf.setVisible(true);
			this.dispose();
		}
		if(caption.equalsIgnoreCase("Student Details Feeding"))
		{
			StudentDetails sd=new StudentDetails();
			sd.setVisible(true);
			this.dispose();
		}
		if(caption.equalsIgnoreCase("Submission status"))
		{
			StatusUpdate st=new StatusUpdate();
			st.setVisible(true);
			this.dispose();
		}
	}
}
