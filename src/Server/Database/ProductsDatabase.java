package Server.Database;

import Structure.Product;

public class ProductsDatabase extends Database {

	private static ProductsDatabase productsDB;

	//Singleton
	public static ProductsDatabase getInstance(){
		if(productsDB == null)
			productsDB = new ProductsDatabase();
		return productsDB;
	}

	//Abertura do arquivo .csv caso ainda nao tenha sido criado
	private ProductsDatabase() {
		HEADER = "name,price,expiration,provider,quantity";
		OpenFile("products.csv");
	}

	//Escreve no arquivo todos os registros de produtos
	public void WriteFile() {
		WriteFile(false,HEADER);

		for (Product p : Products.ListAll()) {
			WriteFile(p.getName(),
					Double.toString((p.getPrice())),
					p.getExpiration(),
					p.getProvider(),
					Integer.toString((p.getAmount_real()))
			);
		}
	}
}
