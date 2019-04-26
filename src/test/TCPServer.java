package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

	public static void main(String[] args) {

		ServerSocket serverSocket = null;

		try {
			// 1. �������� ����
			serverSocket = new ServerSocket();

			// 2. ���ε�(binding)
			// :Socket�� SocketAddress(IPAddress + Port)
			// �� ���ε� �Ѵ�.

			InetAddress inetAddress = InetAddress.getLocalHost();
			// String localhost = inetAddress.getHostAddress();
			// serverSocket.bind(new InetSocketAddress(inetAddress, 5000));
			serverSocket.bind(new InetSocketAddress("0.0.0.0", 5000));

			// 3. accept
			// :Ŭ���̾�Ʈ�� �����û�� ��ٸ���.
			Socket socket = serverSocket.accept();

			InetSocketAddress inetRemotesocketAdress = (InetSocketAddress) socket.getRemoteSocketAddress();

			String remoteHostAdress = inetRemotesocketAdress.getAddress().getHostAddress();
			int remotePort = inetRemotesocketAdress.getPort();

			System.out.println("[server] connected by client[" + remoteHostAdress + " : " + remotePort + "]");

			try {
				// 4. IOStream ne�޾ƿ���
				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();

				while (true) {
					// 5. ������ �б�
					byte[] buffer = new byte[256];
					int readByteCount = is.read(buffer);

					if (readByteCount == -1) {
						// Ŭ���̾�Ʈ�� �������� �� ���
						// close() �޼ҵ� ȣ��
						System.out.println("[server] closed by client");
						break;
					}

					String data = new String(buffer, 0, readByteCount);
					
					System.out.println("[server] received : " + data);
					
					//6. �����;���
					os.write(data.getBytes("UTF-8"));
				}

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (socket != null && socket.isClosed()) {

						socket.close();
					}

				} catch (IOException e) {
					e.printStackTrace();
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (serverSocket != null && serverSocket.isClosed()) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
