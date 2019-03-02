package acdhead;
import java.sql.*;

public class CrudOperation 
{
	private static Connection con;
	
	public static Connection createConnection()
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");                
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdb","root","root");			                                 
		}
		catch(SQLException |ClassNotFoundException cse)
		{
			System.out.println(cse);
		}
		return con;
	}
}
