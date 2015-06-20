package Database;

import java.io.*;
import static java.lang.System.out;

abstract public class Database {

	protected File file = null;
	protected BufferedReader br = null;
	protected FileWriter fw = null;
	protected String path = null;

	// Abre o arquivo
	protected void OpenFile(String filename) {
		if (filename != null) { path = filename; }

		// Tenta abrir o arquivo
		file = new File(path);
		if (!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				out.println("Erro ao criar o arquivo.");
				e.printStackTrace();
			}
		}
	}

	// Abre o Buffered Reader
	protected void OpenReader() {
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			out.println("Erro ao criar o Buffered Reader.");
			e.printStackTrace();
		}
	}

	// Abre o File Writer
	protected void OpenWriter(boolean append) {
		try {
			fw = new FileWriter(file, append);
		} catch (IOException e) {
			out.println("Erro ao criar o File Writer.");
			e.printStackTrace();
		}
	}

	// Fecha o arquivo
	protected void CloseFile(){
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}