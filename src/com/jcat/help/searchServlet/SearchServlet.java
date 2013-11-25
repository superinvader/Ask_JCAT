package com.jcat.help.searchServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
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
		
		JCATDBRecord result = new JCATDBRecord();
		try{
			Class.forName(driver);
			
		    Connection conn = DriverManager.getConnection(url, user, password);
		    if(!conn.isClosed()) {
		    	System.out.println("Succeeded connecting to the Database!" +
		                           "keywords = " + request.getParameter("keywords"));
		    }
		    
		    Statement statement = conn.createStatement();
		    String sql = "select distinct * from jcat_crash_table where keywords='" + request.getParameter("keyworkds") +"'";
		    ResultSet rs = statement.executeQuery(sql);
		    
		    while(rs.next()) {
		    	result.setKeywords(rs.getString("keywords"));
		    	result.setDescription(rs.getString("description"));
		    	result.setSolution(rs.getString("solution"));
		    	result.setAuthor(rs.getString("author"));
		    }
		    rs.close();
		    conn.close();
		}catch(ClassNotFoundException e) {
			System.out.println("Sorry, can't find the Driver!");
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	    response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    String title = "Searching Result";
	    String docType =
	      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
	      "Transitional//EN\">\n";
	    out.println(docType +
	                "<HTML>\n" +
	                "<HEAD>" +
	                "<TITLE>" + title + "</TITLE>" +
	                "<style>\n" +
	                ".result_tb{\n" +
	                "   border:#000000 solid;\n" +
	                "	border-width:1px 0px 0px 1px;\n" +
	                "	width: 100%;\n" +
	                "	height: 100%}\n" +
	                ".result_tb th{\n" +
	                "   padding: 5px 5px 5px 5px;\n" +
	                "	border:#000000 solid;\n" +
	                "	border-width:0px 1px 1px 0px;\n" + 
	                "	font-family: Arial; \n" +
	                "	font-size: 70px; \n" +
	                "	font-style: oblique;\n" +
	                "	font-weight: 900; \n" +
	                "	height:10%;\n" +
	                "	background: YellowGreen\n" +
	                "	}\n" +
	                ".result_tb td{\n" +
	                "   padding: 5px 20px 5px 30px;\n" +
	                "	border:#000000 solid;\n " +
	                "	border-width:0px 1px 1px 0px;\n" +
	                "	font-family: Arial;\n" +
	                "	font-size: 24px;\n " +
	                "	font-style: normal; \n" +
	                "	font-weight: 200;\n " +
	                "	height:30%;\n" +
	                "	text-align: left;\n" +
	                "	vertical-align: middle;\n" +
	                "	background: PaleTurquoise\n" +
	                "}\n" +
	                "</style>\n" +
	                "</HEAD>\n" +
	                "<BODY BGCOLOR=\"#FDF5E6\">\n" +
	                "<div id=\"content\">\n" +
	                "<table class=\"result_tb\" cellspacing=\"0\" border=\"0\">\n" +
	                "<thead>\n" +
	                "<tr>\n" +
	                "<th valign=\"middle\">Keywords</th>\n" +
	                "<th valign=\"middle\">Description</th>\n" +
	                "<th valign=\"middle\">Solution</th>\n" +
	                "<th valign=\"middle\">Author</th>\n" +
	                "</tr>\n" +
	                "</thead>\n" +
	                "<tbody>\n" +
	                "</tbody>\n" +
	                "<tr>\n" +
	                "<td valign=\"middle\">\n" +
	                result.getKeywords() +
	                "</td>\n" +
	                "<td valign=\"middle\">\n" +
	                result.getDescription() +
	                "</td>\n" +
	                "<td valign=\"middle\">\n" +
	                result.getSolution() + 
	                "</td>\n" +
	                "<td valign=\"middle\">\n" +
	                result.getAuthor() + 
	                "</td>\n" +
	                "</tr>\n" +
	                "</table>\n" +
	                "</BODY></HTML>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
