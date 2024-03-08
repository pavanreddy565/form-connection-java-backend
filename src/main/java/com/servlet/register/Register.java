package com.servlet.register;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class Register extends HttpServlet{
	private static final String Insert_query="insert into form values(?,?,?,?,?,?,?,?,?,?)";
	@Override
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//get PrintWriter
		PrintWriter pw=resp.getWriter();
		//set Content type
		resp.setContentType("text/html");
		String name =req.getParameter("Name");
		String fname =req.getParameter("fName");
		String dob =req.getParameter("DOB");
		String addr =req.getParameter("Addr");
		String email =req.getParameter("Email");
		String sname =req.getParameter("SName");
		int marks =Integer.parseInt(req.getParameter("SnameM"));
		String degree =req.getParameter("Degree");
		int percentage =Integer.parseInt(req.getParameter("Percentage"));
		String branch =req.getParameter("branches");
		System.out.println("Name :"+name);
		
		//load the jdbc driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//create the connection
	    try {
	    	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/form","root","Password");
	    	PreparedStatement ps=con.prepareStatement(Insert_query);
	    	//set the values
	    	ps.setString(1,name);
	    	ps.setString(2,fname);
	    	ps.setString(3,dob);
	    	ps.setString(4,addr);
	    	ps.setString(5,email);
	    	ps.setString(6,sname);
	    	ps.setInt(7, marks);
	    	ps.setString(8,degree);
	    	ps.setInt(9,percentage);
	    	ps.setString(10,branch);
	    	
	    	//exucute query
	    	int count=ps.executeUpdate();
	    	if(count==0) {
	    		pw.println("Record not submitted");
	    	}else {
	    		pw.println("Record submitted to database");
	    	}
	    }catch(Exception e) {
	    	pw.println(e.getMessage());
	    	e.printStackTrace();
	    }
		
		//close the stream
		pw.close();
	}@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	
}
