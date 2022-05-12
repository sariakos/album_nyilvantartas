package nhf;

import java.io.Serializable;

/** Egy albumot reprezentáló, szerializálható osztály.
 * 
 * @author Sári Ákos
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
	 * Statikus tagváltozó, amely biztosítja, hogy az albumok azonosítói egyediek lehessenek.
	 */
	private static int count = 0;
	/**
	 * Elkészít egy albumot a megadott paraméterekkel.
	 * @param eloado Az album elõadójának neve.
	 * @param cim Az album címe.
	 * @param ev Az album kiadásának éve.
	 * @param kiado Az albumot kiadó vállalat neve.
	 */
	public Album(String eloado, String cim, int ev, String kiado) {
		this.eloado = eloado;
		this.cim = cim;
		this.ev = ev;
		this.kiado = kiado;
		this.ID = count++;
	}
	
	/**
	 * Visszatér az elõadó nevével.
	 * @return String, mely az elõadó nevét tartalmazza.
	 */
	public String getEloado() {
		return eloado;
	}
	/**
	 * Visszatér az album címével.
	 * @return String, mely az album címét tartalmazza.
	 */
	public String getCim() {
		return cim;
	}
	/**
	 * Visszatér az album kiadási évével.
	 * @return Egész szám, az album kiadási éve.
	 */
	public int getEv() {
		return ev;
	}
	/**
	 * Visszatér a kiadó nevével.
	 * @return String, mely a kiadó nevét tartalmazza.
	 */
	public String getKiado() {
		return kiado;
	}
	/**
	 * Visszatér az album egyedi azonosítójával.
	 * @return Egész szám, az album azonosítószáma.
	 */
	public int getID() {
		return ID;
	}
	
	/**
	 * Beállítja az album elõadójának nevét.
	 * @param name Az elõadó neve.
	 */
	public void setEloado(String name) {
		this.eloado = name;
	}
	/**
	 * Beállítja az album címét.
	 * @param cim Az album címe.
	 */
	public void setCim(String cim) {
		this.cim = cim;
	}
	/**
	 * Beállítja az album kiadási évét.
	 * @param ev Az album kiadási éve.
	 */
	public void setEv(int ev) {
		this.ev = ev;
	}
	/**
	 * Beállítja az album kiadóját.
	 * @param kiado Az album kiadója.
	 */
	public void setKiado(String kiado) {
		this.kiado = kiado;
	}
	
	/**
	 * Eldönti egy adott albumról, hogy megfelel-e a keresett kulcsszónak.
	 * @param str A kulcsszó, mely lehet konkrét szó vagy szórészlet, illetve "tól-ig" idõintervallum is.
	 * @return Igaz, ha megfelel a keresésnek, hamis, ha nem.
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