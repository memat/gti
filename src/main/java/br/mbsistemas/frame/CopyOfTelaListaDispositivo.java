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

import br.mbsistemas.dao.DispositivoDAO;
import br.mbsistemas.model.Dispositivo;

public class CopyOfTelaListaDispositivo extends JFrame {
	private DefaultTableModel dtmDispositivos;
	private JTextField jtfPesquisa;
	private JPanel jpnPesquisar, jpnAcoes, jpnDetalhes;
	private JLabel jlbTitulo, jlbTituloDetalhes;
	private JLabel jlbCodDisp, jlbNomeDisp, jlbUsuDisp, jlbRamalDisp, jlbAreaDisp, jlbIpDisp;
	private JTextArea jtaDescDisp;
	private JTextField jtfCodDisp, jtfNomeDisp, jtfUsuDisp, jtfRamalDisp, jtfAreaDisp, jtfIpDisp, jtfPingDisp;
	private JPanel jpnTitulo, jpnTituloDetalhes;
	private JButton jbtPesquisar;
	private JLabel jlbPing1, jlbPing2, jlbPing3;
	
	public CopyOfTelaListaDispositivo() {
		setTitle("Lista de dispositivo");
		setLayout(null);
		
		criarTitulo();
		criarPesquisa();
		criarTabela();
		
		jpnDetalhes = new JPanel();
		jpnDetalhes.setBounds(615,90, 460, 409);
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
		
		
		jlbTituloDetalhes = new JLabel("DETALHES DO DISPOSITIVO SELECIONADO", SwingConstants.CENTER);
		jlbTituloDetalhes.setBounds(5, 5, jpnTituloDetalhes.getWidth()-10, jpnTituloDetalhes.getHeight()-10);
		jlbTituloDetalhes.setOpaque(true);
		jlbTituloDetalhes.setBackground(Color.black);
		jlbTituloDetalhes.setForeground(Color.white);
		jpnDetalhes.add(jlbTituloDetalhes);
		
		jlbCodDisp = new JLabel("CÓDIGO:", SwingConstants.RIGHT);
		jlbCodDisp.setBounds(3, 30, 70, 24);
		jlbCodDisp.setVisible(true);
		jpnDetalhes.add(jlbCodDisp);
		
		jtfCodDisp = new JTextField();
		jtfCodDisp.setBounds(75, 30, 50, 24);
		jtfCodDisp.setVisible(true);
		jpnDetalhes.add(jtfCodDisp);
		
		jlbNomeDisp = new JLabel("NOME:", SwingConstants.RIGHT);
		jlbNomeDisp.setBounds(100, 30, 70, 24);
		jlbNomeDisp.setVisible(true);
		jpnDetalhes.add(jlbNomeDisp);
		
		jtfNomeDisp = new JTextField();
		jtfNomeDisp.setBounds(175, 30, 260, 24);
		jtfNomeDisp.setVisible(true);
		jpnDetalhes.add(jtfNomeDisp);
		
		jlbCodDisp = new JLabel("DESCRIÇÃO:", SwingConstants.RIGHT);
		jlbCodDisp.setBounds(3, 55, 70, 24);
		jlbCodDisp.setVisible(true);
		jpnDetalhes.add(jlbCodDisp);
		
		jtaDescDisp = new JTextArea();
		jtaDescDisp.setLineWrap(true);
		JScrollPane scrollDesc = new JScrollPane(jtaDescDisp);
		scrollDesc.setBounds(75, 55, 360, 48);
		scrollDesc.setVisible(true);
		jpnDetalhes.add(scrollDesc);
		
		jlbUsuDisp = new JLabel("USUÁRIO:", SwingConstants.RIGHT);
		jlbUsuDisp.setBounds(3, 104, 70, 24);
		jlbUsuDisp.setVisible(true);
		jpnDetalhes.add(jlbUsuDisp);
		
		jtfUsuDisp = new JTextField();
		jtfUsuDisp.setBounds(75, 104, 200, 24);
		jtfUsuDisp.setVisible(true);
		jpnDetalhes.add(jtfUsuDisp);
		
		jlbRamalDisp = new JLabel("RAMAL:", SwingConstants.RIGHT);
		jlbRamalDisp.setBounds(255, 104, 70, 24);
		jlbRamalDisp.setVisible(true);
		jpnDetalhes.add(jlbRamalDisp);
		
		jtfRamalDisp = new JTextField();
		jtfRamalDisp.setBounds(328, 104, 107, 24);
		jtfRamalDisp.setVisible(true);
		jpnDetalhes.add(jtfRamalDisp);
		
		jlbAreaDisp = new JLabel("SETOR:", SwingConstants.RIGHT);
		jlbAreaDisp.setBounds(3, 128, 70, 24);
		jlbAreaDisp.setVisible(true);
		jpnDetalhes.add(jlbAreaDisp);
		
		jtfAreaDisp = new JTextField();
		jtfAreaDisp.setBounds(75, 128, 360, 24);
		jtfAreaDisp.setVisible(true);
		jpnDetalhes.add(jtfAreaDisp);
		
		jlbIpDisp = new JLabel("IP:", SwingConstants.RIGHT);
		jlbIpDisp.setBounds(3, 160, 70, 24);
		jlbIpDisp.setVisible(true);
		jpnDetalhes.add(jlbIpDisp);
		
		jtfIpDisp = new JTextField();
		jtfIpDisp.setBounds(75, 160, 95, 24);
		jtfIpDisp.setVisible(true);
		jpnDetalhes.add(jtfIpDisp);
		
		JButton jbtPing = new JButton("Pingar");
		jbtPing.setBounds(171, 160, 90, 23);
		jbtPing.setVisible(true);
		jbtPing.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Thread(){
					public void run(){
						try{
							
							jlbPing1.setText("");
							jlbPing1.setBackground(Color.white);
							jlbPing1.setForeground(Color.black);
							//PING 1
							Process pingExe1 = Runtime.getRuntime().exec("ping -n 1 " + jtfIpDisp.getText());
							Scanner in1 = new Scanner((pingExe1.getInputStream()));
							
							String ping1 = "";
							
							while ((in1.hasNext())) {
								ping1 = in1.nextLine() + "\n";
							}

							
							
							if(ping1.contains("ms")){
								jlbPing1.setText("RETORNOU");
								jlbPing1.setBackground(Color.green);
								jlbPing1.setForeground(Color.black);
							}else{
								jlbPing1.setText("NÃO RECEBEU RETORNO");
								jlbPing1.setBackground(Color.red);
								jlbPing1.setForeground(Color.white);
							}
							
						}catch (Exception e){
							e.printStackTrace();
						}
					}
				}.start();
				
			}
		});
		jpnDetalhes.add(jbtPing);
		
		
		jlbPing1 = new JLabel("", SwingConstants.CENTER);
		jlbPing1.setOpaque(true);
		jlbPing1.setBackground(Color.white);
		jlbPing1.setBounds(265, 160, 169, 24);
		jlbPing1.setVisible(true);
		jpnDetalhes.add(jlbPing1);
		
		jpnAcoes = new JPanel();
		jpnAcoes.setBounds(10,500, 864, 50);
		jpnAcoes.setVisible(false);
		jpnAcoes.setLayout(null);
		jpnAcoes.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray));
		getContentPane().add(jpnAcoes);
				
		setSize(1100,550);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	private void criarTitulo() {
		jpnTitulo = new JPanel();
		jpnTitulo.setBounds(10, 10, 1064, 30);
		jpnTitulo.setVisible(true);
		jpnTitulo.setLayout(null);
		jpnTitulo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray));
		getContentPane().add(jpnTitulo);
		
		
		jlbTitulo = new JLabel("LISTA DE DISPOSITIVOS", SwingConstants.CENTER);
		jlbTitulo.setBounds(5, 5, jpnTitulo.getWidth()-10, jpnTitulo.getHeight()-10);
		jlbTitulo.setOpaque(true);
		jlbTitulo.setBackground(Color.black);
		jlbTitulo.setForeground(Color.white);
		jpnTitulo.add(jlbTitulo);
		
		
	}

	private void criarPesquisa() {
		jpnPesquisar = new JPanel();
		jpnPesquisar.setBounds(10, 50, 1064, 30);
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
		dtmDispositivos.addColumn("Nome");
		dtmDispositivos.addColumn("Endereço de IP");
		dtmDispositivos.addColumn("Setor");
		dtmDispositivos.addColumn("Ramal");
		dtmDispositivos.addColumn("Usuário");
		
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
				DispositivoDAO dispDao = new DispositivoDAO();
				Dispositivo disp = dispDao.buscar(Dispositivo.class, Long.valueOf(table.getValueAt(table.getSelectedRow(), 0).toString()));
				jtfCodDisp.setText(disp.getCodigo().toString());
				jtfNomeDisp.setText(disp.getNome());
				jtaDescDisp.setText(disp.getDescricao());
				jtfUsuDisp.setText(disp.getUsuario());
				jtfRamalDisp.setText(disp.getRamal());
				jtfAreaDisp.setText(disp.getArea().getNome());
				jtfIpDisp.setText(disp.getEndereco().getEndereco());
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
		scroll.setBounds(10, 90, 600, 410);
		getContentPane().add(scroll);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(130);
		table.getColumnModel().getColumn(3).setPreferredWidth(200);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		
		new Thread(){
			public void run(){
				alimentarTabela();
			}
		}.start();		
	}

	private void alimentarTabela() {
		DispositivoDAO dispDao = new DispositivoDAO();
		ArrayList<Dispositivo> dispositivos = (ArrayList<Dispositivo>) dispDao.listar(Dispositivo.class);		
		for(Dispositivo disp : dispositivos){
			dtmDispositivos.addRow(new String[]{disp.getCodigo().toString(), disp.getNome(), disp.getEndereco().getEndereco(), disp.getArea().getNome(), disp.getRamal(), disp.getUsuario()});
		}
		
	}
	
	private void alimentarTabelaPesquisa(String arg) {
		dtmDispositivos.setRowCount(0);
		
		jbtPesquisar.setEnabled(false);
		jtfPesquisa.setEnabled(false);
		
		
		arg = arg.toUpperCase();
		DispositivoDAO dispDao = new DispositivoDAO();
		ArrayList<Dispositivo> dispositivos = (ArrayList<Dispositivo>) dispDao.listar(Dispositivo.class);		
		for(Dispositivo disp : dispositivos){
			if(
					(disp.getNome().toUpperCase().contains(arg)) || 
					(disp.getArea().getNome().toUpperCase().contains(arg)) ||
					(disp.getDescricao().toUpperCase().contains(arg)) ||
					(disp.getRamal().toUpperCase().contains(arg)) ||
					(disp.getUsuario().toUpperCase().contains(arg)) ||
					(disp.getEndereco().getEndereco().toUpperCase().contains(arg))
			  ){
			dtmDispositivos.addRow(new String[]{disp.getCodigo().toString(), disp.getNome(), disp.getEndereco().getEndereco(), disp.getArea().getNome(), disp.getRamal(), disp.getUsuario()});
			}
		}
		

		jtfPesquisa.setEnabled(true);
		jbtPesquisar.setEnabled(true);
		
	}

	public static void main(String[] args) {
		new CopyOfTelaListaDispositivo();
	}
	
}
