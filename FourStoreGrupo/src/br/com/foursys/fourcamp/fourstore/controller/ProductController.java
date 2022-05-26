package br.com.foursys.fourcamp.fourstore.controller;

import java.util.List;

import br.com.foursys.fourcamp.fourstore.model.Product;
import br.com.foursys.fourcamp.fourstore.model.Sale;
import br.com.foursys.fourcamp.fourstore.service.ProductService;

public class ProductController {
			ProductService service = new ProductService();
			
			public  String registerProduct(String sku, Double originPrice, Double salePrice, Integer qtt) {
			String return1 = "";
			if(sku.length() == 10) {
				Product product = new Product(sku, originPrice, salePrice, qtt);
			 if(product.getOriginPrice() == null || product.getSalePrice() == null || product.getSku() == null || product.getColor() == null || product.getCategory() == null || product.getSize() == null ) {
				 return1 = "N�o foi poss�vel cadastrar o produto";
			} else {
				service.registerProduct(product);
				 return1 += "Produto cadastrado com sucesso";
				 return1 += "\nSKU: " + product.getSku();
				 return1 += "\nQTT: " + product.getQtt();
				 return1 += "\nCOR: " + product.getColor();
				 return1 += "\nDEPARTAMENTO: " + product.getDepartment();
				 return1 += "\nTAMANHO: " + product.getSize();
				 return1 += "\nTIPO: " + product.getType();
				 return1 += "\nCATEGORIA: " + product.getCategory();




			}
			} else {
				return1 = "N�o foi poss�vel cadastrar o produto(SKU INVALIDO)";
			}
			return return1 ;
			
		}

			public String listAllProducts() {
				String return1 = "";
				List<Object> lista = service.listAllProducts();
				for (int x = 0; x < lista.size(); x++) {
					Product product = (Product) lista.get(x);
					return1 = return1 + ""
					 + "Produto cadastrado com sucesso"
					  + "\nSKU: " + product.getSku()
					  + "\nQTT: " + product.getQtt()
					  + "\nCOR: " + product.getColor()
					  + "\nDEPARTAMENTO: " + product.getDepartment()
					  +	"\nTAMANHO: " + product.getSize()
					  + "\nTIPO: " + product.getType()
					  + "\nCATEGORIA: " + product.getCategory()
					  +"\nPRE�O: " +product.getSalePrice();

				}
				return return1;
			}

			public Object searchBySku(String sku) {
				Object return1 = "Produto n�o encontrado";
				Product product = new Product(sku);
				product = service.searchBySku(product);
				if (product == null) {
					return return1;

				}
				return return1 = "Produto encontrado" 
								+ "\nDESCRI��O: " +product.getType() +" " +product.getDepartment() +" " +product.getColor() + " - "+product.getCategory()+"/"+product.getSize()
								+ "\nQUANTIDADE: "+ product.getQtt() 
								+ "\nPRE�O ORIGINAL: " + product.getOriginPrice() 
								+ "\nPRE�O DE VENDA: "	+ product.getSalePrice();
								

			}
			public Object addProduct(String sku, Integer qtt) {
				Object return1 = "Estoque atualizado";
				Product product = new Product(sku);
				product = service.searchBySku(product);
				if (product == null) {
					return "Produto n�o existe";

				}
				else {
					int qttAtual = product.getQtt();
					product.setQtt(qttAtual + qtt);
				}
				return return1;
	
			}
			
			public Object modifyPrice(String sku, Double salePrice) {
				Object return1 = "Novo pre�o cadastrado com sucesso";
				Product product = new Product(sku);
				product = service.searchBySku(product);
				if (product == null) {
					return "Produto n�o encontrado";
				}else {
					product.setSalePrice(salePrice);
				}	
				return return1;
					
				
			}
			public Object saleProduct(String sku, Integer qtt, String cpf, String paymentType) {
				String return1 = "Produto n�o encontrado";
				Product product = new Product(sku, qtt);
				product = service.searchBySku(product);
				if(product != null && qtt<=product.getQtt()) {
					service.registerSale(product, qtt, cpf, paymentType);
					return1 = "Venda realizada com sucesso!";
				}	
				return return1;
			}
			
			public String report() {
				String return1 = "";
				List<Object> list = service.listAllSales();
				for (int x = 0; x < list.size(); x++) {
					Sale sale = (Sale) list.get(x);
					return1 = return1 + ""
					 + "Produto cadastrado com sucesso"
					  + "\nSKU: " + sale.getProduct().getSku()
					  +"\nProduto: "+sale.getProduct().getType() +" " +sale.getProduct().getDepartment() +" " +sale.getProduct().getColor() + " - "+sale.getProduct().getCategory()+"/"+sale.getProduct().getSize()
					  + "\nQuantidade: " + sale.getProduct().getQtt()
					  +"\nPRE�O: " +sale.getProduct().getSalePrice()
					  +"\nMet�do Pagamento: "+sale.getPaymentType();

				}
				return return1;
			}
}
