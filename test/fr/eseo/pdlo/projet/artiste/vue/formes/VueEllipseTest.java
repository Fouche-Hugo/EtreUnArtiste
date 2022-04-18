package fr.eseo.pdlo.projet.artiste.vue.formes;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import fr.eseo.pdlo.projet.artiste.modele.Coordonnees;
import fr.eseo.pdlo.projet.artiste.modele.Remplissage;
import fr.eseo.pdlo.projet.artiste.modele.formes.Ellipse;
import fr.eseo.pdlo.projet.artiste.vue.ihm.PanneauDessin;

public class VueEllipseTest {
	public VueEllipseTest() {
		this.testDessinEllipse();
	}
	
	private void testDessinEllipse() {
		JFrame maFenetre = new JFrame("Etre un Artiste");
		PanneauDessin panneau = new PanneauDessin(800, 600, new Color(0, 0, 0));
		
		panneau.ajouterVueForme(new VueEllipse(new Ellipse(new Coordonnees(0, 0), 200, 100)));
		panneau.ajouterVueForme(new VueEllipse(new Ellipse(new Coordonnees(200, 100), 100, 100)));
		panneau.ajouterVueForme(new VueEllipse(new Ellipse(new Coordonnees(400, 400), 256, 360)));
		
		VueEllipse ellipseRemplie = new VueEllipse(new Ellipse(new Coordonnees(150, 150), 300, 200));
		((Ellipse)ellipseRemplie.getForme()).setRemplissage(Remplissage.UNIFORME);
		panneau.ajouterVueForme(ellipseRemplie);
		
		maFenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		maFenetre.getContentPane().add(panneau, BorderLayout.NORTH);
		maFenetre.pack();
		maFenetre.setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new VueEllipseTest();
			}
		});
	}
}
