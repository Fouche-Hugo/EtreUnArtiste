package fr.eseo.pdlo.projet.artiste;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import fr.eseo.pdlo.projet.artiste.vue.ihm.PanneauBarreEtat;
import fr.eseo.pdlo.projet.artiste.vue.ihm.PanneauBarreOutils;
import fr.eseo.pdlo.projet.artiste.vue.ihm.PanneauDessin;

public class EtreUnArtiste {
	public EtreUnArtiste(String titre, int largeur, int hauteur) {
		this.launchApp(titre, largeur, hauteur);
	}
	
	public EtreUnArtiste() {
		this("Etre un Artiste", 640, 480);
	}
	
	private void launchApp(String titre, int largeur, int hauteur) {
		JFrame maFenetre = new JFrame(titre);
		PanneauDessin panneau = new PanneauDessin(largeur, hauteur, Color.WHITE);
		
		PanneauBarreEtat barre = new PanneauBarreEtat(panneau);
		
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
				if(args.length == 0) {
					new EtreUnArtiste();
				} else {
					String titre = args[0];
					int largeur = 0;
					int hauteur = 0;
					try {
						largeur = Math.abs(Integer.parseInt(args[1]));
					} catch(Throwable e) {
						largeur = 640;
					}
					try {
						hauteur = Math.abs(Integer.parseInt(args[2]));
					}
					catch (Throwable e) {
						hauteur = 480;
					}
					new EtreUnArtiste(titre, largeur, hauteur);
				}
			}
		});
	}
}