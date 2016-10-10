import java.io.*;
import java.net.*;
import java.util.*;

public class client {
	public static void main(String[] args) {
		int interest = 6;
		boolean bool = true;
		while (bool) {
			try {
				System.out.println("CLIENT SIDE: running1");
				System.out.println("CLIENT SIDE: running2");
				Socket s = new Socket("localhost", 6666);
				DataOutputStream dout = new DataOutputStream(
						s.getOutputStream());
				DataInputStream dis = new DataInputStream(s.getInputStream());
				BufferedReader br = new BufferedReader(new InputStreamReader(
						System.in));
				Scanner reader = new Scanner(System.in);
				System.out.println("CLIENT SIDE: running3");

				System.out.println("\nCLient SIDE: " + dis.readUTF());
				int id = reader.nextInt();
				System.out.println(id);
				dout.write(id);
				System.out.println(id);
				System.out.println("\nCLient SIDE: " + dis.readUTF());
				int choice = reader.nextInt();

				String str, username, news_choice, password, topic;
				int i = 0;

				dout.writeInt(choice);
				System.out.println("CLient SIDE: runnings3");

				if (choice == 1) {
					boolean choice_interest = true;
					while (choice_interest) {
						dout.writeBoolean(choice_interest);
						System.out.println(dis.readUTF());
						username = reader.next();
						dout.writeUTF(username);
						dout.flush();
						System.out.println(dis.readUTF());
						password = reader.next();
						dout.writeUTF(password);
						dout.flush();
						System.out.println(dis.readUTF());
						topic = reader.next();
						dout.writeUTF(topic);
						System.out
								.println("Want to enter more Interest?(true/false)");
						choice_interest = reader.nextBoolean();

					}

				}

				else if (choice == 2) {
					;
					System.out
							.println("\n***GET THE LIST OF YOUR INTERESTS HERE***");
					System.out.println("\nEnter username:");
					username = reader.next();
					dout.writeUTF(username);
					dout.flush();
					System.out.println("\nEnter Password:");
					password = reader.next();
					dout.writeUTF(password);
					dout.flush();
					// String [] input = new String[interest];
					System.out.println("\n The interests are : ");
					i = 0;
					while (dis.readInt() != -1) {
						System.out.println("Loop run count: " + i);
						System.out.println(dis.readUTF() + "\t");
						i++;
					}
					System.out.println(dis.readUTF());
					news_choice = reader.next();
					dout.flush();
					dout.writeUTF(news_choice + ".txt");
					System.out.println("News begins here::");
					i = 1;
					String dummy;
					while (dis.readInt() != -1)// dis.readUTF()!= "BYE")
					{

						dummy = dis.readUTF();
						System.out.println(dummy);

					}
				}

				else if (choice == 3) {
					System.out.println("\nEnter username:");
					username = reader.next();
					dout.writeUTF(username);
					dout.flush();
					String[] input = new String[interest];
					System.out.println("\nEnter the interests:");
					for (i = 0; i < interest; i++) {

						input[i] = reader.next();
						dout.writeUTF(input[i]);
						dout.flush();
					}
					System.out.println(dis.readUTF());

				}

				// dout.close();
				// s.close();
				// reader.close();
				System.out.println("Want to review options? (true/false)");
				bool = reader.nextBoolean();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

}