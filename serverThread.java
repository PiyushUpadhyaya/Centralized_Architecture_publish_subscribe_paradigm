import java.io.*;
import java.net.*;
import java.sql.*;

public class serverThread extends serverclassThread {

	serverThread(Socket ss) {
		super(ss);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Throwable {
		// FileOutputStream out = null;
		ServerSocket ss = null;
		System.out.println("in main");
		Socket s = null;
		System.out.println("in try");
		ss = new ServerSocket(6666);

		// System.out.println(e);

		// while(!Thread.currentThread().isInterrupted())
		try {
			while (true) {
				s = ss.accept();
				// establishes connection
				// Thread thread = new serverThread().serverclass(s);
				// thread.start();
				// new serverThread().serverclass(s).start();
				// Thread thread = new serverThread().serverclass(s);
				serverclassThread a = new serverclassThread(s);
				Thread thread1 = new Thread(a);
				thread1.start();
				// a.connection();
			}
		}// s.close();// ss.close();
		catch (Exception e) {
			System.out.println("\n****Socket exception error****");

		}
		s.close();

	}
	/*
	 * public static void serverclass(Socket s) throws Throwable{ this.s = s;
	 * //File in = null; DataInputStream dis=new
	 * DataInputStream(s.getInputStream()); DataOutputStream dout=new
	 * DataOutputStream(s.getOutputStream());
	 * System.out.println("SERVER SIDE: running");
	 * Class.forName("com.mysql.jdbc.Driver");///errrororororoororo
	 * dout.writeUTF("\n1. Subscriber \t 2. Publisher"); //STEP 3: Open a
	 * connection System.out.println("Connecting to database..."); conn =
	 * DriverManager.getConnection(DB_URL,USER,PASS);
	 * 
	 * //STEP 4: Execute a query to create statment with // required arguments
	 * for RS example. System.out.println("Creating statement..."); stmt =
	 * conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
	 * ResultSet.CONCUR_READ_ONLY); String sql; File in = null;
	 * 
	 * //OutputStream os = s.getOutputStream(); //OutputStreamWriter osw = new
	 * OutputStreamWriter(os); try{ int id = 0;//BufferedWriter bw = new
	 * BufferedWriter(osw); id=dis.read(); System.out.println(id); String
	 * filename_sub; if(id==1) {
	 * 
	 * while(true) { dout.writeUTF(
	 * "\n1. Insert Interest \t 2. Get Interests\t 3. Update Interests");
	 * dout.flush(); new server().start(); int choice=(int)dis.readInt();
	 * System.out.println("running " +choice); if(choice==1) {
	 * dout.writeUTF("\nEnter username: \t"); String
	 * username=(String)dis.readUTF();
	 * System.out.println("\nUserName is \n"+username);
	 * dout.writeUTF("\nEnter number interest: \t");
	 * 
	 * int number_of_interest =
	 * dis.readInt();//Integer.parseInt((String)dis.readUTF());
	 * 
	 * System.out.println("\nEnter number interest \n"+number_of_interest);
	 * String [] interest = new String[number_of_interest]; for(int
	 * i=0;i<number_of_interest;i++) { interest[i]=(String)dis.readUTF(); } sql
	 * ="INSERT INTO details" +
	 * " values ('"+username+"','"+interest[0]+"','"+interest
	 * [1]+"','"+interest[2]+"','"+interest[3]+"','"+interest[4]+"')" ;
	 * System.out.println("run"); stmt.executeUpdate(sql);
	 * System.out.println("run"); } else if(choice==2) { String username =
	 * dis.readUTF();
	 * System.out.println("Interests corresponding to UserName ::-> \n"+username
	 * + " are::\n"); String [] interest_list = new String [6]; dout.flush();
	 * sql = "Select * from Details"+" where username = '"+username+"' ";
	 * ResultSet rs = stmt.executeQuery(sql); int j=0,i=0;
	 * System.out.println("SERVER SIDE: INTERESTS ARE \t "); rs.next();
	 * while(j<6)
	 * 
	 * { interest_list[i] = rs.getString(i+1);
	 * System.out.println(interest_list[i] +"\t");
	 * 
	 * dout.writeUTF(interest_list[i]); dout.flush(); i++;j++; } dout.flush();
	 * dout.writeUTF("Select the interest to get updated");
	 * System.out.println("Interest selected : ");
	 * System.out.println(filename_sub=dis.readUTF());
	 * 
	 * in = new File(filename_sub);String c;BufferedReader file_read = new
	 * BufferedReader(new FileReader(filename_sub));//String [][] news = new
	 * String [10][1]; System.out.println(in);
	 * 
	 * if(!(in.exists())== true) in.createNewFile();
	 * 
	 * else { i=0;j=0; while ((c = file_read.readLine())!= null) { dout.flush();
	 * c=file_read.readLine(); //news[i][j]=c; System.out.println(c);
	 * //dout.writeUTF(news[i][i]); dout.writeUTF(c); i++;
	 * System.out.println(i); } System.out.println("LOOP DONE");
	 * dout.writeUTF("BYE"); } file_read.close(); } else if(choice==3) {
	 * dout.writeUTF("\nEnter username: \t"); String
	 * username=(String)dis.readUTF();
	 * System.out.println("\nUserName is \n"+username); sql =
	 * "delete from details" + " where username = '"+username+"'";
	 * stmt.executeUpdate(sql);
	 * dout.writeUTF("\nEnter number of interests: \t");
	 * 
	 * int number_of_interest =
	 * dis.readInt();//Integer.parseInt((String)dis.readUTF());
	 * 
	 * System.out.println("\nEntered number interest \n"+number_of_interest);
	 * String [] interest = new String[number_of_interest]; for(int
	 * i=0;i<number_of_interest;i++) { interest[i]=(String)dis.readUTF(); } sql
	 * ="INSERT INTO details" +
	 * " values ('"+username+"','"+interest[0]+"','"+interest
	 * [1]+"','"+interest[2]+"','"+interest[3]+"','"+interest[4]+"')" ;
	 * 
	 * stmt.executeUpdate(sql); } dout.flush(); } } else if(id==2) { String
	 * filename_pub; //String [][] news = new String [10][1];
	 * //System.out.println(in); dout.writeUTF("Publisher side evoked");
	 * System.out.println("Publisher side evoked"); while(true) {
	 * System.out.println("in loop");
	 * dout.writeUTF("\nDear Publisher enter your username: \t"); String
	 * username=(String)dis.readUTF();
	 * System.out.println("\nUserName is \n"+username);
	 * dout.writeUTF("\nEnter password: \t"); filename_pub=dis.readUTF(); in =
	 * new File(filename_pub);String c;BufferedReader file_read = new
	 * BufferedReader(new FileReader(filename_pub));
	 * dout.writeUTF("\n1. Insert Topic Name \t "); dout.flush(); new
	 * server().start(); String choice=dis.readUTF();
	 * System.out.println("running " +choice);
	 * 
	 * // sql ="INSERT INTO details" +
	 * " values ('"+username+"','"+interest[0]+"','"
	 * +interest[1]+"','"+interest[2]+"','"+interest[3]+"','"+interest[4]+"')" ;
	 * System.out.println("run"); // stmt.executeUpdate(sql); //
	 * System.out.println("run"); dout.flush(); } }
	 * 
	 * }catch(Exception e) { System.out.println("Error in thread subclass"); } }
	 */
}