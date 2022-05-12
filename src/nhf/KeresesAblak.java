package nhf;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * A keresési ablakot reprezentáló osztály.
 * @author Sári Ákos
 */
public class KeresesAblak extends JFrame {

	private static final long serialVersionUID = 2914353242905700246L;
	
	private AlbumLista lista;
	private JTextField input;
	
	/**
	 * Konstruálja és megjeleníti a keresõablakot.
	 * @param lista Megkapja paraméterként a nyilvántartást.
	 */
	public KeresesAblak(AlbumLista lista) {
		super("Keresés");
		this.lista = lista;
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new FlowLayout());
		JLabel l = new JLabel("Írja be a keresett kulcsszót (évszámtartományt is megadhat, így: 1990-2000): ");
		input = new JTextField(11);
		JButton b = new JButton("Keresés");
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
	 * A keresés gomb megnyomását lekezelõ, ActionListener interfacet megvalósító, belsõ privát osztály.
	 * Amennyiben semmi nem lett  beírva a szövegdobozba, mint kulcsszó vagy idõintervallum, 
	 * errõl figyelmeztetõ értesítést kap a felhasználó, és a keresés nem történik meg.
	 * Ha be lett írva valami, akkor egy új ablakon jelennek meg a keresési eredmények.
	 */
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String str = input.getText();
			if(str.equals("")) {
				new FigyelmeztetesAblak("Hiba: Nincs megadva keresési kulcsszó.");
			}
			else {
				KeresesAblak.this.dispose();
				new KeresesiEredmenyek(str,lista);
			}
		}
		
	}
}