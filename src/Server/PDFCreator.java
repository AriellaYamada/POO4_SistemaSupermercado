package Server;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import Structure.CartItem;
import Structure.Sale;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;

import java.util.List;


public class PDFCreator {

	Document doc;

	public PDFCreator() {
		this.doc = null;
	}

	public void CreatePDF( String filename, List<Sale> sales ) {

		doc = new Document(PageSize.A4, 70, 42, 56, 56);
		//A4 595 pts
		//margens: esq 2,5  dir 1,5 cima 2  baixo 2

		try {
			PdfWriter.getInstance(doc, new FileOutputStream(filename + ".pdf"));

			doc.open();

			CreateSalesTable(sales);

		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		doc.close();
	}

	private void CreateSalesTable(List <Sale> sales) throws DocumentException {

		PdfPTable table = new PdfPTable(2);
		table.setSpacingBefore(10);
		table.setSpacingAfter(20);

		PdfPCell cell;

		for (Sale s : sales ) {

			cell = new PdfPCell(new Phrase(s.getDate()));
			cell.setMinimumHeight(20);
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(s.getUser().getName()));
			cell.setMinimumHeight(20);
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			doc.add(table);

			try {
				doc.add(CreateProductsTable(s.getProducts()));
			} catch (DocumentException e) {
				e.printStackTrace();
			}

			Paragraph p = new Paragraph("Total Price: " + s.getTotalPriceAsStr());
			p.setAlignment(Element.ALIGN_RIGHT);
			doc.add(p);
		}

	}
	private PdfPTable CreateProductsTable( List<CartItem> products ) throws DocumentException {

		doc.add(CreateHeaderProductsTable());

		PdfPTable table = new PdfPTable(4); //4 colunas
		table.setTotalWidth(new float[]{ 183, 100, 100, 100 });
		table.setLockedWidth(true);
		PdfPCell cell;

		table.setSpacingBefore(0);
		table.setSpacingAfter(20);

		for (CartItem ci : products ) {


			cell = new PdfPCell(new Phrase(ci.getProduct().getName()));
			cell.setMinimumHeight(20);
			cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(ci.getReservedQtdAsStr()));
			cell.setMinimumHeight(20);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(ci.getPriceAsStr()));
			cell.setMinimumHeight(20);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(ci.getTotalPriceAsStr()));
			cell.setMinimumHeight(20);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

		}

		return table;
	}

	private PdfPTable CreateHeaderProductsTable() throws DocumentException {

		PdfPTable table = new PdfPTable(4); //4 colunas
		table.setTotalWidth(new float[]{ 183, 100, 100, 100 });
		table.setLockedWidth(true);
		PdfPCell cell;

		table.setSpacingBefore(10);
		table.setSpacingAfter(0);

		cell = new PdfPCell(new Phrase("Product"));
		cell.setMinimumHeight(20);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Quantity"));
		cell.setMinimumHeight(20);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Unity Price"));
		cell.setMinimumHeight(20);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Total Price"));
		cell.setMinimumHeight(20);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setVerticalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		return table;
	}
}
