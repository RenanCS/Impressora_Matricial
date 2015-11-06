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

	@Test
	public void testPreencheListaNivel() {
		//Após adicionar 3 valores, terá que retornar um lista de nível contendo 3 valores.
		assertEquals(3, tree.preencheListaNivel(tree.raiz).size());
	}

	@Test
	public void RetornaTotalCasasDecimais() {
		//Retorna a quantidade de casas decimais que terá que deixar para completar com traços, em relação a direita do nodo da esquerda do pai.
		assertEquals(3, tree.RetornaTotalCasasDecimais(tree.raiz,"esq",false));
	}

	
	@Test
	public void testImprimeArvore() {
		fail("Not yet implemented");
	}

}
