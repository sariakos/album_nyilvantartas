package nhf;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Fut�s k�zbeni esem�nyekkor megjelen�, felhaszn�l�t figyelmeztet� ablakot reprezent�l� oszt�ly.
 * @author S�ri �kos
 */
public class FigyelmeztetesAblak extends JFrame {

	private static final long serialVersionUID = -8389037983059286109L;
	
	/**
	 * Figyelmeztet� �zenetet megjelen�t� ablak.
	 * @param msg A megjelen�tend� �zenetet tartalmaz� string.
	 */
	public FigyelmeztetesAblak(String msg) {
		super("Figyelmeztet�s");
		this.setUndecorated(true);
		JLabel l = new JLabel(msg);
		JButton b = new JButton("Rendben");
		JPanel panel = new JPanel();
		GridLayout gl = new GridLayout();
		gl.setColumns(1);
		gl.setRows(2);
		panel.setLayout(gl);
		panel.add(l);
		panel.add(b);
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		this.add(panel);
		this.getRootPane().setDefaultButton(b);
		b.addActionListener(new ButtonListener());
		this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		this.pack();
		this.setAlwaysOnTop(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	/**
	 * ActionListenert megval�s�t� bels� oszt�ly, a (Rendben) gomb megnyom�s�ra elt�nteti az ablakot
	 */
	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			FigyelmeztetesAblak.this.dispose();
		}
		
	}
}
