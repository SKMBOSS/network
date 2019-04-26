package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class NSLookup {

	public static void main(String[] args) {

		BufferedReader br = null;

		try {
			br = new BufferedReader(new InputStreamReader(System.in, "utf-8"));

			String hostName = null;
			System.out.print(">");

			while ((hostName = br.readLine()) != null) {

				if (hostName.equals("exit"))
					break;

				try {
					InetAddress[] inetAddresses = InetAddress.getAllByName(hostName);

					for (int i = 0; i < inetAddresses.length; i++) {
						System.out.println(inetAddresses[i]);
					}

				} catch (UnknownHostException e) {
					// e.printStackTrace();
					System.out.println("잘못된 접근입니다.");
				}
			}
		} catch (Exception e) {

		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}