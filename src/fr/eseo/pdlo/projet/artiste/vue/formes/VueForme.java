package fr.eseo.pdlo.projet.artiste.vue.formes;

import fr.eseo.pdlo.projet.artiste.modele.formes.Forme;

abstract public class VueForme {
	protected final Forme forme;
	
	public VueForme(Forme forme) {
		this.forme = forme;
	}
	
	public Forme getForme() {
		return this.forme;
	}
	
	abstract public void affiche(java.awt.Graphics2D g2d);
}
