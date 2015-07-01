package Server.PDFCreator;

import Structure.User;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

public class UsersCreator {

	private static Document docUser = null;

	public static void CreatePDF(List<User> users) {

		docUser = new Document(PageSize.A4, 70, 42, 56, 56);
		//A4 595 pts
		//margens: esq 2,5  dir 1,5 cima 2  baixo 2

		try {
			PdfWriter.getInstance(docUser, new FileOutputStream("users.pdf"));

			docUser.open();

			docUser.add(CreateTable(users));

		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		docUser.close();
	}

	//Escreve a tabela de usuários
	private static PdfPTable CreateTable(List<User> users) throws DocumentException {

		docUser.add(CreateTable());

		PdfPTable table = new PdfPTable(4); //4 colunas
		table.setTotalWidth(new float[]{ 183, 100, 80, 120 });
		table.setLockedWidth(true);

		table.setSpacingBefore(0);
		table.setSpacingAfter(5);

		//Para cada usuário:
		for (User u : users) {

			//Escreve nome do usuário
			table.addCell(BasicCreator.newCell(u.getName(), Element.ALIGN_JUSTIFIED));

			//Escreve ID do usuário
			table.addCell(BasicCreator.newCell(u.getId()));

			//Escreve telefone do usuário
			table.addCell(BasicCreator.newCell(u.getTel()));

			//Escreve email do usuário
			table.addCell(BasicCreator.newCell(u.getEmail()));
		}

		return table;
	}

	//Escreve o cabeçalho da tabela de usuários
	private static PdfPTable CreateTable() throws DocumentException {

		PdfPTable table = new PdfPTable(4); //4 colunas
		table.setTotalWidth(new float[]{ 183, 100, 80, 120 });
		table.setLockedWidth(true);

		table.setSpacingBefore(5);
		table.setSpacingAfter(0);

		table.addCell(BasicCreator.newCell("Nome"));
		table.addCell(BasicCreator.newCell("UserID"));
		table.addCell(BasicCreator.newCell("Telefone"));
		table.addCell(BasicCreator.newCell("Email"));

		return table;
	}
}
