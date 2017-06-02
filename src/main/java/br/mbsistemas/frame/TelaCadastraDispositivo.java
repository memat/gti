package br.mbsistemas.frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import br.mbsistemas.dao.DispositivoDAO;
import br.mbsistemas.model.Dispositivo;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JTable;

import com.jgoodies.forms.layout.FormSpecs;

import javax.swing.JButton;

public class TelaCadastraDispositivo extends JFrame {

	private JPanel contentPane;
	private JTextField jtfNome;
	private JTextField jtfUsuario;
	private JTextField jtfRamal;
	private DefaultTableModel dtmDispositivos;
	private JTable table;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastraDispositivo frame = new TelaCadastraDispositivo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaCadastraDispositivo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 768, 449);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel jpnTitulo = new JPanel();
		jpnTitulo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.LIGHT_GRAY));
		contentPane.add(jpnTitulo, BorderLayout.NORTH);
		
		JLabel jlbTitulo = new JLabel("CADASTRO DE NOVO DISPOSITIVO");
		jpnTitulo.add(jlbTitulo);
		
		JPanel jpnAcoes = new JPanel();
		contentPane.add(jpnAcoes, BorderLayout.SOUTH);
		
		JButton btnLimparTela = new JButton("Limpar tela");
		
		JButton btnSalvar = new JButton("Salvar");
		
		JButton btnCancelar = new JButton("Cancelar");
		GroupLayout gl_jpnAcoes = new GroupLayout(jpnAcoes);
		gl_jpnAcoes.setHorizontalGroup(
			gl_jpnAcoes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpnAcoes.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnLimparTela, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnCancelar)
					.addPreferredGap(ComponentPlacement.RELATED, 479, Short.MAX_VALUE)
					.addComponent(btnSalvar)
					.addContainerGap())
		);
		gl_jpnAcoes.setVerticalGroup(
			gl_jpnAcoes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpnAcoes.createSequentialGroup()
					.addGroup(gl_jpnAcoes.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSalvar)
						.addComponent(btnLimparTela, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCancelar))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		jpnAcoes.setLayout(gl_jpnAcoes);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel jlbNome = new JLabel("Nome do dispositivo:");
		
		jtfNome = new JTextField();
		jtfNome.setColumns(10);
		
		JLabel jlbTipo = new JLabel("Tipo:");
		
		JComboBox jcbTipo = new JComboBox();
		
		JLabel jtfDescricao = new JLabel("Descrição:");
		
		JTextArea jtaDescricao = new JTextArea();
		jtaDescricao.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.LIGHT_GRAY));
		
		JLabel jlbUsuario = new JLabel("Usuário:");
		
		jtfUsuario = new JTextField();
		jtfUsuario.setColumns(10);
		
		JLabel jlbSetor = new JLabel("Setor:");
		
		JComboBox jcbSetor = new JComboBox();
		
		JLabel jlbRamal = new JLabel("Ramal:");
		
		jtfRamal = new JTextField();
		jtfRamal.setColumns(10);
		
		table = new JTable();
		
		JLabel jlbEndereco = new JLabel("Endereço de IP:");
		
		textField = new JTextField();
		textField.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(99)
					.addComponent(table, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(557, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(jlbNome)
						.addComponent(jlbUsuario)
						.addComponent(jtfDescricao)
						.addComponent(jlbEndereco))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(jtaDescricao, GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE)
								.addContainerGap())
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(jtfNome, GroupLayout.PREFERRED_SIZE, 499, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(jlbTipo)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(jcbTipo, 0, 59, Short.MAX_VALUE)
								.addGap(25))
							.addGroup(gl_panel.createSequentialGroup()
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addComponent(jtfUsuario, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
									.addGroup(gl_panel.createSequentialGroup()
										.addGap(214)
										.addComponent(jlbSetor)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(jcbSetor, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(jlbRamal)))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(jtfRamal, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
								.addContainerGap()))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(jlbNome)
						.addComponent(jtfNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(jcbTipo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(jlbTipo))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(jtaDescricao, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
						.addComponent(jtfDescricao))
					.addGap(8)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(jtfUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(jlbSetor)
						.addComponent(jcbSetor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(jlbRamal)
						.addComponent(jtfRamal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(jlbUsuario))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(jlbEndereco))
					.addGap(192)
					.addComponent(table, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		
	}
}
