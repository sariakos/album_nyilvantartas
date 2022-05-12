package nhf;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit teszt osztály.
 * Egy egyszerû tesztkörnyezet, és 3 osztály összesen 10 darab tesztesete.
 * @author Sári Ákos
 * @version 1.0
 */
public class Tesztek {
	
	Album a1, a2;
	AlbumLista lista;
	AlbumTableModel tm;
	
	/**
	 * Tesztkörnyezet felállítása, amin a tesztek végrehajtódnak.
	 */
	@Before
	public void setUp() {
		a1 = new Album("Teszt egy","Egyik album",1948,"Kiadó");
		a2 = new Album("Teszt kettõ","Másik album",1942,"Kiadó");
		lista = new AlbumLista();
		lista.add(a1);
		lista.add(a2);
		tm = new AlbumTableModel(lista);
	}
	
	/**
	 * Annak tesztelése, hogy a 2 album tényleg eltérõ azonosítóval rendelkezik-e.
	 */
	@Test 
	public void test_UniqueID() {
		Assert.assertNotSame(a1.getID(), a2.getID());
	}
	
	/**
	 * Ellenõrzés, hogy a listához tényleg hozzáadódnak-e az albumok.
	 */
	@Test
	public void test_ListaHozzaadas() {
		Assert.assertEquals(2, lista.size());
	}
	
	/**
	 * Nyilvántartásból való törlés tesztelése, létezõ és nem létezõ
	 * albumokkal.
	 */
	@Test
	public void test_ListarolTorles() {
		int db = lista.size();
		boolean result_letezo = lista.removeAlbum(a1.getID());
		boolean result_nemletezo = lista.removeAlbum(23);
		Assert.assertEquals(true,result_letezo);
		Assert.assertEquals(false, result_nemletezo);
		Assert.assertEquals(db-1,lista.size());
	}
	
	/**
	 * Lista index alapján történõ album lekérdezés tesztelése, valós értékkel.
	 * Ilyenkor a megfelelõ albumot kell kapjuk eredményként.
	 */
	@Test
	public void test_ValidGetAlbumFromLista() {
		Album result = lista.get(0);
		Assert.assertEquals(a1, result);
	}
	
	/**
	 * Betöltés tesztelése nem létezõ fájl esetén.
	 * Ilyenkor IOException kivételt várunk.
	 * @throws Exception Kivételt dob, a hibás, nem létezõ fájl miatt.
	 */
	@Test(expected=IOException.class)
	public void test_InvalidBetoltes() throws Exception {
		File nemletezo = new File("valami.alb");
		lista.listaBetoltes(nemletezo.getCanonicalPath());
	}
	
	/**
	 * AlbumTableModel tesztelése cella szerkeszthetõségre.
	 * Az elsõ (ID) oszlop nem lehet szerkeszthetõ, a többi igen.
	 */
	@Test
	public void test_EditableCells() {
		boolean result_ID = tm.isCellEditable(0, 0); 
		boolean result_nev = tm.isCellEditable(0, 1); 
		Assert.assertEquals(false, result_ID);
		Assert.assertEquals(true, result_nev);
	}
	
	/**
	 * AlbumTableModel tesztelése arra, hogy a megfelelõ adat jelenik-e meg a modellben.
	 */
	@Test
	public void test_CellContent() {
		Object result = tm.getValueAt(0,1);
		Assert.assertEquals(a1.getEloado(),result);
	}
	
	/**
	 * AlbumTableModel tesztelése arra, hogy mi történik, ha esetleg olyan cellából
	 * kérnénk le adatot, amely nem is létezik.
	 */
	@Test(expected=IndexOutOfBoundsException.class)
	public void test_InvalidRow() {
		Assert.assertEquals(null, tm.getValueAt(999, 999));
	}
	
	/**
	 * AlbumTableModel tesztelése szerkeszthetõségre.
	 */
	@Test
	public void test_SetCellValue() {
		tm.setValueAt("átírás", 0, 1);
		Assert.assertEquals("átírás", tm.getValueAt(0, 1));
	}
	
	/**
	 * Album osztály matchesSearch(String str) metódusának tesztelése, idõintervallumra és kulcsszóra egyaránt.
	 */
	@Test
	public void test_MatchesSearch() {
		boolean result_t1 = a1.matchesSearch("1947-1950");
		boolean result_t2 = a2.matchesSearch("kettõ");
		boolean result_f = a1.matchesSearch("valami, amit nem tartalmaz");
		Assert.assertEquals(true, result_t1);
		Assert.assertEquals(true, result_t2);
		Assert.assertEquals(false, result_f);
	}
	
}
