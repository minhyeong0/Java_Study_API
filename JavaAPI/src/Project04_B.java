import java.io.FileOutputStream;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class Project04_B {
	//Paragraph : �ܶ�, ����, �� �����
	//chunk : (�Ը� ū �κ�) �����
	public static void main(String[] args) {
		
		//������ pdf ���� �����
		Document doc = new Document();
		
		try {
			//���� �����
			FileOutputStream fos = new FileOutputStream("ParagraphDemo.pdf");
			
			//���� pdf����� �Լ�
			PdfWriter.getInstance(doc, fos);
			
			//pdf ����
			doc.open();
			
			String content = "your word is a lamp my feet and a light for my path";
			
			//���� �����, �ٰ��� 32
			Paragraph par1 = new Paragraph(32);
			
			//���� ���� 50
			par1.setSpacingBefore(50);
			par1.setSpacingAfter(50);
			
			for(int i=0; i<20; i++) {
				//�κ� �����
				Chunk chunk = new Chunk(content);
				par1.add(chunk);
			}
			
			doc.add(par1);
			doc.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}