package nhf;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
/**
 * �j album nyilv�ntart�shoz ad�s�ra szolg�l� ablakot reprezent�l� oszt�ly.
 * Bels� oszt�lya arra szolg�l, hogy reag�ljon a gombnyom�sokra. 
 * @author S�ri �kos
 */
public class HozzaadasAblak extends JFrame {

	private static final long serialVersionUID = 954655290978078500L;
	
	private JTextField jtf1;
	private JTextField jtf2;
	private JTextField jtf3;
	private JTextField jtf4;
	
	private AlbumLista lista;
	/**
	 * Konstru�lja �s megjelen�ti az �j album hozz�ad�s�ra szolg�l� ablakot.
	 * Ebben a hozz�adand� album 4 param�ter�t lehet megadni.
	 * Param�ter�l kapja a nyilv�ntart�st, az azon t�rt�n� m�veletv�gz�shez.
	 * @param lista Az albumok nyilv�ntart�sa.
	 */
	public HozzaadasAblak(AlbumLista lista) {
		super("�j album hozz�ad�sa");
		this.lista = lista;
		GridLayout grid = new GridLayout();
		grid.setRows(5);
		grid.setColumns(2);
		this.setLayout(grid);
		this.setSize(400,200);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JLabel l1 = new JLabel("El�ad�:");
		JLabel l2 = new JLabel("Album c�me:");
		JLabel l3 = new JLabel("Kiad�si �v:");
		JLabel l4 = new JLabel("Kiad�:");
		this.jtf1 = new JTextField("");
		this.jtf2 = new JTextField("");
		this.jtf3 = new JTextField("");
		this.jtf4 = new JTextField("");
		JButton b1 = new JButton("Hozz�ad�s");
		JButton b2 = new JButton("M�gse");
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
	 * Gombnyom�sok (Hozz�ad�s, M�gse) lekezel�s�re szolg�l�, ActionListener interfacet megval�s�t� bels� priv�t oszt�ly.
	 * El�bbi hozz�adja a nyilv�ntart�shoz a be�rt adatok alapj�n az �j albumot, ut�bbi bez�rja az ablakot.
	 * Figyelmeztet�st kap a felhaszn�l�, �s nem lehets�ges a hozz�ad�s, amennyiben:
	 *  - Nincs megadva valamelyik param�ter a sz�vegdobozban
	 *  - Vagy nem sz�m lett be�rva a kiad�si �vhez.
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
					new FigyelmeztetesAblak("Hiba: Minden mez�t ki kell t�lteni.");
				}
				else {
					try {
						int evszam = Integer.parseInt(ev);					
						Album a = new Album(eloado,cim,evszam,kiado);
						lista.add(a);
						HozzaadasAblak.this.dispose();
						
					}
					catch(NumberFormatException nfe) {
						new FigyelmeztetesAblak("Hiba: Nem sz�m lett megadva kiad�si �vnek.");
					}
				}
			}
			else if(e.getActionCommand().equals("megse")) {
				HozzaadasAblak.this.dispose();
			}
		}
		
	}
}