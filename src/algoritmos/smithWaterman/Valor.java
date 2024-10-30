package algoritmos.smithWaterman;

import java.util.ArrayList;
import java.util.List;

/**
 * Entidade de armazenamento de dados
 */
public class Valor {
	/**
	 * Valor de uma coordenada na tabela de valores
	 */
	Integer valor = 0;
	/**
	 * Uma lista de coordenadas [x,y]
	 */
	List<Integer[]> pais = new ArrayList<Integer[]>();
	
	public Valor() {}
	
	public Valor(Integer num) {
		valor = num;
	}
	
	@Override
	public String toString() {
		String str = "Valor: " + valor + "\n";
		str += "Vetor:\n";
		System.out.println(pais.size());
		if(pais.size() != 0) {
			for(Integer[] v : pais) {
				str += "\t" + v[0] + "," + v[1] + "\n";
			}
		}
		return str;
	}
}
