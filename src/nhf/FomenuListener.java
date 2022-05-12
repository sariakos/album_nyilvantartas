package nhf;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A f�men� gombnyom�saira funkci�kat megjelen�t�, ActionListener interfacet
 * megval�s�t� oszt�ly.
 * @author S�ri �kos
 */
public class FomenuListener implements ActionListener {
	public AlbumLista lista;
	
	/**
	 * Megkapja az albumok nyilv�ntart�s�t a m�veletv�gz�shez.
	 * @param l Albumok nyilv�ntart�sa.
	 */
	public FomenuListener(AlbumLista l) {
		this.lista = l;
	}
	/**
	 * F�men�h�z tartoz� ActionCommandok
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
