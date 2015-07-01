package Server.PDFCreator;

import Structure.Product;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

public class ProductsCreator {

	private static Document docProducts = null;

	public static void CreatePDF(List<Product> products) {

		docProducts = new Document(PageSize.A4, 70, 42, 56, 56);
		//A4 595 pts
		//margens: esq 2,5  dir 1,5 cima 2  baixo 2

		try {
			PdfWriter.getInstance(docProducts, new FileOutputStream("products.pdf"));

			docProducts.open();

			docProducts.add(CreateTable(products));

		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		docProducts.close();
	}

	//Escreve a tabela de produtos
	private static PdfPTable CreateTable(List<Product> products) throws DocumentException {

		docProducts.add(CreateHeaderTable());

		PdfPTable table = new PdfPTable(4); //4 colunas
		table.setTotalWidth(new float[]{ 193, 80, 80, 130 });
		table.setLockedWidth(true);

		table.setSpacingBefore(0);
		table.setSpacingAfter(5);

		//Para cada produto:
		for (Product p : products) {

			//Escreve nome do produto
			table.addCell(BasicCreator.newCell(p.getName(), Element.ALIGN_JUSTIFIED));

			//Escreve preço do protudo
			table.addCell(BasicCreator.newCell(p.getPriceAsStr(), Element.ALIGN_RIGHT));

			//Escreve quantidade do produto
			table.addCell(BasicCreator.newCell(p.getAmountRealAsStr()));

			//Escreve fornecedor do produto
			table.addCell(BasicCreator.newCell(p.getProvider(), Element.ALIGN_JUSTIFIED));
		}

		return table;
	}

	//Escreve o cabeçalho da tabela de produtos
	private static PdfPTable CreateHeaderTable() throws DocumentException {

		PdfPTable table = new PdfPTable(4); //4 colunas
		table.setTotalWidth(new float[]{ 193, 80, 80, 130 });
		table.setLockedWidth(true);

		table.setSpacingBefore(5);
		table.setSpacingAfter(0);

		table.addCell(BasicCreator.newCell("Produto"));
		table.addCell(BasicCreator.newCell("Preço"));
		table.addCell(BasicCreator.newCell("Quantidade"));
		table.addCell(BasicCreator.newCell("Fornecedor"));

		return table;
	}
}
