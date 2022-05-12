package nhf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * A f�men�t reprezent�l� oszt�ly.
 * @author S�ri �kos
 */
public class Fomenu extends JFrame{
	private static final long serialVersionUID = -1175028015325626849L;
	
	//gombok
	private JButton ujalbum;
	private JButton albumok;
	private JButton kereses;
	private JButton torles;
	private JButton db_betoltes;
	private JButton db_mentes;
	
	/**
	 * L�trehozza a nyilv�ntart�st.
	 */
	public AlbumLista lista = new AlbumLista();

	/**
	 * Konstru�lja �s megjelen�ti a f�men�t.
	 */
	public Fomenu() {
		super("Album Nyilv�ntart�s");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600,400);
		this.setResizable(true);
		GridLayout grid = new GridLayout();
		grid.setRows(2);
		grid.setColumns(3);
		this.setLayout(grid);
		this.setLocationRelativeTo(null);
		
		//gombok l�trehoz�sa �s c�mk�z�se
		ujalbum = new JButton("�j album hozz�ad�sa");
		albumok = new JButton("Albumok list�ja");
		kereses = new JButton("Keres�s");
		torles = new JButton("Album t�rl�se");
		db_betoltes = new JButton("Adatb�zis bet�lt�se");
		db_mentes = new JButton("Adatb�zis ment�se");
		
		//action commandok �ll�t�sa, hogy gomb �tc�mk�z�s eset�n ne kelljen �t�rni a listenert
		ujalbum.setActionCommand("ujalbum");
		albumok.setActionCommand("albumok");
		kereses.setActionCommand("kereses_menupont");
		torles.setActionCommand("torles_menupont");
		db_betoltes.setActionCommand("db_betoltes");
		db_mentes.setActionCommand("db_mentes");
		ActionListener al = new FomenuListener(lista);
		ujalbum.addActionListener(al);
		albumok.addActionListener(al);
		kereses.addActionListener(al);
		torles.addActionListener(al);
		db_betoltes.addActionListener(al);
		db_mentes.addActionListener(al);
		
		//gombok hozz�ad�sa
		this.add(ujalbum);
		this.add(albumok);
		this.add(kereses);
		this.add(torles);
		this.add(db_betoltes);
		this.add(db_mentes);
	}
}
