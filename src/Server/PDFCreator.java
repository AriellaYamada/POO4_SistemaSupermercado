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

		PdfPCell cell;

		for (Sale s : sales ) {

			//Escreve a data e o usuário
			PdfPTable table = new PdfPTable(2);
			table.setTotalWidth(new float[]{ 283, 200 });
			table.setLockedWidth(true);
			table.setSpacingBefore(30);
			table.setSpacingAfter(0);

			//Escreve a data
			table.addCell(newCell(s.getDate(), Element.ALIGN_LEFT, false));

			//Escreve o usuário
			table.addCell(newCell(s.getUser().getName(), Element.ALIGN_RIGHT, false));

			doc.add(table);

			try {
				doc.add(CreateProductsTable(s.getProducts()));
			} catch (DocumentException e) {
				e.printStackTrace();
			}

			//Escreve o preço total da compra
			Paragraph p = new Paragraph("Preço Total: " + s.getTotalPriceAsStr());
			p.setAlignment(Element.ALIGN_RIGHT);
			doc.add(p);
		}

	}

	//Escreve a tabela de produtos
	private PdfPTable CreateProductsTable( List<CartItem> products ) throws DocumentException {

		doc.add(CreateHeaderProductsTable());

		PdfPTable table = new PdfPTable(4); //4 colunas
		table.setTotalWidth(new float[]{ 183, 100, 100, 100 });
		table.setLockedWidth(true);

		table.setSpacingBefore(0);
		table.setSpacingAfter(5);

		//Para cada produto:
		for (CartItem ci : products ) {

			//Escreve nome do produto
			table.addCell(newCell(ci.getProduct().getName(), Element.ALIGN_JUSTIFIED));

			//Escreve quantidade do produto
			table.addCell(newCell(ci.getReservedQtdAsStr(), Element.ALIGN_CENTER));

			//Escreve preço unitário do produto
			table.addCell(newCell(ci.getPriceAsStr(), Element.ALIGN_RIGHT));

			//Escreve preço total do produto
			table.addCell(newCell(ci.getTotalPriceAsStr(), Element.ALIGN_RIGHT));
		}

		return table;
	}

	//Escreve o cabeçalho da tabela de produtos
	private PdfPTable CreateHeaderProductsTable() throws DocumentException {

		PdfPTable table = new PdfPTable(4); //4 colunas
		table.setTotalWidth(new float[]{ 183, 100, 100, 100 });
		table.setLockedWidth(true);

		table.setSpacingBefore(5);
		table.setSpacingAfter(0);

		table.addCell(newCell("Produto", Element.ALIGN_CENTER));
		table.addCell(newCell("Quantidade", Element.ALIGN_CENTER));
		table.addCell(newCell("Preço Unitário", Element.ALIGN_CENTER));
		table.addCell(newCell("Preço Total", Element.ALIGN_CENTER));

		return table;
	}
	private PdfPCell newCell(String str, int horizontal) {
		return newCell(str, horizontal, true);
	}

	private PdfPCell newCell(String str, int horizontal, boolean border ) {
		PdfPCell cell = new PdfPCell(new Phrase(str));
		cell.setMinimumHeight(20);
		cell.setHorizontalAlignment(horizontal);
		cell.setVerticalAlignment(Element.ALIGN_CENTER);

		if( !border ) cell.setBorder(Rectangle.NO_BORDER);

		return cell;
	}
}
