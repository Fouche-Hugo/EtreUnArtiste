package fr.eseo.pdlo.projet.artiste.controleur.outils;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import fr.eseo.pdlo.projet.artiste.vue.ihm.PanneauDessin;

public class OutilEllipseTest {
	public OutilEllipseTest() {
		this.testDessinEllipse();
	}
	
	private void testDessinEllipse() {
		JFrame maFenetre = new JFrame("Etre un Artiste");
		PanneauDessin panneau = new PanneauDessin(800, 600,
				new Color(255, 255, 0));
		
		OutilEllipse outil = new OutilEllipse();
		
		panneau.associerOutil(outil);
		
		maFenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		maFenetre.getContentPane().add(panneau, BorderLayout.NORTH);
		maFenetre.pack();
		maFenetre.setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new OutilEllipseTest();
			}
		});
	}
}
