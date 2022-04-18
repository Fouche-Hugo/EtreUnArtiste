package fr.eseo.pdlo.projet.artiste.controleur.outils;

import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import fr.eseo.pdlo.projet.artiste.controleur.actions.ActionSelectionner;
import fr.eseo.pdlo.projet.artiste.modele.Coordonnees;

public class OutilSelectionForme extends Outil {

	@Override
	public void mouseClicked(MouseEvent event) {
		Coordonnees coordsClic = new Coordonnees((double)event.getX(), (double)event.getY());
		int compteur = this.getPanneauDessin().getVueFormes().size();
		boolean fin = false;
		boolean formeSelectionnee = false;
		
		while(!fin) {
			compteur--;
			if(compteur < 0) {
				fin = true;
			} else {
				if(this.getPanneauDessin().getVueFormes().get(compteur).getForme().contient(coordsClic)) {
					fin = true;
					formeSelectionnee = true;
				}
			}
		}
		
		if(formeSelectionnee) {
			JOptionPane.showMessageDialog(this.getPanneauDessin(), "SIUUUUUUUUU",
					ActionSelectionner.NOM_ACTION, JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
