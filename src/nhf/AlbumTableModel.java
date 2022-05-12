package nhf;



import javax.swing.table.DefaultTableModel;
/**
 * Table Model az <b>�sszes</b> nyilv�ntart�sban l�v� album megjelen�t�s�hez.
 * @author S�ri �kos
 */
public class AlbumTableModel extends DefaultTableModel {
	private static final long serialVersionUID = -2337702357452550191L;
	
	/**
	 * A lista, amit megjelen�t, �s amit sz�ks�g eset�n m�dos�t.
	 */
	private AlbumLista lista;
	/**
	 * Az oszlopok fejl�cei.
	 */
	private static String col[] = {"ID","El�ad�","Album c�me","Kiad�si �v","Kiad�"};
	
	/**
	 * L�trehozza a table modelt a megadott nyilv�ntart�s alapj�n.
	 * @param lista A nyilv�ntart�s.
	 */
	public AlbumTableModel(AlbumLista lista) {
		super(col,0);
		this.lista = lista;
	}
	
	/**
	 * Meghat�rozza, hogy egy adott cella szerkeszthet�-e, vagy sem.
	 * Az egyedi azonos�t�t megjelen�t� oszlopban (ID) egyik adat sem m�dos�that�, a t�bbi oszlopban b�rmi.
	 * @param rowIndex Sor sorsz�ma.
	 * @param columnIndex Oszlop sorsz�ma.
	 */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if(columnIndex == 0) {
			return false;
		}
		return true;
	}
	
	/**
	 * Visszat�r egy adott cella tartalm�val.
	 * @param rowIndex Sor sorsz�ma.
	 * @param columnIndex Oszlop sorsz�ma.
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Album a = lista.get(rowIndex);
		if(columnIndex == 0) {
			return a.getID();
		}
		else if(columnIndex == 1) {
			return a.getEloado();
		}
		else if(columnIndex == 2) {
			return a.getCim();
		}
		else if(columnIndex == 3) {
			return a.getEv();
		}
		else if(columnIndex == 4) {
			return a.getKiado();
		}
		return null;
	}
	
	/**
	 * �t�rja a megadott cella tartalm�t, a t�bl�zatban �s a nyilv�ntart�sban egyar�nt.
	 * @param aValue A t�bl�zatban �t�rt �rt�k.
	 * @param rowIndex Sor sorsz�ma.
	 * @param columnIndex Oszlop sorsz�ma.
	 * @throws NumberFormatException Amennyiben kiad�si �vnek nem sz�m lett megadva, kiv�telt dob.
	 */
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) throws NumberFormatException {
		Album a = lista.get(rowIndex);
		String str = (String) aValue;
		if(str.equals("")) {
			new FigyelmeztetesAblak("Nem lehet �res a megadott param�ter.");
		}
		else if(columnIndex == 1) {
			a.setEloado(str);
		}
		else if(columnIndex == 2) {
			a.setCim(str);
		}
		else if(columnIndex == 3) {
			try {
				a.setEv(Integer.parseInt(str));
			}
			catch(NumberFormatException e) {
				new FigyelmeztetesAblak("Hiba: Kiad�si �vnek csak sz�m lehet megadva!");
			}
		}
		else if(columnIndex == 4) {
			a.setKiado(str);
		}
	}
}
