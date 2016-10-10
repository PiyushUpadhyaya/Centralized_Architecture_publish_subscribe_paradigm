import java.io.*;  
import java.net.*; 
import java.util.*;

public class get_interest_sub {
	int interest = 6;
	int i=0;
	String str = dis.readUTF();
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
