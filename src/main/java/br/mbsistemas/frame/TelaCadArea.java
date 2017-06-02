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

public class TelaCadArea extends JInternalFrame implements ActionListener{
	private JPanel jpnTitulo, jpnCad, jpnAcoes;
	private JLabel jlbTitulo, jlbArea;
	private JTextField jtfArea;
	private JButton jbtLimpar, jbtCancelar, jbtSalvar;
	private FabricaJPanel fabPanel = new FabricaJPanel();
	private FabricaJLabel fabLabel = new FabricaJLabel();
	private FabricaJButton fabButton = new FabricaJButton();
	private FabricaJText fabText = new FabricaJText();
	private AreaDAO areaDao = new AreaDAO();
	
	public TelaCadArea() {
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
		
		jlbTitulo = fabLabel.criaLabelTitulo(jlbTitulo, "CADASTRO DE NOVO SETOR", 0, 0, 600, 24);
		jpnTitulo.add(jlbTitulo);
		
		jpnCad = fabPanel.criaPanelTitulo(jpnCad, 5, 35, 583, 160);
		getContentPane().add(jpnCad);		
	}//FECHA CONSTRUTOR PAINEL TITULO
	
	private void construirPainelCadastro(){
		//LINHA 1
		jlbArea = fabLabel.criaLabelForm(jlbArea, "Nome do setor:", 10, 5, 120, 24);
		jpnCad.add(jlbArea);
		
		jtfArea = fabText.criaTextFieldForm(jtfArea, 110, 5, 460, 24);
		jpnCad.add(jtfArea);		
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
		new TelaCadArea();
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
			jtfArea.setText("");
			
			JOptionPane.showMessageDialog(this, "Todos os campos da tela foram limpados!", "Retorno ao usuário", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Ocorreu um erro ao limpar os campos. \nErro da aplicação: " +e.getMessage()+"\nRecomendado: Execute o processo que gerou o erro novamente.", "Retorno ao usuário", JOptionPane.ERROR_MESSAGE);
		}
		
	}//FECHA ACTION LIMPAR
	

	private void actionCancelar(){
		this.dispose();
	}//FECHA ACTION CANCELAR
	
	private void actionSalvar(){
		if(jtfArea.getText().equals("")){
			jtfArea.setText(" ");
		}
				
		Boolean cadastra = false;
		String motivo = "\n";
		
		if(areaDao.buscarArea(jtfArea.getText()) == null){
			cadastra = true;
		}else {
			cadastra = false;
			motivo += "  * Setor com mesmo nome já cadastrado.\n";
		}
		
		if(cadastra){
			try {
				Area area = new Area();
				area.setNome(jtfArea.getText());
				areaDao.salvar(area);

				
				JOptionPane.showMessageDialog(this, "O setor foi cadastrado!", "Retorno ao usuário", JOptionPane.INFORMATION_MESSAGE);	
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "O setor não foi cadastrado. \nMotivo: "+e.getMessage(), "Retorno ao usuário", JOptionPane.ERROR_MESSAGE);
			}
			
		}else {
			JOptionPane.showMessageDialog(this, "O cadastrado foi bloqueado.\nMotivos: "+ motivo, "Retorno ao usuário", JOptionPane.ERROR_MESSAGE);
		}
		
		
		
	}//FECHA ACTION SALVAR
}
