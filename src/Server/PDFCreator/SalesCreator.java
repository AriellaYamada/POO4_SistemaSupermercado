package Server.PDFCreator;

import Structure.CartItem;
import Structure.Sale;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

public class SalesCreator {

	private static Document docSales = null;

	public static void CreatePDFSales(String filename, List<Sale> sales) {

		docSales = new Document(PageSize.A4, 70, 42, 56, 56);
		//A4 595 pts
		//margens: esq 2,5  dir 1,5 cima 2  baixo 2

		try {
			PdfWriter.getInstance(docSales, new FileOutputStream(filename + ".pdf"));

			docSales.open();

			CreateSalesTable(sales);

		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		docSales.close();
	}

	private static void CreateSalesTable(List <Sale> sales) throws DocumentException {

		for (Sale s : sales ) {

			//Escreve a data e o usuário
			PdfPTable table = new PdfPTable(2);
			table.setTotalWidth(new float[]{ 283, 200 });
			table.setLockedWidth(true);
			table.setSpacingBefore(30);
			table.setSpacingAfter(0);

			//Escreve a data
			table.addCell(BasicCreator.newCell(s.getDate(), Element.ALIGN_LEFT, false));

			//Escreve o usuário
			table.addCell(BasicCreator.newCell(s.getUser().getName(), Element.ALIGN_RIGHT, false));

			docSales.add(table);

			try {
				docSales.add(CreateSalesProductsTable(s.getProducts()));
			} catch (DocumentException e) {
				e.printStackTrace();
			}

			//Escreve o preço total da compra
			Paragraph p = new Paragraph("Preço Total: R$ " + s.getTotalPriceAsStr());
			p.setAlignment(Element.ALIGN_RIGHT);
			docSales.add(p);
		}
	}

	//Escreve a tabela de produtos
	private static PdfPTable CreateSalesProductsTable(List<CartItem> products) throws DocumentException {

		docSales.add(CreateHeaderSalesProductsTable());

		PdfPTable table = new PdfPTable(4); //4 colunas
		table.setTotalWidth(new float[]{ 183, 100, 100, 100 });
		table.setLockedWidth(true);

		table.setSpacingBefore(0);
		table.setSpacingAfter(5);

		//Para cada produto:
		for (CartItem ci : products ) {

			//Escreve nome do produto
			table.addCell(BasicCreator.newCell(ci.getProduct().getName(), Element.ALIGN_JUSTIFIED));

			//Escreve quantidade do produto
			table.addCell(BasicCreator.newCell(ci.getReservedQtdAsStr()));

			//Escreve preço unitário do produto
			table.addCell(BasicCreator.newCell(ci.getPriceAsStr(), Element.ALIGN_RIGHT));

			//Escreve preço total do produto
			table.addCell(BasicCreator.newCell(ci.getTotalPriceAsStr(), Element.ALIGN_RIGHT));
		}

		return table;
	}

	//Escreve o cabeçalho da tabela de produtos
	private static PdfPTable CreateHeaderSalesProductsTable() throws DocumentException {

		PdfPTable table = new PdfPTable(4); //4 colunas
		table.setTotalWidth(new float[]{ 183, 100, 100, 100 });
		table.setLockedWidth(true);

		table.setSpacingBefore(5);
		table.setSpacingAfter(0);

		table.addCell(BasicCreator.newCell("Produto"));
		table.addCell(BasicCreator.newCell("Quantidade"));
		table.addCell(BasicCreator.newCell("Preço Unitário"));
		table.addCell(BasicCreator.newCell("Preço Total"));

		return table;
	}
}
