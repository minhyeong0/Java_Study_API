import java.net.*;
import java.io.*;

public class Project05_A_Server {
	
	public static void main(String[] args) {
		
		//���� ���� (�ȳ���)
		//Ŭ���̾�Ʈ���� ���� ip�ּҿ� ��Ʈ��ȣ�� �ĺ��� Ŭ���̾�Ʈ ���� ���� ����
		ServerSocket ss = null;
		try {
			//������ ��Ʈ��ȣ �����
			ss = new ServerSocket(9999);
			System.out.println("Server Ready....");	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		while(true) {
			try {
				//���� ���
				Socket socket = ss.accept();
				System.out.println("client connect success!");
				
				//�а� ���⸦ ���� IO ��ü ����
				//���� Ŭ���̾�Ʈ���� �� ������ �б�
				InputStream in = socket.getInputStream();
				//�ѱ۵����� ���� ����
				DataInputStream dis = new DataInputStream(in);
				//���� �����͸� String ���·� ��ȯ
				String message = dis.readUTF();
				
				//Ŭ���̾�Ʈ���� �޼��� ������
				OutputStream out = socket.getOutputStream();
				//�ѱ۵����� ���� ����
				DataOutputStream dos = new DataOutputStream(out);
				//���� �����͸� String ���·� ������
				dos.writeUTF("[ECHO]"+message+"(from Server!)");
				
				dis.close();
				dos.close();
				socket.close();
				System.out.println("client socket close....");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}//while
	}//main
}//class