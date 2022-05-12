package nhf;



import javax.swing.table.DefaultTableModel;
/**
 * Table Model az <b>összes</b> nyilvántartásban lévõ album megjelenítéséhez.
 * @author Sári Ákos
 */
public class AlbumTableModel extends DefaultTableModel {
	private static final long serialVersionUID = -2337702357452550191L;
	
	/**
	 * A lista, amit megjelenít, és amit szükség esetén módosít.
	 */
	private AlbumLista lista;
	/**
	 * Az oszlopok fejlécei.
	 */
	private static String col[] = {"ID","Elõadó","Album címe","Kiadási év","Kiadó"};
	
	/**
	 * Létrehozza a table modelt a megadott nyilvántartás alapján.
	 * @param lista A nyilvántartás.
	 */
	public AlbumTableModel(AlbumLista lista) {
		super(col,0);
		this.lista = lista;
	}
	
	/**
	 * Meghatározza, hogy egy adott cella szerkeszthetõ-e, vagy sem.
	 * Az egyedi azonosítót megjelenítõ oszlopban (ID) egyik adat sem módosítható, a többi oszlopban bármi.
	 * @param rowIndex Sor sorszáma.
	 * @param columnIndex Oszlop sorszáma.
	 */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if(columnIndex == 0) {
			return false;
		}
		return true;
	}
	
	/**
	 * Visszatér egy adott cella tartalmával.
	 * @param rowIndex Sor sorszáma.
	 * @param columnIndex Oszlop sorszáma.
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
	 * Átírja a megadott cella tartalmát, a táblázatban és a nyilvántartásban egyaránt.
	 * @param aValue A táblázatban átírt érték.
	 * @param rowIndex Sor sorszáma.
	 * @param columnIndex Oszlop sorszáma.
	 * @throws NumberFormatException Amennyiben kiadási évnek nem szám lett megadva, kivételt dob.
	 */
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) throws NumberFormatException {
		Album a = lista.get(rowIndex);
		String str = (String) aValue;
		if(str.equals("")) {
			new FigyelmeztetesAblak("Nem lehet üres a megadott paraméter.");
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
				new FigyelmeztetesAblak("Hiba: Kiadási évnek csak szám lehet megadva!");
			}
		}
		else if(columnIndex == 4) {
			a.setKiado(str);
		}
	}
}
