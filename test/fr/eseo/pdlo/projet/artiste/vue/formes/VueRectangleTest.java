package fr.eseo.pdlo.projet.artiste.vue.formes;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import fr.eseo.pdlo.projet.artiste.modele.Coordonnees;
import fr.eseo.pdlo.projet.artiste.modele.formes.Rectangle;
import fr.eseo.pdlo.projet.artiste.vue.ihm.PanneauDessin;

public class VueRectangleTest {
	public VueRectangleTest() {
		this.testDessinRectangle();
	}
	
	private void testDessinRectangle() {
		JFrame maFenetre = new JFrame("Etre un Artiste");
		PanneauDessin panneau = new PanneauDessin(800, 600, new Color(0, 0, 0));
		
		panneau.ajouterVueForme(new VueRectangle(new Rectangle(new Coordonnees(0, 0), 200, 50)));
		panneau.ajouterVueForme(new VueRectangle(new Rectangle(new Coordonnees(200, 100), 1, 10)));
		panneau.ajouterVueForme(new VueRectangle(new Rectangle(new Coordonnees(400, 400), 256, 360)));
		
		maFenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		maFenetre.getContentPane().add(panneau, BorderLayout.NORTH);
		maFenetre.pack();
		maFenetre.setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new VueRectangleTest();
			}
		});
	}
}
