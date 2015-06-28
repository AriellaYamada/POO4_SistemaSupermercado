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

	public ProductsDatabase() { OpenFile("products.csv"); }

	public void WriteFile() {
		WriteFile("name","price","expiration","provider","quantity");

		for (Product p : Products.getInstance().ListAll()) {
			WriteFile(p.getName(),
					Float.valueOf(p.getPrice()).toString(),
					p.getExpiration(),
					p.getProvider(),
					Integer.valueOf(p.getAmount_real()).toString()
			);
		}
	}
}
