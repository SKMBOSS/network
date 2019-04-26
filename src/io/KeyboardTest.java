package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class KeyboardTest {

	public static void main(String[] args) {

		BufferedReader br = null;

		try {
			// ��ݽ�Ʈ��(ǥ���Է�, Ű����, System.in)

			// ������Ʈ��1
			// byte|byte|byte -> char
			InputStreamReader isr = new InputStreamReader(System.in, "utf-8");

			// ������Ʈ��2
			// char|char|char|\n -> "char1char2char3"
			br = new BufferedReader(isr);

			// read
			String line = null;

			while ((line = br.readLine()) != null) {
				if ("exit".equals(line))
					break;

				System.out.println(">>" + line);
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
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
