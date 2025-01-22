package remontagemSimples;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import algoritmos.smithWaterman.arquivo.Arquivo;

public class Kmers {
	private static Scanner sc = new Scanner(System.in);
	private static String resposta = "";
	
	private boolean verifica(String fixo, String mer) {
		int diferenca = (fixo.length() - mer.length());
		
		String aPrefixo = fixo.substring(0, fixo.length() - (1 + diferenca)); 
		String aSufixo = fixo.substring((1 + diferenca), fixo.length()) ;
		String merPrefixo = mer.substring(0, mer.length() - 1);
		String merSufixo = mer.substring(1, mer.length());
//		System.out.println(fixo);
//		System.out.println(mer);
//		
//		System.out.println("testeeeee");
//		System.out.println(aSufixo);
//		System.out.println("merPrefixo: " + mer);
		if(aSufixo.equals(merPrefixo)) {
			resposta+= mer.charAt(mer.length()-1);
			return true;
		}else if(aPrefixo.equals(merSufixo)) {
			resposta = mer.charAt(0) + resposta;
			return true;
		}
		return false;
	}
	
	public Kmers(ArrayList<String> kmers) {
		resposta += kmers.getFirst();
		kmers.removeFirst();
//		int cont = 0, cont2 = 0;;
		
		while(kmers.size() > 0) {
//			System.out.println(kmers.size());
			for(String k : kmers) {
				if(verifica(resposta, k)) {
					kmers.remove(k);
					break;
				}
			}
			System.out.println(kmers.size());
		}
		
		System.out.println("Finalizado com exito");
	}
	
	public String getResposta() {
		return resposta;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//		String [] MACARRAO = {"ACA","ARR", "CAR", "MAC", "RAO", "RRA"};
		System.out.print("Informe o nome do arquivo: ");
		String[] dadosLidos = Arquivo.apenasLer(sc.nextLine()).split(",");
		ArrayList<String> dados = new ArrayList<String>(Arrays.asList(dadosLidos));
		System.out.print("A seguir, informe o nome do arquivo de saída na qual a resposta serágravada (com sua extensão inclusa): ");
		String nome_arquivo_saida = sc.nextLine();
		System.out.println("Iniciando programa.");
		Kmers kmers = new Kmers(dados);
//		System.out.println(kmers.getResposta());
		Arquivo.escreverEmArquivoEspecifico(nome_arquivo_saida, kmers.getResposta());
		System.out.println("A resposta foi gravada no arquivo " + nome_arquivo_saida + ".");
		sc.close();
	}

}
