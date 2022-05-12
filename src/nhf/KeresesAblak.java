package nhf;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * A keres�si ablakot reprezent�l� oszt�ly.
 * @author S�ri �kos
 */
public class KeresesAblak extends JFrame {

	private static final long serialVersionUID = 2914353242905700246L;
	
	private AlbumLista lista;
	private JTextField input;
	
	/**
	 * Konstru�lja �s megjelen�ti a keres�ablakot.
	 * @param lista Megkapja param�terk�nt a nyilv�ntart�st.
	 */
	public KeresesAblak(AlbumLista lista) {
		super("Keres�s");
		this.lista = lista;
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new FlowLayout());
		JLabel l = new JLabel("�rja be a keresett kulcssz�t (�vsz�mtartom�nyt is megadhat, �gy: 1990-2000): ");
		input = new JTextField(11);
		JButton b = new JButton("Keres�s");
		b.addActionListener(new ButtonListener());
		this.add(l);
		this.add(input);
		this.add(b);
		this.pack();
		this.setLocationRelativeTo(null);
		this.getRootPane().setDefaultButton(b);
		this.setAlwaysOnTop(true);
		this.setVisible(true);
	}
	
	/**
	 * A keres�s gomb megnyom�s�t lekezel�, ActionListener interfacet megval�s�t�, bels� priv�t oszt�ly.
	 * Amennyiben semmi nem lett  be�rva a sz�vegdobozba, mint kulcssz� vagy id�intervallum, 
	 * err�l figyelmeztet� �rtes�t�st kap a felhaszn�l�, �s a keres�s nem t�rt�nik meg.
	 * Ha be lett �rva valami, akkor egy �j ablakon jelennek meg a keres�si eredm�nyek.
	 */
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String str = input.getText();
			if(str.equals("")) {
				new FigyelmeztetesAblak("Hiba: Nincs megadva keres�si kulcssz�.");
			}
			else {
				KeresesAblak.this.dispose();
				new KeresesiEredmenyek(str,lista);
			}
		}
		
	}
}