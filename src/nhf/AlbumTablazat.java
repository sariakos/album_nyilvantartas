package nhf;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * Az �sszes nyilv�ntart�sban l�v� albumot list�z� t�bl�zat, ablak.
 * @author S�ri �kos
 */
public class AlbumTablazat extends JFrame {
	private static final long serialVersionUID = 652710948798872151L;
	
	/**
	 * Megjelen�ti az �sszes nyilv�ntart�sban l�v� albumot (amit param�terk�nt kap) egy t�bl�zatban.
	 * A t�bl�zatban �t�rt adatok �t�r�sra ker�lnek a nyilv�ntart�sban is.
	 * @param lista Az albumokat t�rol� nyilv�ntart�s.
	 */
	public AlbumTablazat(AlbumLista lista) {
		super("Albumok list�ja");
		if(lista.size() == 0) {
			new FigyelmeztetesAblak("A nyilv�ntart�s �res, nincs mit megjelen�teni.");
		}
		else {
			AlbumTableModel model = new AlbumTableModel(lista);
			for(int i = 0; i < lista.size(); i++) {
				String[] sor = {String.valueOf(lista.get(i).getID()) , lista.get(i).getEloado() , lista.get(i).getCim() , String.valueOf(lista.get(i).getEv()) , lista.get(i).getKiado()};
				model.addRow(sor);
			}
			JPanel panel = new JPanel();
			JTable table = new JTable(model);
			JLabel label = new JLabel("Adatok m�dos�t�s�hoz kattintson dupl�n a m�dos�tani k�v�nt mez�be.");
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
