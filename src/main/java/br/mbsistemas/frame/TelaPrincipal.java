package br.mbsistemas.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class TelaPrincipal extends JFrame implements ActionListener {
	private JButton jbtTelaListaDispositivo, jbtTelaListaEndereco;
	private Toolkit tkTela;
	private TelaListaDispositivo tlListaDispositivo = new TelaListaDispositivo();
	
	public TelaPrincipal() {
		setTitle("Tela principal");
		setLayout(new BorderLayout());
		
		JPanel jpnTelas = new JPanel();
		jpnTelas.setVisible(true);
		jpnTelas.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		jpnTelas.setLayout(new GridLayout(1, 1));
		getContentPane().add(jpnTelas, BorderLayout.NORTH);
		
		jbtTelaListaDispositivo = new JButton("Lista de dispositivos");
		jbtTelaListaDispositivo.setVisible(true);
		jbtTelaListaDispositivo.setFocusable(false);
		jbtTelaListaDispositivo.addActionListener(this);
		jpnTelas.add(jbtTelaListaDispositivo);
		
		jbtTelaListaEndereco = new JButton("Lista de endereços");
		jbtTelaListaEndereco.setVisible(true);
		jbtTelaListaEndereco.setFocusable(false);
		jbtTelaListaEndereco.addActionListener(this);
		jpnTelas.add(jbtTelaListaEndereco);
		
		JPanel jpnMenuEsquerdo = new JPanel();
		jpnMenuEsquerdo.setVisible(true);
		jpnMenuEsquerdo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		jpnMenuEsquerdo.setLayout(new GridLayout(1, 1));
		getContentPane().add(jpnMenuEsquerdo, BorderLayout.WEST);
		
		jbtTelaListaEndereco = new JButton("Lista de endereços");
		jbtTelaListaEndereco.setVisible(true);
		jbtTelaListaEndereco.setFocusable(false);
		jbtTelaListaEndereco.addActionListener(this);
		jpnMenuEsquerdo.add(jbtTelaListaEndereco);
		
		getContentPane().add(tlListaDispositivo, BorderLayout.CENTER);		
		
		setSize(tkTela.getDefaultToolkit().getScreenSize().width, tkTela.getDefaultToolkit().getScreenSize().height);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
	}
	
	public static void main(String[] args) {
		String lookAndFeel = UIManager.getSystemLookAndFeelClassName(); // pega aparencia do SO
		try {
			UIManager.setLookAndFeel(lookAndFeel);
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new TelaPrincipal();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == jbtTelaListaDispositivo){
			tlListaDispositivo.setVisible(true);
		}
		if(e.getSource() == jbtTelaListaEndereco){
			TelaListaEndereco tlListaEndereco = new TelaListaEndereco();
		}
		
	}
	
}
