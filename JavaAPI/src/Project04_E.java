import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import dto.ExcelDTO;

public class Project04_E {
	public static void main(String[] args) {
		//���� ���ϰ��
		String fileName = "isbn.xls";
		
		//���� ������ ���
		List<ExcelDTO> data = new ArrayList<ExcelDTO>();
		
		//���ϰ�ü ����
		try(FileInputStream fis = new FileInputStream(fileName)) {
			
			//���������� �о �ε� (������ ���������� ����)
			HSSFWorkbook workbook = new HSSFWorkbook(fis);
			
			//������ ù��° ��Ʈ ����
			HSSFSheet sheet = workbook.getSheetAt(0);
			
			//��Ʈ���� ��ü���� iterator ���·� ������
			Iterator<Row> rows = sheet.rowIterator();
			
			//ù��°�� �����Ͱ� 2��° ���� �����͸� ������
			rows.next();
			
			//�ӽ� �迭
			String[] imsi = new String[5];
			
			//row�� �����Ͱ� ������ ture ������ false
			while(rows.hasNext()) {
				
				//������ ���� �����͸� ������
				HSSFRow row = (HSSFRow) rows.next();
				
				//���� ��ü ��(Į��) ��������
				Iterator<Cell> cells = row.cellIterator();
				
				int i = 0;
				//cell�� ���� �����ϸ� true ������ false
				while(cells.hasNext()) {
					
					//������ �ϳ��� ������
					HSSFCell cell = (HSSFCell) cells.next();
					
					//������ �ִ� �ӽ� �迭
					imsi[i] = cell.toString();
					i++;
					if(i==5) break;
				}
				
				ExcelDTO dto = new ExcelDTO(imsi[0], imsi[1], imsi[2], imsi[3], imsi[4]);
				data.add(dto);
			}
			workbook.close();
			
			pdf_maker(data);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void pdf_maker(List<ExcelDTO> data) {
		
		String[] headers = new String[] {"å����","����","���ǻ�","�̹���"};
		
		//�޸𸮿� ������ pdf ����
		Document doc = new Document(PageSize.A4);
		try {
			//���� pdf ���� ����
			PdfWriter.getInstance(doc, new FileOutputStream("bookList.pdf"));
			
			// doc ����
			doc.open();
			
			//��Ʈ ���� 
			//IDENTITY_H : ���ڸ� ���η� ����, NOT_EMBEDDED : ������ ��Ʈ�� ������� �ʰ� �ܺ��� ��Ʈ�� ����Ѵ�
			BaseFont bFont = BaseFont.createFont("MALGUN.TTF",BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			
			//���� ��Ʈ ����� 12������
			Font fontHeader = new Font(bFont, 12);
			
			//���� ��Ʈ ����� 12������
			Font fontRow = new Font(bFont, 10);
			
			//pdf ���̺� ���� ������� list�� ������ ��ŭ
			PdfPTable table = new PdfPTable(headers.length);
			
			for(String header: headers) {
				
				//pdf �� �����
				PdfPCell cell = new PdfPCell();
				
				//�����
				cell.setGrayFill(0.9f);
				
				//�� ���ֱ�
				cell.setPhrase(new Phrase(header.toUpperCase(), fontHeader));
				
				table.addCell(cell);
			}
			table.completeRow();
			
			for(ExcelDTO dto : data) {
				//�� ���ֱ�
				Phrase phrase = new Phrase(dto.getTitle(), fontRow);
				//�� �߰�
				table.addCell(new PdfPCell(phrase));
				
				//�� ���ֱ�
				phrase = new Phrase(dto.getAuthor(), fontRow);
				//�� �߰�
				table.addCell(new PdfPCell(phrase));
				
				//�� ���ֱ�
				phrase = new Phrase(dto.getCompany(), fontRow);
				//�� �߰�
				table.addCell(new PdfPCell(phrase));
				
				//�̹��� �ֱ�
				Image image = Image.getInstance(dto.getImgurl());
				table.addCell(image);
				
				table.completeRow();
			}
			doc.addTitle("PDF Table Demo");
			doc.add(table);
			
			System.out.println("BookList ���� �Ϸ�");
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			doc.close();
		}
		
		
	}
}














