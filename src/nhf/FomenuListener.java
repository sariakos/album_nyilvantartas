package nhf;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A fõmenü gombnyomásaira funkciókat megjelenítõ, ActionListener interfacet
 * megvalósító osztály.
 * @author Sári Ákos
 */
public class FomenuListener implements ActionListener {
	public AlbumLista lista;
	
	/**
	 * Megkapja az albumok nyilvántartását a mûveletvégzéshez.
	 * @param l Albumok nyilvántartása.
	 */
	public FomenuListener(AlbumLista l) {
		this.lista = l;
	}
	/**
	 * Fõmenühöz tartozó ActionCommandok
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("db_betoltes")) {
			new BetoltesAblak(lista);
		}
		else if(arg0.getActionCommand().equals("db_mentes")) {
			new MentesAblak(lista);
		}
		else if(arg0.getActionCommand().equals("albumok")) {
			new AlbumTablazat(lista);
		}
		else if(arg0.getActionCommand().equals("torles_menupont")) {
			new TorlesAblak(lista);
		}
		else if(arg0.getActionCommand().equals("ujalbum")) {
			new HozzaadasAblak(lista);
		}
		else if(arg0.getActionCommand().equals("kereses_menupont")) {
			new KeresesAblak(lista);
		}
	}
	
}
