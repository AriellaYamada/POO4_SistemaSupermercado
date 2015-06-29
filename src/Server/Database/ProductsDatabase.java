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

	private ProductsDatabase() {
		HEADER = "name,price,expiration,provider,quantity";
		OpenFile("products.csv");
	}

	public void WriteFile() {
		WriteFile(false,HEADER);

		for (Product p : Products.ListAll()) {
			WriteFile(p.getName(),
					Float.valueOf(p.getPrice()).toString(),
					p.getExpiration(),
					p.getProvider(),
					Integer.valueOf(p.getAmount_real()).toString()
			);
		}
	}
}
