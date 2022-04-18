package fr.eseo.pdlo.projet.artiste.controleur.actions;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import fr.eseo.pdlo.projet.artiste.modele.Coordonnees;
import fr.eseo.pdlo.projet.artiste.modele.formes.Cercle;
import fr.eseo.pdlo.projet.artiste.vue.formes.VueCercle;
import fr.eseo.pdlo.projet.artiste.vue.ihm.PanneauBarreEtat;
import fr.eseo.pdlo.projet.artiste.vue.ihm.PanneauBarreOutils;
import fr.eseo.pdlo.projet.artiste.vue.ihm.PanneauDessin;

public class ActionChoisirCouleurTest {
	public ActionChoisirCouleurTest() {
		this.testConstructeur();
	}
	
	private void testConstructeur() {
		JFrame maFenetre = new JFrame("Etre un Artiste");
		PanneauDessin panneau = new PanneauDessin();
		
		PanneauBarreEtat barre = new PanneauBarreEtat(panneau);
		
		panneau.ajouterVueForme(new VueCercle(new Cercle(new Coordonnees(0, 0), 200)));
		panneau.ajouterVueForme(new VueCercle(new Cercle(new Coordonnees(200, 100), 100)));
		panneau.ajouterVueForme(new VueCercle(new Cercle(new Coordonnees(400, 400), 256)));
		
		PanneauBarreOutils panneauOutils = new PanneauBarreOutils(panneau);
		
		maFenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		maFenetre.add(panneauOutils, BorderLayout.EAST);
		maFenetre.add(panneau);
		maFenetre.add(barre, BorderLayout.SOUTH);
		maFenetre.pack();
		maFenetre.setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new ActionChoisirCouleurTest();
			}
		});
	}
}
