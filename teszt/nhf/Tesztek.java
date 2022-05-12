package nhf;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit teszt oszt�ly.
 * Egy egyszer� tesztk�rnyezet, �s 3 oszt�ly �sszesen 10 darab tesztesete.
 * @author S�ri �kos
 * @version 1.0
 */
public class Tesztek {
	
	Album a1, a2;
	AlbumLista lista;
	AlbumTableModel tm;
	
	/**
	 * Tesztk�rnyezet fel�ll�t�sa, amin a tesztek v�grehajt�dnak.
	 */
	@Before
	public void setUp() {
		a1 = new Album("Teszt egy","Egyik album",1948,"Kiad�");
		a2 = new Album("Teszt kett�","M�sik album",1942,"Kiad�");
		lista = new AlbumLista();
		lista.add(a1);
		lista.add(a2);
		tm = new AlbumTableModel(lista);
	}
	
	/**
	 * Annak tesztel�se, hogy a 2 album t�nyleg elt�r� azonos�t�val rendelkezik-e.
	 */
	@Test 
	public void test_UniqueID() {
		Assert.assertNotSame(a1.getID(), a2.getID());
	}
	
	/**
	 * Ellen�rz�s, hogy a list�hoz t�nyleg hozz�ad�dnak-e az albumok.
	 */
	@Test
	public void test_ListaHozzaadas() {
		Assert.assertEquals(2, lista.size());
	}
	
	/**
	 * Nyilv�ntart�sb�l val� t�rl�s tesztel�se, l�tez� �s nem l�tez�
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
	 * Lista index alapj�n t�rt�n� album lek�rdez�s tesztel�se, val�s �rt�kkel.
	 * Ilyenkor a megfelel� albumot kell kapjuk eredm�nyk�nt.
	 */
	@Test
	public void test_ValidGetAlbumFromLista() {
		Album result = lista.get(0);
		Assert.assertEquals(a1, result);
	}
	
	/**
	 * Bet�lt�s tesztel�se nem l�tez� f�jl eset�n.
	 * Ilyenkor IOException kiv�telt v�runk.
	 * @throws Exception Kiv�telt dob, a hib�s, nem l�tez� f�jl miatt.
	 */
	@Test(expected=IOException.class)
	public void test_InvalidBetoltes() throws Exception {
		File nemletezo = new File("valami.alb");
		lista.listaBetoltes(nemletezo.getCanonicalPath());
	}
	
	/**
	 * AlbumTableModel tesztel�se cella szerkeszthet�s�gre.
	 * Az els� (ID) oszlop nem lehet szerkeszthet�, a t�bbi igen.
	 */
	@Test
	public void test_EditableCells() {
		boolean result_ID = tm.isCellEditable(0, 0); 
		boolean result_nev = tm.isCellEditable(0, 1); 
		Assert.assertEquals(false, result_ID);
		Assert.assertEquals(true, result_nev);
	}
	
	/**
	 * AlbumTableModel tesztel�se arra, hogy a megfelel� adat jelenik-e meg a modellben.
	 */
	@Test
	public void test_CellContent() {
		Object result = tm.getValueAt(0,1);
		Assert.assertEquals(a1.getEloado(),result);
	}
	
	/**
	 * AlbumTableModel tesztel�se arra, hogy mi t�rt�nik, ha esetleg olyan cell�b�l
	 * k�rn�nk le adatot, amely nem is l�tezik.
	 */
	@Test(expected=IndexOutOfBoundsException.class)
	public void test_InvalidRow() {
		Assert.assertEquals(null, tm.getValueAt(999, 999));
	}
	
	/**
	 * AlbumTableModel tesztel�se szerkeszthet�s�gre.
	 */
	@Test
	public void test_SetCellValue() {
		tm.setValueAt("�t�r�s", 0, 1);
		Assert.assertEquals("�t�r�s", tm.getValueAt(0, 1));
	}
	
	/**
	 * Album oszt�ly matchesSearch(String str) met�dus�nak tesztel�se, id�intervallumra �s kulcssz�ra egyar�nt.
	 */
	@Test
	public void test_MatchesSearch() {
		boolean result_t1 = a1.matchesSearch("1947-1950");
		boolean result_t2 = a2.matchesSearch("kett�");
		boolean result_f = a1.matchesSearch("valami, amit nem tartalmaz");
		Assert.assertEquals(true, result_t1);
		Assert.assertEquals(true, result_t2);
		Assert.assertEquals(false, result_f);
	}
	
}
