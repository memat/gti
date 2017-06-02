package fabrica;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

public class FabricaJPanel {
	
	public JPanel criaPanelTitulo(JPanel panel, Integer x, Integer y, Integer w, Integer h){
		panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
		panel.setBounds(x, y, w, h);
		panel.setLayout(null);
		panel.setVisible(true);
		return panel;
	}
	
	public JPanel criaPanelConteudo(JPanel panel, Integer x, Integer y, Integer w, Integer h){
		panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
		panel.setBounds(x, y, w, h);
		panel.setLayout(null);
		panel.setVisible(true);
		return panel;
	}

}
