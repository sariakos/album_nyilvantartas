package nhf;

import java.io.Serializable;

/** Egy albumot reprezent�l�, szerializ�lhat� oszt�ly.
 * 
 * @author S�ri �kos
 * @version 1.0
 */
public class Album implements Serializable {
	private static final long serialVersionUID = -8733288157122115878L;
	private String eloado;
	private String cim;
	private int ev;
	private String kiado;
	private int ID;
	/**
	 * Statikus tagv�ltoz�, amely biztos�tja, hogy az albumok azonos�t�i egyediek lehessenek.
	 */
	private static int count = 0;
	/**
	 * Elk�sz�t egy albumot a megadott param�terekkel.
	 * @param eloado Az album el�ad�j�nak neve.
	 * @param cim Az album c�me.
	 * @param ev Az album kiad�s�nak �ve.
	 * @param kiado Az albumot kiad� v�llalat neve.
	 */
	public Album(String eloado, String cim, int ev, String kiado) {
		this.eloado = eloado;
		this.cim = cim;
		this.ev = ev;
		this.kiado = kiado;
		this.ID = count++;
	}
	
	/**
	 * Visszat�r az el�ad� nev�vel.
	 * @return String, mely az el�ad� nev�t tartalmazza.
	 */
	public String getEloado() {
		return eloado;
	}
	/**
	 * Visszat�r az album c�m�vel.
	 * @return String, mely az album c�m�t tartalmazza.
	 */
	public String getCim() {
		return cim;
	}
	/**
	 * Visszat�r az album kiad�si �v�vel.
	 * @return Eg�sz sz�m, az album kiad�si �ve.
	 */
	public int getEv() {
		return ev;
	}
	/**
	 * Visszat�r a kiad� nev�vel.
	 * @return String, mely a kiad� nev�t tartalmazza.
	 */
	public String getKiado() {
		return kiado;
	}
	/**
	 * Visszat�r az album egyedi azonos�t�j�val.
	 * @return Eg�sz sz�m, az album azonos�t�sz�ma.
	 */
	public int getID() {
		return ID;
	}
	
	/**
	 * Be�ll�tja az album el�ad�j�nak nev�t.
	 * @param name Az el�ad� neve.
	 */
	public void setEloado(String name) {
		this.eloado = name;
	}
	/**
	 * Be�ll�tja az album c�m�t.
	 * @param cim Az album c�me.
	 */
	public void setCim(String cim) {
		this.cim = cim;
	}
	/**
	 * Be�ll�tja az album kiad�si �v�t.
	 * @param ev Az album kiad�si �ve.
	 */
	public void setEv(int ev) {
		this.ev = ev;
	}
	/**
	 * Be�ll�tja az album kiad�j�t.
	 * @param kiado Az album kiad�ja.
	 */
	public void setKiado(String kiado) {
		this.kiado = kiado;
	}
	
	/**
	 * Eld�nti egy adott albumr�l, hogy megfelel-e a keresett kulcssz�nak.
	 * @param str A kulcssz�, mely lehet konkr�t sz� vagy sz�r�szlet, illetve "t�l-ig" id�intervallum is.
	 * @return Igaz, ha megfelel a keres�snek, hamis, ha nem.
	 */
	public boolean matchesSearch(String str) {
		int ev1 = -1, ev2 = -1;
		if(str.length() == 9 && str.charAt(4) == '-') {
			try {
				String[] evek = str.split("-",2);
				ev1 = Integer.parseInt(evek[0]);
				ev2 = Integer.parseInt(evek[1]);
			}
			catch (NumberFormatException e) {
				ev1 = -1;
				ev2 = -1;
			}
		}
		String evstr = String.valueOf(ev);
		if((ev1<=ev && ev2>=ev) || 
				(cim.contains(str) || eloado.contains(str) || kiado.contains(str) || evstr.contains(str))) {
			return true;
		}
		return false;
	}
}