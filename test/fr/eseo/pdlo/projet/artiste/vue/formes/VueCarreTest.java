package fr.eseo.pdlo.projet.artiste.vue.formes;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import fr.eseo.pdlo.projet.artiste.modele.Coordonnees;
import fr.eseo.pdlo.projet.artiste.modele.formes.Carre;
import fr.eseo.pdlo.projet.artiste.vue.ihm.PanneauDessin;

public class VueCarreTest {
	public VueCarreTest() {
		this.testDessinCarre();
	}
	
	private void testDessinCarre() {
		JFrame maFenetre = new JFrame("Etre un Artiste");
		PanneauDessin panneau = new PanneauDessin(800, 800, new Color(0, 0, 0));
		
		panneau.ajouterVueForme(new VueCarre(new Carre(new Coordonnees(0, 0), 200)));
		panneau.ajouterVueForme(new VueCarre(new Carre(new Coordonnees(200, 100), 100)));
		panneau.ajouterVueForme(new VueCarre(new Carre(new Coordonnees(400, 400), 256)));
		
		maFenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		maFenetre.getContentPane().add(panneau, BorderLayout.NORTH);
		maFenetre.pack();
		maFenetre.setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new VueCarreTest();
			}
		});
	}
}
