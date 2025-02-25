package br.com.foursys.fourcamp.fourstore.communication;

import java.util.EnumSet;
import java.util.Scanner;

import br.com.fourcamp.fourstore.utils.Utils;
import br.com.foursys.fourcamp.fourstore.controller.ProductController;
import br.com.foursys.fourcamp.fourstore.enums.PaymentsEnum;
import br.com.foursys.fourcamp.fourstore.model.Product;
import br.com.foursys.fourcamp.fourstore.model.Sale;

public class MainMenu {
	 static Scanner sc = new Scanner(System.in);
	 
	public void mainmenu() {
		
		
		while(true) {
			
			System.out.println(
				                  "\n ___________________________________"
								+ "\n|     Bem Vindo ao FourStore        |" 
								+ "\n|___________________________________|"
								+ "\n|1 - Cadastrar produto              |" 
								+ "\n|2 - Atualizar produto              |" 
								+ "\n|3 - Venda                          |"
								+ "\n|4 - Listar produtos                |" 
								+ "\n|5 - Pesquisar produto por sku      |" 
								+ "\n|6 - Relat�rio de venda             |" 
								+ "\n|--------------------------------   |" 
								+ "\n| 0 - S A I R                       |"
								+ "\n|___________________________________|" 
								+ "\n" 
								+ "\nInforme a opera��o desejada: ");
						
			int option = sc.nextInt();
			switch(option) {
			case 0:
				System.out.println("Volte sempre!");
				System.exit(0);
				break;
			case 1: 
				registerProduct();
				break;
			case 2:
				Integer option2;
				System.out.println("    Digite a op��o desejada:"
								+"\n1 - Atualizar quantidade no estoque"
								+"\n2 - Atualizar pre�o de venda do produto");
				option2 = sc.nextInt();
				if(option2 == 1) {
					addProduct();
				}else if(option2 ==2) {
					modifyPrice();
				}else {
					System.out.print("Op��o Inv�lida");
				}
				
			break;
			case 3:
				saleProduct();
				break;
			case 4:
				listAllProducts();
				break;
			case 5:
				searchBySku();
				break;
			case 6:
				report();
			}
			
		}
	}
	
	

	private void report() {
		System.out.println("Relat�rio de Vendas");	
		
		ProductController productC = new ProductController();
		String return1 = productC.report();
		System.out.println(return1);
		
		
	}



	private void modifyPrice() {
		
		Scanner sc3 = new Scanner(System.in);
		ProductController productCont = new ProductController();
		System.out.println("Qual o sku do produto");
		String sku = sc3.next();
		System.out.println("Informe o novo pre�o de venda: ");
		Double salePrice = sc3.nextDouble();
		Object return1 = productCont.modifyPrice(sku, salePrice);
		System.out.println(return1);
		
		
	}


	private void addProduct() {
		Scanner sc3 = new Scanner(System.in);
		ProductController productCont = new ProductController();
		System.out.println("Qual o sku do produto");
		String sku = sc3.next();
		System.out.println("Quantidade de produtos adicionada ao estoque: ");
		Integer qtt = sc3.nextInt();
		Object return1 = productCont.addProduct(sku, qtt);
		System.out.println(return1);
	
	}

	private void saleProduct() {
		String cpf2 = null;
		Scanner sc2 = new Scanner(System.in);
		Double totalPrice = 0.0;
		ProductController productCont = new ProductController();

		System.out.println("Digite o SKU do produto: ");
		String sku = sc2.nextLine();
		System.out.println("Digite a quantidade do item: ");
		Integer qtt = sc2.nextInt();
		Product product = productCont.getProduct(sku);

		
		totalPrice = product.getSalePrice() * qtt;

		System.out.println("O valor da compra �: " + totalPrice);

		System.out.println("Qual a forma de pagamento?");
		for (PaymentsEnum p : EnumSet.allOf(PaymentsEnum.class)) {
			System.out.println(p.getKey());
		}
		String paymentType = sc.next();

		switch (paymentType) {
		case "1":
			getDebit();
			break;
		case "2":
			getCredit();
			break;
		case "3":
			getPix();
			break;
		case "4":
			getMoney();
			break;
		default:
			System.out.println("Escolheu outro");

		}

		System.out.println("Adicionar CPF na nota?");
		System.out.println("1 - Sim" + " " + "2 - N�o");
		int cpf = sc.nextInt();
		if (cpf == 1) {
			String return2 = " ";
			System.out.println("Digite seu CPF: ");
			Utils util = new Utils();
			cpf2 = sc.next();
			return2 = util.validateCPF(cpf2);
			if (return2 == "Por favor digite um CPF de acordo com o padr�o solicitado! ") {
				System.out.println("Por favor digite um CPF de acordo com o padr�o solicitado! ");
			} else {
				Object return1 = productCont.saleProduct(sku, qtt, cpf2, paymentType);
				System.out.println(return2);
				System.out.println(return1);
			}
		} else {
			Object return1 = productCont.saleProduct(sku, qtt, cpf2, paymentType);
			System.out.println(return1);
		}

	}
	private static void searchBySku() {
		Scanner sc2 = new Scanner(System.in);
		 
		ProductController productCont = new ProductController();
		System.out.println("Qual o sku do produto");
		String sku = sc2.nextLine();

		Object return1 = productCont.searchBySku(sku);
		System.out.println(return1);

	}

	private void listAllProducts() {
		ProductController productC = new ProductController();
		String return1 = productC.listAllProducts();
		System.out.println(return1);
	}


	private void registerProduct() {
		
		
		System.out.println("Insira O SKU do produto: ");
		String sku = sc.next();
		System.out.println("Insira A QUANTIDADE do produto: ");
		Integer qtt = sc.nextInt();
		System.out.println("Insira O PRE�O ORIGINAL do produto: ");
		Double originPrice = sc.nextDouble();
		System.out.println("Insira O PRE�O DE VENDA do produto: ");
		Double salePrice = sc.nextDouble();
		ProductController productC = new ProductController();
		String return1 = productC.registerProduct(sku, originPrice, salePrice, qtt);
		System.out.println(return1);
	}
	
	private static void getPix() {
		Scanner sc = new Scanner(System.in);
		Sale saleM = new Sale();
		System.out.println("Digite a chave pix 1- Celular 2- Cpf");
		Integer key = sc.nextInt();
		if(key == 1) {
			 sc.nextLine();
			System.out.println("Digite o numero de telefone");
			String telephone = sc.nextLine();
			System.out.println("Pix realizado com sucesso");
			
		}else {
			sc.nextLine();
			System.out.println("Digite o cpf");
			String cpf = sc.nextLine();
			System.out.println("Pix realizado com sucesso");
		}
	}
	
	private static void getDebit() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Digite o n� do cart�o");
		Integer cardNumberDebit = sc.nextInt();
		System.out.println("Insira o cart�o na maquineta 1- Senha 2- Aproxima��o ");
		Integer option = sc.nextInt();
		if (option == 1) {
			System.out.println("Digite a senha");
			Integer password = sc.nextInt();
			System.out.println("Compra concluida com sucesso");
		} else {
			System.out.println("Aproxime o cart�o");
			System.out.println("Compra concluida com sucesso");
		}
	}

	private static void getCredit() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Digite o n� do cart�o");
		Integer cardNumberCredit = sc.nextInt();
		System.out.println("Insira o cart�o na maquineta 1- Senha 2- Aproxima��o ");
		Integer option = sc.nextInt();
		if (option == 1) {
			System.out.println("Deseja parcelar a compra? 1- SIM  2-N�O");
			Integer splitpay = sc.nextInt();
			if (splitpay == 1) {
				System.out.println("Parcelar em quantas vezes?");
				Integer time = sc.nextInt();
				System.out.println("Digite a senha");
				Integer password = sc.nextInt();
				System.out.println("Compra concluida com sucesso");
			}
		} else {
			System.out.println("Aproxime o cart�o");
			System.out.println("Compra concluida com sucesso");
		}
	}
	private static void getMoney() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Ir� precisar de troco? 1- SIM  2-N�O ");
		Integer moneychange = sc.nextInt();
		if(moneychange == 1) {
			System.out.println("Quanto de troco?");
			moneychange = sc.nextInt();	
			System.out.println("Confira o troco!");
			
		}else {
			System.out.println("Obrigado!");
		}
	
		
	}
}
