package nhf;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * Az összes nyilvántartásban lévõ albumot listázó táblázat, ablak.
 * @author Sári Ákos
 */
public class AlbumTablazat extends JFrame {
	private static final long serialVersionUID = 652710948798872151L;
	
	/**
	 * Megjeleníti az összes nyilvántartásban lévõ albumot (amit paraméterként kap) egy táblázatban.
	 * A táblázatban átírt adatok átírásra kerülnek a nyilvántartásban is.
	 * @param lista Az albumokat tároló nyilvántartás.
	 */
	public AlbumTablazat(AlbumLista lista) {
		super("Albumok listája");
		if(lista.size() == 0) {
			new FigyelmeztetesAblak("A nyilvántartás üres, nincs mit megjeleníteni.");
		}
		else {
			AlbumTableModel model = new AlbumTableModel(lista);
			for(int i = 0; i < lista.size(); i++) {
				String[] sor = {String.valueOf(lista.get(i).getID()) , lista.get(i).getEloado() , lista.get(i).getCim() , String.valueOf(lista.get(i).getEv()) , lista.get(i).getKiado()};
				model.addRow(sor);
			}
			JPanel panel = new JPanel();
			JTable table = new JTable(model);
			JLabel label = new JLabel("Adatok módosításához kattintson duplán a módosítani kívánt mezõbe.");
			panel.setLayout(new BorderLayout());
			panel.add(new JScrollPane(table),BorderLayout.CENTER);
			panel.add(label,BorderLayout.SOUTH);
			this.add(panel);
			this.setSize(650,230);
			this.setResizable(true);
			table.setAutoCreateRowSorter(true);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.setLocationRelativeTo(null);
			this.setAlwaysOnTop(true);
			this.setVisible(true);
		}
	}

}
