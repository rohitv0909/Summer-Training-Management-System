package acdhead;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import login.SignInFrame;

import javax.swing.JButton;

public class AcdheadFrame extends JFrame implements ActionListener
{
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AcdheadFrame frame = new AcdheadFrame();
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
	public AcdheadFrame() 
	{
		createGUI();
	}

	public void createGUI()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 475, 332);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(this);
		btnBack.setBounds(32, 38, 89, 23);
		contentPane.add(btnBack);
		
		JButton btnCourseCreation = new JButton("Create Course");
		btnCourseCreation.addActionListener(this);
		btnCourseCreation.setBounds(233, 38, 144, 23);
		contentPane.add(btnCourseCreation);
		
		JButton btnCreateProjectCategory = new JButton("Create Project Category");
		btnCreateProjectCategory.addActionListener(this);
		btnCreateProjectCategory.setBounds(205, 93, 197, 23);
		contentPane.add(btnCreateProjectCategory);
		
		JButton btnAllotProject = new JButton("Allot Project");
		btnAllotProject.addActionListener(this);
		btnAllotProject.setBounds(233, 204, 144, 23);
		contentPane.add(btnAllotProject);
		
		JButton btnProjectCreation = new JButton("New Project Creation");
		btnProjectCreation.addActionListener(this);
		btnProjectCreation.setBounds(219, 143, 170, 23);
		contentPane.add(btnProjectCreation);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String caption=e.getActionCommand();
		if(caption.equalsIgnoreCase("BACK"))
		{
			SignInFrame lf=new SignInFrame();
			lf.setVisible(true);
			this.dispose();
		}
		
		if(caption.equalsIgnoreCase("Create Course"))
		{
			Courses cs=new Courses();
			cs.setVisible(true);
			this.dispose();
		}
		
		if(caption.equalsIgnoreCase("Create Project Category"))
		{
			ProjectCtg pc=new ProjectCtg();
			pc.setVisible(true);
			this.dispose();
		}
		
		if(caption.equalsIgnoreCase("Allot Project"))
		{
			AssignProject ap=new AssignProject();
			ap.setVisible(true);
			this.dispose();
		}
		
		if(caption.equalsIgnoreCase("New Project Creation"))
		{
			Project pj=new Project();
			pj.setVisible(true);
			this.dispose();
		}
	}
}
