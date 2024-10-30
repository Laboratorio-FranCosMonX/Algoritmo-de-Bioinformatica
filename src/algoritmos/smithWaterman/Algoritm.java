package algoritmos.smithWaterman;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;

/**
 * Reference: https://www.geeksforgeeks.org/regular-expressions-in-java/
 */

public class Algoritm {
	private static final String regex = "(?=\\w+)|(?= )|(?=\\-)";
	private static final Pattern pattern = Pattern.compile(regex);
	private static List<String>Y = new ArrayList<String>();
	private static List<String> X = new ArrayList<String>();
	private static Valor [][] tab;
	
	private static Stack<String> YY = new Stack<String>();
	private static Stack<String> XX = new Stack<String>();	
	
	public Algoritm(String sequenciaY, String sequenciaX) {
		init(sequenciaY, sequenciaX);
		algoritm();
		backtrace();
	}
	
	private void init(String a, String b) {
		//definicoes
		
		a = a.replace("-", "");
		b = b.replace("-", "");
		a = "-" + a;
		b = "-" + b;
		String [] vetY = pattern.split(a);
		String [] vetX = pattern.split(b);
		tab = new Valor[vetY.length][vetX.length];
		
		//preenchimentos
		for(String chr : vetY) {
			Y.add(chr);
		}
		for(String chr : vetX) {
			X.add(chr);
		}
		
		Valor v00 = new Valor(0);
		tab[0][0] = v00;
		for(int i = 1; i < Y.size() ; i++) {
			Valor val = new Valor(tab[i-1][0].valor - 2);
			Integer [] v = {0,i-1};
			val.pais.add(v);
			tab[i][0] = val;
		}
		for(int j = 1; j < X.size() ; j++) {
			Valor val = new Valor(tab[0][j-1].valor - 2);
			Integer [] v = {j-1,0};
			val.pais.add(v);
			tab[0][j] = val;
		}
	}
	
	/**
	 * Impressão da tabela de valores
	 */
	@Override
	public String toString() {
		String str = "";
		int lenY = Y.size();
		int lenX = X.size();
		
		for(int i = lenY - 1 ; i>=0 ; i--) {
			str += Y.get(i) + "\t";
			for(int j = 0 ; j< lenX  ; j++) {
				str += tab[i][j] == null ? "null " : tab[i][j].valor + " ";
			}
			str += "\n";
		}

		str += "\n\t";
		for(int j = 0 ; j< lenX  ; j++) {
			str += X.get(j) + " ";
		}
		
		str += "\n\n RESPOSTA\nVertical: ";
		while(true) {
			try {
				str += YY.pop();
			}catch(Exception e) {
				break;
			}
		}
		str += "\nHorizontal: ";
		while(true) {
			try {
				str += XX.pop();
			}catch(Exception e) {
				break;
			}
		}
		
//		str += "\n" + respostaY + "\n" + respostaX + "\n";
//		System.out.println("\n\n" + tab[0][2].pais.get(0)[0]);
		return str;
	}
	
	/**
	 * Calcular os valores reais de cada localização na tabela
	 * @param x
	 * @param y
	 * @return
	 */
	private Valor compare(int x, int y) {
		int maiorValor = 0;
		int comparaBases = X.get(x).equals(Y.get(y)) ? 1 : 
			X.get(x).equals("-") || Y.get(y).equals("-") ? -2 : -1;
		
		int numeroX = tab[y][x-1].valor - 2;
		int numeroY = tab[y-1][x].valor - 2;
		int numeroXY = tab[y-1][x-1].valor + comparaBases;
		
		maiorValor = numeroX > numeroY && (numeroX > numeroXY || numeroY > numeroXY) ? numeroX :
			numeroY > numeroXY && (numeroY > numeroX || numeroXY > numeroX) ? numeroY :
				numeroXY;
		
		Valor val = new Valor(maiorValor);
		
		if(numeroX == maiorValor) {
			Integer [] paiX = {x-1,y};
			
			if(!val.pais.contains(paiX))val.pais.add(paiX);
		}else if(numeroY == maiorValor) {
			Integer [] paiY = {x,y-1};
			
			if(!val.pais.contains(paiY))val.pais.add(paiY);
		}
		else if( numeroXY == maiorValor) {
			Integer [] paiXY = {x-1,y-1};
			
			if(!val.pais.contains(paiXY))val.pais.add(paiXY);
		}
		return val;
	}
	
	/**
	 * 
	 * @param posicaoAntiga coordenada [x,y]
	 * @param XVisivel true para registrar a letra
	 * @param YVisivel true para registrar a letra
	 */
	private void registrarBackTrace(Integer[] posicaoAntiga, boolean XVisivel, boolean YVisivel) {
		
//		System.out.println("Registrar valores [" + posicaoAntiga[0] + "," + posicaoAntiga[1] + "]");
//		System.out.println("\nX" + XVisivel + " Y" + YVisivel );
		System.out.println(XVisivel + " " + YVisivel);
		if(XVisivel && YVisivel) {
			XX.add(X.get(posicaoAntiga[0]));
			YY.add(Y.get(posicaoAntiga[1]));
		}else if(XVisivel) {
			XX.add(X.get(posicaoAntiga[0]));
			YY.add("-");
		}else if(YVisivel) {
			XX.add("-");
			YY.add(Y.get(posicaoAntiga[1]));
		}
//		System.out.println("Executou register");
	}
	
	/**
	 * Etapas
	 * 1 - Encontrar o maior valor na ultima linha ou coluna
	 * 2 - Pegar as coordenadas do descendente do maior valor (primeiro armazenado) e o armazenar em uma pilha
	 * 3 - iniciar um loop, onde inicia adicionando o descendente do descendente para começar a fazer as comparações.
	 * 4 - na primeira iteração é registrada o primeiro e segundo passo.
	 * 5 - a iteração se encerra quando atinge o ponto [0][0] onde não há descendentes registrados.
	 * @return
	 */
	private void backtrace() {
		Integer maiorValor = -9999999;
		Integer [] posicaoMaiorValor = new Integer[2];		
		
		//encontrar o maior valor
		for(int i = 0 ; i < Y.size() ; i++ ) {
			Integer valorAtual = tab[i][X.size()- 1].valor;
			if(valorAtual > maiorValor) {
				maiorValor = valorAtual;
				posicaoMaiorValor[0] = X.size() - 1;
				posicaoMaiorValor[1] = i;
			}
		}
		
		for(int j = 0 ; j < X.size() ; j++ ) {
			Integer valorAtual = tab[Y.size() - 1][j].valor;
			if(valorAtual > maiorValor) {
				maiorValor = valorAtual;
				posicaoMaiorValor[0] = j;
				posicaoMaiorValor[1] = Y.size() - 1;
			}
		}
		
		//armazena {X,Y}
		Stack<Integer[]> descendente = new Stack<Integer[]>();
		Integer [] pai = {posicaoMaiorValor[0], posicaoMaiorValor[1]};
		descendente.add(pai);
		while(true) {
			try {
				int tamanho = descendente.size();
				Integer [] aux = {
						tab[descendente.get(tamanho - 1)[1]][descendente.get(tamanho - 1)[0]].pais.get(0)[0],
						tab[descendente.get(tamanho - 1)[1]][descendente.get(tamanho - 1)[0]].pais.get(0)[1]
				};
//				System.out.println(aux[1]);
				descendente.add(aux);
//				System.out.println(aux[0] + " " + aux[1]);
//				System.err.println("Testando " + descendente.get(descendente.size() - 2)[0] + " " + descendente.get(descendente.size() - 2)[1]);
				
				tamanho = descendente.size();
				registrarBackTrace(descendente.get(descendente.size() - 2),
						descendente.get(descendente.size() - 2)[0] - descendente.get(tamanho - 1)[0] > 0 ,
						descendente.get(descendente.size() - 2)[1] - descendente.get(tamanho - 1)[1] > 0 );
			}catch(Exception _) {
				System.err.println("Finalização correta ou inesperada, mas prevista.");
				break;
			}
		}
		
	}
	
	private void algoritm() {
		for(int i = 1 ; i < Y.size() ; i++) {
			for(int j = 1 ; j < X.size() ; j++) {
				tab[i][j] = compare(j, i);
			}
		}
	}
	
	public Object [][] getTab (){
		Object [][] aux = new Object [tab.length + 1][tab[0].length + 1];
		int sizeY = aux.length;
		int sizeX = aux[0].length;
		for(int x =0 ; x < sizeX - 1 ; x++) {
			aux[0][x + 1] = X.get(x);
		}
		for(int y = 0 ; y < sizeY - 1 ; y++) {
			aux[y + 1][0] = Y.get(y);
		}
		for(int i = tab[1].length ; i >= 0 ; i--) {
			for(int j = 0 ; j < tab[0].length; j++) {
				aux[i + 1][j+ 1] = tab[i][j].valor;
			}
		}
		
//		String str = "";
//		for(int i = sizeY - 1 ; i >= 0 ; i--) {
//			for(int j = 0 ; j < sizeX ; j++) {
//				str += aux[i][j] + " ";
//			}
//			str += "\n";
//		}
//		System.out.println(str);
		return aux;
	}
	
	/**
	 * return vetor V de tamanho 2 sendo vetor[0] a resposta de Y (vertical) e vetor[1] a resposta de X (horizontal)
	 * @return
	 */
	public String [] getResposta() {
		String [] vStr = new String [2];
		String str = "";
		try {
			while(true) {
				str += YY.pop();
			}
		}catch (Exception _) {
			vStr[0] = str;
			System.err.println("Saída esperada");
		}
		
		String str2 = "";
		try {
			while(true) {
				str2 += XX.pop();
			}
		}catch (Exception _) {
			vStr[1] = str2;
			System.err.println("Saída esperada");
		}
		return vStr;
	}
}
