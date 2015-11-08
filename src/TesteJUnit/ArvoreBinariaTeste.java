package TesteJUnit;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import aa.ArvoreBinaria;

public class ArvoreBinariaTeste {

	ArvoreBinaria<Integer>tree;
	@Before
	public void setUp() throws Exception {
	tree = new ArvoreBinaria<Integer>();
		//Preenchimento de 3 valores 
		tree.inserir(262); 
		tree.inserir(80); 
		tree.inserir(332);
	}

	/*################### 5 TESTES SIMPLES ###################*/
	@Test
	public void testPreencheListaNivel() {
		//Após adicionar 3 valores, terá que retornar um lista de nível informando a quantidade.
		assertEquals(3, tree.preencheListaNivel(tree.raiz).size());
	}

	@Test
	public void testRetornaTotalCasasDecimais() {
		//Retorna a quantidade de casas decimais que terá que deixar para 
		//completar com traços, em relação a direita do nodo da esquerda do pai.
		assertEquals(6,  tree.RetornaTotalCasasDecimais(tree.raiz,"esq",false));
	}

	@Test
	public void TestMontaNivelDireitoNodoAtual() {
		//Retorna a montagem do primeiro nivel  
		assertEquals("|·262··|",  tree.PrintNodo(tree.raiz));
	}
	
	@Test
	public void testCalculaCasaDecimas() {
		//Retorna a montagem do primeiro nivel  
		assertEquals(8,  tree.calculaCasaDecimas(tree.raiz,0));
	}
	
	@Test
	public void testRetornaNivel() {
		//Retorna a montagem do teprimeiro nivel  
		assertEquals(1,  tree.retornaNivel(tree.raiz.esquerdo.chave));
	}
	
	/*################### 5 TESTES DIFICEIS ###################*/
	@Test
	public void TestPrintTelaDificil01() {
			tree = new ArvoreBinaria<Integer>();
			//Preenchimento de 3 valores 
			tree.inserir(262); 
			tree.inserir(80); 
			tree.inserir(332);
			
			tree.inserir(3); 
			tree.inserir(164);
			
			assertEquals("|80··|",  tree.PrintNodo(tree.raiz.esquerdo));
	}

	@Test
	public void TestPrintTelaDificil02() {
			tree = new ArvoreBinaria<Integer>();

			tree.inserir(262); 
			tree.inserir(80); 
			tree.inserir(332);
			tree.inserir(3); 
			tree.inserir(164);
			tree.inserir(297); 
			tree.inserir(353);
			tree.inserir(73); 
			tree.inserir(115); 
			tree.inserir(199);
			
			assertEquals("|··332··|",  tree.PrintNodo(tree.raiz.direito));
	}

	@Test
	public void TestPrintTelaDificil03() {
			tree = new ArvoreBinaria<Integer>();
		
			tree.inserir(262); 
			tree.inserir(80); 
			tree.inserir(332);
			tree.inserir(3); 
			tree.inserir(164);
			tree.inserir(297); 
			tree.inserir(353);
			tree.inserir(73); 
			tree.inserir(115); 
			tree.inserir(199);
			tree.inserir(276); 
			tree.inserir(325); 
			tree.inserir(346);
			tree.inserir(367);
			tree.inserir(24); 
			tree.inserir(143); 
			tree.inserir(192);
			tree.inserir(220); 
			tree.inserir(290); 
			
			assertEquals("     |···················262··············|",  tree.PrintNodo(tree.raiz));
		}

	@Test
	public void testContador() {
			tree = new ArvoreBinaria<Integer>();
			Random rnd = new Random();
			
			for (int i = 0; i < 3000; i++) {
				int numero;
				do {
					numero = rnd.nextInt(1000);
				} while (tree.consultar(numero));
				tree.inserir(numero);
			}	
			
			tree.ImprimeArvore();
			int QuantNodos = tree.preencheListaNivel(tree.raiz).size();
			int QuantInstru = tree.Contador;
			String sDados = String.valueOf(QuantNodos) + ";" + String.valueOf(QuantInstru);
			tree.Gravar(sDados);
			System.out.println("NODOS = " + QuantNodos + " Complexidade =" + QuantInstru);
		}


	
	
	
}
