package fabrica;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class FabricaJLabel {
	
	public JLabel criaLabelTitulo(JLabel label, String texto, Integer x, Integer y, Integer w, Integer h){
		label = new JLabel(texto, SwingConstants.CENTER);
		label.setBounds(x, y, w, h);
		label.setVisible(true);
		return label;
	}

	public JLabel criaLabelForm(JLabel label, String texto, Integer x, Integer y, Integer w, Integer h){
		label = new JLabel(texto, SwingConstants.LEFT);
		label.setBounds(x, y, w, h);
		label.setVisible(true);
		return label;
	}
	
}
