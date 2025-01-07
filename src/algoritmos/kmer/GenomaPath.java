package algoritmos.kmer;

import java.util.ArrayList;
import java.util.List;

/**
 * Mudar para genoma path
 * 
 * criarem ordem lexografica
 * fazer o shotgun (embaralhar)
 */
public class GenomaPath {
	private static String view_kmers = "";
	
	private static Integer n_mer;
	private static List<String> k_mers;
	private static boolean dados_carregados = false;
	
	public GenomaPath(Integer kmer) {
		this.n_mer = kmer;
		this.k_mers = new ArrayList<String>();
	}
	
	public void montar_sequencia(String sequencia) {
		for(int i = 0 ; i < sequencia.length() ; i++) {
			if(i+n_mer > sequencia.length()) break;
			
			String kmer = "";
			for(int j = i; j < i + n_mer; j++) {
				kmer += sequencia.charAt(j);
			}
			
			k_mers.add(kmer);
			view_kmers += kmer + "\n";
		}
		
		System.out.println("Etapa 1 oncluida. Gerado cadeia de caracteres k-mer");
		dados_carregados = true;
	}
	
	public String view_kmers() {
		if(!dados_carregados) return null;
		return view_kmers;
	}

	public static void main(String[] args) {
		GenomaPath m = new GenomaPath(3);
		m.montar_sequencia("CASA");
		System.out.println(m.view_kmers());
	}

}
