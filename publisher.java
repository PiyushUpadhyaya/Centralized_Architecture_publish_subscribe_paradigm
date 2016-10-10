import java.io.*;  
import java.net.*; 
import java.util.*;

public class client{  
public static void main(String[] args) { 
	 int interest = 6;boolean bool=true;
	 while(bool)
	{
	try{      
			System.out.println("CLIENT SIDE: running1");
			Socket s=new Socket("localhost",6666);  
			DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
			DataInputStream dis=new DataInputStream(s.getInputStream()); 
			Scanner reader = new Scanner(System.in);
			System.out.println("CLIENT SIDE: running2");
			
			System.out.println("\nCLient SIDE: "+dis.readUTF());
			
			int choice = reader.nextInt();
			String str,username,news_choice;
			int i=0;
			
			dout.writeInt(choice);
			System.out.println("CLient SIDE: runnings3");
			
			if(choice==1)
			{		
							str = dis.readUTF();
							System.out.println(str);
							username = reader.next();
							dout.writeUTF(username);
							dout.flush();
							str = dis.readUTF();
							System.out.println(str);
							interest = reader.nextInt();
						
							System.out.println("Number of interests to be entered \t"+interest);
							dout.writeInt(interest);
							dout.flush();
							String [] input = new String[interest];
							System.out.println("\nEnter the interests:");
								for(i=0;i<interest;i++)
								{
									
									input[i] = reader.next();
									dout.writeUTF(input[i]);
									dout.flush();
								}
						
			}
			
			else if (choice == 2)
			{
						System.out.println("\nEnter usernmae:");
						username = reader.next();
						dout.writeUTF(username);
						dout.flush();
						String [] input = new String[interest];
						System.out.println("\n The interests are : "+(interest-1));
						for(i=0;i<6;i++)
						{
							input[i] = dis.readUTF();
							if(i>=1)
							{
								System.out.println(" "+ i+"\t" +input[i]+"\t");
							}
						}
						
						System.out.println(dis.readUTF());
						news_choice = reader.next();
						dout.flush();
						dout.writeUTF(news_choice+".txt");
						System.out.println("News begins here::");i=0;String dummy;
						while(true)//dis.readUTF()!= "BYE")
						{
							
							dummy=dis.readUTF();
							if(dummy=="BYE")
								break;
							
							else
							{
								System.out.println(i);
								System.out.println(dummy);
							}
							i++;
						}
			}
			
			else if (choice == 3)
			{
				System.out.println("\nEnter username:");
				username = reader.next();
				dout.writeUTF(username);
				dout.flush();
				String [] input = new String[interest];
				System.out.println("\nEnter the interests:");
				for(i=0;i<interest;i++)
				{
					
					input[i] = reader.next();
					dout.writeUTF(input[i]);
					dout.flush();
				}
			System.out.println(dis.readUTF());
			
			}
				
	//	dout.close();	
		//s.close();
		//reader.close();
		 System.out.println("Want to review options? (true/false)");
		 bool=reader.nextBoolean();
	}catch(Exception e)
	{
	System.out.println(e);
	}  
  }
}

}  