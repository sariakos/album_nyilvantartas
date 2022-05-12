package nhf;

/**
 * Elindítja a programot, megjeleníti a fõmenüt.
 * @author Sári Ákos
 * @version 1.0
 */
public class Main {

	/**
	 * A program belépési pontja, létrehozza és megjeleníti a fõmenüt.
	 * @param args indítási argumentumok
	 */
	public static void main(String[] args) {
		Fomenu ablak = new Fomenu();
		ablak.setVisible(true);
	}

}
