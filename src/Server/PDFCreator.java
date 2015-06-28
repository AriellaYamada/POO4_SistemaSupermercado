package Server;

import java.io.FileOutputStream;

import Structure.Sale;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;


import java.io.IOException;
import java.util.List;

import static java.lang.System.err;

public class PDFCreator {

	public void CreatePDF( String filename, List<Sale> sales ) {
		Document doc = null;

		try {

			doc = new Document(PageSize.A4, 70, 42, 56, 56);
			//A4 595 pts
			//margens: esq 2,5  dir 1,5 cima 2  baixo 2

			PdfWriter.getInstance(doc, new FileOutputStream(filename + ".pdf"));

			doc.open();

			try {
				Paragraph para = new Paragraph("alguma coisa 1");
				doc.add(para);

				doc.add(CreateSalesTable(sales));
			}catch (DocumentException de) {
				err.println(de.getMessage());
			}
			try {
				Paragraph para = new Paragraph("alguma coisa 2");
				doc.add(para);
				doc.add(CreateSalesTable(sales));
			}catch (DocumentException de) {
				err.println(de.getMessage());
			}


		} catch(DocumentException de) {
			err.println(de.getMessage());
		}
		catch(IOException ioe) {
			err.println(ioe.getMessage());
		}
		doc.close();
	}

	private PdfPTable CreateSalesTable( List<Sale> sales ) throws DocumentException{

		PdfPTable table = new PdfPTable(4); //4 colunas
		table.setTotalWidth(new float[]{ 183, 100, 100, 100 });
		table.setLockedWidth(true);
		PdfPCell cell;

		table.setSpacingBefore(10);
		table.setSpacingAfter(20);


		for (Sale s : sales ) {

			cell = new PdfPCell(new Phrase(s.getUser().getName()));
			cell.setMinimumHeight(20);
			cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("produto"));
			cell.setMinimumHeight(20);
			cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("quantia"));
			cell.setMinimumHeight(20);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(s.getDate()));
			cell.setMinimumHeight(20);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
		}

		return table;
	}
}
