package algoritmos.smithWaterman.arquivo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Arquivo {
	
	private Object[] tratarEntrada(List<String> dados) {
		Object[] dado_correto = new Object[5];
		
		for(int i = 0 ; i < 5 ; i++) {
			if(i==0 || i==1) {
				//vertical e horizontal
				dado_correto[i] = dados.get(i);
			}else {
				dado_correto[i] = Integer.parseInt(dados.get(i));
			}
		}
		
		return dado_correto;
	}
	
	public Object[] ler(String nome_arquivo) throws IOException {
		try (BufferedReader leitor = new BufferedReader(new FileReader(nome_arquivo))) { 
			List<String> dados = new ArrayList<String>();
			String linha;
			while ((linha = leitor.readLine()) != null) { 
				dados.add(linha);
			} 
			return tratarEntrada(dados);
		} catch (IOException e) {
			throw new IOException("Ocorreu um erro ao ler o arquivo: " + e.getMessage());
		}
	}
	
	public void escrever(String txt) throws IOException {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("saida.txt"));){
			bw.write(txt);
		} catch (Exception e) {
			throw new IOException("Ocorreu um erro ao escrever no arquivo saida.txt");
		}
	}
}
