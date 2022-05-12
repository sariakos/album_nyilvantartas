package nhf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * A fõmenüt reprezentáló osztály.
 * @author Sári Ákos
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
	 * Létrehozza a nyilvántartást.
	 */
	public AlbumLista lista = new AlbumLista();

	/**
	 * Konstruálja és megjeleníti a fõmenüt.
	 */
	public Fomenu() {
		super("Album Nyilvántartás");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600,400);
		this.setResizable(true);
		GridLayout grid = new GridLayout();
		grid.setRows(2);
		grid.setColumns(3);
		this.setLayout(grid);
		this.setLocationRelativeTo(null);
		
		//gombok létrehozása és címkézése
		ujalbum = new JButton("Új album hozzáadása");
		albumok = new JButton("Albumok listája");
		kereses = new JButton("Keresés");
		torles = new JButton("Album törlése");
		db_betoltes = new JButton("Adatbázis betöltése");
		db_mentes = new JButton("Adatbázis mentése");
		
		//action commandok állítása, hogy gomb átcímkézés esetén ne kelljen átírni a listenert
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
		
		//gombok hozzáadása
		this.add(ujalbum);
		this.add(albumok);
		this.add(kereses);
		this.add(torles);
		this.add(db_betoltes);
		this.add(db_mentes);
	}
}
