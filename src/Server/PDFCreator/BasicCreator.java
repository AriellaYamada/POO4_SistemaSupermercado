package Server.PDFCreator;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;

public class BasicCreator {

	private BasicCreator() {}


	protected static PdfPCell newCell(String str) {
		return newCell(str, Element.ALIGN_CENTER, true);
	}

	protected static PdfPCell newCell(String str, int horizontal) {
		return newCell(str, horizontal, true);
	}

	protected static PdfPCell newCell(String str, int horizontal, boolean border ) {
		PdfPCell cell = new PdfPCell(new Phrase(str));
		cell.setMinimumHeight(20);
		cell.setHorizontalAlignment(horizontal);
		cell.setVerticalAlignment(Element.ALIGN_CENTER);

		if( !border ) cell.setBorder(Rectangle.NO_BORDER);

		return cell;
	}
}
