package nhf;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
/**
 * Új album nyilvántartáshoz adására szolgáló ablakot reprezentáló osztály.
 * Belsõ osztálya arra szolgál, hogy reagáljon a gombnyomásokra. 
 * @author Sári Ákos
 */
public class HozzaadasAblak extends JFrame {

	private static final long serialVersionUID = 954655290978078500L;
	
	private JTextField jtf1;
	private JTextField jtf2;
	private JTextField jtf3;
	private JTextField jtf4;
	
	private AlbumLista lista;
	/**
	 * Konstruálja és megjeleníti az új album hozzáadására szolgáló ablakot.
	 * Ebben a hozzáadandó album 4 paraméterét lehet megadni.
	 * Paraméterül kapja a nyilvántartást, az azon történõ mûveletvégzéshez.
	 * @param lista Az albumok nyilvántartása.
	 */
	public HozzaadasAblak(AlbumLista lista) {
		super("Új album hozzáadása");
		this.lista = lista;
		GridLayout grid = new GridLayout();
		grid.setRows(5);
		grid.setColumns(2);
		this.setLayout(grid);
		this.setSize(400,200);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JLabel l1 = new JLabel("Elõadó:");
		JLabel l2 = new JLabel("Album címe:");
		JLabel l3 = new JLabel("Kiadási év:");
		JLabel l4 = new JLabel("Kiadó:");
		this.jtf1 = new JTextField("");
		this.jtf2 = new JTextField("");
		this.jtf3 = new JTextField("");
		this.jtf4 = new JTextField("");
		JButton b1 = new JButton("Hozzáadás");
		JButton b2 = new JButton("Mégse");
		b1.setActionCommand("hozzaadas");
		b2.setActionCommand("megse");
		ActionListener al = new ButtonListener();
		b1.addActionListener(al);
		b2.addActionListener(al);
		this.add(l1);
		this.add(jtf1);
		this.add(l2);
		this.add(jtf2);
		this.add(l3);
		this.add(jtf3);
		this.add(l4);
		this.add(jtf4);
		this.add(b1);
		this.add(b2);
		this.getRootPane().setDefaultButton(b1);
		this.setLocationRelativeTo(null);
		this.setAlwaysOnTop(true);
		this.setVisible(true);
	}
	
	
	/**
	 * Gombnyomások (Hozzáadás, Mégse) lekezelésére szolgáló, ActionListener interfacet megvalósító belsõ privát osztály.
	 * Elõbbi hozzáadja a nyilvántartáshoz a beírt adatok alapján az új albumot, utóbbi bezárja az ablakot.
	 * Figyelmeztetést kap a felhasználó, és nem lehetséges a hozzáadás, amennyiben:
	 *  - Nincs megadva valamelyik paraméter a szövegdobozban
	 *  - Vagy nem szám lett beírva a kiadási évhez.
	 */
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("hozzaadas")) {
				String eloado, cim, kiado, ev;
				eloado = jtf1.getText();
				cim = jtf2.getText();
				ev = jtf3.getText();
				kiado = jtf4.getText();
				if(eloado.equals("") || cim.equals("") || kiado.equals("") || ev.equals("")) {
					new FigyelmeztetesAblak("Hiba: Minden mezõt ki kell tölteni.");
				}
				else {
					try {
						int evszam = Integer.parseInt(ev);					
						Album a = new Album(eloado,cim,evszam,kiado);
						lista.add(a);
						HozzaadasAblak.this.dispose();
						
					}
					catch(NumberFormatException nfe) {
						new FigyelmeztetesAblak("Hiba: Nem szám lett megadva kiadási évnek.");
					}
				}
			}
			else if(e.getActionCommand().equals("megse")) {
				HozzaadasAblak.this.dispose();
			}
		}
		
	}
}