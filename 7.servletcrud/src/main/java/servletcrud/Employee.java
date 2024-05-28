package servletcrud;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/web")
public class Employee extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		create();
		insert();
		int id=Integer.parseInt(req.getParameter("empid"));
		String name=req.getParameter("empname");
		long num=Long.parseLong(req.getParameter("empnum"));
		int sal=Integer.parseInt(req.getParameter("empsal"));
	}
	
	public static void insert()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306", "test", "root");
			PreparedStatement statement = con.prepareStatement(
					"insert into Employeecrud.Employee values(?,?,?,?))");
			statement.setInt(1, id);
			statement.setString(2, name);
			statement.setLong(3, num);
			statement.setInt(4, sal);
			int b = statement.executeUpdate();
			System.out.println("data inserted");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void create()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/Employeecrud?createDatabaseIfNotExist=true", "test", "root");
			PreparedStatement statement = con.prepareStatement(
					"create table Employeecrud.Employee(empid integer unique,empname varchar(20),empnum long,empsal integer)");
			boolean b = statement.execute();
			System.out.println("table created");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
