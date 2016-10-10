import java.io.*;
import java.net.*;
import java.util.*;

public class publisher_side {
	public static void main(String[] args) {
		boolean bool = true;
		while (bool) {
			try {
				String str, username, password, news_name, dummy;
				System.out.println("CLIENT SIDE: running1");
				Socket s = new Socket("localhost", 6666);
				DataOutputStream dout = new DataOutputStream(
						s.getOutputStream());
				DataInputStream dis = new DataInputStream(s.getInputStream());
				BufferedReader br = new BufferedReader(new InputStreamReader(
						System.in));
				Scanner reader = new Scanner(System.in);
				System.out.println("Publisher SIDE: running1");
				System.out.println("\nCLient SIDE: " + dis.readUTF());
				int id = reader.nextInt();
				System.out.println(id);
				dout.write(id);
				System.out.println(id);
				System.out.println("\nCLient SIDE: " + dis.readUTF());

				System.out.println("\nCLient SIDE Username: " + dis.readUTF());// username
																				// entry
				// username password
				username = reader.next();
				dout.writeUTF(username);
				System.out.println("The username is :" + username);
				System.out.println(dis.readUTF());// Password entry
				password = reader.next();
				dout.writeUTF(password);
				System.out.println("The username is :" + password);
				System.out.println(dis.readUTF());
				news_name = br.readLine();
				System.out.println("The filename is :" + news_name);
				dout.writeUTF(news_name);
				System.out.println(dis.readUTF());
				int i = 0;
				while (true) {
					dout.writeBoolean(true);
					// dout.writeUTF(reader.next());
					dout.writeUTF(br.readLine());
					System.out.println(dis.readUTF());
					dout.flush();
					i++;
					if (i > 15) {
						System.out.println("More to write?true/false");
						bool = reader.nextBoolean();
						if (bool == true)
							i = 0;
						else
							break;
					}

				}
				dout.writeBoolean(false);
				System.out.println("Want to review options? (true/false)");
				bool = reader.nextBoolean();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

}