package fr.eseo.pdlo.projet.artiste.controleur.outils;

import java.awt.event.MouseEvent;

import fr.eseo.pdlo.projet.artiste.modele.Coordonnees;
import fr.eseo.pdlo.projet.artiste.vue.formes.VueForme;

abstract public class OutilForme extends Outil {
	abstract protected VueForme creerVueForme();
	
	@Override
	public void mouseClicked(MouseEvent event) {
		this.setDebut(new Coordonnees(event.getX(), event.getY()));
		if(event.getClickCount() == 2) {
			this.setFin(new Coordonnees(event.getX(), event.getY()));
			this.getPanneauDessin().ajouterVueForme(this.creerVueForme());
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent event) {
		if(event.getX() != this.getDebut().getAbscisse() || event.getY() != this.getDebut().getOrdonnee()) {
			super.mouseReleased(event);
			this.getPanneauDessin().ajouterVueForme(this.creerVueForme());
		}
	}
}
