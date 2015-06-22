package Server;

import java.io.FileOutputStream;
import java.io.OutputStream;port com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFCreator {

		Docunt doc = null;
		OutputStream os = null;

		try {
			//cria o documento tamanho A4, margens de 2,54cm
			doc = new Document(PageSize.A4, 72, 72, 72, 72);

			//cria a stream de saída
			os = new FileOutputStream("out.pdf");

			//associa a stream de saída ao
			PdfWriter.getInstance(doc, os);

			//abre o documento
			doc.open();

			//adiciona o texto ao PDF
			Paragraph p = new Paragraph("Meu primeiro arquivo PDF!");
			doc.add(p);

		} finally {
			if (doc != null) {
				//fechamento do documento
				doc.close();
			}
			if (os != null) {
				//fechamento da stream de saída
				os.close();
			}
		}
	}



}

