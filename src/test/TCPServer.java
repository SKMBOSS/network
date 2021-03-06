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
			// 1. 서버소켓 생성
			serverSocket = new ServerSocket();

			// 2. 바인딩(binding)
			// :Socket에 SocketAddress(IPAddress + Port)
			// 를 바인딩 한다.

			InetAddress inetAddress = InetAddress.getLocalHost();
			// String localhost = inetAddress.getHostAddress();
			// serverSocket.bind(new InetSocketAddress(inetAddress, 5000));
			serverSocket.bind(new InetSocketAddress("0.0.0.0", 5000));

			// 3. accept
			// :클라이언트의 연결요청을 기다린다.
			Socket socket = serverSocket.accept();

			InetSocketAddress inetRemotesocketAdress = (InetSocketAddress) socket.getRemoteSocketAddress();

			String remoteHostAdress = inetRemotesocketAdress.getAddress().getHostAddress();
			int remotePort = inetRemotesocketAdress.getPort();

			System.out.println("[server] connected by client[" + remoteHostAdress + " : " + remotePort + "]");

			try {
				// 4. IOStream ne받아오기
				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();

				while (true) {
					// 5. 데이터 읽기
					byte[] buffer = new byte[256];
					int readByteCount = is.read(buffer);

					if (readByteCount == -1) {
						// 클라이언트가 정상종료 한 경우
						// close() 메소드 호출
						System.out.println("[server] closed by client");
						break;
					}

					String data = new String(buffer, 0, readByteCount);
					
					System.out.println("[server] received : " + data);
					
					//6. 데이터쓰기
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
