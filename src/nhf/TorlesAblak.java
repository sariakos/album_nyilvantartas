package nhf;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Egy album nyilv�ntart�sb�l ID alapj�n t�rt�n� t�rl�s�re haszn�lt ablakot reprezent�l� oszt�ly.
 * @author S�ri �kos
 */
public class TorlesAblak extends JFrame {

	private static final long serialVersionUID = -2965997841732090421L;
	
	private JTextField input;
	private AlbumLista lista;

	/**
	 * Konstru�lja �s megjelen�ti a t�rl�sre szolg�l� ablakot.
	 * Amennyiben nincs album a nyilv�ntart�sban, figyelmezteti a felhaszn�l�t, �s nem ny�lik meg az ablak.
	 * @param lista Az album nyilv�ntart�s.
	 */
	public TorlesAblak(AlbumLista lista) {
		if(lista.size() == 0) {
			new FigyelmeztetesAblak("A nyilv�ntart�s �res, nincs mit t�r�lni bel�le.");
		}
		else {
			this.setTitle("Album t�rl�se");
			this.lista = lista;
			this.setLayout(new FlowLayout());
			JLabel l = new JLabel("A t�r�lni k�v�nt album azonos�t�ja:");
			input = new JTextField(5);
			JButton b = new JButton("T�rl�s");
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
	 * A "T�rl�s" gomb megnyom�s�t lekezel�, ActionListener interfacet megval�s�t�, bels� priv�t oszt�ly.
	 * Amennyiben egy l�tez� ID lett megadva, az album t�rl�dik a nyilv�ntart�sb�l, �s err�l �rtes�t�st is kap a felhaszn�l�.
	 * Ha nem sz�m lett megadva, vagy a megadott ID nem tartozik albumhoz,
	 * arr�l �rtes�t�st kap a felhaszn�l�, ilyenkor term�szetesen semmi sem t�rl�dik.
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
						new FigyelmeztetesAblak("T�r�lte a " + id + " ID-j� albumot.");
						TorlesAblak.this.dispose();
					}
					else new FigyelmeztetesAblak("Nem l�tezik album ilyen ID-vel.");
				}
				catch(NumberFormatException nfe) {
					new FigyelmeztetesAblak("Hiba: Nem sz�mot adott meg!");
				}
			}
		}
		
	}
}