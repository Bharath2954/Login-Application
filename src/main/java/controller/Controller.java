package controller;
import java.sql.*;
public class Controller {

	String dbUrl = "jdbc:mysql://localhost:3306/student";
	String dbUname = "root";
	String dbPassword = "";
	String dbDriver = "com.mysql.cj.jdbc.Driver";
	
	public void save(String name,String userName,String email,String mobile,String password,String DOB)
	{
		try {
			Class.forName(dbDriver);
			Connection connection = DriverManager.getConnection(dbUrl,dbUname,dbPassword);
			Statement statement = connection.createStatement();
			String query = "INSERT INTO user(name,userName,email,mobile,password,DOB) "
					+ "VALUES('"+name+"','"+userName+"','"+email+"','"+mobile+"','"+password+"','"+DOB+"')";
			statement.execute(query);
			System.out.println("Query Executed Successfully");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public boolean verify(String userName,String Password)
	{
		try {
			Class.forName(dbDriver);
			Connection connection = DriverManager.getConnection(dbUrl,dbUname,dbPassword);
			Statement statement = connection.createStatement();
			String query = "SELECT password FROM user WHERE userName = '"+userName+"'";
			ResultSet resultSet = statement.executeQuery(query);
			resultSet.next();
			if(Password.equals(resultSet.getString("password")))
			{
				return true;
			}
			return false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public String remove(String UserName)
	{
		try {
			Class.forName(dbDriver);
			Connection connection = DriverManager.getConnection(dbUrl, dbUname, dbPassword);
			Statement statement = connection.createStatement();
			String query = "DELETE FROM user WHERE userName = '"+UserName+"'";
			String Query = "SELECT userName FROM user WHERE userName = '"+UserName+"'";
			ResultSet resultSet = statement.executeQuery(Query);
			if(!resultSet.next())
			{
				connection.close();
				return "No User Found";
			}
			//statement = connection.createStatement();
			statement.execute(query);
			connection.close();
			return "User Removed Sucessfully";
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
			return "An Error Occured";
		}
	}
}
