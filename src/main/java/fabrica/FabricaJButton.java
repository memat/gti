package fabrica;

import javax.swing.JButton;

public class FabricaJButton {

	public JButton criaButtonAcao(JButton botao, String texto, Integer x, Integer y, Integer w, Integer h){
		botao = new JButton(texto);
		botao.setBounds(x, y, w, h);
		botao.setVisible(true);
		botao.setFocusable(false);
		return botao;
	}
}
