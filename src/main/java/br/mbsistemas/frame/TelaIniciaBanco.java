package br.mbsistemas.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import br.edu.unoesc.exception.DAOException;
import br.mbsistemas.dao.AreaDAO;
import br.mbsistemas.dao.DispositivoDAO;
import br.mbsistemas.dao.EnderecoDAO;
import br.mbsistemas.dao.TipoDispositivoDAO;
import br.mbsistemas.model.Area;
import br.mbsistemas.model.Dispositivo;
import br.mbsistemas.model.Endereco;
import br.mbsistemas.model.TipoDispositivo;

public class TelaIniciaBanco extends JInternalFrame implements ActionListener {
	private JButton jbtEnd, jbtTipo, jbtArea, jbtDisp;
	
	public TelaIniciaBanco() {
		setTitle("Configuração inicial do BD");
		setLayout(null);
		
		jbtEnd = new JButton("1 - Inicia endereço");
		jbtEnd.setVisible(true);
		jbtEnd.setBounds(0, 0, 200, 24);
		jbtEnd.setFocusable(false);
		jbtEnd.addActionListener(this);
		getContentPane().add(jbtEnd);
		
		jbtTipo = new JButton("2 - Inicia tipo");
		jbtTipo.setVisible(true);
		jbtTipo.setBounds(0, 50, 200, 24);
		jbtTipo.setFocusable(false);
		jbtTipo.addActionListener(this);
		getContentPane().add(jbtTipo);
		
		jbtArea = new JButton("3 - Inicia area");
		jbtArea.setVisible(true);
		jbtArea.setBounds(0, 100, 200, 24);
		jbtArea.setFocusable(false);
		jbtArea.addActionListener(this);
		getContentPane().add(jbtArea);
		
		jbtDisp = new JButton("4 - Inicia dispositivo");
		jbtDisp.setVisible(true);
		jbtDisp.setBounds(0, 150, 200, 24);
		jbtDisp.setFocusable(false);
		jbtDisp.addActionListener(this);
		getContentPane().add(jbtDisp);
		
		setSize(215, 213);
		//setLocationRelativeTo(null);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setVisible(true);
		setClosable(true);
	}
	public static void main(String[] args) {
		new TelaIniciaBanco();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == jbtEnd){
			try {
				
			 Endereco end = new Endereco(); 
			 end.setEndereco("192.168.1.1");
			 end.setStatus("L");
			 EnderecoDAO endDao = new EnderecoDAO(); 
			 try {
				endDao.salvar(end);
				JOptionPane.showMessageDialog(null, "Rodar: 1endereco.sql");
			} catch (DAOException e1) {
				JOptionPane.showMessageDialog(null, "Erro: " +e1.getLocalizedMessage());
			}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, e2.getLocalizedMessage());
			}
		}
		
		if(e.getSource() == jbtTipo){
			TipoDispositivo tipo = new TipoDispositivo();
			tipo.setTipo("COMPUTADOR");

			TipoDispositivoDAO tipoDao = new TipoDispositivoDAO();
			try {
				tipoDao.salvar(tipo);
				JOptionPane.showMessageDialog(null, "Rodar: 1tipodispositivo.sql");
			} catch (DAOException e1) {
				JOptionPane.showMessageDialog(null, "Erro: " +e1.getLocalizedMessage());

			}
		}
		
		if(e.getSource() == jbtArea){
			Area area = new Area();
			area.setNome("RAIO-X");
			
			AreaDAO areaDao = new AreaDAO();
			try {
				areaDao.salvar(area);
				JOptionPane.showMessageDialog(null, "Rodar: 1area.sql");
			} catch (DAOException e1) {
				JOptionPane.showMessageDialog(null, "Erro: " +e1.getLocalizedMessage());
			}
		}
		
		if(e.getSource() == jbtDisp){
			EnderecoDAO endDao = new EnderecoDAO();
			Endereco end = endDao.buscar(Endereco.class, 14L);
			end.setStatus("O");
			try {
				endDao.salvar(end);
			} catch (DAOException e2) {
				JOptionPane.showMessageDialog(null, "Erro: " +e2.getLocalizedMessage());
			}
			
			AreaDAO areaDao = new AreaDAO();
			Area area = areaDao.buscar(Area.class, 1L);
			
			TipoDispositivoDAO tpDao = new TipoDispositivoDAO();
			TipoDispositivo tp = tpDao.buscar(TipoDispositivo.class, 1L);
			
			Dispositivo disp = new Dispositivo();
			disp.setNome("RAIOX1");
			disp.setDescricao("");
			disp.setEndereco(end);
			disp.setArea(area);
			disp.setUsuario("Sabrina - Janela");
			disp.setRamal("781");
			disp.setTipoDispositivo(tp);
			
			DispositivoDAO dispDao = new DispositivoDAO();
			try {
				dispDao.salvar(disp);
				JOptionPane.showMessageDialog(null, "Rodar: 1dispositivo.sql");
			} catch (DAOException e1) {
				JOptionPane.showMessageDialog(null, "Erro: " +e1.getLocalizedMessage());
			}
		}
		
	}
}
