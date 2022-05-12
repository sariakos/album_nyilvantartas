package nhf;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Keresési eredmények megjelenítésére használt ablakot reprezentáló osztály.
 * @author Sári Ákos
 */
public class KeresesiEredmenyek extends JFrame {

	private static final long serialVersionUID = -2013076443834839653L;
	
	/**
	 * Amennyiben van találat a keresett kulcsszóra vagy idõintervallumra, 
	 * konstruálja és megjeleníti egy táblázatban a találatokat.
	 * Ha nincs találat, egy figyelmeztetõ ablakot jelenít meg a felhasználónak errõl.
	 * @param str String, a keresett kulcsszóval.
	 * @param lista Az albumokat tartalmazó nyilvántartás.
	 */
	public KeresesiEredmenyek(String str, AlbumLista lista) {
		super("Keresési eredmények");
		String col[] = {"ID","Elõadó","Album címe","Kiadási év","Kiadó"};
		DefaultTableModel tm = new DefaultTableModel(col,0);
		for(int i = 0; i < lista.size(); i++) {
			Album a = lista.get(i);
			if(a.matchesSearch(str)) {
				String[] sor = {String.valueOf(a.getID()), a.getEloado(), a.getCim(), String.valueOf(a.getEv()), a.getKiado()};
				tm.addRow(sor);
			}
		}
		if(tm.getRowCount()==0) {
			new FigyelmeztetesAblak("Nincs találat a megadott keresésre.");
		}
		else {
			JTable j = new JTable(tm);
			j.setAutoCreateRowSorter(true);
			j.setDefaultEditor(Object.class, null);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.setLocationRelativeTo(null);
			JScrollPane sp = new JScrollPane(j);
			this.add(sp);
			this.setSize(500,200);
			this.setResizable(true);
			this.setAlwaysOnTop(true);
			this.setVisible(true);
		}
	}
}