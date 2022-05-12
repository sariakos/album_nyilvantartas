package nhf;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Megjelen�t egy f�jlt kiv�laszt� ablakot (JFileChooser),
 * �s egy f�jln�v megad�sa ut�n abba a f�jlba elmenti a nyilv�ntart�st.
 * Amennyiben a felhaszn�l� �ltal megadott f�jln�v nem tartalmazza a .alb kiterjeszt�st,
 * a program ezt mag�t�l hozz�adja.
 * Amennyiben a nyilv�ntart�sban egyetlen album sem szerepel, �gy nincs mit elmenteni,
 * ezt a program jelzi is a felhaszn�l�nak, ebben az esetben nem is j�n el� a f�jl kiv�laszt� ablak.
 * @author S�ri �kos
 */
public class MentesAblak extends JFrame {

	private static final long serialVersionUID = -4249239738826321956L;

	/**
	 * Megjelen�ti a f�jl kiv�laszt� ablakot.
	 * @param lista Az album nyilv�ntart�s, ez ker�l elment�sre.
	 */
	public MentesAblak(AlbumLista lista) {
		if(lista.size() == 0) {
			new FigyelmeztetesAblak("A nyilv�ntart�s �res, nincs mit elmenteni.");
		}
		else {
			JFileChooser fc = new JFileChooser();
			fc.setDialogTitle("Jelenlegi adatb�zis elment�se");
			fc.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Album adatb�zisok (.alb)","alb");
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
