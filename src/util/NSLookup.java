package util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NSLookup {

	public static void main(String[] args) {
	
		Scanner sc = null;
		try {
			sc = new Scanner(System.in);
			
			while(true) {
				System.out.print(">");
				String hostName = sc.nextLine();
				
				if(hostName.equals("exit")) break;
				
				try {
					InetAddress[] inetAddresses = InetAddress.getAllByName(hostName);
					
					for(int i=0; i<inetAddresses.length; i++) {
						System.out.println(inetAddresses[i]);
						
					}
					
				} catch (UnknownHostException e) {
					//e.printStackTrace();
					System.out.println("잘못된 형식입니다.");
				}	
			}
		} catch (Exception e) {
			
		} finally {
			if (sc != null) {
				sc.close();
			}
		}
	}
}
