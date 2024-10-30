package algoritmos.smithWaterman;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TabelaGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	
	private String[] formarTituloVazio(int q) {
		String[] vetStr = new String[q];
		for(int i = 0 ; i < q ; i++ ) vetStr[i] = "";
		return vetStr;
	}

	/**
	 * Create the frame.
	 */
	public TabelaGUI() {}
	/**
	 * 
	 * @param qtdColunas da tabela
	 * @param data dados da tabela
	 * @param resposta vetor de tamanho dois contendo, respectivamente, a resposta de Y e X
	 */
	public TabelaGUI(Integer qtdColunas, Object[][] data, String [] resposta) {
		inicializar(qtdColunas, data, resposta);
	}
	
	public void inicializar(Integer qtdColunas, Object[][] data, String [] resposta) {
		setTitle("Smith Waterman - etapa 1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 413, 359);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		String[] columnNames = formarTituloVazio(qtdColunas);
		
		table = new JTable( data, columnNames);
		table.setBounds(10, 11, 366, 245);
		table.setEnabled(false);
		contentPane.add(table);
		
		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.setBounds(283, 287, 89, 23);
		btnFinalizar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				finalizarPage();
			}
		});
		contentPane.add(btnFinalizar);
		
		JLabel lblTxtVertical = new JLabel("Vertical");
		lblTxtVertical.setFont(new Font("Arial", Font.PLAIN, 14));
		lblTxtVertical.setBounds(10, 267, 78, 14);
		contentPane.add(lblTxtVertical);
		
		JLabel lblTxtHorizontal = new JLabel("Horizontal");
		lblTxtHorizontal.setFont(new Font("Arial", Font.PLAIN, 14));
		lblTxtHorizontal.setBounds(10, 290, 78, 14);
		contentPane.add(lblTxtHorizontal);
		
		JLabel lblRespostaVertical = new JLabel(resposta[0]);
		lblRespostaVertical.setFont(new Font("Arial", Font.PLAIN, 14));
		lblRespostaVertical.setBounds(98, 268, 164, 14);
		contentPane.add(lblRespostaVertical);
		
		JLabel lblRespostaHorizontal = new JLabel(resposta[1]);
		lblRespostaHorizontal.setFont(new Font("Arial", Font.PLAIN, 14));
		lblRespostaHorizontal.setBounds(98, 290, 164, 14);
		contentPane.add(lblRespostaHorizontal);
		this.setVisible(true);
	}
	
	void finalizarPage() {
		this.dispose();
	}
}
