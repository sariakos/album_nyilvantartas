package nhf;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Egy album nyilvántartásból ID alapján történõ törlésére használt ablakot reprezentáló osztály.
 * @author Sári Ákos
 */
public class TorlesAblak extends JFrame {

	private static final long serialVersionUID = -2965997841732090421L;
	
	private JTextField input;
	private AlbumLista lista;

	/**
	 * Konstruálja és megjeleníti a törlésre szolgáló ablakot.
	 * Amennyiben nincs album a nyilvántartásban, figyelmezteti a felhasználót, és nem nyílik meg az ablak.
	 * @param lista Az album nyilvántartás.
	 */
	public TorlesAblak(AlbumLista lista) {
		if(lista.size() == 0) {
			new FigyelmeztetesAblak("A nyilvántartás üres, nincs mit törölni belõle.");
		}
		else {
			this.setTitle("Album törlése");
			this.lista = lista;
			this.setLayout(new FlowLayout());
			JLabel l = new JLabel("A törölni kívánt album azonosítója:");
			input = new JTextField(5);
			JButton b = new JButton("Törlés");
			this.add(l);
			this.add(input);
			this.add(b);
			b.addActionListener(new ButtonListener());
			this.pack();
			this.setLocationRelativeTo(null);
			this.setAlwaysOnTop(true);
			this.getRootPane().setDefaultButton(b);
			this.setVisible(true);
		}
	}
	
	
	/**
	 * A "Törlés" gomb megnyomását lekezelõ, ActionListener interfacet megvalósító, belsõ privát osztály.
	 * Amennyiben egy létezõ ID lett megadva, az album törlõdik a nyilvántartásból, és errõl értesítést is kap a felhasználó.
	 * Ha nem szám lett megadva, vagy a megadott ID nem tartozik albumhoz,
	 * arról értesítést kap a felhasználó, ilyenkor természetesen semmi sem törlõdik.
	 */
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(input.getText().equals("")) {
				new FigyelmeztetesAblak("Hiba: Nem adott meg ID-t!");
			}
			else {
				try { 
					int id = Integer.parseInt(input.getText());
					boolean success = lista.removeAlbum(id);
					if(success) {
						new FigyelmeztetesAblak("Törölte a " + id + " ID-jû albumot.");
						TorlesAblak.this.dispose();
					}
					else new FigyelmeztetesAblak("Nem létezik album ilyen ID-vel.");
				}
				catch(NumberFormatException nfe) {
					new FigyelmeztetesAblak("Hiba: Nem számot adott meg!");
				}
			}
		}
		
	}
}