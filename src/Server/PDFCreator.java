package Server;

import java.io.FileOutputStream;

import Structure.Sale;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;


import java.io.IOException;
import java.util.List;

import com.itextpdf.text.DocumentException;

import static java.lang.System.err;

public class PDFCreator {

	public void CreatePDF( String filename, String msg, List<Sale> sales ) {
		Document doc = null;

		try {

			doc = new Document(PageSize.A4, 70, 42, 56, 56);
			//margens: esq 2,5  dir 1,5 cima 2  baixo 2

			PdfWriter.getInstance(doc, new FileOutputStream(filename + ".pdf"));

			doc.open();

			Paragraph p = new Paragraph( msg );
			doc.add(CreateSalesTable(sales));

		} catch(DocumentException de) {
			err.println(de.getMessage());
		}
		catch(IOException ioe) {
			err.println(ioe.getMessage());
		}
		doc.close();
	}

	private PdfPTable CreateSalesTable( List<Sale> sales ) {

		PdfPTable table = new PdfPTable(4); //4 colunas

		return table;
	}
	/*public static PdfPTable createFirstTable() {
		// a table with three columns
		PdfPTable table = new PdfPTable(3);
		// the cell object
		PdfPCell cell;
		// we add a cell with colspan 3
		cell = new PdfPCell(new Phrase("Cell with colspan 3"));
		cell.setColspan(3);
		table.addCell(cell);
		// now we add a cell with rowspan 2
		cell = new PdfPCell(new Phrase("Cell with rowspan 2"));
		cell.setRowspan(2);
		table.addCell(cell);
		// we add the four remaining cells with addCell()
		table.addCell("row 1; cell 1");
		table.addCell("row 1; cell 2");
		table.addCell("row 2; cell 1");
		table.addCell("row 2; cell 2");
		return table;
	}*/
}

