package fr.eseo.pdlo.projet.artiste.vue.ihm;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class PanneauBarreEtatTest {
	public PanneauBarreEtatTest() {
		this.testConstructeur();
	}
	
	private void testConstructeur() {
		JFrame maFenetre = new JFrame("Etre un Artiste");
		PanneauDessin panneau = new PanneauDessin();
		
		PanneauBarreEtat barre = new PanneauBarreEtat(panneau);
		
		maFenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		maFenetre.getContentPane().add(panneau, BorderLayout.NORTH);
		maFenetre.getContentPane().add(barre, BorderLayout.SOUTH);
		maFenetre.pack();
		maFenetre.setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new PanneauBarreEtatTest();
			}
		});
	}
}
