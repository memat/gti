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

public class TelaCadEndereco extends JInternalFrame implements ActionListener{
	private JPanel jpnTitulo, jpnCad, jpnAcoes;
	private JLabel jlbTitulo, jlbEndereco, jlbStatus;
	private JTextField jtfEndereco;
	private JComboBox<String> jcbStatus;
	private JButton jbtLimpar, jbtCancelar, jbtSalvar;
	private FabricaJPanel fabPanel = new FabricaJPanel();
	private FabricaJLabel fabLabel = new FabricaJLabel();
	private FabricaJButton fabButton = new FabricaJButton();
	private FabricaJText fabText = new FabricaJText();
	private TipoDispositivoDAO tipoDispDao = new TipoDispositivoDAO();
	private AreaDAO areaDao = new AreaDAO();
	
	public TelaCadEndereco() {
		setTitle("");
		setLayout(null);
		
		construirPainelTitulo();
		construirPainelCadastro();
		construirPainelAcoes();
				
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
		
		jlbTitulo = fabLabel.criaLabelTitulo(jlbTitulo, "CADASTRO DE NOVO ENDEREÇO IP", 0, 0, 600, 24);
		jpnTitulo.add(jlbTitulo);
		
		jpnCad = fabPanel.criaPanelTitulo(jpnCad, 5, 35, 583, 160);
		getContentPane().add(jpnCad);		
	}//FECHA CONSTRUTOR PAINEL TITULO
	
	private void construirPainelCadastro(){
		//LINHA 1
		jlbEndereco = fabLabel.criaLabelForm(jlbEndereco, "Endereço IP:", 10, 5, 120, 24);
		jpnCad.add(jlbEndereco);
		
		jtfEndereco = fabText.criaTextFieldForm(jtfEndereco, 90, 5, 250, 24);
		jpnCad.add(jtfEndereco);
			
		jlbStatus = fabLabel.criaLabelForm(jlbStatus, "Status:", 350, 5, 120, 24);
		jpnCad.add(jlbStatus);
				
		jcbStatus = new JComboBox<String>();
		jcbStatus.setBounds(395, 5, 185, 24);
		jcbStatus.setVisible(true);
		jpnCad.add(jcbStatus);	
		
		jcbStatus.addItem("Livre");
		jcbStatus.addItem("Reservado");
		
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
	
	public static void main(String[] args) {
		new TelaCadEndereco();
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
			jtfEndereco.setText("");
			jcbStatus.setSelectedIndex(0);
			
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
				
		Boolean cadastra = false;
		String motivo = "\n";
		
		if(endDao.buscarEndereco(jtfEndereco.getText()) == null){
			cadastra = true;
			//motivo += "  * Endereço de IP inserido não está cadastrado.\n";
		}else {
			cadastra = false;
			motivo += "  * Endereço de IP inserido já está cadastrado.\n";
		}
		
		if(cadastra){
			try {
				Endereco end = new Endereco();
				end.setEndereco(jtfEndereco.getText());
				end.setStatus(jcbStatus.getSelectedItem().toString().substring(0, 1));
				
				endDao.salvar(end);
				JOptionPane.showMessageDialog(this, "O endereço de IP foi cadastrado", "Retorno ao usuário", JOptionPane.INFORMATION_MESSAGE);	
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "O endereço de IP não foi cadastrado. \nMotivo: "+e.getMessage(), "Retorno ao usuário", JOptionPane.ERROR_MESSAGE);
			}
			
		}else {
			JOptionPane.showMessageDialog(this, "O cadastrado foi bloqueado.\nMotivos: "+ motivo, "Retorno ao usuário", JOptionPane.ERROR_MESSAGE);
		}
		
		
		
	}//FECHA ACTION SALVAR
}
