package fr.eseo.pdlo.projet.artiste.controleur.outils;

import java.awt.event.MouseEvent;

import fr.eseo.pdlo.projet.artiste.modele.Coordonnees;
import fr.eseo.pdlo.projet.artiste.vue.ihm.PanneauBarreOutils;

public class OutilSelectionForme extends Outil {

	private PanneauBarreOutils panneauBarreOutils;

	public OutilSelectionForme(PanneauBarreOutils panneauBarreOutils) {
		this.panneauBarreOutils = panneauBarreOutils;
	}

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
			this.getPanneauDessin().setFormeSelectionneeOn(true);
			this.getPanneauDessin().setIndexFormeSelectionnee(compteur);
			this.panneauBarreOutils.activerBoutonsSelection(true);
		} else {
			this.getPanneauDessin().setFormeSelectionneeOn(false);
			this.panneauBarreOutils.activerBoutonsSelection(false);
		}
	}
}
