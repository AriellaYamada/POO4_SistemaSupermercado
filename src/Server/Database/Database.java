package Server.Database;

import Server.cmdProcess;
import Structure.Def;
import Structure.Product;

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

	// Use como:
	// ProductsDatabase.getInstance().ReadFile(Products.getInstance());
	// [database].getInstance().ReadFile([tipo].getInstance());
	public void ReadFile(ListRegister db){
		OpenReader();
		String line;

		try {
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] splited = Def.splitComma(line);
				db.Register(splited);
			}
		} catch (IOException e) {
			System.err.println("Erro na leitura do arquivo.");
			e.printStackTrace();
		}
	}

	// Use como:
	// [database].getInstance().WriteFile("str 1", "str 2", ... "str x");
	// Ele irá escrever "str 1,str 2,...,str x'\n'" no arquivo
	public void WriteFile(String... value) {
		OpenWriter(false);
		final String ENDLINE = "\n";

		try {
			fw.append(value[0]);
			for (int i = 1; i < value.length; i++){
				fw.append(Def.comma);
				fw.append(value[i]);
			}
			fw.append(ENDLINE);
			fw.flush();
			CloseFile();
		} catch (IOException e){
			System.err.println("Erro na escrita do arquivo.");
			e.printStackTrace();
		}
	}
}