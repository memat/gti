package br.mbsistemas.frame;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class TelaUsuario extends JFrame implements ActionListener{
	private JMenuBar barra;
	private JMenu jmnDispositivo, jmnEndereco, jmnSetor, jmnConfig;
	private JMenuItem jmiCadDispositivo, jmiAltDispositivo, jmiListaDispositivo, jmiCadEndereco, jmiAltEndereco, jmiListaEndereco, jmiCadSetor, jmiAltSetor, jmiListaSetor, jmiIniciaBanco;
	private TelaCadDispositivo tlCadDispositivo = new TelaCadDispositivo();
	private TelaCadEndereco tlCadEndereco = new TelaCadEndereco();
	private TelaCadArea tlCadArea = new TelaCadArea();
	private TelaListaDispositivo tlListaDispositivo = new TelaListaDispositivo();
	private TelaListaEndereco tlListaEndereco = new TelaListaEndereco();
	private TelaListaArea tlListaArea = new TelaListaArea();
	private TelaIniciaBanco tlIniciaBanco = new TelaIniciaBanco();
	private Toolkit tk = null;
	private Integer largura = tk.getDefaultToolkit().getScreenSize().width, altura = tk.getDefaultToolkit().getScreenSize().height-30;
	private ArrayList<JInternalFrame> inFrames = new ArrayList<JInternalFrame>();
	
	private void insereInternalFrames(){
		inFrames.add(tlCadDispositivo);
		inFrames.add(tlCadEndereco);
		inFrames.add(tlCadArea);
		inFrames.add(tlListaDispositivo);
		inFrames.add(tlListaEndereco);
		inFrames.add(tlListaArea);
		inFrames.add(tlIniciaBanco);
		
		alteraVisiblidadeInternalFrames();
	}
	
	private void alteraVisiblidadeInternalFrames(){
		for(JInternalFrame jif : inFrames){
			getContentPane().add(jif);
			jif.setVisible(false);
		}
		posicionaInternalFrames();
	}
	
	private void posicionaInternalFrames(){
		Integer meioDaTela = largura/2;
		Integer AltMeioDaTela = altura/2;
		Integer meioDaFrame = 0;
		Integer AltmeioDaFrame = 0;
		
		for(JInternalFrame frame : inFrames){
			meioDaFrame = frame.getWidth()/2;
			AltmeioDaFrame = frame.getHeight()/2;
			frame.setLocation(meioDaTela-meioDaFrame, (AltMeioDaTela - AltmeioDaFrame) - 90);
		}
		
	}
	
	private void escondeInternalFrames(){
		posicionaInternalFrames();
		for(JInternalFrame frame : inFrames){
			frame.setVisible(false);
		}
	}
	
	public TelaUsuario() {
		setTitle("");
		setLayout(null);
		
		insereInternalFrames();
		
		criarMenuBar();
		
		setSize(largura, altura);
		setJMenuBar(barra);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void criarMenuBar() {
		barra = new JMenuBar();
		
		jmnDispositivo = new JMenu("Dispositivo");
		barra.add(jmnDispositivo);
		
		jmiCadDispositivo = new JMenuItem("Cadastro");
		jmnDispositivo.add(jmiCadDispositivo);
		jmiCadDispositivo.addActionListener(this);
		
		jmiAltDispositivo = new JMenuItem("Alteração");
		//jmnDispositivo.add(jmiAltDispositivo);
		jmiAltDispositivo.addActionListener(this);
		
		jmiListaDispositivo = new JMenuItem("Listar todos");
		jmnDispositivo.add(jmiListaDispositivo);
		jmiListaDispositivo.addActionListener(this);
		
		jmnEndereco = new JMenu("Endereço");
		barra.add(jmnEndereco);
		
		jmiCadEndereco = new JMenuItem("Cadastro");
		jmnEndereco.add(jmiCadEndereco);
		jmiCadEndereco.addActionListener(this);
		
		jmiAltEndereco = new JMenuItem("Alteração");
		//jmnEndereco.add(jmiAltEndereco);
		jmiAltEndereco.addActionListener(this);
		
		jmiListaEndereco = new JMenuItem("Listar todos");
		jmnEndereco.add(jmiListaEndereco);
		jmiListaEndereco.addActionListener(this);
		
		jmnSetor = new JMenu("Setor");
		barra.add(jmnSetor);
		
		jmiCadSetor = new JMenuItem("Cadastro");
		jmnSetor.add(jmiCadSetor);
		jmiCadSetor.addActionListener(this);
		
		jmiAltSetor = new JMenuItem("Alteração");
		//jmnSetor.add(jmiAltSetor);
		jmiAltSetor.addActionListener(this);
		
		jmiListaSetor = new JMenuItem("Listar todos");
		jmnSetor.add(jmiListaSetor);
		jmiListaSetor.addActionListener(this);
		
		jmnConfig = new JMenu("Configurações");
		barra.add(jmnConfig);
		
		jmiIniciaBanco = new JMenuItem("Inicia configurações do banco");
		jmnConfig.add(jmiIniciaBanco);
		jmiIniciaBanco.addActionListener(this);
		
	}

	public static void main(String[] args) {
		new TelaUsuario();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == jmiCadDispositivo){
			escondeInternalFrames();
			tlCadDispositivo.setVisible(true);
		}
		if(e.getSource() == jmiCadEndereco){
			escondeInternalFrames();
			tlCadEndereco.setVisible(true);
		}
		if(e.getSource() == jmiCadSetor){
			escondeInternalFrames();
			tlCadArea.setVisible(true);
		}
		if(e.getSource() == jmiListaDispositivo){
			escondeInternalFrames();
			tlListaDispositivo.setVisible(true);
		}
		if(e.getSource() == jmiListaEndereco){
			escondeInternalFrames();
			tlListaEndereco.setVisible(true);
		}
		if(e.getSource() == jmiListaSetor){
			escondeInternalFrames();
			tlListaArea.setVisible(true);
		}
		if(e.getSource() == jmiIniciaBanco){
			escondeInternalFrames();
			tlIniciaBanco.setVisible(true);
		}
		
	}
}
