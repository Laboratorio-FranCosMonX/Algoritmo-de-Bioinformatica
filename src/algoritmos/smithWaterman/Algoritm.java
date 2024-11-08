package algoritmos.smithWaterman;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import algoritmos.smithWaterman.arquivo.Arquivo;

/**
 * Reference: https://www.geeksforgeeks.org/regular-expressions-in-java/
 */

public class Algoritm {
	private static final String regex = "(?=\\w+)|(?= )|(?=\\-)";
	private static final Pattern pattern = Pattern.compile(regex);
	private static List<String>Y = new ArrayList<String>();
	private static List<String> X = new ArrayList<String>();
	private static Valor [][] tab;
	
	private static Integer GAP = -4;
	private static Integer MISSMATCH = -2;
	private static Integer MATCH = 8;
	
	private static String dadosArquivo = "";
	private static String[] alinhamento = new String[2];
	private static Integer SCORE = 0;
	private static Stack<String> YY = new Stack<String>();
	private static Stack<String> XX = new Stack<String>();	
	
	/*
	 * Inicializador para usar input.txt como dados de entrada (5 linhas obrigatoriamente)
	 */
	public Algoritm() throws IOException {
		Arquivo arq =  new Arquivo();
		Object[] dados_arquivo = arq.ler("input.txt");
		GAP = (Integer) dados_arquivo[2];
		MISSMATCH = (Integer) dados_arquivo[3];
		MATCH = (Integer) dados_arquivo[4];
		
		init(dados_arquivo[0].toString(), dados_arquivo[1].toString());
		algoritm();
		backtrace();
		guardarAlinhamento();
		
		formarRespostaArquivo();
		arq.escrever(dadosArquivo);
		JOptionPane.showMessageDialog(null, "Dados gravados no arquivo saida.txt.");
	}
	
	public Algoritm(String sequenciaY, String sequenciaX, Integer gap, Integer missmatch, Integer match) {
		GAP = gap;
		MISSMATCH = missmatch;
		MATCH = match;
		init(sequenciaY, sequenciaX);
		algoritm();
		backtrace();
		guardarAlinhamento();
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
			Valor val = new Valor(tab[i-1][0].valor + GAP);
			Integer [] v = {0,i-1};
			val.pais.add(v);
			tab[i][0] = val;
		}
		for(int j = 1; j < X.size() ; j++) {
			Valor val = new Valor(tab[0][j-1].valor + GAP);
			Integer [] v = {j-1,0};
			val.pais.add(v);
			tab[0][j] = val;
		}
	}
	
	/**
	 * Calcular os valores reais de cada localização na tabela
	 * @param x
	 * @param y
	 * @return
	 */
	private Valor compare(int x, int y) {
		int maiorValor = 0;
		int comparaBases = X.get(x).equals(Y.get(y)) ? MATCH : 
			X.get(x).equals("-") || Y.get(y).equals("-") ? + GAP : + MISSMATCH;
		
		int numeroX = tab[y][x-1].valor + GAP;
		int numeroY = tab[y-1][x].valor + GAP;
		int numeroXY = tab[y-1][x-1].valor + comparaBases;
		
		maiorValor = numeroX > numeroY && (numeroX > numeroXY || numeroY > numeroXY) ? numeroX :
			numeroY > numeroXY && (numeroY > numeroX || numeroXY > numeroX) ? numeroY :
				numeroXY;
		
		Valor val = new Valor(maiorValor);
		
		if(numeroX == maiorValor) {
			Integer [] paiX = {x-1,y};
			
			if(!val.pais.contains(paiX))val.pais.add(paiX);
		}
		if(numeroY == maiorValor) {
			Integer [] paiY = {x,y-1};
			
			if(!val.pais.contains(paiY))val.pais.add(paiY);
		}
		 if( numeroXY == maiorValor) {
			Integer [] paiXY = {x-1,y-1};
			
			if(!val.pais.contains(paiXY))val.pais.add(paiXY);
		}
		return val;
	}
	
	/**
	 * Passa por toda a matriz a partir do indice [1,1]
	 */
	private void algoritm() {
		for(int i = 1 ; i < Y.size() ; i++) {
			for(int j = 1 ; j < X.size() ; j++) {
				tab[i][j] = compare(j, i);
			}
		}
	}
	
	/**
	 * 
	 * @param posicaoAntiga coordenada [x,y]
	 * @param XVisivel true para registrar a letra
	 * @param YVisivel true para registrar a letra
	 */
	private void registrarBackTrace(Integer[] posicaoAntiga, boolean XVisivel, boolean YVisivel) {
		
//		System.out.println(XVisivel + " " + YVisivel);
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

				descendente.add(aux);

				tamanho = descendente.size();
				registrarBackTrace(descendente.get(descendente.size() - 2),
						descendente.get(descendente.size() - 2)[0] - descendente.get(tamanho - 1)[0] > 0 ,
						descendente.get(descendente.size() - 2)[1] - descendente.get(tamanho - 1)[1] > 0 );
			}catch(Exception _) {
//				System.err.println("Finalização correta ou inesperada, mas prevista.");
				break;
			}
		}
		
	}
	
	/**
	 * Obter uma matriz contendo a tabela gerada em posição de 1ºo quadrante, levando em consideração um plano cartesiano.
	 * @return
	 */
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
				try {
					aux[i + 1][j+ 1] = tab[i][j].valor;					
				}catch(Exception _) {
					break;
				}
			}
		}
		return aux;
	}
	
	/**
	 * monta um vetor "alinhamento" de tamanho 2 sendo alinhamento[0] a resposta de Y (vertical) e alinhamento[1] a resposta de X (horizontal)
	 * @return
	 */
	public void guardarAlinhamento() {
		String str = "";
		try {
			while(true) {
				str += YY.pop();
			}
		}catch (Exception _) {
			alinhamento[0] = str;
		}
		
		String str2 = "";
		try {
			while(true) {
				str2 += XX.pop();
			}
		}catch (Exception _) {
			alinhamento[1] = str2;
		}
		
		int tamanho = str2.length();
		for(int i = 0 ; i < tamanho ; i++) {
			if(str.charAt(i) == str2.charAt(i)) SCORE += MATCH;
			else if(str.charAt(i) == '-' || str2.charAt(i) == '-') SCORE += GAP;
			else SCORE += MISSMATCH;
		}
	}
	
	/**
	 * Método usado para retornar um vetor contendo a resposta do alinhamento. sendo [0] vertical e [1] horizontal
	 * @return
	 */
	public String[] getAlinhamento() {
		return alinhamento;
	}
	
	/*
	 * Obter o valor final do resultado do alinhamento
	 */
	public Integer getScore() {
		return SCORE;
	}

	public String viewMatriz() {
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
		return str;
	}
	
	private void formarRespostaArquivo() {
		int qtdChars = X.size() * 10;
		String linha_De_iguais = "", linha_De_ifen = "";
		for(int i = 0 ; i < qtdChars ; i++) linha_De_iguais += "=";
		for(int i = 0 ; i < qtdChars ; i++) linha_De_ifen += "-";
	
		dadosArquivo += linha_De_ifen + "\n";
		dadosArquivo += "** matrix **\n";
		dadosArquivo += linha_De_iguais + "\n";
		dadosArquivo += viewMatriz() + "\n";
		dadosArquivo += linha_De_iguais + "\n";
		dadosArquivo += "Score = "+ SCORE + "\n";
		dadosArquivo += "** Match = " + MATCH + " | mismatch = " + MISSMATCH + " | gap = " + GAP + " **\n";
		dadosArquivo += linha_De_ifen + "\n" + "Alinhamento \n";
		dadosArquivo += "V = " + alinhamento[0] + "\n" + "H = " + alinhamento[1] + "\n";
		System.out.println(dadosArquivo);
	}
	
	/**
	 * Impressão da tabela de valores. Recomenda-se usar apenas para debug
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
		
		return str;
	}
	
}
