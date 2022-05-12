package nhf;

import java.util.Iterator;
import java.util.LinkedList;

import java.io.*;
/**
 * Az albumokat t�rol� lista, azaz maga a nyilv�ntart�s.
 * @author S�ri �kos
 */
public class AlbumLista extends LinkedList<Album> {
	private static final long serialVersionUID = 3652405053244198564L;
	
	/**
	 * Elt�vol�t egy albumot az azonos�t�ja alapj�n a nyilv�ntart�sb�l.
	 * @param id Az album ID-je.
	 * @return True, ha siker�lt a t�rl�s, false, ha sikertelen volt.
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
	 * Bet�lt egy nyilv�ntart�st a megadott el�r�si �tvonalr�l.
	 * @param path El�r�si �tvonal a mentett list�hoz.
	 * @throws IOException Amennyiben nem siker�lt beolvasni a f�jlt, kiv�telt dob.
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
	 * Elmenti a jelenlegi nyilv�ntart�st az �tvonalk�nt megadott f�jlba.
	 * @param path A f�jl �tvonala, amelybe a ment�s t�rt�nik.
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
