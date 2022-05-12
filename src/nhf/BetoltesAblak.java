package nhf;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Megjelen�t egy f�jlt kiv�laszt� ablakot (JFileChooser),
 * �s a kiv�lasztott f�jlt bet�lti a nyilv�ntart�sba.
 * Csak nyilv�ntart�st tartalmaz� f�jlt (.alb) lehet kiv�lasztani vele.
 * @author S�ri �kos
 */
public class BetoltesAblak extends JFrame {

	private static final long serialVersionUID = -4249239738826321956L;
	/**
	 * Megjelen�ti a f�jl kiv�laszt� ablakot.
	 * @param lista Ebbe a list�ba t�lti be a nyilv�ntart�st.
	 */
	public BetoltesAblak(AlbumLista lista) {
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("V�lasszon egy .alb f�jlt...");
		fc.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Album adatb�zisok (.alb)","alb");
		fc.addChoosableFileFilter(filter);
		fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
		int res = fc.showOpenDialog(this);
		if(res == JFileChooser.APPROVE_OPTION) {
			try {
				File sel = fc.getSelectedFile();
				lista.listaBetoltes(sel.getAbsolutePath());
			}
			catch (IOException e) {
				new FigyelmeztetesAblak("Hiba: A f�jlt nem siker�lt megnyitni.");
			}
		}
	}
	
}
