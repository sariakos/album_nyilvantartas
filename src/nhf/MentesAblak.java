package nhf;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Megjelenít egy fájlt kiválasztó ablakot (JFileChooser),
 * és egy fájlnév megadása után abba a fájlba elmenti a nyilvántartást.
 * Amennyiben a felhasználó által megadott fájlnév nem tartalmazza a .alb kiterjesztést,
 * a program ezt magától hozzáadja.
 * Amennyiben a nyilvántartásban egyetlen album sem szerepel, úgy nincs mit elmenteni,
 * ezt a program jelzi is a felhasználónak, ebben az esetben nem is jön elõ a fájl kiválasztó ablak.
 * @author Sári Ákos
 */
public class MentesAblak extends JFrame {

	private static final long serialVersionUID = -4249239738826321956L;

	/**
	 * Megjeleníti a fájl kiválasztó ablakot.
	 * @param lista Az album nyilvántartás, ez kerül elmentésre.
	 */
	public MentesAblak(AlbumLista lista) {
		if(lista.size() == 0) {
			new FigyelmeztetesAblak("A nyilvántartás üres, nincs mit elmenteni.");
		}
		else {
			JFileChooser fc = new JFileChooser();
			fc.setDialogTitle("Jelenlegi adatbázis elmentése");
			fc.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Album adatbázisok (.alb)","alb");
			fc.addChoosableFileFilter(filter);
			fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
			int sel = fc.showSaveDialog(this);
			if(sel == JFileChooser.APPROVE_OPTION) {
				File mentes = fc.getSelectedFile();
				String path = mentes.getAbsolutePath();
				if(!path.contains(".alb")) {
					path = path + ".alb";
				}
				lista.listaMentes(path);
			}
		}
	}
}
