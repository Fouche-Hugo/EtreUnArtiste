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
			//Test si la forme est déja selectionnée
			if(this.getPanneauDessin().getVuesFormesSelectionnees().contains(this.getPanneauDessin().getVueFormes().get(compteur))) {
				//Alors on la retire
				this.getPanneauDessin().getVuesFormesSelectionnees().remove(this.getPanneauDessin().getVueFormes().get(compteur));
			} else {
				//Sinon on l'ajoute
				boolean placee = false;
				short i = 0;
				if(this.getPanneauDessin().getVuesFormesSelectionnees().size() == 0) {
					placee = true;
				}
				while(!placee) {
					if(this.getPanneauDessin().estDevant(this.getPanneauDessin().getVueFormes().get(compteur),
						this.getPanneauDessin().getVuesFormesSelectionnees().get(i))) {
							i++;
					} else {
						placee = true;
					}
					if(i == this.getPanneauDessin().getVuesFormesSelectionnees().size()) {
						placee = true;
					}
				}
				this.getPanneauDessin().getVuesFormesSelectionnees().add(i, this.getPanneauDessin().getVueFormes().get(compteur));
			}
			//Si jamais il y a au moins 1 forme de selectionnée
			if(this.getPanneauDessin().getVuesFormesSelectionnees().size() > 0) {
				this.panneauBarreOutils.activerBoutonsSelection(true);
			} else {
				this.panneauBarreOutils.activerBoutonsSelection(false);
			}
		} else {
			//Si on clic sur aucune forme, on enlève toute la sélection
			this.getPanneauDessin().getVuesFormesSelectionnees().clear();
			this.panneauBarreOutils.activerBoutonsSelection(false);
		}
		this.getPanneauDessin().repaint();
	}
}
