import java.io.*;
import java.net.*;
import java.sql.*;
import java.lang.*;
import java.util.*;

public class serverclassThread implements Runnable {

	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	final String DB_URL = "jdbc:mysql://localhost:1280/details";
	final String USER = "root";
	static final String PASS = "piyush";
	Statement stmt = null;
	static Connection conn = null;
	Socket s;

	serverclassThread(Socket ss) {
		this.s = ss;
		Online_Subscriber_Vector_list subscriber = new Online_Subscriber_Vector_list(
				ss);
	}

	// File in = null;
	@Override
	public void run() {
		try {
			DataInputStream dis = new DataInputStream(s.getInputStream());
			DataOutputStream dout = new DataOutputStream(s.getOutputStream());

			System.out.println("SERVER SIDE: running");
			// Class.forName("com.mysql.jdbc.Driver");///errrororororoororo
			dout.writeUTF("\n1. Subscriber \t 2. Publisher");
			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 4: Execute a query to create statment with
			// required arguments for RS example.
			System.out.println("Creating statement...");
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			String sql;
			File in = null;

			// OutputStream os = s.getOutputStream();
			// OutputStreamWriter osw = new OutputStreamWriter(os);
			try {
				int id = 0;// BufferedWriter bw = new BufferedWriter(osw);
				id = dis.read();
				System.out.println(id);
				String filename_sub;
				if (id == 1) {

					while (true) {
						dout.writeUTF("\n1. Insert Interest \t 2. Get Interests\t 3. Update Interests");
						dout.flush();
						new server().start();
						int choice = (int) dis.readInt();
						String topic;
						System.out.println("running " + choice);

						if (choice == 1) {
							while (dis.readBoolean()) {
								dout.writeUTF("\nEnter username: \t");
								String username = (String) dis.readUTF();
								Online_Subscriber_Vector_list user = new Online_Subscriber_Vector_list(username,1);
								System.out.println("\nUserName is \n"
										+ username);
								dout.writeUTF("\nEnter password: \t");
								String password = (String) dis.readUTF();
								System.out.println("\nPassword is \n"
										+ password);
								dout.writeUTF("\nEnter your new interest: \t");
								topic = (String) dis.readUTF();
								sql = "INSERT INTO details" + " values ('"
										+ username + "','" + topic + "','"
										+ password + "')";
								stmt.executeUpdate(sql);
							}
						} else if (choice == 2) {
							String username = dis.readUTF();
							String password = dis.readUTF();
							Online_Subscriber_Vector_list user = new Online_Subscriber_Vector_list(username,1);
							String[] interest = new String[20];
							System.out
									.println("Interests corresponding to UserName ::-> \n"
											+ username + " are::\n");
							dout.flush();
							sql = "Select * from details"
									+ " where username = '" + username
									+ "' and password = '" + password + "' ";
							ResultSet rs = stmt.executeQuery(sql);
							int j = 0, i = 0;
							System.out
									.println("SERVER SIDE: INTERESTS ARE \t ");
							i = 0;
							while (rs.next()) {
								dout.writeInt(1);
								dout.flush();
								interest[i] = rs.getString("interest1");
								System.out.println(interest[i] + "\t");
								dout.writeUTF(interest[i]);
								i++;
							}
							dout.writeInt(-1);
							dout.flush();
							dout.writeUTF("\nSelect the interest to get updated");
							System.out.println("Interest selected : ");
							System.out.println(filename_sub = dis.readUTF());

							in = new File(filename_sub);
							String c;
							BufferedReader file_read = new BufferedReader(
									new FileReader(filename_sub));// String [][]
																	// news =
																	// new
																	// String
																	// [10][1];
							System.out.println(in);

							if (!(in.exists()) == true)
								System.out
										.println("Please enter some valid input");

							else {
								i = 0;
								j = 0;
								while ((c = file_read.readLine()) != null) {
									dout.writeInt(1);
									dout.flush();
									c = file_read.readLine();
									System.out.println(c);
									dout.writeUTF(c);
									i++;

								}
								dout.writeInt(-1);
							}

						} else if (choice == 3) {
							dout.writeUTF("\nEnter username: \t");
							String username = (String) dis.readUTF();
							Online_Subscriber_Vector_list user = new Online_Subscriber_Vector_list(username,1);
							System.out.println("\nUserName is \n" + username);
							sql = "delete from details" + " where username = '"
									+ username + "'";
							stmt.executeUpdate(sql);
							dout.writeUTF("\nEnter number of interests: \t");

							int number_of_interest = dis.readInt();// Integer.parseInt((String)dis.readUTF());

							System.out.println("\nEntered number interest \n"
									+ number_of_interest);
							String[] interest = new String[number_of_interest];
							for (int i = 0; i < number_of_interest; i++) {
								interest[i] = (String) dis.readUTF();
							}
							sql = "INSERT INTO details" + " values ('"
									+ username + "','" + interest[0] + "','"
									+ interest[1] + "','" + interest[2] + "','"
									+ interest[3] + "','" + interest[4] + "')";

							stmt.executeUpdate(sql);
						}
						dout.flush();
					}
				} else if (id == 2) {
					String filename_pub = null, password = null;

					// String [][] news = new String [10][1];
					// System.out.println(in);
					dout.writeUTF("Publisher side evoked");
					System.out.println("Publisher side evoked");
					while (true) {
						System.out.println("in loop");
						dout.writeUTF("\nDear Publisher enter your username: \t");
						String username = (String) dis.readUTF();
						System.out.println("\nUserName is \n" + username);
						dout.writeUTF("\nEnter password: \t");
						password = (String) dis.readUTF();
						dout.writeUTF("\nInsert Heading Name: \t");
						filename_pub = dis.readUTF();
						// //CLASSS REQUIRED HERE
						topic_creation test = new topic_creation();
						File to_publish = test.topic_creation(filename_pub
								+ ".txt");

						dout.writeUTF("Enter the content:");
						System.out.println("File Opened");
						int i = 0;
						while (dis.readBoolean()) {
							FileWriter fileWriter = new FileWriter(to_publish,
									true);
							fileWriter.write(dis.readUTF() + " ");
							dout.writeUTF(i + "Time loop ran");
							fileWriter.close();
							i++;

						}
						// VECTOR CLAS NEEDED HERE.PLZ CREATE HERE WHICH WILL
						// STORE THE SUBS AND PUBS ONLINE INFO
					//	String[] List_of_subs = new String [100];
						
						Online_Subscriber_Vector_list object_vec_list= new Online_Subscriber_Vector_list(filename_pub,2);
						
						System.out.println("Loop says: I am done");
						// fileWriter.close();
						dout.flush();
					}
				}

			} catch (Exception e) {
				System.out.println("Error in thread subclass");
			}
		} catch (Exception e) {
			System.out.println("Error in run class");
		}

	}

}
