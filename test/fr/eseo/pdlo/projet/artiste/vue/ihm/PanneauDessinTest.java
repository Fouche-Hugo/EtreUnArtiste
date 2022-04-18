package fr.eseo.pdlo.projet.artiste.vue.ihm;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class PanneauDessinTest {
	public PanneauDessinTest() {
		testConstructeurParDefaut();
		testConstructeur();
	}
	
	private void testConstructeurParDefaut() {
		JFrame maFenetre = new JFrame("Etre un Artiste");
		PanneauDessin panneau = new PanneauDessin();
		
		maFenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		maFenetre.add(panneau, BorderLayout.NORTH);
		maFenetre.pack();
		maFenetre.setLocationRelativeTo(null);
		maFenetre.setVisible(true);
	}
	
	private void testConstructeur() {
		JFrame maFenetre = new JFrame("Blues du Businessman");
		PanneauDessin panneau = new PanneauDessin(600, 300, new Color(255, 0, 0));
		
		maFenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		maFenetre.add(panneau, BorderLayout.NORTH);
		maFenetre.pack();
		maFenetre.setLocationRelativeTo(null);
		maFenetre.setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new PanneauDessinTest();
			}
		});
	}
}
