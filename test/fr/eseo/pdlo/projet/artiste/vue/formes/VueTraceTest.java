package fr.eseo.pdlo.projet.artiste.vue.formes;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import fr.eseo.pdlo.projet.artiste.modele.Coordonnees;
import fr.eseo.pdlo.projet.artiste.modele.formes.Trace;
import fr.eseo.pdlo.projet.artiste.vue.ihm.PanneauDessin;

public class VueTraceTest {
	public VueTraceTest() {
		this.testDessinTrace();
	}
	
	private void testDessinTrace() {
		JFrame maFenetre = new JFrame("Etre un Artiste");
		PanneauDessin panneau = new PanneauDessin(800, 600, new Color(0, 0, 0));
		
		Trace trace = new Trace(new Coordonnees(100, 100), new Coordonnees(200, 300));
		trace.ajouterPoint(new Coordonnees(500, 500));
		trace.ajouterPoint(new Coordonnees(200, 100));
		
		panneau.ajouterVueForme(new VueTrace(trace));
		
		maFenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		maFenetre.getContentPane().add(panneau, BorderLayout.NORTH);
		maFenetre.pack();
		maFenetre.setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new VueTraceTest();
			}
		});
	}
}
