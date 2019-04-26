package test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Localhost {

	public static void main(String[] args) {
		
		try {
			
			InetAddress inetAddress = InetAddress.getLocalHost();
			
			String hostName = inetAddress.getHostName();
			String hostAddress = inetAddress.getHostAddress();
			
			System.out.println(hostName + " : " + hostAddress);
			
			byte[] addresses = inetAddress.getAddress();
			
			for(byte address : addresses) {
				System.out.print(address & 0x000000ff);
			}
			
			for(int i=0; i< addresses.length; i++) {
				
			}
			
//			InetAddress[] inetAddresses =InetAddress.getAllByName(hostName);
//			
//			for(InetAddress addr : inetAddresses) {
//				System.out.println(addr.getHostAddress());
//			}
			
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
		}
	}

}
