package br.mbsistemas.frame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import br.mbsistemas.dao.AreaDAO;
import br.mbsistemas.dao.DispositivoDAO;
import br.mbsistemas.dao.EnderecoDAO;
import br.mbsistemas.model.Area;
import br.mbsistemas.model.Dispositivo;
import br.mbsistemas.model.Endereco;

public class TelaListaArea extends JInternalFrame {
	private DefaultTableModel dtmDispositivos, dtmVinculos;
	private JTextField jtfPesquisa;
	private JPanel jpnPesquisar, jpnAcoes, jpnDetalhes;
	private JLabel jlbTitulo, jlbTituloDetalhes;
	private JTextArea jtaDescDisp;
	private JTextField jtfCodDisp, jtfNomeDisp, jtfUsuDisp, jtfRamalDisp, jtfAreaDisp, jtfIpDisp;
	private JPanel jpnTitulo, jpnTituloDetalhes;
	private JButton jbtPesquisar, jbtExcluir;
	
	public TelaListaArea() {
		setTitle("Lista de setor");
		setLayout(null);
		
		criarTitulo();
		criarPesquisa();
		criarTabela();
		
		jpnDetalhes = new JPanel();
		jpnDetalhes.setBounds(615,40, 460, 409);
		jpnDetalhes.setVisible(true);
		jpnDetalhes.setLayout(null);
		jpnDetalhes.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray));
		getContentPane().add(jpnDetalhes);
		
		jpnTituloDetalhes = new JPanel();
		jpnTituloDetalhes.setBounds(10, 10, 460, 30);
		jpnTituloDetalhes.setVisible(true);
		jpnTituloDetalhes.setLayout(null);
		jpnTituloDetalhes.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray));
		getContentPane().add(jpnDetalhes);
		
		
		jlbTituloDetalhes = new JLabel("VINCULOS DO SETOR SELECIONADO", SwingConstants.CENTER);
		jlbTituloDetalhes.setBounds(5, 5, jpnTituloDetalhes.getWidth()-10, jpnTituloDetalhes.getHeight()-10);
		jlbTituloDetalhes.setOpaque(true);
		jlbTituloDetalhes.setBackground(Color.black);
		jlbTituloDetalhes.setForeground(Color.white);
		jpnDetalhes.add(jlbTituloDetalhes);
		
		
		dtmVinculos = new DefaultTableModel();
		dtmVinculos.addColumn("Nome");
		dtmVinculos.addColumn("Tipo");
		dtmVinculos.addColumn("Setor");
		dtmVinculos.addColumn("Ramal");

		
		JTable tableV = new JTable(dtmVinculos);

		// por padrão, vem sem bordas, então colocamos:
		tableV.setBorder(new LineBorder(Color.black));
		tableV.setGridColor(Color.black);
		tableV.setShowGrid(true);
		
		JScrollPane scrollV = new JScrollPane(); 
		scrollV.getViewport().setBorder(null);
		scrollV.getViewport().add(tableV); 
		scrollV.setBounds(3, 30, 455, 75);
		jpnDetalhes.add(scrollV);
		
		tableV.getColumnModel().getColumn(0).setPreferredWidth(200);
		tableV.getColumnModel().getColumn(1).setPreferredWidth(200);
		tableV.getColumnModel().getColumn(2).setPreferredWidth(100);			
		tableV.getColumnModel().getColumn(3).setPreferredWidth(100);
	
		JLabel jlbTituloDetalhesIp = new JLabel("DETALHES DO SETOR", SwingConstants.CENTER);
		jlbTituloDetalhesIp.setBounds(5, 110, jpnTituloDetalhes.getWidth()-10, jpnTituloDetalhes.getHeight()-10);
		jlbTituloDetalhesIp.setOpaque(true);
		jlbTituloDetalhesIp.setBackground(Color.black);
		jlbTituloDetalhesIp.setForeground(Color.white);
		jpnDetalhes.add(jlbTituloDetalhesIp);
		
		JLabel jlbCodIp = new JLabel("CÓDIGO:", SwingConstants.RIGHT);
		jlbCodIp.setBounds(3, 140, 70, 24);
		jlbCodIp.setVisible(true);
		jpnDetalhes.add(jlbCodIp);
		
		jtfCodDisp = new JTextField();
		jtfCodDisp.setBounds(75, 140, 50, 24);
		jtfCodDisp.setVisible(true);
		jtfCodDisp.setEnabled(false);
		jpnDetalhes.add(jtfCodDisp);
		
		JLabel jlbEndereco = new JLabel("NOME DO SETOR:", SwingConstants.RIGHT);
		jlbEndereco.setBounds(110, 140, 120, 24);
		jlbEndereco.setVisible(true);
		jpnDetalhes.add(jlbEndereco);
		
		jtfNomeDisp = new JTextField();
		jtfNomeDisp.setBounds(240, 140, 210, 24);
		jtfNomeDisp.setVisible(true);
		jtfNomeDisp.setEnabled(false);
		jpnDetalhes.add(jtfNomeDisp);
		
		jbtExcluir = new JButton("Excluir setor");
		jbtExcluir.setBounds(5, 380, 130, 23);
		jbtExcluir.setVisible(true);
		jbtExcluir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			

		});
		jpnDetalhes.add(jbtExcluir);
		
		
		jpnAcoes = new JPanel();
		jpnAcoes.setBounds(10,500, 864, 50);
		jpnAcoes.setVisible(false);
		jpnAcoes.setLayout(null);
		jpnAcoes.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray));
		getContentPane().add(jpnAcoes);
				
		setSize(1100,500);
		setVisible(true);
		setClosable(true);
		setResizable(false);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		//setLocationRelativeTo(null);
	}
	
	private void criarTitulo() {
		jpnTitulo = new JPanel();
		jpnTitulo.setBounds(10, 10, 1064, 30);
		jpnTitulo.setVisible(false);
		jpnTitulo.setLayout(null);
		jpnTitulo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray));
		getContentPane().add(jpnTitulo);
		
		
		jlbTitulo = new JLabel("LISTA DE SETORES", SwingConstants.CENTER);
		jlbTitulo.setBounds(5, 5, jpnTitulo.getWidth()-10, jpnTitulo.getHeight()-10);
		jlbTitulo.setOpaque(true);
		jlbTitulo.setBackground(Color.black);
		jlbTitulo.setForeground(Color.white);
		jpnTitulo.add(jlbTitulo);
		
		
	}

	private void criarPesquisa() {
		jpnPesquisar = new JPanel();
		jpnPesquisar.setBounds(10, 10, 1064, 30);
		jpnPesquisar.setVisible(true);
		jpnPesquisar.setLayout(null);
		jpnPesquisar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray));
		getContentPane().add(jpnPesquisar);
		
		JLabel jlbPesquisar = new JLabel("DESCRIÇÃO:", SwingConstants.RIGHT);
		jlbPesquisar.setBounds(0, 3, 95, 24);
		jlbPesquisar.setVisible(true);
		jpnPesquisar.add(jlbPesquisar);
		
		jtfPesquisa = new JTextField();
		jtfPesquisa.setBounds(100, 3, 860, 24);
		jtfPesquisa.setVisible(true);
		jtfPesquisa.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					if(jtfPesquisa.getText() != null){
					alimentarTabelaPesquisa(jtfPesquisa.getText().toUpperCase());
					}
					
				}
			}
		});
		jpnPesquisar.add(jtfPesquisa);
		
		jbtPesquisar = new JButton("Pesquisar");
		jbtPesquisar.setBounds(960, 3, 100, 23);
		jbtPesquisar.setVisible(true);
		jbtPesquisar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				if(jtfPesquisa.getText() != null){
					alimentarTabelaPesquisa(jtfPesquisa.getText().toUpperCase());
				}
			}
		});
		jpnPesquisar.add(jbtPesquisar);
		
		
	}

	private void criarTabela() {
		dtmDispositivos = new DefaultTableModel();
		dtmDispositivos.addColumn("#");
		dtmDispositivos.addColumn("Setor");
		dtmDispositivos.addColumn("Total");
		
		JTable table = new JTable(dtmDispositivos);

		// por padrão, vem sem bordas, então colocamos:
		table.setBorder(new LineBorder(Color.black));
		table.setGridColor(Color.black);
		table.setShowGrid(true);
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				dtmVinculos.setRowCount(0);
				AreaDAO areaDao = new AreaDAO();
				Area area = areaDao.buscar(Area.class, Long.valueOf(table.getValueAt(table.getSelectedRow(), 0).toString()));
				
				for(Dispositivo disp : area.getDispositivos()){
					dtmVinculos.addRow(new String[]{disp.getNome(), disp.getTipoDispositivo().getTipo(), disp.getArea().getNome(), disp.getRamal()});
				}

				jtfCodDisp.setText(area.getCodigo().toString());
				jtfNomeDisp.setText(area.getNome());
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JScrollPane scroll = new JScrollPane(); 
		scroll.getViewport().setBorder(null);
		scroll.getViewport().add(table); 
		scroll.setBounds(10, 40, 600, 410);
		getContentPane().add(scroll);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(130);
		
		new Thread(){
			public void run(){
				alimentarTabela();
			}
		}.start();		
	}

	private void alimentarTabela() {
		AreaDAO areaDao = new AreaDAO();
		ArrayList<Area> areas = (ArrayList<Area>) areaDao.listar(Area.class);		
		
		for(Area area : areas){
			dtmDispositivos.addRow(new String[]{area.getCodigo().toString(), area.getNome(), String.valueOf(area.getDispositivos().size())});
		}
		
	}
	
	private void alimentarTabelaPesquisa(String arg) {
		dtmDispositivos.setRowCount(0);
		
		jbtPesquisar.setEnabled(false);
		jtfPesquisa.setEnabled(false);
		
		
		arg = arg.toUpperCase();
		AreaDAO areaDao = new AreaDAO();
		ArrayList<Area> areas = (ArrayList<Area>) areaDao.listar(Area.class);		
		for(Area area : areas){
			if(area.getNome().toUpperCase().contains(arg)){				
				dtmDispositivos.addRow(new String[]{area.getCodigo().toString(), area.getNome(), String.valueOf(area.getDispositivos().size())});
			}
		}
		

		jtfPesquisa.setEnabled(true);
		jbtPesquisar.setEnabled(true);
		
	}

	
}
