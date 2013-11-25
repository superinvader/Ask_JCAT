package com.jcat.help.searchServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddJCATRecordServlet
 */
@WebServlet("/AddJCATRecordServlet")
public class AddJCATRecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddJCATRecordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/ask_jcat_db";
		String user = "root";
		String password = "123456";
		
		try{
			Class.forName(driver);
			
		    Connection conn = DriverManager.getConnection(url, user, password);
		    if(!conn.isClosed()) {
		    	System.out.println("Succeeded connecting to the Database!");
		    }
		    
		    Statement statement = conn.createStatement();
		    String sql = "INSERT INTO ask_jcat_db.jcat_crash_table (keywords,description,solution,author) VALUES ('" +
		    		request.getParameter("keywords") + "','" +
		    		request.getParameter("description") + "','" +
		    		request.getParameter("solution") + "','" +
		    		request.getParameter("author") + 
		    		"')";
		    System.out.println(sql);
		    if( !statement.execute(sql)) {
		    	PrintWriter out=response.getWriter();
		    	String a="<body onLoad=\"checkForm()\"><script language=\"JavaScript\" type=\"text/JavaScript\">function checkForm(){"+ 
		    	    "alert(\"Record Add Success!\"); location.href='./index.html'; return true;}</script>";
		    	out.print(a);
		    }
		    
		    conn.close();
		    
		}catch(ClassNotFoundException e) {
			System.out.println("Sorry, can't find the Driver!");
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
