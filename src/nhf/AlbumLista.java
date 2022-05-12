package nhf;

import java.util.Iterator;
import java.util.LinkedList;

import java.io.*;
/**
 * Az albumokat tároló lista, azaz maga a nyilvántartás.
 * @author Sári Ákos
 */
public class AlbumLista extends LinkedList<Album> {
	private static final long serialVersionUID = 3652405053244198564L;
	
	/**
	 * Eltávolít egy albumot az azonosítója alapján a nyilvántartásból.
	 * @param id Az album ID-je.
	 * @return True, ha sikerült a törlés, false, ha sikertelen volt.
	 */
	public boolean removeAlbum(int id) {
		Iterator<Album> i = this.iterator();
		while(i.hasNext()) {
			Album a = i.next();
			if(a.getID() == id) {
				i.remove();
				return true;
			}
		}
		return false;
	}
	/**
	 * Betölt egy nyilvántartást a megadott elérési útvonalról.
	 * @param path Elérési útvonal a mentett listához.
	 * @throws IOException Amennyiben nem sikerült beolvasni a fájlt, kivételt dob.
	 */
	public void listaBetoltes(String path) throws IOException{
		try {
			FileInputStream be = new FileInputStream(path);
			ObjectInputStream oin = new ObjectInputStream(be);
			Object obj = oin.readObject();
			if(obj instanceof LinkedList<?>) {
				LinkedList<?> l = (LinkedList<?>) obj;
				if(l.size() > 0) {
					this.clear();
					for(int i = 0; i<l.size(); i++) {
						Object e = l.get(i);
						if(e instanceof Album) {
							String cim = ((Album) e).getCim();
							String eloado = ((Album) e).getEloado();
							String kiado = ((Album) e).getKiado();
							int ev = ((Album) e).getEv();
							Album a = new Album(eloado,cim,ev,kiado);
							this.add(a);
						}
					}
				}
			}
			oin.close();
			be.close();
		}
		catch (IOException e) {
			throw e; //hogy a teszteset expectelhesse
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * Elmenti a jelenlegi nyilvántartást az útvonalként megadott fájlba.
	 * @param path A fájl útvonala, amelybe a mentés történik.
	 */
	public void listaMentes(String path) {
		try {
			FileOutputStream ki = new FileOutputStream(path);
			ObjectOutputStream ous = new ObjectOutputStream(ki);
			ous.writeObject(this);
			ous.close();
			ki.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
