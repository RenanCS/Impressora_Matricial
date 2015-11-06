package aa;

import java.util.Random;

public class Teste {

	public static void main(String[] args) {
		ArvoreBinaria<Integer> aArvore = new ArvoreBinaria<Integer>();
		
	/*############ TESTE DO TRABALHO ###########*/	
		aArvore.inserir(262); 
		aArvore.inserir(80); 
		aArvore.inserir(332);
		aArvore.inserir(3); 
		aArvore.inserir(164);
		aArvore.inserir(297); 
		aArvore.inserir(353);
		aArvore.inserir(73); 
		aArvore.inserir(115); 
		aArvore.inserir(199);
		aArvore.inserir(276); 
		aArvore.inserir(325); 
		aArvore.inserir(346);
		aArvore.inserir(367);
		aArvore.inserir(24); 
		aArvore.inserir(143); 
		aArvore.inserir(192);
		aArvore.inserir(220); 
		aArvore.inserir(290); 
		aArvore.inserir(318);
		aArvore.inserir(10); 
		aArvore.inserir(31); 
		aArvore.inserir(171);
		aArvore.inserir(206); 
		aArvore.inserir(255);
		aArvore.inserir(45); 
		aArvore.inserir(234);
		aArvore.inserir(38); 
		aArvore.inserir(52); 
		aArvore.inserir(241);
		 
/*################## TESTE COM VALORES RANDOMICOS E CASAS DECIMAIS MAIORES ##############*/
/*
		Random rnd = new Random();
		for (int i = 0; i < 100; i++) {
			int numero;
			do {
				numero = rnd.nextInt(1000);
			} while (aArvore.consultar(numero));
			aArvore.inserir(numero);
		}	
		
*/
		aArvore.ImprimeArvore();		
		
		
	}

}
