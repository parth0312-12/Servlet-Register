package com.coder;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String n=request.getParameter("name");
		String e=request.getParameter("email");
		String p=request.getParameter("password");
		String add=request.getParameter("add");
		String c=request.getParameter("country");
		String s=request.getParameter("state");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/user", "root", "parthmehta");
			PreparedStatement ps=con.prepareStatement("insert into registeruser values(?,?,?,?,?,?)");
			ps.setString(1, n);
			ps.setString(2, e);
			ps.setString(3, p);
			ps.setString(4, add);
			ps.setString(5, c);
			ps.setString(6, s);
			
			int i=ps.executeUpdate();
			if(i>0) {
				out.print("You are successfully registered");
			}
		}catch(Exception e2) {
			System.out.println(e2);
		}
		out.close();
		doGet(request, response);
	}

}
