import java.io.*;  
import java.net.*;
import java.sql.*;

	public class serverThread2 extends Thread{ 
		static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		static final String DB_URL = "jdbc:mysql://localhost:1280/details";
	
		   //  Database credentials
		   static final String USER = "root";
		   static final String PASS = "piyush";
	public static void main(String[] args) throws Throwable{
		Connection conn = null;
		File in = null;
	   // FileOutputStream out = null;
		Statement stmt = null;
		ServerSocket ss=null;
		Socket s=null;
		try{  
			ss=new ServerSocket(7777);
			}
		catch(Exception e)
		{
		System.out.println(e);
		}
	//while(!Thread.currentThread().isInterrupted())
while(true)
{
	try{
		s=ss.accept();//establishes connection   
		Thread thread = new serverThread();
		thread.start();
		DataInputStream dis=new DataInputStream(s.getInputStream()); 
		DataOutputStream dout=new DataOutputStream(s.getOutputStream());
		System.out.println("SERVER SIDE: running");
		Class.forName("com.mysql.jdbc.Driver");///errrororororoororo
		
		//STEP 3: Open a connection
				System.out.println("Connecting to database...");
					conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
					//STEP 4: Execute a query to create statment with
					// required arguments for RS example.
					System.out.println("Creating statement...");
					stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
					String sql,filename;
					
					
					//OutputStream os = s.getOutputStream();
					//OutputStreamWriter osw = new OutputStreamWriter(os);
					//BufferedWriter bw = new BufferedWriter(osw);
					dout.writeUTF("\n1. Insert Interest \t 2. Get Interests\t 3. Update Interests");
					dout.flush();
			while(true)
			{
					new server().start();
					int choice=(int)dis.readInt();
					System.out.println("running " +choice);
					if(choice==1)
					{
						dout.writeUTF("\nEnter username: \t");
						String username=(String)dis.readUTF();
						System.out.println("\nUserName is \n"+username);
						dout.writeUTF("\nEnter number interest: \t");
						
						int number_of_interest = dis.readInt();//Integer.parseInt((String)dis.readUTF());
						
						System.out.println("\nEnter number interest \n"+number_of_interest);
						String [] interest = new String[number_of_interest];
						for(int i=0;i<number_of_interest;i++)
						{
							interest[i]=(String)dis.readUTF();
						}
						sql ="INSERT INTO details" + " values ('"+username+"','"+interest[0]+"','"+interest[1]+"','"+interest[2]+"','"+interest[3]+"','"+interest[4]+"')" ;
						System.out.println("run");
						stmt.executeUpdate(sql);
						System.out.println("run");
					}
					else if(choice==2)
						{
							String username = dis.readUTF();
							System.out.println("Interests corresponding to UserName ::-> \n"+username + " are::\n");
							String [] interest_list = new String [6];
							dout.flush();
							sql = "Select * from Details"+" where username = '"+username+"' ";
							ResultSet rs = stmt.executeQuery(sql);
							int j=0,i=0;
							System.out.println("SERVER SIDE: INTERESTS ARE \t ");
							rs.next();
							while(j<6)
		
							{
								interest_list[i] = rs.getString(i+1);
								System.out.println(interest_list[i] +"\t");
								
								dout.writeUTF(interest_list[i]);
								dout.flush();
								i++;j++;
							}
							dout.flush();
							dout.writeUTF("Select the interest to get updated");
							System.out.println("Interest selected : ");
							System.out.println(filename=dis.readUTF());
		
							in = new File(filename);String c;BufferedReader file_read = new BufferedReader(new FileReader(filename));//String [][] news = new String [10][1];
							System.out.println(in);
							
									if(!(in.exists())== true)
										in.createNewFile();
										
									else
									{	i=0;j=0;
										while ((c = file_read.readLine())!= null)
										{
											dout.flush();
											c=file_read.readLine();
											//news[i][j]=c;
											System.out.println(c);
											//dout.writeUTF(news[i][i]);
											dout.writeUTF(c);
											i++;
											System.out.println(i);
								         }
										System.out.println("LOOP DONE");
								         dout.writeUTF("BYE");
									}
									 file_read.close();
						} 
					else if(choice==3)
						{
							dout.writeUTF("\nEnter username: \t");
							String username=(String)dis.readUTF();
							System.out.println("\nUserName is \n"+username);
							sql = "delete from details" + " where username = '"+username+"'";
							stmt.executeUpdate(sql);
							dout.writeUTF("\nEnter number of interests: \t");
							
							int number_of_interest = dis.readInt();//Integer.parseInt((String)dis.readUTF());
							
							System.out.println("\nEntered number interest \n"+number_of_interest);
							String [] interest = new String[number_of_interest];
							for(int i=0;i<number_of_interest;i++)
							{
								interest[i]=(String)dis.readUTF();
							}
							sql ="INSERT INTO details" + " values ('"+username+"','"+interest[0]+"','"+interest[1]+"','"+interest[2]+"','"+interest[3]+"','"+interest[4]+"')" ;
				
							stmt.executeUpdate(sql);
						}
					dout.flush();
					
			}		
				//	s.close();
				//	ss.close();
	}catch(Exception e)
	{
		System.out.println("\n****Socket exception error****");
		
	}
}
}
}