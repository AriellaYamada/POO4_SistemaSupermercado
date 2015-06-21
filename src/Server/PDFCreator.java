package Server;

import java.io.FileOutputStream;
import java.io.OutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.IOException;
import com.itextpdf.text.DocumentException;

import static java.lang.System.err;

public class PDFCreator {

	public void CreatePDF( String date, String msg ) {
		Document doc = null;
		OutputStream os = null;

		try {

			doc = new Document(PageSize.A4, 70, 42, 56, 56);
			//margens: esq 2,5  dir 1,5 cima 2  baixo 2

			os = new FileOutputStream(date + ".pdf");

			PdfWriter.getInstance(doc, os);

			doc.open();

			Paragraph p = new Paragraph( msg );
			doc.add(p);

		} catch(DocumentException de) {
			err.println(de.getMessage());
		}
		catch(IOException ioe) {
			err.println(ioe.getMessage());
		}
		doc.close();
	}
}

