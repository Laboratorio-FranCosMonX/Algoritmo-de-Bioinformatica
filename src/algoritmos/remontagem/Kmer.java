package algoritmos.remontagem;


public class Kmer {
	public enum Aresta{
		ESQUERDA,
		DIREITA,
		UNDEFINED
	}
	
	private String sufixo = "";
	private String prefixo = "";
	private Boolean visitado = false;
	private String conteudo = "";
	private Integer qtd_nos_conectados = 0;
	
	public Kmer(String kmer) {
		conteudo = kmer;
		for(int i = 0 ; i < kmer.length() ; i++) {
			if(i > 0) sufixo += kmer.charAt(i);
			if(i < kmer.length() - 1) prefixo += kmer.charAt(i);
		}
		
//		System.out.println("Conteudo: " + kmer);
//		System.out.println("sufixo:" + sufixo);
//		System.out.println("prefixo:" + prefixo);
	}
	
	public String verConteudo() {
		return conteudo;
	}
	public void informarQueONoFoiConectadoAOutro() {
		qtd_nos_conectados++;
	}
	public Integer getQuantidadeDeNosConectados() {
		return qtd_nos_conectados;
	}
	public String getSufixo() {
		return sufixo;
	}
	public String getPrefixo() {
		return prefixo;
	}
	/**
	 * 
	 * @param str : prefixo do que esta sendo comparado sob o item fixo
	 * @return
	 */
	public boolean sufixoEIgualA(String str) {
		if(sufixo.equals(""))
			System.err.println("Problema de programação");
		
		if(sufixo.equals(str))
			return true;
		return false;
	}
	/**
	 * 
	 * @param str : sufixo do que esta sendo comparado sob o item fixo
	 * @return
	 */
	public boolean prefixoEIgualA(String str) {
		if(prefixo.equals(""))
			System.err.println("Problema de programação");
		
//		System.out.println("Prefixo:" + prefixo + "\nverificando: " + str);
		if(prefixo.equals(str))
			return true;
		return false;
	}
	public boolean jaFoiVisitado() {
		return visitado;
	}
	public void marcarComoVisitado() {
		visitado = true;
	}
	public void marcarComoNAOVisitado() {
		visitado = false;
	}
}
