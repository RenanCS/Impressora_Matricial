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
	
	}

	/*################### 5 TESTES SIMPLES ###################*/
	@Test
	public void testPrintFacil01() {
		tree = new ArvoreBinaria<Integer>();
		//Preenchimento de 3 valores 
		tree.inserir(19); 
		tree.inserir(6); 
		tree.inserir(22);
		tree.inserir(0);
		tree.inserir(11);
		tree.inserir(50);
		
		tree.ImprimeArvore();
		assertEquals(" |··19·||6·|  22·|0 11    50",tree.sPrintFinal);
	}

	@Test
	public void testPrintFacil02() {
		tree = new ArvoreBinaria<Integer>();
		tree.inserir(27); 
		tree.inserir(16); 
		tree.inserir(29);
		tree.inserir(1);
		tree.inserir(18);
		tree.inserir(28);
		tree.inserir(31);
		tree.ImprimeArvore();
		assertEquals(" |···27···||16·|  |·29·|1  18  28  31",tree.sPrintFinal);
	}

	@Test
	public void testPrintFacil03() {
		tree = new ArvoreBinaria<Integer>();
		//Preenchimento de 3 valores 
		tree.inserir(100); 
		tree.inserir(10); 
		tree.inserir(200);
		tree.inserir(9);
		tree.inserir(15);
		tree.inserir(150);
		tree.inserir(201);
		tree.ImprimeArvore();
		assertEquals(" |···100·····||10·|   |··200··|9  15   150   201",tree.sPrintFinal);
	}
	
	@Test
	public void testPrintFacil04() {
			tree = new ArvoreBinaria<Integer>();
			//Preenchimento de 3 valores 
			tree.inserir(30); 
			tree.inserir(6); 
			tree.inserir(31);
			tree.ImprimeArvore();
			assertEquals("|30·|6  31",tree.sPrintFinal);	
		}
		
	

	@Test
	public void testPrintFacil05() {
			tree = new ArvoreBinaria<Integer>();
			//Preenchimento de 3 valores 
			tree.inserir(13); 
			tree.inserir(9); 
			tree.inserir(18);
			tree.ImprimeArvore();
			assertEquals("|13·|9  18",tree.sPrintFinal);	
		}
	
	/*################### 5 TESTES DIFICEIS ###################*/
	@Test
	public void TestPrintTelaDificil01() {
			tree = new ArvoreBinaria<Integer>();
			//Preenchimento de 3 valores 
			tree.inserir(20); 
			tree.inserir(18); 
			tree.inserir(16);
			tree.inserir(14);
			tree.ImprimeArvore();
			assertEquals("    |·20  |·18|·1614", tree.sPrintFinal);
	}

	@Test
	public void TestPrintTelaDificil02() {
			tree = new ArvoreBinaria<Integer>();

			tree.inserir(30); 
			tree.inserir(33); 
			tree.inserir(35);
			tree.inserir(37);
			tree.ImprimeArvore();
			assertEquals("30·|  33·|    35·|      37", tree.sPrintFinal);
		}

	@Test
	public void TestPrintTelaDificil03() {
			tree = new ArvoreBinaria<Integer>();
		
			tree.inserir(7); 
			tree.inserir(10); 
			tree.inserir(8);
			tree.inserir(9); 
			tree.ImprimeArvore();
			assertEquals("7···| |·10 8|  9", tree.sPrintFinal);
		}


	@Test
	public void TestPrintTelaDificil04() {
			tree = new ArvoreBinaria<Integer>();
		
			tree.inserir(20); 
			tree.inserir(15); 
			tree.inserir(18);
			tree.inserir(17); 
			tree.ImprimeArvore();
			assertEquals("|·····2015···|  |·18  17", tree.sPrintFinal);
		}

	@Test
	public void TestPrintTelaDificil05() {
			tree = new ArvoreBinaria<Integer>();
		
			tree.inserir(50); 
			tree.inserir(30); 
			tree.inserir(35);
			tree.inserir(32); 
			tree.ImprimeArvore();
			assertEquals("|·····5030···|  |·35  32", tree.sPrintFinal);
		}

	
	
	@Test
	public void testContador() {
			tree = new ArvoreBinaria<Integer>();
			Random rnd = new Random();
			
			for (int i = 0; i < 1100; i++) {
				int numero;
				do {
					numero = rnd.nextInt(1000);
				} while (tree.consultar(numero));
				tree.inserir(numero);
			}	
			
			tree.ImprimeArvore();
			int QuantNodos = tree.preencheListaNivel(tree.raiz).size();
			long QuantInstru = tree.Contador;
			String sDados = String.valueOf(QuantNodos) + ";" + String.valueOf(QuantInstru);
			tree.Gravar(sDados);
			System.out.println("NODOS = " + QuantNodos + " Complexidade =" + QuantInstru);
		}

	
	
	
}
