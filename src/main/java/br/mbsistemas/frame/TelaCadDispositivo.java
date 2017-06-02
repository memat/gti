package br.mbsistemas.frame;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import fabrica.FabricaJButton;
import fabrica.FabricaJLabel;
import fabrica.FabricaJPanel;
import fabrica.FabricaJText;
import br.mbsistemas.dao.AreaDAO;
import br.mbsistemas.dao.DispositivoDAO;
import br.mbsistemas.dao.EnderecoDAO;
import br.mbsistemas.dao.TipoDispositivoDAO;
import br.mbsistemas.model.Area;
import br.mbsistemas.model.Dispositivo;
import br.mbsistemas.model.Endereco;
import br.mbsistemas.model.TipoDispositivo;

public class TelaCadDispositivo extends JInternalFrame implements ActionListener{
	private JPanel jpnTitulo, jpnCad, jpnAcoes;
	private JLabel jlbTitulo, jlbNome, jlbDescricao, jlbTipo, jlbUsuario, jlbSetor, jlbRamal, jlbEndereco;
	private JTextField jtfNome, jtfUsuario, jtfRamal, jtfEndereco;
	private JTextArea jtaDescricao;
	private JComboBox<String> jcbArea;
	private JComboBox<String> jcbTipo;
	private JButton jbtLimpar, jbtCancelar, jbtSalvar;
	private FabricaJPanel fabPanel = new FabricaJPanel();
	private FabricaJLabel fabLabel = new FabricaJLabel();
	private FabricaJButton fabButton = new FabricaJButton();
	private FabricaJText fabText = new FabricaJText();
	private TipoDispositivoDAO tipoDispDao = new TipoDispositivoDAO();
	private AreaDAO areaDao = new AreaDAO();
	
	public TelaCadDispositivo() {
		setTitle("");
		setLayout(null);
		
		construirPainelTitulo();
		construirPainelCadastro();
		construirPainelAcoes();
		
		alimentaCombosParalelo();
		
		setResizable(false);
		setVisible(true);
		setClosable(true);
		setSize(600, 270);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		//setLocationRelativeTo(null);
	}//FECHA CONSTRUTOR DA TELA
	
	private void construirPainelTitulo(){
		jpnTitulo = fabPanel.criaPanelTitulo(jpnTitulo, 5, 5, 583, 25);
		getContentPane().add(jpnTitulo);
		
		jlbTitulo = fabLabel.criaLabelTitulo(jlbTitulo, "CADASTRO DE NOVO DISPOSITIVO", 0, 0, 600, 24);
		jpnTitulo.add(jlbTitulo);
		
		jpnCad = fabPanel.criaPanelTitulo(jpnCad, 5, 35, 583, 160);
		getContentPane().add(jpnCad);		
	}//FECHA CONSTRUTOR PAINEL TITULO
	
	private void construirPainelCadastro(){
		//LINHA 1
		jlbNome = fabLabel.criaLabelForm(jlbNome, "Nome do dispositivo:", 10, 5, 120, 24);
		jpnCad.add(jlbNome);
		
		jtfNome = fabText.criaTextFieldForm(jtfNome, 135, 5, 250, 24);
		jpnCad.add(jtfNome);
			
		jlbTipo = fabLabel.criaLabelForm(jlbTipo, "Tipo:", 393, 5, 120, 24);
		jpnCad.add(jlbTipo);
				
		jcbTipo = new JComboBox<String>();
		jcbTipo.setBounds(427, 5, 150, 24);
		jcbTipo.setVisible(true);
		jcbTipo.setEnabled(false);
		jpnCad.add(jcbTipo);
										
		//LINHA 2 
		jlbDescricao = fabLabel.criaLabelForm(jlbDescricao, "Descrição:", 10, 35, 120, 24);
		jpnCad.add(jlbDescricao);
		
		jtaDescricao = fabText.criaTextAreaForm(jtaDescricao, 135, 35, 444, 48);
		JScrollPane jspDesc = new JScrollPane(jtaDescricao);
		jspDesc.setBounds(jtaDescricao.getX(), jtaDescricao.getY(), jtaDescricao.getWidth(), jtaDescricao.getHeight());
		jpnCad.add(jspDesc);
			
		//LINHA 3
		jlbEndereco = fabLabel.criaLabelForm(jlbEndereco, "Endereço de IP:", 10, 90, 120, 24);
		jpnCad.add(jlbEndereco);
		
		jtfEndereco = fabText.criaTextFieldForm(jtfEndereco, 135, 90, 100, 24);
		jpnCad.add(jtfEndereco);
			
		jlbUsuario = fabLabel.criaLabelForm(jlbUsuario, "Usuário(s) frequente(s):", 245, 90, 150, 24);
		jpnCad.add(jlbUsuario);
				
		jtfUsuario = fabText.criaTextFieldForm(jtfUsuario, 390, 90, 190, 24);
		jpnCad.add(jtfUsuario);
				
		//LINHA 4
		jlbSetor = fabLabel.criaLabelForm(jlbSetor, "Setor:", 10, 120, 120, 24);
		jpnCad.add(jlbSetor);
				
		jcbArea = new JComboBox<String>();
		jcbArea.setBounds(135, 120, 255, 24);
		jcbArea.setVisible(true);
		jcbArea.setEnabled(false);
		jpnCad.add(jcbArea);
				
		jlbRamal = fabLabel.criaLabelForm(jlbRamal, "Ramal(is):", 400, 120, 100, 24);
		jpnCad.add(jlbRamal);
			
		jtfRamal = fabText.criaTextFieldForm(jtfRamal, 465, 120, 114, 24);
		jpnCad.add(jtfRamal);
	}//FECHA CONSTRUTOR PAINEL CADASTRO
	
	private void construirPainelAcoes(){
		jpnAcoes = fabPanel.criaPanelTitulo(jpnAcoes, 5, jpnCad.getY()+jpnCad.getHeight()+5, 583, 35);
		getContentPane().add(jpnAcoes);
		
		jbtLimpar = fabButton.criaButtonAcao(jbtLimpar, "Limpar", 5, 5, 100, 24);
		jbtLimpar.addActionListener(this);
		jpnAcoes.add(jbtLimpar);
		
		jbtCancelar = fabButton.criaButtonAcao(jbtCancelar, "Cancelar", 105, 5, 100, 24);
		jbtCancelar.addActionListener(this);
		jpnAcoes.add(jbtCancelar);
		
		jbtSalvar = fabButton.criaButtonAcao(jbtSalvar, "Salvar", 478, 5, 100, 24);
		jbtSalvar.addActionListener(this);
		jpnAcoes.add(jbtSalvar);		
	}//FECHA CONSTRUTOR PAINEL ACOES
	
	private void alimentaCombosParalelo(){
		new Thread(){
			public void run(){
				for(TipoDispositivo tipo : tipoDispDao.listar(TipoDispositivo.class)){
					jcbTipo.addItem(tipo.getTipo());
				}
				jcbTipo.setEnabled(true);
				for(Area area : areaDao.listar(Area.class)){
					jcbArea.addItem(area.getNome());
				}
				jcbArea.setEnabled(true);
			}
		}.start();
	}//FECHA THREAD QUE ALIMENTA COMBO BOX
	
	public static void main(String[] args) {
		new TelaCadDispositivo();
	}//FECHA MAIN 
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//SE O EVENTO FOR JBTLIMPAR
		if(e.getSource() == jbtLimpar){
			actionLimpar();
		}
		//FECHA EVENTO JBTLIMPAR
		//SE O EVENTO FOR JBTCANCELAR
		if(e.getSource() == jbtCancelar){
			actionCancelar();
		}
		//FECHA EVENTO JBTCANCELAR
		//SE O EVENTO FOR JBTSALVAR
		if(e.getSource() == jbtSalvar){
			actionSalvar();
		}
		//FECHA EVENTO JBTSALVAR
		
	}//FECHA ACTION PERFORMED
	
	private void actionLimpar(){
		try {
			jtfNome.setText("");
			jcbTipo.setSelectedIndex(0);
			jtaDescricao.setText("");
			jtfEndereco.setText("");
			jtfUsuario.setText("");
			jcbArea.setSelectedIndex(0);
			jtfRamal.setText("");
			
			JOptionPane.showMessageDialog(this, "Todos os campos da tela foram limpados!", "Retorno ao usuário", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Ocorreu um erro ao limpar os campos. \nErro da aplicação: " +e.getMessage()+"\nRecomendado: Execute o processo que gerou o erro novamente.", "Retorno ao usuário", JOptionPane.ERROR_MESSAGE);
		}
		
	}//FECHA ACTION LIMPAR
	

	private void actionCancelar(){
		this.dispose();
	}//FECHA ACTION CANCELAR
	
	private void actionSalvar(){
		if(jtfEndereco.getText().equals("")){
			jtfEndereco.setText(" ");
		}
		
		
		EnderecoDAO endDao = new EnderecoDAO();
				
		Boolean cadastra = true;
		String motivo = "\n";
		
		if(jtfNome.getText().equals("")){
			cadastra = false;
			motivo += "  * Nome do dispositivo não ser nulo.\n";
		}
		if(endDao.buscarEndereco(jtfEndereco.getText()) == null){
			cadastra = false;
			motivo += "  * Endereço de IP inserido não está cadastrado.\n";
		}else {
			Endereco end = endDao.buscarEndereco(jtfEndereco.getText());
			if(end.getStatus().equals("O")){
				cadastra = false;
				motivo += "  * Endereço de IP escolhido já é usado por outro dispositivo.\n";
			}
		}
		
		if(cadastra){
			try {
				Dispositivo dispositivo = new Dispositivo();
				dispositivo.setNome(jtfNome.getText());
				dispositivo.setTipoDispositivo(tipoDispDao.buscar(TipoDispositivo.class, Long.valueOf(jcbTipo.getSelectedIndex()+1)));
				dispositivo.setDescricao(jtaDescricao.getText());
				dispositivo.setEndereco(endDao.buscarEndereco(jtfEndereco.getText()));
				dispositivo.setUsuario(jtfUsuario.getText());
				dispositivo.setArea(areaDao.buscar(Area.class, Long.valueOf(jcbArea.getSelectedIndex()+1)));
				dispositivo.setRamal(jtfRamal.getText());
				
				DispositivoDAO dispDao = new DispositivoDAO();
				dispDao.salvar(dispositivo);
				
				Endereco endereco = dispositivo.getEndereco();
				endereco.setStatus("O");
				endDao.salvar(endereco);
				
				JOptionPane.showMessageDialog(this, "O dispositivo foi cadastrado", "Retorno ao usuário", JOptionPane.INFORMATION_MESSAGE);	
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "O dispositivo não foi cadastrado. \nMotivo: "+e.getMessage(), "Retorno ao usuário", JOptionPane.ERROR_MESSAGE);
			}
			
		}else {
			JOptionPane.showMessageDialog(this, "O cadastrado foi bloqueado.\nMotivos: "+ motivo, "Retorno ao usuário", JOptionPane.ERROR_MESSAGE);
		}
		
		/*
		Dispositivo dispositivo = new Dispositivo();
		dispositivo.setNome(jtfNome.getText());
		dispositivo.setTipoDispositivo(tipoDispDao.buscar(TipoDispositivo.class, Long.valueOf(jcbTipo.getSelectedIndex()+1)));
		dispositivo.setDescricao(jtaDescricao.getText());
		dispositivo.setEndereco(endDao.buscarEndereco(jtfEndereco.getText()));
		dispositivo.setUsuario(jtfUsuario.getText());
		dispositivo.setArea(areaDao.buscar(Area.class, Long.valueOf(jcbArea.getSelectedIndex()+1)));
		dispositivo.setRamal(jtfRamal.getText());*/
		
		
		
		/*System.out.println("Nome: " + dispositivo.getNome());
		System.out.println("Tipo: " + dispositivo.getTipoDispositivo().getTipo());
		System.out.println("Descrição: " + dispositivo.getDescricao());
		System.out.println("Endereço de IP: " + dispositivo.getEndereco().getEndereco());
		System.out.println("Usuário: " + dispositivo.getUsuario());
		System.out.println("Setor: " + dispositivo.getArea().getNome());
		System.out.println("Ramal: " + dispositivo.getRamal());*/
		
	}//FECHA ACTION SALVAR
}
