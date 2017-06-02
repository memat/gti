package fabrica;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FabricaJText {
	
	public JTextField criaTextFieldForm(JTextField tf, Integer x, Integer y, Integer w, Integer h){
		tf = new JTextField();
		tf.setBounds(x, y, w, h);
		tf.setVisible(true);
		return tf;
	}

	public JTextArea criaTextAreaForm(JTextArea ta, Integer x, Integer y, Integer w, Integer h){
		ta = new JTextArea();
		ta.setBounds(x, y, w, h);
		ta.setVisible(true);
		ta.setLineWrap(true);
		//ta.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
		return ta;
	}

}
