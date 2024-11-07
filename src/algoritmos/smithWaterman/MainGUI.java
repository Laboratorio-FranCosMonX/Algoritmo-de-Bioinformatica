package algoritmos.smithWaterman;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class MainGUI {
	private JFrame mainFrame;
	private JTextField tfVertical;
	private JTextField tfHorizontal;
	private JTextField tfGap;
	private JTextField tfMissMatch;
	private JTextField tfMatch;;
	/**
	 * Create the application.
	 */
	public MainGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainFrame = new JFrame();
		mainFrame.setResizable(false);
		mainFrame.setTitle("Smith Waterman - Entrada");
		mainFrame.setBounds(100, 100, 327, 262);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 311, 223);
		panel.setLayout(null);
		
		tfVertical = new JTextField();
		tfVertical.setColumns(10);
		tfVertical.setBounds(25, 26, 264, 29);
		panel.add(tfVertical);
		
		JLabel lblVertical = new JLabel("Vertical");
		lblVertical.setFont(new Font("Arial", Font.PLAIN, 16));
		lblVertical.setBounds(25, 0, 81, 29);
		panel.add(lblVertical);
		
		tfHorizontal = new JTextField();
		tfHorizontal.setColumns(10);
		tfHorizontal.setBounds(25, 82, 264, 29);
		panel.add(tfHorizontal);
		
		JLabel lblHorizontal = new JLabel("Horizontal");
		lblHorizontal.setFont(new Font("Arial", Font.PLAIN, 16));
		lblHorizontal.setBounds(25, 57, 81, 29);
		panel.add(lblHorizontal);
		
		JButton btnAvancar = new JButton("Avançar");
		btnAvancar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfVertical.getText().length() > 0 && tfHorizontal.getText().length() > 0
						&& tfGap.getText().length() > 0 && tfMissMatch.getText().length() > 0
						&& tfMatch.getText().length() > 0) {
					mostrarResposta(tfVertical.getText(), tfHorizontal.getText(), Integer.parseInt( tfGap.getText()),
							Integer.parseInt(tfMissMatch.getText()), Integer.parseInt(tfMatch.getText()));
					mainFrame.dispose();
				}
			}
		});
		btnAvancar.setBounds(25, 189, 89, 23);
		panel.add(btnAvancar);
		mainFrame.getContentPane().add(panel);
		
		JButton btnArquivo = new JButton("Usar arquivo");
		btnArquivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new Algoritm();
					mainFrame.dispose();
				}catch (Exception _) {
					JOptionPane.showMessageDialog(null, "Houve algum erro ao encontrar o arquivo ou processar as entradas.");
				}
			}
		});
		btnArquivo.setBounds(181, 189, 108, 23);
		panel.add(btnArquivo);
		
		JLabel lblGap = new JLabel("GAP");
		lblGap.setHorizontalAlignment(SwingConstants.CENTER);
		lblGap.setFont(new Font("Arial", Font.PLAIN, 16));
		lblGap.setBounds(25, 113, 58, 29);
		panel.add(lblGap);
		
		tfGap = new JTextField();
		tfGap.setColumns(10);
		tfGap.setBounds(25, 138, 58, 29);
		panel.add(tfGap);
		
		JLabel lblMissmatch = new JLabel("MISSMATCH");
		lblMissmatch.setFont(new Font("Arial", Font.PLAIN, 16));
		lblMissmatch.setBounds(106, 113, 108, 29);
		panel.add(lblMissmatch);
		
		JLabel lblMatch = new JLabel("MATCH");
		lblMatch.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMatch.setFont(new Font("Arial", Font.PLAIN, 16));
		lblMatch.setBounds(224, 113, 65, 29);
		panel.add(lblMatch);
		
		tfMissMatch = new JTextField();
		tfMissMatch.setColumns(10);
		tfMissMatch.setBounds(128, 138, 58, 29);
		panel.add(tfMissMatch);
		
		tfMatch = new JTextField();
		tfMatch.setColumns(10);
		tfMatch.setBounds(231, 138, 58, 29);
		panel.add(tfMatch);
		mainFrame.setVisible(true);
	}
	
	private void mostrarResposta(String vertical, String horizontal, Integer gap, Integer missmatch, Integer match) {
		System.out.println("Tudo funcionou como o esperado");
		try {
			Algoritm alg = new Algoritm(vertical, horizontal, gap, missmatch, match);
			new TabelaGUI(horizontal.length() + 2, alg.getTab(), alg.getAlinhamento());
		} catch (Exception _) {
			System.out.println("");
		}
	}
}
