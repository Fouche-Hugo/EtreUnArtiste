package fr.eseo.pdlo.projet.artiste.controleur.outils;

import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import fr.eseo.pdlo.projet.artiste.controleur.actions.ActionSelectionner;
import fr.eseo.pdlo.projet.artiste.modele.Coordonnees;
import fr.eseo.pdlo.projet.artiste.modele.formes.Forme;

public class OutilSelectionner extends Outil{
	
	@Override
	public void mouseClicked(MouseEvent event) {
		Coordonnees coordsClic = new Coordonnees((double)event.getX(), (double)event.getY());
		Forme formeSelectionnee = null;
		int compteur = this.getPanneauDessin().getVueFormes().size();
		boolean fin = false;
		
		while(!fin) {
			compteur--;
			if(compteur < 0) {
				fin = true;
			} else {
				if(this.getPanneauDessin().getVueFormes().get(compteur).getForme().contient(coordsClic)) {
					formeSelectionnee = this.getPanneauDessin().getVueFormes().get(compteur).getForme();
					fin = true;
				}
			}
		}
		
		if(formeSelectionnee != null) {
			JOptionPane.showMessageDialog(this.getPanneauDessin(), formeSelectionnee.toString(),
					ActionSelectionner.NOM_ACTION, JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
