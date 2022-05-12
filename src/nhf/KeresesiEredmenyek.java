package nhf;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Keres�si eredm�nyek megjelen�t�s�re haszn�lt ablakot reprezent�l� oszt�ly.
 * @author S�ri �kos
 */
public class KeresesiEredmenyek extends JFrame {

	private static final long serialVersionUID = -2013076443834839653L;
	
	/**
	 * Amennyiben van tal�lat a keresett kulcssz�ra vagy id�intervallumra, 
	 * konstru�lja �s megjelen�ti egy t�bl�zatban a tal�latokat.
	 * Ha nincs tal�lat, egy figyelmeztet� ablakot jelen�t meg a felhaszn�l�nak err�l.
	 * @param str String, a keresett kulcssz�val.
	 * @param lista Az albumokat tartalmaz� nyilv�ntart�s.
	 */
	public KeresesiEredmenyek(String str, AlbumLista lista) {
		super("Keres�si eredm�nyek");
		String col[] = {"ID","El�ad�","Album c�me","Kiad�si �v","Kiad�"};
		DefaultTableModel tm = new DefaultTableModel(col,0);
		for(int i = 0; i < lista.size(); i++) {
			Album a = lista.get(i);
			if(a.matchesSearch(str)) {
				String[] sor = {String.valueOf(a.getID()), a.getEloado(), a.getCim(), String.valueOf(a.getEv()), a.getKiado()};
				tm.addRow(sor);
			}
		}
		if(tm.getRowCount()==0) {
			new FigyelmeztetesAblak("Nincs tal�lat a megadott keres�sre.");
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