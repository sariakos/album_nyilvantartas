package nhf;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Megjelenít egy fájlt kiválasztó ablakot (JFileChooser),
 * és a kiválasztott fájlt betölti a nyilvántartásba.
 * Csak nyilvántartást tartalmazó fájlt (.alb) lehet kiválasztani vele.
 * @author Sári Ákos
 */
public class BetoltesAblak extends JFrame {

	private static final long serialVersionUID = -4249239738826321956L;
	/**
	 * Megjeleníti a fájl kiválasztó ablakot.
	 * @param lista Ebbe a listába tölti be a nyilvántartást.
	 */
	public BetoltesAblak(AlbumLista lista) {
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Válasszon egy .alb fájlt...");
		fc.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Album adatbázisok (.alb)","alb");
		fc.addChoosableFileFilter(filter);
		fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
		int res = fc.showOpenDialog(this);
		if(res == JFileChooser.APPROVE_OPTION) {
			try {
				File sel = fc.getSelectedFile();
				lista.listaBetoltes(sel.getAbsolutePath());
			}
			catch (IOException e) {
				new FigyelmeztetesAblak("Hiba: A fájlt nem sikerült megnyitni.");
			}
		}
	}
	
}
