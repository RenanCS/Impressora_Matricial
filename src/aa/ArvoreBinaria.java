package aa;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;



public class ArvoreBinaria <T extends Comparable<T>> {

	// TODO: utilizar classe interna na classe ArvoreBinariaDePesquisa
	// TODO: retirar o public e tornar a classe inacessível fora deste pacote
	public class Nodo<T> {

		public T chave;

		
		public int altura;	
		public int dentroEsquerdo;
		public int dentroDireito;
		public int foraEsquerdo;
		public int foraDireito;
		public int casaDecimal;
		public int nivel;
		public T pai;
		public boolean ehEsquerdoDoPai;
		public boolean temFilhoEsquerdo;
		public boolean temFilhoDireito;
		
		public Nodo<T> esquerdo;
		public Nodo<T> direito;
		public Nodo<T> npai;
		
		
		
		public Nodo(T chave) {
			this.chave = chave;
			altura = 0;
			esquerdo = direito = npai = null;
			dentroDireito = 0;
			dentroEsquerdo = 0;
			foraDireito = 0;
			foraEsquerdo = 0;
			temFilhoEsquerdo = false;
			temFilhoDireito = false;
		}
	}
	
	private Stack<Nodo> aLista = new Stack<Nodo>();
	public int Contador;		
	public Nodo<T> raiz;

	public void inserir(T chave) {
		raiz = inserir0(raiz, chave);
	}

	private Nodo<T> inserir0(Nodo<T> nodo, T chave) {
		if (nodo == null)
			return new Nodo<>(chave);

		//if (chave < nodo.chave) {
		if (chave.compareTo(nodo.chave) < 0) {
			nodo.esquerdo = inserir0(nodo.esquerdo, chave);
			/*if (h(nodo.esquerdo) - h(nodo.direito) == 2) {
				//if (chave < nodo.esquerdo.chave) {
				if (chave.compareTo(nodo.esquerdo.chave) < 0) {
					nodo = rotacionarComFilhoEsquerdo(nodo);
				} else {
					nodo = duplaComFilhoEsquerdo(nodo);
				}
			}*/
		//} else if (chave > nodo.chave) {
		} else if (chave.compareTo(nodo.chave) > 0) {
			nodo.direito = inserir0(nodo.direito, chave);
			/*if (h(nodo.esquerdo) - h(nodo.direito) == -2) {
				//if (chave > nodo.direito.chave) {
					if (chave.compareTo(nodo.direito.chave) > 0) {
				nodo = rotacionarComFilhoDireito(nodo);
				} else {
					nodo = duplaComFilhoDireito(nodo);					
				}
			}*/
		} else
			throw new IllegalArgumentException("Chave duplicada");

		nodo.altura = Math.max(h(nodo.esquerdo), h(nodo.direito)) + 1;

		return nodo;
	}

	private Nodo<T> duplaComFilhoDireito(Nodo<T> k1) {
		k1.direito = rotacionarComFilhoEsquerdo(k1.direito);
		return rotacionarComFilhoDireito(k1);
	}

	private Nodo<T> duplaComFilhoEsquerdo(Nodo<T> k3) {
		k3.esquerdo = rotacionarComFilhoDireito(k3.esquerdo);
		return rotacionarComFilhoEsquerdo(k3);
	}

	private Nodo<T> rotacionarComFilhoDireito(Nodo<T> k1) {
		Nodo<T> k2 = k1.direito;
		k1.direito = k2.esquerdo;
		k2.esquerdo = k1;

		k1.altura = Math.max(h(k1.esquerdo), h(k1.direito)) + 1;
		k2.altura = Math.max(k1.altura, h(k2.direito)) + 1;

		return k2;
	}

	private Nodo<T> rotacionarComFilhoEsquerdo(Nodo<T> k2) {
		Nodo<T> k1 = k2.esquerdo;
		k2.esquerdo = k1.direito;
		k1.direito = k2;

		k2.altura = Math.max(h(k2.esquerdo), h(k2.direito)) + 1;
		k1.altura = Math.max(h(k1.esquerdo), k2.altura) + 1;

		return k1;
	}

	private int h(Nodo<T> nodo) {

		return nodo == null ? -1 : nodo.altura;
	}

	public void preOrdem() {
		System.out.println("PRE=");
		preOrdem0(raiz);
		System.out.println();
	}
	

	private void preOrdem0(Nodo<T> nodo) {
		if (nodo == null)
			return;

		System.out.print(" " + nodo.chave);
		
		preOrdem0(nodo.esquerdo);
		preOrdem0(nodo.direito);

	}

	public void central() {
		System.out.println("CENTRAL=");
		central0(raiz);
		System.out.println();
	}
	
	private void central0(Nodo<T> nodo) {
		if (nodo == null)
			return;

		central0(nodo.esquerdo);

		System.out.print(" " + nodo.chave);
		
		central0(nodo.direito);
	}

	public void posOrdem() {
		System.out.println("POS=");	
		posOrdem0(raiz);
		System.out.println();
	}
	
	private void posOrdem0(Nodo<T> nodo) {
		if (nodo == null)
			return;

		posOrdem0(nodo.esquerdo);
		posOrdem0(nodo.direito);

		System.out.print(" " + nodo.chave);	
	}	
	
	@Override
	public String toString() {
		return String.format("ArvoreBinariaDePesquisa [raiz=%s]",
				toString0(raiz));
	}

	private String toString0(Nodo<T> nodo) {
		if (nodo == null)
			return " # ";

		int delta = getAltura0(nodo.esquerdo) - getAltura0(nodo.direito);
		String msg = String
				.format("%s  nível= ? altura= %d delta= %d grau= %d pai= ? tio = ? %n[%s]%n[%s]",
						nodo.chave, getAltura0(nodo), delta, grau(nodo),
						toString0(nodo.esquerdo), toString0(nodo.direito));
		return msg;
	}

	private int grau(Nodo<T> nodo) {
		if (nodo == null)
			return -1;
		int g = 0;
		if (nodo.esquerdo != null)
			g++;
		if (nodo.direito != null)
			g++;

		return g++;
	}

	public boolean consultar(T chave) {
		return consultar0(raiz, chave);
	}

	private boolean consultar0(Nodo<T> nodo, T chave) {
		if (nodo == null)
			return false;
		
		if (chave.compareTo(nodo.chave) < 0)
			return consultar0(nodo.esquerdo, chave);
		else if (chave.compareTo(nodo.chave) > 0)
			return consultar0(nodo.direito, chave);
		else
			return true;
	}

	public int getAltura() {
		return getAltura0(raiz);
	}

	private int getAltura0(Nodo<T> nodo) {
		if (nodo == null)
			return -1;

		int ae = getAltura0(nodo.esquerdo);
		int ad = getAltura0(nodo.direito);
		
		return 1 + Math.max(ae, ad);
	}

	public boolean isEquilibrada() {
		return isEquilibrada0(raiz);
	}

	private boolean isEquilibrada0(Nodo<T> nodo) {
		if (nodo == null)
			return true;
		int delta = getAltura0(nodo.esquerdo) - getAltura0(nodo.direito);

		if (delta == 2 || delta == -2)
			return false;

		return isEquilibrada0(nodo.esquerdo) && isEquilibrada0(nodo.direito);
	}
	

	public ArrayList<Integer> getInternalElements(){
		ArrayList<Integer> aLista = new ArrayList<Integer>();
		return BuscaElementos0(raiz, aLista);
	}
	
	
	public ArrayList<Integer> BuscaElementos0(Nodo nodo, ArrayList<Integer> aLista){
		
			if(nodo == null) return aLista;
	
			if(nodo.esquerdo != null){
				BuscaElementos0(nodo.esquerdo, aLista);				
				if(!aLista.contains(nodo.chave)){
					aLista.add((Integer) nodo.chave);
				}

			}
			if(nodo.direito != null){
				BuscaElementos0(nodo.direito, aLista);
				if(!aLista.contains(nodo.chave)){
					aLista.add((Integer) nodo.chave);
				}
			}			
			return aLista;		
	}
	


	
	public int folhas(){
		return folhas0(raiz, 0);		
	}

	private int folhas0(Nodo nodo, int count){
		if(nodo == null) return 0;
		
		if(nodo.esquerdo== null && nodo.direito == null){
			count = count +  1;
			return count;
		}
		 if (nodo.esquerdo != null){
			count =  folhas0(nodo.esquerdo, count);
		}
		 if(nodo.direito != null){
			count = folhas0(nodo.direito, count);
		}
		return count;
	}
	
	public ArrayList<T> RecuperaParentes(T Elemento){
		return RecuperaParentes0(raiz,new ArrayList<T>(), Elemento);	
	}

	public ArrayList<T> RecuperaParentes0(Nodo nodo, ArrayList<T> aLista, T elemento){
		if(nodo == null) return aLista;
		
		if(elemento == nodo.chave){
			 aLista.add((T) nodo.chave);
			 return aLista;
		}
		
		if(elemento.compareTo((T) nodo.chave) < 0){
			aLista = RecuperaParentes0(nodo.esquerdo,aLista,elemento);
		}else if (elemento.compareTo( (T) nodo.chave) > 0){
			aLista = RecuperaParentes0(nodo.direito,aLista,elemento);
		}
		
		if(aLista.size() > 0){
			aLista.add((T)nodo.chave);
		}
		
		return aLista;
			
	}
	
	
	public void imprimePos(){
		imprimePos0(raiz);
	}
	
	
	public void imprimePos0(Nodo nodo){
		
		if(nodo.esquerdo != null){
			imprimePos0(nodo.esquerdo);
		}
		if(nodo.direito != null){
			imprimePos0(nodo.direito);
		}
		System.out.println(nodo.chave);
		return;

	}
	
	public void imprimePre(){
		imprimePre0(raiz);
	}
	
	
	public void imprimePre0(Nodo nodo){
		if(nodo == null){System.out.println("Nulo");}

		System.out.println(nodo.chave);
		
		if(nodo.esquerdo != null){
			imprimePre0(nodo.esquerdo);
		}
		if(nodo.direito != null){
			imprimePre0(nodo.direito);
		}
		return;

	}

	public int alturaArvore(){
		return alturaArvore0(raiz);
	}
	
	public int alturaArvore0(Nodo nodo){
		if(nodo == null) return 0;
		
		int altEsq = alturaArvore0(nodo.esquerdo);
		int altDir = alturaArvore0(nodo.direito);
		
		if(altEsq > altDir){
			return altEsq + 1;
		}else{
			return altDir + 1;
		}
	}
	
	public void RetornaGrau(T chave){
		int altura = RetornaAltura(raiz, chave);
		System.out.println("ALTURA ARVORE = " + altura);
	}

	private int RetornaAltura(Nodo nodo, T chave){
		if(nodo == null){
			return -1;
		}
		else{
			int hE = RetornaAltura(nodo.esquerdo, chave);
			if(chave == nodo.chave){
				System.out.println(hE + 1);
			}
			int hD = RetornaAltura(nodo.direito,chave);
			if(chave == nodo.chave){
				System.out.println(hE + 1);
			}
			if(hE > hD){ return hE + 1;}
			else{ return hD + 1;}			
		}
		
	}

	
	private List<T> getCaminho0(Nodo<T> nodo, T chave, List<T> r) {
		if (nodo == null)
			throw new IllegalArgumentException("Chave não encontrada: " + chave);

		r.add(nodo.chave);

		if (chave.compareTo(nodo.chave) < 0)
			return getCaminho0(nodo.esquerdo, chave, r);
		else if (chave.compareTo(nodo.chave) > 0)
			return getCaminho0(nodo.direito, chave, r);
		else
			return r;
	}
	

	
	
/* ################################ ALGORITMO PARA O T3 DE ALPRO 3 ############################*/
	
	//Método que monta o nível dos nodos
	public Stack<Nodo> preencheListaNivel(Nodo raiz){
		Stack<Nodo> aLista = new Stack<Nodo>(); 
	    Queue<Nodo> nodes= new LinkedList<>(); 
	    
		Nodo nodo = raiz;
		aLista.push(raiz);
		raiz.nivel = 0;
		nodes.add(raiz);
		int iNivel = 0;
		
		while(! nodes.isEmpty()){
			Nodo pai = nodes.poll();

			if(pai.esquerdo != null){
				//Adiciona o lado esquerdo
				Nodo aux = pai.esquerdo;
				aux.npai = pai;
				aux.pai = pai.chave;
				aux.nivel = retornaNivel((T) aux.chave);
				aux.casaDecimal = String.valueOf(aux.chave).length();
				aux.ehEsquerdoDoPai = true;
				aLista.push(aux);
				nodes.add(pai.esquerdo);
			}
			if(pai.direito != null){
				Nodo auxDir = pai.direito;
				auxDir.pai = pai.chave;
				auxDir.npai = pai;
				auxDir.nivel = retornaNivel((T) auxDir.chave);
				auxDir.casaDecimal = String.valueOf(auxDir.chave).length();
				auxDir.ehEsquerdoDoPai = false;
				aLista.push(auxDir);
				nodes.add(pai.direito);
			}
		}
		return aLista;		
	}
	
	//Método que retorna o nível do nodo específico
	public int retornaNivel(T valor){
		return retornaNivel0(raiz, valor);
	}
	
	//Método recursivo que procura e retorna o nível do nodo.
	private int retornaNivel0(Nodo nodo, T valor){
		if(nodo == null) return -1;
		
		if(nodo.chave == valor){return 0;}
		else if( ((Comparable<T>) nodo.chave).compareTo(valor) > 0){ 
				return retornaNivel0(nodo.esquerdo, valor) + 1;
		}else{
			return retornaNivel0(nodo.direito,valor) + 1;
		}	
	}
	
	//Método que complementa os caracteres de referência de espaço do nodo
	public String PrintNodo(Nodo nodoAtual){

			String printNivel ="";
			
			int casasDecimaisEsquerdoDoNodoDaEsquerda =0;
			int casasDecimaisDireitaDoNodoDaDireita = 0;
			if(nodoAtual.esquerdo != null){
			casasDecimaisEsquerdoDoNodoDaEsquerda = RetornaTotalCasasDecimais(nodoAtual.esquerdo.esquerdo,"esq",true);
			}
			if(nodoAtual.direito != null){
			 casasDecimaisDireitaDoNodoDaDireita=0;
			}
			int casasDecimaisEsquerdoDoNodo =RetornaTotalCasasDecimais(nodoAtual.esquerdo,"esq",false);
			int casasDecimaisDireitoDoNodo =RetornaTotalCasasDecimais(nodoAtual.direito,"dir",false);
				
			printNivel +=ComplementaEspacoEsq(casasDecimaisEsquerdoDoNodoDaEsquerda);
			printNivel += MontaNivelEsquerdoNodoAtual(nodoAtual.esquerdo);
			
			printNivel+= nodoAtual.chave;
			
			printNivel += MontaNivelDireitoNodoAtual(nodoAtual.direito);
			printNivel += ComplementaEspacoDir(casasDecimaisDireitaDoNodoDaDireita);

			return printNivel;
	}
	
	//Método principal que será chamado para imprimir
	public void ImprimeArvore(){
		print_tree(raiz);
	}
			
	//Método que faz o percurso mais a esquerda ou direta, para imprimir a linha
	public void print_tree(Nodo raiz){
		Contador = 0;
		Stack<Nodo>  lNivel = preencheListaNivel(raiz);
		int quantItens = lNivel.size();
		int i=0;	
		String PrintTela = "";
		boolean bPodeImprimir= false;
		Contador += 6;
		while(i < quantItens){
			Nodo nodoAtual = lNivel.get(i);
			Contador += 13;
			if(PrintTela == "" && (i+1) < quantItens && nodoAtual.nivel != lNivel.get(i+1).nivel && nodoAtual.pai == null){
				PrintTela += PrintNodo(nodoAtual);
				bPodeImprimir = true;
				Contador += 3;
			}
			else{ 
				
				boolean bPercorre = true;
				boolean bValorAnt = false;
				int iValorPai = 0;
				int p = 0;
				Nodo InicioPai =  lNivel.get(0);
				Contador += 7;
				if(PrintTela == ""){
					Contador += 3;
					iValorPai = (int) lNivel.get(0).chave;
				}else{
					Contador += 5;
					if(PrintTela.contains(lNivel.get(i - 1).chave.toString())){
						Contador+= 1;
						bValorAnt = true;
					}
				}
				
				while(p < quantItens){
						Contador += 9;					
						//NAO TEM VALOR DA VARIAVEL ANTERIOR DENTRO DA STRING
						if(!bValorAnt && ((Comparable<T>) lNivel.get(p).chave).compareTo((T) nodoAtual.chave) < 0 && (naoAncestral(lNivel.get(p),nodoAtual))){
							PrintTela +=  String.format("%1$-"+ lNivel.get(p).chave.toString().length() +"s","");	
						}//JA TEM VALOR DA VARIAVEL ANTERIOR DENTRO DA STRING
						else if (((Comparable<T>) lNivel.get(p).chave).compareTo((T) nodoAtual.chave) < 0 ){
							Contador += 19;
							
							//SE TEM FILHO DIREITO && O ATUAL É O PROXIMO DA FILA && NÃO É ANCESTRAL DO ANTERIOR 
							if(lNivel.get(i - 1).direito != null && ((Comparable<T>) lNivel.get(p).chave).compareTo((T) lNivel.get(i - 1).direito.chave ) > 0  && (naoAncestral(lNivel.get(p),nodoAtual))){
								PrintTela +=  String.format("%1$-"+ lNivel.get(p).chave.toString().length() +"s","");	
							//SE O NODO ATUAL É O PRÓXIMO DA FILA  && NÃO EH ANCESTRAL DO ANTERIOR	
							}else if(((Comparable<T>) lNivel.get(p).chave).compareTo((T) lNivel.get(i - 1).chave ) > 0 && (naoAncestral(lNivel.get(p),nodoAtual))){
								Contador += 12;
								
								if(lNivel.get(i - 1).direito != null )
								{	
									Contador += 15;
									//SE O NODO ANTERIOR É DIFERENTE DO NODO ATUAL && NODO ATUAL É O PRÓXIMO DA FILA
									if( lNivel.get(i - 1).direito.chave !=  lNivel.get(p).chave &&   ((Comparable<T>) lNivel.get(p).chave).compareTo((T) lNivel.get(i - 1).direito.chave) > 0 ){
										Contador += 7;
										PrintTela +=  String.format("%1$-"+ lNivel.get(p).chave.toString().length() +"s","");	
									}
								}
								else{
									Contador += 7;
									PrintTela +=  String.format("%1$-"+ lNivel.get(p).chave.toString().length() +"s","");	
								}
							}
						}
						Contador += 2;
					p++;
				}
			
				PrintTela += PrintNodo(nodoAtual);
			    Contador +=4;
				if(i+1 >= quantItens){
					Contador +=1;	
					bPodeImprimir=true;					
				}else if(nodoAtual.nivel != lNivel.get(i+1).nivel){
					bPodeImprimir=true;						
					Contador += 6;
				}	
			}
			i++;
			Contador += 3;
			if(bPodeImprimir){
				System.out.println(PrintTela);
				PrintTela = "";
				bPodeImprimir= false;
				Contador += 4;
			}
		}
	}
	
	//Método que busca verifica se o nodo atual não é ancestral para aplicar o calculo de espaçamento	
	private boolean naoAncestral(Nodo<T> aVerificar, Nodo<T> atual)
	{
		if(aVerificar.pai == null) { return true; }
		if(aVerificar.pai == atual.chave) { return false; }
		return naoAncestral(aVerificar.npai, atual);
	}
	
	//Método que complementa os espaços a Direita do nodo atual
	private String ComplementaEspacoDir(int casasDecimaisDireitaDoNodoDaDireita) {
		String printEspaco="";
		//Complementa espaço a esquerda
		for(int i=0; i<casasDecimaisDireitaDoNodoDaDireita; i++){
			printEspaco+=" ";
		}	
		return printEspaco;
	}
	
	//Método que complementa os espaços do lado esquerdo do nodo atual
	private String ComplementaEspacoEsq(int casasDecimaisEsquerdaDoNodoDaEsquerda) {
		String printEspaco="";
		//Complementa espaço a esquerda
		for(int i=0; i<casasDecimaisEsquerdaDoNodoDaEsquerda; i++){
			printEspaco+=" ";
		}	
		return printEspaco;
	}
	
	//Método que soma os espaços das casas decimais.
	public int RetornaTotalCasasDecimais(Nodo nodo, String lado, boolean espacoFora){
		
			if(lado.equals("esq")){
				int casasDecimaisEsquerdoDoNodo = 0;	
				if (nodo != null){
					if(espacoFora){
						casasDecimaisEsquerdoDoNodo = calculaCasaDecimas(nodo.esquerdo,0);
						casasDecimaisEsquerdoDoNodo += calculaCasaDecimas(nodo.direito,0);
					}else{
						casasDecimaisEsquerdoDoNodo = calculaCasaDecimas(nodo.direito,0);
					}
					casasDecimaisEsquerdoDoNodo += String.valueOf(nodo.chave).toString().length();
				}
				return casasDecimaisEsquerdoDoNodo;
			}
			else{
				int casasDecimaisDireitoDoNodo = 0;
				if(nodo != null){
					if(espacoFora){
						casasDecimaisDireitoDoNodo = calculaCasaDecimas(nodo.direito,0);
					}else{
						casasDecimaisDireitoDoNodo = calculaCasaDecimas(nodo.esquerdo,0);
					}							
					casasDecimaisDireitoDoNodo += String.valueOf(nodo.chave).toString().length();
				}
				return casasDecimaisDireitoDoNodo;			
			}
	}
	
	//Método que complementa com ífens e referência para colocar pipe para o lado direito 
	public String MontaNivelDireitoNodoAtual(Nodo direito) {
		String sPrint = "";
		//Lado Direito
		int casasDecimaisDireitoDoNodo =RetornaTotalCasasDecimais(direito,"dir",false);
		
		//Coloca ífens no lado direito do nodo (---)
		for(int i=0; i<casasDecimaisDireitoDoNodo - 1; i++){
			sPrint +="·";
		}

		if(casasDecimaisDireitoDoNodo > 0){
		sPrint +="|";
		}
		
		return sPrint;	
	}

	//Método que complementa com ífens e referência para colocar pipe para o lado essquerdo
	private String MontaNivelEsquerdoNodoAtual(Nodo esquerdo) {
		String sPrint = "";
		//Lado Esquerdo
		int casasDecimaisEsquerdoDoNodo =RetornaTotalCasasDecimais(esquerdo,"esq", false);
			
		if(casasDecimaisEsquerdoDoNodo > 0){
			//Cria a String que será printada na tela
			sPrint +="|";
		}
			
		//Coloca ifens no lado esquerdo do nodo (---)
		for(int i=0; i<casasDecimaisEsquerdoDoNodo - 1; i++){
			sPrint+="·";
		}
		return sPrint;
	}

	//Método que calcula o total das casas decimais de cada string
	public int calculaCasaDecimas(Nodo nodo, int count){
		if(nodo == null) return count += 0;
		else{
			if(nodo.esquerdo != null){
				count = calculaCasaDecimas(nodo.esquerdo,count);
			}
			if(nodo.direito != null){
				count = calculaCasaDecimas(nodo.direito,count);
			}
			count += String.valueOf(nodo.chave).toString().length();
		}
		return count;
	}
	
	
	public void Gravar(String texto){  
	    String conteudo = texto;  
	    try{  
	        // o true significa q o arquivo será constante  
	        FileWriter x = new FileWriter("C:\\Users\\Renan\\Documents\\GitHub\\aa\\BLA.csv",true);   


	        conteudo += "\n"; // criando nova linha e recuo no arquivo              
	        x.write(conteudo); // armazena o texto no objeto x, que aponta para o arquivo             
	        x.close(); // cria o arquivo              
	     }  
	    // em caso de erro apreenta mensagem abaixo  
	    catch(Exception e){  
	      
	    }  
	}  
	

}
