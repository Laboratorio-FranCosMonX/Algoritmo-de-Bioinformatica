package algoritmos.smithWaterman;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainGUI {
	private JFrame mainFrame;
	private JTextField tfVertical;
	private JTextField tfHorizontal;;
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
		mainFrame.setBounds(100, 100, 327, 194);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 311, 155);
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
				if(tfVertical.getText().length() > 0 && tfHorizontal.getText().length() > 0) {
					mostrarResposta(tfVertical.getText(), tfHorizontal.getText());
					mainFrame.dispose();
				}
			}
		});
		btnAvancar.setBounds(101, 124, 89, 23);
		panel.add(btnAvancar);
		mainFrame.getContentPane().add(panel);
		mainFrame.setVisible(true);
	}
	
	private void mostrarResposta(String vertical, String horizontal) {
		System.out.println("Tudo funcionou como o esperado");
		try {
			Algoritm alg = new Algoritm(vertical, horizontal);
			new TabelaGUI(horizontal.length() + 2, alg.getTab(), alg.getResposta());
		} catch (Exception _) {
			System.out.println("");
		}
	}
}
