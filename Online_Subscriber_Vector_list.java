import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.io.*;
import java.net.*;
import java.sql.*;

public class Online_Subscriber_Vector_list {

	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	final String DB_URL = "jdbc:mysql://localhost:1280/details";
	final String USER = "root";
	static final String PASS = "piyush";
	Statement stmt = null;
	static Connection conn = null;
	ResultSet rs = null;
	
	public Vector<Socket> Socket_info = new Vector<Socket>();
	public Vector<String> username_info = new Vector<String>();
	public Socket s;
	public Online_Subscriber_Vector_list(Socket s) {
		
		Socket_info.add(s);
		System.out.println();
		//for (int i = 0; i < Socket_info.size(); i++) {
			//System.out.println(Socket_info.get(i));
		//}
	}

	/*public Online_Subscriber_Vector_list(String name) {
		
		username_info.add(name);
		System.out.println();
		//for (int i = 0; i < username_info.size(); i++) {
		//	System.out.println(username_info.get(i));
	//	}
	}*/
	
	public Online_Subscriber_Vector_list(String interest,int x)
	{
		//for x==1 interest is username and for x==2 interest=interest;
	if(x==1)
	{
		
		 		username_info.add(interest);
				System.out.println("in sub cond");
				for (int i = 0; i < username_info.size(); i++) {
					System.out.println(username_info.get(i));
				}
			
	}
	else if(x==2)
	{
		String[] List_of_subs = new String [100];
		int i=0;
		//serverclassThread object = new serverclassThread();
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
		} catch (SQLException e) {
			
			System.out.println("IN ONLINE SUBSCRIBER VECTOR LIST 3rd COnstrutor DB Connection error");
		}
		String sql;
		int j=0;
		for(i=0;i<username_info.size();i++)
		{
			sql = "Select * from details" + " where username = '" + username_info.get(i)
					+ "' and interest1 = '" + interest + "' ";
			try {
				ResultSet rs = stmt.executeQuery(sql);
			} catch (SQLException e) {
				System.out
						.println("IN ONLINE SUBSCRIBER VECTOR LIST 3rd COnstrutor sql execution error");
			}

			try {
				while (rs.next()) {
					List_of_subs[j] = rs.getString("interest1");
					j++;
				}
				System.out.println("users " + List_of_subs[0]);
			} catch (SQLException e) {
				System.out
						.println("IN ONLINE SUBSCRIBER VECTOR LIST 3rd COnstrutor rs.next error");
			}
		
		for(i=0;i<j;i++)
			{
				System.out.println(List_of_subs[i]);
				System.out.println("Loop WOrking in online last below list printing");
			}
		}
	/*	try{
			DataInputStream dis = new DataInputStream(s.getInputStream());
			DataOutputStream dout = new DataOutputStream(s.getOutputStream());
			}catch(Exception e){
				System.out.println("Error in Online Subs class in creating dout and dis");
			}*/
	}
	}

}
